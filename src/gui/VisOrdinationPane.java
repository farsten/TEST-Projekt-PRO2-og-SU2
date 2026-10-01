package gui;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import ordination.DagligFast;
import ordination.DagligSkæv;
import ordination.Ordination;
import ordination.PN;
import ordination.Patient;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class VisOrdinationPane extends GridPane {
    private final ListView<Patient> lstPatient = new ListView<>();
    private final ListView<Ordination> lstOrdination = new ListView<>();
    private final OrdinationDetailsPane ordinationDetailsPane = new OrdinationDetailsPane();

    public VisOrdinationPane() {
        this.setPadding(new Insets(20));
        this.setHgap(20);
        this.setVgap(10);
        this.setGridLinesVisible(false);

        this.add(new Label("Vælg patient"), 0, 0);
        this.add(lstPatient, 0, 1);
        lstPatient.getItems().setAll(Controller.getAllePatienter());
        lstPatient.getSelectionModel().selectedIndexProperty().addListener(_ ->
            lstOrdination.getItems().setAll(
                lstPatient.getSelectionModel().getSelectedItem().getOrdinationer()
            )
        );
        lstPatient.getSelectionModel().selectFirst();

        this.add(new Label("Vælg ordination"), 1, 0);
        this.add(lstOrdination, 1, 1);
        lstOrdination.getSelectionModel().selectedItemProperty().addListener(_ -> updateDetails());

        this.add(new Label("Ordinations detaljer"), 2, 0);
        this.add(ordinationDetailsPane, 2, 1);
    }

    public void updateDetails() {
        ordinationDetailsPane.clear();
        int selectedIndex = lstOrdination.getSelectionModel().getSelectedIndex();
        if (selectedIndex != -1) {
            Ordination ordination = lstOrdination.getSelectionModel().getSelectedItem();
            ordinationDetailsPane.clear();
            ordinationDetailsPane.setOrdination(ordination);
            switch (ordination) {
                case DagligFast dagligFast -> ordinationDetailsPane.setFast(
                    dagligFast.getDoser()[0], dagligFast.getDoser()[1],
                    dagligFast.getDoser()[2], dagligFast.getDoser()[3]
                );
                case DagligSkæv dagligSkæv -> ordinationDetailsPane.setSkæv(dagligSkæv);
                case PN pn -> ordinationDetailsPane.setPN(pn);
                default -> {
                }
            }
        }
    }

    public void updateControls() {
        int selectedIndex = 0;
        if (lstOrdination.getSelectionModel().getSelectedIndex() != -1) {
            selectedIndex = lstOrdination.getSelectionModel().getSelectedIndex();
        }
        lstOrdination.getItems().setAll(lstPatient.getSelectionModel().getSelectedItem().getOrdinationer());
        lstOrdination.getSelectionModel().select(selectedIndex);
    }
}
