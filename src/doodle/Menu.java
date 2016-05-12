package doodle;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.StringTokenizer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class Menu extends JFrame {
	myB b1,b2,b3,b4;
	Jc j1,j2,j3,j4,j5;
	myPanel right;
	JTextArea name;
	JLabel player_name;
	public Menu(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(600, 340);
		this.setLocation(540, 400);
	//	this.setBackground(Color.black);
		name = new JTextArea();
		player_name = new JLabel("Player Name");
		b1 = new myB(150,100,"Easy",this);
		b2 = new myB(150,200,"Medium",this);
		b3 = new myB(150,300,"Hard",this);
		b4 = new myB(150, 400, "Exit",this);
		j1 = new Jc("first|row|1");
		j2 = new Jc("second|row|2");
		j3 = new Jc("third|row|3");
		j4 = new Jc("fourth|row|4");
		j5 = new Jc("fifth|row|5");
//		j1.setSize(400,100);
//		j2.setSize(400,100);
//		j3.setSize(400,100);
//		j4.setSize(400,100);
//		j5.setSize(400,100);
		j1.setLocation(10,10);
		right = new myPanel();
		right.setLayout(new GridLayout(12,1));
	//	right.setBackground(Color.BLUE);
	//	//this.repaint();
		//right.setLayout(new GridLayout(12,12));
		right.setSize(300, 600);
		right.setLocation(300,0);
		right.add(player_name);
		right.add(name);
		right.add(j1);
		right.add(j2);
		right.add(j3);
		right.add(j4);
		right.add(j5);
		right.add(b1);
		right.add(b2);
		right.add(b3);
		right.add(b4);
	//	right.repaint();
		this.getContentPane().add(right);
		this.setVisible(true);
	}

}
class myB extends JButton{
	Menu menu;
	public myB(int x,int y,String s,Menu m){
		super(s);
		this.menu = m;
		this.setLocation(x, y);
		this.setSize(120, 60);
		this.addMouseListener(new Ml(menu));
	}


}
class Jc extends JCheckBox{
	public Jc (String s){
		super(s);
		this.setAlignmentX(SwingConstants.CENTER);
	}
}
class myPanel extends JPanel {
	ImageIcon image;
	public myPanel(){
		super();
		image = new ImageIcon("back.png");
		repaint();
//		this.addMouseListener(new Ml());
	}
//	@Override
//	public void paint(Graphics arg0){
//		super.paint(arg0);
//		arg0.drawImage(image.getImage(),0,0,null);
//	}
}
class Ml implements MouseListener{
	Menu menu;
	public Ml(Menu m) {
		// TODO Auto-generated constructor stub
		this.menu = m;
	}
	private int [] ori_handell(){
		int [] a = new int[5];
		a[0] = to_integer(menu.j1.isSelected());
		a[1] = to_integer(menu.j2.isSelected());
		a[2] = to_integer(menu.j3.isSelected());
		a[3] = to_integer(menu.j4.isSelected());
		a[4] = to_integer(menu.j5.isSelected());
		return a;
	}
	private int to_integer(boolean bool){
		if(bool)
			return 1;
		else
			return 0;
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		StringTokenizer st = new StringTokenizer(menu.name.getText());
		if(st.countTokens() == 0){
			JOptionPane.showMessageDialog(menu, "Please enter your name");
		}
		else{
		if(arg0.getSource().equals(menu.b1)){
			new MainFrame(0, 15,ori_handell());
		}
		else if(arg0.getSource().equals(menu.b2))
			new MainFrame(1, 15,ori_handell());
		else if(arg0.getSource().equals(menu.b3))
			new MainFrame(4, 10,ori_handell());
		else if(arg0.getSource().equals(menu.b4))
			System.exit(0);
		}
			
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("yes1");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		//System.out.println("yes2");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}