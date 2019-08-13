package Sudoku;

/**
 * This class is designed to keep the sudoku question, each question was made by 81 single number
 * it also contain a level which indicates the level of question.
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public class SudokuList {
    private SudokuSingle[][] list;
    private Level level;
    
    /**
     * an default constructor which crate a 2d-array which has 81 different single sudoku numbers
     * it also indicates the level to undefined when creating the sudoku list.
     */
    public SudokuList() {
        this.setList(new SudokuSingle[9][9]);
        this.setDefaultValue();
        this.setLevel(level.UNDEFINED);
    }
//    public SudokuList(Level level) {
//        this.setList(new SudokuSingle[9][9]);
//        this.setLevel(level);
//    }
    /**
     * a method which set the default value to each single sudoku number.
     */
    public void setDefaultValue() {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                list[x][y] = new SudokuSingle();
                list[x][y].setWritable(true);
            }
        }
    }
    
    /**
     * a method which set the value to a single sudoku number.
     * @param PositionX the front value of an 2-d array 
     * @param PositionY the rear value of an 2-d array 
     * @param number the value of this position
     * @param writable an indicator which indicates if the number is writable or not
     * @param indicator an indicator which show the status of suduku number.
     */
    public void setSingleValue(int PositionX, int PositionY, int number, boolean writable, ValueIndicator indicator)
    {
        list[PositionX][PositionY].setNumber(number);
        list[PositionX][PositionY].setWritable(writable);
        list[PositionX][PositionY].setIndicator(indicator);
    }
    
    /**
     * This method is specially designed for write in tools.
     * @return a string which contains the level description of a question.
     */
    @Override
    public String toString()
    {
        return this.level.getDescribe();
    }
    
    /**
     * A getter method which return the sudoku list.
     * @return a 2-d array of sudokusingle(group of single sudoku numbers)
     */
    public SudokuSingle[][] getList()
    {
        return this.list;
    }
    /**
     * A setter method which set the sudoku list.
     * @param  a 2-d array of sudokusingle(group of single sudoku numbers)
     */
    public void setList(SudokuSingle[][] list)
    {
        this.list = list;
    }
    /**
     * A getter method which return the level of sudoku list.
     * @return a level indicator which indicates the level of question
     */
    public Level getLevel()
    {
        return this.level;
    }
    /**
     * A setter method which set the level of sudoku list.
     * @param level a level indicator which indicates the level of question
     */
    public void setLevel(Level level)
    {
        this.level = level;
    }
}
