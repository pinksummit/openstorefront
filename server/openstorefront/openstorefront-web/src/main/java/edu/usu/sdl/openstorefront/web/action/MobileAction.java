/*
 * Copyright 2018 Space Dynamics Laboratory - Utah State University Research Foundation.
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
package edu.usu.sdl.openstorefront.web.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;

/**
 *
 * @author dshurtleff
 */
public class MobileAction
		extends BaseAction
{

	@DefaultHandler
	public Resolution loadPage()
	{
		return new ForwardResolution("/WEB-INF/mobile/landing.jsp");
	}

	@HandlesEvent("Search")
	public Resolution searchPage()
	{
		return new ForwardResolution("/WEB-INF/mobile/searchResults.jsp");
	}

	@HandlesEvent("Details")
	public Resolution details()
	{
		return new ForwardResolution("/WEB-INF/mobile/details.jsp");
	}

	@HandlesEvent("Modern")
	public Resolution modern()
	{
		return new ForwardResolution("/WEB-INF/mobile/modern.jsp");
	}

}
