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

    public boolean getIsActive() {
        return isActive;
    }

    public void setBackgroundColor(Color color) {
        lamp.setBackground(new Background(new BackgroundFill(color, new CornerRadii(3), Insets.EMPTY)));
        if(color == Color.web("0xF04747")) {
            isActive = false;
        } else if(color == Color.DIMGRAY) {
            isActive = true;
        }
    }

    public void switchBackgroundColor() {
        if(!isActive) {
            setBackgroundColor(Color.web("0xF04747"));
        } else {
            setBackgroundColor(Color.DIMGRAY);
        }
        isActive = !isActive;
    }

}
