package Order;

import javax.swing.ImageIcon;

class Menu {

	protected String name;
	protected int price;
	protected ImageIcon image;
	protected String menuType;
	protected int quantity;	
	protected int optionPrice;
	
	public int getOptionPrice() {
		return optionPrice;
	}

	public void setOptionPrice(int optionPrice) {
		this.optionPrice = optionPrice;
	}
	Menu(){
		
	}
	Menu(String n,int p,ImageIcon i,String m,int q){
		this.name=n;
		this.price=p;
		this.image=i;
		this.menuType=m;
		this.quantity=q;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public ImageIcon getImage() {
		return image;
	}
	
	public int getQuantity() {
		return quantity;
	}
	String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	public void setName(String name) {
		this.name = name;
		this.image= new ImageIcon("C:\\공차\\"+menuType+"\\"+ name.replaceAll(" ","")+".png");
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}	
}

class MilkTea extends Menu{
	MilkTea(String n, int p, ImageIcon i, String m, int q) {
		super(n, p, i, m, q);
	}
	
	MilkTea(){
		iceOption = true;
		amountOfIceOption="보통";
		sweetnessOption=50;
		size="Large";
		pearlOption=false;
		coconutOption=false;
		milkFoamOption=false;
		aloeOption=false;
	}

	//기본 옵션
	private boolean iceOption;
	private String amountOfIceOption;
	private int sweetnessOption;
	private int largePrice;
	private int jumboPrice;
	private String size;
	
	//추가옵션
	private boolean pearlOption;
	private boolean coconutOption;
	private boolean milkFoamOption;
	private boolean aloeOption;


	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean isIceOption() {
		return iceOption;
	}

	public String getAmountOfIceOption() {
		return amountOfIceOption;
	}

	public int getSweetnessOption() {
		return sweetnessOption;
	}

	public boolean isPearlOption() {
		return pearlOption;
	}

	public boolean isCoconutOption() {
		return coconutOption;
	}

	public boolean isMilkFoamOption() {
		return milkFoamOption;
	}

	public boolean isAloeOption() {
		return aloeOption;
	}

	public void setIceOption(boolean iceOption) {
		this.iceOption = iceOption;
	}

	public void setAmountOfIceOption(String amountOfIceOption) {
		this.amountOfIceOption = amountOfIceOption;
	}

	public void setSweetnessOption(int sweetnessOption) {
		this.sweetnessOption = sweetnessOption;
	}

	public void setPearlOption(boolean pearlOption) {
		this.pearlOption = pearlOption;
	}

	public void setCoconutOption(boolean coconutOption) {
		this.coconutOption = coconutOption;
	}

	public void setMilkFoamOption(boolean milkFoamOption) {
		this.milkFoamOption = milkFoamOption;
	}

	public void setAloeOption(boolean aloeOption) {
		this.aloeOption = aloeOption;
	}

	public int getLargePrice() {
		return largePrice;
	}

	public int getJumboPrice() {
		return jumboPrice;
	}

	public void setLargePrice(int largePrice) {
		this.largePrice = largePrice;
	}

	public void setJumboPrice(int jumboPrice) {
		this.jumboPrice = jumboPrice;
	}
}

class Coffee extends Menu{
	
	Coffee(){
		iceOption = true;
		amountOfIceOption="보통";
		size="Large";
		syrupOption=false;
		shotOption=false;
	}
	Coffee(String n, int p, ImageIcon i, String m, int q) {
		super(n, p, i, m, q);
	}

	//기본 옵션
	private boolean iceOption;
	private String amountOfIceOption;
	private int largePrice;
	private int jumboPrice;
	private String size;
	//추가옵션
	private boolean syrupOption;
	private boolean shotOption;

	public boolean isIceOption() {
		return iceOption;
	}

	public String getAmountOfIceOption() {
		return amountOfIceOption;
	}

	public boolean isSyrupOption() {
		return syrupOption;
	}

	public boolean isShotOption() {
		return shotOption;
	}

	public void setIceOption(boolean iceOption) {
		this.iceOption = iceOption;
	}

	public void setAmountOfIceOption(String amountOfIceOption) {
		this.amountOfIceOption = amountOfIceOption;
	}

	public void setSyrupOption(boolean syrupOption) {
		this.syrupOption = syrupOption;
	}

	public void setShotOption(boolean shotOption) {
		this.shotOption = shotOption;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	public int getLargePrice() {
		return largePrice;
	}

	public int getJumboPrice() {
		return jumboPrice;
	}

	public void setLargePrice(int largePrice) {
		this.largePrice = largePrice;
	}

	public void setJumboPrice(int jumboPrice) {
		this.jumboPrice = jumboPrice;
	}
	
}