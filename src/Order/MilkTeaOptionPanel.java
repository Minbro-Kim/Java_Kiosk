package Order;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Hashtable;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

class MilkTeaOptionPanel extends JPanel{

	private OptionSectionPanel osp;
	private MilkTea milkTea;
	private OptionDialog optionD;
	
	MilkTeaOptionPanel(MilkTea mt,OptionDialog od){
		milkTea = mt;
		optionD=od;
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(400,460));
		//아이스 옵션
		JPanel amountOfIceOptionPanel = new JPanel();
		osp = new OptionSectionPanel("얼음량", amountOfIceOptionPanel);
		JPanel iceOptionPanel = new JPanel();
		ButtonGroup iceButtonGroup = new ButtonGroup();
		JRadioButton iceRadioBtn = new JRadioButton("ICE");
		iceRadioBtn.addItemListener(new MyItemListener(this,osp,milkTea,optionD));
		JRadioButton hotRadioBtn = new JRadioButton("HOT");
		iceButtonGroup.add(iceRadioBtn);
		iceButtonGroup.add(hotRadioBtn);
		iceOptionPanel.add(iceRadioBtn);
		iceOptionPanel.add(hotRadioBtn);
		//옵션에 따라 버튼이 선택되어 있도록
		if(milkTea.isIceOption()==true) {//아이스면
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
		smallRB.addItemListener(new MyItemListener(this,osp,milkTea,optionD ));
		middleRB.addItemListener(new MyItemListener(this,osp,milkTea,optionD));
		largeRB.addItemListener(new MyItemListener(this,osp,milkTea,optionD));		
		amountBG.add(smallRB);
		amountBG.add(middleRB);
		amountBG.add(largeRB);
		amountOfIceOptionPanel.add(smallRB);
		amountOfIceOptionPanel.add(middleRB);
		amountOfIceOptionPanel.add(largeRB);
		//옵션에 따라 버튼이 선택되어 있도록
		if(milkTea.getAmountOfIceOption().equals("적음")) {
			smallRB.setSelected(true);
		}
		else if(milkTea.getAmountOfIceOption().equals("보통")){
			middleRB.setSelected(true);
		}
		else if(milkTea.getAmountOfIceOption().equals("많음")) {
			largeRB.setSelected(true);
		}
		add(osp);
		
		//당도 옵션
		JPanel sweetnessOptionPanel = new JPanel();
		//기본 당도 옵션선택 위치
		int sweetLabel=2;
		switch(milkTea.getSweetnessOption()){
		case 0:
			sweetLabel=0;
			break;
		case 30:
			sweetLabel=1;
			break;
		case 50:
			sweetLabel=2;
			break;
		case 70:
			sweetLabel=3;
			break;
		case 100:
			sweetLabel=4;
			break;
		}
		JSlider sweetnessSlider = new JSlider(JSlider.HORIZONTAL,0,4,sweetLabel);
		sweetnessSlider.setPaintLabels(true);//슬라이더 눈금 라벨 표시
		sweetnessSlider.setPaintTicks(true);//슬라이더 눈금 표시
		sweetnessSlider.setMajorTickSpacing(1);//1단위로 글자 표시
        sweetnessSlider.setSnapToTicks(true);// Snap to ticks 설정 (라벨 값에 자동으로 스냅)
		 // 틱 레이블을 표시할 텍스트를 정의(일정한 단위로 선택가능한 것이 아니기 때문에 해시테이블 이용)
         Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
         labelTable.put(0, new JLabel("0"));
         labelTable.put(1, new JLabel("30"));
         labelTable.put(2, new JLabel("50"));
         labelTable.put(3, new JLabel("70"));
         labelTable.put(4, new JLabel("100"));
		 sweetnessSlider.setLabelTable(labelTable);     
		 sweetnessSlider.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		        JLabel labelValue = labelTable.get(sweetnessSlider.getValue());
		        milkTea.setSweetnessOption(Integer.parseInt(labelValue.getText()));
		    }
		});
		sweetnessOptionPanel.add(sweetnessSlider);
		add(new OptionSectionPanel("당도", sweetnessOptionPanel));
		
		//사이즈 옵션
		JPanel sizeOptionPanel = new JPanel();
		ButtonGroup sizeBG = new ButtonGroup();
		JRadioButton largeSizeRB = new JRadioButton("Large");
		sizeBG.add(largeSizeRB);
		largeSizeRB.addItemListener(new MyItemListener(this,osp,milkTea,optionD ));
		JRadioButton JumboSizeRB = new JRadioButton("Jumbo");
		sizeBG.add(JumboSizeRB);
		JumboSizeRB.addItemListener(new MyItemListener(this,osp,milkTea,optionD));
		sizeOptionPanel.add(largeSizeRB);
		if(milkTea.getJumboPrice()!=0)//사이즈 두개인 경우만 점보표시
		sizeOptionPanel.add(JumboSizeRB);
		//옵션에 따라 버튼이 선택되어 있도록
		if(milkTea.getSize().equals("Large")) {//라지면
			largeSizeRB.setSelected(true);
		}
		else if(milkTea.getSize().equals("Jumbo")){
			JumboSizeRB.setSelected(true);
		}
		add(new OptionSectionPanel("사이즈", sizeOptionPanel));	
		
		//추가 옵션(펄, 코코넛, 밀크폼, 알로에)
		JPanel extraOptionPanel = new JPanel();
		extraOptionPanel.setPreferredSize(new Dimension(400,60));
		JCheckBox pearlCB = new JCheckBox("펄");
		JCheckBox coconutCB = new JCheckBox("코코넛");
		JCheckBox milkFoamCB = new JCheckBox("밀크폼");
		JCheckBox aloeCB = new JCheckBox("알로에");
		pearlCB.addItemListener(new MyItemListener(this,osp,milkTea,optionD ));
		coconutCB.addItemListener(new MyItemListener(this,osp,milkTea,optionD ));
		milkFoamCB.addItemListener(new MyItemListener(this,osp,milkTea,optionD ));
		aloeCB.addItemListener(new MyItemListener(this,osp,milkTea,optionD ));
		extraOptionPanel.add(pearlCB);
		extraOptionPanel.add(new JLabel("(+1000원)"));
		extraOptionPanel.add(coconutCB);
		extraOptionPanel.add(new JLabel("(+700원)"));
		extraOptionPanel.add(milkFoamCB);
		extraOptionPanel.add(new JLabel("(+500원)"));
		extraOptionPanel.add(aloeCB);
		extraOptionPanel.add(new JLabel("(+1000원)"));
		if(milkTea.isPearlOption()==true) {
			pearlCB.setSelected(true);
			milkTea.setOptionPrice(milkTea.getOptionPrice()-1000);
			optionD.updatePQ();
		}
		else pearlCB.setSelected(false);
		if(milkTea.isCoconutOption()==true) {
			coconutCB.setSelected(true);
			milkTea.setOptionPrice(milkTea.getOptionPrice()-700);
			optionD.updatePQ();
		}
		else coconutCB.setSelected(false);
		if(milkTea.isMilkFoamOption()==true) {
			milkFoamCB.setSelected(true);
			milkTea.setOptionPrice(milkTea.getOptionPrice()-500);
			optionD.updatePQ();
		}
		else milkFoamCB.setSelected(false);
		if(milkTea.isAloeOption()==true) {
			aloeCB.setSelected(true);
			milkTea.setOptionPrice(milkTea.getOptionPrice()-1000);
			optionD.updatePQ();
		}
		else aloeCB.setSelected(false);
		add(new OptionSectionPanel("추가옵션", extraOptionPanel));
		
		//수량 옵션
		JPanel quantityOptionPanel = new JPanel();
		SpinnerModel spinnerModel = new SpinnerNumberModel(milkTea.getQuantity(), 1, 30, 1); // 초기값, 최소값, 최대값, 증가값
        JSpinner quantitySpinner = new JSpinner(spinnerModel);
        quantitySpinner.addChangeListener(new ChangeListener() {
		    @Override
		    public void stateChanged(ChangeEvent e) {
		       milkTea.setQuantity((int)quantitySpinner.getValue());
		       optionD.updatePQ();
		    }
		});
        
        quantityOptionPanel.add(quantitySpinner);
        add(new OptionSectionPanel("수량 옵션", quantityOptionPanel));
		
	}
	void update(MilkTeaOptionPanel p,OptionSectionPanel op) {
		if(milkTea.isIceOption()==false) {
			op.setVisible(false);
			p.setPreferredSize(new Dimension(400,390));//옵션이 없어지기때문에 패널 크기 줄이기
		}
		else if(milkTea.isIceOption()==true) {
			op.setVisible(true);
			p.setPreferredSize(new Dimension(400,460));//패널크기 원래대로 늘리기
		}
		p.revalidate();
	}
	
	
	
	class MyItemListener implements ItemListener{
		MilkTeaOptionPanel parentPanel;
		OptionSectionPanel optionSP;
		MilkTea milkTea;
		OptionDialog optionD;
		
		MyItemListener(MilkTeaOptionPanel p, OptionSectionPanel op, MilkTea mt, OptionDialog od){
			optionSP = op;
			parentPanel =p;
			milkTea = mt;
			optionD = od;
		}
		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getSource() instanceof JRadioButton) {
				JRadioButton rb = (JRadioButton)e.getSource();
				if (e.getStateChange()==ItemEvent.SELECTED) {
					switch(rb.getText()) {
						case "ICE":
							milkTea.setIceOption(true);
							parentPanel.update(parentPanel,optionSP);
							break;
						case "적음":
						case "보통":
			            case "많음":
							milkTea.setAmountOfIceOption(rb.getText());
							break;
			            case "Large":
			            	milkTea.setPrice(milkTea.getLargePrice());
			            	milkTea.setSize("Large");
			            	optionD.updatePQ();
			            	break;
			            case "Jumbo":
			            	milkTea.setPrice(milkTea.getJumboPrice());
			            	milkTea.setSize("Jumbo");
			            	optionD.updatePQ();
			            	break;
					}
					
				}
				else if (e.getStateChange()==ItemEvent.DESELECTED) {
					switch(rb.getText()) {
					case "ICE":
						milkTea.setIceOption(false);
						milkTea.setAmountOfIceOption("보통");//장바구니내의 상품의 옵션을 변경할때, hot에서 ice로 옵션을 변경할때, 얼음량 기본옵션이 보통으로 선택되도록함
						parentPanel.update(parentPanel,optionSP);
						break;
					}
				}
			}
			else if (e.getSource() instanceof JCheckBox) {
				JCheckBox cb = (JCheckBox)e.getSource();
				if (e.getStateChange()==ItemEvent.SELECTED) {
					switch(cb.getText()) {
					case "펄":
						milkTea.setPearlOption(true);
						milkTea.setOptionPrice(milkTea.getOptionPrice()+1000);
						optionD.updatePQ();
						break;
					case "알로에":
						milkTea.setAloeOption(true);
						milkTea.setOptionPrice(milkTea.getOptionPrice()+1000);
						optionD.updatePQ();
						break;
					case "코코넛":
						milkTea.setCoconutOption(true);
						milkTea.setOptionPrice(milkTea.getOptionPrice()+700);
						optionD.updatePQ();
						break;
					case "밀크폼":
						milkTea.setMilkFoamOption(true);
						milkTea.setOptionPrice(milkTea.getOptionPrice()+500);
						optionD.updatePQ();
						break;
					}
				}
				else if(e.getStateChange()==ItemEvent.DESELECTED) {
					switch(cb.getText()) {
					case "펄":
						milkTea.setPearlOption(false);
						milkTea.setOptionPrice(milkTea.getOptionPrice()-1000);
						optionD.updatePQ();
						break;
					case "알로에":
						milkTea.setAloeOption(false);
						milkTea.setOptionPrice(milkTea.getOptionPrice()-1000);
						optionD.updatePQ();
						break;
					case "코코넛":
						milkTea.setCoconutOption(false);
						milkTea.setOptionPrice(milkTea.getOptionPrice()-700);
						optionD.updatePQ();
						break;
					case "밀크폼":
						milkTea.setMilkFoamOption(false);
						milkTea.setOptionPrice(milkTea.getOptionPrice()-500);
						optionD.updatePQ();
						break;
					}
				}
			}
				
		}
	}
	
}
