package com.company;

import javax.swing.*;
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
                RunProgram.run();
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

}
