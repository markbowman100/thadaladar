package com.markweb.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.markweb.config.LoginConfig;
import com.markweb.objects.CreateCampaignFormBean;

@Repository
public class CampaignDaoImpl implements CampaignDao {
	
	private LoginConfig config = new LoginConfig();
	private DataSource source = config.getDataSource();
	private JdbcTemplate template = config.getJdbcTemplate(source);
	private static Logger log = Logger.getLogger("com.markweb.controller");

	@Override
	public List<Map<String, Object>> getCampaigns(String username) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();

		try {
			String sql = "SELECT "
						+ "play.PlayerId, cam.CampaignId, Title, Description, Game, Version, AcceptingPlayers "
					+ "FROM "
						+ "Campaign cam "
					+ "LEFT JOIN "
						+ "Player play "
					+ "ON "
						+ "cam.CampaignId = play.CampaignId "
					+ "LEFT JOIN "
						+ "User user "
					+ "ON "
						+ "user.UserId = play.UserId "
					+ "WHERE "
						+ "user.Username = ?;";

			resultSet = template.queryForList(sql, new Object[] { username },
					new int[] { Types.VARCHAR });

		} catch (Exception e) {
			log.info("AdventureDaoImpl getCampaigns " + e);
		}
		
		return resultSet;
	}

	@Override
	public List<Map<String, Object>> getAdventures(int campaignId, int playerId) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		//TODO: Might be a broken query... fix it
		try {
			String sql = "SELECT DISTINCT "
						+ "padv.RowId, padv.PlayerId, adv.AdventureId, adv.Title, adv.Description "
					+ "FROM "
						+ "Adventure adv "
					+ "LEFT JOIN "
						+ "Player_Adventure padv "
					+ "ON "
						+ "padv.AdventureId = adv.AdventureId "
					+ "WHERE "
						+ "padv.CampaignId = ? "
					+ "AND "
						+ "padv.playerId = ? "
					+ "AND "
						+ "padv.Complete = 0;";

			resultSet = template.queryForList(sql, new Object[] { campaignId, playerId },
					new int[] { Types.INTEGER, Types.INTEGER });

		} catch (Exception e) {
			log.info("CampaignDaoImpl getAdventures " + e);
		}
		
		return resultSet;
	}
	
	@Override
	public List<Map<String, Object>> getPlayers(int campaignId) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		//TODO: Need to add username in where clause
		try {
			String sql = "SELECT "
							+ "Username, RoleId "
						+ "FROM "
							+ "Player play "
						+ "LEFT JOIN "
							+ "User user "
						+ "ON "
							+ "play.UserId = user.UserId "							
						+ "WHERE "
							+ "CampaignId = ?;";

			resultSet = template.queryForList(sql, new Object[] { campaignId },
					new int[] { Types.INTEGER });

		} catch (Exception e) {
			log.info("CampaignDaoImpl getPlayers " + e);
		}

		return resultSet;
	}

	@Override
	public int createCampaign(CreateCampaignFormBean campaign, String username) {
		int rows = 0;
		try {
			String sql = "INSERT INTO "
						+ "Campaign( "
						+ "Title "
						+ ", Description "
						+ ", Game "
						+ ", Version "
						+ ", AcceptingPlayers "
						+ ", Creator) "
					+ "VALUES "
						+ "(?, ?, ?, ?, ?, (SELECT UserId FROM User WHERE Username = ?)) ";

			rows = template.update(sql, new Object[] { campaign.getTitle(), campaign.getDescription(), 
					campaign.getGame(), campaign.getVersion(), campaign.getAcceptingPlayers(), username},
					new int[] { Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, 
							Types.INTEGER, Types.VARCHAR});

		} catch (Exception e) {
			log.info("CampaignDaoImpl createCampaign " + e);
		}
		return rows;
	}
}
