package com.markweb.objects;

import java.time.LocalDate;

public class Adventure {
	
	int adventureId;
	int playerAdventureId;
	int playerId;
	String title;
	String description;
	AdventureScene scene;
	LocalDate startDate;
	LocalDate endDate;
	LocalDate dueDate;
	
	public int getAdventureId() {
		return adventureId;
	}
	public void setAdventureId(int adventureId) {
		this.adventureId = adventureId;
	}
	public int getPlayerAdventureId() {
		return playerAdventureId;
	}
	public void setPlayerAdventureId(int playerAdventureId) {
		this.playerAdventureId = playerAdventureId;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public void setScene(AdventureScene scene) {
		this.scene = scene;
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
	public AdventureScene getScene() {
		return scene;
	}
	public void setScenes(AdventureScene scene) {
		this.scene = scene;
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
