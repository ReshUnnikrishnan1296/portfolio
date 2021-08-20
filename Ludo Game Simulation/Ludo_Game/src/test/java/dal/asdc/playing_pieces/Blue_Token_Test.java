package dal.asdc.playing_pieces;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Blue_Token_Test {
	
	@Test
	void constructor_test() {
		Token token = new Blue_Token(0);
		int[][] position = token.get_coordinate_position();

		assertEquals("BLUE",token.get_token_colour());
		assertEquals(true,token.get_is_token_at_home());
		assertEquals(false,token.get_is_token_at_winning_box());
		assertEquals(0,token.get_token_number());
		assertEquals(2,position[0][0]);
		assertEquals(12,position[0][1]);
	}
	
	@Test
	void is_home_test() {
		Token token = new Blue_Token(0);
		assertEquals(true,token.is_home());
	}
	
	@Test
	void check_move_towards_winning_square_test_less_than_last_box() {
		Token token = new Blue_Token(0);
		int[][] token_path = token.get_token_path();
		int[][] token_position = {{token_path[(token_path.length - 3)][0],token_path[(token_path.length - 3)][1]}};
		token.set_coordinate_position(token_position);
		assertEquals(true,token.check_move_towards_winning_square(2));
	}
	
	@Test
	void check_move_towards_winning_square_test_grater_than_last_box() {
		Token token = new Blue_Token(0);
		int[][] token_path = token.get_token_path();
		int[][] token_position = {{token_path[(token_path.length - 3)][0],token_path[(token_path.length - 3)][1]}};
		token.set_coordinate_position(token_position);
		assertEquals(false,token.check_move_towards_winning_square(4));
	}
	
	@Test
	void is_at_winning_square_test() {
		Token token = new Blue_Token(0);
		int[][] winning_position = {{6,6}};
		token.set_coordinate_position(winning_position);
		assertEquals(true,token.is_at_winning_square());
	}
	
	@Test
	void set_at_home_test() {
		Token token = new Blue_Token(0);
		int[][] position = {{6,6}};
		token.set_coordinate_position(position);
		assertEquals(false,token.is_home());	
		token.set_at_home();
		assertEquals(true,token.is_home());	
	}

}
