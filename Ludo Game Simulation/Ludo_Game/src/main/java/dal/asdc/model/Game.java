package dal.asdc.model;

import org.springframework.stereotype.Component;

/**
 * @author Reshma Unnikrishnan
 * */

@Component

public class Game {
	private int game_id;
	private String game_time;
	private int player_id;
	private int game_winner_id;
	private int game_runner_id;
	private String game_status;
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public String getGame_time() {
		return game_time;
	}
	public void setGame_time(String game_time) {
		this.game_time = game_time;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public int getGame_winner_id() {
		return game_winner_id;
	}
	public void setGame_winner_id(int game_winner_id) {
		this.game_winner_id = game_winner_id;
	}
	public int getGame_runner_id() {
		return game_runner_id;
	}
	public void setGame_runner_id(int game_runner_id) {
		this.game_runner_id = game_runner_id;
	}
	public String getGame_status() {
		return game_status;
	}
	public void setGame_status(String game_status) {
		this.game_status = game_status;
	}
}
