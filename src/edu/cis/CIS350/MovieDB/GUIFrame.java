package edu.cis.CIS350.MovieDB;

import javax.swing.JFrame;

public class GUIFrame extends JFrame {
	private GUIPanel currentPanel;
	
	public GUIFrame() 	{
		currentPanel = new GUIPanel();
		
		setupFrame();
	}
	
	private void setupFrame()	{
		this.setContentPane(currentPanel);
	}

}
