import java.awt.*;
/**
*  @author Alvaro Valdez-Duran
*/
public class Giant extends Critter {
    /**
    * @see times is used to count each step in the frame so the class can correctly procced with each method
    */
    private int times = 0;
    /**
    * @see giantMessage stores the four strings used for Giant 
    */
    private Color giantColor;
    /**
    * @see giantMessage list is used to store the string used in toString()
    */
    private String[] giantMessage = {"fee", "fie", "foe", "fum"};
    /**
    * @see gntMessage stores the string used to show either of the 4 strings in the giantMessage list
    */
    private String gntMessage;
    public Giant(){
      giantColor = Color.GRAY;
    }
    /**
    * @param info stores the data of what is surrounding the critter, this information is used in the if statements to properly instruct the critter to move a certain direction
    * @return the return for this method is the action the critter will take that is corresponding with a if statement in the method
    */
    public Action getMove(CritterInfo info) {
        times++;
        if (info.getFront() == Neighbor.OTHER) {
            return Action.INFECT;
        } else if(info.getRight()==Neighbor.EMPTY) {
            return Action.HOP;
        }  else{
            return Action.RIGHT;
        }
    }
    /**
    * @return getColor() returns the color of the giant
    */
    public Color getColor() {
        return Color.GRAY;
    }
    /**
    * @return toString() returns the string format of Giant and changes string every 4 steps grabbing the string from the giantMessage list
    */
    public String toString() {
      if(times%6 == 0) gntMessage = giantMessage[times/6%4];
      return gntMessage;
    }
}