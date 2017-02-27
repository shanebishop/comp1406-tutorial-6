import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class DVDCollectionAppView2 extends Pane implements DVDView {
    private ListView<DVD>       tList;
    private TextField           tField, yField, lField;
    private DVDButtonPane       buttonPane;

    private DVDCollection       model;

    public ListView<DVD> getTitleList() { return tList; }
    public TextField getTitleField() { return tField; }
    public TextField getYearField() { return yField; }
    public TextField getLengthField() { return lField; }
    public DVDButtonPane getButtonPane() { return buttonPane; }
    public int getSelectedIndex() { return tList.getSelectionModel().getSelectedIndex(); }
    public String getSelectedTitle() { return tList.getSelectionModel().getSelectedItem().getTitle(); }

    public DVDCollectionAppView2(DVDCollection m) {
        model = m;

        // Create the labels
        Label label1 = new Label("DVDs");
        label1.relocate(10, 10);
        Label label2 = new Label("Title");
        label2.relocate(10, 202);
        Label label3 = new Label("Year");
        label3.relocate(10, 242);
        Label label4 = new Label("Length");
        label4.relocate(120, 242);

        // Create the TextFields
        tField = new TextField();
        tField.relocate(50, 200);
        tField.setPrefSize(500,30);
        yField = new TextField();
        yField.relocate(50, 240);
        yField.setPrefSize(55,30);
        lField = new TextField();
        lField.relocate(180, 240);
        lField.setPrefSize(45,30);

        // Create the lists
        tList = new ListView<DVD>();
        tList.relocate(10, 40);
        tList.setPrefSize(540,150);

        // Create the buttons
        buttonPane = new DVDButtonPane();
        buttonPane.relocate(250, 240);
        buttonPane.setPrefSize(305,30);

        // Add all the components to the Pane
        getChildren().addAll(label1, label2, label3, label4, tField, yField, lField, tList, buttonPane);

        setPrefSize(548, 268);

        update(model, -1);
    }

    public void update(DVDCollection model, int selectedDVD) {
        tList.setItems(FXCollections.observableArrayList(model.getDVDList()));

        if (tList.getSelectionModel().getSelectedIndex() < 0) {
            tField.setText("");
            yField.setText("");
            lField.setText("");
        }
        else {
            DVD selected = tList.getSelectionModel().getSelectedItem();
            tField.setText(selected.getTitle());
            yField.setText("" + selected.getYear());
            lField.setText("" + selected.getDuration());
        }
    }
}