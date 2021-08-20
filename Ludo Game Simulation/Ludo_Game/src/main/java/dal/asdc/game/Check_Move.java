package dal.asdc.game;

import dal.asdc.playing_pieces.Token;

public class Check_Move {
		
	public boolean check_is_token_movable(Token selected_token,int dice_number) {
		if(selected_token.is_home() && dice_number<6) {
			return false;
		}
		/*if(selected_token.check_move_towards_winning_square(dice_number)) {
			return false;
		}*/
		
		if(selected_token.is_at_winning_square()) {
			return false;
		}
		return true;
	}
}




/*public boolean is_token_at_home(int[][] position,String token_colour) {
Token_positions token_positions = new Token_positions();
token_positions.get_red_token_position()
if(token_colour.equals(Colour.RED.toString())) {
	for(int i=0;i<4;i++) {
		if(red_home[i][0]==position[0][0] && red_home[i][1]==position[0][1]) {
			return true;
		}
	}
}else if(token_colour.equals(Colour.GREEN.toString())) {
	for(int i=0;i<4;i++) {
		if(green_home[i][0]==position[0][0] && green_home[i][1]==position[0][1]) {
			return true;
		}
	}
}else if(token_colour.equals(Colour.YELLOW.toString())) {
	for(int i=0;i<4;i++) {
		if(yellow_home[i][0]==position[0][0] && yellow_home[i][1]==position[0][1]) {
			return true;
		}
	}
}else if(token_colour.equals(Colour.BLUE.toString())) {
	for(int i=0;i<4;i++) {
		if(blue_home[i][0]==position[0][0] && blue_home[i][1]==position[0][1]) {
			return true;
		}
	}
}
return false;
}*/

