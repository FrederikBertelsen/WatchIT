package presentationLayer;

import javax.swing.*;
import java.awt.*;

// Denne klasse gør det nemt at skabe et dialog vindue så brugeren kan få fejl og advarsler.
// Den er abstract, da klasse bare er en holder til nogle statiske funktioner.
public abstract class DialogCreator {
    public static void createExceptionDialog(String message){
        JDialog dialog = createJDialog(message);
        dialog.setTitle("FEJL");
        dialog.getContentPane().setBackground(Color.red);
    }

    public static void createWarningDialog(String message){
        JDialog dialog = createJDialog(message);
        dialog.setTitle("ADVARSEL");
        dialog.getContentPane().setBackground(Color.yellow);

    }

    private static JDialog createJDialog(String message){
        JDialog dialog = new JDialog();

        dialog.setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
//        dialog.getContentPane().setBackground(Color.red);


        JLabel errorLabel = new JLabel("      " + message + "      ");
        errorLabel.setFont(new Font("", Font.PLAIN,20));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialog.getContentPane().add(errorLabel);

        JButton closeButton = new JButton("       OK       ");
        closeButton.setFont(new Font("", Font.BOLD,20));
        closeButton.addActionListener(e -> {
            dialog.dispose();
        });
        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialog.add(closeButton);

        dialog.setAlwaysOnTop(true);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return dialog;
    }

}
