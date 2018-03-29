package Task_2.Models;

import Task_2.MainView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;

public class Ball extends Circle {
    private MainView view;
    private double stepX;
    private double stepY;
    private Random random = new Random();
    private double x;
    private double y;

    public Ball( MainView view ) {
        super();

        this.view = view;

        stepX = 4;
        stepY = 5; //(random.nextDouble() * 7.5) -1;
        stepX = 5;//(random.nextDouble() * 7.5) -1;

        this.setRadius(15);
        x = 250;
        y = 250;
        this.setCenterX(x);
        this.setCenterY(y);
        this.setFill(Color.AQUA);

        view.getGroup().getChildren().add(this);

    }

    public void moveBall() {
        this.setCenterX(this.getCenterX() + stepX);
        this.setCenterY(this.getCenterY() + stepY);
    }

    public double getStepX() {
        return stepX;
    }

    public void setStepX( double stepX ) {
        this.stepX = stepX;
    }

    public double getStepY() {
        return stepY;
    }

    public void setStepY( double stepY ) {
        this.stepY = stepY;
    }
}
