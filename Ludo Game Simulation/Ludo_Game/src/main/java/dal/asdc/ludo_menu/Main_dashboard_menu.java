package dal.asdc.ludo_menu;

/**
 * @author Reshma Unnikrishnan**/

public class Main_dashboard_menu {
	
	private enum menu_options{
		LOGIN,
		REGISTER,
		EXIT
	}
	
	private enum redirect_class{
		Player_login,
		Player_register
	}
	
	public class Main_menu {
		public final menu_options menu;
		public redirect_class redir = null;
		
		public Main_menu(menu_options menu) {
			this.menu = menu;
		}
		
		public redirect_class get_menu_page() {
			switch(menu) 
			{
				case LOGIN:
					{
						return redirect_class.Player_login;
					}
				case REGISTER:
					{
						return redirect_class.Player_register;
					}
				default:
					{
						return redirect_class.Player_login;
					}
			}
			
		}
	
		public redirect_class get_login_page()
		{
			Main_menu mm = new Main_menu(menu_options.LOGIN);
			return mm.get_menu_page();
		}
		
		public redirect_class get_register_page()
		{
			Main_menu mm = new Main_menu(menu_options.REGISTER);
			return mm.get_menu_page();
		}

}
}
