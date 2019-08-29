package app.control;

import app.data.DataStore;
import app.data.IdentifiableDao;
import app.model.Categoria;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class CategoriaTableControl extends TableControl<Categoria> {


    @Override
    public void initialize() {
        super.initialize();
        setEditorFxml("/fxml/CategoriaEditorGridPane.fxml");

        TableColumn<Categoria, String> fxColumnName = new TableColumn<>("Nombre");
        fxColumnName.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombre"));
        fxTable.getColumns().add(fxColumnName);

        fxTable.setItems(listedObjects);
        addContent();
    }


    @Override
    protected IdentifiableDao<Categoria> getDataOrigin() {
        return DataStore.getCategorias();
    }


}
