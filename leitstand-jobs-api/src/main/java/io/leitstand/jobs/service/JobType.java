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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.leitstand.commons.model.Scalar;
import io.leitstand.jobs.jsonb.JobTypeAdapter;

@JsonbTypeAdapter(JobTypeAdapter.class)
public class JobType extends Scalar<String>{

	private static final long serialVersionUID = 1L;

	public static JobType jobType(String type) {
		return valueOf(type);
	}
	
	public static JobType valueOf(String type) {
		return fromString(type,JobType::new);
	}

	@NotNull(message="{job_type.required}")
	@Pattern(message="{job_type.invalid}", regexp="\\p{Print}{1,64}")
	private String value;
	
	public JobType(String value){
		this.value = value;
	}
	
	@Override
	public String getValue() {
		return value;
	}

}
