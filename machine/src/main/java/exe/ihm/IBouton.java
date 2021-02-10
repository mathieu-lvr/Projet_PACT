package exe.ihm;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JButton;

public class IBouton extends JButton implements MouseListener {

	private Fenetre fenetre;
	private int inset = 5;
	private Color buttonColor = Color.cyan.brighter().brighter().brighter().brighter();

	public IBouton() {
		super("Identifiants");
		this.setPreferredSize(new Dimension(300, 300));
		this.setForeground(Color.white);
		this.setFont(new Font("Impact", Font.PLAIN, 50));
		setContentAreaFilled(false);
		this.addMouseListener(this);
	}

	public void setFenetre(Fenetre fen) {
		this.fenetre = fen;
	}

	public Fenetre getFenetre() {
		return this.fenetre;
	}

	protected void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int vWidth = getWidth();
		int vHeight = getHeight();

		// Calculate the size of the button
		int vButtonHeight = vHeight - (inset * 2);
		int vButtonWidth = vWidth - (inset * 2);
		int vArcSize = vButtonHeight / 4;

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Create the gradient paint for the first layer of the button
		g2d.setColor(Color.cyan.darker().darker());

		// Paint the first layer of the button
		g2d.fillRoundRect(inset, inset, vButtonWidth, vButtonHeight, vArcSize, vArcSize);

		// Calulate the size of the second layer of the button
		int vHighlightInset = 2;
		int vButtonHighlightHeight = vButtonHeight - (vHighlightInset * 2);
		int vButtonHighlightWidth = vButtonWidth - (vHighlightInset * 2);
		int vHighlightArcSize = vButtonHighlightHeight;

		g2d.fillRoundRect(inset + vHighlightInset, inset + vHighlightInset, vButtonHighlightWidth,
				vButtonHighlightHeight, vHighlightArcSize, vHighlightArcSize);
		RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(inset, inset, vButtonWidth, vButtonHeight, vArcSize,
				vArcSize);
		g2d.clip(r2d);
		g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		super.paintComponent(g);

		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
	}

	Shape shape;

	public boolean contains(int x, int y) {
		int vWidth = getWidth();
		int vHeight = getHeight();

		// Calculate the size of the button
		int vButtonHeight = vHeight - (inset * 2);
		int vButtonWidth = vWidth - (inset * 2);
		int vArcSize = vButtonHeight / 4;
		// If the button has changed size, make a new shape object.
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new RoundRectangle2D.Float(inset, inset, vButtonWidth, vButtonHeight, vArcSize, vArcSize);
		}
		return shape.contains(x, y);
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
		fenetre.layout(5);
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
