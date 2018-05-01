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
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JToolBar;
import java.awt.event.MouseAdapter;

public class MinesUI implements MouseListener{

	private int cells;
	private JFrame frame;
	private Polygon land[][];
	private boolean occupied[][];
	JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinesUI window = new MinesUI(4);
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
		cells=cell;
		land=new Polygon[cells][cells];
		occupied=new boolean[cells][cells];
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(cells*20, cells*20, cells*85, cells*35+50); //Save: cells*85, cells*35
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		panel=new JPanel(){
			public void paintComponent(Graphics g){
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
				for(int i=0;i<cells;i++){
					for(int j=0;j<cells;j++){
						if(land[i][j].inside(e.getX(),e.getY())&&occupied[i][j]!=true){
							Polygon p=land[i][j];
							panel=new JPanel(){
								public void paintComponent(Graphics g){
									g.drawOval(p.xpoints[0]+6, p.ypoints[0]+3, 20, 20);
								}
							};
						occupied[i][j]=true;
						}
					}
				}
				frame.getContentPane().add(panel);
				frame.revalidate();
			}
		});
		frame.addMouseListener(this);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
	}
	private Polygon drawCell(Graphics g, int x, int y){
	    int[] xCords={x,x+50,x+25,x-25};
	    int[] yCords={y,y,y+25,y+25};
	    Polygon p=new Polygon(xCords,yCords,4);
	    g.drawPolygon(p);
	    return p;
	}
	public void setCells(int c){
		cells=c;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
