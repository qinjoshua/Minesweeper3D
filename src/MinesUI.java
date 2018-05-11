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
import javax.swing.JButton;

public class MinesUI extends Board implements MouseListener {
	/**
	 * Variable of Board class
	 */
	private Board board;
	/**
	 * Length, Width, and Height of board
	 */
	private int cells;
	/**
	 * JFrame of GUI
	 */
	private JFrame frame;
	/**
	 * Saves each individual cell/rectangle
	 */
	private Polygon land[][][];
	/**
	 * Checks if cell is occupied with an image(#,flag,?)
	 */
	private boolean occupied[][][];
	/**
	 * Saves cells occupied with a flag
	 */
	private JPanel flags[][][];
	/**
	 * Saves cells occupied with a question mark
	 */
	private JPanel question[][][];
	/**
	 * JPanel to be added to frame
	 */
	private JPanel panel;
	/**
	 * List of all the panels placed onto frame (for deletion/reset purposes)
	 */
	private JPanel panelList[][][];
	/**
	 * Saves explosion panel (for deletion/reset purposes)
	 */
	private JLabel explode;
	/**
	 * Stops clicks from having an effect on the game after it's over
	 */
	private boolean endGame;

	/**
	 * Launches the application.
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
	 * Initializes the frame and the menu bar
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
				level(3);
			}
		});
		mnGame.add(mntmEasy);

		JMenuItem mntmMedium = new JMenuItem("Medium");
		mntmMedium.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				level(4);
			}
		});
		mnGame.add(mntmMedium);

		JMenuItem mntmHard = new JMenuItem("Hard");
		mntmHard.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				level(5);
			}
		});
		mnGame.add(mntmHard);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmRules = new JMenuItem("Rules");
		mntmRules.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				new Help().setVisible(true);
			}
		});
		mnHelp.add(mntmRules);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				level(cells);
			}
		});
		menuBar.add(btnReset);

		initialize(3, 310);
	}

	/**
	 * Initialize variables and board, then adds panel onto frame. Also calls
	 * clickAlgorithm.
	 */
	private void initialize(int c, int length) {
		cells = c;
		endGame = false;
		board = new Board(cells, cells, cells, cells * 2);
		frame.setBounds(cells * 20, cells * 20, cells * 55, length); // Save:
																		// cells
																		// * 55
		land = new Polygon[cells][cells][cells];
		occupied = new boolean[cells][cells][cells];
		panelList = new JPanel[cells][cells][cells];
		flags = new JPanel[cells][cells][cells];
		question = new JPanel[cells][cells][cells];
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
		clickAlgorithm();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Draws an individual cell at a certain point
	 * 
	 * @param g
	 *            -Provided by override of paintComponent
	 * @param x
	 *            -Starting horizontal point
	 * @param y
	 *            -Starting vertical point
	 * @return Individual cell at the point
	 */
	private Polygon drawCell(Graphics g, int x, int y) {
		int[] xCords = { x, x + 25, x + 12, x - 12 };
		int[] yCords = { y, y, y + 25, y + 25 };
		Polygon p = new Polygon(xCords, yCords, 4);
		g.drawPolygon(p);
		return p;
	}

	/**
	 * Reveals all the mines.
	 * 
	 * @return Panel of mine-images (saved for deletion purposes)
	 */
	private JPanel showMines() {
		JPanel mines = new JPanel() {
			public void paintComponent(Graphics g) {
				for (int i = 0; i < cells; i++) {
					for (int j = 0; j < cells; j++) {
						for (int k = 0; k < cells; k++) {
							if (board.getBoard()[i][j][k].getMine() == true) {
								Polygon l = land[i][j][k];
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

	/**
	 * Resets the board for a new game. Clears frame using panelList and
	 * explode.
	 */
	private void clearBoard() {
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
		if (explode != null) {
			frame.remove(explode);
		}
		frame.repaint();
	}

	/**
	 * Clears and reinitializes the board
	 * 
	 * @param c
	 *            -cells (l,w,h of board)
	 */
	private void level(int c) {
		clearBoard();
		if (c == 3) {
			initialize(c, 310);
		} else if (c == 4) {
			initialize(c, 490);
		} else if (c == 5) {
			initialize(c, 720);
		}
	}

	/**
	 * Implements the explosion animation onto the cell
	 * 
	 * @param p
	 *            -Clicked mine
	 */
	public void explode(Polygon p) {
		try {
			Icon gif = new ImageIcon("graphics\\explode.gif");
			JLabel explode = new JLabel(gif);
			explode.setBounds(p.xpoints[0], p.ypoints[0], 21, 21);
			frame.getContentPane().add(explode);
			this.explode = explode;
		} catch (Exception e) {
			System.out.println("GUI Explode Error");
		}
	}

	/**
	 * Loops through land[][][] to check which cell is clicked. 
	 * -If left-clicked places number. 
	 * -If right-clicked places flag. 
	 * -If right-clicked, but flag it already there, remove flag 
	 * -If left-clicked on mine, ends game.
	 */
	private void clickAlgorithm() {
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (endGame == false) {
					for (int i = 0; i < cells; i++) {
						for (int j = 0; j < cells; j++) {
							for (int k = 0; k < cells; k++) {
								if (land[i][j][k].contains(e.getX(), e.getY())) {
									if (e.getModifiers() == MouseEvent.BUTTON1_MASK && occupied[i][j][k] == false) {
										if (board.getBoard()[i][j][k].getMine() == false) {
											placeNumber(i, j, k);
										} else {
											endGame(i, j, k);
										}
									} else if (e.getModifiers() == MouseEvent.BUTTON3_MASK && occupied[i][j][k] == false
											&& flags[i][j][k] == null) {
										placeFlag(i, j, k);
									} else if (e.getModifiers() == MouseEvent.BUTTON3_MASK && occupied[i][j][k] == true
											&& flags[i][j][k] != null) {
										placeQuestion(i, j, k);
									} else if (e.getModifiers() == MouseEvent.BUTTON3_MASK && occupied[i][j][k] == true
											&& question[i][j][k] != null) {
										removeQuestion(i, j, k);
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
	}

	/**
	 * Calls generatePointValue (from Board class) and prints the value onto the
	 * cell.
	 * 
	 * @param i,j,k
	 *            -Coordinates of cell
	 */
	private void placeNumber(int i, int j, int k) {
		int num = board.generatePointValue(i, j, k);
		if (num != 0) {
			panel = new JPanel() {
				public void paintComponent(Graphics g) {
					try {
						BufferedImage image;
						image = ImageIO.read(new File("graphics\\numbers\\" + num + ".png"));
						g.drawImage(image, land[i][j][k].xpoints[0], land[i][j][k].ypoints[0], this);
					} catch (IOException h) {
						System.out.println(h.getMessage() + " " + num);
					}
				}
			};
		}
		occupied[i][j][k] = true;
		panelList[i][j][k] = panel;
	}

	/**
	 * Places a flag on the corresponding cell.
	 * 
	 * @param i,j,k
	 *            -Coordinates of cell
	 */
	private void placeFlag(int i, int j, int k) {
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				try {
					BufferedImage image;
					image = ImageIO.read(new File("graphics\\flag.png"));
					g.drawImage(image, land[i][j][k].xpoints[0], land[i][j][k].ypoints[0], this);
				} catch (IOException h) {
					System.out.println("GUI Flag Error");
				}
			}
		};
		panelList[i][j][k] = panel;
		flags[i][j][k] = panel;
		occupied[i][j][k] = true;
	}

	/**
	 * Places a question mark on the corresponding cell.
	 * 
	 * @param i,j,k
	 *            -Coordinates of cell
	 */
	private void placeQuestion(int i, int j, int k) {
		panelList[i][j][k].setVisible(false);
		frame.remove(panelList[i][j][k]);
		panel = new JPanel() {
			public void paintComponent(Graphics g) {
				try {
					BufferedImage image;
					image = ImageIO.read(new File("graphics\\question.png"));
					g.drawImage(image, land[i][j][k].xpoints[0], land[i][j][k].ypoints[0], this);
				} catch (IOException h) {
					System.out.println("GUI Question Error");
				}
			}
		};
		panelList[i][j][k] = panel;
		flags[i][j][k] = null;
		question[i][j][k] = panel;
		occupied[i][j][k] = true;
	}

	/**
	 * Removes question mark.
	 * 
	 * @param i,j,k
	 *            -Coordinates of cell
	 */
	private void removeQuestion(int i, int j, int k) {
		panelList[i][j][k].setVisible(false);
		frame.remove(panelList[i][j][k]);

		panelList[i][j][k] = null;
		question[i][j][k] = null;
		occupied[i][j][k] = false;
	}

	/**
	 * Calls showMines and explode. Sets variables appropriately.
	 * 
	 * @param i,j,k
	 *            -Coordinates of cell
	 */
	private void endGame(int i, int j, int k) {
		panel = showMines();
		endGame = true;

		occupied[i][j][k] = true;
		panelList[i][j][k] = panel;
		explode(land[i][j][k]);
	}

	/**
	 * Ignore. Required my implementation.
	 */
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
