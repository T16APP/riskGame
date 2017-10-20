package com.risk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * This class is used for the lower window of the main frame
 * author Sandeep Swainch
 * version 1.0
 */
public class gameGuiPanel extends JPanel {

	public gameGuiPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.lightGray);
		this.setLayout(new FlowLayout());

	}

}
