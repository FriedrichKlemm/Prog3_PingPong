package Task_2.Controller;

import Task_2.MainView;
import Task_2.Models.Ball;
import Task_2.Models.Racket;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;

import java.beans.EventHandler;

public class Animation extends AnimationTimer {
    private MainView view;
    private Racket racket;
    private Ball ball;

    public Animation( MainView view ) {
        this.view = view;
        racket = new Racket(view);
        view.getField().setOnKeyPressed(new InputController(racket, view, this));

        ball = new Ball(view);
    }

    public void restart() {
        view.getGroup().getChildren().clear();
        view.getPauseButton().setText("Continue");
        this.stop();
    }

    @Override
    public void handle( long now ) {
        keepPaddleInPane();
        keepBallInPane();
        handlePaddleCollesion();
        ball.moveBall();


        view.getField().requestFocus();

        try {
            Thread.sleep(5);
        } catch (InterruptedException ignored) {

        }
    }

    private void speedUP() {
        if (view.getTextScore() != 0) {
            if (view.getTextScore() % 10 == 0) {
                ball.setStepY(ball.getStepY() * 1.1);
                ball.setStepX(ball.getStepX() * 1.1);
            }
        }
    }

    private void decreasePaddleSize() {
        if (view.getTextScore() != 0) {
            if (view.getTextScore() % 10 == 0) {
                racket.setHeight(racket.getHeight() * 0.8);
                racket.setStepY(racket.getStepY() * 1.2);
            }
        }
    }

    private void keepPaddleInPane() {
        racket.setBlockBottom(false);
        racket.setBlockTop(false);
        if (racket.getY() <= 0) {
            racket.setBlockTop(true);
        }

        if (racket.getY() + racket.getHeight() >= view.getField().getHeight()) {
            racket.setBlockBottom(true);
        }
    }

    private void keepBallInPane() {
        if (ball.getCenterX() + ball.getRadius() >= view.getField().getWidth()) {
            ball.setStepX(ball.getStepX() - 2 * ball.getStepX());
        }
        if (ball.getCenterY() + ball.getRadius() >= view.getField().getHeight()) {
            ball.setStepY(ball.getStepY() - 2 * ball.getStepY());
        }
        if (ball.getCenterY() - ball.getRadius() <= 0) {
            ball.setStepY(ball.getStepY() - 2 * ball.getStepY());
        }
        if (ball.getCenterX() - ball.getRadius() <= 0) {
            this.stop();
            view.getGameOver().setVisible(true);
        }
    }

    private void handlePaddleCollesion() {
        if (ball.intersects(racket.getLayoutBounds())) {
            ball.setStepX(ball.getStepX() - 2 * ball.getStepX());
            view.setTextScore(view.getTextScore() + 1);
            speedUP();
            decreasePaddleSize();
        }
        /*if (ball.getCenterX() - ball.getRadius() <= racket.getX() + racket.getWidth()){
            if (ball.getCenterY() - ball.getRadius() >= racket.getY() && ball.getCenterY() + ball.getRadius() <= racket.getY() + racket.getHeight()) {
                ball.setStepX(ball.getStepX() - 2 * ball.getStepX());
                view.setTextScore(view.getTextScore() + 1);
            }
        }

        if (ball.getCenterX() - ball.getRadius() >= racket.getX() && ball.getCenterX() + ball.getRadius() <= racket.getX() + racket.getWidth()) {
            if (ball.getCenterY() + ball.getRadius() == racket.getY()) {
                ball.setStepX(ball.getStepX() - 2 * ball.getStepX());
                ball.setStepY(ball.getStepY() - 2 * ball.getStepY());
                view.setTextScore((view.getTextScore() + 1));
            }
        }

        if (ball.getCenterX() - ball.getRadius() >= racket.getX() && ball.getCenterX() + ball.getRadius() <= racket.getX() + racket.getWidth()) {
            if (ball.getCenterY() - ball.getRadius() == racket.getHeight() + racket.getY()) {
                ball.setStepX(ball.getStepX() - 2 * ball.getStepX());
                ball.setStepY(ball.getStepY() - 2 * ball.getStepY());
                view.setTextScore((view.getTextScore() + 1));
            }
        }*/
    }
}
