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
package edu.usu.sdl.openstorefront.util;

import edu.usu.sdl.openstorefront.doc.ConsumeField;
import edu.usu.sdl.openstorefront.exception.OpenStorefrontRuntimeException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.logging.Logger;
import org.apache.commons.beanutils.BeanUtils;

/**
 * Reflection and Service related methods
 *
 * @author dshurtleff
 */
public class ServiceUtil
{

	private static final Logger log = Logger.getLogger(ServiceUtil.class.getName());

	public static final String LOOKUP_ENTITY = "LookupEntity";
	public static final String BASECOMPONENT_ENTITY = "BaseComponent";
	public static final String COMPOSITE_KEY_SEPERATOR = "#";
	public static final String COMPOSITE_KEY_REPLACER = "~";

	/**
	 * This check for Value Model Objects
	 *
	 * @param fieldClass
	 * @return
	 */
	public static boolean isComplexClass(Class fieldClass)
	{
		boolean complex = false;
		if (!fieldClass.isPrimitive()
				&& !fieldClass.isArray()
				&& !fieldClass.getSimpleName().equalsIgnoreCase(String.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Long.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Integer.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Boolean.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Double.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Float.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(BigDecimal.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Date.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(List.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Map.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Collection.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Queue.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(Set.class.getSimpleName())
				&& !fieldClass.getSimpleName().equalsIgnoreCase(BigInteger.class.getSimpleName())) {
			complex = true;
		}
		return complex;
	}

	/**
	 * Check for class to see if it's a collection class
	 *
	 * @param checkClass
	 * @return
	 */
	public static boolean isCollectionClass(Class checkClass)
	{
		boolean collection = false;
		if (checkClass.getSimpleName().equalsIgnoreCase(List.class.getSimpleName())
				|| checkClass.getSimpleName().equalsIgnoreCase(Map.class.getSimpleName())
				|| checkClass.getSimpleName().equalsIgnoreCase(Collection.class.getSimpleName())
				|| checkClass.getSimpleName().equalsIgnoreCase(Queue.class.getSimpleName())
				|| checkClass.getSimpleName().equalsIgnoreCase(Set.class.getSimpleName())) {
			collection = true;
		}
		return collection;
	}

	/**
	 * This gets all declared field of the whole object hierarchy
	 *
	 * @param typeClass
	 * @return
	 */
	public static List<Field> getAllFields(Class typeClass)
	{
		List<Field> fields = new ArrayList<>();
		if (typeClass.getSuperclass() != null) {
			fields.addAll(getAllFields(typeClass.getSuperclass()));
		}
		for (Field field : typeClass.getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers()) == false
					&& Modifier.isFinal(field.getModifiers()) == false) {
				fields.add(field);
			}
		}
		return fields;
	}

	/**
	 * This checks that an entity is a lookup entity
	 *
	 * @param entityClass
	 * @return
	 */
	public static boolean isSubLookupEntity(Class entityClass)
	{
		return isSubClass(LOOKUP_ENTITY, entityClass);
	}

	/**
	 * This checks class name to determine if a given class is subtype of the
	 * class name;
	 *
	 * @param className
	 * @param entityClass
	 * @return
	 */
	public static boolean isSubClass(String className, Class entityClass)
	{
		if (entityClass == null) {
			return false;
		}

		if (className.equals(entityClass.getSimpleName())) {
			return true;
		} else {
			return isSubClass(className, entityClass.getSuperclass());
		}
	}
	
	/**
	 * Compares to object of the same type
	 * @param original
	 * @param compare
	 * @param consumeFieldsOnly
	 * @return 
	 */
	public static boolean compareObjects(Object original, Object compare, boolean consumeFieldsOnly)
	{
		boolean changed = false;
			
		if (original != null && compare == null)
		{
			changed = true;
		}else if (original == null && compare != null)
		{
			changed = true;
		}
		else if (original != null && compare != null)
		{
			if (original.getClass().isInstance(compare))
			{
				List<Field> fields = getAllFields(original.getClass());
				for (Field field : fields)
				{
					boolean check = true;
					if (consumeFieldsOnly)
					{
						ConsumeField consume = (ConsumeField) field.getAnnotation(ConsumeField.class);
						if (consume == null)
						{
							check = false;
						}
					}
					if (check)
					{
						try
						{
							changed = compareFields(BeanUtils.getProperty(original, field.getName()), BeanUtils.getProperty(compare, field.getName()));
							if (changed)
							{
								break;
							}
						}
						catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex)
						{
							throw new OpenStorefrontRuntimeException("Can't compare object types", ex);
						}
					}
				}
			}
			else
			{
				throw new OpenStorefrontRuntimeException("Can't compare different object types", "Check objects");
			}
		}		
		return changed;
	}
	
	public static boolean compareFields(Object original, Object newField)
	{
		boolean changed = false;
		if (original != null && newField == null)
		{
			changed = true;
		}else if (original == null && newField != null)
		{
			changed = true;
		}
		else if (original != null && newField != null)
		{
			changed = !(original.equals(newField));
		}		
		return changed;
	}	
	
}