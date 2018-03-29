package Task_2.Models;

import Task_2.MainView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Racket extends Rectangle {
    private double dY;
    private double dX;
    private double stepY = 10;
    MainView view;
    private boolean blockTop = false;
    private boolean blockBottom = false;

    public Racket( MainView view ) {
        super();

        this.view = view;

        System.out.println(view.getField().getHeight());
        System.out.println(view.getField().getWidth());

        this.dY = 250;
        this.dX = 50;
        this.setWidth(20);
        this.setHeight(200);
        this.setFill(Color.CYAN);

        System.out.println(dX + ";" + dY);

        setX(dX);
        setY(dY);

        view.getGroup().getChildren().add(this);
    }

    public void moveUp() {
        if (blockTop == false) {
            dY = dY - stepY;
            setY(dY);
        }
    }

    public void moveDown() {
        if (blockBottom == false) {
            dY = dY + stepY;
            setY(dY);
        }
    }

    public double getUperBorder() {
        return getY();
    }

    public double getBottomBorder() {
        return getY() - getHeight();
    }

    public boolean isBlockTop() {
        return blockTop;
    }

    public void setBlockTop( boolean block ) {
        this.blockTop = block;
    }

    public boolean isBlockBottom() {
        return blockBottom;
    }

    public void setBlockBottom( boolean block ) {
        this.blockBottom = block;
    }

    public void setStepY( double stepY ) {
        this.stepY = stepY;
    }

    public double getStepY() {
        return stepY;
    }
}
