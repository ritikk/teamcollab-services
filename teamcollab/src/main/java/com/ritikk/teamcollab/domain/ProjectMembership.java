package com.ritikk.teamcollab.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProjectMembership {
	private int organizationID;
	private int projectID;
	private int memberID;
	private boolean hasWriteAccess;
	public int getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(int organizationID) {
		this.organizationID = organizationID;
	}
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public int getMemberID() {
		return memberID;
	}
	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}
	public boolean isHasWriteAccess() {
		return hasWriteAccess;
	}
	public void setHasWriteAccess(boolean hasWriteAccess) {
		this.hasWriteAccess = hasWriteAccess;
	}
	
}
