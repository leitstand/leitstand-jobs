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

import javax.json.bind.annotation.JsonbTypeAdapter;

import io.leitstand.commons.model.Scalar;
import io.leitstand.jobs.jsonb.TaskNameAdapter;

@JsonbTypeAdapter(TaskNameAdapter.class)
public class TaskName extends Scalar<String> {

	private static final long serialVersionUID = 1L;

	public static TaskName taskName(String name) {
	    return valueOf(name);
	}
	
	public static TaskName taskName(Scalar<String> name) {
	    return valueOf(name);
	}
	
	public static TaskName valueOf(Scalar<String> name) {
		return fromString(name.getValue(),TaskName::new);
	}
	
	public static TaskName valueOf(String name) {
		return fromString(name,TaskName::new);
	}
	
	private String value;
	
	public TaskName(String value) {
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}

}
