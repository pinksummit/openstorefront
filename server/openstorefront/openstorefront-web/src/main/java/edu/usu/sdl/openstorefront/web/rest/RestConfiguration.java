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

import edu.usu.sdl.core.CoreSystem;
import edu.usu.sdl.openstorefront.common.manager.FileSystemManager;
import edu.usu.sdl.openstorefront.common.manager.PropertiesManager;
import edu.usu.sdl.openstorefront.service.ServiceProxy;
import edu.usu.sdl.openstorefront.service.io.HelpImporter;
import edu.usu.sdl.openstorefront.service.io.LookupImporter;
import edu.usu.sdl.openstorefront.service.manager.AsyncTaskManager;
import edu.usu.sdl.openstorefront.service.manager.ConfluenceManager;
import edu.usu.sdl.openstorefront.service.manager.DBLogManager;
import edu.usu.sdl.openstorefront.service.manager.DBManager;
import edu.usu.sdl.openstorefront.service.manager.JiraManager;
import edu.usu.sdl.openstorefront.service.manager.JobManager;
import edu.usu.sdl.openstorefront.service.manager.LDAPManager;
import edu.usu.sdl.openstorefront.service.manager.MailManager;
import edu.usu.sdl.openstorefront.service.manager.OSFCacheManager;
import edu.usu.sdl.openstorefront.service.manager.OsgiManager;
import edu.usu.sdl.openstorefront.service.manager.PluginManager;
import edu.usu.sdl.openstorefront.service.manager.ReportManager;
import edu.usu.sdl.openstorefront.service.manager.SearchServerManager;
import edu.usu.sdl.openstorefront.service.manager.UserAgentManager;
import edu.usu.sdl.openstorefront.validation.ValidationUtil;
import edu.usu.sdl.openstorefront.web.init.ApplicationInit;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author dshurtleff
 */
@ApplicationPath("api")
public class RestConfiguration
		extends ResourceConfig
{

	public static final String APPLICATION_BASE_PATH = "api";

	@Inject
	public RestConfiguration(ServiceLocator locator)
	{		
		// jersy 2.x does not support @Immediate once https://github.com/jersey/jersey/issues/2563 is resolved for the version we are using
		// replace register(new ApplicationInit()); with ServiceLocatorUtilities.enableImmediateScope(locator);
		register(new ApplicationInit());
		register(new AbstractBinder()
		{
			@Override
			protected void configure()
			{
				bind(CoreSystem.class).to(CoreSystem.class).in(Singleton.class);
				bind(PropertiesManager.class).to(PropertiesManager.class).in(Singleton.class);
				bind(OsgiManager.class).to(OsgiManager.class).in(Singleton.class);
				bind(FileSystemManager.class).to(FileSystemManager.class).in(Singleton.class);
				bind(DBManager.class).to(DBManager.class).in(Singleton.class);
				bind(SearchServerManager.class).to(SearchServerManager.class).in(Singleton.class);
				bind(OSFCacheManager.class).to(OSFCacheManager.class).in(Singleton.class);
				bind(JiraManager.class).to(JiraManager.class).in(Singleton.class);
				bind(ConfluenceManager.class).to(ConfluenceManager.class).in(Singleton.class);
				bind(LookupImporter.class).to(LookupImporter.class).in(Singleton.class);
				bind(MailManager.class).to(MailManager.class).in(Singleton.class);
				bind(JobManager.class).to(JobManager.class).in(Singleton.class);
				bind(UserAgentManager.class).to(UserAgentManager.class).in(Singleton.class);
				bind(AsyncTaskManager.class).to(AsyncTaskManager.class).in(Singleton.class);
				bind(ReportManager.class).to(ReportManager.class).in(Singleton.class);
				bind(LDAPManager.class).to(LDAPManager.class).in(Singleton.class);
				bind(HelpImporter.class).to(HelpImporter.class).in(Singleton.class);
				bind(DBLogManager.class).to(DBLogManager.class).in(Singleton.class);
				bind(PluginManager.class).to(PluginManager.class).in(Singleton.class);
				bind(ServiceProxy.class).to(ServiceProxy.class).in(RequestScoped.class);
				bind(ValidationUtil.class).to(ValidationUtil.class).in(RequestScoped.class);
			}
		});
		packages(true, "edu.usu.sdl.openstorefront");
	}
}
