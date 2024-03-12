package Order;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

class OptionDialog extends JDialog{

	private Menu menu;
	private Menu oldMenu;
	private JPanel namePanel;
	private JLabel numberLabel;
	private JLabel priceLabel;
	private JPanel basketPanel;
	private OrderingPanel.ShoppingBasketPanel sbp;
	private String buttonType;
	

	void updatePQ() {
		numberLabel.setText("수량: "+menu.getQuantity()+"개");
		priceLabel.setText("금액: "+(menu.getPrice()+menu.getOptionPrice())*menu.getQuantity()+"원");
	}

	

	OptionDialog(Menu m, JPanel p,OrderingPanel.ShoppingBasketPanel sbp,OrderingPanel op,String type){
		buttonType = type;
		switch(m.getMenuType()) {
		case "밀크티":
			MilkTea milkMenu = new MilkTea(m.getName(),m.getPrice(),m.getImage(),m.getMenuType(),m.getQuantity());
			MilkTea mt = (MilkTea)m;
			milkMenu.setLargePrice(mt.getLargePrice());
			milkMenu.setJumboPrice(mt.getJumboPrice());
			milkMenu.setIceOption(mt.isIceOption());
			milkMenu.setAmountOfIceOption(mt.getAmountOfIceOption());
			milkMenu.setSweetnessOption(mt.getSweetnessOption());
			milkMenu.setSize(mt.getSize());
			milkMenu.setPearlOption(mt.isPearlOption());
			milkMenu.setAloeOption(mt.isAloeOption());
			milkMenu.setMilkFoamOption(mt.isMilkFoamOption());
			milkMenu.setCoconutOption(mt.isCoconutOption());
			milkMenu.setOptionPrice(mt.getOptionPrice());
			menu = milkMenu;
			oldMenu = mt;
			break;
		case "커피":
			Coffee coffeeMenu = new Coffee(m.getName(),m.getPrice(),m.getImage(),m.getMenuType(),m.getQuantity());
			Coffee cf = (Coffee)m;
			coffeeMenu.setLargePrice(cf.getLargePrice());
			coffeeMenu.setJumboPrice(cf.getJumboPrice());
			coffeeMenu.setIceOption(cf.isIceOption());
			coffeeMenu.setAmountOfIceOption(cf.getAmountOfIceOption());
			coffeeMenu.setSize(cf.getSize());
			coffeeMenu.setSyrupOption(cf.isSyrupOption());
			coffeeMenu.setShotOption(cf.isShotOption());
			coffeeMenu.setOptionPrice(cf.getOptionPrice());
			menu = coffeeMenu;
			oldMenu = cf;
			break;
		case "디저트":
			menu = new Menu(m.getName(),m.getPrice(),m.getImage(),m.getMenuType(),m.getQuantity());
			menu.setOptionPrice(m.getOptionPrice());
			oldMenu = m;
			break;
		}
		
		this.sbp = sbp;
		basketPanel = sbp.getBasketPanel();
		
		setUndecorated(true);//타이틀바 제거
		setModal(true);//다이어로그 외에 클릭 불가
		
		Container d = getContentPane();
		((JPanel)d).setBorder(new LineBorder(Color.BLACK,3));
		//왼쪽 패널
		JPanel westPanel = new JPanel(new BorderLayout());
		westPanel.setPreferredSize(new Dimension(200,400));
		d.add(westPanel,BorderLayout.WEST);
		
		//이미지 패널
		JPanel imagePanel = new JPanel(new BorderLayout());
		imagePanel.setBackground(Color.WHITE);
		imagePanel.setPreferredSize(new Dimension(200,300));
		westPanel.add(imagePanel, BorderLayout.CENTER);
		
		ImageIcon image = MenuButton.changeImageSize(menu.getImage(),180,200);
		JLabel imageLabel = new JLabel(image);
		imagePanel.add(imageLabel);
		
		
		//이름, 가격 패널
		namePanel = new JPanel(new GridLayout(3,1));
		namePanel.setBackground(Color.WHITE);
		namePanel.setPreferredSize(new Dimension(200,100));
		westPanel.add(namePanel, BorderLayout.SOUTH);
		//이름, 가격 라벨
		
		JLabel nameLabel = new JLabel(menu.getName());
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		numberLabel = new JLabel("수량: "+menu.getQuantity()+"개");
		numberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		priceLabel = new JLabel("금액: "+(menu.getPrice()+menu.getOptionPrice())*menu.getQuantity()+"원");
		priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		namePanel.add(nameLabel);
		namePanel.add(numberLabel);
		namePanel.add(priceLabel);
		
		
		
		//오른쪽 패널
		JPanel eastPanel = new JPanel(new BorderLayout());
		eastPanel.setPreferredSize(new Dimension(400,400));
		d.add(eastPanel);
		//옵션 패널
		JPanel optionPanel = new JPanel();
		optionPanel.setPreferredSize(new Dimension(400,350));
		eastPanel.add(optionPanel,BorderLayout.CENTER);
		
		//밀크티 옵션
		if(menu.getMenuType().equals("밀크티")) {
			MilkTea milkTea = (MilkTea)menu;
			JScrollPane sp =new JScrollPane(new MilkTeaOptionPanel(milkTea,this));
			sp.setPreferredSize(new Dimension(400,350));
			sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//수평 스크롤 제거
			eastPanel.add(sp);
		}
		//커피 옵션
		if(menu.getMenuType().equals("커피")) {
			Coffee coffee = (Coffee)menu;
			JScrollPane sp2 =new JScrollPane(new CoffeeOptionPanel(coffee,this));
			sp2.setPreferredSize(new Dimension(400,350));
			sp2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//수평 스크롤 제거
			eastPanel.add(sp2);
			
		}
		//디저트 옵션
		if(menu.getMenuType().equals("디저트")) {
			Menu desert = menu;
			JScrollPane sp3 =new JScrollPane(new OptionPanel(desert,this));
			sp3.setPreferredSize(new Dimension(400,350));
			sp3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//수평 스크롤 제거
			eastPanel.add(sp3);
			
		}
		//버튼 패널
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setPreferredSize(new Dimension(400,50));
		eastPanel.add(buttonsPanel,BorderLayout.SOUTH);
		
		JButton cancleBtn = new JButton("취소");
		cancleBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		//장바구니에 추가
		JButton addBtn = new JButton("장바구니 담기");
		if(buttonType.equals("basket"))addBtn.setText("장바구니 수정");
		addBtn.addActionListener(new MyBasketActionListener(menu,basketPanel,sbp,this,op));
		buttonsPanel.add(cancleBtn);
		buttonsPanel.add(addBtn);
		setSize(600,400);
		setLocationRelativeTo(p);//메뉴탭 가운데에 위치하도록함.
		setVisible(true);
		
	}
	//장바구니 담기 버튼 리스너
	class MyBasketActionListener implements ActionListener{
		private JPanel basketPanel;
		private Menu menu;
		private OrderingPanel.ShoppingBasketPanel sbp;
		private OptionDialog optionDialog;
		private Vector<Menu>  menuVector = new Vector<>();
		
		MyBasketActionListener(Menu m,JPanel bkp,OrderingPanel.ShoppingBasketPanel sbp,OptionDialog od,OrderingPanel op){
			basketPanel = bkp;
			menu = m;
			this.sbp = sbp;
			optionDialog = od;
			menuVector = op.getMenuVector();
		}

		int checkSameMenu(Menu tm) {
			int targetIndex = -1;
			for(int i=0;i<menuVector.size();i++) {
				if(targetIndex>=0)break;
				if(tm.getMenuType().equals(menuVector.get(i).getMenuType())) {
					switch(menuVector.get(i).getMenuType()) {
					 case "밀크티":
						 MilkTea Mmenu = (MilkTea)menuVector.get(i);
						 MilkTea Tmenu = (MilkTea)tm;
						 if((Tmenu.getName().equals(Mmenu.getName()))&&(Tmenu.getSize().equals(Mmenu.getSize()))
								 &&(Tmenu.isIceOption()==Mmenu.isIceOption())&&(Tmenu.getAmountOfIceOption().equals(Mmenu.getAmountOfIceOption()))
								 &&(Tmenu.getSweetnessOption()==Mmenu.getSweetnessOption())&&(Tmenu.isAloeOption()==Mmenu.isAloeOption())
								 &&(Tmenu.isPearlOption()==Mmenu.isPearlOption())&&(Tmenu.isCoconutOption()==Mmenu.isCoconutOption())&&(Tmenu.isMilkFoamOption()==Mmenu.isMilkFoamOption())) {
							 oldMenu=Mmenu;
							 targetIndex=i;
						 }
								 
						 break;
					 case "커피":
						 Coffee Cmenu = (Coffee)menuVector.get(i);
						 Coffee Tcmenu = (Coffee)tm;
						 if((Tcmenu.getName().equals(Cmenu.getName()))&&(Tcmenu.getSize().equals(Cmenu.getSize()))
								 &&(Tcmenu.isIceOption()==Cmenu.isIceOption())&&(Tcmenu.getAmountOfIceOption().equals(Cmenu.getAmountOfIceOption()))
								 &&(Tcmenu.isShotOption()==Cmenu.isShotOption())&&(Tcmenu.isSyrupOption()==Cmenu.isSyrupOption())) {
							 oldMenu=Cmenu;
							 targetIndex=i;
						 }
						 break;
					 case "디저트":
						 Menu Dmenu = menuVector.get(i);
						 Menu Tdmenu = tm;
						 if(Tdmenu.getName().equals(Dmenu.getName())) {
							 oldMenu=Dmenu;
							 targetIndex=i;
						 }
						 break;
					 }
				}
			 }
			return targetIndex;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			optionDialog.dispose();
			if(buttonType.equals("menuTab")) {
				int tindex=checkSameMenu(menu);
				if(tindex<0) {
					sbp.setBasketNumber(sbp.getBasketNumber()+menu.getQuantity());
					sbp.setBasketPrice(sbp.getBasketPrice()+(menu.getPrice()+menu.getOptionPrice())*menu.getQuantity());
					sbp.updateBasketNP();
					switch(menu.getMenuType()) {
					case "밀크티":
						MilkTea milkTea = (MilkTea)menu;
						BasketMenuButton basketMilkTeaMenuButton = new BasketMenuButton(milkTea,basketPanel,sbp,optionDialog,menuVector,-1);
						sbp.updateBasketPanelSize();
						menuVector.add(milkTea);
						break;
					case "커피":
						Coffee coffee = (Coffee)menu;
						BasketMenuButton basketCoffeeMenuButton = new BasketMenuButton(coffee,basketPanel,sbp,optionDialog,menuVector,-1);
						sbp.updateBasketPanelSize();
						menuVector.add(coffee);
						break;
					case "디저트":
						Menu desert = menu;
						BasketMenuButton basketMenuButton = new BasketMenuButton(desert,basketPanel,sbp,optionDialog,menuVector,-1);
						sbp.updateBasketPanelSize();
						menuVector.add(desert);
						break;
					}
				}
				else if(tindex>=0){
					menu.setQuantity(oldMenu.getQuantity()+menu.getQuantity());
					sbp.setBasketNumber(sbp.getBasketNumber()-oldMenu.getQuantity()+menu.getQuantity());
					sbp.setBasketPrice(sbp.getBasketPrice()-(oldMenu.getPrice()+oldMenu.getOptionPrice())*oldMenu.getQuantity()+(menu.getPrice()+menu.getOptionPrice())*menu.getQuantity());
					sbp.updateBasketNP();
					switch(menu.getMenuType()) {
					case "밀크티":
						MilkTea milkTea = (MilkTea)menu;
						menuVector.set(tindex, milkTea);
						basketPanel.remove(tindex);
						BasketMenuButton basketMilkTeaMenuButton = new BasketMenuButton(milkTea,basketPanel,sbp,optionDialog,menuVector,tindex);
						basketPanel.repaint();
						basketPanel.revalidate();
						break;
					case "커피":
						Coffee coffee = (Coffee)menu;
						menuVector.set(tindex, coffee);
						basketPanel.remove(tindex);
						BasketMenuButton basketCoffeeMenuButton = new BasketMenuButton(coffee,basketPanel,sbp,optionDialog,menuVector,tindex);
						basketPanel.repaint();
						basketPanel.revalidate();
						break;
					case "디저트":
						Menu desert = menu;
						menuVector.set(tindex, desert);
						basketPanel.remove(tindex);
						BasketMenuButton basketDesertMenuButton = new BasketMenuButton(desert,basketPanel,sbp,optionDialog,menuVector,tindex);
						basketPanel.repaint();
						basketPanel.revalidate();
						break;
					}
				}
				
			}
			else if(buttonType.equals("basket")) {
				sbp.setBasketNumber(sbp.getBasketNumber()-oldMenu.getQuantity()+menu.getQuantity());
				sbp.setBasketPrice(sbp.getBasketPrice()-(oldMenu.getPrice()+oldMenu.getOptionPrice())*oldMenu.getQuantity()+(menu.getPrice()+menu.getOptionPrice())*menu.getQuantity());
				sbp.updateBasketNP();
				int t1index=checkSameMenu(oldMenu);//옵션변경전메뉴
				switch(menu.getMenuType()) {
				case "밀크티":
					MilkTea milkTea = (MilkTea)menu;
					int t2index=checkSameMenu(milkTea);
					if((t2index>=0)&&(t1index!=t2index)) {
						milkTea.setQuantity(milkTea.getQuantity()+menuVector.get(t2index).getQuantity());
						if(t1index>t2index) {
							int a= t1index;
							t1index=t2index;
							t2index=a;//항상 t1<t2
						}
						basketPanel.remove(t2index);
						basketPanel.remove(t1index);
						BasketMenuButton basketMilkTeaMenuButton = new BasketMenuButton(milkTea,basketPanel,sbp,optionDialog,menuVector,t1index);
						menuVector.set(t1index, milkTea);
						menuVector.remove(t2index);
						
					}
					else {
						basketPanel.remove(t1index);
						menuVector.set(t1index, milkTea);
						BasketMenuButton basketMilkTeaMenuButton = new BasketMenuButton(milkTea,basketPanel,sbp,optionDialog,menuVector,t1index);
					}
					basketPanel.repaint();
					basketPanel.revalidate();
					sbp.updateBasketPanelSize();
					break;
				case "커피":
					Coffee coffee = (Coffee)menu;
					int c2index=checkSameMenu(coffee);
					if((c2index>=0)&&(t1index!=c2index)) {
						coffee.setQuantity(coffee.getQuantity()+menuVector.get(c2index).getQuantity());
						if(t1index>c2index) {
							int a= t1index;
							t1index=c2index;
							c2index=a;//항상 t1<c2
						}
						basketPanel.remove(c2index);
						basketPanel.remove(t1index);
						BasketMenuButton basketCoffeeMenuButton = new BasketMenuButton(coffee,basketPanel,sbp,optionDialog,menuVector,t1index);
						menuVector.set(t1index, coffee);
						menuVector.remove(c2index);
						
					}
					else {
						basketPanel.remove(t1index);
						menuVector.set(t1index, coffee);
						BasketMenuButton basketCoffeeMenuButton = new BasketMenuButton(coffee,basketPanel,sbp,optionDialog,menuVector,t1index);
					}
					basketPanel.repaint();
					basketPanel.revalidate();
					sbp.updateBasketPanelSize();
					break;
				case "디저트":
					Menu desert = menu;
					int d2index=checkSameMenu(desert);
					if((d2index>=0)&&(t1index!=d2index)) {
						desert.setQuantity(desert.getQuantity()+menuVector.get(d2index).getQuantity());
						if(t1index>d2index) {
							int a= t1index;
							t1index=d2index;
							d2index=a;//항상 t1<d2
						}
						basketPanel.remove(d2index);
						basketPanel.remove(t1index);
						BasketMenuButton basketDesertMenuButton = new BasketMenuButton(desert,basketPanel,sbp,optionDialog,menuVector,t1index);
						menuVector.set(t1index, desert);
						menuVector.remove(d2index);
						
					}
					else {
						menuVector.set(t1index, desert);
						basketPanel.remove(t1index);
						BasketMenuButton basketMilkTeaMenuButton = new BasketMenuButton(desert,basketPanel,sbp,optionDialog,menuVector,t1index);
					}
					basketPanel.repaint();
					basketPanel.revalidate();
					sbp.updateBasketPanelSize();
					break;
				}
			}
		}
	}
	
}
