package dal.asdc.playing_pieces;

public abstract class Token {
    int[][] position;
    private boolean is_token_at_home=true;
    private boolean is_token_at_winning_box = false;
    private String token_colour;
    private int token_number;

    public Token(String token_colour, int token_number, int positionX, int positionY) {
        this.token_colour = token_colour;
        this.token_number = token_number;
        int positionX_temp = positionX;
        int positionY_temp = positionY;
        position = new int[][]{{positionX_temp,positionY_temp}};
    }

    public String get_token_colour() {
        return token_colour;
    }

    public int[][] get_coordinate_position() {
        return position;
    }

    public void set_coordinate_position(int[][] position_new) {
        System.out.println(position_new[0][0]+" --- "+position_new[0][1] );
        position = position_new;
    }

    public int get_token_number() {
        return token_number;
    }

    public boolean get_is_token_at_home() {
        return is_token_at_home;
    }

    public void set_is_token_at_home(boolean is_token_at_home) {
        this.is_token_at_home = is_token_at_home;
    }

    public boolean get_is_token_at_winning_box() {
        return is_token_at_winning_box;
    }

    public void set_is_token_at_winning_box(boolean is_token_at_winning_box) {
        this.is_token_at_winning_box = is_token_at_winning_box;
    }


    public abstract boolean is_home();
    public abstract boolean check_move_towards_winning_square(int dice_number);
    public abstract boolean is_at_winning_square();
    public abstract int[][] get_token_path();
    public abstract int[][] get_winning_square();
    public abstract void set_at_home();
}
