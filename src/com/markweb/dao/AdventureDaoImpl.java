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

@Repository
public class AdventureDaoImpl implements AdventureDao {

	private LoginConfig config = new LoginConfig();
	private DataSource source = config.getDataSource();
	private JdbcTemplate template = config.getJdbcTemplate(source);
	private static Logger log = Logger.getLogger("com.markweb.dao.AdventureDaoImpl");

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
	public List<Map<String, Object>> getAdventure(int playerAdventureId, String username) {
		
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
						+ "pa.RowId = ? "
					+ "AND "
						+ "pa.Complete = 0 "
					+ "AND "
						+ "user.Username = ?;";

			resultSet = template.queryForList(sql, new Object[] { playerAdventureId, username },
					new int[] { Types.INTEGER, Types.VARCHAR });

		} catch (Exception e) {
			log.info("AdventureDaoImpl getAdventure " + e);
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

	@Override
	public int completeAdventure(int playerAdventureId) {
		int rows = 0;
		try {
			String sql = "UPDATE "
						+ "Player_Adventure padv "
					+ "SET "
						+ "padv.Complete = 1 "
					+ "WHERE  "
						+ "padv.RowId = ?;";

			rows = template.update(sql, new Object[] { playerAdventureId },
					new int[] { Types.INTEGER });

		} catch (Exception e) {
			log.info("AdventureDaoImpl updateSceneUnselectedOptions " + e);
		}
		return rows;
	}
	
	

}
