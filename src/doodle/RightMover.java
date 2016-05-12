package doodle;

public class RightMover extends Thread {
	public MainCharacter ch;
	public Ground parent;
	boolean turn_on;
	public RightMover(MainCharacter mc,Ground g){
		this.ch = mc;
		this.parent = g;
		this.turn_on = false;
//		try {
//			this.wait();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
//		synchronized(this){
//			while(!turn_on){
//				try {
//					this.wait();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			this.notifyAll();
//		}
//		this.ch.pos_y += 16;
		while(true){
			if(turn_on)
				return;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-gen1erated catch block
				e.printStackTrace();
			}
			this.ch.pos_x += 2;
			parent.repaint();
		//}
		}
	}
	public void manage(){
	
	}
	
}