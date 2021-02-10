
package exe.ihm;

import exe.arduino.SimpleWrite;
import exe.network.NetworkInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ConfirmButton extends JButton implements MouseListener {
	private exe.ihm.Fenetre fenetre;
	private int inset = 5;
	private Color buttonColor = Color.cyan.brighter().brighter().brighter().brighter();
	JTextField idField = null;
	JPasswordField passwordField = null;

	public ConfirmButton(JTextField idField, JPasswordField passwordField) {
		super("");
		this.setPreferredSize(new Dimension(100, 100));
		this.setForeground(Color.white);
		// this.setBackground(Color.CYAN.darker());
		setContentAreaFilled(false);
		this.addMouseListener(this);
		this.setBorderPainted(false);
		this.idField = idField;
		this.passwordField = passwordField;
	}

	protected final void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		Image img = null;
		try {
			//img = ImageIO.read(new File("machine/exe/ihm/images/confirmBouton.png"));
			img = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("confirmBouton.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erreur dans l'affichage de l'image du confirmBouton") ;
			String workingDir = System.getProperty("user.dir");
			System.out.println("Current working directory : " + workingDir);
		}
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public void setFenetre(exe.ihm.Fenetre fen) {
		this.fenetre = fen;
	}

	public exe.ihm.Fenetre getFenetre() {
		return this.fenetre;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override

		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

			NetworkInterface networkInterface = new NetworkInterface();
			int nb = networkInterface.logUser(this.idField.getText(),this.passwordField.getText()) ;
			// nb = -1 si l'utilisateur n'est pas dans la base de données
			if (nb==-1){
				fenetre.getContentPane().removeAll();
				fenetre.getContentPane().validate();
				fenetre.layout(9) ;
				fenetre.setMode("depot");
				fenetre.getContentPane().revalidate();
			}
			// Sinon c'est qu'on a trouvé l'utilisateur dans la BDD
			else {
				if (this.fenetre.getMode()=="depot"){
					fenetre.getContentPane().removeAll();
					fenetre.getContentPane().validate();
					try {
						SimpleWrite.depot();
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					fenetre.layout(11) ;
					fenetre.setMode("depot");
					fenetre.getContentPane().revalidate();
				}
				if (this.fenetre.getMode()=="retrait"){
					fenetre.getContentPane().removeAll();
					fenetre.getContentPane().validate();
					try {
						SimpleWrite.retrait();
					} catch (IOException ex) {
						ex.printStackTrace();
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					fenetre.layout(10) ;
					fenetre.setMode("depot");
					fenetre.getContentPane().revalidate();
				}

			}
		}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
