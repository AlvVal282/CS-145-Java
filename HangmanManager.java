import java.util.*;
/**
*  @author Alvaro Valdez-Duran
*/
public class HangmanManager{
   /**
   * @see pattern is a variable that stores the the pattern of word 
   */
   private String pattern;
   /**
   * @see patternMap is a Map that stores each word in the words list to its corresponding pattern
   */
   private Map<String,Set<String>> patternMap = new TreeMap<>();
   /**
   * @see guesses is a Set that stores all inputed guesses from the user
   */
   private Set<Character> guesses = new TreeSet<>();
   /**
   * @see words is a Set that stores words from the dictionary list
   */
   private Set<String> words = new TreeSet<>();
   /**
   * @see guessesLeft is a variable that stores the amount guesses left the user has
   */
   private int guessesLeft;
   /**
   * @see HangmanManager is a method that stores words of a specific length, entered in the game, from dicitionary into the word TreeSet. It also stores the max into guessesLeft and throws a IllegalArguementException if length and max are less than 1 since you can't play with zero guesses and there is no word with a length of 0
   * @param dictionary is a list of the words that will be used thorughout the game
   * @param length is a number of characters that will be used in the game
   * @param max is a number that is associated with amount of guesses the user has
   */
   public HangmanManager(List<String> dictionary, int length, int max){
      if(length<1||max<1) throw new IllegalArgumentException();
      for(String word: dictionary){
         if(word.length()==length) words.add(word);
      }
      guessesLeft = max;
   }
   /**
   * @return words returns the updated set of the words TreeSet
   */
   public Set<String> words(){
      return words;
   }
   /**
   * @return guessesLeft return the updated amount of guesses left
   */
   public int guessesLeft(){
      return guessesLeft;
   }
   /**
   * @return guesses returns a Set of all the characters the user guessed
   */
   public Set<Character> guesses(){
      return guesses;
   }
   /**
   * @see pattern uses a StringBuilder to be able to append to the string if guesses contains a character from the updated word TreeSet and uses a for loop to iterate through each character of the word
   * @return pattern returns the updated word pattern after the user inputed a guess and creates the pattern with only the characters that are in the guesses TreeSet
   */
   public String pattern(){
      StringBuilder pattern = new StringBuilder();
      String word = words.iterator().next();
      for (int i = 0; i < word.length(); i++){ 
         if(guesses.contains(word.charAt(i))) pattern.append(word.charAt(i)+" ");
         else pattern.append("- ");
      }
      return pattern.toString();
   }
   /**
   * @see record is the method that is used to call generate pattern so that it creates a pattern for each word that is in the words TreeSet and then adds the pattern to the patternMap TreeMap as the key and the word as the value, if patternMap does not contain pattern it will then put pattern as a key and then create a TreeSet from that pattern to then store different words that have the same pattern. From their it then goes through each value for the  pattern that is stored in the patternMap to then count the occurences of the character guess or any charcters stored in guesses that may be in values of the pattern. After it has gone through each value of the pattern it compares the size of the Set of pattern in patternMap to the variable size, if it is greater than size then size will now equal the size of the Set of pattern in patternMap and it will clear what was in patternMap2 so that it can store the pattern and its values in its set. While it is still in the if statement it will also check if occurences is greater than the occurences from before, if so then the occurences from before will now store the recent occurences. If the size of the Set of pattern in patternMap is equal to the variable size than it will store the pattern and its values in patternMap2 and if the occurences of the letter guess is lesser than the occurences of before than the uccurences from before will now store the recent occurences. After the method has gone through each word in the words TreeSet than it will go to the next piece of code which is to add the most recent guess to the guesses TreeSet, set patternMap equal to patternMap2 since the game will now reference the words that are in patternMap2, substract 1 from guessesLeft if the count equals zero and store all values of patterMap into words.  
   * @param guess is the variable that will be used to check and create the pattern for each word that is in the words TreeSet if word contains the guess character in its string
   * @return record returns the amount of occurences the guess character had in the words in the patternMap2 TreeMap
   */
   public int record(char guess){
      int count = 0,min = 0,size = 0;
      patternMap.clear();
      Map<String, Set<String>> patternMap2 = new TreeMap<>();
      for(String word: words){
         pattern = generatePattern(word,guess);
         if(!patternMap.containsKey(pattern)) patternMap.put(pattern, new TreeSet<String>());
         patternMap.get(pattern).add(word);
         for(String word2: patternMap.get(pattern)){
            for(int i = 0; i<word.length();i++){
               if(word.charAt(i) == guess) min++;
               else if (guesses.contains(guess)) min++;
            }
            if(patternMap.get(pattern).size()>size){
               if(min>count) count = min;
               size = patternMap.get(pattern).size();
               patternMap2.clear();
               patternMap2.put(pattern,patternMap.get(pattern));
               if(min>count) count = min;
            } else if(patternMap.get(pattern).size() == size){
               patternMap2.put(pattern,patternMap.get(pattern));
               if(min<count) count = min;
            }
            min = 0;
         }
      }  
      guesses.add(guess);
      patternMap=patternMap2;
      if(count==0) guessesLeft--;
      words = patternMap.values().iterator().next();
      return count;
   }
   /**
   * @see generatePattern uses a for loop to iterate through each character of the word and adds a character of the word to the pattern if the character chosen is the guess character or the the character of the word is in the guesses TreeSet
   * @param word is the varaible used to create a pattern for it if any of its characters is the guess varaible or its in the guesses TreeSet
   * @param guess is the variable that is used to see of the word variable has the guess character in its string
   * @return generatePattern returns the pattern for the word that is inputed in the parameters, it uses the latest guess of the user and all the guesses inputed before that are in the guesses TreeSet to create a pattern for the word inputed
   */
   private String generatePattern(String word, char guess){
      String pattern="";
      for(int i = 0; i<word.length();i++){
         if(word.charAt(i) == guess) pattern+=guess;
         else if (guesses.contains(word.charAt(i))) pattern+=word.charAt(i); 
         else pattern+= "-";
      }
      return pattern;
   }
}
