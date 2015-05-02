package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * this is the gui of the program.
 */

public class View extends JFrame{
	
	JPanel viewPanel;
	JPanel controllPanel;
	
	View() {
		super("Ages");
		setLayout(new BorderLayout());
		/*
		 * the frame will consist of two panels. the main panel will have all the graphics and so will show the gameboard.
		 * the second panel will be a controllpanel covering 1/5 of the bottom of the frame.
		 */
		
		//creating the panels, these will most likely be changed at some point
		viewPanel = new JPanel();
		controllPanel = new JPanel();
		
		
		//TODO: debug: the panels are set to different colors for testing purposes.
		viewPanel.setBackground(Color.GREEN);
		controllPanel.setBackground(Color.BLUE);
		
		//setting the size of the controllpanel that contains information and buttons and such
		controllPanel.setPreferredSize(new Dimension(800,100));
		
		
		//adding the panels to the frame
		add(viewPanel,BorderLayout.CENTER);
		add(controllPanel,BorderLayout.SOUTH);
		/*
		 * Frame behaviour
		 */
		setSize(800,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
}
