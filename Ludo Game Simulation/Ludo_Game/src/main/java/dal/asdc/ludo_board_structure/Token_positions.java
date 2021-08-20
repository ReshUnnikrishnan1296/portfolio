package dal.asdc.ludo_board_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Reshma Unnikrishnan**/

public class Token_positions {
	
	 static Map<String,String> two_player_positions_map = new HashMap<String,String>();
	 static Map<String,String> three_player_positions_map = new HashMap<String,String>();
	 static Map<String,String> four_player_positions_map = new HashMap<String,String>();
	
	 static Map<String,String> red_token_positions_map = new HashMap<String,String>() {{
		 put("{2,3}","R1");
		 put("{3,3}","R2");
		 put("{3,2}","R3");
		 put("{2,2}","R4");
		}};;
	 static Map<String,String> green_token_positions_map = new HashMap<String,String>() {{
		 put("{11,3}","G1");
		 put("{12,3}","G2");
		 put("{12,2}","G3");
		 put("{11,2}","G4");
	 }};
	 static Map<String,String> empty_green_token_positions_map = new HashMap<String,String>() {{
		 put("{11,3}","");
		 put("{12,3}","");
		 put("{12,2}","");
		 put("{11,2}","");
	 }};
	static Map<String,String> yellow_token_positions_map = new HashMap<String,String>() {{
		 put("{11,12}","Y1");
		 put("{11,11}","Y2");
		 put("{12,11}","Y3");
		 put("{12,12}","Y4");
	 }};
	static Map<String,String> blue_token_positions_map = new HashMap<String,String>() {{
		 put("{2,12}","B1");
		 put("{2,11}","B2");
		 put("{3,12}","B3");
		 put("{3,11}","B4");
	 }};
	static Map<String,String> empty_blue_token_positions_map = new HashMap<String,String>() {{
		 put("{2,12}","");
		 put("{2,11}","");
		 put("{3,12}","");
		 put("{3,11}","");
		 }};

	 public Map<String,String> get_red_token_positions() {
			return red_token_positions_map;
		}
	 
	 public Map<String,String> get_green_token_positions() {
			return green_token_positions_map;
		}
	 public Map<String,String> get_empty_green_token_positions() {
			return empty_green_token_positions_map;
		}
	 public Map<String,String> get_yellow_token_positions() {
			return yellow_token_positions_map;
		}
	 
	 public Map<String,String> get_blue_token_positions() {
			return blue_token_positions_map;
		}
	 public Map<String,String> get_empty_blue_token_positions() {
			return empty_blue_token_positions_map;
		}
	 

	 public Map<String,String> get_two_player_positions(){
		two_player_positions_map.putAll(red_token_positions_map);
		two_player_positions_map.putAll(yellow_token_positions_map);
		two_player_positions_map.putAll(empty_green_token_positions_map);
		two_player_positions_map.putAll(empty_blue_token_positions_map);
		return two_player_positions_map;
		 
	 }
	 
	 public Map<String,String> get_three_player_positions(){
		three_player_positions_map.putAll(red_token_positions_map);
		three_player_positions_map.putAll(yellow_token_positions_map);
		three_player_positions_map.putAll(blue_token_positions_map);
		three_player_positions_map.putAll(empty_green_token_positions_map);
		return three_player_positions_map;
	 }
	 
	 public Map<String,String> get_four_player_positions(){
		four_player_positions_map.putAll(red_token_positions_map);
		four_player_positions_map.putAll(yellow_token_positions_map);
		four_player_positions_map.putAll(blue_token_positions_map);
		four_player_positions_map.putAll(green_token_positions_map);
		return four_player_positions_map;
	 }
	 
	 public Map<String,String> get_board_attributes(int ply_num){
		 Map<String,String> board_attributes_map = new HashMap<String,String>();
		 if(ply_num == 2) {
			 board_attributes_map = get_two_player_positions();
		 }else if(ply_num == 3) {
			 board_attributes_map = get_three_player_positions();
		 }else if(ply_num == 4) {
			 board_attributes_map = get_four_player_positions();
		 }
		 return board_attributes_map;
	 }
}
