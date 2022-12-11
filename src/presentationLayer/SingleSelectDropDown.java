package presentationLayer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

// Denne klasse udvider SelectDropDown klassen, for at lave en dropdown-menu hvor kun en valgmulighed kan være valgt ad gangen.
public class SingleSelectDropDown extends SelectDropDown {

    // Konstruktøren tager et navn og en liste af navne på menu-valgmuligheder som argumenter
    public SingleSelectDropDown(String name, String[] itemNames) {
        // Kalder SelectDropDown's konstruktøren med navnet og menu-valgmuligheder
        super(name, itemNames);

        // Opret en liste af JRadioButtonMenuItem objekter med samme længde som liste af valgmuligheder
        menuItems = new JRadioButtonMenuItem[itemNames.length];
        // For hvert valgmulighed
        for (int i = 0; i < itemNames.length; i++) {
            // Opret et nyt JRadioButtonMenuItem med navnet på dette objekt
            JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(itemNames[i]);
            // Tilføj dette objekt til listen af menu-valgmuligheder
            menuItems[i] = menuItem;

            // Tilføj en ActionListener til dette object, så programmet kan reagere, når det bliver klikket på
            // det er så det kan fravælge alle andre valgte valgmuligheder
            menuItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Når dette object bliver valgt, kalder clearCheckBoxes-metoden med dette objekt som argument
                    clearCheckBoxes(menuItem);
                }
            });

            // Tilføj dette object til menuen
            add(menuItem);
        }
    }

    // Denne metode tager et JRadioButtonMenuItem-object som argument
    // Denne metode fravælger alle andre valgte valgmuligheder, så 2 valgmuligheder ikke kan være valgt på samme tid.
    public void clearCheckBoxes(JRadioButtonMenuItem selectedMenuItem) {
        // For hvert object i listen af menu-valmuligheder
        for (JMenuItem menuItem : menuItems) {
            // Hvis dette object ikke er det valgte objekt
            if (menuItem != selectedMenuItem) {
                // fravælg valgmuligheden
                menuItem.getModel().setSelected(false);
            }
        }
    }

    // Denne metode returnerer navnet på det valgte menu-valgmulighed
    public String getSelected() {
        // For hvert object i listen af menu-valgmuligheder
        for (JMenuItem menuItem : menuItems) {
            // Hvis dette object er valgt
            if (menuItem.getModel().isSelected()) {
                // Returner valgmulighedens navn
                return menuItem.getText();
            }
        }
        // Hvis ingen valgmuligheder er valgt, returner en tom tekststreng for at vise at ingen valgmuligheder er valgt
        return "";
    }
}