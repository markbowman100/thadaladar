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
						+ "padv.playerId = ?;";

			resultSet = template.queryForList(sql, new Object[] { campaignId, playerId },
					new int[] { Types.INTEGER, Types.INTEGER });

		} catch (Exception e) {
			log.info("AdventureDaoImpl getAdventures " + e);
		}

		return resultSet;
	}

	@Override
	public List<Map<String, Object>> getScenes(int playerAdventureId) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		//TODO: Need to add username in where clause
		try {
			String sql = "SELECT "
						+ "ps.RowId, ps.PlayerId, sce.SceneId, sce.Story, sce.Title "
					+ "FROM "
						+ "Scene sce "
					+ "LEFT JOIN "
						+ "Player_Scene ps "
					+ "ON "
						+ "sce.SceneId = ps.SceneId "
					+ "WHERE "
						+ "ps.PlayerAdventureId = ? "
					+ "AND "
						+ "ps.SelectedOption IS NULL "
					+ "ORDER BY "
						+ "OrderBy "
					+ "LIMIT 1;";

			resultSet = template.queryForList(sql, new Object[] { playerAdventureId },
					new int[] { Types.INTEGER });

		} catch (Exception e) {
			log.info("AdventureDaoImpl getScenes " + e);
		}

		return resultSet;
	}
	
	@Override
	public List<Map<String, Object>> getNextScene(int nextSceneId, int playerId) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		//TODO: Fix this to use the correct arguments
		try {
			String sql = "SELECT "
						+ "ps.RowId, ps.PlayerId, sce.SceneId, sce.Story, sce.Title "
					+ "FROM "
						+ "Scene sce "
					+ "LEFT JOIN "
						+ "Player_Scene ps "
					+ "ON "
						+ "sce.SceneId = ps.SceneId "
					+ "WHERE "
						+ "ps.SceneId = ? "
					+ "AND "
						+ "ps.PlayerId = ? "
					+ "LIMIT 1;";

			resultSet = template.queryForList(sql, new Object[] { nextSceneId, playerId },
					new int[] { Types.INTEGER, Types.INTEGER });

		} catch (Exception e) {
			log.info("AdventureDaoImpl getScenes " + e);
		}

		return resultSet;
	}

	@Override
	public List<Map<String, Object>> getScenesOptions(int sceneId) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		//TODO: Need to add username in where clause
		try {
			String sql = "SELECT "
						+ "so.Title, so.NextSceneId "
					+ "FROM "
						+ "Scene_Option so "
					+ "WHERE "
						+ "so.SceneId = ? ;";

			resultSet = template.queryForList(sql, new Object[] { sceneId },
					new int[] { Types.INTEGER });

		} catch (Exception e) {
			log.info("AdventureDaoImpl getScenesOptions " + e);
		}

		return resultSet;
	}
	
	@Override
	public List<Map<String, Object>> getAdventure(int playerAdventureId) {
		List<Map<String, Object>> resultSet = new ArrayList<Map<String, Object>>();
		//TODO: Might be a broken query... fix it
		try {
			String sql = "SELECT " 
						+ "pa.RowId, pa.PlayerId, adv.AdventureId, adv.Title, adv.Description "
					+ "FROM "
						+ "Campaign cam "
					+ "LEFT JOIN "
						+ "Player play "
					+ "ON "
						+ "cam.CampaignId = play.CampaignId "
					+ "LEFT JOIN "
						+ "Player_Adventure pa "
					+ "ON "
						+ "play.PlayerId = pa.PlayerId "
					+ "LEFT JOIN "
						+ "Adventure adv "
					+ "ON "
						+ "adv.AdventureId = pa.AdventureId "
					+ "LEFT JOIN "
						+ "User user "
					+ "ON "
						+ "play.UserId = user.UserId "
					+ "WHERE "
						+ "pa.RowId = ? ;";

			resultSet = template.queryForList(sql, new Object[] { playerAdventureId },
					new int[] { Types.INTEGER });

		} catch (Exception e) {
			log.info("AdventureDaoImpl getAdventure " + e);
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
			log.info("AdventureDaoImpl getPlayers " + e);
		}

		return resultSet;
	}

	@Override
	public int updateSceneSelectedOption(int sceneId, int playerSceneId, String option) {
		int rows = 0;
		try {
			String sql = "UPDATE "
							+ "Player_Scene "
						+ "SET "
							+ "SelectedOption = (SELECT OptionId FROM Scene_Option WHERE Title = ? AND SceneId = ?) "
						+ "WHERE  "
							+ "RowId = ?;";

			rows = template.update(sql, new Object[] { option, sceneId, playerSceneId },
					new int[] { Types.VARCHAR, Types.INTEGER, Types.INTEGER });

		} catch (Exception e) {
			log.info("AdventureDaoImpl updateSceneSelectedOption " + e);
		}
		return rows;
	}
	
	@Override
	public int updateSceneUnselectedOptions(int playerId, int nextSceneId, int playerAdventureId) {
		int rows = 0;
		try {
			String sql = "UPDATE "
						+ "Player_Scene ps "
					+ "LEFT JOIN "
						+ "Scene sce "
					+ "ON "
						+ "ps.SceneId = sce.SceneId "
					+ "SET "
						+ "ps.SelectedOption = 6 "
					+ "WHERE  "
						+ "ps.PlayerId = ? "
					+ "AND "
						+ "sce.OrderBy < (SELECT OrderBy FROM Scene WHERE SceneId = ?) "
					+ "AND "
						+ "ps.PlayerAdventureId = ? "
					+ "AND "
						+ "ps.SelectedOption IS NULL;";

			rows = template.update(sql, new Object[] { playerId, nextSceneId, playerAdventureId },
					new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER });

		} catch (Exception e) {
			log.info("AdventureDaoImpl updateSceneUnselectedOptions " + e);
		}
		return rows;
	}

}
