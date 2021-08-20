package dal.asdc.ludo_board_structure;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Token_positionsTest {

	private Token_positions tp;
	
	@BeforeEach
	void initEach() {
		tp = new Token_positions();
	}
	
	@Test
	void testget_board_attributes() {
		assertEquals(4,tp.yellow_token_positions_map.size());
	}
	
	@Test
	void testget_two_player_positions() {
		tp.get_two_player_positions();
		assertEquals(16,tp.two_player_positions_map.size());
	}
	
	@Test
	void testget_three_player_positions() {
		tp.get_three_player_positions();
		assertEquals(16,tp.three_player_positions_map.size());
	}

	@Test
	void testget_four_player_positions() {
		tp.get_four_player_positions();
		assertEquals(16,tp.four_player_positions_map.size());
	}

	@Test
	void testget_red_token_positions() {
		Map<String,String> red_map = new HashMap<String,String>(){{put("{2,3}","R1");put("{3,3}","R2");put("{3,2}","R3");put("{2,2}","R4");}};
		assertEquals(red_map,tp.get_red_token_positions());
	}
	
	@Test
	void testget_yellow_token_positions() {
		Map<String,String> yellow_map = new HashMap<String,String>(){{ put("{11,12}","Y1");put("{11,11}","Y2");put("{12,11}","Y3");put("{12,12}","Y4");}};
		assertEquals(yellow_map,tp.get_yellow_token_positions());
	}
	
	@Test
	void testget_green_token_positions() {
		Map<String,String> green_map = new HashMap<String,String>(){{put("{11,3}","G1");put("{12,3}","G2");put("{12,2}","G3");put("{11,2}","G4");}};
		assertEquals(green_map,tp.get_green_token_positions());
	}
	
	@Test
	void testget_blue_token_positions() {
		Map<String,String> blue_map = new HashMap<String,String>(){{put("{2,12}","B1");put("{2,11}","B2");put("{3,12}","B3");put("{3,11}","B4");}};
		assertEquals(blue_map,tp.get_blue_token_positions());
	}
	
	@Test
	void testget_empty_green_token_positions() {
		Map<String,String> empty_green_map = new HashMap<String,String>(){{put("{11,3}","");put("{12,3}","");put("{12,2}","");put("{11,2}","");}};
		assertEquals(empty_green_map,tp.get_empty_green_token_positions());
	}
	
	@Test
	void testget_empty_blue_token_positions() {
		Map<String,String> empty_blue_map = new HashMap<String,String>(){{ put("{2,12}","");put("{2,11}","");put("{3,12}","");put("{3,11}","");}};
		assertEquals(empty_blue_map,tp.get_empty_blue_token_positions());
	}
	
}
