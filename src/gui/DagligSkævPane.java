package gui;

import java.time.DateTimeException;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class DagligSkævPane extends GridPane {
    private final TextField txtTimeMinut = new TextField();
    private final TextField txtMængde = new TextField();
    private final ListView<String> listDosis = new ListView<>();
    private final Label lblError = new Label();

    public DagligSkævPane() {
        setHgap(20);
        setVgap(10);
        setGridLinesVisible(false);

        txtTimeMinut.setPromptText("TT:MM");
        txtMængde.setPromptText("Mængde");

        HBox hbox = new HBox(8);
        hbox.getChildren().add(txtTimeMinut);
        hbox.getChildren().add(txtMængde);
        Button btnOpret = new Button("Opret dosis");
        hbox.getChildren().add(btnOpret);
        add(hbox, 0, 0);

        listDosis.setMaxHeight(100);
        add(listDosis, 0, 1);

        btnOpret.setOnAction(_ -> opretDosis());

        lblError.setTextFill(Color.RED);
        add(lblError, 0, 2, 2, 1);
    }

    private void opretDosis() {
        lblError.setText("");
        String klStr = txtTimeMinut.getText();
        String mgdStr = txtMængde.getText();
        try {
            double mængde = Double.parseDouble(mgdStr);
            if (mængde <= 0) {
                txtMængde.clear();
                lblError.setText("Mængde eller klokkeslæt har ikke korrekt format");
                return;
            }
            Integer.parseInt(klStr.substring(0, 2));
            Integer.parseInt(klStr.substring(3));
            String dosis = klStr + " " + mgdStr;
            listDosis.getItems().add(dosis);
        } catch (NumberFormatException e) {
            txtMængde.clear();
            txtTimeMinut.clear();
            lblError.setText("Mængde  eller klokkeslæt er ikke korrekt format");
        } catch (DateTimeException e) {
            txtTimeMinut.clear();
            lblError.setText("Klokkeslæt er ikke korrekt format");
        }
    }

    public String[] getDosisArray() {
        ObservableList<String> items = listDosis.getItems();
        return items.toArray(new String[0]);
    }
}
