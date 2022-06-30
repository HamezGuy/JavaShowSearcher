import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

//used this site https://stackoverflow.com/questions/1647907/junit-how-to-simulate-system-in-testing
//also used this https://www.tutorialspoint.com/remove-newline-space-and-tab-characters-from-a-string-in-java

//This tests the functionality of the front end classes 
public class FrontendDeveloperTests
{

    //Initalizes systemIn and printStream to the relevent system processes
    private static final InputStream systemIn = System.in;
    private static final PrintStream systemOut = System.out;

    //Data storage of input and output as ByteStreams 
    private static ByteArrayInputStream testInput;
    private static ByteArrayOutputStream testOutput;

    //this method sets up everything so output prints into the bitStream testOutput
    public static void setUpOutput() {
        //Personal note, can also use this for JSON saving
        testOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOutput));
    }

    /**
     * this method simulates user input through code (stdin)
     * params
     * String testString, which is the string to be inputted
     */
    public static void setUpInput(String testString)
    {
        testInput = new ByteArrayInputStream(testString.getBytes());
        System.setIn(testInput);
    }

    /**
     * Restores streams to their proper output/input channels in System
     */
    public static void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    
    //tests if the correct items are printed at the beginning of the program
    //remember testOutput and testInput are what are supposed to be tested
    public static boolean test1() { 
        
        setUpOutput(); //sets up the output to be the ByteStream

        //Instance of the test class
        IShowSearcherFrontendClass testingClass = new IShowSearcherFrontendClass();
        testingClass.displayCommandMenu();

        //sets up the strings to be tested and output, trims them
        String testString1 = testOutput.toString().trim().replaceAll("[\\n\\t\\r ]", "");
        String testString2 = "Welcome to the Show Searcher App!" +
        "\n=================================" +
        "\nCommand Menu:" +
        "\t1) Search by [T]itle Word" +
        "\t2) Search by [Y]ear First Produced" +
        "\t3) [F]ilter by Streaming Provider" +
        "\t4) [Q]uit" + 
        "Choose a command from the menu above: ";

        testString2 = testString2.trim().replaceAll("[\\n\\t\\r ]", "");

        //boolean true if equal, false if not
        return testStringEquality(testString1, testString2);

    }

    
    /**
     * helper function to test String equality, tests if 2 strings equal
     * @param string1
     * @param string2
     * @return type boolean
     */
    private static boolean testStringEquality(String string1, String string2)
    {
        if(string1.equals(string2))
        {
            restoreSystemInputOutput();
            //TODO testing only remove when done

            return true;
        }
        else{
            restoreSystemInputOutput();
            //TODO testing only remove when done
            System.out.println("string one " + string1);
            System.out.println("string two " + string2);
            return false;
        }
    }
    


    /**
     * This test tests for if StreamingServices is displaying correctly
     * @return
     */
    public static boolean test2() { 

        setUpOutput(); //sets up output

        //sets up the test Instance, calling displayStream
        IShowSearcherFrontendClass testingClass = new IShowSearcherFrontendClass();
        testingClass.displayStreamingServices();

        //sets up both strings and tests if equal. 
        String string1 = testOutput.toString();
        String string2 = "Providers that shows are being searched for:" + 
        //TODO change this to x later, right now it should return false as there is no backend connection
        "\n\t1) _" + "_" + "_ [N]eflix" +
        "\n\t2) _"+ "_" + "_ [H]ulu" + 
        "\n\t3) _"+ "_" + "_ [P]rime Video" +
        "\n\t4) _"+ "_" + "_ [D]isney+" +
        "\n\t5) [Q]uit toggling provider filters" +
        "\nChoose the provider that you'd like to toggle the filter for: ";

        string1 = string1.trim().replaceAll("[\\n\\t\\r ]", "");
        string2 = string2.trim().replaceAll("[\\n\\t \\r]", "");

        //returns boolean true if strings are equal, else false
        return testStringEquality(string1, string2);
    }


    /**
     * tests how yearSearch Handles failure
     * @return
     */
    public static boolean test3() { 
        //sets up a string w/ instructions so user can input values to test
        System.out.print("Testing only, Choose a year to search for, testing enter a number between 1500-2022: ");

        //sets up output stream
        setUpOutput();

        //sets up instancte of test class
        IShowSearcherFrontendClass testingClass = new IShowSearcherFrontendClass();
        testingClass.yearSearch();

        //sets up strings and output to compare, trims and removes unnessecary factors
        String testString1 = testOutput.toString().trim().replaceAll("[\\n\\t\\r ]", "");
        String testString2 = "Choose the year that you would like to search for: Found 0 Matches.";

        testString2 = testString2.trim().replaceAll("[\\n\\t\\r ]", "");

        //returns boolean
        return testStringEquality(testString1, testString2);
    }

    /**
     * Tests titlesearch method
     * @return
     */
    public static boolean test4() { 

        //asks user for input for testing, really can't do it manually
        System.out.print("Testing only, Choose a word to search for, please enter a number: ");

        //instance of class to tests
        IShowSearcherFrontendClass testingClass = new IShowSearcherFrontendClass();

        //sets up output
        setUpOutput();

        //calls method
        testingClass.titleSearch();
        
        //README setUpInput("please work"); //I think due to the other class being run, I can't test this...

        //strings to test to each toher
        String testString1 = testOutput.toString().trim().replaceAll("[\\n\\t\\r ]", "");
        String testString2 = "Choose a word that you would like to search for: Found 0 matches.";

        testString2 = testString2.trim().replaceAll("[\\n\\t\\r ]", "");

        return testStringEquality(testString1, testString2);

    }

    /**
     * Tests yearsearch method
     * @return
     */
    public static boolean test5() { 

        //testing for user to put in values specified
        System.out.print("Testing only, Choose a year to search for enter a string: ");

        //sets up output
        setUpOutput();

        //new instance of class to test
        IShowSearcherFrontendClass testingClass = new IShowSearcherFrontendClass();
        testingClass.yearSearch();

        String testString1 = testOutput.toString().trim().replaceAll("[\\n\\t\\r ]", "");
        String testString2 = "Choose the year that you would like to search for:" + 
        "Value is not a year within the valid range , going back to Menu";

        testString2 = testString2.trim().replaceAll("[\\n\\t\\r ]", "");

        //boolean true if strings match
        return testStringEquality(testString1, testString2);
    }

    //main to test functions
    public static void main(String[] args)
    {
        //tests
        System.out.println(test1());
        System.out.println(test2());
        System.out.println(test3());
        System.out.println(test4());
        System.out.println(test5());

    }
    
}
