package dal.asdc.ludo_menu;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class Dashboard_menu_Test {
	
	private Dashboard_menu d_menu;
	
	@BeforeEach
	void initEach() {
		d_menu = new Dashboard_menu();
	}

	@Test
	void testDecide_dashboard_menu_page() {
		assertEquals("Scoreboard_menu.jsp",d_menu.decide_dashboard_menu_page(3));
	}

	@Test
	void testDecide_game_menu_page() {
		assertEquals("Three_player_game.jsp",d_menu.decide_game_menu_page(2));
	}

	@Test
	void testDecide_tournament_menu_page() {
		assertEquals("Start_tournament.jsp",d_menu.decide_tournament_menu_page(1));
	}

}
