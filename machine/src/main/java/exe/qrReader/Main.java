package exe.qrReader;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.google.zxing.WriterException;

public class Main {

	public static void main(String[] args) {
		try {
            byte[] test = exe.qrReader.QrCodeGenerator.getQRCodeImage("J'ai h�sit� � me suicider mais finalement �a marche donc je suis content.", 350, 350);
            BufferedImage img = ImageIO.read(new ByteArrayInputStream(test));
            JFrame frame = new JFrame("frame") ;
            JPanel panel = new JPanel() ;
            JLabel lab = new JLabel(new ImageIcon(test));
            panel.add(lab);
            frame.setContentPane(panel);
            frame.setSize(350, 350);
            frame.setLocation(300, 200);
            frame.setVisible(true);

            System.out.println("Le QR Code devrait �tre qqpart");
            try {
            	// C://Users/nguye/Documents/T�l�com/1A/PACT/Module IHM/pact35/machine/exe/qrReader/
            	File outputfile = new File("resources/tokenID.png");
                
            	ImageIO.write(img, "png", outputfile);
                System.out.println("Le fichier image a �t� modifi�.");

            } catch (IOException e) {
                System.out.println("Le fichier image n'a pas �t� modifi�.");
            }
            

        } catch (WriterException e) {
            System.out.println("Could not generate QR Code, WriterException :: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Could not generate QR Code, IOException :: " + e.getMessage());
        }
			
			new exe.qrReader.WebcamQRCodeExample2();
			
		}
} 