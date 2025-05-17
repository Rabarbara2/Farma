package main;

import java.io.Serializable;

public class FieldState implements Serializable {
    public int x;
    public int y;
    public String name;
    public int fieldState;
    public Integer state;
    public Integer variety;

    public FieldState(int x, int y, String name, int fieldState, Integer state, Integer variety) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.fieldState = fieldState;
        this.state = state;
        this.variety = variety;
    }
}
