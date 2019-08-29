package app.control;

import app.misc.StaticHelpers;
import app.model.Categoria;
import com.google.common.base.Strings;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class CategoriaGridControl extends GridControl<Categoria> {

    @FXML
    private TextField fxId;
    @FXML
    public TextField fxNombre;

    @FXML
    void initialize() {
    }

    @Override
    public void updateEditee(Categoria editee) {
        editee.setNombre(StaticHelpers.textInputEmptyToNull(fxNombre));
    }

    @Override
    public Categoria buildNew() {
        return new Categoria(StaticHelpers.textInputEmptyToNull(fxNombre));
    }

    @Override
    public void setFields(Categoria editee) {
        if (editee.getId() != null)
            fxId.setText((Integer.toString(editee.getId())));
        fxNombre.setText(Strings.nullToEmpty(editee.getNombre()));
    }

    @Override
    public boolean validFields() {
        return fxNombre.getText().trim().length() > 1;
    }
}
