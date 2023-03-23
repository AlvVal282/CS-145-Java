import java.util.*;
import java.io.*;
/**
* @author Alvaro Valdez-Duran
*/
public class QuestionTree{
   /**
   * @see overallRoot is a QuestionTreeNode object that stores the entirety of the questions and answers
   */
   private QuestionTreeNode overallRoot;
   /**
   * @see my is a UserInterface object that is used so that the QustionTree class can be used in different settings
   */
   private UserInterface my;
   /**
   * @see totalGames is used to store the amount of games played
   */
   int totalGames = 0;
   /**
   * @see gamesWon is an int object used to store the amount of games the questioner has won.
   */
   int gamesWon = 0;
   /**
   * @param ui is a UserInterface object that is passed down so that it can update the current UserInterface in the QuestionTree class
   */
   // QuestionTree is a contructor method that intializes "my" and the tree of overallRoot
   public QuestionTree (UserInterface ui){
      my = ui;
      overallRoot = new QuestionTreeNode("Is it an animal?");
      overallRoot.setYes(new QuestionTreeNode("bird"));
      overallRoot.setNo(new QuestionTreeNode("teacher"));
   }
   // play() is a method that when called upon starts the beginnig of the game and updates the QuestionTreeNode overallRoot when more questions and answers are added.
   // play() also calls upon itself in a private method 
   public void play(){
      overallRoot = play(overallRoot);
   }
   /**
   * @return play() returns a QuestionTreeNode that is used to update the current tree of overallRoot
   * @param root is a QuesitonTreeNode object that is used to ask questions and give potential answers
   */
   // play() is a method that is used to go through each question or answer from the passed down QuestionTreeNode object and uses recursion to traverse through each node
   // if the node passed down is a leaf node it will assume its a object and ask the user if it is the object they had in mind. If it wasn't it will add one to the totalGames and go into a differnt method
   // If the node was a question it will display the question and will traverse left or right depening on the users response 
   private QuestionTreeNode play(QuestionTreeNode root){
      if(root.leafNode()){
         my.print("Is your object a "+root.getData()+"?");
         if(yesNo(root.getData())){
            gamesWon++;
            totalGames++;
            my.println("I win!");
         } else {
            totalGames++;
            root = addQuestion(root);
         }
      } else {
         if(yesNo(root.getData())) root.setYes(play(root.getYes()));
         else root.setNo(play(root.getNo()));
      }
      return root;
   }
   /**
   * @return yesNo() returns true if the response given from the user is a yes
   * @param question is a String object that is printed out so that the user knows what is being asked of them or if the object is the one they had in mind
   */
   // yesNo() is a method that also uses a while loop if the given response is not a "yes" or a "no".
   private boolean yesNo(String question){
      if(question.contains("?")) my.print(question);
      String answer = my.nextLine().toLowerCase();
      while(!answer.contains("yes") && !answer.contains("no")){
         my.println("Please enter the correct response. Yes or No?");
         if(question.contains("?")) my.print(question);
         else my.print("Is your object a "+question+"?");
         answer = my.nextLine().toLowerCase();
      }
      return answer.contains("yes");
   }
   /**
   * @return addQuestion() is a method that returns a QuestionTreeNode node that is used to add more questions and answer to overallRoot so that it continually grows smarter and is more efficient at getting the right answer
   * @param root is a QuestionTreeNode object passed down so that it can be appropriately rearranged so that all the old and new answers are the leafnodes and the old and new questions are the parent node
   */
   // addQuestion() is a method that also prints out questions so that it can differiantiate the difference between the sibling nodes and correctly traverse through the tree
   private QuestionTreeNode addQuestion(QuestionTreeNode root){
      my.print("I lose. What is your object?");
      String object = my.nextLine().toLowerCase().trim();
      my.print("Type a yes/no question to distinguish your item from "+root.getData()+":");
      String question = my.nextLine().trim();
      QuestionTreeNode answer = new QuestionTreeNode(object);
      QuestionTreeNode question2 = new QuestionTreeNode(question);
      my.print("And what is the answer for you object?");
      if(my.nextLine().toLowerCase().contains("yes")){
         question2.setYes(answer);
         question2.setNo(root);
      } else {
         question2.setYes(root);
         question2.setNo(answer);
      }
      return question2;
   }
   /**
   * @param output is a PrintStream object that is used to store all the questions and answers of the overallRoot into a text file
   */
   public void save(PrintStream output){
      save(output, overallRoot);
   }
   /**
   * @param output is a PrintStream object that is used to store all the questions and answers of the overallRoot into a text file
   * @param root is the QuestionTreeNode overallRoot that is used to extract all the questions and answers from it so that they can be added into a text file in a sorted order
   */
   // save() is method that uses recursion when the given data of the root given is a question and will sort all the questions and asnwers in pre-order
   private void save(PrintStream output, QuestionTreeNode root){
      if(root.leafNode()) output.println("A:"+root.getData());
      else {
         output.println("Q:"+root.getData());
         save(output, root.getYes());
         save(output, root.getNo());
      }
   }
   /**
   * @param input is a Scanner object that is used to add previous questions and answers from other games in to overallRoot
   */
   //load() is also a method that calls upon another method so that it can appropriately sort all the information correctly
   public void load(Scanner input){
      overallRoot = setTree(input);        
   }
   /**
   * @return setTree(0 will return a QuestionTreeNode tree that is filled with all questions and answer from the file given in the correct order and manner
   * @param input is the same scanner object from the load() that is passed down from it
   */
   // setTree() is also a method that uses recursion to traverse through each question and it also uses the substring method so that the "Q:" and "A:" is not seen by the user and looks visually cleaner
   private QuestionTreeNode setTree(Scanner input){
      String QandA = input.nextLine();
      if(QandA.contains("Q:")){
         QuestionTreeNode yes = setTree(input);
         QuestionTreeNode no = setTree(input);
         if(yes.getData().contains("A:") || yes.getData().contains("Q:")) yes.setData(yes.getData().substring(2));
         if(no.getData().contains("A:") || no.getData().contains("Q:")) no.setData(no.getData().substring(2));
         return new QuestionTreeNode(QandA.substring(2), yes, no);
      } else return new QuestionTreeNode(QandA.substring(2));
   }
   /**
   * @return totalGames() returns the total amount of games played in the one session
   */
   public int totalGames(){
      return totalGames;
   }
   /**
   * @return gamesWon() returns the total amount of games won by the questioner in the one session
   */
   public int gamesWon(){
      return gamesWon;
   }
}