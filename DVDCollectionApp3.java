import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class DVDCollectionApp3 extends Application {
    private DVDCollection         model;
    private DVDCollectionAppView3 view;

    public DVDCollectionApp3(DVDCollection c) {
        model = c;
        model.addObserver(view);
    }

    public void start(Stage primaryStage) {
        Pane aPane = new Pane();

        // Create the view
        view = new DVDCollectionAppView3(model);
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

        view.getDVDList().setOnMousePressed(new EventHandler<MouseEvent>() {
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
        String title  = JOptionPane.showInputDialog("Please enter a title:");
        int year      = Integer.parseInt(JOptionPane.showInputDialog("Please enter a year:"));
        int length    = Integer.parseInt(JOptionPane.showInputDialog("Please enter a duration:"));

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
