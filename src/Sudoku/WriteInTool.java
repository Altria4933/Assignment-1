/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sudoku;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.InputMismatchException;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author dalto
 */
public class WriteInTool extends JFrame
{
    ArrayList<SudokuList> list = new ArrayList<SudokuList>();
    JList<SudokuList> jlist;
    JLabel[] rightToLeft = new JLabel[9];

    public WriteInTool(String title)
    {
        super(title);
        Catgory ca = new Catgory();
        this.list = ca.all;
        
        this.setSize(900, 500);
        this.setLayout(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JLabel label = new JLabel("请选择写入类型");
        label.setLocation(20, 20);
        label.setSize(100, 30);
        this.getContentPane().add(label);
        
        JButton remove = new JButton("删除鸭");
        remove.setLocation(150, 50);
        remove.setSize(80, 30);
        this.getContentPane().add(remove);
        
        
        JButton clear = new JButton("清空鸭");
        clear.setLocation(250, 50);
        clear.setSize(80, 30);
        this.getContentPane().add(clear);
        
        JButton add = new JButton("添加鸭");
        add.setLocation(350, 50);
        add.setSize(80, 30);
        this.getContentPane().add(add);
        
        JButton save = new JButton("存储鸭");
        save.setLocation(450, 50);
        save.setSize(80, 30);
        this.getContentPane().add(save);
        
        JLabel indicator = new JLabel();
        indicator.setText("你好鸭~");
        indicator.setLocation(550, 50);
        indicator.setSize(200, 30);
        this.getContentPane().add(indicator);
        
        String[] options = {"简单","中等","困难"};
        JComboBox select = new JComboBox(options);
        select.setLocation(20, 50);
        select.setSize(100,30);
        this.getContentPane().add(select);
        
//        JSlider game = new JSlider(JSlider.HORIZONTAL,0,100,100);
//        game.setLocation(600, 0);
//        game.setSize(200, 150);
//        game.setMajorTickSpacing(20);
//        game.setMinorTickSpacing(1);
//        game.setPaintTicks(true);
//        game.setPaintLabels(true);
//        game.disable();
//        this.getContentPane().add(game);
//               
//        JLabel gameLabel = new JLabel("强尼同学丑陋指数");
//        gameLabel.setLocation(460, 50);
//        gameLabel.setSize(120, 20);
//        this.getContentPane().add(gameLabel);
        
        jlist = new JList(this.getItemInList());
        jlist.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollList = new JScrollPane(jlist,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollList.setSize(250, 300);
        scrollList.setLocation(500, 100);
        this.getContentPane().add(scrollList);
        
        
        JTextField[][] field = new JTextField[9][9];
        
        for(int x = 0; x < 9; x++)
        {
            for(int y = 0; y < 9; y++)
            {
                field[x][y] = new JTextField();
                field[x][y].setLocation(30*(y) + 50, 30*(x) + 130);
                field[x][y].setSize(20, 20);
                this.getContentPane().add(field[x][y]);
            }
        }
        
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < 9; i++)
                {
                    for(int y = 0; y < 9; y++)
                    {
                        field[i][y].setText("");
                    }
                }
            }
        });
        
        remove.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                int number = jlist.getSelectedIndex();
                
                if(number != -1)
                {
                    list.remove(number);
                    jlist.setListData(getItemInList());
                }
            }
        });
        
        add.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                boolean correctInput = false;
                Level newLevel = Level.UNDEFINED;
                SudokuList newList = new SudokuList();
                for(int x = 0; x < 9; x++)
                {
                    for(int y = 0; y < 9; y++)
                    {
                        if(field[x][y].getText().trim().equals(""))
                        {
                            newList.setSingleValue(x, y, 0,true,ValueIndicator.UNSET);
                        }
                        else
                        {           
                            int number = Integer.parseInt(field[x][y].getText());

                            if(number > 0 && number <= 9)
                            {
                                correctInput = true;
                                newList.setSingleValue(x, y, number,false,ValueIndicator.QUESTIONS);
                                
                                if(select.getSelectedIndex() != -1)
                                {
                                    int selectNum = select.getSelectedIndex();
                                    
                                    if(selectNum == 0)
                                    {
                                        newLevel = Level.EASY;
                                    }
                                    else if(selectNum == 1)
                                    {
                                        newLevel = Level.MEDIUM;
                                    }
                                    else if(selectNum == 2)
                                    {
                                        newLevel = Level.HARD;
                                    }
                                }
                            }
                            else
                            {
                                indicator.setText("数字在[" + x + "][ " + y + "] 被输入错误！");
                                x = 10; y = 10;
                                correctInput = false;
                            }
                        }
                    }
                }
                
                if(correctInput == true)
                {
                    boolean exist = false;
                    for(int i = 0; i < list.size(); i++)
                    {
                        int count = 0;
                        for(int x = 0; x < 9; x++)
                        {
                            for(int y = 0; y < 9; y++)
                            {
                                if(list.get(i).getList()[x][y].getNumber() == (newList.getList()[x][y]).getNumber())
                                {
                                    count ++;
                                }
                            }
                        }
                        
                        if(count == 81)
                        {
                            exist = true;
                            i = list.size()+1;
                        }
                    }
                    
                    if(exist == false)
                    {
                        newList.setLevel(newLevel);
                        list.add(newList);
                        jlist.setListData(getItemInList());
                        indicator.setText("添加成功！");
                    }
                    else
                    {
                        indicator.setText("已存在！");

                    }
                }
            }
        });
        
        save.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                FileIO io = new FileIO();
                boolean status = io.writeGameData(list);
                
                if(status == true){indicator.setText("存好了鸭！");}
                else{indicator.setText("失败了鸭！");}
            }
        });
        
        jlist.addListSelectionListener(new ListSelectionListener() 
        {
            @Override
            public void valueChanged(ListSelectionEvent e) 
            {
                int selectNum = jlist.getSelectedIndex();
                
                if(selectNum != -1)
                {
                    SudokuList selectList = list.get(selectNum);
                    for(int x = 0; x < 9; x++)
                    {
                        for (int y = 0; y < 9; y++)
                        {
                            if(selectList.getList()[x][y].getNumber() != 0)
                            {
                                field[x][y].setText(selectList.getList()[x][y].getNumber() + "");
                            }
                            else
                            {
                                field[x][y].setText("");
                            }
                        }
                    }
                }
            }
        });
        
    }
    
    public SudokuList[] getItemInList()
    {
        SudokuList[] tempList = new SudokuList[this.list.size()];
        for(int i = 0; i < list.size(); i++)
        {
           tempList[i] = this.list.get(i);
        }
        
        return tempList;
    }

    
    public static void main(String[] args)
    {
        WriteInTool tool = new WriteInTool("写入数据工具");
        tool.setVisible(true);
    }
}
