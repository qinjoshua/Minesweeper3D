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
	private Polygon land[][][];
	private boolean occupied[][][];
	private JPanel panel;
	private JPanel panelList[][][];
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
		Board b=new Board(cells,cells,cells,cells*2);
		b.createBoard();;
		frame.setBounds(cells*20, cells*20, cells*85, cells*125); //Save: cells*85, cells*35
		land=new Polygon[cells][cells][cells];
		occupied=new boolean[cells][cells][cells];
		panelList=new JPanel[cells][cells][cells];
		
		panel=new JPanel(){
			public void paintComponent(Graphics g){
				graphics=g;
				for(int i=0;i<cells;i++){
					for(int j=0;j<cells;j++){
						for(int k=0;k<cells;k++){
							land[i][j][k]=drawCell(g,(30*cells)+(k*50)-(j*25),(j*25)+(i*25*cells)+10);
						}
					}
				}
			}
		};
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getModifiers()==MouseEvent.BUTTON1_MASK){
					System.out.println(e.getX()+" "+e.getY());
					for(int i=0;i<cells;i++){
						for(int j=0;j<cells;j++){
							for(int k=0;k<cells;k++){
								System.out.println(occupied[i][j][k]);
								if(land[i][j][k].contains(e.getX(),e.getY())&&occupied[i][j][k]!=true){
									Polygon p=land[i][j][k];
									if(b.getBoard()[i][j][k]!=new MineCell(-1, true)){
										int x=i,y=j,z=k;
										panel=new JPanel(){
											public void paintComponent(Graphics g){
												g.drawString(String.valueOf(b.generatePointValue(x, y, z)), p.xpoints[0]+12,p.ypoints[0]+13);
												//Test t =new Test(p.xpoints[0],p.ypoints[0]);
												//t.paintComponent(g);
											}
										};
									}else{b.explode();}
									occupied[i][j][k]=true;
									panelList[i][j][k]=panel;
								}else if(e.getModifiers()==MouseEvent.BUTTON3_MASK){
									//Right click action
								}
							}
						}
					}
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
				for(int k=0;k<cells;k++){
					if(occupied[i][j][k]==true){
						frame.remove(panelList[i][j][k]);
					}
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
