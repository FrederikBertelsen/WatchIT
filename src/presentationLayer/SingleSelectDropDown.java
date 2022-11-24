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

    private ArrayList<JRadioButtonMenuItem> menuItems;


    public SingleSelectDropDown(String name, String[] itemNames) {
        super(name);

        this.name = name;
        this.itemNames = itemNames;

        menuItems = new ArrayList<>();
        for (String genre : itemNames) {
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(genre);
            menuItems.add(menuItem);

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
