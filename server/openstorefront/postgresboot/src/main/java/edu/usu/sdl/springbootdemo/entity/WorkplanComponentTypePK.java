/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.springbootdemo.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dshurtleff
 */
@Embeddable
public class WorkplanComponentTypePK
		implements Serializable
{

	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "\"componentType\"")
	private String componentType;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "\"workplanId\"")
	private String workplanId;

	public WorkplanComponentTypePK()
	{
	}

	public WorkplanComponentTypePK(String componentType, String workplanId)
	{
		this.componentType = componentType;
		this.workplanId = workplanId;
	}

	public String getComponentType()
	{
		return componentType;
	}

	public void setComponentType(String componentType)
	{
		this.componentType = componentType;
	}

	public String getWorkplanId()
	{
		return workplanId;
	}

	public void setWorkplanId(String workplanId)
	{
		this.workplanId = workplanId;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (componentType != null ? componentType.hashCode() : 0);
		hash += (workplanId != null ? workplanId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof WorkplanComponentTypePK)) {
			return false;
		}
		WorkplanComponentTypePK other = (WorkplanComponentTypePK) object;
		if ((this.componentType == null && other.componentType != null) || (this.componentType != null && !this.componentType.equals(other.componentType))) {
			return false;
		}
		if ((this.workplanId == null && other.workplanId != null) || (this.workplanId != null && !this.workplanId.equals(other.workplanId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "edu.usu.sdl.proto.postgres.entity.WorkplanComponentTypePK[ componentType=" + componentType + ", workplanId=" + workplanId + " ]";
	}

}
