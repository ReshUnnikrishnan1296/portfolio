package dal.asdc.ludo_score_history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import dal.asdc.dao.IPlayer_dao;
import dal.asdc.dao.Player_dao;
import dal.asdc.model.Player;

/**
 * @author Reshma Unnikrishnan**/

public class Player_history{

	private final IPlayer_dao iPlayer_dao;

    @Autowired
    public Player_history(Player_dao player_dao) {
        this.iPlayer_dao = player_dao;
    }
	
	public void save_player_history(Player player) {
		this.iPlayer_dao.save_record(player);
	}

	public List<Player> load_player_history() {
		return this.iPlayer_dao.select_all_record();
	}
	
	public Player search_player_history_by_id(int plyr_id) {
		return this.iPlayer_dao.filter_by_id(plyr_id);
	}
	
	public void update_record(int player_id, Player player) {
		this.iPlayer_dao.update_record(player_id, player);
	}
}
