package dal.asdc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dal.asdc.ludo_board_structure.Ludo_board_formation;
import dal.asdc.ludo_menu.Dashboard_menu;
import dal.asdc.model.Main_menu;
import dal.asdc.model.Dash_menu;
import dal.asdc.model.Game_menu;

@Controller
public class Menu_controller {
	
	Dashboard_menu dash_menu = new Dashboard_menu();
	Ludo_board_formation l_board = new Ludo_board_formation();
	
	@Autowired
	Main_menu m_menu;
	
	@Autowired
	Dash_menu ds_menu;
	
	@Autowired
	Game_menu gm_menu;
	
	
	@RequestMapping(value = "/")
	public String menu(Model model) {
		model.addAttribute("Main_menu", m_menu);
		return "Menu_display.jsp";
	}

	@RequestMapping(value="/d_menu", method=RequestMethod.POST)
	public String dash_menu(@RequestParam("sel_menu") String smenu, Model model) {
		model.addAttribute("Dash_menu", ds_menu);
		model.addAttribute("smenu", smenu);
		return "Dashboard_menu.jsp";
	}
	

	@RequestMapping(value="/dash_menu", method=RequestMethod.POST)
	public String decide_dash_menu(@RequestParam("dash_menu") String dmenu, Model model) {
		model.addAttribute("dash_menu", dmenu);
		model.addAttribute("Game_menu", gm_menu);
		String decide_dash = dash_menu.decide_dashboard_menu_page(Integer.parseInt(dmenu));
		return decide_dash;
	}
	
	@RequestMapping(value="/game_menu", method=RequestMethod.POST)
	public String decide_game_menu(@RequestParam("game_menu") int gmenu, Model model) {
		model.addAttribute("gamemenu", gmenu);
		l_board.add_game_type(gmenu);
		return "ludo_board.jsp";
	}
	
	
}
