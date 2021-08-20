package dal.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.asdc.model.Player;

/**
 * @author Reshma Unnikrishnan **/

public class Player_dao implements IPlayer_dao {
		
	@Override
	public void save_record(Player player) {
		IJDBC_Connection jdbc = new JDBC_Connection();
		String create_query = "INSERT into Player (player_id, player_name, player_email, player_password, acc_created_date) "
				+ "values(?,?,?,?,?)";
		try {
			Connection conn = jdbc.createDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(create_query);
			preparedStatement.setInt(1, player.getPlayer_id());
            preparedStatement.setString(2, player.getPlayer_name());
            preparedStatement.setString(3, player.getPlayer_email());
            preparedStatement.setString(4, player.getPlayer_password());
            preparedStatement.setString(5, player.getAcc_created_date());
            preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Player> select_all_record() {
	        List<Player> player_list = new ArrayList<>();
	        IJDBC_Connection jdbc = new JDBC_Connection();
	        String select_all_query = "SELECT * from Players";
	        try {
				Connection conn = jdbc.createDBConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(select_all_query);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    Player player = new Player();
	                    player.setPlayer_id(resultSet.getInt("player_id"));
	                    player.setPlayer_name(resultSet.getString("player_name"));
	                    player.setPlayer_email(resultSet.getString("player_email"));
	                    player.setPlayer_password(resultSet.getString("player_password"));
	                    player.setAcc_created_date(resultSet.getString("acc_created_date"));
	                    player_list.add(player);
	                }
	                return player_list;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}

	@Override
	public void update_record(int player_id, Player player) {
		 IJDBC_Connection jdbc = new JDBC_Connection();
		 String player_update_query = "UPDATE Player SET player_name = ?, player_email = ?, player_password = ?," +
                 " acc_created_date = ? WHERE player_id = ?";
		 try {
			 Connection conn = jdbc.createDBConnection();
			 PreparedStatement preparedStatement = conn.prepareStatement(player_update_query);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                	 preparedStatement.setInt(1, player.getPlayer_id());
	                     preparedStatement.setString(2, player.getPlayer_name());
	                     preparedStatement.setString(3, player.getPlayer_email());
	                     preparedStatement.setString(4, player.getPlayer_password());
	                     preparedStatement.setString(5, player.getAcc_created_date());
		             preparedStatement.executeUpdate();
	                }}
				} catch (SQLException sqlException) {
			        throw new RuntimeException(sqlException);
			    }
			
	}

	@Override
	public Player filter_by_id(int player_id) {
		IJDBC_Connection jdbc = new JDBC_Connection();
		String filter_by_id_query = "SELECT * from Player where player_id = ?";
		 try{
			 Connection conn = jdbc.createDBConnection();
	         PreparedStatement preparedStatement = conn.prepareStatement(filter_by_id_query);
	         preparedStatement.setInt(1, player_id);
	         try (ResultSet resultSet = preparedStatement.executeQuery()) {
	        	 Player player = null;
	             	if (resultSet.next()) {
	             		player = new Player();
	             		player.setPlayer_name(resultSet.getString("player_name"));
	             		player.setPlayer_email(resultSet.getString("player_email"));
	             		player.setPlayer_password(resultSet.getString("player_password"));
	             		player.setAcc_created_date(resultSet.getString("acc_created_date"));
	                }
	                return player;
	            }
	        } catch (SQLException sqlException) {
	            throw new RuntimeException(sqlException);
	        }
	}


}
