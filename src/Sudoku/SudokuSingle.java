package Sudoku;

/**
 * This is the class which defines a single sudoku number, a single sudoku number contains
 * an number which indicates the value of this position, an boolean which state the writability
 * of the sudoku number, and a indicator show the different status of this number.
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public class SudokuSingle 
{
    private int number;
    private boolean writable;
    private ValueIndicator status;
    
    /**
     * This is a default method which designed to creart a new sudoku single number.
     */
    public SudokuSingle()
    {
        this.number = 0;
        this.writable = true;
        this.status = ValueIndicator.QUESTIONS;
    }
    
    /**
     * This method is designed to return the indicator of the single sudoku number.
     * @return an enum of value indicator which show the state of the number.
     */
    public ValueIndicator getIndicator()
    {
        return this.status;
    }
    /**
     * The method is designed to set the enum indicator to the single sudoku number.
     * @param indicator which is indicator for the single sudoku item.
     */
    public void setIndicator(ValueIndicator indicator)
    {
        this.status = indicator; 
    }
    /**
     * This method is designed to get the value of the single sudoku number.
     * @return an integer which state the value of that single sudoku numbers.
     */
    public int getNumber()
    {
        return this.number;
    }
    /**
     * This method id designed to set the value of the single sudoku number.
     * @param number an integer which state the number of that single sudoku number.
     */
    public void setNumber(int number)
    {
        this.number = number;
    }
    /**
     * This method is designed to get the value of writable information of that single sudoku number.
     * @return an boolean which indicates the writability of the single sudoku number.
     */
    public boolean getWritable()
    {
        return this.writable;
    }
    /**
     * This method is designed to set the value of writable information of that single sudoku number.
     * @param writable an boolean which indicates the writability of the single sudoku number.
     */
    public void setWritable(boolean writable)
    {
        this.writable = writable;
    }
}
