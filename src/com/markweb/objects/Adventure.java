package com.markweb.objects;

import java.time.LocalDate;
import java.util.List;

public class Adventure {
	
	String title;
	List<AdventureScene> scenes;
	LocalDate startDate;
	LocalDate endDate;
	LocalDate dueDate;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<AdventureScene> getScenes() {
		return scenes;
	}
	public void setScenes(List<AdventureScene> scenes) {
		this.scenes = scenes;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
}
