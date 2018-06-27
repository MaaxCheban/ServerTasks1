package com.company;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        JFrame jFrame = new JFrame();

        jFrame.setTitle("Title");
        jFrame.setSize(950, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setLayout(new GridBagLayout());



        JPanel content = new JPanel();
        content.setLayout(new FlowLayout());


        JLabel inputTextLabel = new JLabel("Type some text:");
        JLabel inputPortLabel = new JLabel("Specify port:");
        JLabel inputHostLabel = new JLabel("Specify host:");


        JTextField textField = new JTextField(15);
        JTextField portField = new JTextField(4);
        JTextField hostField = new JTextField(8);

        JButton connectButton = new JButton("Connect to the server");
        JButton sendButton = new JButton("Send");

        jFrame.add(content);

        content.add(inputTextLabel);
        content.add(textField);

        content.add(inputPortLabel);
        content.add(portField);

        content.add(inputHostLabel);
        content.add(hostField);

        content.add(connectButton);
        content.add(sendButton);


        jFrame.setVisible(true);

    }
}
