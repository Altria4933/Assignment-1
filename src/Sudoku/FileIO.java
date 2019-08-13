package Sudoku;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class is controlled all the functions of read / write function, this class
 * can read text file and convertor to the arraylist, in addition, it also write the
 * data from different class into the text file.
 * @author QIJIN CHEN(16939553), WANFANG ZHOU(16937889)
 */
public class FileIO 
{
    /**
     * this method can read the user information from text file
     * those information is shortly being converted and add to the arraylist and
     * return the value.
     * @return an arraylist which is user profile.
     */
    public ArrayList<UserProfile> readUserData()
    {
        ArrayList<UserProfile> userData = new ArrayList<UserProfile>();
        
        try
        {
            FileReader readUser = new FileReader("User.txt");
            BufferedReader bufferedReadUser = new BufferedReader(readUser);
            
            String line = null;
            while((line = bufferedReadUser.readLine()) != null)
            {
                String[] tokens = line.split(" ");
                UserProfile newUser = new UserProfile(tokens[0],Integer.parseInt(tokens[1]),Long.parseLong(tokens[2]));
                userData.add(newUser);
            }
            bufferedReadUser.close(); readUser.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Cannot Find File!");
        }
        catch(IOException e)
        {
            System.err.println("Cannot read from the file ");
        }
        
        return userData;
    }
    /**
     * this method is load data from an user profile arraylist and write into the 
     * text file.
     * @param userinfo an arraylist which contains user profile
     */
    public void writeUserData(ArrayList<UserProfile> userinfo)
    {
        try
        {
            PrintWriter writer = new PrintWriter("User.txt");
            for(int i = 0; i < userinfo.size(); i++)
            {
                writer.println(userinfo.get(i).getUserName() + " " + userinfo.get(i).getUserScore()
                                    +" " + userinfo.get(i).getTimeSpent());
            }
            writer.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Cannot Find the File!");
        }
        catch(IOException e)
        {
            System.err.println("Cannot Write to the File!");
        }
    }
    
    /**
     * this method is designed to read the game data from the text file, and return 
     * the arraylist of sudokulist back.
     * @return an arraylist of sudokulist.
     */
    public ArrayList<SudokuList> readGameData()
    {
        ArrayList<SudokuList> readArray = new ArrayList<SudokuList>();
        try
        {
            FileReader reader = new FileReader("Data.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            
            int numOfQ = Integer.parseInt(bufferedReader.readLine());
            
            for(int i = 0; i < numOfQ; i++)
            {
                // read the description of the question and sort it into different
                // labels.
                SudokuList newList = new SudokuList();
                Level level = Level.UNDEFINED;
                
                String line = bufferedReader.readLine();

                if (line.trim().equalsIgnoreCase("UNDEFINED")) 
                {
                    level = Level.UNDEFINED;
                } 
                else if (line.trim().equalsIgnoreCase("HARD")) 
                {
                    level = Level.HARD;
                } 
                else if (line.trim().equalsIgnoreCase("MEDIUM")) 
                {
                    level = Level.MEDIUM;
                } 
                else if (line.trim().equalsIgnoreCase("EASY")) 
                {
                    level = Level.EASY;
                }
                
                // read information from text file.
                for(int count = 0; count < 81; count ++)
                {
                    line = bufferedReader.readLine();
                    String[] tokens = line.split(" ");
                    
                    int pointX = Integer.parseInt(tokens[0]);
                    int pointY = Integer.parseInt(tokens[1]);
                    int value = Integer.parseInt(tokens[2]);
                    
                    if(value == 0)
                    {
                        newList.setSingleValue(pointX, pointY, value, true, ValueIndicator.UNSET);
                    }
                    else
                    {
                        newList.setSingleValue(pointX, pointY, value, false,ValueIndicator.QUESTIONS);
                    }
                    newList.setLevel(level);
                }
                
                readArray.add(newList);
            }
            bufferedReader.close(); reader.close();
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Cannot Find the File!");
        }
        catch(IOException e)
        {
            System.err.println("Cannot read Data!");
        }
        catch(NumberFormatException e)
        {
            System.err.println("Cannot Read Data!");   
        }
                
        return readArray;
    }
    /**
     * This methoed is designed to write information of sudoku list arraylist into text
     * file
     * @param writeArray an arraylist which needs to be write to the text file
     * @return an boolean which indicates this file is successfully write or not.
     */
    public boolean writeGameData(ArrayList<SudokuList> writeArray)
    {
        boolean success = false;
        
        try
        {
            PrintWriter output = new PrintWriter("Data.txt");
            output.println(writeArray.size());
            
            for(int i = 0; i < writeArray.size(); i++)
            {
                //output the level of this sudoku question.
                output.println(writeArray.get(i).getLevel().getDescribe());
                for(int x =0; x < 9; x++)
                {
                    for(int y = 0; y < 9; y++)
                    {
                        output.println(x + " " + y + " " + writeArray.get(i).getList()[x][y].getNumber());
                    }
                }
            }
            
            output.close();
            success = true;
        }
        catch(FileNotFoundException e)
        {
            System.err.println("Cannot Find the File!");
        }
        catch(IOException e)
        {
            System.err.println("Cannot write Data!");
        }
        
        return success;
    }
    
    /**
     * Test component
     */
//    public static void main(String[] args)
//    {
//        FileIO io = new FileIO();
////        io.writeArray.add(new SudokuList());
////        io.writeArray.get(0).setSingleValue(1, 1, 2);
////        io.writeData();
//
//        ArrayList<SudokuList> test = new ArrayList<SudokuList>();
//        test = io.readGameData();
//        System.out.println(test.get(0).getList()[1][1].getNumber() + " " +test.get(0).getList()[1][1].getWritable());
//    }
}
