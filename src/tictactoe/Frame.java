package tictactoe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class Frame extends JFrame
{
	//Attributes
	private Panel m_GUIpanelTTT;
	
	public Frame()
	{
		// Customize
		super.setTitle("Tic Tac Toe!");
		setLayout(new GridLayout(1, 1));
		setSize(1300, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Initialize
		m_GUIpanelTTT = new Panel();
		
		// Add to the frame
		add(m_GUIpanelTTT);
		
		// SetVisibility
		setVisible(true);
	}
}