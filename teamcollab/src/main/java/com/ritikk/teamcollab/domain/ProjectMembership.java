package com.ritikk.teamcollab.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class defines a member's Project Membership
 * @author ritik
 *
 */
@XmlRootElement
public class ProjectMembership {
	private int organizationID;
	private int projectID;
	private String username;
	private boolean hasWriteAccess;
	
	public ProjectMembership(int organizationID, int projectID, String username,
			boolean hasWriteAccess) {
		this.organizationID = organizationID;
		this.projectID = projectID;
		this.username = username;
		this.hasWriteAccess = hasWriteAccess;
	}
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isHasWriteAccess() {
		return hasWriteAccess;
	}
	public void setHasWriteAccess(boolean hasWriteAccess) {
		this.hasWriteAccess = hasWriteAccess;
	}
	
}
