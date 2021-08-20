package dal.asdc.playing_pieces;

public class Red_Token extends Token{
	
	static int[][] red_home = {{2,3},{3,3},{3,2},{2,2}};
	int[][] winning_square = {{6,6},{7,7},{8,8},{6,7},{7,6}};
	int[][] red_token_path = {{1,1},{2,2},{3,3},{4,4},{5,5},{6,6},{7,7},{8,8},{9,9}};

	public Red_Token(int numer_of_token) {
		super("RED",numer_of_token,red_home[numer_of_token][0],red_home[numer_of_token][1]);
	}
	
	public boolean is_home() {
		int[][] selected_token_postion = get_coordinate_position();
		for(int i=0;i<4;i++) {
			if((red_home[i][0]==selected_token_postion[0][0]) && (red_home[i][1]==selected_token_postion[0][1])) {
				return true;
			}
		}
		return false;
	}
	
	public boolean check_move_towards_winning_square(int dice_number) {
		int[][] selected_token_postion = get_coordinate_position();
		for(int i=0;i<red_token_path.length;i++) {
			if(red_token_path[i][0]==selected_token_postion[0][0] && red_token_path[i][1]==selected_token_postion[0][1]) {
				if((i+dice_number)>red_token_path.length) {
					return false;
				}else {
					return true;
				}
			}
		}
		return false;
	}

	public boolean is_at_winning_square() {

		int[][] selected_token_postion = get_coordinate_position();
		System.out.println(selected_token_postion[0][0]+" -*- "+selected_token_postion[0][1] );

		for(int i=0;i<5;i++) {
			System.out.println(winning_square[i][0]+" win "+winning_square[i][1] );

			if(winning_square[i][0]==selected_token_postion[0][0] && winning_square[i][1]==selected_token_postion[0][1]) {
				System.out.println("true" );
				return true;
			}
		}
		return false;
	}
	
	public int[][] get_token_path(){
		return red_token_path;
	}
	
	public int[][] get_winning_square(){
		return red_token_path;
	}
	
	public void set_at_home() {
		int token_number = get_token_number();
		int[][] perfect_home_cordinates = {{red_home[token_number][0],red_home[token_number][1]}};
		set_coordinate_position(perfect_home_cordinates);
		set_is_token_at_home(true);
	}
	
}
