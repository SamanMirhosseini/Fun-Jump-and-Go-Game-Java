package doodle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Ground extends JPanel implements Runnable,KeyListener{
	public Vector <Wall> wall;
	int [] orienation;
	MainCharacter ch;
	Thread th;
	Thread Downer;
	ImageIcon goal;
	MainFrame frame;
	int level;
	RightMover r;
	int speed;
	//Bullet b = new Bullet(this, ch, 20,20 );
	public Vector <Enemy> enemy;
	public LinkedList <Bullet> bullet;
	public Ground(int num,int l,MainFrame mafr,int speed,int [] a){
		this.speed = speed;
		this.level=l;
		this.frame = mafr;
		this.orienation = a;
		setLayout(null);
		setSize(800,880);
		setLocation(0, 0);
		setBackground(Color.YELLOW);
		setVisible(true);
		goal = new ImageIcon("Goal.png");
		ch = new MainCharacter(0, 840,this);
		//Rectangle r = new Rectangle(0,860,60,60);
		//System.out.println(r.intersects(ch.body));
		addKeyListener(this);
		wall = new Vector<Wall>();
		bullet = new LinkedList<Bullet>();
		r = new RightMover(ch, this);
//		r.start();
		Object o = new Object();
	
		this.make_wall();
		for(Wall j : wall){
			(new Thread(j)).start();
		}
		
		//(new Thread(ch)).start();
//		
//		for(int i = 1 ; i < 10;i++){
//		try {
//			Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		sh();
		
		 
//		}
		enemy = new Vector<Enemy>();
//		(new Thread(b)).start();
		this.make_the_enemies();
	}
	synchronized public void addbullet(int x,int y,boolean r){
		Bullet temp = new Bullet(this, ch, x, y,r);
		this.bullet.add(temp);
		(new Thread(temp)).start();
	}
	private void sh(){
		for(Wall j : wall){
			System.out.print(j.pos_x+"\t");
		}
		System.out.println();
	}
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics arg0) throws java.util.ConcurrentModificationException{
		// TODO Auto-generated method stub
		//synchronized(this){
		super.paint(arg0);
		arg0.setColor(Color.BLACK);
		for(Enemy e:this.enemy)
		arg0.drawImage(e.image.getImage(), e.pos_x, e.pos_y, null);
		arg0.drawImage(goal.getImage(), 600, 0, null);
		if(ch.height == 40){
			arg0.drawImage(ch.image.getImage(), ch.pos_x, ch.pos_y, null);
		}
		else{
			arg0.drawImage(ch.sit.getImage(), ch.pos_x, ch.pos_y, null);
		}
		for(Wall i : wall){
				arg0.fillRect(i.pos_x,i.pos_y,i.length,20);
			/*else{
				arg0.fillRect(i.pos_x,i.pos_y,i.length,20);
				arg0.drawImage(i.image.getImage(), i.char_pos_x, i.char_pos_y, null);
			}*/
		}		
		if(!this.bullet.isEmpty()){
			try{
		for(Bullet b:this.bullet){
			if(b != null)
			arg0.drawImage(b.image.getImage(),b.pos_x, b.pos_y, null);
		}
			}
			catch(java.util.ConcurrentModificationException e){
				
			}
		}
	}
	public void make_the_enemies(){
		switch(this.level){
		case 0:
			break;
		case 1:
			for(int i = 0; i < 5;i+=2){
				Enemy temp = new Enemy(this, ch, 0, 160*i,true);
				this.enemy.add(temp);
				(new Thread(temp)).start();
			}
			for(int j = 1; j < 5;j+=2){
				Enemy temp = new Enemy(this, ch, 740, 160*j+ 20,false);
				this.enemy.add(temp);
				(new Thread(temp)).start();
			}
		break;
		case 2:
		for(int i = 0; i < 5;i++){
			Enemy temp = new Enemy(this, ch, 0, 160*i,true);
			this.enemy.add(temp);
			(new Thread(temp)).start();
		}
		for(int j = 0; j < 5;j++){
			Enemy temp = new Enemy(this, ch, 740, 160*j+20,false);
			this.enemy.add(temp);
			(new Thread(temp)).start();
		}
		break;
		case 3:
			for(int i = 0; i < 5;i++){
				Enemy temp = new Enemy(this, ch, 0, 160*i,true);
				this.enemy.add(temp);
				(new Thread(temp)).start();
			}
			for(int i = 0; i < 5;i++){
				Enemy temp = new Enemy(this, ch, 80, 160*i,true);
				this.enemy.add(temp);
				(new Thread(temp)).start();
			}
			for(int j = 0; j < 5;j++){
				Enemy temp = new Enemy(this, ch, 740, 160*j+20,false);
				this.enemy.add(temp);
				(new Thread(temp)).start();
			}
		break;
		case 4:
			for(int i = 0; i < 5;i++){
				Enemy temp = new Enemy(this, ch, 0, 160*i+20,true);
				this.enemy.add(temp);
				(new Thread(temp)).start();
			}
			for(int i = 0; i < 5;i++){
				Enemy temp = new Enemy(this, ch, 80, 160*i,true);
				this.enemy.add(temp);
				(new Thread(temp)).start();
			}
			for(int j = 0; j < 5;j++){
				Enemy temp = new Enemy(this, ch, 740, 160*j,false);
				this.enemy.add(temp);
				(new Thread(temp)).start();
			}
			for(int j = 0; j < 5;j++){
				Enemy temp = new Enemy(this, ch, 660, 160*j+20,false);
				this.enemy.add(temp);
				(new Thread(temp)).start();
			}
		break;
		default:
			JOptionPane.showMessageDialog(this,"Looser");
			System.exit(0);
			break;
		}
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.repaint();
	
	}
	/*Wall w1 = new Wall(0,100,100,this);
	Wall w2 = new Wall(200,100,100,this);
	Wall w3 = new Wall(400,100,100,this);
	Wall w4 = new Wall(600,100,100,this);
	wall.add(w1);
	wall.add(w2);
	wall.add(w3);
	wall.add(w4);*/
	private void make_wall(){
		for(int i = 1;i < 6;i++){
			for(int j = 0; j < 3;j++){
				int x = (int) ((Math.random()*10) + (Math.random())*120);
				Wall temp = new Wall(400*j+10*i+x,160*(i-1)+80,200,this,this.orienation[i-1],i,this.ch,this.level);
				this.wall.add(temp);
			}
		}
		System.out.println(this.wall.size());
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		int p = 0;
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			ch.moveR();
			if(ch.pos_y == ch.height || ch.pos_y == ch.height+1*160 || ch.pos_y == ch.height+2*160 || ch.pos_y == ch.height+3*160 || ch.pos_y == ch.height+4*160){
			for(Wall k:this.wall)
				if(this.ch.same(k))
					p++;
			if(p == 0){
				for(Wall i: this.wall)
					i.prch = false;
				Downer = new Thread(new MoveDown(this, ch,false));
				Downer.start();
			}
			}
			p=0;
			}
			if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			ch.moveL();
			//this.parent.repaint();
			if(ch.pos_y == 40 || ch.pos_y == 200 || ch.pos_y == 360 || ch.pos_y == 520 || ch.pos_y == 680){
			for(Wall k:this.wall)
				if(this.ch.same(k))
					p++;
			if(p == 0){
				for(Wall i: this.wall)
					i.prch = false;
				Downer = new Thread(new MoveDown(this, ch,false));
				Downer.start();
			}
			}
			
			p=0;
			}
			if(arg0.getKeyCode() == KeyEvent.VK_UP ){
				//if(!th.isAlive()){
				if(!this.ch.moving && !this.ch.sitting){
				for(Wall i: this.wall)
					i.prch = false;
				th = new Thread(ch);
				//ch.goin_up = true;
				th.start();
				}
				//}
			}
			if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			if(!ch.sitting && !ch.moving){
				ch.sitting = true;
			ch.height = 5;
			ch.pos_y = ch.pos_y + 25;
			System.out.println(ch.height);
			}
			}
//			if(wall.get(0).body.intersects(ch.body))
//				JOptionPane.showMessageDialog(this, null);
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			if(!ch.moving && ch.sitting){
			ch.sitting = false;
			ch.pos_y = ch.pos_y - 25;
			ch.height = 40;
			System.out.println(ch.height);
			}
			}
		
	}
	public void RP(){
		synchronized(this){
			this.repaint();
		}
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void processComponentEvent(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		super.processComponentEvent(arg0);
		if(arg0.getID()== Message.close){
			this.frame.dispose();
		}
			
	}

}
