package app.control;

import app.data.DataStore;
import app.misc.StaticHelpers;
import app.model.Categoria;
import app.model.Producto;
import com.google.common.base.Strings;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;


public class ProductoGridControl extends GridControl<Producto> {

    @FXML
    private TextField fxId;
    @FXML
    public TextField fxNombre;
    @FXML
    public TextField fxPrecio;
    @FXML
    public ComboBox<Categoria> fxCbxCategoria;

    @FXML
    void initialize() {
        fxCbxCategoria.getItems().addAll(DataStore.getCategorias().findAll());
    }

    @Override
    public void updateEditee(Producto editee) {
        editee.setNombre(StaticHelpers.textInputEmptyToNull(fxNombre));
        editee.setPrecio(Integer.parseInt(fxPrecio.getText().trim()));
        editee.setCategoria(fxCbxCategoria.getSelectionModel().getSelectedItem());
    }

    @Override
    public Producto buildNew() {
        Producto producto = new Producto();
        updateEditee(producto);
        return producto;
    }

    @Override
    public void setFields(Producto editee) {
        fxId.setText((Integer.toString(editee.getId())));
        fxNombre.setText(Strings.nullToEmpty(editee.getNombre()));
        fxPrecio.setText(Integer.toString(editee.getPrecio()));
        fxCbxCategoria.getSelectionModel().select(editee.getCategoria());
    }

    @Override
    public boolean validFields() {
        return fxNombre.getText().trim().length() > 1 &&
               StaticHelpers.isInteger(fxPrecio.getText().trim()) &&
               fxCbxCategoria.getSelectionModel().getSelectedItem() != null;
    }
}
