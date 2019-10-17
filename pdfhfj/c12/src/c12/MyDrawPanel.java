package c12;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.JPanel;

public class MyDrawPanel extends JPanel implements ControllerEventListener{

	boolean msg=false;
	@Override
	public void controlChange(ShortMessage event) {
		// TODO Auto-generated method stub
		msg=true;
		repaint();
		
	}
	public void paintComponent(Graphics g) {
		if(msg) {
			Graphics2D g2=(Graphics2D) g;
			int r=(int)(Math.random()*250);
			int gr=(int)(Math.random()*250);
			int b=(int)(Math.random()*250);
			
			g.setColor(new Color(r,gr,b));
			
			int ht=(int)((Math.random()*120)+10);
			int width=(int)((Math.random()*120)+10);
			int x=(int)((Math.random()*40)+10);
			int y=(int)((Math.random()*40)+10);
			
			g.fillRect(x, y, ht, width);
		}
	}
	

}
