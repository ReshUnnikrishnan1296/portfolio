package dal.asdc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dal.asdc.model.Player;

/**
 * @author Reshma Unnikrishnan
 * */

@Controller
public class Player_controller {
	@Autowired
//	private PlayerDao plyr_dao;
	
//	@PostMapping("/bookTickets")
//	public String bookTicket(@RequestBody List<Player> player) {
//		plyr_dao.save(player);
//		return "ticket booked : " + player.size();
//	}

//	@GetMapping(value="/get_players")
//	public List<Player> getPlayers() {
//		return (List<Player>) plyr_dao.findAll();
//	}
	
	@RequestMapping(value="player")
	public String getPlayersTest() {
		return "ludo_board.jsp";
	}
}
