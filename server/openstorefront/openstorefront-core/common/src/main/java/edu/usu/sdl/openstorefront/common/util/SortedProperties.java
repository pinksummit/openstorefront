/*
 * Copyright 2016 Space Dynamics Laboratory - Utah State University Research Foundation.
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
package edu.usu.sdl.openstorefront.common.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author dshurtleff
 */
public class SortedProperties
	extends Properties
{

	@Override
	public synchronized Enumeration<Object> keys()
	{
		List<Object> result = Collections.list(super.keys()); 
		Collections.sort(result, (a, b) -> a.toString().compareTo(b.toString())); 
		return Collections.enumeration(result);				
	}
	
}