package dal.asdc.model;

import org.springframework.stereotype.Component;

/**
 * @author Reshma Unnikrishnan
 * */

@Component
public class Main_menu {
	public String sel_menu;
	 
	public String getsel_menu() {
		return sel_menu;
	}
 
	public void setsel_menu(String sel_menu) {
		this.sel_menu = sel_menu;
	}
}
