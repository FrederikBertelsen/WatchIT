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
     // Konstruktøren opretter et nyt Gallery-objekt med en GridLayout med 0 rækker og 5 kolonner
     public Gallery() {
         super(new GridLayout(0, 5));

         // Sæt baggrundsfarven til grå
         setBackground(Color.LIGHT_GRAY);
     }

     // Denne metode opdaterer GalleryPanel-objekterne i den nuværende array baseret på de givne mediefiler
     public void updatePanels(ArrayList<Media> mediaList){
         setVisible(false);
         // Kald resetCurrentPanels-metoden med antallet af mediefiler som argument
         clearCurrentPanels(mediaList.size());

         if (mediaList.size() == 0){
             DialogCreator.createWarningDialog("Ingen medier passer til din søgning.");
             return;
         }

         // For hver mediefil i listen over mediefiler
         for (Media media : mediaList) {
             // Opret et nyt GalleryPanel-objekt med den givne mediefil
             GalleryPanel newGalleryPanel = new GalleryPanel(media);
             // Tilføj GalleryPanel-objektet til galleriet
             add(newGalleryPanel);
         }
         setVisible(true);
     }
     // denne metode fjerner alle de GalleryPanel-objekter der er i arrayet,
     // og sætter arrayets længde til den nye mængde af medier der skal være i brugergrænsefladen.
     private void clearCurrentPanels(int newLength){
         // fjern alle de GalleryPanel-objekter der er i galleriet
         for(Component galleryPanel : getComponents()){
             remove(galleryPanel);
         }
     }
 }

