package Sudoku;

import java.util.ArrayList;

/**
 *  This is the class which designed to maintain all the user informations.
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public class UserVerify 
{
    ArrayList<UserProfile> allUser;
    
    FileIO io;
    
    /**
     * This is the default constructor which can allow user to be read from file io class
     * and store it to the local arraylist.
     */
    public UserVerify()
    {
        io = new FileIO();
        allUser = io.readUserData();
    }
    /**
     * This is the method design to return all user informations.
     * @return an Array list of user information.
     */
    public ArrayList<UserProfile> getAllUser()
    {
        return this.allUser;
    }
    /**
     * This method is design to save all the user information to the text file via the file
     * io class.
     */
    public void saveAllUser()
    {
        io.writeUserData(allUser);
    }

    /**
     * This method is design to add a user to the local array list.
     * @param newUser an profile of user, it is a userprofile type.
     */
    public void addUser(UserProfile newUser)
    {
        allUser.add(newUser);
    }
    public void removeUser(UserProfile remove)
    {
        for(int i = 0; i < allUser.size(); i++)
        {
            if(allUser.get(i).equals(remove))
            {
                allUser.remove(i);
                break;
            }
        }
    }
    
    /**
     * This method is design to check the duplication of user,
     * if the user is not exist yet, it will return null back,
     * if there is user exist, it will return the corresponding user 
     * information back.
     * @param userName a string which is the new user's name, it been provide to check the duplicate.
     * @return an user profile information which contains the information of user.
     */
    public UserProfile isExistence(String userName)
    {
        int position = -1;
        for(int i = 0; i < allUser.size(); i++)
        {
            if(allUser.get(i).getUserName().trim().equalsIgnoreCase(userName))
            {
                position = i;
                break;
            }
        }
        
        if(position == -1)
        {
            return null;
        }
        else
        {
            return allUser.get(position);
        }
    }
}
