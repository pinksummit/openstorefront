/*
 * Copyright 2017 Space Dynamics Laboratory - Utah State University Research Foundation.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * See NOTICE.txt for more information.
 */
package edu.usu.sdl.openstorefront.web.extension;

import edu.usu.sdl.openstorefront.web.init.ApplicationInit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import org.glassfish.hk2.api.ServiceLocator;
//import net.sourceforge.stripes.controller.Intercepts;
//import net.sourceforge.stripes.controller.LifecycleStage;

/**
 *
 * @author kbair
 */
//@Intercepts(LifecycleStage.ActionBeanResolution)
public class StripesInjection
		implements Interceptor
{

	private static final Logger LOG = Logger.getLogger(StripesInjection.class.getName());

	@Override
	public Resolution intercept(ExecutionContext context) throws Exception
	{
		ActionBean bean = context.getActionBean();
		LOG.log(Level.INFO, String.format("Running injection for instance of %s", bean.getClass().getSimpleName()));
        ServletContext ctx = context.getActionBeanContext().getServletContext();
        ServiceLocator locator = (ServiceLocator) ctx.getAttribute(ApplicationInit.KEY);
        locator.inject(bean);

		return context.proceed();
	}

}
