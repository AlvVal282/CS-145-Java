// skeleton version of the class
import java.util.*;
/**
* @author Alvaro Valdez-Duran
*/
public class Guitar37 implements Guitar {	
   /**
   * @see KEYBOARD is field that stores all the keys on a keyboard that will be used to create a sound
   */
   public static final String KEYBOARD =  "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";  // keyboard layout 
   /**
   * @see strings is a GuitarString object that is used to store all values of frequency, of each key.
   */
   private GuitarString[] strings;
   /**
   * @see stringPluck is a TreeMap that is used to store each frequency to it corresponding key
   */
   private Map<Character, GuitarString[]> stringPluck = new TreeMap<>();
   /**
   * @see strings37 initlize the size of the field strings
   */
   private int strings37 = KEYBOARD.length();
   /**
   * @see tic is a int that stores how many times the tic() has been called
   */
   private int tic = 0;
   // Guitar37() is a contructor method, when called it initilaizes the size of strings and stores each frequency in strings to the corresponding key, in the TreeMap
   public Guitar37(){
      strings = new GuitarString[strings37];
      for(int i = 0; i<strings37;i++){
         strings[i] = new GuitarString(440. * Math.pow(2,(i-24.)/12.));
         stringPluck.put(KEYBOARD.charAt(i), strings);
      }
   }
   /**
   * @param pitch is a int that is used to correctly pluck the key that is wanted to be played
   */
   //playNote() is a method that makes sures that the pitch is less than string37, if it isn't there will be no key in that can be played,
   //and be greater than 0. If pitch meets the requirements than it will find the correct key to pluck and it will do so.
   public void playNote(int pitch){
      pitch+=24;
      if(pitch<strings37 && pitch>0){
         int i = 0;
         for(char key: stringPluck.keySet()){
            if(i==pitch) strings[i].pluck();
            i++;
         }
      }
   }
   /**
   * @param letter is a char that is used to check if it is a letter in the string KEYBOARD
   * @return hasString() will return a true or false depending on if the char inputted is also within the string KEYBOARD
   */
   public boolean hasString(char letter){
      for(int i=0; i<strings37; i++){
         if(KEYBOARD.charAt(i)==letter) return true;
      }
      return false;
   }
   /**
   * @param string is a char that is used to make sure that it is a letter within the string KEYBOARD and if so then it will 
   */
   public void pluck(char string){
      if(!hasString(string)) throw new IllegalArgumentException();
      strings[KEYBOARD.indexOf(string)].pluck();
   }
   /**
   * @return sample() returns a double that stores the sum of all 37 frequencies
   */
   public double sample() {
      double sumAll = 0.;
      for(GuitarString strings2: strings){
         sumAll+= strings2.sample();
      }
      return sumAll;
      
   }
   // tic() is a method that is used to dissapate the energy of each string plucked
   public void tic(){
      for(GuitarString strings2: strings){
         strings2.tic();
      }
      tic++;
   }
   /**
   * @return time() returns the amount of times that the tic() method has been called
   */
   public int time(){
      return tic;
   }
}