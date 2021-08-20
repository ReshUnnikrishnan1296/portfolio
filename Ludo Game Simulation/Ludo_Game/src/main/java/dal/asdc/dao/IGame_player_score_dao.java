package dal.asdc.dao;

import java.util.List;
import dal.asdc.model.Game_player_score;

/**
 * @author Reshma Unnikrishnan**/

public interface IGame_player_score_dao {
	public void insert_score(Game_player_score game_player_score);
	public List<Game_player_score> get_player_score(int player_id);
	public List<Game_player_score> get_game_score(int game_id);
}
