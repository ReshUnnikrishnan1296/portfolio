package dal.asdc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Board_controller {

	@RequestMapping("home")
	public String home() {
		return "ludo_board.jsp";
	}
	
	@RequestMapping("/start_game")
	public String display_board() {
		return "ludo_board.jsp";
	}
	
}
