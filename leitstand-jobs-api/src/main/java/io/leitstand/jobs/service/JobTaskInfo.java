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

import static io.leitstand.commons.model.BuilderUtil.assertNotInvalidated;

/**
 * A task executed as part of a job. 
 * <p>
 * The <code>JobTaskInfo</code> object enhances the job task information with meta data of the job that owns the task.
 */
public class JobTaskInfo extends JobTask{

	/**
	 * Creates a <code>JobTaskInfo</code> value object builder.
	 * @return a <code>JobTaskInfo</code> value object builder.
	 */
	public static Builder newJobTaskInfo(){
		return new Builder();
	}
	
	/**
	 * The <code>JobTaskInfo</code> value object builder.
	 */
	public static class Builder extends JobTask.JobTaskBuilder<JobTaskInfo,Builder>{
		
		Builder(){
			super(new JobTaskInfo());
		}
		
		/**
		 * Sets the job ID.
		 * @param jobId the job ID.
		 * @return a reference to this builder to continue object creation.
		 */
		public Builder withJobId(JobId jobId){
			assertNotInvalidated(getClass(), object);
			object.jobId = jobId;
			return this;
		}

		/**
		 * Sets the job name.
		 * @param jobName the job name.
		 * @return a reference to this builder to continue object creation.
		 */
		public Builder withJobName(JobName jobName){
			assertNotInvalidated(getClass(), object);
			object.jobName = jobName;
			return this;
		}

		/**
		 * Sets the job type.
		 * @param jobType the job type.
		 * @return a reference to this builder to continue object creation.
		 */
		public Builder withJobType(JobType jobType){
			assertNotInvalidated(getClass(), object);
			object.jobType = jobType;
			return this;
		}
		
		
		/**
		 * Sets the job application.
		 * @param jobApplication the job application.
		 * @return a reference to this builder to continue object creation.
		 */
		public Builder withJobApplication(JobApplication jobApplication){
			assertNotInvalidated(getClass(), object);
			object.jobApplication = jobApplication;
			return this;
		}
		
	}
	
	private JobId jobId;
	
	private JobName jobName;
	
	private JobType jobType;
	
	private JobApplication jobApplication;
	
	
	/**
	 * Returns the job ID.
	 * @return the job ID.
	 */
	public JobId getJobId() {
		return jobId;
	}
	
	/**
	 * Returns the job name.
	 * @return the job name.
	 */
	public JobName getJobName() {
		return jobName;
	}

	/**
	 * Returns the job type.
	 * @return the job type.
	 */
	public JobType getJobType() {
		return jobType;
	}

	/**
	 * Return the job application.
	 * @return the job application.
	 */
	public JobApplication getJobApplication() {
		return jobApplication;
	}

}
