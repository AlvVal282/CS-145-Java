import java.awt.*;
import java.util.Random;
/**
*  @author Alvaro Valdez-Duran
*/
public class Lion extends Critter {
   /**
   * @see Var2 is used to randomly select a number between a selected range of numbers
   */
   Random Var2 = new Random();
   /**
   * @see animalColor stores the three colors used for lions 
   */
   private Color[] animalColor = {Color.RED,Color.GREEN,Color.BLUE};
   /**
   * @see lionColor used to contain what the color of the lion will be
   */
   private Color lionColor;
   /**
   * @see  times is used to count each step in the frame so the class can correctly procced with each method
   */
   private int times = 0;
   public Lion(){
      lionColor = animalColor[Var2.nextInt(3)];
   }
   /**
    * @param info stores the data of what is surrounding the critter, this information is used in the if statements to properly instruct the critter to move a certain direction
    * @return the return for this method is the action the critter will take that is corresponding with a if statement in the method
    */
   public Action getMove(CritterInfo info) {
      times++;
      if (info.getFront() == Neighbor.OTHER) {
         return Action.INFECT;
      } else if(info.getFront() == Neighbor.WALL||info.getRight() == Neighbor.WALL) {
         return Action.LEFT;
      } else if(info.getFront() == Neighbor.SAME){
         return Action.RIGHT;
      } else{
         return Action.HOP;
      }
   }
   /**
    * @return this method will randomly select a color from the animalColor list every three steps
    */
   public Color getColor() {
      if(times%3==0) lionColor = animalColor[Var2.nextInt(3)];
      return lionColor;
   }
   /**
    * @return this method will return lion as a string in the frame
    */
   public String toString() {
      return "L";
   }
}