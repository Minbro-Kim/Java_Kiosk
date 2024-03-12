package Order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

class ComplainDialog extends JDialog{

	private String name,gender,number;
	private String text;
	JTextField nameF;
	JComboBox<String> genderB;
	JTextArea complain;
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	ComplainDialog(MainFrame mf){
		
		setUndecorated(true);//타이틀바 제거
		setModal(true);//다이어로그 외에 클릭 불가
		Container d = getContentPane();
		//d.setLayout(new BorderLayout());
		((JPanel)d).setBorder(new LineBorder(Color.BLACK,3));
		
		JPanel tp = new JPanel();
		tp.setPreferredSize(new Dimension(600,100));
		JLabel title = new JLabel("건의사항");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		tp.add(title);
		d.add(tp,BorderLayout.NORTH);
		
		JPanel mp = new JPanel(new BorderLayout());
		mp.setPreferredSize(new Dimension(600,250));
		JPanel wp = new JPanel(new GridLayout(4,1));
		wp.setPreferredSize(new Dimension(100,250));
		JPanel ep = new JPanel(new GridLayout(4,1));
		ep.setPreferredSize(new Dimension(500,250));
		mp.add(wp,BorderLayout.WEST);
		mp.add(ep,BorderLayout.EAST);
		wp.add(new JLabel("이름"));
		nameF = new JTextField(20);
		JPanel nameP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		nameP.add(nameF);
		ep.add(nameP);
		
		wp.add(new JLabel("성별"));
		String genders[] = {"여자","남자"};
		genderB = new JComboBox<>(genders);
		setGender("여자");//기본여자
		genderB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gender = (String) genderB.getSelectedItem();
            }
        });
		JPanel genderP = new JPanel(new FlowLayout(FlowLayout.LEFT));
		genderP.add(genderB);
		ep.add(genderP);
		
		wp.add(new JLabel("건의사항"));
		complain = new JTextArea();
		complain.setLineWrap(true);//자동 줄바꿈 설정
		JPanel complainP = new JPanel(new BorderLayout());
		
		//complain.setPreferredSize(new Dimension(300,50));
		//sp.setPreferredSize(new Dimension(300,100));
		JScrollPane sp = new JScrollPane(complain);
		complainP.add(sp);
		ep.add(complainP);
		
		
		d.add(mp,BorderLayout.CENTER);
		
		JPanel bp = new JPanel();
		bp.setPreferredSize(new Dimension(600,50));
		JButton noButton = new JButton("건너뛰기");
		noButton.addActionListener(new MyYNActionListener(this,mf));
		JButton yesButton = new JButton("제출하기");
		yesButton.addActionListener(new MyYNActionListener(this,mf));
		bp.add(noButton);
		bp.add(yesButton);
		d.add(bp,BorderLayout.SOUTH);
		
		setSize(600,400);
		setLocationRelativeTo(mf);//
		setVisible(true);
	}
	
	class MyYNActionListener implements ActionListener {
		private JDialog jd;
		private MainFrame mf;
		MyYNActionListener(JDialog jd,MainFrame mf){
			this.jd=jd;
			this.mf=mf;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			jd.dispose();
			mf.changeToInitialPanel();
			JButton bt = (JButton)e.getSource();
			if(bt.getText().equals("제출하기")) {
				name = nameF.getText();
				gender = getGender();
				text = complain.getText();
				text = text.replaceAll(","," ");//,제거
				new WriteComplain(name,gender,text);
			}
			
		}
	}
}
