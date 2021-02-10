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

import javax.imageio.ImageIO;
import javax.swing.JButton;

public class FailButton extends JButton implements MouseListener {
	private Fenetre fenetre;
	private int inset = 5;
	private Color buttonColor = Color.cyan.brighter().brighter().brighter().brighter();
	String mode = null;


	public FailButton() {
		super("FAIL");
		this.setPreferredSize(new Dimension(100, 100));
		this.setForeground(Color.white);
		// this.setBackground(Color.CYAN.darker());
		this.setForeground(Color.black);

		setContentAreaFilled(false);
		this.addMouseListener(this);
		this.setBorderPainted(false);
	}



	public void setFenetre(Fenetre fen) {
		this.fenetre = fen;
	}

	public Fenetre getFenetre() {
		return this.fenetre;
	}
	public void setMode(String s) {
		this.mode = s ; 
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
		fenetre.layout(7);
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
