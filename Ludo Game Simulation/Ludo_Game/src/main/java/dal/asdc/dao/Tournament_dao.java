package dal.asdc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.asdc.model.Player;
import dal.asdc.model.Tournaments;

/**
 * @author Reshma Unnikrishnan**/

public class Tournament_dao implements ITournament_dao{

	public void execute_statement() {
		IJDBC_Connection jdbc = new JDBC_Connection();
		try {
		Connection conn = jdbc.createDBConnection();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void create_record(Tournaments tournaments) {
		JDBC_Connection jdbc = new JDBC_Connection();
		String create_query = "INSERT into Tournaments (tournament_id, tournament_time, num_of_participants, tournament_winner_id, tournament_runner_id, tournament_status) "
				+ "values(?,?,?,?,?,?)";
		try {
			Connection conn = jdbc.createDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(create_query);
			preparedStatement.setInt(1, tournaments.getTournament_id());
            preparedStatement.setString(2, tournaments.getTournament_time());
            preparedStatement.setInt(3, tournaments.getNum_of_participants());
            preparedStatement.setInt(4, tournaments.getTournament_winner_id());
            preparedStatement.setInt(5, tournaments.getTournament_runner_id());
            preparedStatement.setString(6, tournaments.getTournament_status());
            preparedStatement.executeUpdate();
			}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Tournaments> select_all_record() {
        List<Tournaments> tournament_list = new ArrayList<>();
        IJDBC_Connection jdbc = new JDBC_Connection();
        String select_all_query = "";
        try {
			Connection conn = jdbc.createDBConnection();
			PreparedStatement preparedStatement = conn.prepareStatement(select_all_query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                	Tournaments tournaments = new Tournaments();
                	tournaments.setTournament_id(resultSet.getInt("tournament_id"));
                	tournaments.setTournament_time(resultSet.getString("tournament_time"));
                	tournaments.setNum_of_participants(resultSet.getInt("num_of_participants"));
                	tournaments.setTournament_winner_id(resultSet.getInt("tournament_winner_id"));
                	tournaments.setTournament_runner_id(resultSet.getInt("tournament_runner_id"));
                	tournaments.setTournament_status(resultSet.getString("tournament_status"));
                	tournament_list.add(tournaments);
                }
                return tournament_list;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	@Override
	public void update_record(Integer tournament_id, Tournaments tournaments) {
		 IJDBC_Connection jdbc = new JDBC_Connection();
		 String tournament_update_query = "UPDATE Tournaments SET tournament_winner_id = ?, tournament_runner_id = ?, tournament_status = ? WHERE tournament_id = ?";
		 try {
			 Connection conn = jdbc.createDBConnection();
			 PreparedStatement preparedStatement = conn.prepareStatement(tournament_update_query);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while (resultSet.next()) {
	                	 preparedStatement.setInt(1, tournaments.getTournament_winner_id());
	                     preparedStatement.setInt(2, tournaments.getTournament_runner_id());
	                     preparedStatement.setString(3, tournaments.getTournament_status());
	                     preparedStatement.setInt(4, tournaments.getTournament_id());
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
