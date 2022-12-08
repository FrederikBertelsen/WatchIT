package presentationLayer;

import javax.swing.*;
import java.util.ArrayList;

public abstract class SelectDropDown extends JMenu {
    protected String name;
    protected String[] itemNames;
    protected JMenuItem[] menuItems;

    public SelectDropDown(String name, String[] itemNames) {
        super(name);
        this.name = name;
        this.itemNames = itemNames;
    }

    public void resetSelected() {
        // for each item in this menu
        for (JMenuItem menuItem : menuItems) {
            // deselect it
            menuItem.getModel().setSelected(false);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public String[] getItemNames() {
        return itemNames;
    }
}
