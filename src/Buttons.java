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
	    int[] xCords={x,x+50,x+25,x-25};
	    int[] yCords={y,y,y+25,y+25};
	    Polygon p=new Polygon(xCords,yCords,4);
	    g.drawPolygon(p);
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
