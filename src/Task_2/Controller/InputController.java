package Task_2.Controller;

import Task_2.MainView;
import Task_2.Models.Racket;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import static javafx.scene.input.KeyCode.SPACE;

public class InputController implements EventHandler<KeyEvent> {
    Racket racket;
    MainView view;
    Animation animation;

    public InputController( Racket racket, MainView view, Animation animation ) {
        this.view = view;
        this.racket = racket;
        this.animation = animation;
    }


    @Override
    public void handle( KeyEvent event ) {

        switch (event.getCode()) {
            case W: {
                if (view.getPauseButton().getText().equals("Pause")) {
                    racket.moveUp();
                }
                break;
            }
            case S: {
                if (view.getPauseButton().getText().equals("Pause")) {
                    racket.moveDown();
                }
                break;
            }
            case SPACE: {
                if (view.getPauseButton().getText().equals("Pause")) {
                    view.getPauseButton().setText("Continue");
                    animation.stop();
                    view.getField().requestFocus();
                    break;
                } else {
                    view.getPauseButton().setText("Pause");
                    animation.start();
                    view.getField().requestFocus();
                    break;
                }
            }
        }
        //System.out.println("HI");

    }

}
