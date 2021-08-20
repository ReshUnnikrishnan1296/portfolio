package dal.asdc.player;
import java.util.*;

public class Player_intialiser {

    enum Colour {
        RED,
        GREEN,
        YELLOW,
        BLUE
    }


    private Map<String,Player> player_list= new HashMap<>();
    private Map<String,Player_command> input_commands= new HashMap<>();

    private Player red_player=player_list.get("RED");
    private Player blue_player=player_list.get("BLUE");
    private Player yellow_player=player_list.get("YELLOW");
    private Player green_player=player_list.get("GREEN");

    public void intialise(int number_of_players){
        for (int i = 0; i < number_of_players; i++) {
            player_list.put(String.valueOf(i),new Player());
        }
    }

    private void randomise(){
        List<String> colour_list = new ArrayList<String>();
        for(Colour colour: Colour.values()){
            colour_list.add(colour.toString());
        }

        Collections.shuffle(colour_list);

        for (int i=0;i<player_list.size();i++){
            player_list.get(String.valueOf(i)).setColour(colour_list.get(i));
        }

    }

    private void assign_colour_home_position(){

        input_commands.put("RED", new Red_token_command(red_player));
        input_commands.put("YELLOW", new Yellow_token_command(yellow_player));
        input_commands.put("BLUE", new Blue_token_command(blue_player));
        input_commands.put("GREEN", new Green_token_command(green_player));

        for (int i = 0; i < player_list.size(); i++) {
            String colour=player_list.get(String.valueOf(i)).getColour();
            Player_command player_command = input_commands.get(colour);
            player_command.execute();
        }
    }


}
