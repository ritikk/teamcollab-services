package com.ritikk.teamcollab.domain;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Organization {
	private int organizationId;
	private String name;
	
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