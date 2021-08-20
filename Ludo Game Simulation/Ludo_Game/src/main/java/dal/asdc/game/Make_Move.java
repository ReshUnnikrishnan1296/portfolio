package dal.asdc.game;

import java.util.ArrayList;
import java.util.List;

import dal.asdc.playing_pieces.Green_Token;
import dal.asdc.playing_pieces.Red_Token;
import dal.asdc.playing_pieces.Token;

public class Make_Move {
	
	Check_Move check_move = new Check_Move();
	List<Token> blue_tokens = new ArrayList();
	List<Token> red_tokens = new ArrayList();
	List<Token> green_tokens = new ArrayList();
	List<Token> yellow_tokens = new ArrayList();
	List<Token> all_tokens = new ArrayList();
	int total_player = 4;
	
	public void check_moving_path() {
		Token token = new Green_Token(0);
		if(check_move.check_is_token_movable(token, 6)){
			make_move(token,6);
		}else {
			//show error
			System.out.print("You can't play this token");
		}
	}
	
	public void make_move(Token selected_token, int dice_number) {
		int[][] token_path = selected_token.get_token_path();

		if(selected_token.is_home()) {
			if(dice_number==6) {
				int[][] new_position = {{token_path[0][0],token_path[0][1]}};
				selected_token.set_coordinate_position(new_position);
				selected_token.set_is_token_at_home(false);
				//this is list of tokens of current player
				red_tokens.set(selected_token.get_token_number(), selected_token);
				//set token_moved
			}else {
				//show some error
			}
		}else{
			int token_index_on_path = -1;
			int[][] token_position = selected_token.get_coordinate_position();
			
			token_index_on_path = get_token_path_index(token_position,token_path);
			
			for(int player_count = 0 ; player_count < total_player ; player_count++) {
				//Player p = total_players[player_count]
				for(int coloured_token_count = 0 ; coloured_token_count < 4 ; coloured_token_count++) {
					//improve this loops
					all_tokens.add(red_tokens.get(coloured_token_count));
				}
			}
			
			if(token_index_on_path+dice_number < token_path.length) {
				int[][] after_move_position = {{token_path[token_index_on_path+dice_number][0],token_path[token_index_on_path+dice_number][1]}};
				boolean is_defeat = false;
				for(int all_token_counter = 0 ; all_token_counter < all_tokens.size() ; all_token_counter++) {
					Token temp_all_token_holder = all_tokens.get(all_token_counter);
					int[][] temp_all_token_holder_position = temp_all_token_holder.get_coordinate_position();
					if(temp_all_token_holder_position[0][0] == after_move_position[0][0] &&
							temp_all_token_holder_position[0][1] == after_move_position[0][1]) {
						is_defeat = true;
					}
				}
				
				if(is_defeat) {
					//defeat
					selected_token.set_coordinate_position(after_move_position);
					selected_token.set_is_token_at_home(false);
					selected_token.set_is_token_at_winning_box(false);
					
					for(int all_token_counter = 0 ; all_token_counter < all_tokens.size() ; all_token_counter++) {
						Token temp_all_token_holder = all_tokens.get(all_token_counter);
						int[][] temp_all_token_holder_position = temp_all_token_holder.get_coordinate_position();
						if(temp_all_token_holder_position[0][0] == after_move_position[0][0] &&
								temp_all_token_holder_position[0][1] == after_move_position[0][1]) {
							temp_all_token_holder.set_at_home();
							red_tokens.set(temp_all_token_holder.get_token_number(), temp_all_token_holder);
						}
					}
					
				}else {
					//normal move
					selected_token.set_coordinate_position(after_move_position);
					selected_token.set_is_token_at_home(false);
					selected_token.set_is_token_at_winning_box(false);
					//this is list of tokens of current player
					red_tokens.set(selected_token.get_token_number(), selected_token);
				}
				
			}else if(token_index_on_path+dice_number == token_path.length) {
				//in winning box
				int[][] winning_zone_coordinates = selected_token.get_winning_square();
				int[][] new_position = {{winning_zone_coordinates[0][0],winning_zone_coordinates[0][1]}};
				selected_token.set_coordinate_position(new_position);
				selected_token.set_is_token_at_winning_box(true);
				//this is list of tokens of current player
				red_tokens.set(selected_token.get_token_number(), selected_token);
				//set token_moved
			}else {
				//show error
			}
			
		}
	}
	
	public int get_token_path_index(int[][] token_position, int[][] token_path) {
		int temp_path_index = 0;
		for(int path_index = 0; path_index < token_path.length ; path_index++) {
			if(token_path[path_index][0]==token_position[0][0] && token_path[path_index][1]==token_position[0][1]) {
				temp_path_index = path_index;
			}
		}
		return temp_path_index;
	}

}
