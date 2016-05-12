package doodle;

import java.util.Random;

import javax.swing.ImageIcon;

public class Enemy implements Runnable{
	ImageIcon image;
	int pos_x;
	int pos_y;
	boolean right;
	Ground parent;
	MainCharacter mc;
	public Enemy(Ground g,MainCharacter m,int x,int y,boolean r){
		this.right = r;
		if(r)
		image = new ImageIcon("8201.png");
		else
		image = new ImageIcon("1.png");
		this.pos_x = x;
		this.pos_y = y;
		this.parent = g;
		this.mc = m;
	}
	@Override
	public void run() {
		Random random = new Random();
		// TODO Auto-generated method stub
		if(right){
		while(true){
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(random.nextInt() % 2 == 0 && !this.mc.moving)
				
				parent.addbullet(this.pos_x+60, this.pos_y+10,true);
		}
		}
		else{
			while(true){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(random.nextInt() % 2 == 0 && !this.mc.moving)
					parent.addbullet(this.pos_x-60, this.pos_y+10,false);
			}	
		}
		
	}
	
}
