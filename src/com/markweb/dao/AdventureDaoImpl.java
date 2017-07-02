package com.markweb.dao;

import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import com.markweb.config.LoginConfig;
import com.markweb.objects.Campaign;

public class AdventureDaoImpl implements AdventureDao {

	private LoginConfig config = new LoginConfig();
	private DataSource source = config.getDataSource();
	private JdbcTemplate template = config.getJdbcTemplate(source);
	private static Logger log = Logger.getLogger("com.markweb.controller");

	@Override
	public List<Map<String, Object>> getCampaigns(String username) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();

		try {
			String sql = "SELECT "
						+ "cam.CampaignId, Title, Description, Game, Version, AcceptingPlayers, StartDate, EndDate "
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
	public List<Map<String, Object>> getAdventures(int campaignId, String username) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		//TODO: Might be a broken query... fix it
		try {
			String sql = "SELECT "
						+ "adv.AdventureId, adv.PlayerId, adv.Title, adv.Description "
					+ "FROM "
						+ "Adventure adv"
					+ "LEFT JOIN "
						+ "Campaign cam "
					+ "ON "
						+ "cam.CampaignId = adv.CampaignId "
					+ "LEFT JOIN "
						+ "Player play "
					+ "ON "
						+ "cam.CampaignId = play.CampaignId "
					+ "WHERE "
						+ "CampaignId = ?;";

			resultSet = template.queryForList(sql, new Object[] { campaignId },
					new int[] { Types.INTEGER });

		} catch (Exception e) {
			log.info("JourneyDaoImpl attemptLoginCredentials " + e);
		}

		return resultSet;
	}

	@Override
	public List<Map<String, Object>> getScenes(int adventureId, String username) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		//TODO: Need to add username in where clause
		try {
			String sql = "SELECT "
						+ "SceneId, Title, Story "
					+ "FROM "
						+ "Scene "
					+ "WHERE "
						+ "AdventureId = ?;";

			resultSet = template.queryForList(sql, new Object[] { adventureId },
					new int[] { Types.INTEGER });

		} catch (Exception e) {
			log.info("JourneyDaoImpl attemptLoginCredentials " + e);
		}

		return resultSet;
	}

	@Override
	public List<Map<String, Object>> getScenesOptions(int sceneId, String username) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		//TODO: Need to add username in where clause
		try {
			String sql = "SELECT "
						+ "OptionId, Title "
					+ "FROM "
						+ "Scene_Option "
					+ "WHERE "
						+ "OptionId = ?;";

			resultSet = template.queryForList(sql, new Object[] { sceneId },
					new int[] { Types.INTEGER });

		} catch (Exception e) {
			log.info("JourneyDaoImpl attemptLoginCredentials " + e);
		}

		return resultSet;
	}

	@Override
	public List<Map<String, Object>> getPlayers(int campaignId, String username) {
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
			log.info("JourneyDaoImpl attemptLoginCredentials " + e);
		}

		return resultSet;
	}

}
