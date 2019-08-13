package Sudoku;

/**
 * This method is used to providing the help document, when user type help
 * it will display the help information.
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public class Help 
{
    /**
     * An enum which store different help code, different code is used in different part.
     */
    public enum helpEnum{
        CHOSEN_LEVEL, ENTER_NAME, CLEAR,CHANGE_LEVEL, SAVE_PROCESS, GAME_IN_PROGRESS;
    }
    
    /**
     * an method which designed to print out the help information.
     * @param help an enum which indicates different help code.
     */
    public void getHelpInfo(helpEnum help)
    {
        switch(help)
        {
            case CLEAR:
            {
                System.out.println("\nYou can remove everything you've input in this question so far");
                System.out.println("'YES' to comfirm, 'NO' to go back, by doing so, all data will be cleared");
                System.out.println("and the time counter will not be cleared \n");
                break;
            }
            case CHOSEN_LEVEL:
            {
                System.out.println("\nYou can choose level from Simple, Medium and Hard");
                System.out.println("Each level will be awared with different marks");
                System.out.println("Simple - 40, Medium - 80, Hard - 100");
                System.out.println("The mark will decreased 1 point each 8 seconds\n");
                break;
            }
            case CHANGE_LEVEL:
            {
                System.out.println("\nBy doing this, you can re-select your question or level");
                System.out.println("This action will also discard everthing you've done so far");
                System.out.println("And reset the timer for you.\n");
                break;
            }
            case SAVE_PROCESS:
            {
                System.out.println("\nIn the save process, you can choose either to save your ");
                System.out.println("Progress or not, input 'YES' to save the marks, time usage ");
                System.out.println("and name to it.\n");
                break;
            }
            case ENTER_NAME:
            {
                System.out.println("\nYou can enter your name, the system will verify name");
                System.out.println("If the name is exist, you can chose to continue or change name");
                System.out.println("There is no limitations to the name type, it can be anything");
                System.out.println("However, you cannot chose 'help' as your name\n");
                break;
            }
            case GAME_IN_PROGRESS:
            {
                System.out.println("\nYou can do following things");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("CODE      | DESCRIPTIONS");
                System.out.println("-----------------------------------------------------------------------");
                System.out.println("CHANGE    | Change Level/Question (Will discard current progress)");
                System.out.println("HELP      | Show the help information.");
                System.out.println("EXIT      | Exit game (Will discard current progress)");
                System.out.println("CHECK     | Check the correntness of input value");
                System.out.println("SET       | Set Position Number, SET A1 1 means set the value of A1 postion to 1");
                System.out.println("REMOVE    | Remove the filled value, REMOVE A1 means remove value on A1 position");
                System.out.println("CLEAR     | Clean all the value you have input, it will not discard the game");
                System.out.println("TIME      | See the time spent, also include the past user time spent information");
                System.out.println("RANK      | See ranking information, it will display information of all users(sort by marks)");
                System.out.println("ASSIST    | Assist Mode, it will check correctness of value inputed, it will check");
                System.out.println("          | every single time when you input value, it can turned off by 'ASSIST OFF'");
                System.out.println("          | command, you will need to manually check correctness by enter 'CHECK'.");
                System.out.println("          | By default, assist mode is 'ON'");
                System.out.println("-----------------------------------------------------------------------\n");
                break;
            }
                    
        }
    }
    

}
