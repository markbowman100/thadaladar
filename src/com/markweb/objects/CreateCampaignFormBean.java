package com.markweb.objects;

import org.hibernate.validator.constraints.NotBlank;

public class CreateCampaignFormBean {
	
	@NotBlank(message = "Must Not Be Blank")
	String title;
	@NotBlank(message = "Must Not Be Blank")
	String description;
	@NotBlank(message = "Must Not Be Blank")
	String game;
	@NotBlank(message = "Must Not Be Blank")
	String version;
	Boolean acceptingPlayers;
	Boolean publicGame;
	
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
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Boolean getAcceptingPlayers() {
		return acceptingPlayers;
	}
	public void setAcceptingPlayers(Boolean acceptingPlayers) {
		this.acceptingPlayers = acceptingPlayers;
	}
	public Boolean getPublicGame() {
		return publicGame;
	}
	public void setPublicGame(Boolean publicGame) {
		this.publicGame = publicGame;
	}
	
}
