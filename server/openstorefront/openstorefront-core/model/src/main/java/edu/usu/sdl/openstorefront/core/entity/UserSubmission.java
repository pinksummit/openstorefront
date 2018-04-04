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
import edu.usu.sdl.openstorefront.core.annotation.ConsumeField;
import edu.usu.sdl.openstorefront.core.annotation.DataType;
import edu.usu.sdl.openstorefront.core.annotation.FK;
import edu.usu.sdl.openstorefront.core.annotation.PK;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

/**
 *
 * @author dshurtleff
 */
public class UserSubmission
		extends StandardEntity<UserSubmission>
{

	private static final long serialVersionUID = 1L;

	@PK(generated = true)
	@NotNull
	private String userSubmissionId;

	@NotNull
	@FK(SubmissionTemplateStatus.class)
	private String templateId;

	@NotNull
	@ConsumeField
	@APIDescription("Type of listing")
	@FK(value = ComponentType.class, enforce = true)
	private String componentType;

	@FK(Component.class)
	private String originalComponentId;

	@ConsumeField
	@DataType(UserSubmissionField.class)
	@Embedded
	@OneToMany(orphanRemoval = true)
	private List<UserSubmissionField> fields;

	@SuppressWarnings({"squid:S2637", "squid:S1186"})
	public UserSubmission()
	{
	}

	@Override
	public <T extends StandardEntity> void updateFields(T entity)
	{
		super.updateFields(entity);

		UserSubmission userSubmission = (UserSubmission) entity;
		this.setFields(userSubmission.getFields());
	}

	public String getUserSubmissionId()
	{
		return userSubmissionId;
	}

	public void setUserSubmissionId(String userSubmissionId)
	{
		this.userSubmissionId = userSubmissionId;
	}

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

	public String getOriginalComponentId()
	{
		return originalComponentId;
	}

	public void setOriginalComponentId(String originalComponentId)
	{
		this.originalComponentId = originalComponentId;
	}

	public List<UserSubmissionField> getFields()
	{
		return fields;
	}

	public void setFields(List<UserSubmissionField> fields)
	{
		this.fields = fields;
	}

	public String getComponentType()
	{
		return componentType;
	}

	public void setComponentType(String componentType)
	{
		this.componentType = componentType;
	}

}
