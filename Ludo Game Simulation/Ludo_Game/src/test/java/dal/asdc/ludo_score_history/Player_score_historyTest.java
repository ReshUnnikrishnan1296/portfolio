package dal.asdc.ludo_score_history;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Player_score_historyTest {

	private Player_score_history player_score_his;
	
	@BeforeEach
	void initEach() {
		player_score_his = new Player_score_history();
		
	}
	
	@Test
	void testSave_history() {
		
	}

	@Test
	void testLoad_history() {
	}

	@Test
	void testForm_score_board() {
		player_score_his.load_history(1);
		player_score_his.form_score_board();
	}

	@Test
	void testDisplay_score_board() {
	}

	@Test
	void testForm_save_score() {
		player_score_his.form_save_score("2|1", 2, 1, 20);
		player_score_his.form_save_score("3|1", 3, 1, 50);
	}

}
