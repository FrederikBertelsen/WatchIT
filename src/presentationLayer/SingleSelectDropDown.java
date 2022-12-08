package presentationLayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class SingleSelectDropDown extends SelectDropDown {
    public SingleSelectDropDown(String name, String[] itemNames) {
        super(name, itemNames);

        menuItems = new JRadioButtonMenuItem[itemNames.length];
        for (int i = 0; i < itemNames.length; i++) {
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(itemNames[i]);
            menuItems[i] = menuItem;

            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    clearCheckBoxes(menuItem);
                }
            });

            add(menuItem);
        }
    }

    public void clearCheckBoxes(JRadioButtonMenuItem selectedMenuItem) {
        for (JMenuItem menuItem : menuItems) {
            if (menuItem != selectedMenuItem) {
                menuItem.getModel().setSelected(false);
            }
        }
    }

    public String getSelected() {
        // for each item in this menu
        for (JMenuItem menuItem : menuItems) {
            // if the item is selected, add it's text to the output
            if (menuItem.getModel().isSelected()) {
                return menuItem.getText();
            }
        }
        return "";
    }
}
