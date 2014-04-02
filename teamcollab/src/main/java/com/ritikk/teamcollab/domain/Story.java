package com.ritikk.teamcollab.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class defines a Story resource
 * @author ritik
 *
 */
@XmlRootElement
public class Story {

	private int storyID;
	private String title;
	private String description;
	private float estimate;
	private float actual;
	private int assignedTo;
	private int projectID;
	public int getStoryID() {
		return storyID;
	}
	public void setStoryID(int storyID) {
		this.storyID = storyID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getEstimate() {
		return estimate;
	}
	public void setEstimate(float estimate) {
		this.estimate = estimate;
	}
	public float getActual() {
		return actual;
	}
	public void setActual(float actual) {
		this.actual = actual;
	}
	public int getAssignedTo() {
		return assignedTo;
	}
	public void setAssignedTo(int assignedTo) {
		this.assignedTo = assignedTo;
	}
	public int getProjectID() {
		return projectID;
	}
	public void setProjectID(int projectID) {
		this.projectID = projectID;
	}
	
	
}
