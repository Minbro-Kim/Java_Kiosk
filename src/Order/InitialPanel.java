package Order;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
//초기화면
class InitialPanel extends JPanel{

	InitialPanel(MainFrame mf){
		//레이아웃 타입
		setLayout(new BorderLayout());
		//안내 문구
		JLabel welcomeLabel = new JLabel("어서오세요. 공차입니다.");
		ImageIcon dglogo = new ImageIcon("C:\\공차\\공차로고\\동국대로고.jpg");
		JPanel logoP =new JPanel();
		logoP.setPreferredSize(new Dimension(1000,200));
		logoP.add(new JLabel(dglogo));
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(logoP,BorderLayout.NORTH);
		add(welcomeLabel,BorderLayout.CENTER);
		//주문하기 버튼
		JButton orderingButton = new JButton("주문하기");
		orderingButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mf.changePanel(2);
			}
		});
		add(orderingButton,BorderLayout.SOUTH);
	}
}
