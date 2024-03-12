package Order;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

class ReadMenu {

	private String menuFile;
	private int milkTeaNumber;
	private int coffeeNumber;
	private int desertNumber;
	private MilkTea milkTea[];
	private Coffee coffee[];
	private Menu desert[];
	ReadMenu(JPanel p){
		menuFile = "C:\\공차\\공차메뉴목록.csv";
		milkTeaNumber = 0;
		coffeeNumber = 0;
		desertNumber = 0;
		milkTea = new MilkTea[10];
		coffee = new Coffee[10];
		desert = new Menu[10];
		readFile(p);
	}
	
	public int getMilkTeaNumber() {
		return milkTeaNumber;
	}
	public int getCoffeeNumber() {
		return coffeeNumber;
	}

	public int getDesertNumber() {
		return desertNumber;
	}
	public MilkTea[] getMilkTea() {
		return milkTea;
	}

	public Coffee[] getCoffee() {
		return coffee;
	}

	public Menu[] getDesert() {
		return desert;
	}



	public void setMilkTea(MilkTea[] milkTea) {
		this.milkTea = milkTea;
	}

	public void setCoffee(Coffee[] coffee) {
		this.coffee = coffee;
	}

	public void setDesert(Menu[] desert) {
		this.desert = desert;
	}
	
	void readFile(JPanel p) {
		try(BufferedReader br = new BufferedReader(new FileReader(menuFile))){
			//읽고 버릴 두줄
			String menuType = br.readLine();
			String menuIndex = br.readLine();
			
			while(true) {
				String line = br.readLine();
				if(line == null) break;
				String index[] = line.split(",");  //","를 구분자로 단어를 분리
				if(!(index[0].equals(""))) {
					milkTea[milkTeaNumber] = new MilkTea();
					milkTea[milkTeaNumber].setMenuType("밀크티");
					milkTea[milkTeaNumber].setName(index[0]);
					milkTea[milkTeaNumber].setLargePrice(Integer.parseInt(index[1]));
					milkTea[milkTeaNumber].setQuantity(1);
					milkTea[milkTeaNumber].setOptionPrice(0);
					if(index.length>2) {
						if(!(index[2].equals("")))milkTea[milkTeaNumber].setJumboPrice(Integer.parseInt(index[2]));
					}
					milkTeaNumber++;
				}
				if(index.length>3) {
					if(!(index[3].equals(""))) {
						coffee[coffeeNumber] = new Coffee();
						coffee[coffeeNumber].setMenuType("커피");
						coffee[coffeeNumber].setName(index[3]);
						coffee[coffeeNumber].setLargePrice(Integer.parseInt(index[4]));
						coffee[coffeeNumber].setQuantity(1);
						coffee[coffeeNumber].setOptionPrice(0);
						if(index.length>5) {
							if(!(index[5].equals("")))coffee[coffeeNumber].setJumboPrice(Integer.parseInt(index[5]));
						}
						coffeeNumber++;
					}
				}
				if(index.length>6) {
					if(!(index[6].equals(""))) {
						desert[desertNumber] = new Menu();
						desert[desertNumber].setMenuType("디저트");
						desert[desertNumber].setName(index[6]);
						desert[desertNumber].setPrice(Integer.parseInt(index[7]));
						desert[desertNumber].setQuantity(1);
						desert[desertNumber].setOptionPrice(0);
						desertNumber++;
					}
				}
			}
		}
		catch(IOException e) {
			//오류발생 다이어로그
			JOptionPane.showMessageDialog(p,"메뉴를 불러오는데 실패하였습니다.");
		}
	}
}
