import java.util.Scanner;

/********************************************
* AUTHOR: Ava Gamble
* COLLABORATORS: 
* LAST MODIFIED: May 18, 2026 
********************************************/

/********************************************
* Quipu
*********************************************
* PROGRAM DESCRIPTION:
* A user will enter a number between 0 and 999 and this program will display the number as a quipu.
*********************************************
* ALGORITHM:
* 1. Display intro welcoming message.
* 2. Loop user input interaction until they decide to stop (Replay loop).
* 3. Prompt user for a number between 0 and 999.
* 4. Nested validation loop: If out of bounds (< 0 or > 999), show error and prompt again.
* 5. Split input into hundreds, tens, and ones places using integer division and modulo.
* 6. Print structural headers and pass isolated digits into the custom Quipu drawing engine methods.
* 7. Prompt user to continue or terminate [Y/N].
*********************************************/

public class Main
{
  /***** CONSTANT SECTION *****/
  public static final int QUIPU_WIDTH = 30;

  public static void main(String[] args)
  {
    /***** DECLARATION SECTION *****/
    Scanner input;
    int userNumber;
    int hundreds, tens, ones;
    String choice;
    boolean keepPlaying;

    /***** INITIALIZATION SECTION *****/
    input = new Scanner(System.in);
    keepPlaying = true;
    
    /***** INTRO SECTION *****/
    System.out.println("Hello! This program turns any whole number between 0 and 999 into a digital Quipu.\n");

    /***** INPUT + PROCESSING + OUTPUT LOOP SECTION *****/
    while (keepPlaying)
    {
        // 1. Get and Validate User Input
        System.out.print("Please enter a number between 0 and 999: ");
        userNumber = input.nextInt();
        
        while (userNumber < 0 || userNumber > 999)
        {
            System.out.println("ERROR: please enter value between 0 - 999\n");
            System.out.print("Please enter a number between 0 and 999: ");
            userNumber = input.nextInt();
        }

        // 2. Math Processing: Split the digits (Step 3)
        hundreds = userNumber / 100;
        tens = (userNumber / 10) % 10;
        ones = userNumber % 10;

        System.out.println("Hundreds   = " + hundreds);
        System.out.println("Tens       = " + tens);
        System.out.println("Ones       = " + ones);
        System.out.println();

        // 3. Output Generation (Step 2 & 4)
        System.out.println("Your quipu:");
        printQuipuCords(hundreds, tens, ones);
        System.out.println();

        // 4. Replay Decision Prompt
        System.out.print("Would you like to make another quipu? [Y/N]: ");
        choice = input.next();
        System.out.println();
        
        if (choice.equalsIgnoreCase("N"))
        {
            keepPlaying = false;
        }
    }
    
    System.out.println("Goodbye!");
    input.close();
  }

  /***** STATIC METHODS *****/

  /**
   * Generates a structural set of vertical knot blocks matching input counts. (Step 1)
   * * @param knotCount the count of knots to render
   */
  public static void printKnots(int knotCount)
  {
      for (int i = 0; i < knotCount; i++)
      {
          UtilityBelt.printCentered(QUIPU_WIDTH, "*");
      }
  }

  /**
   * Orchestrates the construction layout of the cord design mapping out places. (Step 2)
   * * @param h hundreds digit value
   * @param t tens digit value
   * @param o ones digit value
   */
  public static void printQuipuCords(int h, int t, int o)
  {
      // Draw top header anchor bounds
      UtilityBelt.printCentered(QUIPU_WIDTH, "___");
      UtilityBelt.printCentered(QUIPU_WIDTH, "|");

      // Render hundreds tier 
      printKnots(h);
      UtilityBelt.printCentered(QUIPU_WIDTH, "|");

      // Render tens tier
      printKnots(t);
      UtilityBelt.printCentered(QUIPU_WIDTH, "|");

      // Render ones tier
      printKnots(o);
      UtilityBelt.printCentered(QUIPU_WIDTH, "|");

      // Draw bottom overline terminal marker
      UtilityBelt.printCentered(QUIPU_WIDTH, "\u203E");
  }
}

// ==========================================
// UTILITYBELT STANDARD HELPER TOOLKIT
// ==========================================
class UtilityBelt 
{
    /**
     * Center-aligns text inside a specified terminal layout space width block.
     */
    public static void printCentered(int width, String text) 
    {
        if (text.length() >= width) {
            System.out.println(text);
        } else {
            int padding = (width - text.length()) / 2;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < padding; i++) {
                sb.append(" ");
            }
            sb.append(text);
            System.out.println(sb.toString());
        }
    }
}