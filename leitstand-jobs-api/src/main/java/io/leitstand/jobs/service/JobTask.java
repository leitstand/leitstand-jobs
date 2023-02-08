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
import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.JsonObject;

import io.leitstand.commons.model.ValueObject;
import io.leitstand.inventory.service.ElementAlias;
import io.leitstand.inventory.service.ElementGroupId;
import io.leitstand.inventory.service.ElementGroupName;
import io.leitstand.inventory.service.ElementGroupType;
import io.leitstand.inventory.service.ElementId;
import io.leitstand.inventory.service.ElementName;
import io.leitstand.inventory.service.ElementRoleName;

/**
 * A task executed as part of a job.
 */
public class JobTask extends ValueObject{
	
	/**
	 * Creates a builder for a <code>JobTask</code> value object.
	 * @return a builder for a <code>JobTask</code> value object.
	 */
	public static Builder newJobTask() {
		return new Builder();
	}

	/**
	 * A base class for <code>JobTask</code> value objects and 
	 * value objects derived from the <code>JobTask</code> class. 
	 */
	public static class JobTaskBuilder<T extends JobTask, B extends JobTaskBuilder<T,B>> {

		protected T object;
		
		JobTaskBuilder(T object){
			this.object = object;
		}
		
		/**
		 * Sets the task ID.
		 * @param taskId the task ID
		 * @return a reference to this builder to continue object creation
		 */
		public B withTaskId(TaskId taskId) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).taskId = taskId;
			return (B) this;
		}

		/**
		 * Sets the task name.
		 * @param taskName the task name
		 * @return a reference to this builder to continue object creation
		 */
		public B withTaskName(TaskName taskName) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).taskName = taskName;
			return (B) this;
		}
		
		/**
		 * Sets the task type.
		 * @param taskType the task type
		 * @return a reference to this builder to continue object creation
		 */
		public B withTaskType(TaskType taskType) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).taskType = taskType;
			return (B) this;
		}
		
		/**
		 * Sets the element ID for tasks related to an element.
		 * @param elementId the element ID
		 * @return a reference to this builder to continue object creation
		 */
		public B withElementId(ElementId elementId) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).elementId = elementId;
			return (B) this;
		}

		/**
		 * Sets the element name for tasks related to an element.
		 * @param elementName the element name
		 * @return a reference to this builder to continue object creation
		 */
		public B withElementName(ElementName elementName) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).elementName = elementName;
			return (B) this;
		}

		/**
		 * Sets the element alias for tasks related to an element.
		 * @param elementAlias the element alias
		 * @return a reference to this builder to continue object creation
		 */
		public B withElementAlias(ElementAlias elementAlias) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).elementAlias = elementAlias;
			return (B) this;
		}

		/**
		 * Sets the element role for tasks related to an element.
		 * @param elementRole the element role
		 * @return a reference to this builder to continue object creation
		 */
		public B withElementRole(ElementRoleName elementRole) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).elementRole = elementRole;
			return (B) this;
		}

		/**
		 * Sets the element group ID for tasks related to an element.
		 * @param groupID the element group ID
		 * @return a reference to this builder to continue object creation
		 */
		public B withGroupId(ElementGroupId groupId) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).groupId = groupId;
			return (B) this;
		}

		/**
		 * Sets the element group name for tasks related to an element.
		 * @param groupName the element group name
		 * @return a reference to this builder to continue object creation
		 */
		public B withGroupName(ElementGroupName groupName) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).groupName = groupName;
			return (B) this;
		}
		
		
		/**
		 * Sets the element group type for tasks related to an element.
		 * @param groupType the element group type
		 * @return a reference to this builder to continue object creation
		 */
		public B withGroupType(ElementGroupType groupType) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).groupType = groupType;
			return (B) this;
		}
		
		/**
		 * Sets the task state.
		 * @param taskState the task state.
		 * @return a reference to this builder to continue object creation
		 */
		public B withTaskState(State taskState) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).taskState = taskState;
			return (B) this;
		}
		
		/**
		 * Sets whether this task is a canary task.
		 * @param canary the canary flag
		 * @return a reference to this builder to continue object creation
		 */
		public B withCanary(boolean canary) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).canary = canary;
			return (B) this;
		}
		
		/**
		 * Sets the task parameters.
		 * @param parameters the task parameter JSON object
		 * @return a reference to this builder to continue object creation
		 */
		public B withParameter(JsonObject parameters) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).parameters = parameters;
			return (B) this;
		}
		
		/**
		 * Sets the last modification date of this task.
		 * @param date the last modification date
		 * @return a reference to this builder to continue object creation
		 */
		public B withDateLastModified(Date date) {
			assertNotInvalidated(getClass(),object);
			((JobTask)object).dateModified = new Date(date.getTime());
			return (B) this;
		}
		
		/**
		 * Sets the journal records of this task.
		 * @param messages the journal records.
		 * @return a reference to this builder to continue object creation.
		 */
		public B withJournal(JobTaskMessage.Builder... messages) {
			return withJournal(stream(messages)
								.map(JobTaskMessage.Builder::build)
								.collect(toList()));
		}

		
		/**
		 * Sets the journal records of this task.
		 * @param messages the journal records.
		 * @return a reference to this builder to continue object creation.
		 */
		public B withJournal(List<JobTaskMessage> messages) {
			assertNotInvalidated(getClass(), object);
			((JobTask)object).journal = new ArrayList<>(messages);
			return (B) this;
		}
		
		/**
		 * Creates the value object and invalidates this builder.
		 * @return the value object.
		 */
		public T build() {
			try {
				assertNotInvalidated(getClass(), object);
				return this.object;
			} finally {
				this.object = null;
			}
		}

	}

	/**
	 * A
	 * @author mast
	 *
	 */
	public static class Builder extends JobTaskBuilder<JobTask,Builder>{

		Builder() {
			super(new JobTask());
		}
	}
	
	private TaskId taskId;

	private TaskName taskName;
	
	private TaskType taskType;
	
	private ElementGroupId groupId;
	
	private ElementGroupName groupName;
	
	private ElementGroupType groupType;
	
	private ElementId elementId;

	private ElementName elementName;
	
	private ElementAlias elementAlias;
	
	private ElementRoleName elementRole;
	
	private State taskState;
	
	private Date dateModified;

	private boolean canary;
	
	private JsonObject parameters;
	
	private List<JobTaskMessage> journal = emptyList();
	
	/**
	 * Returns the task ID.
	 * @return the task ID.
	 */
	public TaskId getTaskId() {
		return taskId;
	}
	
	/**
	 * Returns the task name.
	 * @return the task name.
	 */
	public TaskName getTaskName() {
		return taskName;
	}
	
	/**
	 * Returns the task type.
	 * @return the task type.
	 */
	public TaskType getTaskType() {
		return taskType;
	}

	/**
	 * Returns the task state.
	 * @return the task state.
	 */
	public State getTaskState() {
		return taskState;
	}
	
	/**
	 * Returns whether this task is a canary task.
	 * @return <code>true</code> if this task is a canary task, <code>false</code> if not.
	 */
	public boolean isCanary() {
		return canary;
	}

	/**
	 * Returns the last modification timestamp of this task.
	 * @retrn the last modification date
	 */
	public Date getDateModified() {
		if(dateModified != null) {
			return new Date(dateModified.getTime());
		}
		return null;
	}
	
	/**
	 * Returns the journal records.
	 * @return the journal records.
	 */
	public List<JobTaskMessage> getJournal() {
		return unmodifiableList(journal);
	}
	
	/**
	 * Returns the task parameters.
	 * @return the task parameters.
	 */
	public JsonObject getParameters() {
		return parameters;
	}
	
	/**
	 * Returns the element group ID if the task is related to an element.
	 * @return the element group ID or <code>null</code> if the task is not related to an element.
	 */
	public ElementGroupId getGroupId() {
		return groupId;
	}

	/**
	 * Returns the element group name if the task is related to an element.
	 * @return the element group name or <code>null</code> if the task is not related to an element.
	 */
	public ElementGroupName getGroupName() {
		return groupName;
	}
	
	/**
	 * Returns the element group type if the task is related to an element.
	 * @return the element group type or <code>null</code> if the task is not related to an element.
	 */
	public ElementGroupType getGroupType() {
		return groupType;
	}

	/**
	 * Returns the element ID if the task is related to an element.
	 * @return the element ID or <code>null</code> if the task is not related to an element.
	 */
	public ElementId getElementId() {
		return elementId;
	}

	/**
	 * Returns the element name if the task is related to an element.
	 * @return the element name or <code>null</code> if the task is not related to an element.
	 */
	public ElementName getElementName() {
		return elementName;
	}

	/**
	 * Returns the element alias if the task is related to an element.
	 * @return the element alias or <code>null</code> if the task is not related to an element.
	 */
	public ElementAlias getElementAlias() {
		return elementAlias;
	}
	
	/**
	 * Returns the element role if the task is related to an element.
	 * @return the element role or <code>null</code> if the task is not related to an element.
	 */
	public ElementRoleName getElementRole() {
		return elementRole;
	}

}