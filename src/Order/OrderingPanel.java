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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

//주문 화면
class OrderingPanel extends JPanel{
	private ShoppingBasketPanel sbp;
	private MenuTabPanel mtp;
	public MenuTabPanel getMtp() {
		return mtp;
	}

	private Vector<Menu> menuVector = new Vector<>();
	public ShoppingBasketPanel getSbp() {
		return sbp;
	}

	public void setSbp(ShoppingBasketPanel sbp) {
		this.sbp = sbp;
	}
	
	public Vector<Menu> getMenuVector() {
		return menuVector;
	}

	public void setMenuVector(Vector<Menu>  menuVector) {
		this.menuVector = menuVector;
	}

	OrderingPanel(MainFrame m){
		sbp = new ShoppingBasketPanel(m);
		setLayout(new BorderLayout());
		//상단패널
		TopPanel tp = new TopPanel();
		add(tp,BorderLayout.NORTH);
		//메뉴탭패널
		mtp = new MenuTabPanel(this);
		add(mtp,BorderLayout.CENTER);
		//장바구니 패널
		
		add(sbp,BorderLayout.EAST);
;		
		
	}
	//상단패널
	class TopPanel extends JPanel{
		TopPanel(){
			
			setPreferredSize(new Dimension(1000,63));
			setBackground(new Color(150,0,0));
			setLayout(new BorderLayout());
			
			JLabel cafeName = new JLabel("공차");
			cafeName.setHorizontalAlignment(SwingConstants.CENTER);
			add(cafeName,BorderLayout.CENTER);
		}
		
	}
	
	//메뉴 탭 패널
	class MenuTabPanel extends JPanel{
		
		
		MenuTabPanel(OrderingPanel op){
			setPreferredSize(new Dimension(700,600));
			setLayout(new BorderLayout());
			//메뉴별 탭
			ReadMenu rm = new ReadMenu(this);//메뉴 읽어오기
			
			//밀크티 탭
			JPanel milkTeaTab = new JPanel(new GridLayout(3,3));
			MilkTea milkTea[] = rm.getMilkTea();
			int milkTeaNumber = rm.getMilkTeaNumber();
			MenuButton mb[] = new MenuButton[milkTeaNumber];
			for(int i=0;i<milkTeaNumber;i++) {
				mb[i]= new MenuButton(milkTea[i],milkTeaTab,sbp,op);
			}
			
			//커피 탭
			JPanel coffeeTab = new JPanel(new GridLayout(3,3));
			Coffee coffee[] = rm.getCoffee();
			int coffeeNumber = rm.getCoffeeNumber();
			MenuButton coffeeMb[] = new MenuButton[coffeeNumber];
			for(int i=0;i<coffeeNumber;i++) {
				coffeeMb[i]= new MenuButton(coffee[i],coffeeTab,sbp,op);
			}
			
			//디저트탭
			JPanel desertTab = new JPanel(new GridLayout(3,3));
			Menu desert[] = rm.getDesert();
			int desertNumber = rm.getDesertNumber();
			MenuButton desertMb[] = new MenuButton[desertNumber];
			for(int i=0;i<desertNumber;i++) {
				desertMb[i]= new MenuButton(desert[i],desertTab,sbp,op);
			}
			JTabbedPane tp = new JTabbedPane();
			tp.addTab("밀크티", milkTeaTab);
			tp.addTab("커피", coffeeTab);
			tp.addTab("디저트", desertTab);
			
			add(tp);

		}
	}
	
	//장바구니 패널
	class ShoppingBasketPanel extends JPanel{
		private int basketNumber;
		private int basketPrice;
		private JPanel basketPanel;
		private JLabel totalNumberLabel;
		private JLabel totalPriceLabel;
		
		public int getBasketNumber() {
			return basketNumber;
		}

		public int getBasketPrice() {
			return basketPrice;
		}

		public void setBasketNumber(int basketNumber) {
			this.basketNumber = basketNumber;
		}

		public void setBasketPrice(int basketPrice) {
			this.basketPrice = basketPrice;
		}

		public JPanel getBasketPanel() {
			return basketPanel;
		}

		public void setBasketPanel(JPanel basketPanel) {
			this.basketPanel = basketPanel;
		}
		
		void updateBasketNP() {
			totalNumberLabel.setText("총 수량: "+basketNumber+"개");
			totalPriceLabel.setText("총 금액: "+basketPrice+"원");
		}
		void updateBasketPanelSize() {
			int basketNumber = basketPanel.getComponentCount();
			int height = 215*basketNumber;
			if(basketNumber<=2) {
				basketPanel.setPreferredSize(new Dimension(300,470));
				basketPanel.revalidate();
			}
			else if(basketNumber>2) {
				basketPanel.setPreferredSize(new Dimension(300,height));
				basketPanel.revalidate();
			}
			
		}

		ShoppingBasketPanel(MainFrame mf){
			setPreferredSize(new Dimension(300,600));
			
			setLayout(new BorderLayout());
			//장바구니 칸
			basketPanel = new JPanel(new FlowLayout());
			basketPanel.setPreferredSize(new Dimension(300,470));
			
			JScrollPane scrollP = new JScrollPane(basketPanel);//스크롤팬 부착
			scrollP.setPreferredSize(new Dimension(300,470));
			scrollP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);//수평 스크롤 제거
			add(scrollP,BorderLayout.NORTH);
			//총가격 칸
			JPanel showTotalPricePanel = new JPanel(new GridLayout(2,1));
			showTotalPricePanel.setPreferredSize(new Dimension(300,60));
			totalNumberLabel = new JLabel("개수: "+basketNumber+"개");
			totalPriceLabel = new JLabel("총 금액: "+basketPrice+"원");
			
			showTotalPricePanel.add(totalNumberLabel);
			showTotalPricePanel.add(totalPriceLabel);
			add(showTotalPricePanel,BorderLayout.CENTER);
			//결제,취소 버튼 칸
			JPanel paymentPanel = new JPanel(new GridLayout(1,2));
			paymentPanel.setPreferredSize(new Dimension(300,70));
			JButton cancleButton = new JButton("취소");
			cancleButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mf.changeToInitialPanel();;//초기화면으로 돌아가기
				}
			});
			JButton paymentButton = new JButton("결제");	
			paymentButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if(basketNumber==0) {
						JOptionPane.showMessageDialog(mf,"선택된 상품이 없습니다.");
					}
					else mf.changePanel(4);
					
				}
			});
			paymentButton.setBackground(new Color(250,50,0));
			paymentPanel.add(cancleButton);
			paymentPanel.add(paymentButton);
			add(paymentPanel,BorderLayout.SOUTH);
			
		}
	}
}

