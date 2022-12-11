package presentationLayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashSet;

// Denne klasse udvider SelectDropDown klassen, for at lave en dropdown-menu hvor flere valgmuligheder kan være valgt på samme tid.
public class MultiSelectDropDown extends SelectDropDown {
    // Konstruktøren tager et navn og en liste af navne på menu-valgmuligheder som argumenter
    public MultiSelectDropDown(String name, String[] itemNames) {
        // Kalder SelectDropDown's konstruktøren med navnet og menu-valgmuligheder
        super(name, itemNames);

        // Opret en liste af JMenuItem objekter med samme længde som liste af valgmuligheder
        menuItems = new JMenuItem[itemNames.length];
        // For hvert valgmulighed
        for (int i = 0; i < itemNames.length; i++) {
            // Opret et nyt JCheckBoxMenuItem med navnet på dette objekt
            JCheckBoxMenuItem menuItem = new JCheckBoxMenuItem(itemNames[i]);
            // Tilføj dette objekt til listen af menu-valgmuligheder
            menuItems[i] = menuItem;

            // Tilføj dette object til menuen
            add(menuItem);
        }
    }

    // Denne metode returnerer navnet på de valgte menu-valgmuligheder
    public HashSet<String> getSelected() {
        HashSet<String> selectedItems = new HashSet<>();
        // For hvert object i listen af menu-valgmuligheder
        for (JMenuItem menuItem : menuItems) {
            // Hvis dette object er valgt
            if (menuItem.getModel().isSelected()) {
                // tilføj valgmulighedens navn til output listen
                selectedItems.add(menuItem.getText());
            }
        }
        // returner listen af valgte valgmuligheder
        return selectedItems;
    }
}
