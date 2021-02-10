package exe.ihm;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static javax.imageio.ImageIO.read;

public class IDScreen extends JPanel {
    String mode = null;
    boolean success = false;
    boolean isFirst = false;

    public String getMode() {
        return this.mode;
    }

    public void setMode(String s) {
        if (s.equals("depot")) {
            this.mode = s;
        } else if (s.equals("retrait")) {

            this.mode = s;
        } else {
            return;
        }
    }

    public boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(boolean s) {
        this.success = s;
    }

    @Override
    public final void paintComponent(Graphics g) {
        Image img = null;
        if (success) {
            switch (mode) {
                case ("retrait"):
                    try {
                        //Image img = ImageIO.read(new File("machine/exe/images/IDValideretrait.png"));
                        img = read(ClassLoader.getSystemClassLoader().getResource("IDValideretrait.png"));
                        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                    } catch (IOException e) {
                        System.out.println("Erreur dans l'ouverture de l'image");
                        Fenetre fenetre = null;
                        JOptionPane.showMessageDialog(fenetre, "Erreur dans l'ouverture de l'image", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case ("depot"):
                    try {
                        //Image img = ImageIO.read(new File("machine/exe/images/IDValidedepot.png"));
                        img = read(ClassLoader.getSystemClassLoader().getResource("IDValidedepot.png"));

                        g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
                    } catch (IOException e) {
                        System.out.println("Erreur dans l'ouverture de l'image");
                        Fenetre fenetre = null;
                        JOptionPane.showMessageDialog(fenetre, "Erreur dans l'ouverture de l'image", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    break;

            }

        } else {
            try {
                //Image img = ImageIO.read(new File("machine/exe/images/IDNonValide.png"));
                img = read(ClassLoader.getSystemClassLoader().getResource("IDNonValide.png"));

                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            } catch (IOException e) {
                System.out.println("Erreur dans l'ouverture de l'image");
                Fenetre fenetre = null;
                JOptionPane.showMessageDialog(fenetre, "Erreur dans l'ouverture de l'image", "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}

