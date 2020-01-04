/*
 * (c) RtBrick, Inc - All rights reserved, 2015 - 2019
 */
package io.leitstand.jobs.service;

import java.util.Date;

import javax.json.bind.annotation.JsonbProperty;

import io.leitstand.commons.model.ValueObject;

public class JobSettings extends ValueObject{

	public static Builder newJobSettings(){
		return new Builder();
	}

	public static class Builder {
		private JobSettings job = new JobSettings();
		
		public Builder withJobApplication(JobApplication application) {
			job.jobApplication = application;
			return this;
		}
		
		public Builder withJobType(JobType type) {
			job.jobType = type;
			return this;
		}
		
		public Builder withJobName(JobName name){
			job.jobName = name;
			return this;
		}
		
		public Builder withJobId(JobId jobId){
			job.jobId = jobId;
			return this;
		}
		
		public Builder withJobState(TaskState state){
			job.jobState = state;
			return this;
		}

		public Builder withSchedule(JobSchedule schedule){
			job.schedule = schedule;
			return this;
		}
		
		public Builder withDateModified(Date date) {
			job.dateModified = new Date(date.getTime());
			return this;
		}
		
		public JobSettings build(){
			try{
				return job;
			} finally {
				this.job = null;
			}
		}


	}

	@JsonbProperty("job_id")
	private JobId jobId;
	
	@JsonbProperty("job_state")
	private TaskState jobState;
	
	@JsonbProperty("job_name")
	private JobName jobName;
	
	@JsonbProperty("job_type")
	private JobType jobType;
	
	@JsonbProperty("job_application")
	private JobApplication jobApplication;
	
	private JobSchedule schedule;
	
	@JsonbProperty("date_modified")
	private Date dateModified;
	
	public JobId getJobId() {
		return jobId;
	}

	public TaskState getJobState() {
		return jobState;
	}
	
	public JobType getJobType() {
		return jobType;
	}
	
	public JobApplication getJobApplication() {
		return jobApplication;
	}

	public JobName getJobName() {
		return jobName;
	}
	
	public JobSchedule getSchedule() {
		return schedule;
	}

	public Date getDateModified() {
		if(dateModified == null) {
			return null;
		}
		return new Date(dateModified.getTime());
	}
	
}
