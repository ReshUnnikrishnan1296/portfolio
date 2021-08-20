package dal.asdc.ludo_menu;

import java.util.Map;
import static java.util.Map.entry;

/**
 * @author Reshma Unnikrishnan**/

public class Dashboard_menu {

	final Map<Integer, String> dashboard_menu_map = Map.ofEntries(
		    entry(1, "Game_menu.jsp"),
		    entry(2, "Tournament_menu.jsp"),
		    entry(3, "Scoreboard_menu.jsp"),
		    entry(4, "Menu_display.jsp")
		);
	
	final Map<Integer, String> game_menu_map = Map.ofEntries(
		    entry(1, "Two_player_game.jsp"),
		    entry(2, "Three_player_game.jsp"),
		    entry(3, "Four_player.jsp"),
		    entry(4, "Computer_vs_player.jsp")
		);
	
	final Map<Integer, String> tournament_menu_map = Map.ofEntries(
		    entry(1, "Start_tournament.jsp"),
		    entry(2, "Join_tournament.jsp")
		);
	
	public String decide_dashboard_menu_page(int choice) {
		String display_choice = null;
		for(Integer i : dashboard_menu_map.keySet()) {
			if(choice == i) {
				display_choice=dashboard_menu_map.get(i);
			}
		}
		return display_choice;
	}
	
	public String decide_game_menu_page(int choice) {
		String display_choice = null;
		for(Integer i : game_menu_map.keySet()) {
			if(choice == i) {
				display_choice=game_menu_map.get(i);
			}
		}
		return display_choice;
	}
	
	public String decide_tournament_menu_page(int choice) {
		String display_choice = null;
		for(Integer i : tournament_menu_map.keySet()) {
			if(choice == i) {
				display_choice=tournament_menu_map.get(i);
			}
		}
		return display_choice;
	}

}
