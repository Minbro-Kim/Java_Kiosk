package Order;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
//기본 메뉴 옵션
class OptionPanel extends JPanel{
	private OptionSectionPanel osp;
	private Menu menu;
	private OptionDialog od;
	
	OptionPanel(Menu menu,OptionDialog od){
		this.menu = menu;
		this.od=od;
		setLayout(new FlowLayout());
		setPreferredSize(new Dimension(400,340));
		
		//수량 옵션
		JPanel quantityOptionPanel = new JPanel();
		SpinnerModel spinnerModel = new SpinnerNumberModel(menu.getQuantity(), 1, 30, 1); // 초기값, 최소값, 최대값, 증가값
		JSpinner quantitySpinner = new JSpinner(spinnerModel);
		quantitySpinner.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				menu.setQuantity((int)quantitySpinner.getValue());
				od.updatePQ();
			}
		});
        quantityOptionPanel.add(quantitySpinner);
        add(new OptionSectionPanel("수량 옵션", quantityOptionPanel));
	}
}
