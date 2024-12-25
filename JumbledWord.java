Package JumbledWord;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JumbledWord {
    public static void main(String[] args) throws FileNotFoundException {
        boolean runGame = true;
        boolean triedBefore = false;
        int chance, controller, i, j, k, currentScore, totalScore, index, a, length;
        String guess;
        char[] alphabeth = { 'A', 'B', 'C', '�', 'D', 'E', 'F', 'G', '�', 'H', '�', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                '�', 'P', 'R', 'S', '�', 'T', 'U', '�', 'V', 'Y', 'Z' };
        //System is designed to consonant with every input file. That's why I created an array that keeps alphabeth.
        Scanner input = new Scanner(System.in);
        try {
            File myObj = new File("input.txt");
            //the input file should be in the game files.
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                String[] shuffledLetter = data.split(":");
                a = shuffledLetter[0].length() + 1;
                String[] words = data.split(",");
                words[0] = words[0].substring(a);



                length = words.length;
                String[] controlArray = new String[length];
                for(i=0; i<length; i++)
                    controlArray[i]= "#";

                totalScore = 0;
                chance = 3;
                index = 0;

                System.out.println("Welcome to the game. You should guess " + words.length + " different words by using letters: " + shuffledLetter[0]);
                System.out.println("The score for a word is the sum of the points for the letters in the word, multiplied by the\n" +
                        "length of the word. Letters are scored incrementally: A is worth 1, B is worth 2, C is worth\n" +
                        "3, and so on.");
                //I gave 3 chance to make mistake, because I understood that way.
                while (runGame) {
                    currentScore = 0;
                    controller = 0;
                    System.out.print("Please, enter your guess. Remember you have only " + chance + " chance:  ");
                    guess = input.next();
                    guess = guess.toUpperCase();

                    for (j = 0; j < words.length; j++) {
                        if (guess.equals(words[j])) {
                            triedBefore = false;
                            for (k = 0; k < controlArray.length; k++) {
                                if (controlArray[k].equals(guess)) {
                                    triedBefore = true;
                                    break;
                                    //This loop checks have words tried before.
                                }
                            }
                        } else
                            controller++;

                    }
                    if (triedBefore && controller != words.length) {
                        System.out.println("The word has been tried before.");
                        chance--;

                    }
                    if (controller == words.length) {
                        System.out.println("No match.");
                        chance--;
                        //We check all of the possibility by using controller. We check all the words.
                    }
                    if (!triedBefore && controller != words.length) {
                        controlArray[index] = guess;
                        index++;
                        //This loop is changing controlArray. In the next step we will see its help.
                        //If words tried first time, words will be registered.
                        for (i = 0; i < guess.length(); i++) {
                            for (j = 0; j < alphabeth.length; j++) {
                                if (guess.charAt(i) == alphabeth[j])
                                    currentScore += j + 1;
                            }
                        }
                        currentScore *= guess.length();
                        //current score is keeping score of every single word.
                        System.out.println("The point you gained from the guess '" + guess + "' is " + currentScore);
                        totalScore += currentScore;
                        //total score is the score that we have when game is finished.
                    }
                    if (chance == 0) {
                        System.out.println("Game Over");
                        runGame = false;
                    } else if (index == words.length) {
                        System.out.println("Congrats, you got it.");
                        runGame = false;
                    }


                }
                System.out.println("Your total score is " + totalScore);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file not found. Please, verify the game files.");
        }
        }

}
