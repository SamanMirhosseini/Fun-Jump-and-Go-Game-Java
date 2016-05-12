package doodle;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ContainerEvent;

import javax.swing.JFrame;

public class MainFrame extends JFrame {
	Ground ground;
	int level;
	int speed;
	int [] ori;
	public MainFrame(int level,int speed,int [] ori){
		this.level = level;
		this.speed = speed;
		this.ori = ori;
		setSize(830,1000);
		setLayout(null);
		setVisible(true);
		setLocation(0, 0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		ground = new Ground(4,level,this,speed,this.ori);
		(new Thread(ground)).start();
		getContentPane().add(ground);
		this.ground.setVisible(true);
		addKeyListener(ground);
	}
	/* (non-Javadoc)
	 * @see java.awt.Container#processContainerEvent(java.awt.event.ContainerEvent)
	 */
	@Override
	protected void processContainerEvent(ContainerEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getID()==Message.next_level){
		synchronized (this) {
			try {
				new MainFrame(this.level + 1,this.speed,this.ori);
				
				this.wait();
				this.dispose();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.dispose();
	
		}
		super.processContainerEvent(arg0);
	}
	static public void main(String[]args){
		new Menu();
		//MainFrame mf = new MainFrame(4,40);
	}
	
}
