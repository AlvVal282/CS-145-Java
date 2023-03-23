import java.awt.*;
import java.util.Random;
/**
*  @author Alvaro Valdez-Duran
*/
public class Gator extends Critter {
   /**
   * @see gatorColor stores the color of what the gator will be
   */
   private Color gatorColor;
   /**
   *  @see Var2 is used to randomly select a number between a selected range of numbers
   */
   Random Var2 = new Random();
   /**
   * @see times is used to count each step in the frame so the class can correctly procced with each method
   */
   private int times = 0;
   /**
   * @see animalColor is a list that stores the two colors the gator will switch from
   */
   private Color[] animalColor = {Color.GREEN,Color.BLUE};
   /**
    * @param info stores the data of what is surrounding the critter, this information is used in the if statements to properly instruct the critter to move a certain direction
    * @return the return for this method is the action the critter will take that is corresponding with a if statement in the method
    */
   public Action getMove(CritterInfo info) {
      times++;
      if (info.getFront() == Neighbor.OTHER) return Action.INFECT;
      
      else if(info.getRight() == Neighbor.SAME) return Action.LEFT;
      
      else if(info.getLeft() == Neighbor.SAME) return Action.RIGHT;
      
      else if (info.getRight() == Neighbor.OTHER) return Action.RIGHT;
      
      else if (info.getLeft() == Neighbor.OTHER) return Action.LEFT;
      
      else return Action.HOP;
   }
   /**
    * @return will return what the color of the gator will be every 2 steps
    */
   public Color getColor() {
      if(times%2==0) gatorColor = animalColor[Var2.nextInt(2)];
      return gatorColor;
   }
   /**
    * @return will return what the string format of the gator
    */
   public String toString() {
      return "/*\\";
   }
}