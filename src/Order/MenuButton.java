package Order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class MenuButton {
	private Menu menu;
	private ImageIcon menuImage;
	private int w,h;
	
	MenuButton(Menu m,JPanel p,OrderingPanel.ShoppingBasketPanel sbp,OrderingPanel op){
		menu =m;
		menuImage = menu.getImage();
		w = 105;
		h = 125;
		menuImage = changeImageSize(menuImage,w,h);
		
		JButton imageButton = new JButton(menuImage);
		imageButton.setBackground(Color.WHITE);
		imageButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				OptionDialog od = new OptionDialog(menu,p,sbp,op,"menuTab");

			}
		});		
		JPanel menuButtonPanel = new JPanel(new BorderLayout());
		JPanel tp = new JPanel(new FlowLayout());
		tp.setPreferredSize(new Dimension(imageButton.getSize()));
		tp.add(imageButton);
		menuButtonPanel.add(tp,BorderLayout.CENTER);
		
		JPanel bp = new JPanel(new FlowLayout());
		JLabel menuName = new JLabel(menu.getName());
		menuName.setHorizontalAlignment(SwingConstants.CENTER);
		menuName.setVerticalAlignment(SwingConstants.CENTER);
		bp.setPreferredSize(new Dimension(150,40));
		bp.add(menuName);
		menuButtonPanel.add(bp,BorderLayout.SOUTH);
		
		p.add(menuButtonPanel);
	}
	
	public static ImageIcon changeImageSize(ImageIcon i, int w, int h) {
		Image image = i.getImage();
		int width = w;
		int height = h;
		Image scaledImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
		return scaledImageIcon;
	}
}


class BasketMenuButton{
	private Menu menu;
	private JPanel cp;//이름,가격, 옵션 표기 패널
	public JPanel getCp() {
		return cp;
	}

	public void setCp(JPanel cp) {
		this.cp = cp;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	void writeCp(Menu m) {
		menu = m;
		JLabel menuName = new JLabel(menu.getName());
		cp.add(menuName);
		
		cp.add(new JLabel("가격: "+(menu.getPrice()+menu.getOptionPrice())*menu.getQuantity()+"원"));
		cp.add(new JLabel("수량: "+menu.getQuantity()+"개"));
		
		switch(menu.getMenuType()) {
		case "밀크티":
			cp.setLayout(new GridLayout(7,1));
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
			cp.setLayout(new GridLayout(6,1));
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
			cp.setLayout(new GridLayout(3,1));
		}
	}
	BasketMenuButton(Menu m, JPanel p, OrderingPanel.ShoppingBasketPanel sbp,OptionDialog od,Vector<Menu>  menuVector, int index){
		menu=m;
		ImageIcon menuImage = menu.getImage();
		menuImage = MenuButton.changeImageSize(menuImage,65,70);
		JButton imageButton = new JButton(menuImage);
		imageButton.setBackground(Color.WHITE);
		imageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				OrderingPanel op = (OrderingPanel)(((p.getParent()).getParent()).getParent()).getParent();
				OptionDialog basketDialog = new OptionDialog(menu,op.getMtp(),sbp,op,"basket");				
			}
		});
		
		JPanel menuButtonPanel = new JPanel(new BorderLayout());
		menuButtonPanel.setPreferredSize(new Dimension(300,210));
		JPanel tp = new JPanel(new FlowLayout());
		tp.add(imageButton);
		menuButtonPanel.add(tp,BorderLayout.NORTH);
		
		cp = new JPanel(new FlowLayout());
		writeCp(menu);
		menuButtonPanel.add(cp,BorderLayout.CENTER);
		
		JButton deleteButton = new JButton("삭제");
		
		deleteButton.addActionListener(new MydeleteAcionListener(p,sbp,od,menuButtonPanel,menuVector));
	
		menuButtonPanel.add(deleteButton,BorderLayout.SOUTH);
		if(index>=0)p.add(menuButtonPanel,index);
		else p.add(menuButtonPanel);
		
	}
	
	class MydeleteAcionListener implements ActionListener{
		JPanel p;
		OrderingPanel.ShoppingBasketPanel sbp;
		OptionDialog od;
		JPanel menuButtonPanel;
		Vector<Menu>  menuVector = new Vector<>();
		MydeleteAcionListener(JPanel p,OrderingPanel.ShoppingBasketPanel sbp,OptionDialog od,JPanel menuButtonPanel,Vector<Menu>  menuVector){
			this.p=p;
			this.sbp=sbp;
			this.od=od;
			this.menuButtonPanel=menuButtonPanel;
			this.menuVector = menuVector;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			sbp.setBasketNumber(sbp.getBasketNumber()-menu.getQuantity());
			sbp.setBasketPrice(sbp.getBasketPrice()-(menu.getPrice()+menu.getOptionPrice())*menu.getQuantity());
			sbp.updateBasketNP();
			menuVector.remove(menu);
			p.remove(menuButtonPanel);
			p.repaint();//패널 업데이트
			sbp.updateBasketPanelSize();
		}
	}
	
}