package com.risk.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class gameGuiPanel extends JPanel {

	public gameGuiPanel(int width, int height) {
		this.setPreferredSize(new Dimension(width, height));

		this.setBackground(Color.lightGray);
		this.setLayout(new FlowLayout());

	}

}
