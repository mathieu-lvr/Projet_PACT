package exe.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class Fenetre extends JFrame {
	private String mode = null;

	public Fenetre() {
		super("Recocup");
		layout(0);
		exe.arduino.SimpleWrite.initialize() ;
		pack();
		setVisible(true);
		this.setExtendedState(MAXIMIZED_BOTH);
	}

	public void setMode(String s) {
		this.mode = s;
	}

	public String getMode() {
		return this.mode;
	}

	public void layout(int step) {
		switch (step) {
			// Cas Menu d'accueil.
			case (0): {
				JPanel container = new JPanel(new GridBagLayout());

				GridBagConstraints c = new GridBagConstraints();

				c.anchor = GridBagConstraints.CENTER;
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				DBouton bd = new DBouton();
				RBouton br = new RBouton();
				JPanel boutonPane = new JPanel();
				// Border border = new Border();
				// boutonPane.setBorder(border);
				bd.setFenetre(this);
				br.setFenetre(this);
				boutonPane.add(bd, BorderLayout.EAST);
				boutonPane.add(br, BorderLayout.WEST);
				boutonPane.setBorder(new LineBorder(Color.cyan.darker().darker(), 4, true));
				container.add(boutonPane, c);
				GridBagConstraints d = new GridBagConstraints();
				d.gridy = 1;
				d.gridx = 0;
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);

				container.add(bh, d);
				this.setContentPane(container);

				break;
			}
			// Cas Menu d'accueil -> D�pot
			case (1): {
				JPanel container = new JPanel(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.anchor = GridBagConstraints.CENTER;
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				QBouton bq = new QBouton();
				IBouton bi = new IBouton();
				JPanel boutonPane = new JPanel();
				bq.setFenetre(this);
				bi.setFenetre(this);
				boutonPane.add(bq, BorderLayout.EAST);
				boutonPane.add(bi, BorderLayout.WEST);
				boutonPane.setBorder(new LineBorder(Color.cyan.darker().darker(), 4, true));

				container.add(boutonPane);
				GridBagConstraints d = new GridBagConstraints();
				d.gridy = 1;
				d.gridx = 0;
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);

				container.add(bh, d);

				this.setContentPane(container);
				break;

			}
			// Cas Menu d'accueil -> Retrait
			case (2): {
				JPanel container = new JPanel(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.anchor = GridBagConstraints.CENTER;
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				QBouton bq = new QBouton();
				IBouton bi = new IBouton();
				JPanel boutonPane = new JPanel();
				bq.setFenetre(this);
				bi.setFenetre(this);
				boutonPane.add(bq, BorderLayout.EAST);
				boutonPane.add(bi, BorderLayout.WEST);
				boutonPane.setBorder(new LineBorder(Color.cyan.darker().darker(), 4, true));

				container.add(boutonPane);
				GridBagConstraints d = new GridBagConstraints();
				d.gridy = 1;
				d.gridx = 0;
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				container.add(bh, d);
				this.setContentPane(container);
				break;

			}
			// Cas Menu d'accueil -> D�pot -> QR Code
			case (3): {
				JPanel container = new JPanel();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				QRScreen qr = new QRScreen();
				qr.setIsFirst(true);
				qr.setMode("depot");
				qr.repaint();
				SuccessButton bs = new SuccessButton();
				bs.setFenetre(this);
				FailButton bf = new FailButton();
				bf.setFenetre(this);
				bs.setMode("depot");
				bf.setMode("depot");
				container.add(bs);
				this.setContentPane(qr);
				this.getContentPane().add(bs);
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				this.getContentPane().add(bh);
				this.getContentPane().add(bf);

				break;

			}
			// Cas Menu d'accueil -> Retrait -> QR Code
			case (4): {
				JPanel container = new JPanel();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				QRScreen qr = new QRScreen();
				qr.setIsFirst(true);
				qr.setMode("retrait");
				qr.repaint();
				SuccessButton bs = new SuccessButton();
				bs.setFenetre(this);
				FailButton bf = new FailButton();
				bf.setFenetre(this);
				bs.setMode("retrait");
				bf.setMode("retrait");
				container.add(bs);
				this.setContentPane(qr);
				this.getContentPane().add(bs);
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				this.getContentPane().add(bh);
				this.getContentPane().add(bf);

				break;
			}
			// Cas Menu d'accueil -> D�pot -> Identifiants
			case (5): {
				JPanel container = new JPanel(new GridBagLayout());
				JPanel top = new JPanel(new GridBagLayout());
				GridBagConstraints c = new GridBagConstraints();
				c.anchor = GridBagConstraints.CENTER;
				JTextField jtf1 = new JTextField("Ton p'tit nom?");
				JLabel label = new JLabel("Identifiants : ");
				label.setFont(new Font("Impact", Font.PLAIN, 60));
				label.setForeground(Color.cyan.darker().darker());
				Font police = new Font("Impact", Font.ITALIC, 50);
				jtf1.setFont(police);
				jtf1.setPreferredSize(new Dimension(320, 60));
				jtf1.setForeground(Color.CYAN.darker().darker());
				top.add(label);
				c.gridy = 0;
				c.gridx = 1;
				top.add(jtf1, c);

				JPasswordField jtf2 = new JPasswordField("");
				JLabel label2 = new JLabel("Mot de passe : ");
				label2.setFont(new Font("Impact", Font.PLAIN, 60));
				label2.setForeground(Color.cyan.darker().darker());
				jtf2.setFont(police);
				jtf2.setPreferredSize(new Dimension(320, 60));
				jtf2.setForeground(Color.CYAN.darker().darker());
				// JPanel top = new JPanel();
				c.gridy = 1;
				c.gridx = 0;
				top.add(label2, c);
				c.gridy = 1;
				c.gridx = 1;
				top.add(jtf2, c);

				container.add(top);

				ConfirmButton bc = new ConfirmButton(jtf1, jtf2);
				bc.setFenetre(this);
				container.add(bc);

				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				container.add(bh);
				this.setContentPane(container);
				break;
			}
			// Cas Menu d'accueil -> D�pot -> QR Code + r�ussi
			case (6): {
				JPanel container = new JPanel();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				QRScreen qr = new QRScreen();
				qr.setIsFirst(false);
				qr.setMode("depot");
				qr.setSuccess(true);
				qr.repaint();
				this.setContentPane(qr);
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				this.getContentPane().add(bh);

				break;
			}
			// Cas Menu d'accueil -> D�pot/Retrait -> QR Code + rat�
			case (7): {
				JPanel container = new JPanel();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				QRScreen qr = new QRScreen();
				qr.setIsFirst(false);
				qr.setMode("depot");
				qr.setSuccess(false);
				qr.repaint();
				this.setContentPane(qr);
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				this.getContentPane().add(bh);

				break;
			}
			// Cas Menu d'accueil -> Retrait -> QR Code + r�ussi
			case (8): {
				JPanel container = new JPanel();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				QRScreen qr = new QRScreen();
				qr.setIsFirst(false);
				qr.setMode("retrait");
				qr.setSuccess(true);
				qr.repaint();
				this.setContentPane(qr);
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				this.getContentPane().add(bh);

				break;
			}
			case (9): {
				JPanel container = new JPanel();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				IDScreen idScreen = new IDScreen();
				//idScreen.setMode("depot");
				idScreen.setSuccess(false);
				idScreen.repaint();
				this.setContentPane(idScreen);
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				this.getContentPane().add(bh);

				break;
			}
			// Cas Menu d'accueil -> Retrait -> ID + reussi
			case (10): {
				JPanel container = new JPanel();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				IDScreen idScreen = new IDScreen();
				idScreen.setMode("retrait");
				this.setMode("retrait");
				idScreen.setSuccess(true);
				idScreen.repaint();
				this.setContentPane(idScreen);
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				this.getContentPane().add(bh);

				break;
			}
			// Cas Menu d'accueil -> Depot -> ID + reussi
			case (11): {
				JPanel container = new JPanel();
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				int screenHeight = screenSize.height;
				int screenWidth = screenSize.width;
				container.setPreferredSize(new Dimension(screenWidth, screenHeight));
				IDScreen idScreen = new IDScreen();
				idScreen.setMode("depot");
				this.setMode("depot");
				idScreen.setSuccess(true);
				idScreen.repaint();
				this.setContentPane(idScreen);
				HomeButton bh = new HomeButton();
				bh.setFenetre(this);
				this.getContentPane().add(bh);

				break;
			}
			// Cas Menu d'accueil -> Retrait -> ID + reussi


		}

	}
}
