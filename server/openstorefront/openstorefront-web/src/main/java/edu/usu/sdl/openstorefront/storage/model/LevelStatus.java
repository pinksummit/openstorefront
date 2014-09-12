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
package edu.usu.sdl.openstorefront.storage.model;

import edu.usu.sdl.openstorefront.doc.APIDescription;

/**
 *
 * @author dshurtleff
 */
@APIDescription("Evaluation level status: Complete, Pending...etc")
public class LevelStatus
		extends LookupEntity
{

	public static final String COMPLETE = "C";
	public static final String IN_PROGRESS = "P";
	public static final String HALTED = "H";
	public static final String NOT_STARTED = "N";

	public LevelStatus()
	{
	}

}
