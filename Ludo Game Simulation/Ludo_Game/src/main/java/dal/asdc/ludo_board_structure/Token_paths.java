package dal.asdc.ludo_board_structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Reshma Unnikrishnan**/

public class Token_paths {
	List<String> red_path_1 = Arrays.asList("{1,5}","{2,5}","{3,5}","{4,5}","{5,5}");
	List<String> red_path_2 = Arrays.asList("{6,4}","{6,3}","{6,2}","{6,1}","{6,0}","{7,0}","{8,0}");
	List<String> red_win_path = Arrays.asList("{1,7}","{2,7}","{3,7}","{4,7}","{5,7}");
	List<String> green_path_1 = Arrays.asList("{8,1}","{8,2}","{8,3}","{8,4}","{8,5}");
	List<String> green_path_2 = Arrays.asList("{9,6}","{10,6}","{11,6}","{12,6}","{13,6}","{14,6}","{14,7}","{14,8}");
	List<String> green_win_path = Arrays.asList("{7,1}","{7,2}","{7,3}","{7,4}","{7,5}");
	List<String> yellow_path_1 = Arrays.asList("{13,8}","{12,8}","{11,8}","{10,8}","{9,8}");
	List<String> yellow_path_2 = Arrays.asList("{8,9}","{8,10}","{8,11}","{8,12}","{8,13}","{8,14}","{7,14}","{6,14}");
	List<String> yellow_win_path =Arrays.asList("{13,7}","{12,7}","{11,7}","{10,7}","{9,7}");
	List<String> blue_path_1 = Arrays.asList("{6,13}","{6,12}","{6,11}","{6,10}","{6,9}");
	List<String> blue_path_2 = Arrays.asList("{5,8}","{4,8}","{3,8}","{2,8}","{1,8}","{0,8}","{0,7}","{0,6}");
	List<String> blue_win_path = Arrays.asList("{7,13}","{7,12}","{7,11}","{7,10}","{7,9}");
	
}
