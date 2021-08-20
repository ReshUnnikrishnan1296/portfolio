package dal.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.asdc.model.Game;
import dal.asdc.model.Player;

/**
 * @author Reshma Unnikrishnan **/

public class Game_dao implements IGame_dao{
	
	public void execute_statement() {
		IJDBC_Connection jdbc = new JDBC_Connection();
		try {
		Connection conn = jdbc.createDBConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void create_record(Game game) {
		IJDBC_Connection jdbc = new JDBC_Connection();
		String create_query = "INSERT into Game (game_id, game_time, player_id, game_winner_id, game_runner_id, game_status) "
				+ "values(?,?,?,?,?)";
		try {
			Connection conn = jdbc.createDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(create_query);
			preparedStatement.setInt(1, game.getGame_id());
            preparedStatement.setString(2, game.getGame_time());
            preparedStatement.setInt(3, game.getPlayer_id());
            preparedStatement.setInt(4, game.getGame_winner_id());
            preparedStatement.setInt(5, game.getGame_runner_id());
            preparedStatement.setString(6, game.getGame_status());
            preparedStatement.executeUpdate();
			}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Game> select_all_record() {
	        List<Game> game_list = new ArrayList<>();
	        IJDBC_Connection jdbc = new JDBC_Connection();
	        String select_all_query = "SELECT * from Game";
	        try {
				Connection conn = jdbc.createDBConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(select_all_query);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                    Game game = new Game();
	                    game.setGame_id(resultSet.getInt("game_id"));
	                    game.setGame_time(resultSet.getString("game_time"));
	                    game.setPlayer_id(resultSet.getInt("player_id"));
	                    game.setGame_winner_id(resultSet.getInt("game_winner_id"));
	                    game.setGame_runner_id(resultSet.getInt("game_runner_id"));
	                    game.setGame_status(resultSet.getString("game_status"));
	                    game_list.add(game);
	                }
	                return game_list;
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	}

	@Override
	public void update_record(Integer game_id, Game game) {
		 JDBC_Connection jdbc = new JDBC_Connection();
		 String player_update_query = "UPDATE Game SET player_id = ?, game_winner_id = ?, game_runner_id = ?," +
                 " game_status = ? WHERE game_id = ?";
		 try {
			 Connection conn = jdbc.createDBConnection();
			 PreparedStatement preparedStatement = conn.prepareStatement(player_update_query);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                	 preparedStatement.setInt(1, game.getPlayer_id());
	                     preparedStatement.setInt(2, game.getGame_winner_id());
	                     preparedStatement.setInt(3, game.getGame_runner_id());
	                     preparedStatement.setString(4, game.getGame_status()); 
		             preparedStatement.executeUpdate();
	                }}
				} catch (SQLException sqlException) {
			        throw new RuntimeException(sqlException);
			    }
			
	}

	@Override
	public void filter_by_id() {
		// TODO Auto-generated method stub
		
	}
}
