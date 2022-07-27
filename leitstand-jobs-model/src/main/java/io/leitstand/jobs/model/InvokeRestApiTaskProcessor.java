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

import static io.leitstand.commons.etc.FileProcessor.yaml;
import static io.leitstand.jobs.model.TaskResult.completed;
import static io.leitstand.jobs.model.TaskResult.failed;
import static io.leitstand.jobs.model.TaskResult.rejected;
import static io.leitstand.jobs.service.JobTaskMessage.newJobTaskMessage;
import static io.leitstand.jobs.service.ReasonCode.JOB1000I_REST_API_CALL_SUCCEEDED;
import static io.leitstand.jobs.service.ReasonCode.JOB1001E_REST_API_CALL_REJECTED;
import static io.leitstand.jobs.service.ReasonCode.JOB1002E_REST_API_CALL_FAILED;
import static java.lang.String.format;
import static java.util.logging.Logger.getLogger;

import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;

import io.leitstand.commons.http.GenericRestClient;
import io.leitstand.commons.http.Request;

public class InvokeRestApiTaskProcessor implements TaskProcessor {
	
	private static final Logger LOG = getLogger(InvokeRestApiTaskProcessor.class.getName());
	
	private GenericRestClient client;
	
	public InvokeRestApiTaskProcessor(GenericRestClient client) {
		this.client = client;
	}
	
	public TaskResult execute(Job_Task task) {
		try {
			Request  request  = adaptFromJsonJsonRequest(task);
			Response response = client.invoke(request);
			return mapStatusToTaskState(request, response);
		} catch(Exception e) {
			LOG.info(() -> format("%s: Cannot execute task %s (%s) of %s job due to an unexpected error: %s",
								  JOB1002E_REST_API_CALL_FAILED.getReasonCode(),
								  task.getTaskName(),
								  task.getTaskId(),
								  task.getJobName(),
								  e.getMessage()));
			
			return failed(newJobTaskMessage()
						  .withReason(JOB1002E_REST_API_CALL_FAILED)
						  .withMessage(format("Execution failed due to an unexpected error: %s",
								  			  e.getMessage())));
		}
	}

	private Request adaptFromJsonJsonRequest(Job_Task task) throws IOException {
		return yaml(Request.class)
			   .process(new StringReader(task.getParameters().toString()));
	}

	protected TaskResult mapStatusToTaskState(Request request, Response response) {
		switch(response.getStatus()) {
			case 200: //OK
			case 201: //Created
			case 202: //Accepted
			case 204: //No content
				return completed(newJobTaskMessage()
								 .withReason(JOB1000I_REST_API_CALL_SUCCEEDED)
								 .withMessage(format("Successfully sent %s request to %s%s endpoint (%d).",
										 			 request.getMethod(),			 
										 			 client.getEndpoint(),
										 			 request.getPath(),
										 			 response.getStatus())));
			case 401: //Unauthorized
			case 403: //Forbidden
			case 409: //Conflict
				return rejected(newJobTaskMessage()
						 		.withReason(JOB1001E_REST_API_CALL_REJECTED)
								.withMessage(format("%s request to %s%s endpoint got rejected (%d).",
													request.getMethod(),			 
													client.getEndpoint(),
													request.getPath(),
													response.getStatus())));
			default:
				return failed(newJobTaskMessage()
				 			  .withReason(JOB1002E_REST_API_CALL_FAILED)
				 			  .withMessage(format("%s request to %s%s endpoint failed (%d).",
					 			 				  request.getMethod(),			 
					 			 				  client.getEndpoint(),
					 			 				  request.getPath(),
					 			 				  response.getStatus())));
		}
	
	}
}
