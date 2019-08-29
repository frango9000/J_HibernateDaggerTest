package app.control;

import app.MainFX;
import app.data.IdentifiableDao;
import app.misc.Flogger;
import app.model.Identifiable;
import app.view.FXMLStage;
import app.view.FxDialogs;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public abstract class TableControl<T extends Identifiable> extends BorderPane {

    protected final ObservableList<T> listedObjects = FXCollections.observableArrayList();

    @FXML
    protected TableView<T> fxTable;
    @FXML
    protected TableColumn<T, Integer> fxColumnId;

    protected String editorFxml;

    public TableControl() {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/GenericActivableTablePane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (final IOException e) {
            Flogger.atSevere().withCause(e).log();
        }
    }

    @FXML
    void initialize() {

        fxColumnId.setCellValueFactory(new PropertyValueFactory<T, Integer>("id"));
    }

    public void setEditorFxml(String editorFxml) {
        this.editorFxml = editorFxml;
    }

    @FXML
    protected void fxBtnAddAction(ActionEvent actionEvent) throws IOException {

        EditorControl<T> editorControl = getEditorControl(editorFxml, null, getDataOrigin());
        FXMLStage stage = new FXMLStage(editorControl, "Creator");
        stage.showAndWait();
        fxTable.refresh();
        addContent();
    }

    @FXML
    protected void fxBtnEditAction(ActionEvent actionEvent) throws IOException {
        T selected = fxTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            EditorControl<T> editorControl = getEditorControl(editorFxml, selected, getDataOrigin());
            FXMLStage stage = new FXMLStage(editorControl, selected.getClass().getSimpleName() + " Editor");
            stage.showAndWait();
            fxTable.refresh();
        }
    }

    @FXML
    protected void fxBtnEliminarAction(ActionEvent actionEvent) {
        T selected = fxTable.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (FxDialogs.showConfirmBoolean("Cuidado", "Deseas eliminar el Id " + selected.getId() + " ?")) {
                boolean success = getDataOrigin().delete(selected);
                FxDialogs.showInfo("", "Id " + selected.getId() + (success ? " " : "NO ") + "eliminado");
                if (success) {
                    listedObjects.remove(selected);
                }
            }
        }
    }

    @FXML
    protected void fxBtnLimpiarAction(ActionEvent actionEvent) {
        listedObjects.clear();
    }

    @FXML
    protected void fxBtnRefreshAction(ActionEvent actionEvent) {
        fxTable.refresh();
    }

    @FXML
    protected void fxBtnReloadAction(ActionEvent actionEvent) {
        addContent(true);
    }

    @FXML
    protected void fxBtnPullAction(ActionEvent actionEvent) {
        getDataOrigin().findAll();
        addContent(true);
    }

    @FXML
    protected void fxButtonBackAction(ActionEvent actionEvent) {
        ((BorderPane) MainFX.getMainStage().getScene().getRoot()).setCenter(null);
    }


    protected void addContent(boolean clean) {
        if (clean) {
            listedObjects.clear();
        }
        getDataOrigin().findAll().forEach(t -> {
            if (!listedObjects.contains(t)) {
                listedObjects.add(t);
            }
        });
    }

    protected void addContent() {
        addContent(false);
    }

    public <T extends Identifiable> EditorControl<T> getEditorControl(String fxml, T editee, IdentifiableDao<T> dataOrigin) {
        FXMLLoader loader = new FXMLLoader(TableControl.class.getResource(fxml));
        Pane pane = null;
        GridControl<T> control = null;
        try {
            pane    = loader.load();
            control = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new EditorControl<>(editee, dataOrigin, control, pane);

    }

    protected abstract IdentifiableDao<T> getDataOrigin();
}
