/*
 * Copyright 2020 RtBrick Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package io.leitstand.jobs.model;

import static io.leitstand.jobs.service.JobId.randomJobId;
import static io.leitstand.jobs.service.JobName.jobName;
import static io.leitstand.jobs.service.TaskId.randomTaskId;
import static io.leitstand.jobs.service.TaskState.ACTIVE;
import static io.leitstand.jobs.service.TaskState.COMPLETED;
import static io.leitstand.jobs.service.TaskState.CONFIRM;
import static io.leitstand.jobs.service.TaskState.FAILED;
import static java.lang.Boolean.TRUE;
import static java.util.Arrays.asList;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.StringReader;

import javax.enterprise.event.Event;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import org.junit.Before;
import org.junit.Test;

public class TaskProcessingServiceTest {

	static JsonObject asJson(String message) {
		try(JsonReader reader = Json.createReader(new StringReader(message))) {
			return reader.readObject();
		}
	}
	
	
	private TaskProcessingService service;
	private TaskProcessorDiscoveryService processors;
	private Job_Task task;
	private Job job;
	private TaskProcessor processor;
	private Event<TaskStateChangedEvent> event;
	
	
	@Before
	public void initTestResources() {
		processors = mock(TaskProcessorDiscoveryService.class);
		event = mock(Event.class);
		service = new TaskProcessingService(processors, event);
		job = mock(Job.class);
		when(job.getJobId()).thenReturn(randomJobId());
		when(job.getJobName()).thenReturn(jobName("unit-job_name"));
		task = mock(Job_Task.class);
		when(task.getJob()).thenReturn(job);
		when(task.getTaskId()).thenReturn(randomTaskId());
		processor = mock(TaskProcessor.class);
		when(processors.findElementTaskProcessor(task)).thenReturn(processor);
	}
	
	@Test
	public void complete_task_and_job_when_processor_completes_task() {
	    when(processor.execute(task)).thenReturn(COMPLETED);
	    when(task.isSucceeded()).thenReturn(true);
	    
	    
		service.executeTask(task);
	
		verify(task).setTaskState(COMPLETED);
		verify(job).completed();
	}	
	
	@Test
    public void job_failed_when_processor_reports_error() {
        when(processor.execute(task)).thenReturn(FAILED);
        when(task.isFailed()).thenReturn(true);
        
        service.executeTask(task);
    
        verify(task).setTaskState(FAILED);
        verify(job).failed();
    }
	
    @Test
    public void do_not_touch_job_state_for_asynchronous_tasks() {
        when(processor.execute(task)).thenReturn(ACTIVE);
        
        service.executeTask(task);
    
        verify(task).setTaskState(ACTIVE);
        verify(job,never()).failed();
        verify(job,never()).completed();
    }
	
    @Test
    public void set_job_to_CONFIRM_state_when_canary_task_is_completed() {
        when(task.isCanary()).thenReturn(true);
        when(task.isActive()).thenReturn(true);
        
        service.updateTask(task,COMPLETED);
        verify(task).setTaskState(CONFIRM);
        verify(job).setJobState(CONFIRM);
        verify(event).fire(any(TaskStateChangedEvent.class));
        verifyZeroInteractions(processor);
        
    }
	
   
   @Test
   public void ask_not_for_conformation_if_canary_task_failed() {
       when(task.isCanary()).thenReturn(true);
       when(task.isFailed()).thenReturn(true);
       
       service.updateTask(task,FAILED);
       verify(task).setTaskState(FAILED);
       verify(event).fire(any(TaskStateChangedEvent.class));
       verify(job).failed();
       verifyZeroInteractions(processor);
   }
   
   @Test
   public void mark_job_as_FAILED_when_task_is_failed() {
       when(task.isFailed()).thenReturn(true);
       
       service.updateTask(task,FAILED);
  
       verify(task).setTaskState(FAILED);
       verify(job).failed();
       verify(event).fire(any(TaskStateChangedEvent.class));
       verifyZeroInteractions(processor);
   }
   
   @Test
   public void attempt_to_complete_job_when_task_was_completed() {
       Job_Task task = mock(Job_Task.class); // Task w/o assigned processor
       when(task.getJob()).thenReturn(job);
       when(task.isSucceeded()).thenReturn(true);
       
       service.executeTask(task);
       
       verify(task).setTaskState(COMPLETED);
       verify(job).completed();
   }
   
   @Test
   public void attempt_to_complete_job_when_task_state_set_to_completed() {
       service.updateTask(task,COMPLETED);
       verify(job).completed();
   }
   
   @Test
   public void ask_not_for_conformation_to_execute_canary_task() {
       when(task.isCanary()).thenReturn(true);
       
       service.updateTask(task,ACTIVE);
       verify(task).setTaskState(ACTIVE);
       verify(event).fire(any(TaskStateChangedEvent.class));
       verifyZeroInteractions(processor);
   }
   
   @Test
   public void set_job_to_ACTIVE_state_when_canary_task_is_confirmed() {
       when(task.isCanary()).thenReturn(TRUE);
       when(task.isSuspended()).thenReturn(TRUE);
       Job_Task successor = mock(Job_Task.class);
       Job_Task_Transition transition = mock(Job_Task_Transition.class);
       when(transition.getTo()).thenReturn(successor);
       when(task.getSuccessors()).thenReturn(asList(transition));
       
       service.updateTask(task,COMPLETED);
       
       verify(task,never()).setCanary(false); // It remains a canary task
       verify(task).setTaskState(COMPLETED);
       verify(job).confirmed();
       verify(event).fire(any(TaskStateChangedEvent.class));
       verifyZeroInteractions(processor);
       
   }
   
}


