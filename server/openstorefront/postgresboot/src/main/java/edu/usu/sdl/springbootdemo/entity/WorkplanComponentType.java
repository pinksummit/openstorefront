/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.springbootdemo.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dshurtleff
 */
@Entity
@Table(name = "workplan_component_type", catalog = "test", schema = "storefront")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "WorkplanComponentType.findAll", query = "SELECT w FROM WorkplanComponentType w")
	, @NamedQuery(name = "WorkplanComponentType.findByComponentType", query = "SELECT w FROM WorkplanComponentType w WHERE w.workplanComponentTypePK.componentType = :componentType")
	, @NamedQuery(name = "WorkplanComponentType.findByWorkplanId", query = "SELECT w FROM WorkplanComponentType w WHERE w.workplanComponentTypePK.workplanId = :workplanId")})
public class WorkplanComponentType
		implements Serializable
{

	private static final long serialVersionUID = 1L;
	@EmbeddedId
	protected WorkplanComponentTypePK workplanComponentTypePK;
	@JoinColumn(name = "\"workplanId\"", referencedColumnName = "\"workPlanId\"", insertable = false, updatable = false)
	@ManyToOne(optional = false)
	private Workplan workplan;

	public WorkplanComponentType()
	{
	}

	public WorkplanComponentType(WorkplanComponentTypePK workplanComponentTypePK)
	{
		this.workplanComponentTypePK = workplanComponentTypePK;
	}

	public WorkplanComponentType(String componentType, String workplanId)
	{
		this.workplanComponentTypePK = new WorkplanComponentTypePK(componentType, workplanId);
	}

	public WorkplanComponentTypePK getWorkplanComponentTypePK()
	{
		return workplanComponentTypePK;
	}

	public void setWorkplanComponentTypePK(WorkplanComponentTypePK workplanComponentTypePK)
	{
		this.workplanComponentTypePK = workplanComponentTypePK;
	}

	public Workplan getWorkplan()
	{
		return workplan;
	}

	public void setWorkplan(Workplan workplan)
	{
		this.workplan = workplan;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (workplanComponentTypePK != null ? workplanComponentTypePK.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof WorkplanComponentType)) {
			return false;
		}
		WorkplanComponentType other = (WorkplanComponentType) object;
		if ((this.workplanComponentTypePK == null && other.workplanComponentTypePK != null) || (this.workplanComponentTypePK != null && !this.workplanComponentTypePK.equals(other.workplanComponentTypePK))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "edu.usu.sdl.proto.postgres.entity.WorkplanComponentType[ workplanComponentTypePK=" + workplanComponentTypePK + " ]";
	}

}
