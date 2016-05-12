package doodle;


import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MoveAction;

public class MainCharacter implements Runnable{
	int pos_x;
	int pos_y;
	int visibilty;
	Thread down;
	//RightMover r;
	boolean moving;
	boolean sitting;
	Vector <Wall> check;
	Ground parent;
	ImageIcon image;
	ImageIcon sit;
	int height;
	public Rectangle body;
	public MainCharacter(int x,int y,Ground p){
		//r = new RightMover(this, parent);
		this.pos_x = x;
		this.sitting = false;
		sit = new ImageIcon("sitdown.png");
		this.pos_y = y;
		this.height = 40;
		this.visibilty = 1;
		this.moving = false;
		image = new ImageIcon("kio.png");
		body = new Rectangle(x,y,40,40);
		this.parent = p;
		this.check = p.wall;
	}
	public boolean same(Wall w){
		if(this.pos_x + 40 >= w.pos_x  && this.pos_x <= w.pos_x+w.length && Math.abs(w.pos_y - this.pos_y) <= 10)
			return true;
		return false;
	}
	public boolean samebull(Bullet b){
		if(this.pos_x>= b.pos_x && this.pos_x <= b.pos_x+60 && Math.abs(b.pos_y - this.pos_y) <= 20)
			return true;
		return false;
	}
	public void moveR(){
		if(this.pos_x <= 760 && !this.sitting){
		this.pos_x = pos_x + 10;
		parent.repaint();
		}
	}
	public void moveL(){
		if(this.pos_x >= 0 && !this.sitting){
		this.pos_x = pos_x - 10;
		parent.repaint();
		}
	}
	public void MoveUp(){
//		for(int i = 1;i < 80;i++){
//			long t =System.currentTimeMillis()+20;
//			while(System.currentTimeMillis()< t);
//			this.pos_y = pos_y - 1;
//			parent.repaint();
//		}
	}
	public void MoveDown(){
		for(int i = 1;i < 80;i++){
//			try {
//				Thread.sleep(1);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			this.pos_y = pos_y + 4;
			parent.repaint();
		}
	}
	/*@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT)
		moveR();
		if(arg0.getKeyCode() == KeyEvent.VK_LEFT)
		moveL();
		this.parent.repaint();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}*/
	@Override
	public void run() throws java.util.ConcurrentModificationException{
		// TODO Auto-generated method stub
		Wall temp = null;
		this.moving = true;
		int h = 0;
		boolean keep_goin = true;
		for(int i = 1;i <= 50&&keep_goin;i++){
			long t =System.currentTimeMillis()+10;
			while(System.currentTimeMillis()< t);
			this.pos_y = pos_y - 4;
			if(this.pos_y <= -40 && this.pos_x >= 600 && this.pos_x <= 680){
				this.parent.frame.dispatchEvent(new LevelChange(parent, Message.next_level,parent));
				parent.frame.dispose();
				this.parent.dispatchEvent(new CloseEvent(parent, Message.close));
			}
//			for(int l = 0; l < parent.wall.size();l++){
//				if(this.body.intersects(parent.wall.get(l).body)&& this.pos_y < 650/*this.pos_y < 650 && this.pos_x > w.pos_x && w.pos_x < w.pos_x + 200*/){
//					h++;
//					//parent.wall.get(l).prch = true;
//					System.out.println(this.pos_y +"&"+parent.wall.get(l)+"  ");
//					JOptionPane.showMessageDialog(parent, null);
//					//parent.th.interrupt();
//					return;
//				}
//				else
				for(Wall w:parent.wall){
					if(this.same(w)){
						System.out.println("lol");
						keep_goin = false;
						this.pos_y = this.pos_y + 60;
						Thread downey = new Thread(new MoveDown(parent, this,false));
						downey.start();
						w.prch = false;
						return;
						//break;
					}
				}
				for(Bullet bu:parent.bullet){
					if(this.samebull(bu)){
						parent.dispatchEvent(new CloseEvent(parent, Message.close));
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						JOptionPane.showMessageDialog(parent,"Looser");
						System.exit(0);
						parent.frame.dispose();
						this.parent.dispatchEvent(new CloseEvent(parent, Message.close));
						synchronized(parent.frame){
						try {
							parent.frame.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						}
					}
				}
				parent.repaint();
//				}
			
		}
		this.moving = false;
		down = new Thread(new MoveDown(parent, this,false));
		down.start();
//		while(h==0){
//			long t =System.currentTimeMillis()+10;
//			while(System.currentTimeMillis()< t);
//			//if(this.pos_y > 660 && this.pos_y < 720){
//				//this.visibilty = 0;
//				for(Wall m:parent.wall){
//					if(this.same(m)){
////						parent.r.turn_on = true;
////						parent.r.start();
//						temp = m;
//						h = 9000;
//						temp.prch = true;
//					}
//				}
//			//}
//			this.pos_y = pos_y + 2;
//			parent.repaint();
//		}
//		this.pos_y = temp.pos_y-40;
	}
	
}