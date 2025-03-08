package main;

// jest abstrakcyjna klasa
// są konstruktory
// nie wiem gdzie wcisnąć przeciążenie metod (zwykle mogą przyjąć tylko jeden typ zmiennej i albo nie ma innego albo sie wysypią)
// przesłonięcie mam w player w draw
// dziedziczenie jest w player po entity ale mega na siłe.
//interfejs troche tez nie wiem gdzie





// + dziedziczenie, interfejsy, przeciążanie metod, kolejka,


import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Farma Astra");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();

    }
}