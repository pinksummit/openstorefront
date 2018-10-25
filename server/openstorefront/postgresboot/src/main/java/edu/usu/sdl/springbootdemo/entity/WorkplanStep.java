/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.springbootdemo.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dshurtleff
 */
@Entity
@Table(name = "workplan_step", catalog = "test", schema = "storefront")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "WorkplanStep.findAll", query = "SELECT w FROM WorkplanStep w")
	, @NamedQuery(name = "WorkplanStep.findByWorkPlanStepId", query = "SELECT w FROM WorkplanStep w WHERE w.workPlanStepId = :workPlanStepId")
	, @NamedQuery(name = "WorkplanStep.findByName", query = "SELECT w FROM WorkplanStep w WHERE w.name = :name")
	, @NamedQuery(name = "WorkplanStep.findByDescription", query = "SELECT w FROM WorkplanStep w WHERE w.description = :description")
	, @NamedQuery(name = "WorkplanStep.findByRoleLogicCondition", query = "SELECT w FROM WorkplanStep w WHERE w.roleLogicCondition = :roleLogicCondition")
	, @NamedQuery(name = "WorkplanStep.findByStepOrder", query = "SELECT w FROM WorkplanStep w WHERE w.stepOrder = :stepOrder")
	, @NamedQuery(name = "WorkplanStep.findByApprovalStateToMatch", query = "SELECT w FROM WorkplanStep w WHERE w.approvalStateToMatch = :approvalStateToMatch")})
public class WorkplanStep
		implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "\"workPlanStepId\"")
	private String workPlanStepId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name")
	private String name;
	@Size(max = 2147483647)
	@Column(name = "description")
	private String description;
	@Size(max = 20)
	@Column(name = "\"roleLogicCondition\"")
	private String roleLogicCondition;
	@Basic(optional = false)
	@NotNull
	@Column(name = "\"stepOrder\"")
	private int stepOrder;
	@Size(max = 20)
	@Column(name = "\"approvalStateToMatch\"")
	private String approvalStateToMatch;
	@JoinColumn(name = "\"workPlanId\"", referencedColumnName = "\"workPlanId\"")
	@ManyToOne(optional = false)
	private Workplan workplan;

	public WorkplanStep()
	{
	}

	public WorkplanStep(String workPlanStepId)
	{
		this.workPlanStepId = workPlanStepId;
	}

	public WorkplanStep(String workPlanStepId, String name, int stepOrder)
	{
		this.workPlanStepId = workPlanStepId;
		this.name = name;
		this.stepOrder = stepOrder;
	}

	public String getWorkPlanStepId()
	{
		return workPlanStepId;
	}

	public void setWorkPlanStepId(String workPlanStepId)
	{
		this.workPlanStepId = workPlanStepId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getRoleLogicCondition()
	{
		return roleLogicCondition;
	}

	public void setRoleLogicCondition(String roleLogicCondition)
	{
		this.roleLogicCondition = roleLogicCondition;
	}

	public int getStepOrder()
	{
		return stepOrder;
	}

	public void setStepOrder(int stepOrder)
	{
		this.stepOrder = stepOrder;
	}

	public String getApprovalStateToMatch()
	{
		return approvalStateToMatch;
	}

	public void setApprovalStateToMatch(String approvalStateToMatch)
	{
		this.approvalStateToMatch = approvalStateToMatch;
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
		hash += (workPlanStepId != null ? workPlanStepId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof WorkplanStep)) {
			return false;
		}
		WorkplanStep other = (WorkplanStep) object;
		if ((this.workPlanStepId == null && other.workPlanStepId != null) || (this.workPlanStepId != null && !this.workPlanStepId.equals(other.workPlanStepId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "edu.usu.sdl.proto.postgres.entity.WorkplanStep[ workPlanStepId=" + workPlanStepId + " ]";
	}

}
