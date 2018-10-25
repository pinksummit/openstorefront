/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.springbootdemo.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dshurtleff
 */
@Entity
@Table(name = "workplan", catalog = "test", schema = "storefront")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Workplan.findAll", query = "SELECT w FROM Workplan w")
	, @NamedQuery(name = "Workplan.findByWorkPlanId", query = "SELECT w FROM Workplan w WHERE w.workPlanId = :workPlanId")
	, @NamedQuery(name = "Workplan.findByName", query = "SELECT w FROM Workplan w WHERE w.name = :name")
	, @NamedQuery(name = "Workplan.findByInProgressColor", query = "SELECT w FROM Workplan w WHERE w.inProgressColor = :inProgressColor")
	, @NamedQuery(name = "Workplan.findByPendingColor", query = "SELECT w FROM Workplan w WHERE w.pendingColor = :pendingColor")
	, @NamedQuery(name = "Workplan.findByCompleteColor", query = "SELECT w FROM Workplan w WHERE w.completeColor = :completeColor")
	, @NamedQuery(name = "Workplan.findBySubStatusColor", query = "SELECT w FROM Workplan w WHERE w.subStatusColor = :subStatusColor")
	, @NamedQuery(name = "Workplan.findByWorkPlanType", query = "SELECT w FROM Workplan w WHERE w.workPlanType = :workPlanType")
	, @NamedQuery(name = "Workplan.findByEvaluationTemplateId", query = "SELECT w FROM Workplan w WHERE w.evaluationTemplateId = :evaluationTemplateId")
	, @NamedQuery(name = "Workplan.findByAppliesToChildComponentTypes", query = "SELECT w FROM Workplan w WHERE w.appliesToChildComponentTypes = :appliesToChildComponentTypes")
	, @NamedQuery(name = "Workplan.findByDefaultWorkPlan", query = "SELECT w FROM Workplan w WHERE w.defaultWorkPlan = :defaultWorkPlan")
	, @NamedQuery(name = "Workplan.findByAdminRole", query = "SELECT w FROM Workplan w WHERE w.adminRole = :adminRole")
	, @NamedQuery(name = "Workplan.findBySecurityMarkingType", query = "SELECT w FROM Workplan w WHERE w.securityMarkingType = :securityMarkingType")
	, @NamedQuery(name = "Workplan.findByDataSensitivity", query = "SELECT w FROM Workplan w WHERE w.dataSensitivity = :dataSensitivity")
	, @NamedQuery(name = "Workplan.findByActiveStatus", query = "SELECT w FROM Workplan w WHERE w.activeStatus = :activeStatus")
	, @NamedQuery(name = "Workplan.findByCreateUser", query = "SELECT w FROM Workplan w WHERE w.createUser = :createUser")
	, @NamedQuery(name = "Workplan.findByCreateDts", query = "SELECT w FROM Workplan w WHERE w.createDts = :createDts")
	, @NamedQuery(name = "Workplan.findByUpdateUser", query = "SELECT w FROM Workplan w WHERE w.updateUser = :updateUser")
	, @NamedQuery(name = "Workplan.findByUpdateDts", query = "SELECT w FROM Workplan w WHERE w.updateDts = :updateDts")
	, @NamedQuery(name = "Workplan.findByAdminModified", query = "SELECT w FROM Workplan w WHERE w.adminModified = :adminModified")})
public class Workplan
		implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 80)
	@Column(name = "\"workPlanId\"")
	private String workPlanId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name")
	private String name;
	@Size(max = 255)
	@Column(name = "\"inProgressColor\"")
	private String inProgressColor;
	@Size(max = 255)
	@Column(name = "\"pendingColor\"")
	private String pendingColor;
	@Size(max = 255)
	@Column(name = "\"completeColor\"")
	private String completeColor;
	@Size(max = 255)
	@Column(name = "\"subStatusColor\"")
	private String subStatusColor;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "\"workPlanType\"")
	private String workPlanType;
	@Size(max = 255)
	@Column(name = "\"evaluationTemplateId\"")
	private String evaluationTemplateId;
	@Column(name = "\"appliesToChildComponentTypes\"")
	private Boolean appliesToChildComponentTypes;
	@Column(name = "\"defaultWorkPlan\"")
	private Boolean defaultWorkPlan;
	@Size(max = 255)
	@Column(name = "\"adminRole\"")
	private String adminRole;
	@Size(max = 255)
	@Column(name = "\"securityMarkingType\"")
	private String securityMarkingType;
	@Size(max = 255)
	@Column(name = "\"dataSensitivity\"")
	private String dataSensitivity;
	@Basic(optional = false)
	@NotNull
	@Column(name = "\"activeStatus\"")
	private String activeStatus;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "\"createUser\"")
	private String createUser;
	@Basic(optional = false)
	@NotNull
	@Column(name = "\"createDts\"")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDts;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "\"updateUser\"")
	private String updateUser;
	@Basic(optional = false)
	@NotNull
	@Column(name = "\"updateDts\"")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateDts;
	@Column(name = "\"adminModified\"")
	private Boolean adminModified;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "workplan")
	private List<WorkplanComponentType> workplanComponentTypeList;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "workplan")
	private List<WorkplanStep> workplanStepList;

	public Workplan()
	{
	}

	public Workplan(String workPlanId)
	{
		this.workPlanId = workPlanId;
	}

	public Workplan(String workPlanId, String name, String workPlanType, String activeStatus, String createUser, Date createDts, String updateUser, Date updateDts)
	{
		this.workPlanId = workPlanId;
		this.name = name;
		this.workPlanType = workPlanType;
		this.activeStatus = activeStatus;
		this.createUser = createUser;
		this.createDts = createDts;
		this.updateUser = updateUser;
		this.updateDts = updateDts;
	}

	public String getWorkPlanId()
	{
		return workPlanId;
	}

	public void setWorkPlanId(String workPlanId)
	{
		this.workPlanId = workPlanId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getInProgressColor()
	{
		return inProgressColor;
	}

	public void setInProgressColor(String inProgressColor)
	{
		this.inProgressColor = inProgressColor;
	}

	public String getPendingColor()
	{
		return pendingColor;
	}

	public void setPendingColor(String pendingColor)
	{
		this.pendingColor = pendingColor;
	}

	public String getCompleteColor()
	{
		return completeColor;
	}

	public void setCompleteColor(String completeColor)
	{
		this.completeColor = completeColor;
	}

	public String getSubStatusColor()
	{
		return subStatusColor;
	}

	public void setSubStatusColor(String subStatusColor)
	{
		this.subStatusColor = subStatusColor;
	}

	public String getWorkPlanType()
	{
		return workPlanType;
	}

	public void setWorkPlanType(String workPlanType)
	{
		this.workPlanType = workPlanType;
	}

	public String getEvaluationTemplateId()
	{
		return evaluationTemplateId;
	}

	public void setEvaluationTemplateId(String evaluationTemplateId)
	{
		this.evaluationTemplateId = evaluationTemplateId;
	}

	public Boolean getAppliesToChildComponentTypes()
	{
		return appliesToChildComponentTypes;
	}

	public void setAppliesToChildComponentTypes(Boolean appliesToChildComponentTypes)
	{
		this.appliesToChildComponentTypes = appliesToChildComponentTypes;
	}

	public Boolean getDefaultWorkPlan()
	{
		return defaultWorkPlan;
	}

	public void setDefaultWorkPlan(Boolean defaultWorkPlan)
	{
		this.defaultWorkPlan = defaultWorkPlan;
	}

	public String getAdminRole()
	{
		return adminRole;
	}

	public void setAdminRole(String adminRole)
	{
		this.adminRole = adminRole;
	}

	public String getSecurityMarkingType()
	{
		return securityMarkingType;
	}

	public void setSecurityMarkingType(String securityMarkingType)
	{
		this.securityMarkingType = securityMarkingType;
	}

	public String getDataSensitivity()
	{
		return dataSensitivity;
	}

	public void setDataSensitivity(String dataSensitivity)
	{
		this.dataSensitivity = dataSensitivity;
	}

	public String getActiveStatus()
	{
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus)
	{
		this.activeStatus = activeStatus;
	}

	public String getCreateUser()
	{
		return createUser;
	}

	public void setCreateUser(String createUser)
	{
		this.createUser = createUser;
	}

	public Date getCreateDts()
	{
		return createDts;
	}

	public void setCreateDts(Date createDts)
	{
		this.createDts = createDts;
	}

	public String getUpdateUser()
	{
		return updateUser;
	}

	public void setUpdateUser(String updateUser)
	{
		this.updateUser = updateUser;
	}

	public Date getUpdateDts()
	{
		return updateDts;
	}

	public void setUpdateDts(Date updateDts)
	{
		this.updateDts = updateDts;
	}

	public Boolean getAdminModified()
	{
		return adminModified;
	}

	public void setAdminModified(Boolean adminModified)
	{
		this.adminModified = adminModified;
	}

	@XmlTransient
	public List<WorkplanComponentType> getWorkplanComponentTypeList()
	{
		return workplanComponentTypeList;
	}

	public void setWorkplanComponentTypeList(List<WorkplanComponentType> workplanComponentTypeList)
	{
		this.workplanComponentTypeList = workplanComponentTypeList;
	}

	@XmlTransient
	public List<WorkplanStep> getWorkplanStepList()
	{
		return workplanStepList;
	}

	public void setWorkplanStepList(List<WorkplanStep> workplanStepList)
	{
		this.workplanStepList = workplanStepList;
	}

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (workPlanId != null ? workPlanId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Workplan)) {
			return false;
		}
		Workplan other = (Workplan) object;
		if ((this.workPlanId == null && other.workPlanId != null) || (this.workPlanId != null && !this.workPlanId.equals(other.workPlanId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "edu.usu.sdl.proto.postgres.entity.Workplan[ workPlanId=" + workPlanId + " ]";
	}

}
