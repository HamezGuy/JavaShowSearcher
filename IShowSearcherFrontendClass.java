import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            IShowSearcherFrontendClass.java
// Files:            IShowSearcherFrontend.java
// Semester:         (course) CS400 Spring 2022
//
// Author:           James Gui
// Email:            jgui2@wisc.edu
// CS Login:         gui
// Lecturer's Name:  Professor Florian
// Lab Section:      Blue Team _ Team DA
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     N/A (team members not included)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          N/A
//
// Online sources:   N/A
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * this class is linked with the backend developer class
 * This class displays a list of options for the user to interact with such as:
 * Allowing the user to search through database for movies with a certain title
 * Allowing user to search for movies with a certain year
 * Filtering above searching by streaming services that the user can choose to-
 * add or remove from the search list
 *
 * <p>Bugs: will not be able to take null arguments from user
 * Possible issues if backend class fails, no error checking for that 
 *
 * @author James Gui
 */
public class IShowSearcherFrontendClass implements IShowSearcherFrontend
{
    
    /* README, NOTE ONLY, what Ishow contains
        String title; // retrieve the title of this show object
        int year; // retrieve the year that this show was first produced
        int rating; // retrieve the Rotten Tomatoes Rating (out of 100)
        boolean isAvailable; // checks show availability 
    */


    Scanner classScanner = new Scanner(System.in);
    public static boolean canLoop = true;
    
    ShowSearcherBackend backEndClass = new ShowSearcherBackend();

    /**
     * This method drives the entire read, eval, print loop (repl) for the
     * Song Search App.  This loop will continue to run until the user 
     * explicitly enters the quit command. 
     */
    @Override
    public void runCommandLoop() { 

        String userInput;

        canLoop = true;
        while(canLoop)
        {
            this.displayCommandMenu();

            userInput = classScanner.nextLine();

            userInput = userInput.toLowerCase();
            //TODO check for non alphabeticals, should i throw error or continue with parsed file?

            switch(userInput)
            {
                case "1":
                case "t":
                    titleSearch();
                break;

                case "2":
                case "y":
                    yearSearch();
                break;

                case "3":
                case "f":
                    displayStreamingServices();
                break;

                case "4":
                case "q":
                    System.out.print("Exiting program");
                    System.exit(0);
                break;

                default:
                    System.out.println("Incorrect input, please put in 1-4, or t, y, f, or q: ");
                    System.out.println("Going back to main menu: ");
                break;
            }
        }
        
    }

    /**
     * This clears the values to ensure that there will be no issues during startup
     */
    private void setUpDataClear()
    {
        backEndClass.setProviderFilter("Netflix", true);
        backEndClass.setProviderFilter("Hulu", true);
        backEndClass.setProviderFilter("Prime Video", true);
        backEndClass.setProviderFilter("Disney+", true);
    }


    // to help make it easier to test the functionality of this program, 
    // the following helper methods will help support runCommandLoop():
    @Override
    public void displayCommandMenu() {

        //TODO future, figure out how to have System.in read multiple line input
        //Readme, if i want, i can change out \r\n with just the Environment.newline, its \n for linux which i'll use here
        System.out.print("Welcome to the Show Searcher App!" + 
        "\n=================================" +
        "\nCommand Menu:" +
        "\n\t1) Search by [T]itle Word" +
        "\n\t2) Search by [Y]ear First Produced" +
        "\n\t3) [F]ilter by Streaming Provider" +
        "\n\t4) [Q]uit" + 
        "\nChoose a command from the menu above: ");
        
    }

    /**
     * Displays streaming services and allows user to toggle the values of each streaming service
     * on or off, will have a x if service is on, _ if service is off
     * void, return null
     */
    public void displayStreamingServices(){

        System.out.println("Providers that shows are being searched for:" + 
        "\n\t1) _" + helperMethodProviderSearch("Netflix") + "_ [N]eflix" +
        "\n\t2) _"+ helperMethodProviderSearch("Hulu") + "_ [H]ulu" + 
        "\n\t3) _"+ helperMethodProviderSearch("Prime Video") + "_ [P]rime Video" +
        "\n\t4) _"+ helperMethodProviderSearch("Disney+") + "_ [D]isney+" +
        "\n\t5) [Q]uit toggling provider filters" +
        "\nChoose the provider that you'd like to toggle the filter for: ");

        String userInput = classScanner.nextLine();

        userInput = userInput.toLowerCase();

        
        switch(userInput)
            {
                case "1":
                case "n":
                    System.out.println("Neflix choosen");
                    backEndClass.toggleProviderFilter("Netflix");
                break;

                case "2":
                case "h":
                    System.out.println("Hulu choosen");
                    backEndClass.toggleProviderFilter("Hulu");
                break;

                case "3":
                case "p":
                    System.out.println("Prime Video choosen");
                    backEndClass.toggleProviderFilter("Prime Video");
                break;

                case "4":
                case "d":
                    System.out.println("Disney+ choosen");
                    backEndClass.toggleProviderFilter("Disney+");
                break;

                case "5":
                case "q":
                    System.out.print("Exiting program");
                    System.exit(0);
                break;

                default:
                    System.out.println("Incorrect input, please put in 1-5, or n, h, p, d, or q: ");
                    System.out.println("Going back to main menu: ");
                break;
            }

    }

    /**
     * 
     * @param providerName
     * @return
     */
    public char helperMethodProviderSearch(String providerName)
    {
        boolean isSearchable = false;
        isSearchable = backEndClass.getProviderFilter(providerName);
        //if backendclass.issearchable.name.equals(providerName);
        if(isSearchable)
        {
            return 'x';
        }
        else{
            return '_';
        }
        
    }

    // displays a list of shows
    @Override
    public void displayShows(List<IShow> shows) {

        System.out.println("Found " + backEndClass.getNumberOfShows() + " matches.");

        int tempInt = 1;
        for(IShow show : shows)
        {
            //TODO figure out how to get the provider, likely have to parse through and check each provider, is this a backend
            //or frontend job? 
            System.out.println(tempInt + ". " + show.getTitle() +
            "\n\t" + show.getRating() + " " + show.getYear() + " " + show.isAvailableOn("xxx"));

            tempInt ++ ;
        }
    }

    
    /**
     * reads word from System.in, displays results
     */
    @Override
    public void titleSearch() {
        String tempString;
        System.out.print("Choose a word that you would like to search for: ");
        
        tempString = classScanner.nextLine();
        List<IShow> showInformation = new ArrayList<IShow>();
        showInformation = backEndClass.searchByTitleWord(tempString);

        //parse through shows and adds to list of shows
        displayShows(showInformation);
        return;
    }

    /**
     * Reads year from user input, displays movies based on the year
     */
    @Override
    public void yearSearch() {
        int year = -100;

        System.out.print("Choose the year that you would like to search for: ");

        //while loop is only for future, if i want to keep at same location
        canLoop = true;
        while(canLoop)
        {
            if(classScanner.hasNextInt())
            {
                year = classScanner.nextInt();
                classScanner.nextLine();
                if(year >= 1800 && year <= 2022)
                {
                    break;
                }

                System.out.println("Value is not a year within the valid range, going back to Menu");
                return;
            }
            else{
                System.out.println("Value is not a year within the valid range, going back to Menu");
                return;
            }
        }

        List<IShow> showInformation = new ArrayList<IShow>();
        showInformation = backEndClass.searchByYear(year);
        
        displayShows(showInformation);
        return;
    }

    /**
     * Main class, runs the program
     * @param args 
     */
    public static void main(String[] args)
    {
        IShowSearcherFrontendClass test = new IShowSearcherFrontendClass();
        test.setUpDataClear();
        test.runCommandLoop();
    }

}
