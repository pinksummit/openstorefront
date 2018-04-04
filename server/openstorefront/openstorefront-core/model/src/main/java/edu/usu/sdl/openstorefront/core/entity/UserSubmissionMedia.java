/*
 * Copyright 2018 Space Dynamics Laboratory - Utah State University Research Foundation.
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
package edu.usu.sdl.openstorefront.core.entity;

import edu.usu.sdl.openstorefront.core.annotation.APIDescription;
import edu.usu.sdl.openstorefront.core.annotation.PK;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * This holds media and local resources for an user submission This basically
 * need to just hold the pointer to file the other metadata should be held on
 * the field
 *
 * @author dshurtleff
 */
@APIDescription("This is part of the submission form field")
@Embeddable
public class UserSubmissionMedia
		implements Serializable
{

	private static final long serialVersionUID = 1L;

	@PK(generated = true)
	@NotNull
	private String submissionMediaId;

	private String fieldId;

	@ManyToOne(cascade = {CascadeType.ALL})
	@APIDescription("A local media file")
	private MediaFile file;

	@SuppressWarnings({"squid:S2637", "squid:S1186"})
	public UserSubmissionMedia()
	{
	}

	public String getSubmissionMediaId()
	{
		return submissionMediaId;
	}

	public void setSubmissionMediaId(String submissionMediaId)
	{
		this.submissionMediaId = submissionMediaId;
	}

	public String getFieldId()
	{
		return fieldId;
	}

	public void setFieldId(String fieldId)
	{
		this.fieldId = fieldId;
	}

	public MediaFile getFile()
	{
		return file;
	}

	public void setFile(MediaFile file)
	{
		this.file = file;
	}

}
