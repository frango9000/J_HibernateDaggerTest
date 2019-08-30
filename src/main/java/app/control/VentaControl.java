package app.control;

import app.data.DataStore;
import app.misc.Flogger;
import app.model.Producto;
import app.model.Vendido;
import app.model.Venta;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import jfxtras.scene.control.LocalDateTimeTextField;

public class VentaControl extends BorderPane {

    protected final ObservableList<Producto> listedProductos = FXCollections.observableArrayList();
    protected final ObservableList<Vendido> listedVendidos = FXCollections.observableArrayList();

    @FXML
    private TableView<Vendido> fxTableVendidos;
    @FXML
    private TableColumn<Vendido, Integer> fxColVendidoId;
    @FXML
    private TableColumn<Vendido, Producto> fxColVendidoNombre;
    @FXML
    private TableColumn<Vendido, Integer> fxColVendidoCantidad;
    @FXML
    private TableColumn<Vendido, Integer> fxColVendidoPrecio;
    @FXML
    private TableView<Producto> fxTableProductos;
    @FXML
    private TableColumn<Producto, Integer> fxColProductoId;
    @FXML
    private TableColumn<Producto, String> fxColProductoNombre;
    @FXML
    private TableColumn<Producto, Integer> fxColProductoPrecioU;
    @FXML
    private TextField fxFieldId;
    @FXML
    private LocalDateTimeTextField fxFieldDate;
    @FXML
    private MenuButton fxMenuCategorias;

    {
        try {
            final FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/VentasPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();
        } catch (final IOException e) {
            Flogger.atSevere().withCause(e).log();
        }

        fxTableVendidos.setItems(listedVendidos);
        fxTableProductos.setItems(listedProductos);
        fxColVendidoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        fxColVendidoNombre.setCellValueFactory(new PropertyValueFactory<>("producto"));
        fxColVendidoCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        fxColVendidoPrecio.setCellValueFactory(new PropertyValueFactory<>("precioUnidad"));

        fxColProductoId.setCellValueFactory(new PropertyValueFactory<>("id"));
        fxColProductoNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fxColProductoPrecioU.setCellValueFactory(new PropertyValueFactory<>("precio"));
        listedProductos.addAll(DataStore.getProductos().findAll());

        DataStore.getCategorias().findAll().forEach(categoria -> {
            MenuItem cat = new MenuItem(categoria.getNombre());
            cat.setOnAction(event -> {
                listedProductos.clear();
                listedProductos.addAll(categoria.getProductos());
            });
            fxMenuCategorias.getItems().add(cat);
        });


    }

    public VentaControl(Venta venta) {
        if (venta != null) {

        } else {

        }
    }


    @FXML
    void fxBtnAdd(ActionEvent event) {
        Producto selected = fxTableProductos.getSelectionModel().getSelectedItem();
        if (selected != null) {
            boolean exists = false;
            Iterator<Vendido> it = listedVendidos.iterator();
            while (it.hasNext()) {
                if (it.next().getProducto() == selected) {
                    it.next().setCantidad(it.next().getCantidad() + 1);
                    exists = true;
                    break;
                }
            }
            if (!exists) {
                Vendido vendido = new Vendido();
                vendido.setProducto(selected);
                vendido.setCantidad(1);
                vendido.setPrecioUnidad(selected.getPrecio());
                listedVendidos.add(vendido);
            }
        }
    }

    @FXML
    void fxBtnDiscard(ActionEvent event) {
        this.setVisible(false);

    }

    @FXML
    void fxBtnEdit(ActionEvent event) {

    }

    @FXML
    void fxBtnRemove(ActionEvent event) {
        Vendido selected = fxTableVendidos.getSelectionModel().getSelectedItem();
        if (selected != null) {
            listedVendidos.remove(selected);
        }

    }

    @FXML
    void fxBtnSave(ActionEvent event) {
        Venta venta = new Venta();
        venta.setFecha_hora(LocalDateTime.now());
        listedVendidos.forEach(vendido -> vendido.setVenta(venta));
        DataStore.getVentas().save(venta);
        DataStore.getVendidos().save(venta.getVendidos());
    }
}
