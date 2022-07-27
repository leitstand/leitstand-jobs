package io.leitstand.jobs.service;

import static io.leitstand.commons.model.BuilderUtil.assertNotInvalidated;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;

import io.leitstand.commons.Reason;
import io.leitstand.commons.messages.Message.Severity;
import io.leitstand.security.auth.UserName;
import io.leitstand.security.auth.jpa.UserNameConverter;

@Embeddable
public class JobTaskMessage {

	public static Builder newJobTaskMessage() {
		return new Builder();
	}
	
	public static class Builder {
		
		private JobTaskMessage m = new JobTaskMessage();

		public Builder withSeverity(Severity severity) {
			assertNotInvalidated(getClass(), m);
			this.m.severity = severity;
			return this;
		}
		
		public Builder withReason(Reason reason) {
			return withSeverity(reason.getSeverity())
				   .withReason(reason.getReasonCode());
		}
		
		public Builder withReason(String reason) {
			assertNotInvalidated(getClass(), m);
			this.m.reason = reason;
			return this;
		}
		
		public Builder withUserName(UserName userName) {
			assertNotInvalidated(getClass(), m);
			this.m.userName = userName;
			return this;
		}

		public Builder withMessage(String message) {
			assertNotInvalidated(getClass(), m);
			this.m.message = message;
			return this;
		}
		
		public Builder withDateCreated(Date dateCreated) {
			assertNotInvalidated(getClass(),m);
			this.m.dateCreated = new Date(dateCreated.getTime());
			return this;
		}
		
		public JobTaskMessage build() {
			try {
				assertNotInvalidated(getClass(), m);
				return m;
			} finally {
				this.m = null;
			}
		}
	}
	
	private String reason;
	@Enumerated(STRING)
	private Severity severity;
	@Convert(converter=UserNameConverter.class)
	private UserName userName;
	private String message;
	@Temporal(TIMESTAMP)
	private Date dateCreated = new Date();
	
	
	public Severity getSeverity() {
		return severity;
	}
	
	public String getReason() {
		return reason;
	}
	
	public UserName getUserName() {
		return userName;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Date getDateCreated() {
		return new Date(dateCreated.getTime());
	}
	
}
