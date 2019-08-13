package Sudoku;

/**
 * this class is designed to draw the shape of the rectangular table of sudoku question,
 * it will also show the color of relevant position, like
 * show red color when there is input mistakes
 * show grey color when the number is unwritable(question)
 * show no color means that is the user's input.
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public class Display 
{
    public SudokuList list = new SudokuList();
    
    /**
     * This update class which can allow another class to provide latest list of
     * questions and update the current list, this list will be shortly being using
     * during the appearance method.
     * @param questions a sudoku table which include questions and user input(81 position).
     */
    public void update(SudokuList questions)
    {
        this.list = questions;
    }
    
    /**
     * This method is used to draw the table and the number.
     */
    public void appearance()
    {
        // RED COLOR System.out.println("\033[31;41mTEST\033[0m");
        // GREY COLOR System.out.println("\033[37;47mTEST\033[0m");
        
        //Numetric line     
        System.out.println("---------------------------------------------------------------");
        for(int number = 0; number < 3 ; number ++)
        {
            System.out.printf("      %d     %d     %d",number*3 + 1,number*3 + 2,number*3 + 3);
        }
        System.out.print("\n");
        
        for(int i = 0; i < 19; i++)
        {
            //Upper and Lower between Boundaries.
            if(i % 6 == 0 || i == 18)
            {
                System.out.print("  +");
                for(int index =0; index < 3; index++)
                {
                    System.out.print("+=====+=====+=====+");
                }
                System.out.println("+");
            }
            else
            {
                if(i % 2 != 0)
                {
                    System.out.printf("%c",'A' + i/2);
                    System.out.print(" |");
                    for(int index = 0; index < 9; index++)
                    {
                        String numberString;
                        int number = list.getList()[i/2][index].getNumber();
                        ValueIndicator indicator = list.getList()[i/2][index].getIndicator();
                        if(number != 0 && indicator == ValueIndicator.QUESTIONS)
                        {
                            //Grey Color -- Default Question Value
                            System.out.printf("| \033[37;47m %s \033[0m ", number);
                        }
                        else if (number != 0 && indicator == ValueIndicator.USER_INPUT)
                        {
                            System.out.printf("|  %s  ", number);
                        }
                        else if (number != 0 && indicator == ValueIndicator.VALUE_DUPLICATION)
                        {
                            //Red Color -- indicator of wrong input
                            System.out.printf("| \033[31;41m %s \033[0m ", number); 
                        }
                        else
                        {
                            System.out.printf("|  %s  ", " ");
                        }
                        if(index == 2 || index == 5 || index == 8)
                        {
                            System.out.printf("|");
                        }
                    }
                    System.out.println("|");
                }
                else
                {
                    System.out.print("  +");
                    for(int index =0; index < 3; index++)
                    {
                        System.out.print("+-----+-----+-----+");
                    }
                    System.out.println("+");
                }
            }
        }
        System.out.println("---------------------------------------------------------------");
    }
    
    /**
     * Test Component
     */
//    public static void main(String[] args)
//    {
//        Display test_component = new Display();
//        test_component.appearance();
//    }
}
