package main;

// jest abstrakcyjna klasa
// są konstruktory
// przeciążenie w gamefield
// przesłonięcie mam w player w draw
// dziedziczenie jest w player po entity ale mega na siłe.
//interfejs troche tez nie wiem gdzie

// TODO dodaj jescze śmieszną obsługe wyjątków i idk poczytaj co to ta kolejka i czy gdzies wcisniesz
// Ogarnij to spaghetti bo jest taki syf że aj waj

// + dziedziczenie, interfejsy, przeciążanie metod, kolejka,


import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Symulator ciecierzycy i bratków");
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        gamePanel.startGameThread();

    }
}