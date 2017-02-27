import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class DVDCollectionApp2 extends Application {
    private DVDCollection         model;
    private DVDCollectionAppView2 view;

    public DVDCollectionApp2(DVDCollection c) {
        model = c;
        model.addObserver(view);
    }

    public void start(Stage primaryStage) {
        Pane aPane = new Pane();

        // Create the view
        view = new DVDCollectionAppView2(model);
        aPane.getChildren().add(view);

        view.getButtonPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleAddButtonPress();
            }
        });

        view.getButtonPane().getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleDeleteButtonPress();
            }
        });

        view.getTitleList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleListSelection();
            }
        });

        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();
    }

    private void handleAddButtonPress() {
        String title  = view.getTitleField().getText();
        int    year   = Integer.parseInt(view.getYearField().getText());
        int    length = Integer.parseInt(view.getLengthField().getText());

        model.add(new DVD(title, year, length));
        //view.update(model, -1);
    }

    private void handleDeleteButtonPress() {
        model.remove(view.getSelectedTitle());
        //view.update(model, -1);
    }

    private void handleListSelection() {
        view.update(model, view.getSelectedIndex());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
