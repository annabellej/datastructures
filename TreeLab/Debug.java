import java.util.*;
/**
 * Provides some methods for ease of debugging.  Use locally in each project.  
 *
 * @author Marina Peregrino 
 * @version Jan 6, 2018 
 *          Uses new interface.  
 *          future expansion, develop general test suit, extend for each lab.  
 */
abstract class Debug
{
    //used to prompt for command line input
    static Scanner in = new Scanner(System.in);
    static boolean debug = true;    // use this to quickly disable all debug io.  

    /**
     * debug printout
     * postcondition: out is printed to System.out
     * @param out the string to send to System.out
     */
    static void debugPrint(String out)
    {
        if(debug) System.out.println("debug: " + out);
    }

    /**
     * If debug is active, displays given string and waits for user to enter text, 
     * otherwise does nothing.  
     * 
     * @param out the string to send to System.out
     */

    static void debugPause(String out)
    {
        if(debug) //System.out.println(out +"\ndebug Pause: press enter to continue");
            getUserInput(out +"\ndebug Pause: press enter to continue");
    }

    /**
     * If debug is active, waits for user to enter text, 
     * otherwise does nothing.  
     */
    static void debugPause()
    {
        if(debug) 
            getUserInput("debug Pause: press enter to continue");
    }

    /**
     * Prints a prompt, then waits for user to enter text.  
     * Called from debugPause
     * 
     * @param   out     the prompt that is printed before getting input 
     * @return the string entered by the user
     */
    static String getUserInput(String out)
    {
        System.out.println(out);
        return in.nextLine();
    }

    /**
     * Waits  for user to enter text.  
     * Called from debugPause
     * 
     * @return the string entered by the user
     */
    static String getUserInput()
    {
        return in.nextLine();
    }
}
