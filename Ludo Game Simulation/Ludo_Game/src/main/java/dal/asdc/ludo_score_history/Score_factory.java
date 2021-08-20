package dal.asdc.ludo_score_history;

/**
 * @author Reshma Unnikrishnan**/
public class Score_factory {
	
	public static IScore_history get_score_history(String score_type) {
		if(score_type.equalsIgnoreCase(null)) {
			return null;
		}
		else if(score_type.equalsIgnoreCase("Player")) {
			return new Player_score_history();
		} 
		else if(score_type.equalsIgnoreCase("Game")) {
			return new Game_score_history();
		} 
		else if(score_type.equalsIgnoreCase("Tournament")) {
			return new Tournaments_score_history();
		} 
		return null;
	}
}
