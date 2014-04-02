package com.ritikk.teamcollab.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class defines a Project resource
 * @author ritik
 *
 */
@XmlRootElement
public class Project {
	private int projectID;
	private String name;
	private int organizationID;
	
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOrganizationID() {
		return organizationID;
	}
	public void setOrganizationID(int organizationID) {
		this.organizationID = organizationID;
	}
}
