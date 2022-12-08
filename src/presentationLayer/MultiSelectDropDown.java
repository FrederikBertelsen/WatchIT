package presentationLayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

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

    public HashSet<String> getSelected() {
        HashSet<String> selectedItems = new HashSet<>();
        // for each item in this menu
        for (JMenuItem menuItem : menuItems) {
            // if the item is selected, add it's text to the output
            if (menuItem.getModel().isSelected()) {
                selectedItems.add(menuItem.getText());
            }
        }

        return selectedItems;
    }
}
