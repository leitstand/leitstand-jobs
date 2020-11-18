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

import static io.leitstand.jobs.model.Job_Task.findTaskById;
import static io.leitstand.jobs.model.Job_Task.markExpiredTasks;
import static io.leitstand.jobs.service.TaskState.TIMEOUT;

import java.util.Date;

import javax.enterprise.event.Event;
import javax.inject.Inject;

import io.leitstand.commons.model.Repository;
import io.leitstand.commons.model.Service;
import io.leitstand.jobs.service.TaskId;

@Service
public class TaskExpiryManager {
	
	@Inject
	private Event<TaskStateChangedEvent> taskEventSink;
	
	@Inject
	@Jobs
	private Repository repository;
	
	public void taskTimedout(Date expired) {
		repository.execute(markExpiredTasks(expired));
	}

	public void taskTimedout(TaskId taskId){
		Job_Task task = repository.execute(findTaskById(taskId));
		task.setTaskState(TIMEOUT);
		taskEventSink.fire(new TaskStateChangedEvent(task));
	}
	
}
