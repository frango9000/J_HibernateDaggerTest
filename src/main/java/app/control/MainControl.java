package app.control;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MainControl extends BorderPane {

    public MenuItem fxCategorias;
    public MenuItem fxProductos;
    public MenuItem fxVentas;
    public MenuItem fxVendidos;
    public BorderPane fxMainPane;

    public static Pane loadFXML() {
        String url = "/fxml/MainPane.fxml";
        Pane pane = null;
        try {
            pane = FXMLLoader.load(MainControl.class.getResource(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    public void fxCategoriasAction(ActionEvent actionEvent) {
        CategoriaTableControl u = new CategoriaTableControl();
        fxMainPane.setCenter(u);
    }

    public void fxProductosAction(ActionEvent actionEvent) {
    }

    public void fxVentasAction(ActionEvent actionEvent) {
    }

    public void fxVendidosAction(ActionEvent actionEvent) {
    }
}
