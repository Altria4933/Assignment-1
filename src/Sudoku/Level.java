package Sudoku;

/**
 * This is a class which defines question level, all the sudoku question in the list
 * were having a level, it will be used to identify the level of question.
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public enum Level 
{
    /**
     * it defines four different types of level
     * the undefined is used when crates new sudoku questions
     * it contains a description of the class, which can be print out.
     */
    UNDEFINED("Undefined"),EASY("Easy"),MEDIUM("Medium"),HARD("Hard");
    
    private String describe;
    
    /**
     * A default constructor which set the description to the enum value
     * @param describe an string description of enum
     */
    private Level(String describe)
    {
        this.describe = describe;
    }
    /**
     * a getter method which return the description of the value back
     * @return an string contain the description of file.
     */
    public String getDescribe()
    {
        return this.describe;
    }
}
