package dataLayer;
//de to første er blot til at displaye billidet
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class TestClass1 {
    public static void main(String[] args){
        /*loadTest("data/serier.txt");
        System.out.println();
        loadTest("data/film.txt");
        System.out.println();
        */
        getImageTest("data/filmplakater","The Exorcist");
        getImageTest("data/serieforsider","Twin Peaks");
    }
    public static void loadTest(String path){
        DataHandler dataHandler = new DataHandlerImpl(path);
        ArrayList<String> datas;
        try {
            datas = dataHandler.load();
        } catch (Exception e){
            datas = new ArrayList<>();
        }
        for (String data : datas) {
            System.out.println(data);
        }
    }

    public static void getImageTest(String imageFolderPath,String mediaName){
        DataHandler dataHandler = new DataHandlerImpl("data/serier.txt", imageFolderPath);
        try{
            BufferedImage img = dataHandler.getImage(mediaName);
            showLoadedImage(img);
        } catch (IOException e){
            System.out.println("lol u messed up dude");
        }
    }

    public static void showLoadedImage(BufferedImage img){
        JFrame frame = new JFrame();
        frame.getContentPane().setLayout(new FlowLayout());
        frame.getContentPane().add(new JLabel(new ImageIcon(img)));
        frame.pack();
        frame.setVisible(true);
    }
}
