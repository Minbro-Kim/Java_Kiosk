package Order;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRootPane;

public class MainFrame extends JFrame {

	private Container c = getContentPane();
	private InitialPanel ip;
	private OrderingPanel op;
	private ReciptPanel rp;

	public MainFrame() {
		setTitle("공차 키오스크");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		ip = new InitialPanel(this);
		c.add(ip);
		
		setSize(1000,700);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	void changeToInitialPanel() {
		c.removeAll();
		ip = new InitialPanel(this);
		c.add(ip);
		c.repaint();
		c.revalidate();
	}
	void changePanel(int p) {
		JPanel currentPanel = (JPanel) c.getComponent(0);
		currentPanel.setVisible(false);
		
		if(p==2) {
			op = new OrderingPanel(this);
			c.add(op,BorderLayout.CENTER);
		}
		else if(p==4) {
			JPanel currentPanel2 = (JPanel) c.getComponent(1);
			currentPanel2.setVisible(false);
			rp = new ReciptPanel(this,op);
			c.add(rp,BorderLayout.CENTER);
			//c.repaint();
			
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		new MainFrame();
	}

}
