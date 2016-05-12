package doodle;

import javax.swing.ImageIcon;

public class Bullet implements Runnable{
	Ground parent;
	MainCharacter mc;
	ImageIcon image;
	int pos_x;
	int pos_y;
	boolean right;
	public Bullet(Ground g,MainCharacter mc,int x,int y,boolean r){
		this.right = r;
		this.parent = g;
		this.mc = mc;
		if(r)
		this.image = new ImageIcon("bullet.jpg");
		else
		this.image = new ImageIcon("bulletleft.jpg");
		this.pos_x = x;
		this.pos_y = y;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(right){
		while(this.pos_x<=880){
			try {
				Thread.sleep(12);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.pos_x = this.pos_x + 4;
			if(this.pos_y < 200 && this.pos_x >550){
				this.pos_x = 10000;
			}
			//parent.repaint();
		}
		//return;
		//parent.bullet.remove(this);
		return;
		}
		else{
			while(this.pos_x>=-80){
				try {
					Thread.sleep(24);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.pos_x = this.pos_x - 4;
				//parent.repaint();
			}
			//return;
			//parent.bullet.remove(this);
			return;
		}
	}
}
