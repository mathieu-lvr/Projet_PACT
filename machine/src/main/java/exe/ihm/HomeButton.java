package exe.ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.*;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;

public class HomeButton extends JButton implements MouseListener {
	private Fenetre fenetre;
	private int inset = 5;
	private Color buttonColor = Color.cyan.brighter().brighter().brighter().brighter();

	public HomeButton() {
		super("");
		this.setPreferredSize(new Dimension(100, 100));
		this.setForeground(Color.white);
		// this.setBackground(Color.CYAN.darker());
		setContentAreaFilled(false);
		this.addMouseListener(this);
		this.setBorderPainted(false);
	}

	protected final void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		String workingDir = System.getProperty("user.dir");
	    System.out.println("Current working directory : " + workingDir);
	    Image img = null;
		try {
			workingDir = System.getProperty("user.dir");
		    System.out.println("Current working directory : " + workingDir);
			img = ImageIO.read(ClassLoader.getSystemClassLoader().getResource("homeBouton.png"));
			System.out.println("Ouverture de l'image r�ussie");
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Erreur dans l'ouverture de l'image (HomeBouton)");

			workingDir = System.getProperty("user.dir");
		    System.out.println("Current working directory : " + workingDir);
			e.printStackTrace();
		
		}
		g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}

	public void setFenetre(Fenetre fen) {
		this.fenetre = fen;
	}

	public Fenetre getFenetre() {
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
		fenetre.getContentPane().removeAll();
		fenetre.getContentPane().validate();
		fenetre.layout(0);
		fenetre.getContentPane().revalidate();
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
