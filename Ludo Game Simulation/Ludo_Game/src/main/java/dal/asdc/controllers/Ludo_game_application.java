package dal.asdc.controllers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
"dal.asdc.model", "dal.asdc.controllers"})
public class Ludo_game_application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Ludo_game_application.class, args);
		//pl_his.get_all_player_history();
	}

}
