package dal.asdc.ludo_score_history;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dal.asdc.dao.Game_dao;
import dal.asdc.dao.Game_player_score_dao;
import dal.asdc.dao.IGame_dao;
import dal.asdc.dao.IGame_player_score_dao;
import dal.asdc.dao.IPlayer_dao;
import dal.asdc.dao.Player_dao;
import dal.asdc.model.Game;
import dal.asdc.model.Game_player_score;
import dal.asdc.model.Player;

/**
 * @author Reshma Unnikrishnan**/

public class Game_score_history implements IScore_history{
	
	IGame_player_score_dao gm_ply_sc = new Game_player_score_dao();
	private Game_player_score model_gm_ply = new Game_player_score();
	private List<Game_player_score> model_gm_ply_list = new ArrayList<>();
	IGame_dao game_dao = new Game_dao();
	Game game = new Game();
	IPlayer_dao plyr_dao = new Player_dao();
	Player plyr = new Player();
	
	@Override
	public void save_history() {
		gm_ply_sc.insert_score(model_gm_ply);
	}

	@Override
	public void load_history(int game_id) {
		model_gm_ply_list = gm_ply_sc.get_game_score(game_id);
	}

	@Override
	public Map<String,String> form_score_board() {
		int player_id=0;
		Map<String,String> player_score_map = new HashMap<String,String>();
		for(Game_player_score model : model_gm_ply_list) {
            player_id = model.getPlayer_id();
            plyr = plyr_dao.filter_by_id(player_id);
    		String player_name = plyr.getPlayer_name();
    		String player_score = String.valueOf(model.getScore());
    		player_score_map.put("player_name", player_name);
    		player_score_map.put("player_score", player_score);
        }
		
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
