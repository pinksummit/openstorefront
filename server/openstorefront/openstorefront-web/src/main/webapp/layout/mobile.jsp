<%-- 
/* 
 * Copyright 2016 Space Dynamics Laboratory - Utah State University Research Foundation.
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
--%>

<%@page import="edu.usu.sdl.openstorefront.security.RedirectUtil"%>
<%@page import="edu.usu.sdl.openstorefront.web.init.ShiroAdjustedFilter"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="edu.usu.sdl.openstorefront.core.entity.Branding"%>
<%@page import="edu.usu.sdl.openstorefront.service.ServiceProxy"%>
<%@page import="edu.usu.sdl.openstorefront.security.SecurityUtil"%>
<%@page import="edu.usu.sdl.openstorefront.common.manager.PropertiesManager"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="stripes" uri="http://stripes.sourceforge.net/stripes.tld" %>
<stripes:layout-definition>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

		<meta name="HandheldFriendly" content="True">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=10, user-scalable=yes">
		
	<%
		String appVersion = PropertiesManager.getInstance().getApplicationVersion();		
		request.setAttribute("appVersion", appVersion);
		
		Branding brandingView = ServiceProxy.getProxy().getBrandingService().getCurrentBrandingView();
		
		request.setAttribute("appTitle", brandingView.getApplicationName());		
		request.setAttribute("branding", brandingView);
		request.setAttribute("user", SecurityUtil.getCurrentUserName());
		request.setAttribute("usercontext", SecurityUtil.getUserContext());
		request.setAttribute("admin", SecurityUtil.isEntryAdminUser());
		
		request.setAttribute("idleTimeoutMinutes", PropertiesManager.getInstance().getValue(PropertiesManager.KEY_UI_IDLETIMEOUT_MINUTES, "-1"));
		request.setAttribute("idlegraceperiod", PropertiesManager.getInstance().getValue(PropertiesManager.KEY_UI_IDLETIMEGRACE_MINUTES, "1"));
		request.setAttribute("enableWebsocket", PropertiesManager.getInstance().getValue(PropertiesManager.KEY_ENABLE_WEBSOCKETS, "false"));
		
	%>	

	<link href="webjars/extjs/6.2.0/build/classic/theme-triton/resources/theme-triton-all-debug.css" rel="stylesheet" type="text/css"/>
	<link href="webjars/extjs/6.2.0/build/packages/ux/classic/triton/resources/ux-all-debug.css" rel="stylesheet" type="text/css"/>
	<link href="webjars/extjs/6.2.0/build/packages/charts/classic/triton/resources/charts-all-debug.css" rel="stylesheet" type="text/css"/>
	<link href="webjars/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="Branding.action?CSS&template=extTritonTheme.jsp&v=${appVersion}" rel="stylesheet" type="text/css"/>	
	<link href="Branding.action?CSS&template=apptemplate.jsp&v=${appVersion}" rel="stylesheet" type="text/css"/>
	<link href="Branding.action?Override&v=${appVersion}" rel="stylesheet" type="text/css"/>	
	
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/appicon.png" type="image/x-icon">	

	<script src="webjars/extjs/6.2.0/ext-bootstrap.js" type="text/javascript"></script>
	<script src="webjars/extjs/6.2.0/build/classic/theme-triton/theme-triton.js" type="text/javascript"></script>
	<script src="webjars/extjs/6.2.0/build/packages/ux/classic/ux-debug.js" type="text/javascript"></script>
	<script src="webjars/extjs/6.2.0/build/packages/charts/classic/charts-debug.js" type="text/javascript"></script>
	<script src="scripts/socket.io.js" type="text/javascript"></script>
		
	<%-- Core Utils --%>	
	<script src="scripts/global/override.js?v=${appVersion}" type="text/javascript"></script>		
	<script src="scripts/util/coreUtil.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/util/dateUtil.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/global/coreService.js?v=${appVersion}" type="text/javascript"></script>	
	
	<%-- Custom Components --%>		
	<script src="scripts/component/notificationPanel.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/component/framePanel.js?v=${appVersion}" type="text/javascript"></script>	
	<script src="scripts/component/userProfilePanel.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/component/feedbackWindow.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/component/help.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/component/supportMedia.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/component/faq.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/component/standardComponents.js?v=${appVersion}" type="text/javascript"></script>
	<script src="scripts/extension/tinymceExtensions.js?v=${appVersion}" type="text/javascript"></script>
	<script src="webjars/tinymcetextarea/5.1/tinymce/tinymce.min.js" type="text/javascript"></script>
	<script src="webjars/tinymcetextarea/5.1/TinyMCETextArea.js" type="text/javascript"></script>	
	
	<title>${appTitle}</title>
        <stripes:layout-component name="html_head"/>
    </head>
    <body>

	
	<stripes:layout-component name="body_header" />	
	
	
          <stripes:layout-component name="contents"/>
		  
	
	<stripes:layout-component name="body_footer" />	
	
	<script type="text/javascript">			

		

		
	</script>
	

	
    </body>
</html>
</stripes:layout-definition>