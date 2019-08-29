package app.control;

import app.model.Identifiable;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.GridPane;

public abstract class GridControl<T extends Identifiable> extends GridPane {

    private MenuButton fxButtonMenu;

    void setFxButtonMenu(MenuButton fxButtonMenu) {
        this.fxButtonMenu = fxButtonMenu;
    }

    public abstract void updateEditee(T editee);

    public abstract T buildNew();

    public abstract void setFields(T editee);

    public abstract boolean validFields();
}
