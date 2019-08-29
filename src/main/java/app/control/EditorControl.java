package app.control;

import app.data.IdentifiableDao;
import app.misc.Flogger;
import app.model.Identifiable;
import app.view.FxDialogs;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class EditorControl<T extends Identifiable> extends BorderPane {

    @FXML
    public BorderPane fxGenericEditorBorderPane;
    protected T editee;
    protected boolean creating = true;
    protected GridControl<T> gridControl;
    private IdentifiableDao<T> dataOrigin;

    {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GenericEditorPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (final IOException e) {
            Flogger.atSevere().withCause(e).log();
        }
    }

    public EditorControl(T editee, IdentifiableDao<T> dataOrigin, GridControl<T> gridControl, Pane gridpane) {
        this.dataOrigin  = dataOrigin;
        this.gridControl = gridControl;
        setCenter(gridpane);
        if (editee != null) {
            creating    = false;
            this.editee = editee;
            gridControl.setFields(this.editee);
        }
    }


    @FXML
    protected void fxBtnDiscardAction(ActionEvent actionEvent) {
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    protected void fxBtnSaveAction(ActionEvent event) {
        if (gridControl.validFields()) {
            boolean success = false;
            if (creating) {
                editee  = gridControl.buildNew();
                success = dataOrigin.save(editee).isPresent();
            } else {
                try {
                    dataOrigin.getEntityManager().getTransaction().begin();
                    gridControl.updateEditee(editee);
                    dataOrigin.getEntityManager().getTransaction().commit();
                    success = true;
                } catch (Exception e) {
                    dataOrigin.getEntityManager().getTransaction().rollback();
                    Flogger.atWarning().withCause(e).log();
                }
            }
            if (success) {
                FxDialogs.showInfo("Success",
                                   editee.getClass().getSimpleName() + " " + (creating ? "creado" : "modificado"));
                ((Node) event.getSource()).getScene().getWindow().hide();
            } else {
                FxDialogs.showError("Fail!",
                                    editee.getClass().getSimpleName() + " no " + (creating ? "creado" : "modificado"));
                gridControl.setFields(editee);
            }
        } else {
            Flogger.atWarning().log("Invalid Fields");
            FxDialogs.showError("Fail!", "Invalid Fields");
        }
    }

}