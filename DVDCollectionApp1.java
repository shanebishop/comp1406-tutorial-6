import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;

public class DVDCollectionApp1  extends Application{
    private DVDCollection         model;
    private DVDCollectionAppView1 view;

    public DVDCollectionApp1() {
        model = DVDCollection.example1();
        //model.addObserver(view);
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the view
        view = new DVDCollectionAppView1(model);
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
                handleListClicked(view.getTitleList());
            }
        });
        view.getYearList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleListClicked(view.getYearList());
            }
        });
        view.getLengthList().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                handleListClicked(view.getLengthList());
            }
        });

        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        // Start up two other apps with different views
        DVDCollectionApp2 app2 = new DVDCollectionApp2(model);
        DVDCollectionApp3 app3 = new DVDCollectionApp3(model);
        app2.start(new Stage());
        app3.start(new Stage());
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

    private void handleListClicked(ListView v) {
        view.update(model, view.getSelectedIndex(v));
    }

    public static void main(String[] args) {
        launch(args);
    }
}