package sample;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;


public class TicTacToe_Code implements ActionListener {

    JFrame frame = new JFrame("TicTacToe!");  // creating frame

    JButton[] Boxes = new JButton[9]; // array of buttons

    JPanel panel = new JPanel(); // creating fist panel to show tic tac toe tital

    JPanel Button_panel = new JPanel(); // creating second panel to store all the buttons
    JPanel JPanelfor_winning = new JPanel(); // creating second panel to show the winnings

    JLabel tital = new JLabel(); // label for the tictactoe tital for the first 2 seconds


    Random random = new Random(); // this is to choose the random numbers do decide who's turn is it on the beginning
    boolean turn_decider; // assign the random value to this variable

    JLabel Wins_for_X = new JLabel();
    JLabel Wins_for_O = new JLabel();

    JFrame winners_frame;



    JPanel mainpanel;


    int x_wins_counts = 0;
    int O_wins_counts = 0;


    TicTacToe_Code() {
        frame.setVisible(true);  // setting the frames visibility
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // telling the program to close everything
        frame.setResizable(false); // preventing user from resizing the window
        frame.setSize(500, 1000); // setting the size of the window


        panel.setBounds(0, 20, 500, 50); // setting the bounds of the 1st panel
        panel.setBackground(new Color(0x000000)); // setting the background to black for the 1st panel
        frame.add(panel, BorderLayout.NORTH); // adding the 1st panel to the frame
        panel.add(tital); // adding the tital to the 1st panel

        tital.setText("Welcome to TicTacToe!"); // printing tictactoe on the display for the first 2 seconds
        tital.setFont(new Font("Display", Font.BOLD, 40)); /* setting the style of the tictactoe word
                                                                      and the turn indicator*/
        tital.setForeground(Color.red); // setting the color of the tictactoe and the turn indicator

        Wait(); // waiting for 2 seconds

        tital.setText("Loading......"); // printing tictactoe on the display for the first 2 seconds

        Wait();


        Printing_Buttons();  // Calling the method to print buttons


        Turn(); // Calling Method to decide who's turn it is and display the who's turn is it

        Wins_for_X.setText("X Total wins = " + x_wins_counts); // printing the total X wins label
        Wins_for_X.setFont(new Font("Times Roman", Font.BOLD, 25)); // setting the style of the total X wins label
        JPanelfor_winning.add(Wins_for_X); // adding total X wins to the panel

        Wins_for_O.setText("| O Total wins = " + O_wins_counts); // printing the total O wins label
        Wins_for_O.setFont(new Font("Times Roman", Font.BOLD, 25)); // setting the style of the total O wins label
        JPanelfor_winning.add(Wins_for_O); // adding total O wins to the panel
        JPanelfor_winning.setBackground(new Color(0x6BA162));
        JPanelfor_winning.setBounds(0,0,50,50);
        frame.add(JPanelfor_winning, BorderLayout.SOUTH); // adding the 3rd panel to the frame



    }

    public void Printing_Buttons() {
        // method for the buttons
        for (int i = 0; i < 9; i++) {
            // for loop to print the 9 buttons
            Boxes[i] = new JButton(); // creating each button
            Boxes[i].setPreferredSize(new Dimension(5,5));
            Boxes[i].setText(""); // setting the text into the button
            Boxes[i].setFont(new Font("Monospaced", Font.BOLD+Font.ITALIC,50));// setting the text style in the button
            Boxes[i].addActionListener(this);
            Boxes[i].setBackground(new Color(0xE8E8E8));
            Boxes[i].setFocusable(false);
            Button_panel.add(Boxes[i]); // adding the each button into the 2nd panel

        }
        frame.add(Button_panel); // adding the 2nd panel into the frame
        Button_panel.setLayout(new GridLayout(3, 3)); /* setting the table of the 2nd panel.
                                                               it will be like 3X3 tabel*/
    }

    public void reseting_the_buttons_and_turn()
    {
        for (int i = 0; i < 9; i++) {
            // clearing all the buttons
            Boxes[i].setPreferredSize(new Dimension(5,5));
            Boxes[i].setText(""); // setting the text into the button
            Boxes[i].setFont(new Font("Monospaced", Font.BOLD+Font.ITALIC,50));// setting the text style in the button
            Boxes[i].setBackground(new Color(0xE8E8E8));
            Boxes[i].setEnabled(true);
            Boxes[i].setFocusable(false);
            Turn();
        }

    }

    public void Wait()
    {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void Turn()
    {
        // method to decide the turn


//        try {
//            // this is to delay the display of the player's turn
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        if(random.nextInt(2) == 1)
        {
            // if the random picked number is 1. than it will assing true to the turn_decider and it will be X's turn
            turn_decider = true;
            tital.setText("X Turn");
        }
        else {
            // if the random picked number is 1. than it will assing false to the turn_decider and it will be 0's turn
            turn_decider = false;
            tital.setText("O Turn");
        }
    }

    public void updating_wins()
    {
        // this method is updating the score board
        Wins_for_X.setText("X Total wins = " + x_wins_counts); // printing the total X wins label
        Wins_for_X.setFont(new Font("Times Roman", Font.BOLD, 25)); // setting the style of the total X wins label
        JPanelfor_winning.add(Wins_for_X); // adding total X wins to the panel

        Wins_for_O.setText("| O Total wins = " + O_wins_counts); // printing the total O wins label
        Wins_for_O.setFont(new Font("Times Roman", Font.BOLD, 25)); // setting the style of the total O wins label
        JPanelfor_winning.add(Wins_for_O); // adding total O wins to the panel
        JPanelfor_winning.setBackground(new Color(0x6BA162));
        frame.add(JPanelfor_winning, BorderLayout.SOUTH); // adding the 3rd panel to the frame
    }

    public void play_again(String user)
    {
//        JOptionPane play_again_panel = new JOptionPane();
//        int yes_or_no = JOptionPane.showConfirmDialog(play_again_panel,user+" Wins. "+"game is over. " + "Would you like to play again?" + JOptionPane.YES_NO_OPTION);
//
//        if(yes_or_no == JOptionPane.YES_OPTION)
//        {
//            reseting_the_buttons_and_turn();
//        }
//        else if(yes_or_no == JOptionPane.NO_OPTION)
//        {
//            System.out.println("Thank you for playing!");
//            System.exit(0);
//        }
//        else if(yes_or_no == JOptionPane.CANCEL_OPTION)
//        {
//            System.out.println("Button Pressed: cancel");
//            System.out.println("When you are ready to leave, press the X button on the top right");
//        }

        // the first frame will get the winners name and then add the name to mySQL table
        // {
        winners_frame = new JFrame();
        winners_frame.setVisible(true);  // setting the frames visibility
        winners_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // telling the program to close everything
        winners_frame.setMinimumSize(new Dimension(550,100));
        winners_frame.setResizable(false); // preventing user from resizing the window

        mainpanel = new JPanel();
        mainpanel.setLayout(new BoxLayout(mainpanel,BoxLayout.LINE_AXIS));
        winners_frame.add(mainpanel);

        JPanel add_info_to_database_panel = new JPanel();
        mainpanel.add(add_info_to_database_panel);

        JLabel enter_name_label = new JLabel("Enter winner's name");
        add_info_to_database_panel.add(enter_name_label);

        JTextField enter_winners_name = new JTextField();
        enter_winners_name.setPreferredSize(new Dimension(80,40));
        add_info_to_database_panel.add(enter_winners_name);

        JButton add = new JButton("Click this button to add");
        add.setFocusable(false);
        add.setPreferredSize(new Dimension(200,30));
        add.addActionListener(ev->{

            try {
               Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","F84reqa@HldrOBRa");

                String winners_name = enter_winners_name.getText();

                Statement stm = conn.createStatement();
                String data_base_operation = "INSERT INTO USERS VALUES(' " + winners_name +"')";
                stm.execute(data_base_operation);
                stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            Connection conn = null;
            Statement stm = null;
            String name = enter_winners_name.getText(); // getting the winner's name
            try{
                System.out.println("connecting to the database");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tictactoe","root","F84reqa@HldrOBRa"); // connection to the database
                System.out.println("Connected");

                stm = conn.createStatement();
                String sql = "INSERT INTO winning_table " + "VALUES('" + name + "')"; // inserting the name to the database
                stm.executeUpdate(sql);

            } catch (SQLException se){
                se.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if(stm != null)
                        conn.close();
                }catch (SQLException se){

                }
                try {
                    if(conn != null)
                        conn.close();
                }catch (SQLException se){
                    se.printStackTrace();
                }
            }
            // }

            // this will open the seconds frame which will ask the user if they want to play again
            //{
            JFrame buttons_frame = new JFrame();  // creating the frame
            buttons_frame.setVisible(true);  // setting the frames visibility
            buttons_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // telling the program to close everything
            buttons_frame.setMinimumSize(new Dimension(500,100));

            JPanel button_panel = new JPanel(); // creating the penel for the buttons
            buttons_frame.add(button_panel);

            JLabel play_again_label = new JLabel(); // creating the label
            play_again_label.setText(user+" Wins, would like to play again?"); // setting the text to the label
            button_panel.add(play_again_label); // adding the label to the button

            JButton yes_button = new JButton("Yes"); // creating the yes button
            yes_button.setPreferredSize(new Dimension(80,30)); // setting the dimentions to the button
            yes_button.setFocusable(false);
            yes_button.addActionListener(ev2->{
                // actionperformed for the yes button
                reseting_the_buttons_and_turn(); // calling the method to reset the buttons
                winners_frame.dispose(); // closing the 1st frame
                buttons_frame.dispose(); // closing the 2ns frame
            });
            button_panel.add(yes_button); // adding the button to the panel

            JButton no = new JButton("No"); // creating the no button
            no.setPreferredSize(new Dimension(80,30));
            no.setFocusable(false);
            no.addActionListener(ev3->{
                // actionperformed for the no button
                System.out.println("Thank you for playing!"); // printing this to the IDE's terminal
                System.exit(0); // closing the program
            });
            button_panel.add(no); // adding the button to the panel


        });
        add_info_to_database_panel.add(add);
        // }

    }

    public void winning_conditions()
    {
        if((Boxes[0].getText().equals("X") ) && (Boxes[1].getText().equals("X")) && (Boxes[2].getText().equals("X")))
        {
            tital.setText("X Wins");
            if_x_is_winner(0,1,2);
            x_wins_counts++;
            updating_wins();
            play_again("X");
        }
        else if((Boxes[3].getText().equals("X")) && (Boxes[4].getText().equals("X")) && (Boxes[5].getText().equals("X")))
        {
            tital.setText("X Wins");
            if_x_is_winner(3,4,5);
            x_wins_counts++;
            updating_wins();
            play_again("X");
        }
        else if((Boxes[6].getText().equals("X")) && (Boxes[7].getText().equals("X")) && (Boxes[8].getText().equals("X")))
        {
            tital.setText("X Wins");
            if_x_is_winner(6,7,8);
            x_wins_counts++;
            updating_wins();
            play_again("X");
        }
        else if((Boxes[0].getText().equals("X")) && (Boxes[3].getText().equals("X")) && (Boxes[6].getText().equals("X")))
        {
            tital.setText("X Wins");
            if_x_is_winner(0,3,6);
            x_wins_counts++;
            updating_wins();
            play_again("X");
        }
        else if((Boxes[1].getText().equals("X")) && (Boxes[4].getText().equals("X")) && (Boxes[7].getText().equals("X")))
        {
            tital.setText("X Wins");
            if_x_is_winner(1,4,7);
            x_wins_counts++;
            updating_wins();
            play_again("X");
        }
        else if((Boxes[2].getText().equals("X")) && (Boxes[5].getText().equals("X")) && (Boxes[8].getText().equals("X")))
        {
            tital.setText("X Wins");
            if_x_is_winner(2,5,8);
            x_wins_counts++;
            updating_wins();
            play_again("X");
        }
        else if((Boxes[0].getText().equals("X")) && (Boxes[4].getText().equals("X")) && (Boxes[8].getText().equals("X")))
        {
            tital.setText("X Wins");
            if_x_is_winner(0,4,8);
            x_wins_counts++;
            updating_wins();
            play_again("X");
        }
        else if((Boxes[6].getText().equals("X")) && (Boxes[4].getText().equals("X")) && (Boxes[2].getText().equals("X")))
        {
            tital.setText("X Wins");
            if_x_is_winner(6,4,2);
            x_wins_counts++;
            updating_wins();
            play_again("X");
        }

        else if((Boxes[0].getText().equals("O")) && (Boxes[1].getText().equals("O")) && (Boxes[2].getText().equals("O")))
        {
            tital.setText("O Wins");
            if_O_is_winner(0,1,2);
            O_wins_counts++;
            updating_wins();
            play_again("O");
        }
        else if((Boxes[3].getText().equals("O")) && (Boxes[4].getText().equals("O")) && (Boxes[5].getText().equals("O")))
        {
            tital.setText("O Wins");
            if_O_is_winner(3,4,5);
            O_wins_counts++;
            updating_wins();
            play_again("O");
        }
        else if((Boxes[6].getText().equals("O")) && (Boxes[7].getText().equals("O")) && (Boxes[8].getText().equals("O")))
        {
            tital.setText("O Wins");
            if_O_is_winner(6,7,8);
            O_wins_counts++;
            updating_wins();
            play_again("O");
        }
        else if((Boxes[0].getText().equals("O")) && (Boxes[3].getText().equals("O")) && (Boxes[6].getText().equals("O")))
        {
            tital.setText("O Wins");
            if_O_is_winner(0,3,6);
            O_wins_counts++;
            updating_wins();
            play_again("O");
        }
        else if((Boxes[1].getText().equals("O")) && (Boxes[4].getText().equals("O")) && (Boxes[7].getText().equals("O")))
        {
            tital.setText("O Wins");
            if_O_is_winner(1,4,7);
            O_wins_counts++;
            updating_wins();
            play_again("O");
        }
        else if((Boxes[2].getText().equals("O")) && (Boxes[5].getText().equals("O")) && (Boxes[8].getText().equals("O")))
        {
            tital.setText("O Wins");
            if_O_is_winner(2,5,8);
            O_wins_counts++;
            updating_wins();
            play_again("O");
        }
        else if((Boxes[0].getText().equals("O")) && (Boxes[4].getText().equals("O")) && (Boxes[8].getText().equals("O")))
        {
            tital.setText("O Wins");
            if_O_is_winner(0,4,8);
            O_wins_counts++;
            updating_wins();
            play_again("O");
        }
        else if((Boxes[6].getText().equals("O")) && (Boxes[4].getText().equals("O")) && (Boxes[2].getText().equals("O")))
        {
            tital.setText("O Wins");
            if_O_is_winner(6,4,2);
            O_wins_counts++;
            updating_wins();
            play_again("O");
        }



    }

    public void if_x_is_winner(int x,int y,int z)
    {
        Boxes[x].setBackground(new Color(3, 109, 8));
        Boxes[y].setBackground(new Color(3, 109, 8));
        Boxes[z].setBackground(new Color(3, 109, 8));
        for(int i = 0; i<9;i++)
        {
            Boxes[i].setEnabled(false); // makes the button disable
        }
    }
    public void if_O_is_winner(int x,int y,int z)
    {
        Boxes[x].setBackground(new Color(111, 206, 8));
        Boxes[y].setBackground(new Color(111, 206, 8));
        Boxes[z].setBackground(new Color(111, 206, 8));
        for(int i = 0; i<9;i++)
        {
            Boxes[i].setEnabled(false); // makes the button disable
        }
    }
    public boolean if_its_draw()
    {
        // this method will check if it's draw
        boolean all_buttons_filled = true; // this will mark the button to false if the button is available to pick
        for(int i=0; i<9;i++)
        {
            // this loop will check all the buttons if it's blank
            if(Boxes[i].getText().equals(""))
            {
                // if the button is blank then the field called "all_buttons_filled" will be false
                all_buttons_filled = false;
            }
        }
        return all_buttons_filled; // returning the boolean
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++)
        // for loop to process the each input that user will give
        {
            if (e.getSource() == Boxes[i]) {
                if (turn_decider) {
                    if (Boxes[i].getText().equals("")) {
                        Boxes[i].setText("X");
                        Boxes[i].setForeground(new Color(0, 0, 0));
                        tital.setText("O Turn");
                        turn_decider = false;
                        winning_conditions(); // checking the winning condition

                    }
                } else {
                    if (Boxes[i].getText().equals("")) {
                        Boxes[i].setText("O");
                        Boxes[i].setForeground(new Color(0, 92, 255));
                        tital.setText("X Turn");
                        turn_decider = true; // turning this flag to true so that it would be X's can play his turn
                        winning_conditions(); // checking the winning condition
                    }
                }
            }
        }
        if(if_its_draw())
        {
            // if "all_buttons_filled" variable is true, then it will print draw to screen and reset the buttons and turn.
            tital.setText("Draw");
            play_again("No");
            reseting_the_buttons_and_turn();
        }


    }
}