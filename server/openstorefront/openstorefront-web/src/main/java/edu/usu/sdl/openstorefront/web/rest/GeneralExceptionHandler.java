/*
 * Copyright 2014 Space Dynamics Laboratory - Utah State University Research Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usu.sdl.openstorefront.web.rest;

import edu.usu.sdl.openstorefront.core.entity.ErrorTypeCode;
import edu.usu.sdl.openstorefront.core.model.ErrorInfo;
import edu.usu.sdl.openstorefront.core.view.SystemErrorModel;
import edu.usu.sdl.openstorefront.service.ServiceProxy;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author dshurtleff
 */
@Provider
public class GeneralExceptionHandler
		implements ExceptionMapper<Throwable>
{

	@Context
	HttpServletRequest httpServletRequest;

	@Override
	public Response toResponse(Throwable exception)
	{
		if (exception instanceof WebApplicationException) {
			WebApplicationException webApplicationException = (WebApplicationException) exception;
			return webApplicationException.getResponse();
		} else {
			ErrorInfo errorInfo = new ErrorInfo(exception, httpServletRequest);
			errorInfo.setErrorTypeCode(ErrorTypeCode.REST_API);

			ServiceProxy serviceProxy = new ServiceProxy();
			SystemErrorModel systemErrorModel = serviceProxy.getSystemService().generateErrorTicket(errorInfo);

			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(systemErrorModel).type(MediaType.APPLICATION_JSON).build();
		}
	}

}
