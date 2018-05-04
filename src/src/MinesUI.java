import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Panel;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Path2D;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.event.MouseAdapter;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JRadioButtonMenuItem;


public class MinesUI extends Board{
	private int cells;
	private JFrame frame;
	private JPanel panel;
	private Color color = Color.BLACK;
	private Graphics graphics;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinesUI window = new MinesUI(3);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MinesUI(int cell) {
		frame = new JFrame();

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenuItem mntmEasy = new JMenuItem("Easy");
		mntmEasy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				panel.removeAll();
				initialize(3);
			}
		});
		mnGame.add(mntmEasy);

		JMenuItem mntmMedium = new JMenuItem("Medium");
		mntmMedium.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				panel.removeAll();
				initialize(4);
			}
		});
		mnGame.add(mntmMedium);

		JMenuItem mntmHard = new JMenuItem("Hard");
		mntmHard.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				panel.removeAll();
				initialize(5);
			}
		});
		mnGame.add(mntmHard);

		JMenuItem mntmCustom = new JMenuItem("Custom");
		mntmCustom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// Promts user for cell size (always squared)
				panel.removeAll();
				// initialize(c);
			}
		});
		mnGame.add(mntmCustom);

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmRules = new JMenuItem("Rules");
		mntmRules.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Help().setVisible(true);
			}
		});
		mnHelp.add(mntmRules);

		initialize(cell);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int c) {
		cells = c;
		frame.setBounds(cells * 20, cells * 20, cells * 85, cells * 35 + 50); // Save:
																				// cells*85,
		panel=new JPanel();	
		panel.setLayout(new GridLayout());
		// cells*35
		for (int i = 0; i < cells; i++) {
			for (int j = 0; j < cells; j++) {
				int x = (30 * cells) + (j * 50) - (i * 25);
				int y = i * 25;
				JButton b = new Buttons(x,y);
				//JButton b = new JButton();
				b.setLayout(null);
				panel.add(b);
				frame.setContentPane(panel);
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.out.println(x+" "+y);
					}
				});
				b.setVisible(true);
			}
		}
		
		frame.repaint();
		frame.revalidate();
	}

	/**
	 * 
	 * @param c
	 *            -color to be changed
	 */
	public void setColor(Color c) {
		color = c;
	}
}
