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
package edu.usu.sdl.openstorefront.service.mapping;

import edu.usu.sdl.openstorefront.core.entity.SubmissionFormField;
import edu.usu.sdl.openstorefront.core.entity.SubmissionFormSection;
import edu.usu.sdl.openstorefront.core.entity.SubmissionFormTemplate;
import edu.usu.sdl.openstorefront.core.entity.UserSubmission;
import edu.usu.sdl.openstorefront.core.entity.UserSubmissionField;
import edu.usu.sdl.openstorefront.core.model.ComponentAll;
import edu.usu.sdl.openstorefront.validation.ValidationResult;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dshurtleff
 */
public class MappingController
{

	private static final Logger LOG = Logger.getLogger(MappingController.class.getName());

	private MapperFactory mapperFactory;

	public MappingController()
	{
		mapperFactory = new MapperFactory();
	}

	public MappingController(MapperFactory mapperFactory)
	{
		this.mapperFactory = mapperFactory;
	}

	/**
	 * Check the template to make sure it can be mapped
	 *
	 * @param template
	 * @return
	 */
	public ValidationResult verifyTemplate(SubmissionFormTemplate template)
	{
		Objects.requireNonNull(template);

		ValidationResult result = new ValidationResult();

		List<ComponentAll> allComponents = new ArrayList<>();
		ComponentAll mainComponent = new ComponentAll();
		allComponents.add(mainComponent);

		verifySections(template, mainComponent, allComponents);
		result.merge(validateComponents(allComponents));
		return result;
	}

	private void verifySections(SubmissionFormTemplate template, ComponentAll mainComponent, List<ComponentAll> allComponents)
	{
		if (template.getSections() != null) {
			for (SubmissionFormSection section : template.getSections()) {
				verifyFields(section.getFields(), mainComponent, allComponents);
			}
		}
	}

	private void verifyFields(List<SubmissionFormField> fields, ComponentAll mainComponent, List<ComponentAll> allComponents)
	{
		if (fields != null) {
			for (SubmissionFormField field : fields) {
				BaseMapper mapper = mapperFactory.getMapperForField(field.getMappingType());
				allComponents.addAll(mapper.mapField(mainComponent, field));
			}
		}
	}

	private ValidationResult validateComponents(List<ComponentAll> allComponents)
	{
		ValidationResult result = new ValidationResult();

		for (ComponentAll componentAll : allComponents) {
			result.merge(componentAll.validate());
		}

		return result;
	}

	/**
	 * Create entries from submission
	 *
	 * @param template
	 * @param userSubmission
	 * @return All entries created
	 */
	public List<ComponentAll> mapUserSubmissionToEntry(SubmissionFormTemplate template, UserSubmission userSubmission)
	{
		List<ComponentAll> componentAlls = new ArrayList<>();

		List<ComponentAll> allComponents = new ArrayList<>();
		ComponentAll mainComponent = new ComponentAll();
		allComponents.add(mainComponent);

		mapTemplateSections(template, mainComponent, allComponents, userSubmission);

		return componentAlls;
	}

	private void mapTemplateSections(SubmissionFormTemplate template, ComponentAll mainComponent, List<ComponentAll> allComponents, UserSubmission userSubmission)
	{
		if (template.getSections() != null) {

			Map<String, UserSubmissionField> userFieldMap = new HashMap<>();
			for (UserSubmissionField submissionField : userSubmission.getFields()) {
				userFieldMap.put(userSubmission.getTemplateId(), submissionField);
			}

			for (SubmissionFormSection section : template.getSections()) {
				mapTemplateFields(section.getFields(), mainComponent, allComponents, userFieldMap);
			}
		}
	}

	private void mapTemplateFields(List<SubmissionFormField> fields, ComponentAll mainComponent, List<ComponentAll> allComponents, Map<String, UserSubmissionField> submissionFields)
	{
		if (fields != null) {
			for (SubmissionFormField field : fields) {
				BaseMapper mapper = mapperFactory.getMapperForField(field.getMappingType());
				UserSubmissionField userField = submissionFields.get(field.getFieldId());
				if (userField != null) {

					allComponents.addAll(mapper.mapField(mainComponent, field, userField));
				} else {
					if (LOG.isLoggable(Level.FINEST)) {
						LOG.log(Level.FINEST, () -> "No user data for field: {0}" + field.getLabel());
					}
				}
			}
		}
	}

	/**
	 * Create a submission from a set of entries. It assumes all entries will be
	 * related.
	 *
	 * @param template
	 * @param userSubmission
	 * @return userSubmission with all
	 */
	public UserSubmission mapEntriesToUserSubmission(SubmissionFormTemplate template, List<ComponentAll> fullComponents)
	{
		UserSubmission userSubmission = null;

		return userSubmission;
	}

}
