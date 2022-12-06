package presentationLayer;

import javax.swing.*;
import java.util.ArrayList;

public class MultiSelectDropDown extends JMenu {
    private String name;
    private String[] itemNames;
    private JCheckBoxMenuItem[] menuItems;



    public MultiSelectDropDown(String name, String[] itemNames) {
        super(name);

        this.name = name;
        this.itemNames = itemNames;

        menuItems = new JCheckBoxMenuItem[itemNames.length];
        for (int i = 0; i < itemNames.length; i++) {
            JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(itemNames[i]);
            menuItems[i] = menuItem;
            add(menuItem);
        }
    }

    public ArrayList<String> getSelectedStrings() {
        ArrayList<String> selectedItems = new ArrayList<>();
        // for each item in this menu
        for (int i = 0; i < menuItems.length; i++) {
            JMenuItem menuItem = menuItems[i];
            // if the item is selected, add it's text to the output
            if (menuItem.getModel().isSelected()) {
                selectedItems.add(menuItem.getText());
            }
        }

        return selectedItems;
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
