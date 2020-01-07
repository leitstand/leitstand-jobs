/*
 * (c) RtBrick, Inc - All rights reserved, 2015 - 2019
 */
package io.leitstand.jobs.model;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import io.leitstand.commons.db.DatabaseService;

@Dependent
public class JobsDatabaseServiceProducer {

	@Resource(lookup="java:/jdbc/leitstand")
	private DataSource ds;
	
	@Produces
	@ApplicationScoped
	@Jobs
	public DatabaseService createSchedulerDatabaseService() {
		return new DatabaseService(ds);
	}
	
}
