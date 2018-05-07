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
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.event.MouseAdapter;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JRadioButtonMenuItem;

public class MinesUI extends Board implements MouseListener{

	private int cells;
	private JFrame frame;
	private Polygon land[][];
	private boolean occupied[][];
	private JPanel panel;
	private JPanel panelList[][];
	private Color color=Color.BLACK;
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
		    	clearBoard();
		        initialize(3);
		    }
		});
		mnGame.add(mntmEasy);
		
		JMenuItem mntmMedium = new JMenuItem("Medium");
		mntmMedium.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	clearBoard();
		        initialize(4);
		    }
		});
		mnGame.add(mntmMedium);
		
		JMenuItem mntmHard = new JMenuItem("Hard");
		mntmHard.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	clearBoard();
		    }
		});
		mnGame.add(mntmHard);
		
		JMenuItem mntmCustom = new JMenuItem("Custom");
		mntmCustom.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
		    	//Promts user for cell size (always squared)
		    	//initialize(c);
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
		cells=c;
		Board board=new Board(cells,cells,cells,cells*2);
		frame.setBounds(cells*20, cells*20, cells*85, cells*35+50); //Save: cells*85, cells*35
		land=new Polygon[cells][cells];
		occupied=new boolean[cells][cells];
		panelList=new JPanel[cells][cells];
		
		panel=new JPanel(){
			public void paintComponent(Graphics g){
				graphics=g;
				for(int i=0;i<cells;i++){
					for(int j=0;j<cells;j++){
						land[i][j]=drawCell(g,(30*cells)+(j*50)-(i*25),(i*25));
					}
				}
			}
		};
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getModifiers()==MouseEvent.BUTTON1_MASK){
					for(int i=0;i<cells;i++){
						for(int j=0;j<cells;j++){
							if(land[i][j].contains(e.getX(),e.getY())&&occupied[i][j]!=true){
								Polygon p=land[i][j];
								panel=new JPanel(){
									public void paintComponent(Graphics g){
										g.setColor(Color.RED);
										g.fillOval(p.xpoints[0]+6, p.ypoints[0]+3, 20, 20);//To be replaced		
									}
								};
							occupied[i][j]=true;
							panelList[i][j]=panel;
							}
						}
					}
				}else if(e.getModifiers()==MouseEvent.BUTTON3_MASK){
					//Right click action
				}
				frame.getContentPane().add(panel);
				frame.repaint();
				frame.revalidate();
			}
		});
		frame.addMouseListener(this);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	private Polygon drawCell(Graphics g, int x, int y){
	    int[] xCords={x,x+50,x+25,x-25};
	    int[] yCords={y,y,y+25,y+25};
	    Polygon p=new Polygon(xCords,yCords,4);
	    g.drawPolygon(p);
	    return p;
	}
	public void clearBoard(){
		for(int i=0;i<cells;i++){
			for(int j=0;j<cells;j++){
				if(occupied[i][j]==true){
					frame.remove(panelList[i][j]);
				}
			}
		}
	}
	public void setCells(int c){
		cells=c;
	}
	/**
	 * 
	 * @param c -color to be changed
	 */
	public void setColor(Color c){
		color=c;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}