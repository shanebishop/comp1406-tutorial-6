import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class DVDCollectionAppView3 extends Pane implements DVDView {
    private ListView<String>    dList;
    private DVDButtonPane       buttonPane;

    private DVDCollection       model;

    public ListView<String> getDVDList() { return dList; }
    public DVDButtonPane getButtonPane() { return buttonPane; }
    public int getSelectedIndex() { return dList.getSelectionModel().getSelectedIndex(); }
    public String getSelectedTitle() { return model.getDVDList()[getSelectedIndex()].getTitle(); }

    public DVDCollectionAppView3(DVDCollection m) {
        model = m;

        // Create the labels
        Label label1 = new Label("Title, Year, Length (min.)");
        label1.relocate(10, 10);

        // Create the lists
        dList = new ListView<String>();
        dList.relocate(10, 40);
        dList.setPrefSize(300,250);

        // Create the buttons
        buttonPane = new DVDButtonPane();
        buttonPane.relocate(10, 300);
        buttonPane.setPrefSize(305,30);

        // Add all the components to the Pane
        getChildren().addAll(label1, dList, buttonPane);

        setPrefSize(310, 330);

        update(model, -1);
    }

    public void update(DVDCollection model, int selectedDVD) {
        String[] dvds = new String[model.getDvdCount()];
        for (int i = 0; i < dvds.length; i++) {
            dvds[i] = model.getDVDList()[i].getTitle() + ", " +
                    model.getDVDList()[i].getYear() + ", " +
                    model.getDVDList()[i].getDuration();
        }
        dList.setItems(FXCollections.observableArrayList(dvds));
    }
}