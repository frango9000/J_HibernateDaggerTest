package app.control;

import app.data.DataStore;
import app.data.IdentifiableDao;
import app.model.Categoria;
import app.model.Producto;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductoTableControl extends TableControl<Producto> {


    @Override
    public void initialize() {
        super.initialize();
        setEditorFxml("/fxml/ProductoEditorGridPane.fxml");

        TableColumn<Producto, String> fxColumnName = new TableColumn<>("Nombre");
        fxColumnName.setCellValueFactory(new PropertyValueFactory<Producto, String>("nombre"));
        fxTable.getColumns().add(fxColumnName);

        TableColumn<Producto, Integer> fxColumnPrecio = new TableColumn<>("Precio");
        fxColumnPrecio.setCellValueFactory(new PropertyValueFactory<Producto, Integer>("precio"));
        fxTable.getColumns().add(fxColumnPrecio);

        TableColumn<Producto, Categoria> fxColumnCategoria = new TableColumn<>("Categoria");
        fxColumnCategoria.setCellValueFactory(new PropertyValueFactory<Producto, Categoria>("categoria"));
        fxTable.getColumns().add(fxColumnCategoria);

        fxTable.setItems(listedObjects);
        addContent();
    }


    @Override
    protected IdentifiableDao<Producto> getDataOrigin() {
        return DataStore.getProductos();
    }


}
