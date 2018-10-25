/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.usu.sdl.springbootdemo.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dshurtleff
 */
@Entity
@Table(name = "component", catalog = "test", schema = "storefront")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name = "Component.findAll", query = "SELECT c FROM Component c")
	, @NamedQuery(name = "Component.findByComponentId", query = "SELECT c FROM Component c WHERE c.componentId = :componentId")
	, @NamedQuery(name = "Component.findByName", query = "SELECT c FROM Component c WHERE c.name = :name")
	, @NamedQuery(name = "Component.findByDescription", query = "SELECT c FROM Component c WHERE c.description = :description")
	, @NamedQuery(name = "Component.findByComponentType", query = "SELECT c FROM Component c WHERE c.componentType = :componentType")
	, @NamedQuery(name = "Component.findByGuid", query = "SELECT c FROM Component c WHERE c.guid = :guid")
	, @NamedQuery(name = "Component.findByExternalId", query = "SELECT c FROM Component c WHERE c.externalId = :externalId")
	, @NamedQuery(name = "Component.findByOrganization", query = "SELECT c FROM Component c WHERE c.organization = :organization")
	, @NamedQuery(name = "Component.findByReleaseDate", query = "SELECT c FROM Component c WHERE c.releaseDate = :releaseDate")
	, @NamedQuery(name = "Component.findByVersion", query = "SELECT c FROM Component c WHERE c.version = :version")
	, @NamedQuery(name = "Component.findByApprovedUser", query = "SELECT c FROM Component c WHERE c.approvedUser = :approvedUser")
	, @NamedQuery(name = "Component.findByApprovedDts", query = "SELECT c FROM Component c WHERE c.approvedDts = :approvedDts")
	, @NamedQuery(name = "Component.findByChangeApprovalMode", query = "SELECT c FROM Component c WHERE c.changeApprovalMode = :changeApprovalMode")
	, @NamedQuery(name = "Component.findByLastActivityDts", query = "SELECT c FROM Component c WHERE c.lastActivityDts = :lastActivityDts")
	, @NamedQuery(name = "Component.findBySubmittedDts", query = "SELECT c FROM Component c WHERE c.submittedDts = :submittedDts")
	, @NamedQuery(name = "Component.findByNotifyOfApprovalEmail", query = "SELECT c FROM Component c WHERE c.notifyOfApprovalEmail = :notifyOfApprovalEmail")
	, @NamedQuery(name = "Component.findByDataSource", query = "SELECT c FROM Component c WHERE c.dataSource = :dataSource")
	, @NamedQuery(name = "Component.findByLastModificationType", query = "SELECT c FROM Component c WHERE c.lastModificationType = :lastModificationType")
	, @NamedQuery(name = "Component.findByFileHistoryId", query = "SELECT c FROM Component c WHERE c.fileHistoryId = :fileHistoryId")
	, @NamedQuery(name = "Component.findByRecordVersion", query = "SELECT c FROM Component c WHERE c.recordVersion = :recordVersion")
	, @NamedQuery(name = "Component.findByApprovalState", query = "SELECT c FROM Component c WHERE c.approvalState = :approvalState")
	, @NamedQuery(name = "Component.findByPendingChangeId", query = "SELECT c FROM Component c WHERE c.pendingChangeId = :pendingChangeId")
	, @NamedQuery(name = "Component.findBySubmissionUser", query = "SELECT c FROM Component c WHERE c.submissionUser = :submissionUser")
	, @NamedQuery(name = "Component.findByOwnerUser", query = "SELECT c FROM Component c WHERE c.ownerUser = :ownerUser")
	, @NamedQuery(name = "Component.findByAssignedLibrarian", query = "SELECT c FROM Component c WHERE c.assignedLibrarian = :assignedLibrarian")
	, @NamedQuery(name = "Component.findByAssignedLibrarianDts", query = "SELECT c FROM Component c WHERE c.assignedLibrarianDts = :assignedLibrarianDts")
	, @NamedQuery(name = "Component.findBySecurityMarkingType", query = "SELECT c FROM Component c WHERE c.securityMarkingType = :securityMarkingType")
	, @NamedQuery(name = "Component.findByDataSensitivity", query = "SELECT c FROM Component c WHERE c.dataSensitivity = :dataSensitivity")
	, @NamedQuery(name = "Component.findByActiveStatus", query = "SELECT c FROM Component c WHERE c.activeStatus = :activeStatus")
	, @NamedQuery(name = "Component.findByCreateUser", query = "SELECT c FROM Component c WHERE c.createUser = :createUser")
	, @NamedQuery(name = "Component.findByCreateDts", query = "SELECT c FROM Component c WHERE c.createDts = :createDts")
	, @NamedQuery(name = "Component.findByUpdateUser", query = "SELECT c FROM Component c WHERE c.updateUser = :updateUser")
	, @NamedQuery(name = "Component.findByUpdateDts", query = "SELECT c FROM Component c WHERE c.updateDts = :updateDts")
	, @NamedQuery(name = "Component.findByAdminModified", query = "SELECT c FROM Component c WHERE c.adminModified = :adminModified")})
public class Component
		implements Serializable
{

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 80)
	@Column(name = "\"componentId\"")
	private String componentId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "name")
	private String name;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 2147483647)
	@Column(name = "description")
	private String description;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "\"componentType\"")
	private String componentType;
	@Size(max = 255)
	@Column(name = "guid")
	private String guid;
	@Size(max = 255)
	@Column(name = "\"externalId\"")
	private String externalId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "organization")
	private String organization;
	@Column(name = "\"releaseDate\"")
	@Temporal(TemporalType.DATE)
	private Date releaseDate;
	@Size(max = 255)
	@Column(name = "version")
	private String version;
	@Size(max = 80)
	@Column(name = "\"approvedUser\"")
	private String approvedUser;
	@Column(name = "\"approvedDts\"")
	@Temporal(TemporalType.TIMESTAMP)
	private Date approvedDts;
	@Size(max = 80)
	@Column(name = "\"changeApprovalMode\"")
	private String changeApprovalMode;
	@Column(name = "\"lastActivityDts\"")
	@Temporal(TemporalType.TIME)
	private Date lastActivityDts;
	@Column(name = "\"submittedDts\"")
	@Temporal(TemporalType.TIME)
	private Date submittedDts;
	@Size(max = 255)
	@Column(name = "\"notifyOfApprovalEmail\"")
	private String notifyOfApprovalEmail;
	@Size(max = 255)
	@Column(name = "\"dataSource\"")
	private String dataSource;
	@Size(max = 255)
	@Column(name = "\"lastModificationType\"")
	private String lastModificationType;
	@Size(max = 255)
	@Column(name = "\"fileHistoryId\"")
	private String fileHistoryId;
	@Column(name = "\"recordVersion\"")
	private Integer recordVersion;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 80)
	@Column(name = "\"approvalState\"")
	private String approvalState;
	@Size(max = 80)
	@Column(name = "\"pendingChangeId\"")
	private String pendingChangeId;
	@Size(max = 255)
	@Column(name = "\"submissionUser\"")
	private String submissionUser;
	@Size(max = 255)
	@Column(name = "\"ownerUser\"")
	private String ownerUser;
	@Size(max = 255)
	@Column(name = "\"assignedLibrarian\"")
	private String assignedLibrarian;
	@Column(name = "\"assignedLibrarianDts\"")
	@Temporal(TemporalType.DATE)
	private Date assignedLibrarianDts;
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

	public Component()
	{
	}

	public Component(String componentId)
	{
		this.componentId = componentId;
	}

	public Component(String componentId, String name, String description, String componentType, String organization, String approvalState, String activeStatus, String createUser, Date createDts, String updateUser, Date updateDts)
	{
		this.componentId = componentId;
		this.name = name;
		this.description = description;
		this.componentType = componentType;
		this.organization = organization;
		this.approvalState = approvalState;
		this.activeStatus = activeStatus;
		this.createUser = createUser;
		this.createDts = createDts;
		this.updateUser = updateUser;
		this.updateDts = updateDts;
	}

	public String getComponentId()
	{
		return componentId;
	}

	public void setComponentId(String componentId)
	{
		this.componentId = componentId;
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

	public String getComponentType()
	{
		return componentType;
	}

	public void setComponentType(String componentType)
	{
		this.componentType = componentType;
	}

	public String getGuid()
	{
		return guid;
	}

	public void setGuid(String guid)
	{
		this.guid = guid;
	}

	public String getExternalId()
	{
		return externalId;
	}

	public void setExternalId(String externalId)
	{
		this.externalId = externalId;
	}

	public String getOrganization()
	{
		return organization;
	}

	public void setOrganization(String organization)
	{
		this.organization = organization;
	}

	public Date getReleaseDate()
	{
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate)
	{
		this.releaseDate = releaseDate;
	}

	public String getVersion()
	{
		return version;
	}

	public void setVersion(String version)
	{
		this.version = version;
	}

	public String getApprovedUser()
	{
		return approvedUser;
	}

	public void setApprovedUser(String approvedUser)
	{
		this.approvedUser = approvedUser;
	}

	public Date getApprovedDts()
	{
		return approvedDts;
	}

	public void setApprovedDts(Date approvedDts)
	{
		this.approvedDts = approvedDts;
	}

	public String getChangeApprovalMode()
	{
		return changeApprovalMode;
	}

	public void setChangeApprovalMode(String changeApprovalMode)
	{
		this.changeApprovalMode = changeApprovalMode;
	}

	public Date getLastActivityDts()
	{
		return lastActivityDts;
	}

	public void setLastActivityDts(Date lastActivityDts)
	{
		this.lastActivityDts = lastActivityDts;
	}

	public Date getSubmittedDts()
	{
		return submittedDts;
	}

	public void setSubmittedDts(Date submittedDts)
	{
		this.submittedDts = submittedDts;
	}

	public String getNotifyOfApprovalEmail()
	{
		return notifyOfApprovalEmail;
	}

	public void setNotifyOfApprovalEmail(String notifyOfApprovalEmail)
	{
		this.notifyOfApprovalEmail = notifyOfApprovalEmail;
	}

	public String getDataSource()
	{
		return dataSource;
	}

	public void setDataSource(String dataSource)
	{
		this.dataSource = dataSource;
	}

	public String getLastModificationType()
	{
		return lastModificationType;
	}

	public void setLastModificationType(String lastModificationType)
	{
		this.lastModificationType = lastModificationType;
	}

	public String getFileHistoryId()
	{
		return fileHistoryId;
	}

	public void setFileHistoryId(String fileHistoryId)
	{
		this.fileHistoryId = fileHistoryId;
	}

	public Integer getRecordVersion()
	{
		return recordVersion;
	}

	public void setRecordVersion(Integer recordVersion)
	{
		this.recordVersion = recordVersion;
	}

	public String getApprovalState()
	{
		return approvalState;
	}

	public void setApprovalState(String approvalState)
	{
		this.approvalState = approvalState;
	}

	public String getPendingChangeId()
	{
		return pendingChangeId;
	}

	public void setPendingChangeId(String pendingChangeId)
	{
		this.pendingChangeId = pendingChangeId;
	}

	public String getSubmissionUser()
	{
		return submissionUser;
	}

	public void setSubmissionUser(String submissionUser)
	{
		this.submissionUser = submissionUser;
	}

	public String getOwnerUser()
	{
		return ownerUser;
	}

	public void setOwnerUser(String ownerUser)
	{
		this.ownerUser = ownerUser;
	}

	public String getAssignedLibrarian()
	{
		return assignedLibrarian;
	}

	public void setAssignedLibrarian(String assignedLibrarian)
	{
		this.assignedLibrarian = assignedLibrarian;
	}

	public Date getAssignedLibrarianDts()
	{
		return assignedLibrarianDts;
	}

	public void setAssignedLibrarianDts(Date assignedLibrarianDts)
	{
		this.assignedLibrarianDts = assignedLibrarianDts;
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

	@Override
	public int hashCode()
	{
		int hash = 0;
		hash += (componentId != null ? componentId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object)
	{
		// TODO: Warning - this method won't work in the case the id fields are not set
		if (!(object instanceof Component)) {
			return false;
		}
		Component other = (Component) object;
		if ((this.componentId == null && other.componentId != null) || (this.componentId != null && !this.componentId.equals(other.componentId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString()
	{
		return "edu.usu.sdl.proto.postgres.entity.Component[ componentId=" + componentId + " ]";
	}

}
