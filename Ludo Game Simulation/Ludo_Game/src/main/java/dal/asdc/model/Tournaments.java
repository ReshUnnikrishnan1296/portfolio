package dal.asdc.model;

/**
 * @author Reshma Unnikrishnan**/

public class Tournaments {
	private int tournament_id;
	private String tournament_time;
	private int num_of_participants;
	private int tournament_winner_id;
	private int tournament_runner_id;
	private String tournament_status;
	
	public int getTournament_id() {
		return tournament_id;
	}
	public void setTournament_id(int tournament_id) {
		this.tournament_id = tournament_id;
	}
	public String getTournament_time() {
		return tournament_time;
	}
	public void setTournament_time(String tournament_time) {
		this.tournament_time = tournament_time;
	}
	public int getNum_of_participants() {
		return num_of_participants;
	}
	public void setNum_of_participants(int num_of_participants) {
		this.num_of_participants = num_of_participants;
	}
	public int getTournament_winner_id() {
		return tournament_winner_id;
	}
	public void setTournament_winner_id(int tournament_winner_id) {
		this.tournament_winner_id = tournament_winner_id;
	}
	public int getTournament_runner_id() {
		return tournament_runner_id;
	}
	public void setTournament_runner_id(int tournament_runner_id) {
		this.tournament_runner_id = tournament_runner_id;
	}
	public String getTournament_status() {
		return tournament_status;
	}
	public void setTournament_status(String tournament_status) {
		this.tournament_status = tournament_status;
	}
}
