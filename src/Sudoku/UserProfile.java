package Sudoku;

import java.util.Comparator;

/**
 * This class is charged for managing the user profile and information.
 * Those information contains the user name, score and the total time spend(in millisecond)
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public class UserProfile 
{
    private String userName;
    private int userScore;
    private long timeSpent;
    
    /**
     * This is the default constructor which can creats an empty user.
     */
    public UserProfile()
    {
        this.setUserName("Anonymous");
        this.setUserScore(0);
        this.setTimeSpent(0);
    }
    /**
     * This is the constructor which can set user's name, score and the total time spent
     * for the whole game.
     * @param userName a string which indicates the user name.
     * @param userScore a integer number which indicates the user scores.
     * @param timeSpent a long which keep the total user using time in millisecond.
     */
    public UserProfile(String userName, int userScore, long timeSpent)
    {
        this.setUserName(userName);
        this.setUserScore(userScore);
        this.setTimeSpent(timeSpent);
    }
    
    /**
     * This is the method which can get the total time that user have spend(in millisecond)
     * @return a long type of number which state the time spent.
     */
    public long getTimeSpent()
    {
        return this.timeSpent;
    }
    /**
     * This is the method which can set the total time spent that user have done so fa
     * @param timeSpent an long type of data which user have spent.
     */
    public void setTimeSpent(long timeSpent)
    {
        this.timeSpent = timeSpent;
    }
    /**
     * This is the method which can get the user name.
     * @return an string which indicates the user name.
     */
    public String getUserName()
    {
        return this.userName;
    }
    
    /**
     * This is the method which can set the user name.
     * @param userName an String which indicates the name of the user.
     */
    public void setUserName(String userName)
    {
        this.userName = userName;
    }
    /**
     * This method is designed to get the user score.
     * @return an integer which states the total score of user.
     */
    public int getUserScore()
    {
        return this.userScore;
    }
    /**
     * This method is design to set the user score.
     * @param userScore an integer which states the total score of user.
     */
    public void setUserScore(int userScore)
    {
        this.userScore = userScore;
    }
}

/**
 * This class is used to compare two different users score.
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
class sortByScore implements Comparator<UserProfile>
{
    public int compare(UserProfile a, UserProfile b)
    {
        return b.getUserScore() - a.getUserScore();
    }
}
