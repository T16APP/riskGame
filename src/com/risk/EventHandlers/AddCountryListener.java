package com.risk.EventHandlers;




import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.lang.Thread.State;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

import com.risk.model.Country;
import com.risk.view.applicationWindow;

/**
 * Class that listens to user presses of the "Add Country" menu item
 * in the "edit" menu.
 */
public class AddCountryListener extends JFrame implements ActionListener {

	applicationWindow gui;
    Country countries;
    //Color color;
    Container content;
    JTextField txtColor, txtNumState, txtCountryName,txtContinentName;
    JColorChooser chooser;
    JFrame frame, frameColor;
    JLabel lblContinentName, lblCountryName, banner;
    JPanel panel1, panel2, panel3;
    JButton btnCreate, btnColor;

    /**
     * Constructor.
     * @param gui The user interface that holds the graph to be altered.
     */
    public AddCountryListener() {
    	gui = applicationWindow.getInstance();
    }

    /**
     * Causes a new window to Pop-up. This window then asks
     * the user to indicate the name, the number of states, and
     * the color of the country. The names of individual states must be
     * set later.
     * @param actionEvent Not used.
     */
    public void actionPerformed(ActionEvent actionEvent) {

      
        //JGraph graph = gui.getGraph();
        frame = new JFrame("MAP Editor");
        Container content = getContentPane();
        content.removeAll();
       
      
       lblContinentName = new JLabel("Name of the Continent: ");
       lblCountryName = new JLabel("Name of Country: ");
       

        btnCreate = new JButton("ADD CONTINENT ");
       

        panel1 = new JPanel();
        panel2 = new JPanel();
        panel3 = new JPanel();

        txtContinentName= new JTextField("Continent Name ");
        txtContinentName.setBounds(10, 100, 100, 20);
        txtContinentName = new JTextField("Country Name ");

       panel1.add(lblContinentName);
        panel1.add(txtContinentName);
     //   panel1.setBorder(border);
        panel1.setBounds(10, 20, 300, 50);

        panel2.add(lblCountryName);
        panel2.add(txtCountryName);
    //    panel2.setBorder(border);
        panel2.setBounds(10, 100, 300, 50);

        panel3.add(btnCreate);
        panel3.add(btnColor);
        panel3.setBounds(10, 200, 300, 50);

        frame.setLayout(new GridLayout(3, 1));
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);

        frame.setSize(350, 200);
        frame.setVisible(true);

     //   btnColor.addActionListener(new ColorAction());
      //  btnCreate.addActionListener(new CreateAction());
     //   btnColor.setSelected(false);
    }

  
}
