import java.awt.EventQueue;
import java.awt.Graphics;

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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;

import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.event.MouseAdapter;
import javax.swing.JCheckBoxMenuItem;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JRadioButtonMenuItem;

public class MinesUI extends Board implements MouseListener {

	private Board board;
	private int cells;
	private JFrame frame;
	private Polygon land[][][];
	private boolean occupied[][][];
	private JPanel flags[][][];
	private JPanel panel;
	private JPanel panelList[][][];
	private JLabel explode;
	private boolean endGame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinesUI window = new MinesUI();
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
	public MinesUI() {
		frame = new JFrame();

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);

		JMenuItem mntmEasy = new JMenuItem("Easy");
		mntmEasy.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearBoard();
				initialize(3, 310);
			}
		});
		mnGame.add(mntmEasy);

		JMenuItem mntmMedium = new JMenuItem("Medium");
		mntmMedium.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearBoard();
				initialize(4, 490);
			}
		});
		mnGame.add(mntmMedium);

		JMenuItem mntmHard = new JMenuItem("Hard");
		mntmHard.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearBoard();
				initialize(5, 720);
			}
		});
		mnGame.add(mntmHard);

		// Bonus. May remove.//
		//JMenu mnOptions = new JMenu("Options");
		//menuBar.add(mnOptions);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmRules = new JMenuItem("Rules");
		mntmRules.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Help().setVisible(true);
			}
		});
		mnHelp.add(mntmRules);

		initialize(3, 310);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(int c, int length) {
		cells = c;
		endGame = false;
		board = new Board(cells, cells, cells, cells * 2);
		frame.setBounds(cells * 20, cells * 20, cells * 55, length); // Save:
																		// cells*85,
																		// cells*105

		land = new Polygon[cells][cells][cells];
		occupied = new boolean[cells][cells][cells];
		panelList = new JPanel[cells][cells][cells];
		flags = new JPanel[cells][cells][cells];

		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				for (int i = 0; i < cells; i++) {
					for (int j = 0; j < cells; j++) {
						for (int k = 0; k < cells; k++) {
							land[i][j][k] = drawCell(g, (20 * cells) + (k * 25) - (j * 12),
									(j * 25) + (i * 25 * cells) + (i * 5) + 5);
						}
					}
				}
			}
		};
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (endGame == false) {
					for (int i = 0; i < cells; i++) {
						for (int j = 0; j < cells; j++) {
							for (int k = 0; k < cells; k++) {
								if (land[i][j][k].contains(e.getX(), e.getY())) {
									Polygon p = land[i][j][k];
									int x = i, y = j, z = k;
									if (e.getModifiers() == MouseEvent.BUTTON1_MASK && occupied[i][j][k] == false) {
										if (board.getBoard()[i][j][k].getMine() == false) {
											panel = new JPanel() {
												public void paintComponent(Graphics g) {
													int num = board.generatePointValue(x, y, z);
													try {
														BufferedImage image;
														image = ImageIO
																.read(new File("graphics\\numbers\\" + num + ".png"));
														g.drawImage(image, p.xpoints[0], p.ypoints[0], this);
													} catch (IOException h) {
														System.out.println("GUI Numbers Error");
													}
												}
											};
											occupied[i][j][k] = true;
											panelList[i][j][k] = panel;
										} else {
											panel=showMines(land[i][j][k]);
											endGame = true;
											
											occupied[i][j][k]=true;
											panelList[i][j][k] = panel;
											explode(p);
										}
									} else if (e.getModifiers() == MouseEvent.BUTTON3_MASK && occupied[i][j][k] == false
											&& flags[i][j][k] == null) {
										panel = new JPanel() {
											public void paintComponent(Graphics g) {
												try {
													BufferedImage image;
													image = ImageIO.read(new File("graphics\\flag.png"));
													g.drawImage(image, p.xpoints[0], p.ypoints[0], this);
												} catch (IOException h) {
													System.out.println("GUI Flag Error");
												}
											}
										};
										panelList[i][j][k] = panel;
										flags[i][j][k] = panel;
										occupied[i][j][k] = true;
									} else if (e.getModifiers() == MouseEvent.BUTTON3_MASK && occupied[i][j][k] == true
											&& flags[i][j][k] != null) {
										
										panelList[i][j][k].setVisible(false);
										frame.remove(panelList[i][j][k]);
										
										panelList[i][j][k] = null;
										flags[i][j][k] = null;
										occupied[i][j][k] = false;
									}
								}
							}
						}
					}
					frame.getContentPane().add(panel);
					frame.repaint();
					frame.revalidate();
				}
			}
		});
		frame.addMouseListener(this);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private Polygon drawCell(Graphics g, int x, int y) {
		int[] xCords = { x, x + 25, x + 12, x - 12 };
		int[] yCords = { y, y, y + 25, y + 25 };
		Polygon p = new Polygon(xCords, yCords, 4);
		g.drawPolygon(p);
		return p;
	}

	private JPanel showMines(Polygon p) {
		JPanel mines = new JPanel() {
			public void paintComponent(Graphics g) {
				for(int i=0;i<cells;i++){
					for(int j=0;j<cells;j++){
						for(int k=0;k<cells;k++){
							if(board.getBoard()[i][j][k].getMine()==true){
								Polygon l=land[i][j][k];
								try {
									BufferedImage image = ImageIO.read(new File("graphics\\mine.png"));
									g.drawImage(image, l.xpoints[0], l.ypoints[0], this);
								} catch (IOException h) {
									System.out.println("GUI Mine Error");
								}
							}
						}
					}
				}
			}
		};
		return mines;

	}

	public void clearBoard() {
		board = null;
		for (int i = 0; i < cells; i++) {
			for (int j = 0; j < cells; j++) {
				for (int k = 0; k < cells; k++) {
					if (occupied[i][j][k] == true) {
						frame.remove(panelList[i][j][k]);
					}
				}
			}
		}
		if(explode!=null){	
			frame.remove(explode);
		}
	}
	public void explode(Polygon p){
		try{
			Icon gif = new ImageIcon("graphics\\explode.gif");
			JLabel explode=new JLabel(gif);
			explode.setBounds(p.xpoints[0], p.ypoints[0], 21, 21);
			frame.getContentPane().add(explode);
			this.explode=explode;
		}catch(Exception e){System.out.println("GUI Explode Error");}
	}
	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}
}
