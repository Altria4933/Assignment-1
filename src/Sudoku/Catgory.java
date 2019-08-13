package Sudoku;

import java.util.ArrayList;

/**
 * This class is designed to classify the different levels of sudoku questions
 * it include the following functions:
 * 1. read information from text file, via FileIo class
 * 2. sort different questions into different ArrayList allow user to choose
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public class Catgory 
{
    ArrayList<SudokuList> all = new ArrayList<SudokuList>();
    ArrayList<SudokuList> hard = new ArrayList<SudokuList>();
    ArrayList<SudokuList> medium = new ArrayList<SudokuList>();
    ArrayList<SudokuList> easy = new ArrayList<SudokuList>();
    ArrayList<SudokuList> undefined = new ArrayList<SudokuList>();
    
    /**
     * a default constructors which read data from text file via FileIo class
     * initial the 'all' arraylist to fill the data from text file
     * stater sorting process, to separate question into their own arraylist, it measure by the question level.
     */
    public Catgory()
    {
        FileIO io = new FileIO();
        this.all = io.readGameData();
        
        process();
    }
    
    /**
     * sorting the question to their own arraylist, those question are measured by a enum label
     */
    public void process()
    {
        for(int i = 0; i<all.size(); i++)
        {
            Level level = all.get(i).getLevel();
            
            if(level == Level.HARD)
            {
                hard.add(all.get(i));
            }
            else if (level == Level.MEDIUM)
            {
                medium.add(all.get(i));
            }
            else if (level == Level.EASY)
            {
                easy.add(all.get(i));
            }
            else if (level == Level.UNDEFINED)
            {
                undefined.add(all.get(i));
            }
        }
        
    }
    
    /**
     * getter method for hard question, it return several sudoku question in a list
     * @return an arraylist which type is sudokulist.
     */
    public ArrayList<SudokuList> getHardQuestion()
    {
        return this.hard;
    }
    /**
     * getter method for medium question, it return several sudoku question in a list
     * @return an arraylist which type is sudokulist.
     */
    public ArrayList<SudokuList> getMediumQuestion()
    {
        return this.medium;
    }
    /**
     * getter method for easy question, it return several sudoku question in a list
     * @return an arraylist which type is sudokulist.
     */
    public ArrayList<SudokuList> getEasyQuestion()
    {
        return this.easy;
    }
    /**
     * getter method for undefined question, it return several sudoku question in a list
     * @return an arraylist which type is sudokulist.
     */
    public ArrayList<SudokuList> getUndefinedQuestion()
    {
        return this.undefined;
    }
    
    /**
     * Testing component
     */
//    public static void main(String[] args)
//    {
//        Catgory c = new Catgory();
//        System.out.println(c.getHardQuestion().size());
//    }
}
