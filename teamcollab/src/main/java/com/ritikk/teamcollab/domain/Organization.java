package com.ritikk.teamcollab.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class defines an Organization resource
 * @author ritik
 *
 */
@XmlRootElement
public class Organization {
	private int organizationId;
	private String name;
	
	public Organization() {
	}
	
	public Organization(int organizationId, String name) {
		this.organizationId = organizationId;
		this.name = name;
	}
	
	public int getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}