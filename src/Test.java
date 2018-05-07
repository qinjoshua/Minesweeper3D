import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Test extends JPanel {
	int x;
	int y;
	public Test(int a,int b){
		x=a;y=b;
	}
	public void paintComponent(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(x+6, y+3, 20, 20);//To be replaced		
		
	}
}
