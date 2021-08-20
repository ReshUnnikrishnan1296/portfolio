package dal.asdc.dao;

import java.util.List;

import dal.asdc.model.Player;

/**
 * @author Reshma Unnikrishnan
 * */

public interface IPlayer_dao {
	public void save_record(Player player);
	public List<Player> select_all_record();
	public void update_record(int player_id, Player player);
	public Player filter_by_id(int player_id);
}

