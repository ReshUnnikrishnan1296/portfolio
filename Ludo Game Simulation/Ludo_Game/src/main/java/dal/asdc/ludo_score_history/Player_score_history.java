package dal.asdc.ludo_score_history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.asdc.dao.Game_player_score_dao;
import dal.asdc.dao.IGame_player_score_dao;
import dal.asdc.dao.IPlayer_dao;
import dal.asdc.dao.Player_dao;
import dal.asdc.model.Game_player_score;
import dal.asdc.model.Player;

/**
 * @author Reshma Unnikrishnan**/

public class Player_score_history implements IScore_history {
	
	IGame_player_score_dao gm_ply_sc = new Game_player_score_dao();
	private Game_player_score model_gm_ply = new Game_player_score();
	private List<Game_player_score> model_gm_ply_list = new ArrayList<>();
	IPlayer_dao plyr_dao = new Player_dao();
	Player plyr = new Player();
	
	@Override
	public void save_history() {
		gm_ply_sc.insert_score(model_gm_ply);
	}

	@Override
	public void load_history(int player_id) {
		model_gm_ply_list = gm_ply_sc.get_player_score(player_id);
	}

	@Override
	public Map<String,String> form_score_board() {
		int plyr_total_score = 0;
		int player_id=0;
		Map<String,String> player_score_map = new HashMap<String,String>();
		for(Game_player_score model : model_gm_ply_list) {
            System.out.println(model.getScore());
            plyr_total_score = plyr_total_score + model.getScore();
            player_id = model.getPlayer_id();
        }
		plyr = plyr_dao.filter_by_id(player_id);
		String player_name = plyr.getPlayer_name();
		String player_email = plyr.getPlayer_email();
		player_score_map.put("player_name", player_name);
		player_score_map.put("player_email", player_email);
		player_score_map.put("total_score", String.valueOf(plyr_total_score));
		return player_score_map;
	}

	public void form_save_score(String score_id,int game_id,int player_id,int score) {
		model_gm_ply.setScore_id(score_id);
		model_gm_ply.setGame_id(game_id);
		model_gm_ply.setPlayer_id(player_id);
		model_gm_ply.setScore(score);
		
		save_history();
	}
}
