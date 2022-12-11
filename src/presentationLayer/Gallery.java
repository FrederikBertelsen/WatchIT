package presentationLayer;

import domainLayer.dataStructure.Media;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/*
 Klassen Gallery repræsenterer en brugergrænseflade-komponent, der viser en række,
 af "GalleryPanel"-objekter i et gitterlayout med fem kolonner.
 Det bliver brugt til at vise film og serierne fra data.
 */
 public class Gallery extends JPanel {
     // Dette er en array, der indeholder de nuværende instanser af GalleryPanel
     public GalleryPanel[] currentGalleryPanels;

     // Konstruktøren opretter et nyt Gallery-objekt med en GridLayout med 0 rækker og 5 kolonner
     public Gallery() {
         super(new GridLayout(0, 5));

         // Sæt baggrundsfarven til grå
         setBackground(Color.gray);

         // Opret en tom array til GalleryPanel-objekter
         currentGalleryPanels = new GalleryPanel[0];
     }

     // Denne metode opdaterer GalleryPanel-objekterne i den nuværende array baseret på de givne mediefiler
     public void updatePanels(ArrayList<Media> medias){
         // Kald resetCurrentPanels-metoden med antallet af mediefiler som argument
         resetCurrentPanels(medias.size());

         // For hver mediefil i listen over mediefiler
         for (int i = 0; i < medias.size(); i++) {
             // Opret et nyt GalleryPanel-objekt med den givne mediefil
             GalleryPanel newGalleryPanel = new GalleryPanel(medias.get(i));
             // Tilføj GalleryPanel-objektet til arrayet med de nuværende GalleryPanel-objekter
             currentGalleryPanels[i] = newGalleryPanel;
             // Tilføj GalleryPanel-objektet til dette JPanel
             add(newGalleryPanel);
         }
     }
     // denne metode fjerner alle de GalleryPanel-objekter der er i arrayet,
     // og sætter arrayets længde til den nye mængde af medier der skal være i brugergrænsefladen.
     private void resetCurrentPanels(int newLength){
         // fjern alle de GalleryPanel-objekter der er i arrayet
         for(GalleryPanel galleryPanel : currentGalleryPanels){
             remove(galleryPanel);
         }
         // sæt længden til den nye mængde af medier
         currentGalleryPanels = new GalleryPanel[newLength];
     }
 }

