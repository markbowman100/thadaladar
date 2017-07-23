package com.markweb.objects;

import java.util.List;
import java.time.*;

public class Campaign {
	
	int playerId;
	int id;
	String title;
	String description;
	String game;
	String version;
	List<String> gameMasters;
	List<String> otherPlayers;
	boolean acceptingPlayers;
	LocalDate startDate;
	LocalDate endDate;
	List<Adventure> adventures;
	
	
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
	public List<String> getGameMasters() {
		return gameMasters;
	}
	public void setGameMasters(List<String> gameMasters) {
		this.gameMasters = gameMasters;
	}
	public List<String> getOtherPlayers() {
		return otherPlayers;
	}
	public void setOtherPlayers(List<String> otherPlayers) {
		this.otherPlayers = otherPlayers;
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
	public List<Adventure> getAdventures() {
		return adventures;
	}
	public void setAdventures(List<Adventure> adventures) {
		this.adventures = adventures;
	}
	public boolean isAcceptingPlayers() {
		return acceptingPlayers;
	}
	public void setAcceptingPlayers(boolean acceptingPlayers) {
		this.acceptingPlayers = acceptingPlayers;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
