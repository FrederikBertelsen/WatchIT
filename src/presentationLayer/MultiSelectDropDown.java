package presentationLayer;

import javax.swing.*;
import java.util.ArrayList;

public class MultiSelectDropDown extends JMenu {
    private String name;
    private String[] itemNames;


    public MultiSelectDropDown(String name, String[] itemNames) {
        super(name);

        this.name = name;
        this.itemNames = itemNames;

        for (String genre : itemNames) {
            JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(genre);
            add(menuItem);
        }
    }

    public ArrayList<String> getSelectedStrings() {
        ArrayList<String> selectedItems = new ArrayList<>();
        // for each item in this menu
        for (int i = 0; i < getItemCount(); i++) {
            JMenuItem menuItem = getItem(i);
            // if the item is selected, add it's text to the output
            if (menuItem.getModel().isSelected()) {
                selectedItems.add(menuItem.getText());
            }
        }

        return selectedItems;
    }

    public void resetSelected() {
        // for each item in this menu
        for (int i = 0; i < getItemCount(); i++) {
            JMenuItem menuItem = getItem(i);
            // deselect it
            menuItem.getModel().setSelected(false);
        }
    }
}
