package ru.home.beatBox.test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TwoButtons {

    JFrame frame;
    JLabel label;

    public static void main(String[] args) {
        TwoButtons gui = new TwoButtons();
        gui.go();
    }

    public void go() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton labelButton = new JButton("Change label");
        labelButton.addActionListener(new LabelListener());

        JButton colorButton = new JButton("Change colors");
        colorButton.addActionListener(new ColorListener());

        MyDrawPanel panel = new MyDrawPanel();

        label = new JLabel("I'm a label");

        frame.getContentPane().add(BorderLayout.SOUTH, colorButton);
        frame.getContentPane().add(BorderLayout.CENTER, panel);
        frame.getContentPane().add(BorderLayout.EAST, labelButton);
        frame.getContentPane().add(BorderLayout.WEST, label);
        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    class LabelListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            label.setText("Ouch!");
        }
    }

    class ColorListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            frame.repaint();
        }
    }
}
