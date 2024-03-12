package Order;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class CoffeeOptionPanel extends JPanel{

	private OptionSectionPanel osp;
	private Coffee coffee;
	private OptionDialog od;
	
	CoffeeOptionPanel(Coffee coffee,OptionDialog od){
		this.coffee = coffee;
		this.od=od;
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(400,340));
		
		JPanel amountOfIceOptionPanel = new JPanel();
		osp = new OptionSectionPanel("얼음량", amountOfIceOptionPanel);
		//아이스 옵션
		JPanel iceOptionPanel = new JPanel();
		ButtonGroup iceButtonGroup = new ButtonGroup();
		JRadioButton iceRadioBtn = new JRadioButton("ICE");
		iceRadioBtn.addItemListener(new MyCoffeeItemListener(this,osp,coffee,od));
		JRadioButton hotRadioBtn = new JRadioButton("HOT");
		iceButtonGroup.add(iceRadioBtn);
		iceButtonGroup.add(hotRadioBtn);
		iceOptionPanel.add(iceRadioBtn);
		iceOptionPanel.add(hotRadioBtn);
		//옵션에 따라 버튼이 선택되어 있도록
		if(coffee.isIceOption()==true) {//아이스면
			iceRadioBtn.setSelected(true);
		}
		else {
			hotRadioBtn.setSelected(true);
			update(this,osp);//핫 선택시 얼음량 옵션 안보이게
		}
		add(new OptionSectionPanel("아이스/핫", iceOptionPanel));
		
		//얼음량 옵션
		ButtonGroup amountBG = new ButtonGroup();
		JRadioButton smallRB = new JRadioButton("적음");
		JRadioButton middleRB = new JRadioButton("보통");
		JRadioButton largeRB = new JRadioButton("많음");
		smallRB.addItemListener(new MyCoffeeItemListener(this,osp,coffee,od ));
		middleRB.addItemListener(new MyCoffeeItemListener(this,osp,coffee,od));
		largeRB.addItemListener(new MyCoffeeItemListener(this,osp,coffee,od));
		amountBG.add(smallRB);
		amountBG.add(middleRB);
		amountBG.add(largeRB);
		amountOfIceOptionPanel.add(smallRB);
		amountOfIceOptionPanel.add(middleRB);
		amountOfIceOptionPanel.add(largeRB);
		//옵션에 따라 버튼이 선택되어 있도록
		if(coffee.getAmountOfIceOption().equals("적음")) {
			smallRB.setSelected(true);
		}
		else if(coffee.getAmountOfIceOption().equals("보통")){
			middleRB.setSelected(true);
		}
		else if(coffee.getAmountOfIceOption().equals("많음")) {
			largeRB.setSelected(true);
		}
		add(osp);
		
		//사이즈 옵션
		JPanel sizeOptionPanel = new JPanel();
		ButtonGroup sizeBG = new ButtonGroup();
		JRadioButton largeSizeRB = new JRadioButton("Large");
		sizeBG.add(largeSizeRB);
		largeSizeRB.addItemListener(new MyCoffeeItemListener(this,osp,coffee,od ));
		JRadioButton JumboSizeRB = new JRadioButton("Jumbo");
		sizeBG.add(JumboSizeRB);
		JumboSizeRB.addItemListener(new MyCoffeeItemListener(this,osp,coffee,od ));
		sizeOptionPanel.add(largeSizeRB);
		if(coffee.getJumboPrice()!=0)
		sizeOptionPanel.add(JumboSizeRB);
		//옵션에 따라 버튼이 선택되어 있도록
		if(coffee.getSize().equals("Large")) {//라지면
			largeSizeRB.setSelected(true);
		}
		else if(coffee.getSize().equals("Jumbo")){
			JumboSizeRB.setSelected(true);
		}
		add(new OptionSectionPanel("사이즈", sizeOptionPanel));
		
		//추가 옵션(시럽, 샷추가)
		JPanel extraOptionPanel = new JPanel();
		JCheckBox syrupCB = new JCheckBox("시럽");
		JCheckBox shotCB = new JCheckBox("샷추가");
		syrupCB.addItemListener(new MyCoffeeItemListener(this,osp,coffee,od ));
		shotCB.addItemListener(new MyCoffeeItemListener(this,osp,coffee,od ));
		extraOptionPanel.add(syrupCB);
		extraOptionPanel.add(new JLabel("(+100원)"));
		extraOptionPanel.add(shotCB);
		extraOptionPanel.add(new JLabel("(+500원)"));
		if(coffee.isSyrupOption()==true) {
			syrupCB.setSelected(true);
			coffee.setOptionPrice(coffee.getOptionPrice()-100);
			od.updatePQ();
		}
		else shotCB.setSelected(false);
		if(coffee.isShotOption()==true) {
			shotCB.setSelected(true);
			coffee.setOptionPrice(coffee.getOptionPrice()-500);
			od.updatePQ();
		}
		else shotCB.setSelected(false);
		add(new OptionSectionPanel("추가옵션", extraOptionPanel));
		
		//수량 옵션
		JPanel quantityOptionPanel = new JPanel();
		SpinnerModel spinnerModel = new SpinnerNumberModel(coffee.getQuantity(), 1, 30, 1); // 초기값, 최소값, 최대값, 증가값
		JSpinner quantitySpinner = new JSpinner(spinnerModel);
		quantitySpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				coffee.setQuantity((int)quantitySpinner.getValue());
				od.updatePQ();
			}
		});
        quantityOptionPanel.add(quantitySpinner);
        add(new OptionSectionPanel("수량 옵션", quantityOptionPanel));
	}
	
	void update(CoffeeOptionPanel p,OptionSectionPanel op) {
		if(coffee.isIceOption()==false) {
			op.setVisible(false);
			p.setPreferredSize(new Dimension(400,300));//옵션이 없어지기때문에 패널 크기 줄이기
		}
		else if(coffee.isIceOption()==true) {
			op.setVisible(true);
			p.setPreferredSize(new Dimension(400,340));//패널크기 원래대로 늘리기
		}
		p.revalidate();
	}
	
	class MyCoffeeItemListener implements ItemListener{
		CoffeeOptionPanel parentPanel;
		OptionSectionPanel op;
		Coffee coffee;
		OptionDialog od;
		
		MyCoffeeItemListener(CoffeeOptionPanel p, OptionSectionPanel op, Coffee cf, OptionDialog od){
			this.op = op;
			parentPanel =p;
			coffee = cf;
			this.od = od;
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() instanceof JRadioButton) {
				JRadioButton rb = (JRadioButton)e.getSource();
				if (e.getStateChange()==ItemEvent.SELECTED) {
					switch(rb.getText()) {
						case "ICE":
							coffee.setIceOption(true);
							parentPanel.update(parentPanel,op);
							break;
						case "적음":
						case "보통":
			            case "많음":
			            	coffee.setAmountOfIceOption(rb.getText());
							break;
			            case "Large":
			            	coffee.setPrice(coffee.getLargePrice());
			            	coffee.setSize("Large");
			            	od.updatePQ();
			            	break;
			            case "Jumbo":
			            	coffee.setPrice(coffee.getJumboPrice());
			            	coffee.setSize("Jumbo");
			            	od.updatePQ();
			            	break;
					}
					
				}
				else if (e.getStateChange()==ItemEvent.DESELECTED) {
					switch(rb.getText()) {
					case "ICE":
						coffee.setIceOption(false);
						parentPanel.update(parentPanel,op);
						break;
					}
				}
			}
			else if (e.getSource() instanceof JCheckBox) {
				JCheckBox cb = (JCheckBox)e.getSource();
				if (e.getStateChange()==ItemEvent.SELECTED) {
					switch(cb.getText()) {
					case "시럽":
						coffee.setSyrupOption(true);
						coffee.setOptionPrice(coffee.getOptionPrice()+100);
						od.updatePQ();
						break;
					case "샷추가":
						coffee.setShotOption(true);
						coffee.setOptionPrice(coffee.getOptionPrice()+500);
						od.updatePQ();
						break;
					
					}
				}
				else if(e.getStateChange()==ItemEvent.DESELECTED) {
					switch(cb.getText()) {
					case "시럽":
						coffee.setSyrupOption(false);
						coffee.setOptionPrice(coffee.getOptionPrice()-100);
						od.updatePQ();
						break;
					case "샷추가":
						coffee.setShotOption(false);
						coffee.setOptionPrice(coffee.getOptionPrice()+100);
						od.updatePQ();
						break;
					
					}
				}
			}
				
		}
	}
}
