package dal.asdc.game;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Make_Move_Test {
	
	/*@Test
	void check_moving_path_test() {
		Make_Move make_move = new Make_Move();
		make_move.check_moving_path();
	}*/
	
	
	
	@Test
	void get_token_path_index_test() {
		Make_Move make_move = new Make_Move();
		int[][] token_position = {{3,4}};
		int[][] token_path = {{2,3},{3,4},{4,5}};
		
		int index = make_move.get_token_path_index(token_position, token_path);
		
		assertEquals(1,index);
	}
}
