///*
// * Copyright 2017 Space Dynamics Laboratory - Utah State University Research Foundation.
// *
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU General Public License for more details.
// *
// * You should have received a copy of the GNU General Public License
// * along with this program.  If not, see <http://www.gnu.org/licenses/>.
// * See NOTICE.txt for more information.
// */
//package edu.usu.sdl.openstorefront.web.rest.resource;
//
//import edu.usu.sdl.openstorefront.common.manager.PropertiesManager;
//import edu.usu.sdl.openstorefront.core.entity.ComponentQuestion;
//import edu.usu.sdl.openstorefront.core.entity.ComponentQuestionResponse;
//import edu.usu.sdl.openstorefront.service.ServiceProxy;
//import javax.ws.rs.core.Application;
//import javax.ws.rs.core.Response;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.test.JerseyTest;
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;
//import org.junit.Test;
//import org.mockito.Mockito;
//
///**
// *
// * @author kbair
// */
//public class ComponentRESTResourceTest extends JerseyTest
//{
//
//	@Override
//	protected Application configure()
//	{
//		return new ResourceConfig(ComponentRESTResource.class);
//	}
//
//	/**
//	 * Test of addComponentQuestion method, of class ComponentRESTResource.
//	 */
//	@Test
//	public void testAddComponentQuestion()
//	{
//		// Arrange
//		System.out.println("addComponentQuestion");
//		String componentId = "0ddc8a11-b1d2-468d-988e-91b6de650f9b";
//		//	Set up Mocks
//		ComponentQuestion mockQuestion = Mockito.mock(ComponentQuestion.class);
//		Mockito.when(mockQuestion.getOrganization()).thenReturn("Widgets are Us");
//		Mockito.when(mockQuestion.getUserTypeCode()).thenReturn("user");
//		Mockito.when(mockQuestion.getQuestion()).thenReturn("How much wood could a woodchuck chuck?");
//
//		PropertiesManager mockProperties = Mockito.mock(PropertiesManager.class);
//		Mockito.when(mockProperties.get(Mockito.eq(PropertiesManager.KEY_USER_REVIEW_AUTO_APPROVE), Mockito.anyString())).thenReturn("false");
//
//		ServiceProxy mockService = Mockito.mock(ServiceProxy.class);
//
//		ComponentRESTResource instance = new ComponentRESTResource();
//		instance.properties = mockProperties;
//		instance.service = mockService;
//
//		Response expResult = null;
//
//		// Act
//		Response result = instance.addComponentQuestion(componentId, mockQuestion);
//		
//		// Assert
//		Mockito.verify(mockProperties).get(Mockito.eq(PropertiesManager.KEY_USER_REVIEW_AUTO_APPROVE), Mockito.anyString());
//		Mockito.verify(mockQuestion).setActiveStatus(ComponentQuestion.PENDING_STATUS);
//		Mockito.verify(mockQuestion).setComponentId(componentId);
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of updateComponentQuestion method, of class ComponentRESTResource.
//	 */
//	//@Test
//	public void testUpdateComponentQuestion()
//	{
//		System.out.println("updateComponentQuestion");
//		String componentId = "";
//		String questionId = "";
//		ComponentQuestion question = null;
//		ComponentRESTResource instance = new ComponentRESTResource();
//		Response expResult = null;
//		Response result = instance.updateComponentQuestion(componentId, questionId, question);
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of addComponentQuestionResponse method, of class
//	 * ComponentRESTResource.
//	 */
//	//@Test
//	public void testAddComponentQuestionResponse()
//	{
//		System.out.println("addComponentQuestionResponse");
//		String componentId = "";
//		String questionId = "";
//		ComponentQuestionResponse response = null;
//		ComponentRESTResource instance = new ComponentRESTResource();
//		Response expResult = null;
//		Response result = instance.addComponentQuestionResponse(componentId, questionId, response);
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//	/**
//	 * Test of updateComponentQuestionResponse method, of class
//	 * ComponentRESTResource.
//	 */
//	//@Test
//	public void testUpdateComponentQuestionResponse()
//	{
//		System.out.println("updateComponentQuestionResponse");
//		String componentId = "";
//		String questionId = "";
//		String responseId = "";
//		ComponentQuestionResponse questionResponseInput = null;
//		ComponentRESTResource instance = new ComponentRESTResource();
//		Response expResult = null;
//		Response result = instance.updateComponentQuestionResponse(componentId, questionId, responseId, questionResponseInput);
//		assertEquals(expResult, result);
//		// TODO review the generated test code and remove the default call to fail.
//		fail("The test case is a prototype.");
//	}
//
//}
