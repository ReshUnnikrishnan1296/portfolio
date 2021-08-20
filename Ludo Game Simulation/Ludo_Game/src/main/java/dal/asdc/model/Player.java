package dal.asdc.model;

import org.springframework.stereotype.Component;

/**
 * @author Reshma Unnikrishnan
 * */

@Component
public class Player {
	private int player_id;
	private String player_name;
	private String player_email;
	private String player_password;
	private String acc_created_date;
	
	public int getPlayer_id() {
		return player_id;
	}
	public void setPlayer_id(int player_id) {
		this.player_id = player_id;
	}
	public String getPlayer_name() {
		return player_name;
	}
	public void setPlayer_name(String player_name) {
		this.player_name = player_name;
	}
	public String getPlayer_email() {
		return player_email;
	}
	public void setPlayer_email(String player_email) {
		this.player_email = player_email;
	}
	public String getPlayer_password() {
		return player_password;
	}
	public void setPlayer_password(String player_password) {
		this.player_password = player_password;
	}
	public String getAcc_created_date() {
		return acc_created_date;
	}
	public void setAcc_created_date(String acc_created_date) {
		this.acc_created_date = acc_created_date;
	}
	
	
}

