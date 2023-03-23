import java.awt.*;
/**
*  @author Alvaro Valdez-Duran
*/
public class Bear extends Critter {
    /**
    *  @see bearColor used to contain what the color of the Bear will be
    */
    private Color bearColor;
    /**
    *  @see times is used to count each step in the frame so the class can correctly procced with each method
    */
    private int times = 0;
    /**
    * @param polar is a boolean data field that alternates between true and false to then pick the color of the Bear
    */
    public Bear (boolean polar){
      if(polar) bearColor = Color.WHITE;
      else bearColor = Color.BLACK;
    }
    /**
    * @param info stores the data of what is surrounding the critter, this information is used in the if statements to properly instruct the critter to move a certain direction
    * @return the return for this method is the action the critter will take that is corresponding with a if statement in the method
    */
    public Action getMove(CritterInfo info) {
        times++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if(info.getFront() == Neighbor.EMPTY){
            return Action.HOP;
        } else{
            return Action.LEFT;
        }
    }
    /**
    * @return this method will return the color of the bear depending on what polar is
    */
    public Color getColor() {
        return bearColor;
    }
    /**
    * @return this method will return how the bear will look in the frame 
    */
    public String toString() {
        if(times%2==0) return "\\";
        else return "/";
    }
}