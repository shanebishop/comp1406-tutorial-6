import javafx.collections.FXCollections;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

public class DVDCollectionAppView1 extends Pane implements DVDView {
    private ListView<String>    tList;
    private ListView<Integer>   yList, lList;
    private DVDButtonPane       buttonPane;

    private DVDCollection       model;

    public ListView<String> getTitleList() { return tList; }
    public ListView<Integer> getYearList() { return yList; }
    public ListView<Integer> getLengthList() { return lList; }
    public DVDButtonPane getButtonPane() { return buttonPane; }
    public int getSelectedIndex() { return getSelectedIndex(tList); }
    public int getSelectedIndex(ListView v) { return v.getSelectionModel().getSelectedIndex(); }
    public String getSelectedTitle() { return tList.getSelectionModel().getSelectedItem(); }

    public DVDCollectionAppView1(DVDCollection m) {
        model = m;

        // Create the labels
        Label label1 = new Label("Title");
        label1.relocate(10, 10);
        Label label2 = new Label("Year");
        label2.relocate(220, 10);
        Label label3 = new Label("Length");
        label3.relocate(290, 10);

        // Create the lists
        tList = new ListView<String>();
        tList.relocate(10, 40);
        tList.setPrefSize(200,150);

        yList = new ListView<Integer>();
        yList.relocate(220, 40);
        yList.setPrefSize(60,150);

        lList = new ListView<Integer>();
        lList.relocate(290, 40);
        lList.setPrefSize(60,150);

        // Create the button pane
        buttonPane = new DVDButtonPane();
        buttonPane.relocate(30, 200);
        buttonPane.setPrefSize(305,30);

        // Add all the components to the Pane
        getChildren().addAll(label1, label2, label3, tList, yList, lList, buttonPane);

        setPrefSize(348, 228);

        update(model, -1);
    }

    public void update(DVDCollection model, int selectedDVD) {
        DVD[] dvds = model.getDVDList();
        String[] titles = new String[model.getDvdCount()];
        Integer[] years = new Integer[model.getDvdCount()];
        Integer[] lengths = new Integer[model.getDvdCount()];
        for (int i = 0; i < dvds.length; i++) {
            titles[i] = dvds[i].getTitle();
            years[i] = dvds[i].getYear();
            lengths[i] = dvds[i].getDuration();
        }

        // Fill in the separate ListViews
        tList.setItems(FXCollections.observableArrayList(titles));
        tList.getSelectionModel().select(selectedDVD);
        yList.setItems(FXCollections.observableArrayList(years));
        yList.getSelectionModel().select(selectedDVD);
        lList.setItems(FXCollections.observableArrayList(lengths));
        lList.getSelectionModel().select(selectedDVD);
    }
}