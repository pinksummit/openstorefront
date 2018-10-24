/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.proto.postgres;

import edu.usu.sdl.openstorefront.common.util.StringProcessor;
import edu.usu.sdl.openstorefront.common.util.TimeUtil;
import edu.usu.sdl.openstorefront.core.entity.ApprovalStatus;
import edu.usu.sdl.proto.postgres.entity.Component;
import edu.usu.sdl.proto.postgres.entity.Workplan;
import edu.usu.sdl.proto.postgres.entity.WorkplanComponentType;
import edu.usu.sdl.proto.postgres.entity.WorkplanComponentTypePK;
import edu.usu.sdl.proto.postgres.entity.WorkplanStep;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.junit.Test;

/**
 *
 * @author dshurtleff
 */
public class SwapTest
{

	@Test
	public void testSwap()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("edu.usu.sdl.proto_postgres_jar_1.0PU");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();

		Component component = new Component();
		component.setComponentId(StringProcessor.uniqueId());
		component.setName("Test-jpa");
		component.setDescription("Test Description-jpa");
		component.setComponentType("MYType");
		component.setOrganization("TEST-jpa");
		component.setApprovalState(ApprovalStatus.APPROVED);
		component.setGuid("5555555");
		component.setLastActivityDts(TimeUtil.currentDate());
		component.setActiveStatus("A");
		component.setCreateUser("ds");
		component.setCreateDts(TimeUtil.currentDate());
		component.setUpdateUser("ds");
		component.setUpdateDts(TimeUtil.currentDate());

		em.persist(component);
		em.getTransaction().commit();

		TypedQuery<Component> componentQuery = em.createNamedQuery("Component.findAll", Component.class);
		List<Component> components = componentQuery.getResultList();
		System.out.println("Found: " + components.size());
		components.forEach(c -> {
			System.out.println(c.getName());
		});

		em.close();

	}

	@Test
	public void testWorkplan()
	{

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("edu.usu.sdl.proto_postgres_jar_1.0PU");
		EntityManager em = factory.createEntityManager();

		em.getTransaction().begin();

		Workplan workplan = new Workplan();
		workplan.setName("Test");
		workplan.setWorkPlanId(StringProcessor.uniqueId());
		workplan.setWorkPlanType("TEST");
		workplan.setActiveStatus("A");
		workplan.setCreateUser("ds");
		workplan.setCreateDts(TimeUtil.currentDate());
		workplan.setUpdateUser("ds");
		workplan.setUpdateDts(TimeUtil.currentDate());

		WorkplanComponentType workplanComponentType = new WorkplanComponentType();
		workplanComponentType.setWorkplan(workplan);

		WorkplanComponentTypePK workplanComponentTypePK = new WorkplanComponentTypePK();
		workplanComponentTypePK.setComponentType("TEST");
		workplanComponentTypePK.setWorkplanId(workplan.getWorkPlanId());

		workplanComponentType.setWorkplanComponentTypePK(workplanComponentTypePK);
		List<WorkplanComponentType> types = new ArrayList<>();
		types.add(workplanComponentType);

		workplan.setWorkplanComponentTypeList(types);

		WorkplanStep step = new WorkplanStep();
		step.setWorkPlanStepId(StringProcessor.uniqueId());
		step.setName("TEST-STEP");
		step.setWorkplan(workplan);
		step.setStepOrder(1);

		List<WorkplanStep> steps = new ArrayList<>();
		steps.add(step);
		workplan.setWorkplanStepList(steps);

		em.persist(workplan);
		em.getTransaction().commit();

		TypedQuery<Workplan> componentQuery = em.createNamedQuery("Workplan.findAll", Workplan.class);
		List<Workplan> workplans = componentQuery.getResultList();
		System.out.println("Found: " + workplans.size());
		workplans.forEach(p -> {
			System.out.println(p.getName());
			p.getWorkplanComponentTypeList().forEach(t -> {
				System.out.println(t.getWorkplanComponentTypePK().getComponentType());
			});

		});

		em.close();

	}

}
