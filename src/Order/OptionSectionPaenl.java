package Order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

class OptionSectionPanel extends JPanel{
	
	OptionSectionPanel(String optionTitle, JPanel optionP){
		setLayout(new BorderLayout());
		setBackground(Color.YELLOW);
		JLabel optionLabel = new JLabel(optionTitle);//옵션 이름
		optionLabel.setPreferredSize(new Dimension(400,30));
		Font customFont = new Font("고딕",Font.BOLD, 15);
		optionLabel.setFont(customFont);
		add(optionLabel,BorderLayout.NORTH);
		add(optionP,BorderLayout.CENTER);
		
	}
}
