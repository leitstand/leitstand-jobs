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
package io.leitstand.jobs.service;

import javax.json.JsonObject;

/**
 * A service to read, execute or update a job task.
 */
public interface JobTaskService {
	/**
	 * Returns the job task information of a single task.
	 * @param jobId the job ID
	 * @param taskId the task ID
	 * @return the job task information.
	 */
	JobTaskInfo getJobTask(JobId jobId, TaskId taskId);

	/**
	 * Executes the task with the specified task ID.
	 * @param jobId the job ID
	 * @param taskId the task ID
	 */
	void executeTask(JobId jobId, TaskId taskId);
	
	/**
	 * Updates the task state of the task with the specified ID.
	 * @param jobId the job ID
	 * @param taskId the task ID
	 * @param state thew new task state.
	 */
	void updateTask(JobId jobId, TaskId taskId, State state);
	
	/**
	 * Updates the task parameters of the task with the specified ID.
	 * @param jobId the job ID
	 * @param taskId the task ID
	 * @param parameters the new parameters
	 * @param comment a comment to describe the applied change
	 */
	void setTaskParameter(JobId jobId, TaskId taskId, JsonObject parameters, String comment);
}
