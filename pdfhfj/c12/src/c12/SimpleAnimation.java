package c12;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleAnimation {
	int x=70;
	int y=70;
	
	public static void main(String[] args) {
		SimpleAnimation gui=new SimpleAnimation();
		gui.go();
	}

	private void go() {
		// TODO Auto-generated method stub
		JFrame frame=new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		MyDrawPanel drawPanel=new MyDrawPanel();
		
		frame.getContentPane().add(drawPanel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		for(int i=0;i<130;i++) {
			x++;
			y++;
			drawPanel.repaint();
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	class DrawPanel extends JPanel{
		public void paintComponent(Graphics g) {
			g.setColor(Color.green);
			g.fillOval(x, y, 40, 40);
		}
	}

}
