/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.proto.postgres;

import edu.usu.sdl.openstorefront.common.util.StringProcessor;
import edu.usu.sdl.openstorefront.common.util.TimeUtil;
import edu.usu.sdl.openstorefront.core.entity.ApprovalStatus;
import edu.usu.sdl.openstorefront.core.entity.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.Test;

/**
 *
 * @author dshurtleff
 */
public class BridgeTest
{

	@Test
	public void testBridge() throws ClassNotFoundException, SQLException
	{

		Class.forName("org.postgresql.Driver");

		Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "net76!");

		//	DataSource datasource =
		QueryRunner run = new QueryRunner();

		Map<String, String> columnToPropertyOverrides = new HashMap<>();
		columnToPropertyOverrides.put("componentId", "componentId");
		columnToPropertyOverrides.put("approvalState", "approvalState");
		columnToPropertyOverrides.put("lastActivityDts", "lastActivityDts");
		columnToPropertyOverrides.put("activeStatus", "activeStatus");

		ResultSetHandler<List<Component>> h = new BeanListHandler<Component>(Component.class, new BasicRowProcessor(new BeanProcessor(columnToPropertyOverrides)));

		try {

			Component component = new Component();
			component.setComponentId(StringProcessor.uniqueId());
			component.setName("Test");
			component.setDescription("Test Description");
			component.setComponentType("MYType");
			component.setOrganization("TEST");
			component.setApprovalState(ApprovalStatus.APPROVED);
			component.setGuid("5555555");
			component.setLastActivityDts(TimeUtil.currentDate());
			component.setActiveStatus(Component.ACTIVE_STATUS);
			component.setCreateUser("ds");
			component.setCreateDts(TimeUtil.currentDate());
			component.setUpdateUser("ds");
			component.setUpdateDts(TimeUtil.currentDate());

			//insert record
			run.update(connection,
					"INSERT INTO storefront.component (\"componentId\", \"componentType\", name, description, organization, \"approvalState\", guid, \"lastActivityDts\", \"activeStatus\", \"createUser\", \"createDts\", \"updateUser\", \"updateDts\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					component.getComponentId(),
					component.getComponentType(),
					component.getName(),
					component.getName(),
					component.getDescription(),
					component.getApprovalState(),
					component.getGuid(),
					new Timestamp(component.getLastActivityDts().getTime()),
					component.getActiveStatus(),
					component.getCreateUser(),
					new Timestamp(component.getCreateDts().getTime()),
					component.getUpdateUser(),
					new Timestamp(component.getUpdateDts().getTime())
			);

			//query
			List<Component> results = run.query(
					connection, "SELECT * FROM storefront.component WHERE name=?", h, "Test");
			System.out.println("Found: " + results.size());
			results.forEach(r -> StringProcessor.printObject(r));

		} finally {
			// Use this helper method so we don't have to check for null
			DbUtils.close(connection);
		}

	}

}
