package exe.ihm;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import static javax.imageio.ImageIO.read;

public class QRScreen extends JPanel {
	String mode = null;
	boolean success = false;
	boolean isFirst = true;

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

	public boolean getIsFirst() {
		return this.isFirst;
	}

	public void setIsFirst(boolean s) {
		this.isFirst = s;
	}

	@Override
	public final void paintComponent(Graphics g) {
		if (isFirst) {
			Image img = null;
			try {
				img = read(ClassLoader.getSystemClassLoader().getResource("ScannerQRCode.png"));
				//img = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("scan.png"));

				System.out.println("L�, on est cens� avoir une image affich�e (scanner)");

				g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
			} catch (IOException e) {

				String workingDir = System.getProperty("user.dir");
				System.out.println("Current working directory : " + workingDir);
				System.out.println("test test");
				Fenetre fenetre = null;
				JOptionPane.showMessageDialog(fenetre, "Erreur dans l'ouverture de l'image", "Erreur",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			Image img = null;
			if (success) {
				switch (mode) {
					case ("retrait"):
						try {
							//Image img = ImageIO.read(new File("machine/exe/images/QRCodevalide(retrait).png"));
							img = read(ClassLoader.getSystemClassLoader().getResource("QRCodevalide(retrait).png"));
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
							//Image img = ImageIO.read(new File("machine/exe/images/QRCodevalide(depot).png"));
							img = read(ClassLoader.getSystemClassLoader().getResource("QRCodevalide(depot).png"));

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
					//Image img = ImageIO.read(new File("machine/exe/images/QRCodenonvalide.png"));
					img = read(ClassLoader.getSystemClassLoader().getResource("QRCodenonvalide.png"));

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
}