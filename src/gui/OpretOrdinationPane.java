package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import ordination.Lægemiddel;
import ordination.Patient;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class OpretOrdinationPane extends GridPane {
    private final ListView<Patient> lstPatient = new ListView<>();
    private final ListView<Lægemiddel> lstLægemiddel = new ListView<>();
    private final ToggleGroup toggleGroup = new ToggleGroup();
    private final Label lblError;

    public OpretOrdinationPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        lblError = new Label();
        lblError.setTextFill(Color.RED);

        RadioButton rbPN = new RadioButton("PN");
        rbPN.setUserData(TypeOrdination.PN);
        RadioButton rbSkæv = new RadioButton("Daglig skæv");
        rbSkæv.setUserData(TypeOrdination.SKÆV);
        RadioButton rbFast = new RadioButton("Daglig fast");
        rbFast.setUserData(TypeOrdination.FAST);
        toggleGroup.getToggles().add(rbPN);
        toggleGroup.getToggles().add(rbSkæv);
        toggleGroup.getToggles().add(rbFast);

        this.add(new Label("Vælg patient"), 0, 0);
        this.add(lstPatient, 0, 1, 1, 2);
        lstPatient.getItems().setAll(Controller.getAllePatienter());

        this.add(new Label("Vælg lægemiddel"), 1, 0);
        this.add(lstLægemiddel, 1, 1, 1, 2);
        lstLægemiddel.getItems().setAll(Controller.getAlleLægemidler());

        this.add(new Label("Vælg ordination"), 2, 0);
        GridPane innerPane = new GridPane();
        innerPane.setVgap(10);
        innerPane.add(rbPN, 0, 0);
        innerPane.add(rbSkæv, 0, 1);
        innerPane.add(rbFast, 0, 2);

        this.add(innerPane, 2, 1);

        this.add(lblError, 0, 3, 1, 2);
        Button btnOpret = new Button("Opret ordination");
        this.add(btnOpret, 2, 2);
        btnOpret.setOnAction(_ -> actionOpret());

        RowConstraints row1 = new RowConstraints();
        RowConstraints row2 = new RowConstraints();
        RowConstraints row3 = new RowConstraints();
        row3.setValignment(VPos.BOTTOM);
        getRowConstraints().addAll(row1, row2, row3);
    }

    public void actionOpret() {
        lblError.setText("");
        if (lstPatient.getSelectionModel().getSelectedIndex() == -1) {
            lblError.setText("Du skal vælge en patient.");
        } else if (lstLægemiddel.getSelectionModel().getSelectedIndex() == -1) {
            lblError.setText("Du skal vælge et lægemiddel.");
        } else if (toggleGroup.getSelectedToggle() == null) {
            lblError.setText("Du skal vælge ordinationens type.");
        } else {
            OpretOrdinationDialog dia = new OpretOrdinationDialog(
                lstPatient.getSelectionModel().getSelectedItem(),
                lstLægemiddel.getSelectionModel().getSelectedItem(),
                (TypeOrdination) toggleGroup.getSelectedToggle().getUserData());
            dia.showAndWait();
        }
    }

    public void updateControls() {
        lstPatient.getSelectionModel().clearSelection();
        lstLægemiddel.getSelectionModel().clearSelection();
        toggleGroup.selectToggle(null);
    }
}
