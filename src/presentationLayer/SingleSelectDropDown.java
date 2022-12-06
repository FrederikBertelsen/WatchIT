package presentationLayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class SingleSelectDropDown extends JMenu {
    private String name;
    private String[] itemNames;

    private JRadioButtonMenuItem[] menuItems;


    public SingleSelectDropDown(String name, String[] itemNames) {
        super(name);

        this.name = name;
        this.itemNames = itemNames;

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

    public String getSelectedString() {
        return getSelectedString();
    }

    public void clearCheckBoxes(JRadioButtonMenuItem selectedMenuItem){
        for (JRadioButtonMenuItem menuItem : menuItems) {
            if (menuItem != selectedMenuItem) {
                menuItem.getModel().setSelected(false);
            }
        }
    }
}
