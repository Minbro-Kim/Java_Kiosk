package Order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

class ReciptPanel extends JPanel{

	private Vector<Menu> menuVector = new Vector<>();
	private int basketNumber;
	private int basketPrice;
	private OrderingPanel op;
	private OrderingPanel.ShoppingBasketPanel sbp;
	private MainFrame mf;
	private JPanel cp;
	
	ReciptPanel(MainFrame mf,OrderingPanel op){
		setLayout(new BorderLayout());
		this.op = op;
		this.mf = mf;
		
		menuVector = op.getMenuVector();
		sbp = op.getSbp();
		basketNumber = sbp.getBasketNumber();
		basketPrice = sbp.getBasketPrice();
		JPanel totalPanelP = new JPanel(new GridLayout(2,1));
		totalPanelP.setPreferredSize(new Dimension(300,70));
		totalPanelP.add(new JLabel("총수량: "+basketNumber+"개"));
		totalPanelP.add(new JLabel("결제금액: "+basketPrice+"원"));
		JPanel totalPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		totalPanel.add(totalPanelP);
		
		JPanel tp = new JPanel();
		tp.setPreferredSize(new Dimension(1000,63));
		JPanel ep = new JPanel();
		ep.setPreferredSize(new Dimension(350,400));
		JPanel wp = new JPanel();
		wp.setPreferredSize(new Dimension(350,400));
		add(tp,BorderLayout.NORTH);
		add(ep,BorderLayout.EAST);
		add(wp,BorderLayout.WEST);
		JPanel rp = new JPanel(new GridLayout(menuVector.size()+1,1));
		JLabel title = new JLabel("결제 내역");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		add(title,BorderLayout.NORTH);
		rp.setBackground(Color.WHITE);
		
		
		for(int i=0;i<menuVector.size();i++) {
			cp = new JPanel();
			cp.setPreferredSize(new Dimension(300,150));
			Menu menu = menuVector.get(i); 
			cp.add(new JLabel(menu.getName()));
			cp.add(new JLabel("가격: "+(menu.getPrice()+menu.getOptionPrice())*menu.getQuantity()+"원"));
			cp.add(new JLabel("수량: "+menu.getQuantity()+"개"));
			switch(menu.getMenuType()) {
			case "밀크티":
				cp.setLayout(new GridLayout(8,1));
				MilkTea mt = (MilkTea)menu;
				String iceString;
				if(mt.isIceOption()==true) {
					iceString = "ICE";
					cp.add(new JLabel("옵션: "+iceString+"/"+ mt.getAmountOfIceOption()));
				}
				else {
					iceString="HOT";
					cp.add(new JLabel("옵션: "+iceString));
				}
				
				cp.add(new JLabel("당도: "+mt.getSweetnessOption()+"%"));
				cp.add(new JLabel("크기: "+mt.getSize()));
				
				String pearlString="";
				String coconutString="";
				String milkFoamString="";
				String aloeString="";
				
				if(mt.isPearlOption()==true) {
					pearlString = "펄 ";
				}
				if(mt.isCoconutOption()==true) {
					coconutString = "코코넛 ";
				}
				if(mt.isMilkFoamOption()==true) {
					milkFoamString = "밀크폼 ";
				}
				if(mt.isAloeOption()==true) {
					aloeString = "알로에 ";
				}
				
				cp.add(new JLabel("추가 :"+pearlString+coconutString+milkFoamString+aloeString));
				break;
			case "커피":
				cp.setLayout(new GridLayout(7,1));
				Coffee coffee = (Coffee)menu;
				String coffeeIceString;
				if(coffee.isIceOption()==true) {
					coffeeIceString = "ICE";
					cp.add(new JLabel("옵션: "+coffeeIceString+"/"+ coffee.getAmountOfIceOption()));
				}
				else {
					coffeeIceString="HOT";
					cp.add(new JLabel("옵션: "+coffeeIceString));
				}
				
				cp.add(new JLabel("크기: "+coffee.getSize()));
				
				String syrupString="";
				String shotString="";
			
				if(coffee.isSyrupOption()==true) {
					syrupString = "시럽 ";
				}
				if(coffee.isShotOption()==true) {
					shotString = "샷추가 ";
				}
						
				cp.add(new JLabel("추가 :"+syrupString+shotString));
				break;
				
			case "디저트":
				cp.setLayout(new GridLayout(4,1));
			}
			
			cp.add(new JLabel("------------------------------------------------"));
			rp.add(cp);
		}
		rp.add(totalPanel);
		JScrollPane jp = new JScrollPane(rp);
		jp.setPreferredSize(new Dimension(300,400));
		jp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//수평 스크롤 제거
	
		add(jp,BorderLayout.CENTER);
		JButton exitButton = new JButton("확인");
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showD();
				
			}
		});
		add(exitButton,BorderLayout.SOUTH);
		new WriteFile(menuVector,basketNumber,basketPrice);
	}
	void showD() {
		ComplainDialog cd = new ComplainDialog(mf);
	}
}
