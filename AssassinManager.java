import java.util.*;
/**
* @author Alvaro Valdez-Duran
*/
public class AssassinManager{
   /**
   * @see killRing is a AssassinNode that stores every name in order and helps identify killers and the victims
   */
   AssassinNode killRing;
   /**
   * @see graveyard stores all the victims that have been killed
   */
   AssassinNode graveyard;
   /**
   * @param names is a list of strings that contains the names that were in a file. it is also used to enter each name in the list into the killRing AssassinNode
   */
   // AssassinManager() is a method that stores strings from a list into a AssassinNode in order as seen in the files. The method also checks if the String list is empty and will throw an IllegalArgumentException if it is
   public AssassinManager(List<String> names){
      if(names.isEmpty()) throw new IllegalArgumentException();
      for(int i = names.size()-1; i>=0;i--){
         String name = names.get(i);
         killRing = new AssassinNode(name,killRing);
      }
   }
   // printKillRing() is a method that prints all names in the killRing AssassinNode and shows who is stalking who until it reaches the end of the AssassinNode
   public void printKillRing(){
      AssassinNode current = killRing;
      while(current!=null){
         if(current.next==null) System.out.println(" "+ current.name+" is stalking "+ killRing.name);
         else{
            AssassinNode current2 = current.next;
            System.out.println(" "+current.name+" is stalking "+current2.name);
         }
         current = current.next;
      }  
   }
   // printGraveYard() is a method that prints all names in the graveyard AssassinNode and shows is killed who until reaches the end of its listNode
   public void printGraveyard(){
      AssassinNode current = graveyard;
      while(current!=null){
         System.out.println(" "+current.name+" was killed by "+ current.killer);
         current = current.next;
      }
   }
   /**
   * @return the method returns true or false depending on whether or not the inputted name is in the killRing AssassinNode
   * @param name is used to check if it is in the killRing AssassinNode
   */
   public boolean killRingContains(String name){
      AssassinNode current = killRing;
      while(current!=null){
         if(current.name.equalsIgnoreCase(name)) return true;
         current = current.next;
      }
      return false;
   }
  /**
  * @return the method returns true or false depending on whether or not the inputted name is in the graveyard AssassinNode
  * @param name is used to check if it is in the graveyard AssassinNode
  */
   public boolean graveyardContains(String name){
      AssassinNode current = graveyard;
      while(current!=null){
         if(current.name.equalsIgnoreCase(name)) return true;
         current = current.next;
      }
      return false;
   }
   /**
   * @return the method returns true or false depending if the killRing AssassinNode contains on one name
   */
   public boolean gameOver(){
      if(killRing.next==null) return true;
      return false;
   }
   // winner() is a method that uses the gameOver() method to check if there is only one name left, if so it will return that name. If the gameOver() method returns false the winner() will return null
   public String winner(){
      if(gameOver()) return killRing.name;
      return null;
   }
   /**
   * @param name is used to take it from the killRing AssassinNode and place it in the graveyard ListNode and also tag the name before it as its killer. The name also checks if it is in the killRing AssassinNode, if not it will throw a IllegalArgumentEcxeption.
   */
   // kill() uses three AssassinNode reference to appropriately put everything in its place. It also makes sure that the killRing AssassinNode loops the list so that the bottom name is the killer of the top name if the top name is the inputted value
   // the method also places the victim name in the graveyard and updates the killRing AssassinNode so that the victim name's stalker is now stalking who they were stalking previously
   public void kill(String name){
      if(!killRingContains(name)) throw new IllegalArgumentException();
         AssassinNode current = killRing;
         AssassinNode current2 = null;
      while (!current.name.equalsIgnoreCase(name) && current != null){
         current2 = current;
         current = current.next;
      }
      if(current2 == null){
         AssassinNode current3 = killRing;
         killRing = killRing.next;
         while(current3.next!=null){
            current3 = current3.next;
         }
         current2=current3;
      }
      else current2.next = current.next;
      current.next = graveyard;
      graveyard = current;
      current.killer = current2.name;
   }      
}