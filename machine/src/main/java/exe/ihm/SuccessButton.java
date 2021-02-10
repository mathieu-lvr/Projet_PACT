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

public class SuccessButton extends JButton implements MouseListener {
	private Fenetre fenetre;
	private int inset = 5;
	private Color buttonColor = Color.cyan.brighter().brighter().brighter().brighter();
	String mode = null;
	public SuccessButton() {
		super("SUCCESS");
		this.setPreferredSize(new Dimension(100, 100));
		this.setForeground(Color.white);
		// this.setBackground(Color.CYAN.darker());
		this.setForeground(Color.black);
		setContentAreaFilled(false);
		this.addMouseListener(this);
		this.setBorderPainted(false);
	}

	public void setMode(String s) {
		this.mode = s;
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
		if (fenetre.getMode() == "depot") {
			fenetre.layout(6);
			try {
				SimpleWrite.depot();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		} else {
			fenetre.layout(8);
			try {
				SimpleWrite.retrait();
				NetworkInterface net = new NetworkInterface();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
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
