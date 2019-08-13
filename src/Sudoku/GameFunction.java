/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sudoku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author dalto
 */
public class GameFunction 
{
    public UserProfile user;
    public TimeControl time;
    public Help help;
    public Catgory catgory;
    public Random random;
    public Display display;
    public UserType type;
    public UserVerify userList;
    public boolean isAssistMode;
    public ArrayList<SudokuList> questionList;
    public SudokuList question;
    Scanner scan = new Scanner(System.in);
    Scanner intScanner;
    
    boolean gameInProgress = true;
    
    public enum delayTime
    {
        NO_DELAY(0),TWO_SECOND(2000);
        
        private int delayTime;
        private delayTime(int time)
        {
            this.delayTime = time;
        }
        public int getDelayTime()
        {
            return this.delayTime;
        }
    }
    public enum UserType
    {
        OLD_USER, NEW_USER, UNDEFINED;
    }
    public enum PositionLetter
    {
        A(0,'A'),B(1,'B'),C(2,'C'),D(3,'D'),E(4,'E'),F(5,'F'),G(6,'G'),H(7,'H'),I(8,'I');
        
        private int numberIndicator;
        private char letter;
        
        private PositionLetter(int number, char letters)
        {
            this.numberIndicator = number;
            this.letter = letters;
        }
        public int getNumberIndicator()
        {
            return numberIndicator;
        }
        public char getLetter()
        {
            return letter;
        }
    }
    
    public GameFunction()
    {
        type = UserType.UNDEFINED;
        isAssistMode = true;
        time = new TimeControl();
        random = new Random();
        help = new Help();
        catgory = new Catgory();
        userList = new UserVerify();
        user = new UserProfile();
        display = new Display();
        question = new SudokuList();
        questionList = new ArrayList<SudokuList>();
        
        welcomeMsg();
        UserProfile user = askUserName();
        levelChoose();
        
        while(gameInProgress)
        {
            display.update(question);
            display.appearance();
            System.out.printf(">> ");
            String userInput = scan.nextLine();
            String[] tokens = userInput.trim().split(" ");
                    
            switch(tokens[0].toUpperCase())
            {
                case "CHANGE":
                {
                    changeLevel();
                    break;
                }
                case "HELP": 
                {
                    help.getHelpInfo(Help.helpEnum.GAME_IN_PROGRESS);
                    break;
                }
                case "EXIT": 
                {
                    saveProcee();
                    gameInProgress = false;
                    break;
                }
                case "CHECK": 
                {
                    checkAns();
                    break;
                }
                case "SET": 
                {
                    setNumbers(tokens);
                    break;
                }
                case "REMOVE": 
                {
                    removeNumbers(tokens);
                    break;
                }
                case "CLEAR":
                {
                    clear();
                    break;
                }
                case "ASSIST":
                {
                    assistMode(tokens[1]);
                    break;
                }
                case "RANK":
                {
                    seeRanking();
                    break;
                }
                case "TIME":
                {
                    seeTime();
                    break;
                }
                default:
                {
                    System.out.println("Unknown Command, enter 'help' to see what you can do");
                    break;
                }
            }
        }
    }
    public int scoreCounter()
    {
        int initialMark = 0;
        switch(question.getLevel())
        {
            case EASY:
            {
                initialMark = 40;
                break;
            }
            case MEDIUM:
            {
                initialMark = 80;
                break;
            }
            case HARD:
            {
                initialMark = 100;
                break;
            }
        }
        int mark = initialMark - (int)(time.getTimeIntervalMiliSec()/8000);
        return mark <= 0? 0:mark;
    }
    
    public void seeTime()
    {
        time.setEnd();
        System.out.println(user.getUserName() +",You've spent: " + time.getSingleTime());
        System.out.println("If you finish now, you can get " + scoreCounter());
    }
    public void seeRanking()
    {
        time.setEnd();
        ArrayList<UserProfile> rank = userList.getAllUser();
        Collections.sort(rank,new sortByScore());
        
        System.out.println("Past User:");
        for(int i = 0; i < rank.size(); i++)
        {   
            System.out.printf("%d User Name: %15s Score: %3d Total Time Spent: %s. \n",i+1,rank.get(i).getUserName()
                                    ,rank.get(i).getUserScore(),time.timeConvetor(rank.get(i).getTimeSpent()));
        }
        
        System.out.println("\nCurrent User:");
        
        if(type == UserType.NEW_USER)
        {
            System.out.printf("  User Name: %15s Score: %3d Total Time Spent: %s. \n",this.user.getUserName(),this.user.getUserScore(),
                    this.time.getSingleTime());
        }
        else
        {
            System.out.printf("  User Name: %15s Score: %3d Total Time Spent: %s. \n",this.user.getUserName(),this.user.getUserScore(),
                    time.timeConvetor(user.getTimeSpent() + time.getTimeIntervalMiliSec()));
            System.out.printf("             %15s            Current Time Spent: %s. \n","",time.getSingleTime());
        }
    }
    
    
    public void changeLevel()
    {
        boolean loop = true;
        
        while(loop)
        {
            System.out.println("This action will discard everything you've done so far");
            System.out.println("Do you wish to continue?(YES/NO)");
            System.out.printf(">> ");
            String userInput = scan.nextLine();
            
            if(userInput.equalsIgnoreCase("yes"))
            {
                levelChoose();
                loop = false;
            }
            else if (userInput.equalsIgnoreCase("HELP"))
            {
                help.getHelpInfo(Help.helpEnum.CHOSEN_LEVEL);
            }
            else if (userInput.equalsIgnoreCase("no"))
            {
               loop = false;
            }
        }
    }
    
    
    public void assistMode(String token)
    {
        if(token.equalsIgnoreCase("ON"))
        {
            isAssistMode = true;
            System.out.println("The assist mode is turned on.");
        }
        else if (token.equalsIgnoreCase("OFF"))
        {
            isAssistMode = false;
            System.out.println("The assist mode is turned off.");
        }
        else
        {
            System.out.println("Unknow Command");
        }
    }
    
    public void clear()
    {
        boolean loop = true;
        while(loop)
        {
            System.out.println("This action will clean everything you've done for this question");
            System.out.println("Do you wish to continue? (Yes/No)");
            System.out.print(">> ");
            
            String input = scan.nextLine();
            
            if(input.trim().equalsIgnoreCase("YES"))
            {
                for(int x = 0; x < 9; x++)
                {
                    for(int y = 0; y < 9; y++)
                    {
                        if(question.getList()[x][y].getWritable() == true)
                        {
                            question.getList()[x][y].setNumber(0);
                            
                        }
                    }
                }
                time.setStart();
                checkAns();
                loop = false;
            }
            else if (input.trim().equalsIgnoreCase("NO"))
            {
                loop = false;
            }
            else if (input.trim().equalsIgnoreCase("HELP"))
            {
                help.getHelpInfo(Help.helpEnum.CLEAR);
            }
            else
            {
                System.out.println("Invaild command, please enter Yes/No. \n");
            }
        }
    }
    public void removeNumbers(String[] tokens)
    {
        char[] letter = tokens[1].toUpperCase().toCharArray();

        if((letter[0] >= 'A' && letter[0] <= 'I')&&(letter[1] >= '1' && letter[1] <= '9'))
        {
            int positionOne = PositionLetter.valueOf(letter[0] + "").getNumberIndicator();
            int positionTwo = Integer.parseInt(letter[1]+"") - 1;
            
            if(question.getList()[positionOne][positionTwo].getWritable() == true)
            {
                question.getList()[positionOne][positionTwo].setNumber(0);
            }
            else
            {
                System.out.println("You cannot remove value from " + tokens[1]);
            }

        }
        else
        {
            System.out.println("Invaild Position");
        }
        
        if(isAssistMode == true)
        {
            checkAns();
        }
    }
    
    public void setNumbers(String[] tokens)
    {
        char[] letter = tokens[1].toUpperCase().toCharArray();

        if((letter[0] >= 'A' && letter[0] <= 'I')&&(letter[1] >= '1' && letter[1] <= '9') && letter.length == 2)
        {
            intScanner = new Scanner(tokens[2]);
            boolean vaildValue = false;

            if(intScanner.hasNextInt())
            {
                int value = intScanner.nextInt();

                if(value >= 1 && value <= 9)
                {
                    vaildValue = true;
                }
                else
                {
                    System.out.println("The range of value should between 1 - 9");
                }
            }
            else
            {
                System.out.println("The value should be an interger!");
            }

            if(vaildValue)
            {
                int value = Integer.parseInt(tokens[2]);
                int positionNumberOne = -1;
                int positionNumberTwo = Integer.parseInt(letter[1] + "") - 1;
                for (int i = 0; i < PositionLetter.values().length; i++) 
                {
                    if (letter[0] == PositionLetter.values()[i].getLetter()) 
                    {
                        positionNumberOne = PositionLetter.values()[i].getNumberIndicator();
                    }
                }

                if (question.getList()[positionNumberOne][positionNumberTwo].getWritable() == true) 
                {
                    this.question.setSingleValue(positionNumberOne, positionNumberTwo, value, true, ValueIndicator.USER_INPUT);
                } 
                else 
                {
                    System.out.println("You cannot set value in position " + PositionLetter.values()[positionNumberOne].getLetter()
                            + positionNumberTwo);
                } 
            }

        }
        else
        {
            System.out.println("Invaild Position");
        }
        
        if(isAssistMode == true)
        {
            checkAns();
        }        
    }

    public void success()
    {
        System.out.println("You've successfully solved the quiz!");
        
        time.setEnd();
        int score = scoreCounter();
        user.setTimeSpent(user.getTimeSpent() + time.getTimeIntervalMiliSec());
        
        user.setUserScore(user.getUserScore() + score);
        System.out.println("You've got " + user.getUserScore());
        
        
        boolean loop = false;
        do
        {
            System.out.println("Do you wish to continue? (Yes/No)");
            
            String input = scan.nextLine();
            
            if(input.trim().equalsIgnoreCase("YES"))
            {
                levelChoose();
                loop = false;
            }
            else if (input.trim().equalsIgnoreCase("NO"))
            {
                saveProcee();
                gameInProgress = false;
                loop = false;
            }
            else
            {
                System.out.println("Invaild Command");
                loop = true;
            }
            
        }while(loop == true);
        
    }
    public void saveProcee()
    {
        System.out.println("Do you wish to store your record? (Yes/No)");
        System.out.printf(">> ");
        String input = scan.nextLine();
        
        boolean loop = true;
        while(loop)
        {
            if(input.trim().equalsIgnoreCase("YES"))
            {
                if(type == type.NEW_USER)
                {
                    this.user.setTimeSpent(this.time.getTimeIntervalMiliSec());
                }
                else
                {
                    this.user.setTimeSpent(this.user.getTimeSpent() + this.time.getTimeIntervalMiliSec());
                }
                this.userList.addUser(user);
                this.userList.saveAllUser();
                System.out.println("Successfully saved! hope to see you next time!");
                loop = false;
            }
            else if (input.trim().equalsIgnoreCase("NO"))
            {
                loop = false;
                System.out.println("All good! Hope to see you next time!");
            }
            else if (input.trim().equalsIgnoreCase("HELP"))
            {
                help.getHelpInfo(Help.helpEnum.SAVE_PROCESS);
            }
            else
            {
                System.out.println("Invaild Command");
                loop = true;
            }
        }
    }
    
    public void checkAns()
    {
        HashSet<Integer> numberDuplicate = new HashSet<Integer>();
        numberDuplicate.clear();
        for(int x = 0; x < 9; x++)
        {
            for(int y = 0; y < 9; y++)
            {
                if(question.getList()[x][y].getWritable() == false)
                {
                    question.getList()[x][y].setIndicator(ValueIndicator.QUESTIONS);
                }
                else
                {
                    question.getList()[x][y].setIndicator(ValueIndicator.USER_INPUT);
                }
            }
        }
        
        //Block check 
        for(int block = 0; block < 3; block++)
        {
            for(int block2 = 0; block2 <3; block2 ++)
            {
                for(int x = 0; x < 3; x++)
                {
                    for(int y = 0; y < 3; y++)
                    {
                        int number = question.getList()[x + block*3][y + block2*3].getNumber();
                        
                        if (number != 0) 
                        {
                            if (!numberDuplicate.add(number)) 
                            {
                                int duplicate = number;

                                for (int index = 0; index < 3; index++) 
                                {
                                    for(int index2 = 0; index2 < 3; index2++)
                                    {
                                        if (question.getList()[index+ block*3][index2+ block2*3].getNumber() == duplicate) 
                                        {
                                            question.getList()[index+ block*3][index2+ block2*3].setIndicator(ValueIndicator.VALUE_DUPLICATION);
                                        }
                                    }
                                }
                            }
                            else 
                            {
                                if (question.getList()[x][y].getIndicator() != ValueIndicator.VALUE_DUPLICATION) 
                                {
                                    setNormalIndicator(x, y);
                                }
                            }
                        } 
                    }
                }
                numberDuplicate.clear();
            }
        }

        //Row check        
        for(int x = 0; x < 9; x++)
        {
            for(int y = 0; y < 9; y++)
            {
                int number = question.getList()[x][y].getNumber();
                
                if(number != 0)
                {
                    if(!numberDuplicate.add(number))
                    {
                        int duplicate = number;

                        for (int index = 0; index < 9; index++) 
                        {
                            if (question.getList()[x][index].getNumber() == duplicate) 
                            {
                                question.getList()[x][index].setIndicator(ValueIndicator.VALUE_DUPLICATION);
                            }
                        }
                    }
                    else 
                    {
                        if(question.getList()[x][y].getIndicator() != ValueIndicator.VALUE_DUPLICATION)
                        {
                            setNormalIndicator(x,y);
                        }
                    }
                }
            }
            numberDuplicate.clear();
        }
        
        //Coloum check
        for(int y = 0; y < 9; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                int number = question.getList()[x][y].getNumber();
                
                if(number != 0)
                {
                    if(!numberDuplicate.add(number))
                    {
                        int duplicate = number;

                        for(int index = 0; index <9 ; index++)
                        {
                            if (question.getList()[index][y].getNumber() == duplicate) 
                            {
                                question.getList()[index][y].setIndicator(ValueIndicator.VALUE_DUPLICATION);
                            }

                        }
                    }
                    else
                    {
                        if(question.getList()[x][y].getIndicator() != ValueIndicator.VALUE_DUPLICATION)
                        {
                            setNormalIndicator(x,y);
                        }
                    }
                }
            }
            numberDuplicate.clear();
        }
        int count = 0;
        
        for(int x = 0; x <9; x++)
        {
            for(int y = 0; y <9; y++)
            {
                if(question.getList()[x][y].getNumber() != 0 && question.getList()[x][y].getIndicator() != ValueIndicator.VALUE_DUPLICATION)
                {
                    count++;
                }
            }
        }
        
        if(count == 81)
        {
            success();
        }
        
    }
    public void setNormalIndicator(int x, int y)
    {
        if (question.getList()[x][y].getWritable() == true) 
        {
            question.getList()[x][y].setIndicator(ValueIndicator.USER_INPUT);
        } 
        else 
        {
            question.getList()[x][y].setIndicator(ValueIndicator.QUESTIONS);
        }
    }
    
    public void welcomeMsg()
    {
        System.out.println("Hello! Welcome To Play The Sudoku Games!");
    }

    
    public void levelChoose()
    {
        boolean correctInput = false;
        while(correctInput == false)
        {
            System.out.println("Please Choose Game Level");
            System.out.println("You can choose one from 'Simple' 'Medium' 'Hard'");
            System.out.println("Enter 'Help' For Further Information");
            System.out.printf(">> ");
            String userinput = scan.nextLine();
            
            if(userinput.equalsIgnoreCase("help"))
            {
                help.getHelpInfo(Help.helpEnum.CHOSEN_LEVEL);
            }
            else if (userinput.equalsIgnoreCase("simple"))
            {
                questionList = catgory.getEasyQuestion();
                correctInput = true;
            }
            else if (userinput.equalsIgnoreCase("medium"))
            {
                questionList = catgory.getMediumQuestion();
                correctInput = true;
            }
            else if (userinput.equalsIgnoreCase("hard"))
            {
                questionList = catgory.getHardQuestion();
                correctInput = true;
            }
            else
            {
                System.out.println("Unknown Command");
            }
            System.out.println("");
        }
        
        question = questionChoose();
    }
    
    public SudokuList questionChoose()
    {
        int number = questionList.size();
        int randomNumber = random.nextInt(number);
        time.setStart();
        return questionList.get(randomNumber);
    }
    public UserProfile askUserName()
    {
        boolean success = false;
        do
        {
            System.out.println("You can see more information by enter 'help' command");
            String userInput;
            do
            {  
                System.out.printf("Please enter your name: ");
                userInput = scan.nextLine();
                if(userInput.equalsIgnoreCase("HELP"))
                {
                    help.getHelpInfo(Help.helpEnum.ENTER_NAME);
                }
            }while(userInput.trim().equalsIgnoreCase("HELP"));
            
            UserProfile oldUser = userList.isExistence(userInput.trim());
            
            if(oldUser == null)
            {
                success = true;
                user = new UserProfile(userInput,0,0);
                type = UserType.NEW_USER;
                System.out.println("Great! your name is being created!");
                System.out.println(user.getUserName() + ", your score is " + user.getUserScore());
            }
            else
            {
                System.out.println("Oops, it seem your recored is already exist");
                
                boolean userEnterStatus = false;
                while(userEnterStatus == false)
                {
                    System.out.println("Enter 'Yes' to continue with current account");
                    System.out.println("Enter 'No' to creart another name");
                    
                    String userCommand = scan.nextLine();
                    if(userCommand.equalsIgnoreCase("yes"))
                    {
                        user = oldUser;
                        userList.removeUser(oldUser);
                        type = UserType.OLD_USER;
                        success = true;
                        userEnterStatus = true;
                        System.out.println("Great! The data is successfully loaded");
                        System.out.println(user.getUserName() + ", your score is " + user.getUserScore());
                    }
                    else if (userCommand.equalsIgnoreCase("no"))
                    {
                        userEnterStatus = true;
                    }
                    else
                    {
                        System.out.println("Unknown Command");
                    }
                }
            }
            
        }while(success == false);
                
        System.out.println("");
        return user;
    }
}
