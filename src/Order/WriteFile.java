package Order;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;

class WriteFile {

	private Vector<Menu> menuVector = new Vector<>();
	private int basketNumber;
	private int basketPrice;
	WriteFile(Vector<Menu> menuVector, int bn, int bp){
		this.menuVector = menuVector;
		basketNumber=bn;
		basketPrice=bp;
		writeCSV();
	
	}
	 void writeCSV() {
		 try (BufferedWriter csvWriter = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream("C:\\공차\\매출.csv", true), StandardCharsets.UTF_8))) {
			 csvWriter.write("\uFEFF");//한글깨짐방지
			 File csvFile = new File("C:\\공차\\매출.csv");
			 // Header 작성
			 if (csvFile.length() == 0) {
				 // 파일이 비어있을 때만 Header를 작성
				 csvWriter.write("매출(결제내역)\n");
				 csvWriter.write("----------------------------------------------------------------------------------------------------\n");
				 csvWriter.write("상품명,가격,수량,사이즈,옵션\n");
				 csvWriter.write("----------------------------------------------------------------------------------------------------\n");
			 }
			 for(int i=0;i<menuVector.size();i++) {
				 switch(menuVector.get(i).getMenuType()) {
				 case "밀크티":
					 MilkTea Mmenu = (MilkTea)menuVector.get(i);
					 String line = Mmenu.getName()+","+(Mmenu.getPrice()+Mmenu.getOptionPrice())*Mmenu.getQuantity()+","+Mmenu.getQuantity()+","+Mmenu.getSize();
					 if((Mmenu.isPearlOption()==false)&&(Mmenu.isCoconutOption()==false)&&(Mmenu.isMilkFoamOption()==false)&&(Mmenu.isAloeOption()==false));
					 else line+=",";
					 if(Mmenu.isPearlOption()==true)line+="펄 ";
					 if(Mmenu.isCoconutOption()==true)line+="코코넛 ";
					 if(Mmenu.isMilkFoamOption()==true)line+="밀크폼 ";
					 if(Mmenu.isAloeOption()==true)line+="알로에";
					 csvWriter.write(line+"\n");
					 break;
				 case "커피":
					 Coffee Cmenu = (Coffee)menuVector.get(i);
					 String line2 = Cmenu.getName()+","+(Cmenu.getPrice()+Cmenu.getOptionPrice())*Cmenu.getQuantity()+","+Cmenu.getQuantity()+","+Cmenu.getSize();
					 if((Cmenu.isSyrupOption()==false)&&(Cmenu.isShotOption()==false));
					 else line2+=",";
					 if(Cmenu.isSyrupOption()==true)line2+="시럽 ";
					 if(Cmenu.isShotOption()==true)line2+="샷추가";
					 csvWriter.write(line2+"\n");
					 break;
				 case "디저트":
					 Menu Dmenu = menuVector.get(i);
					 csvWriter.write(Dmenu.getName()+"," +(Dmenu.getPrice()+Dmenu.getOptionPrice())*Dmenu.getQuantity()+","+Dmenu.getQuantity()+"\n");
					 break;
				 }
				 
			 }
            // 합계 정보 작성
            csvWriter.write("총 수량," + String.valueOf(basketNumber) + "\n");
            csvWriter.write("결제금액," + String.valueOf(basketPrice) + "\n");
            csvWriter.write("----------------------------------------------------------------------------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

  
}
