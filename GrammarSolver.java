import java.util.*;
/**
* @author Alvaro Valdez-Duran
*/
public class GrammarSolver{
   Random rand = new Random();
   /**
   * @see map is used to store the symbol and it rules in a TreeMap
   */
   private TreeMap<String, String[]> map = new TreeMap<>();
   /**
   * @param list is used to each line of code from a file passed on through GrammarMain to GrammarSOlver so it can properly sort thorugh all symbols and their keys
   */
   // GrammarSolver() is used to appropriately split each line in "list" so that all symbols are stored in a map as keys and their rules as values
   // This method also checks if the list inputted has two line with the same symbol and will throw an IllegalArgumentException, lastly this mehtod also trims all the symbols and their rules
   public GrammarSolver(List<String> list){
      if(list==null||list.size()==0) throw new IllegalArgumentException();
      for(String line:list){
         String[] symbolrules = line.split("::=");
         String symbol = symbolrules[0].trim();
         String[] rules = symbolrules[1].split("[|]");
         if(map.containsKey(symbol)) throw new IllegalArgumentException();
         int i = 0;
         for(String currentRule:rules){
            String temp = currentRule;
            temp = temp.trim();
            rules[i]=temp;
            i++;
         }
         map.put(symbol,rules);
      }
   }
   /**
   * @return contains returns true or false depending on if the give parameter is in the TreeMap "map"
   * @param symbol is the parameter inputted that is checked if it is in the TreeMap "map"
   */
   // this method returns a boolean and checks if the given parameter is null or empty, if so it will throw an IllegalArgumentException
   public boolean contains(String symbol){
      if(symbol==null||symbol.length()==0) throw new IllegalArgumentException();
      return map.containsKey(symbol);
   }
   /**
   * @return getSymbols returns a TreeSet of the symbols in the "map" TreeMap
   */
   public Set<String> getSymbols(){
      return map.keySet();
   }
   /**
   * @return generate returns the phrase, sentence, or word that correlates with inputted symbol
   * @param symbol is used part in creating the recursion algorithm
   */
   // This method uses recursion, the mthod continues to call upon itself if the given "symbol" is a key in the TreeMap "map"
   public String generate(String symbol){
      if(symbol == null|| symbol.length()==0) throw new IllegalArgumentException();
      if(!map.containsKey(symbol)) return symbol;
      
      String endSentence = "";
      String[] rules = map.get(symbol);
      int randNum = rand.nextInt(rules.length);
      String getRule = rules[randNum];
      String[] splitRules = getRule.split(" ");
      
      for(String rule: splitRules){
       if(map.containsKey(rule)) endSentence+=generate(rule);
       else if(rule=="") endSentence+="";
       else endSentence+=rule+" ";
      }
      
      return endSentence;
   }
}