import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Panel;
import java.awt.geom.Path2D;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JToolBar;

public class MinesUI {

	private int cells;
	private JFrame frame;
	private Graphics graphics;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MinesUI window = new MinesUI(15);
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
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(cells*20, cells*20, 375, 214); //Save: cells*85, cells*35
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		
		JPanel panel_1 = new JPanel(){
			public void paint(Graphics g){
				graphics=g;
				for(int i=0;i<cells;i++){
					for(int j=0;j<cells;j++){
						drawCell((30*cells)+(j*50)-(i*25),(i*25));
					}
				}
			}
		};
		frame.getContentPane().add(panel_1, BorderLayout.CENTER);
		
	}
	private void drawCell(int x, int y){
	    graphics.drawLine(x,y,x+50,y);
	    graphics.drawLine(x+50,y,x+25,y+25);
	    graphics.drawLine(x+25,y+25,x-25,y+25);
	    graphics.drawLine(x-25,y+25,x,y);
	}
	public void setCells(int c){
		cells=c;
	}
}
