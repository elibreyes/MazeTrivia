package com.company;

import javax.swing.*;

public class GamePlayGui {
    private JPanel gameplayPanel;
    private JMenuBar menuBar;
    private JMenu menu2;
    private JMenuItem helpAbout;
    private JMenuItem helpInstructions;
    private JMenu menu1;
    private JMenuItem fileSaveGame;
    private JMenuItem fileLoadGame;
    private JMenuItem fileExitGame;
    private JTextPane mazeDisplayTextPane;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GamePlayGui");
        frame.setContentPane(new GamePlayGui().gameplayPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
