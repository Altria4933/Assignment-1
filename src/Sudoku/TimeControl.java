package Sudoku;

import java.util.Calendar;

/**
 * This method is designed to maintain the time information for the whole game
 * it contains severals method which controls it.
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public class TimeControl 
{
    private long start;
    private long end;
    private long total;
    private Calendar calender;
    
    /**
     * This method is a default constructor.
     */
    public TimeControl()
    {
        calender = Calendar.getInstance();
    }
    /**
     * This is method which can return a time usage for this single game of user
     * @return a string which describe the time usage.
     */
    public String getSingleTime()
    {
        long singleTime = this.end - this.start;
        return timeConvetor(singleTime);
    }
    
    /**
     * This is the method which can convert millisecond to the hour, mins, and second
     * and then return as a single string.
     * @param input a long types of variable of millisecond.
     * @return an string which contains the hour, time and min information.
     */
    public String timeConvetor(long input)
    {
        calender.setTimeInMillis(input);
        
        int hours = calender.get(Calendar.HOUR);
        int mins = calender.get(Calendar.MINUTE);
        int second = calender.get(Calendar.SECOND);
        
        return hours + " Hour " + mins +" Minutes " + second +" Seconds";
    }
    
    /**
     * This is the method which designed to set the start time in millisecond.
     */
    public void setStart()
    {
        this.start = System.currentTimeMillis();
    }
    /**
     * This is the method which design to set the end time in millisecond.
     */
    public void setEnd()
    {
        this.end = System.currentTimeMillis();
    }
    
    /**
     * This is the method which design to get the Time intervals from start and end in
     * millisecond.
     * @return an long which is the millisecond 
     */
    public long getTimeIntervalMiliSec()
    {
        return this.end - this.start;
    }
   
}
