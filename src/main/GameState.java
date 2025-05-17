package main;

import java.io.*;

public class GameState implements Serializable {
    public static GameState instance;

    int playerX;
    int playerY;
    int points;
    public FieldState[][] fieldStateArray = new FieldState[16][10];

    public static GameState getInstance() {
        if (instance == null) {
            deserialize();
            if (instance == null) {
                instance = new GameState();
            }
        }
        return instance;
    }

    public void setPlayerState(int x, int y, int points) {
        playerX = x;
        playerY = y;
        this.points = points;
    }

    public void setFieldState(int x, int y, int fieldState) {
        String type = null;
        if (null != fieldStateArray[x][y]) {
            type = fieldStateArray[x][y].name;
        }
        setFieldState(x, y, type, fieldState);
    }

    public void setFieldState(int x, int y, String type, int fieldState) {
        Integer state = null;
        if (null != fieldStateArray[x][y]) {
            state = fieldStateArray[x][y].state;
        }
        setFieldState(x, y, type, fieldState, state);
    }

    public void setFieldState(int x, int y, String type, int fieldState, Integer state) {
        Integer variety = null;
        if (null != fieldStateArray[x][y]) {
            variety = fieldStateArray[x][y].variety;
        }
        setFieldState(x, y, type, fieldState, state, variety);
    }

    public void setFieldState(int x, int y, String type, int fieldState, Integer state, Integer variety) {
        fieldStateArray[x][y] = new FieldState(x, y, type, fieldState, state, variety);
    }

    public int getPlayerX() {
        return playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public int getPoints() {
        return points;
    }

    public static void serialize() {
        GameState serialized = instance;
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("GameState.bin"))) {
            outputStream.writeObject(serialized);
            System.out.println("Serializacja zrobiona");
        }
        catch (IOException e) {
            System.out.println("Błąd zapisu");
        }
    }

    public static void deserialize() {
        GameState deserialized;

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("GameState.bin"))) {
            deserialized = (GameState) inputStream.readObject();
            instance = deserialized;
        }
        catch (IOException e) {
            System.out.println("Błąd odczytu");
        }
        catch (ClassNotFoundException e) {

        }
    }
}
