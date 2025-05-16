package main;

import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Symulator ciecierzycy i bratków");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("save or load");
        JMenuItem save = new JMenuItem("save");
        menu.add(save);
        JMenuItem load = new JMenuItem("load");

        menu.add(load);

        menubar.add(menu);


        GamePanel gamePanel = new GamePanel();
        load.addActionListener(e -> {gamePanel.load();});
        save.addActionListener(e -> {gamePanel.save();});
        window.setJMenuBar(menubar);

        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();

    }
}