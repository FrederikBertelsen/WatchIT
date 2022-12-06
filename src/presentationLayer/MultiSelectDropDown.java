package presentationLayer;

import javax.swing.*;
import java.util.ArrayList;

public class MultiSelectDropDown extends SelectDropDown {



    public MultiSelectDropDown(String name, String[] itemNames) {
        super(name, itemNames);

        menuItems = new JMenuItem[itemNames.length];
        for (int i = 0; i < itemNames.length; i++) {
            JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(itemNames[i]);
            menuItems[i] = menuItem;
            add(menuItem);
        }
    }

    public void resetSelected() {
        // for each item in this menu
        for (int i = 0; i < menuItems.length; i++) {
            JMenuItem menuItem = menuItems[i];
            // deselect it
            menuItem.getModel().setSelected(false);
        }
    }
}
