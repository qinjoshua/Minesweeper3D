import java.awt.Component;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Shape;

import javax.swing.JButton;

public class Buttons extends JButton {
	private int x;
	private int y;
	public Buttons(int x, int y){
		this.x=x;
		this.y=y;
	}
	public void paintComponent(Graphics g){
	  // int[] xCords={x,x+50,x+25,x-25};
	//    int[] yCords={y,y,y+25,y+25};
		
		//int[] xCords = {5, 15};
		//int[] yCords = {25, 35};
		
	   // Polygon p = new Polygon(xCords, yCords, 4);
	    
	    //g.drawPolygon(p);
	   // g.drawPolyline(xCords, yCords, 1);
		g.drawLine(50, 0, 50, 100);
		g.drawLine(50, 100, 100, 300);
		g.drawLine(350, 100, 100, 300);
	     // g.drawLine(50, 0, 50, 150);
	       // g.drawLine(0, 75, 150, 75);
		}
	//Which one works?
	private Polygon drawCell(Graphics g, int x, int y){
	    int[] xCords={x,x+50,x+25,x-25};
	    int[] yCords={y,y,y+25,y+25};
	    Polygon p=new Polygon(xCords,yCords,4);
	    g.drawPolygon(p);
	    return p;
	}
}
