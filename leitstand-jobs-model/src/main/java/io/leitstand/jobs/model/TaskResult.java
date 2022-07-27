package io.leitstand.jobs.model;

import static io.leitstand.jobs.service.State.ACTIVE;
import static io.leitstand.jobs.service.State.COMPLETED;
import static io.leitstand.jobs.service.State.FAILED;
import static io.leitstand.jobs.service.State.REJECTED;
import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.List;

import io.leitstand.jobs.service.JobTaskMessage;
import io.leitstand.jobs.service.State;

public class TaskResult {

    public static TaskResult failed() {
        return new TaskResult(FAILED,emptyList());
    }
    
    public static TaskResult failed(List<JobTaskMessage> messages) {
    	return new TaskResult(FAILED,new ArrayList<>(messages));
    }
    
    public static TaskResult failed(JobTaskMessage.Builder... messages) {
        return new TaskResult(FAILED,stream(messages)
        							 .map(JobTaskMessage.Builder::build)
        							 .collect(toList()));
    }

    public static TaskResult rejected() {
        return new TaskResult(REJECTED,emptyList());
    }
    
    public static TaskResult rejected(JobTaskMessage.Builder... messages) {
        return new TaskResult(REJECTED,stream(messages)
        							   .map(JobTaskMessage.Builder::build)
        							   .collect(toList()));
    }
   
    public static TaskResult rejected(List<JobTaskMessage> messages) {
    	return new TaskResult(REJECTED,new ArrayList<>(messages));
    }
    
    public static TaskResult completed(JobTaskMessage.Builder... messages) {
    	return new TaskResult(COMPLETED,stream(messages)
				 						.map(JobTaskMessage.Builder::build)
				 						.collect(toList()));
    }
    
    public static TaskResult completed(List<JobTaskMessage> messages) {
    	return new TaskResult(COMPLETED,new ArrayList<>(messages));
    }
    
    public static TaskResult completed() {
        return new TaskResult(COMPLETED,emptyList());
    }
    
    public static TaskResult active() {
        return new TaskResult(ACTIVE,emptyList());
    }
    
    private TaskResult(State taskState, List<JobTaskMessage> messages) {
        this.taskState = taskState;
        this.messages = messages;
    }
    
    
    private State taskState;
    private List<JobTaskMessage> messages;
    
    public State getTaskState() {
        return taskState;
    }
    
    public List<JobTaskMessage> getMessages() {
        return messages;
    }
    
}
