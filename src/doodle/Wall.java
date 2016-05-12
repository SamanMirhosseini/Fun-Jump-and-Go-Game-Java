package doodle;

import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Wall implements Runnable{
	int  pos_x;
	int  pos_y;
	int diff;
	int  length;
	int char_pos_x;
	int char_pos_y;
	int right;
	int row;
	MainCharacter character;
	boolean prch;
	ImageIcon image;
	Thread downdown;
	public Rectangle body;
	Ground parent;
	Rectangle boundries;
	public Wall(int x,int y,int length,Ground g,int right,int row,MainCharacter c,int diff){
		image = new ImageIcon("yes.gif");
		this.diff = diff;
		this.pos_x = x;
		this.pos_y = y;
		this.length = length;
		this.parent = g;
		ImageIcon image;
		this.char_pos_x = -1;
		this.char_pos_y = -1;
		this.prch = false;
		this.right = right;
		this.body = new Rectangle(x,y,length,20);
		this.row = row;
		this.character = c;
	}
	private int get_slepp_time(){
		switch (this.diff) {
		case 0:
			return 40;
		case 1:
			return 20;
		case 3:
			return 14;
		}
		return 14;
	}
	@Override
	public void run() throws java.util.ConcurrentModificationException{
		// TODO Auto-generated method stub
		try{
		int p = 0;
		while(true){
		try {
			Thread.sleep(parent.speed);
		} catch (InterruptedException e) {
			// TODO Auto-gen1erated catch block
			e.printStackTrace();
		}
		if(this.right == 1){
		if(this.pos_x > 1000)
			this.pos_x = -200;
		this.body.x = this.pos_x;
		this.pos_x = pos_x + 2;
		if (prch){
			if(this.character.pos_x <= 760){
				for(Bullet o : parent.bullet){
					if(this.character.samebull(o)){
						this.parent.dispatchEvent(new CloseEvent(parent, Message.close));
						JOptionPane.showMessageDialog(parent,"Looser");
						System.exit(0);
					}
				}
		this.character.pos_x += 2;
			}
			else{
				if(!this.character.same(this)){
					this.prch = false;
					downdown = new Thread(new MoveDown(parent,this.character,false));
					downdown.start();
				}
			}
		}
		this.body.x = this.pos_x;
		parent.repaint();
		}
		else{
			if(this.pos_x <-200)
				this.pos_x = 1000;
			this.body.x = this.pos_x;
			this.pos_x -= 2;
			if(prch){
				if(this.character.pos_x >= 0){
			this.character.pos_x -= 2;
			for(Bullet o : parent.bullet){
				if(this.character.samebull(o)){
					this.parent.dispatchEvent(new CloseEvent(parent, Message.close));
					JOptionPane.showMessageDialog(parent,"Looser");
					System.exit(0);
				}
				}
				}
				else{
					if(!this.character.same(this)){
						this.prch = false;
						downdown = new Thread(new MoveDown(parent,this.character,false));
						downdown.start();
					}
				}
			}
			this.body.x = this.pos_x;
			parent.repaint();	
	}
	}
	}
		catch(java.util.ConcurrentModificationException e){
			
		}
	}

}
