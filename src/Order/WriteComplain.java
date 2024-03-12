package Order;

import java.awt.Component;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import javax.swing.JLabel;
import javax.swing.JPanel;

class WriteComplain {

	private String name,gender,text;
	WriteComplain(String n, String g, String t){
		name=n;
		gender=g;
		text=t;
		writeComplain();
	}
	
	void writeComplain() {
		 try (BufferedWriter csvWriter = new BufferedWriter(
               new OutputStreamWriter(new FileOutputStream("C:\\공차\\건의사항.csv", true), StandardCharsets.UTF_8))) {
			 csvWriter.write("\uFEFF");//한글깨짐방지
			 File csvFile = new File("C:\\공차\\건의사항.csv");
			 // Header 작성
			 if (csvFile.length() == 0) {
				 // 파일이 비어있을 때만 Header를 작성
				 csvWriter.write("건의사항\n");
				 csvWriter.write("----------------------------------------------------------------------------------------------------\n");
				 csvWriter.write("이름,성별,건의사항\n");
				 csvWriter.write("----------------------------------------------------------------------------------------------------\n");
			 }

			
           // 합계 정보 작성
           csvWriter.write(name+"," +gender+","+text+"\n");
           csvWriter.write("----------------------------------------------------------------------------------------------------\n");
       } catch (IOException e) {
           e.printStackTrace();
       }
   }

}
