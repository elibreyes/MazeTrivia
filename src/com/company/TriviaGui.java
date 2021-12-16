package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TriviaGui {
    private JPanel mainMenu;
    private JLabel mainMenuLabel;
    private JButton mainMenuButton;
    private JMenuBar menuBar;
    private JMenu menu1;
    private JMenu menu2;
    private JMenuItem fileSaveGame;
    private JMenuItem fileLoadGame;
    private JMenuItem fileExitGame;
    private JMenuItem helpAbout;
    private JMenuItem helpInstructions;

    public TriviaGui() {
        mainMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //RunProgram.run();
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Trivia Maze");
        frame.setContentPane(new TriviaGui().mainMenu);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void saveFrame() {
        JFrame saveFrame = new JFrame();
        saveFrame.setTitle("Save/Load Window");


        /**
         * _________ enter the name load/save
         * button and that will dispose on close
         */
        saveFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        saveFrame.setSize(new Dimension(400,400));
        saveFrame.setResizable(false);
        saveFrame.setLayout(new BorderLayout());

        /*JPanel topPanel = new JPanel();
        JTextField leftText = new JTextField();
        leftText.setPreferredSize(new Dimension(100,20));
        String[] operationStrings = { "+", "-", "*", "/"};
        JComboBox<String> operationList = new JComboBox<>(operationStrings);
        JTextField rightText = new JTextField();
        rightText.setPreferredSize(new Dimension(100,20));
        JButton convertButton = new JButton("=");
        topPanel.add(leftText);
        topPanel.add(operationList);
        topPanel.add(rightText);
        topPanel.add(convertButton);
        saveFrame.add(topPanel, BorderLayout.NORTH);*/

        saveFrame.setVisible(true);
    }

}
