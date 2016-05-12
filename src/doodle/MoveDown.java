package doodle;

import javax.swing.JOptionPane;

public class MoveDown implements Runnable{
	Ground parent;
	boolean up;
	MainCharacter mc;
	public MoveDown(Ground g,MainCharacter m,boolean up){
		this.mc = m;
		this.up = up;
		this.parent = g;
	}
	public boolean same(Wall w){
		if(mc.pos_x + 40 >= w.pos_x && mc.pos_x <= w.pos_x+w.length && Math.abs(w.pos_y - mc.pos_y) <= 60)
			return true;
		return false;
	}

	@Override
	public void run()throws java.util.ConcurrentModificationException{
		// TODO Auto-generated method stub
		this.mc.moving = true;
		int h = 0;
		int counter = 0;
		Wall temp = null;
		while(h==0){
			
			long t =System.currentTimeMillis()+10;
			while(System.currentTimeMillis()< t);
			//if(this.pos_y > 660 && this.pos_y < 720){
				//this.visibilty = 0;
				if(mc.pos_y >= 880){
					this.parent.dispatchEvent(new CloseEvent(parent, Message.close));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					JOptionPane.showMessageDialog(parent,"Looser");
					System.exit(0);
					return;
				}
				for(Bullet bu:parent.bullet){
					if(mc.samebull(bu)){
						this.parent.dispatchEvent(new CloseEvent(parent, Message.close));
						JOptionPane.showMessageDialog(parent,"Looser");
						System.exit(0);
					}
				}
				for(Wall m:parent.wall){
					if(this.same(m)){
//						parent.r.turn_on = true;
//						parent.r.start();
						temp = m;
						h = 9000;
						if(!up)
						temp.prch = true;
						this.mc.moving = false;
					}
				}
			//}
			mc.pos_y = mc.pos_y + 4;
			counter++;
			if(counter == 20){
				this.mc.height = 40;
				this.mc.sitting = false;
			}
			parent.repaint();
		}
		mc.pos_y = temp.pos_y-mc.height;
		System.out.println(mc.pos_y);
		this.mc.moving = false;
		return;
	}
	

}
