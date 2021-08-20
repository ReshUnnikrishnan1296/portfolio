package dal.asdc.dao;

import java.util.List;

import dal.asdc.model.Tournaments;

/**
 * @author Reshma Unnikrishnan**/

public interface ITournament_dao {
	public void create_record(Tournaments tournaments);
	public List<Tournaments> select_all_record();
	public void update_record(Integer tournament_id, Tournaments tournaments);
	public void filter_by_id();
}
