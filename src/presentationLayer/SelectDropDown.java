package presentationLayer;

import javax.swing.*;

// Dette er en abstrakt klasse, der udvider JMenu for at lave en dropdown-menu
public abstract class SelectDropDown extends JMenu {
    // Navnet på dropdown-menuen
    protected String name;
    // Navnene på valgmulighederne i dropdown-menuen
    protected String[] itemNames;
    // valgmulighedernes JMenuItem-objekter, der er i dropdown-menuen
    protected JMenuItem[] menuItems;

    // Konstruktør, der tager navnet på dropdown-menuen og navnene på dets elementer som input
    public SelectDropDown(String name, String[] itemNames) {
        // Kald JMenu's konstruktør med navnet på dropdown-menuen som input
        super(name);
        // Gem navnet på dropdown-menuen
        this.name = name;
        // Gem navnene på elementerne i dropdown-menuen
        this.itemNames = itemNames;
    }

    // Denne metode nulstiller valget af valgmuligheder i dropdown-menuen
    public void clearSelected() {

        // For hvert element i dropdown-menuen
        for (JMenuItem menuItem : menuItems) {
            // Fravælg det
            menuItem.getModel().setSelected(false);
        }
    }

    public int getItemCount(){
        return menuItems.length;
    }
}
