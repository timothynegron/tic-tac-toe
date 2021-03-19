//***************************************************************************
//File: GUIpanelTTT.java
//
//Purpose: To create a JPanel that uses JLabels, JTextFields, & JButtons for 
//		a Tic Tac Toe game. Extends from JPanel and implements Action
//		Listener.
//
//Written By: Timothy Negron
//
//Compilers: Eclipse Java Oxygen
//		   
//Update Information
//------------------
//
//Name:
//Date:
//Description: 
//
//***************************************************************************

package tictactoe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Panel extends JPanel implements ActionListener
{
	/***********************
	 * Attributes
	 ***********************/
	//First Row
	private JLabel[][] m_titleRowLBL = new JLabel [1][3];
	//Second Row
	private JLabel m_gamerTagLBL;
	private JTextField m_player1TF, m_player2TF;
	//Third Row
	private JLabel m_blank1, m_blank2;
	private JButton m_startBTN;
	//Fourth, Fifth, Sixth Row (three row array)
	private JButton[][] m_gameBTNS = new JButton [3][3];
	//Seventh Row
	private JLabel[][] m_currentPlayersLBL = new JLabel [1][3];
	// & Sixth Row (two row array)
	private JLabel[][] m_topPlayersLBL = new JLabel [2][3];
	// Eight Row
	private JButton m_createBTN;
	private JTextArea m_textArea;
	
	//Other Attributes
	public final static int NUM_OF_RC = 3; //Number of Rows & columns
	public final static int MAX_POINTS = 3; //Maximum number of points
	public final static String X = "X";
	public final static String O = "O";
	public static int turnTracker = 0;
	public static int PlayerID = 0;
	private int pointsP1 = 0;
	private int pointsP2 = 0;
	private String check;
	private int lineNumber = 0;
	private int fileLineNumber = 0;
	public final static String tttFile = "src/TTTrecords.txt";
	
	//***************************************************************************
	//Method: PanelMain()
	//
	//Purpose: To construct the panel.
	//			   
	//***************************************************************************
	public Panel()
	{
		//GamerTag = new Oplayer[100];
		
		setLayout(new GridLayout(7,3));
		
		/***********************
		 * Initializing & Adding
		 ***********************/
		//First Row
		m_titleRowLBL[0][0] = new JLabel("Tic");
		m_titleRowLBL[0][0].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		m_titleRowLBL[0][0].setHorizontalAlignment(JLabel.RIGHT);
		m_titleRowLBL[0][1] = new JLabel("Tac");
		m_titleRowLBL[0][1].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		m_titleRowLBL[0][1].setHorizontalAlignment(JLabel.CENTER);
		m_titleRowLBL[0][2] = new JLabel("Toe!");
		m_titleRowLBL[0][2].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		m_titleRowLBL[0][2].setHorizontalAlignment(JLabel.LEFT);
		add(m_titleRowLBL[0][0]);add(m_titleRowLBL[0][1]);add(m_titleRowLBL[0][2]);
		
		//Second Row
		m_gamerTagLBL = new JLabel("Gamertag:");
		m_gamerTagLBL.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		m_gamerTagLBL.setHorizontalAlignment(JLabel.CENTER);
		m_player1TF = new JTextField("X");
		m_player1TF.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		m_player1TF.setHorizontalAlignment(JLabel.CENTER);
		m_player2TF = new JTextField("O");
		m_player2TF.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		m_player2TF.setHorizontalAlignment(JLabel.CENTER);
		add(m_gamerTagLBL); add(m_player1TF); add(m_player2TF);
		
		//Third Row
		m_startBTN = new JButton("Start");
		m_startBTN.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		m_startBTN.addActionListener(this);//Eight Row
		m_createBTN = new JButton("Create");
		m_createBTN.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		m_createBTN.addActionListener(this);
		m_textArea = new JTextArea();
		add(m_startBTN); add(m_createBTN); add(new JScrollPane(m_textArea));
		
		
		//Fourth, Fifth, Sixth
		for(int row=0; row < NUM_OF_RC; row++)
		{
			for(int col=0; col < NUM_OF_RC; col++)
			{
				m_gameBTNS[row][col] = new JButton("-");
				m_gameBTNS[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
				m_gameBTNS[row][col].setForeground(Color.BLACK);
				m_gameBTNS[row][col].addActionListener(this);
				m_gameBTNS[row][col].setEnabled(false);
				add(m_gameBTNS[row][col]); //ADD BUTTONS as well
			}
		}
		
		//Sixth Row
		m_currentPlayersLBL[0][0] = new JLabel("Current Players:");
		m_currentPlayersLBL[0][0].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		m_currentPlayersLBL[0][0].setHorizontalAlignment(JLabel.CENTER);
		m_currentPlayersLBL[0][1] = new JLabel("-");
		m_currentPlayersLBL[0][1].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		m_currentPlayersLBL[0][1].setHorizontalAlignment(JLabel.CENTER);
		m_currentPlayersLBL[0][2] = new JLabel("-");
		m_currentPlayersLBL[0][2].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30));
		m_currentPlayersLBL[0][2].setHorizontalAlignment(JLabel.CENTER);
		add(m_currentPlayersLBL[0][0]);add(m_currentPlayersLBL[0][1]);add(m_currentPlayersLBL[0][2]);
				
	}

	//***************************************************************************
	//Method: actionPerformed()
	//
	//Purpose: To respond to users click on any button displayed on the frame.
	//			   
	//***************************************************************************
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton btnCLICKED = (JButton)e.getSource();
		String gamerTagP1, gamerTagP2;
		
		if(btnCLICKED.equals(m_startBTN))
		{
			System.out.println(++lineNumber+ ". " + "Start Button Selected");
			for(int row=0; row < NUM_OF_RC; row++)
			{
				for(int col=0; col < NUM_OF_RC; col++)
				{
					m_gameBTNS[row][col].setEnabled(true);
				}
			}
			
			gamerTagP1 = m_player1TF.getText();
			gamerTagP2 = m_player2TF.getText();
			
			m_createBTN.setEnabled(false);
			m_startBTN.setEnabled(false);
			m_player1TF.setEnabled(false);
			m_player2TF.setEnabled(false);
			
			m_currentPlayersLBL[0][1].setText(m_player1TF.getText());
			m_currentPlayersLBL[0][2].setText(m_player2TF.getText());
		}
		else if(btnCLICKED.equals(m_createBTN))
		{
			appendToTextArea();
		}
		else if((turnTracker % 2) == 0) //if turn tracker has no remainder when divisible by 2 than Player 1 Selected
		{
			for(int row = 0; row < NUM_OF_RC; row++)
			{
				for(int col = 0; col < NUM_OF_RC; col++)
				{
					if(m_gameBTNS[row][col] == btnCLICKED)
					{
						System.out.println(++lineNumber+ ". " + "Selection by Player 1");
						m_gameBTNS[row][col].setText(X);
						m_gameBTNS[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
						m_gameBTNS[row][col].setForeground(Color.MAGENTA);
						m_gameBTNS[row][col].setEnabled(false);
						
						appendToFile(++fileLineNumber+". Selection by " + m_player1TF.getText()+ 
								" Selected " + X + " on --- Row: " + Integer.toString(row) + 
								" Column: " + Integer.toString(col));
						
						turnTracker++;
						
						if(turnTracker > 4)
						{
						checkForWinner();
						}
					}
				}
			}
		}
		else if((turnTracker % 2) != 0) // if turn tracker has a remainder when divisible by 2 than Player 2 Selected
		{
			for(int row = 0; row < NUM_OF_RC; row++)
			{
				for(int col = 0; col < NUM_OF_RC; col++)
				{
					if(btnCLICKED == m_gameBTNS[row][col]) 
					{System.out.println(++lineNumber+ ". " + "Selection by Player 2");
						m_gameBTNS[row][col].setText(O);
						m_gameBTNS[row][col].setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
						m_gameBTNS[row][col].setForeground(Color.GREEN);
						m_gameBTNS[row][col].setEnabled(false);

						appendToFile(++fileLineNumber+". Selection by " + m_player2TF.getText()+  
						" Selected " + O + " on --- Row: " + Integer.toString(row) + 
						" Column: " + Integer.toString(col));
						
						turnTracker++;
						if(turnTracker > 4)
						{
							checkForWinner();
						}
					}
				}
			}
		}
		
		if(turnTracker == 9)
		{
			m_currentPlayersLBL[0][1].setText("Tie Game!");
			m_currentPlayersLBL[0][2].setText("Tie Game!");
			
			appendToFile(++fileLineNumber+". Tie Game!"); // Tie Game!
			resetGame();
		}
	} //		//----End of Action Performed----//
	
	//***************************************************************************
	//Method: checkForWinner(String Player, JButton btnCLICKED)
	//
	//Purpose: Called every time as soon as it is possible for a player to win.
	//		This method check if X or O has won.
	//			   
	//***************************************************************************
	
	public void checkForWinner()
	{		
		checkRows();
		checkColumns();
		checkBackSlash();
		checkForwardSlash();	
	}
	
	//***************************************************************************
	//Method: checkRows()
	//
	//Purpose: To see if someone won through rows
	//			   
	//***************************************************************************
	
	public void checkRows()
	{
		//Checking if someone won through rows (Horizontal)		
		for(int row = 0; row < NUM_OF_RC; row++)
		{
			for(int col = 0; col < NUM_OF_RC; col++)
			{
				check = m_gameBTNS[row][col].getText();
				
				if(check == X)
				{
					pointsP1++;
				}
				else if(check == O)
				{
					pointsP2++;
				}
				
				if(pointsP1 == MAX_POINTS)
				{
					m_currentPlayersLBL[0][1].setText(m_player1TF.getText()+" Wins!");
					m_currentPlayersLBL[0][2].setText(m_player2TF.getText()+" Lost!");
					
					appendToFile(++fileLineNumber+". " + m_player1TF.getText()+" Wins!"); // Player 1 Wins
					resetGame();
				}
				else if(pointsP2 == MAX_POINTS)
				{
					m_currentPlayersLBL[0][1].setText( m_player1TF.getText()+" Lost!");
					m_currentPlayersLBL[0][2].setText(m_player2TF.getText()+" Wins!");
					
					appendToFile(++fileLineNumber+". " + m_player2TF.getText()+" Wins!"); // Player 2 Wins
					resetGame();
				}
			}
		
			pointsP1 = 0;
			pointsP2 = 0;
		}	
	}
	
	//***************************************************************************
	//Method: checkColumns()
	//
	//Purpose: To see if someone won through columns
	//			   
	//***************************************************************************
	
	public void checkColumns()
	{
		//Check if someone wins through columns (vertical)
		for(int col = 0; col < NUM_OF_RC; col++)
		{
			for(int row = 0; row < NUM_OF_RC; row++)
			{
				check = m_gameBTNS[row][col].getText();
				
				if(check == X)
				{
					pointsP1++;
				}
				else if(check == O)
				{
					pointsP2++;
				}
				
				if(pointsP1 == MAX_POINTS)
				{
					m_currentPlayersLBL[0][1].setText(m_player1TF.getText()+" Wins!");
					m_currentPlayersLBL[0][2].setText(m_player2TF.getText()+" Lost!");
					
					appendToFile(++fileLineNumber+". " + m_player1TF.getText()+" Wins!"); // Player 1 Wins
					resetGame();
				}
				else if(pointsP2 == MAX_POINTS)
				{
					m_currentPlayersLBL[0][1].setText(m_player1TF.getText()+" Lost!");
					m_currentPlayersLBL[0][2].setText(m_player2TF.getText()+" Wins!");
					
					appendToFile(++fileLineNumber+". " + m_player2TF.getText()+" Wins!"); // Player 2 Wins
					resetGame();
				}
			}
			
			pointsP1 = 0;
			pointsP2 = 0;
		}
	}
	
	//***************************************************************************
	//Method: checkBackSlash()
	//
	//Purpose: To see if someone won diagonally from left to right starting from 
	//		   the top.
	//			   
	//***************************************************************************
	
	public void checkBackSlash()
	{
		//Check if someone wins through backslash
		for(int row = 0, col = 0; row < NUM_OF_RC; row++, col++)
		{
			check = m_gameBTNS[row][col].getText();
				
			if(check == X)
			{
				pointsP1++;
			}
			else if(check == O)
			{
				pointsP2++;
			}
			
			if(pointsP1 == MAX_POINTS)
			{
				m_currentPlayersLBL[0][1].setText(m_player1TF.getText()+" Wins!");
				m_currentPlayersLBL[0][2].setText(m_player2TF.getText()+" Lost.. :(");
				
				appendToFile(++fileLineNumber+". " + m_player1TF.getText()+" Wins!"); // Player 1 Wins
				resetGame();
			}
			else if(pointsP2 == MAX_POINTS)
			{
				m_currentPlayersLBL[0][1].setText(m_player1TF.getText()+" Lost.. :(");
				m_currentPlayersLBL[0][2].setText(m_player2TF.getText()+" Wins!");
				
				appendToFile(++fileLineNumber+". " + m_player2TF.getText()+" Wins!"); // Player 2 Wins
				resetGame();
			}
		}
				
		pointsP1 = 0;
		pointsP2 = 0;
	}
	
	//***************************************************************************
	//Method: checkForwardSlash()
	//
	//Purpose: To see if someone won diagonally from left to right starting from
	//		   the bottom.
	//			   
	//***************************************************************************
	
	public void checkForwardSlash()
	{
		//Check if someone wins through forward slash
		for(int row = 0, col = 2; row < NUM_OF_RC; row++, col--)
		{
			check = m_gameBTNS[row][col].getText();
				
			if(check == X)
			{
				pointsP1++;
			}
			else if(check == O)
			{
				pointsP2++;
			}
			
			if(pointsP1 == MAX_POINTS)
			{
				m_currentPlayersLBL[0][1].setText(m_player1TF.getText()+" Wins!");
				m_currentPlayersLBL[0][2].setText(m_player2TF.getText()+" Lost.. :(");
				
				appendToFile(++fileLineNumber+". " + m_player1TF.getText()+" Wins!"); // Player 1 Wins
				resetGame();
			}
			else if(pointsP2 == MAX_POINTS)
			{
				m_currentPlayersLBL[0][1].setText(m_player1TF.getText()+" Lost.. :(");
				m_currentPlayersLBL[0][2].setText(m_player2TF.getText()+" Wins!");
				
				appendToFile(++fileLineNumber+". " + m_player2TF.getText()+" Wins!"); // Player 2 Wins
				resetGame();
			}
		}
		
		pointsP1 = 0;
		pointsP2 = 0;
	}
	
	//***************************************************************************
	//Method: resetGame()
	//
	//Purpose: To reset the Start buttons and Game board.
	//			   
	//***************************************************************************
	
	public void resetGame()
	{
		m_startBTN.setText("Start New Match!");
		m_createBTN.setEnabled(true);
		m_startBTN.setEnabled(true);
		m_player1TF.setEnabled(true);
		m_player2TF.setEnabled(true);
		turnTracker = 0;
		for(int row=0; row < NUM_OF_RC; row++)
		{
			for(int col = 0; col < NUM_OF_RC; col++)
			{
				m_gameBTNS[row][col].setText("-");
				m_gameBTNS[row][col].setForeground(Color.BLACK);
				m_gameBTNS[row][col].setEnabled(false);
			}
		}
	}
	
	//***************************************************************************
	//Method: appendToFile()
	//
	//Purpose: To append all on going information about what is happening in the
	//		   game to a file.
	//			   
	//***************************************************************************
	public void appendToFile(String text)
	{
		PrintWriter outPut = null;
		
		try
		{
			FileOutputStream fileOutAppend = new FileOutputStream(tttFile, true);
			outPut = new PrintWriter(fileOutAppend);
			
			outPut.println(text);
			System.out.println(++lineNumber+ ". Append To File Method: try ");
		}
		catch(FileNotFoundException e)
		{
			System.out.println(++lineNumber+ ". Check your permissions and path or file name. Exception: "+e.getMessage());
		}
		finally
		{
			if(outPut != null)
			{
				outPut.close();
				System.out.println(++lineNumber+ ". Append To File Method: File Closing");
			}
		}
	}
	
	//***************************************************************************
	//Method: appendToTextArea()
	//
	//Purpose: To append all data in file to the Text Area on the frame.
	//			   
	//***************************************************************************
	
	public void appendToTextArea()
	{
		Scanner file = null;
		
		try
		{
			file = new Scanner(new File(tttFile));
			int lineNum = 0;
			
			while(file.hasNextLine())
			{
				m_textArea.append(file.nextLine() + "\n");
			}
			System.out.println(++lineNumber+ ". Append To Text Area Method: try ");
		}
		catch(FileNotFoundException e)
		{
			System.out.println(++lineNumber+ ". Check your permissions and path or file name. Exception: "+e.getMessage());
		}
		finally
		{
			if(file != null)
			{
				file.close();
				System.out.println(++lineNumber+ ". Append To Text Area Method: File Closing");
			}
		}
	}
}
