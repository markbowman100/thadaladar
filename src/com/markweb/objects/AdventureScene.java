package com.markweb.objects;

import java.time.LocalDate;
import java.util.List;

public class AdventureScene {
	
	int sceneId;
	int playerSceneId;
	int playerId;
	String title;
	String story;
	String selectedOption;
	List<SceneOption> options;
	String comment;
	LocalDate startDate;
	LocalDate endDate;
	LocalDate dueDate;
	
	public int getSceneId() {
		return sceneId;
	}
	public void setSceneId(int sceneId) {
		this.sceneId = sceneId;
	}
	public int getPlayerSceneId() {
		return playerSceneId;
	}
	public void setPlayerSceneId(int playerSceneId) {
		this.playerSceneId = playerSceneId;
	}
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStory() {
		return story;
	}
	public void setStory(String story) {
		this.story = story;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public List<SceneOption> getOptions() {
		return options;
	}
	public void setOptions(List<SceneOption> options) {
		this.options = options;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
