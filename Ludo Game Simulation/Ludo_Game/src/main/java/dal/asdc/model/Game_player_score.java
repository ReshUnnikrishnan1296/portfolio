package dal.asdc.model;

/**
 * @author Reshma Unnikrishnan**/

public class Game_player_score {
	private String score_id;
	private int game_id;
	private int player_id;
	private int score;
	public String getScore_id() {
		return score_id;
	}
	public void setScore_id(String score_id) {
		this.score_id = score_id;
	}
	public int getGame_id() {
		return game_id;
	}
	public void setGame_id(int game_id) {
		this.game_id = game_id;
	}
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
}
