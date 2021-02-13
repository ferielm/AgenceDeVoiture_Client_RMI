/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Feriel Mokhtari
 */
public class GridBagLayoutDemo {


    public void addComponentsToPane(Container pane) {

        JButton button;
        pane.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        button = new JButton("Vis");        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        pane.add(button, c);


        button = new JButton("is Dispo");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 0;
        c.gridwidth = 1;
        pane.add(button, c);

        
        JLabel l = new JLabel(new ImageIcon(getClass().getResource("/images/visualiser.png")));
        c.fill = GridBagConstraints.HORIZONTAL;
        l.setSize(100, 100);
        c.ipady = 50;      //make this component tall
        c.weightx = 0.0;
        c.gridwidth = 3;
        c.insets = new Insets(10, 0, 0, 0);  //top padding
        c.gridx = 0;
        c.gridy = 1;
        pane.add(l, c);

        
        l = new JLabel("Long-Named Button 3");        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 0;       //reset to default
        c.weighty = 1.0;   //request any extra vertical space
        c.anchor = GridBagConstraints.PAGE_END; //bottom of space
        c.insets = new Insets(10, 0, 0, 0);  //top padding
        c.gridx = 0;       //aligned with button 2
        c.gridwidth = 3;   //2 columns wide
        c.gridy = 2;       //third row
        pane.add(l, c);
    }

    /**
     * Create the GUI and show it. For thread safety, this method should be
     * invoked from the event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("GridBagLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        new GridBagLayoutDemo().addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
