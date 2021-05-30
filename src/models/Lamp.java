package models;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class Lamp {

    private final Button lamp;
    private boolean isActive;

    public Lamp(Button lamp) {
        this.lamp = lamp;
        isActive = false;
    }

    public Button getLamp() {
        return lamp;
    }

    public Color getBackgroundColor() {
        if(isActive) {
            return Color.RED;
        } else {
            return Color.GRAY;
        }
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setBackgroundColor(Color color) {
        lamp.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void switchBackgroundColor() {
        if(!isActive) {
            setBackgroundColor(Color.RED);
        } else {
            setBackgroundColor(Color.GRAY);
        }
        isActive = !isActive;
    }

}
