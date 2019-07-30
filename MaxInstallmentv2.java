import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;


public class MaxInstallmentv2 {
     
	public String findMaxInstallment(String xml,int AmountInstallment,int Order) throws IOException, SQLException, ClassNotFoundException{ 
		List<Integer> maxCredits = null;
		int i = 0,j = 0;
		String output = "";
		String SQL_INSERT = "INSERT INTO Table_Kredi VALUES(?,?,?)";
		String SQL_UPDATE = "UPDATE Table_Kredi SET Taksit_Tutari=? WHERE Sira=?";
		
		try{
			String info = "";
			
			//Kredi arrayList created
			ArrayList<Kredi> Credits = new ArrayList<Kredi>();
			
			File xmlFile = new File("C:/yazilim/" + xml + ".xml");
			Reader fileReader = new FileReader(xmlFile);
			BufferedReader bufReader = new BufferedReader(fileReader);
			
			StringBuilder sb = new StringBuilder();
			String line = bufReader.readLine();
			while(line != null){
					sb.append(line);
					line = bufReader.readLine();				
			}
			
			String xmlString = sb.toString();
			bufReader.close();
		
			
			/*String xml = "<Kredi><sira>1</sira><odeme_tarihi>12/05/2016</odeme_tarihi><taksit_tutari>500</taksit_tutari>" +
					"<sira>2</sira><odeme_tarihi>13/06/2016</odeme_tarihi><taksit_tutari>500</taksit_tutari>" +
					"<sira>3</sira><odeme_tarihi>12/07/2016</odeme_tarihi><taksit_tutari>1500</taksit_tutari></Kredi>";*/
			  
			
			JAXBContext jc = JAXBContext.newInstance(Kredi.class);
			javax.xml.bind.Unmarshaller unmarshaller = jc.createUnmarshaller();
			
			
			//SQL Binding
			Connection baglanti = null;
			PreparedStatement statement = null;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			
			baglanti  = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=myDatabase;user=mytable;password=emre3673");
			statement = baglanti.prepareStatement(SQL_INSERT);
			

			for(String s : xmlString.split("(?=<sira>)|(?<=</taksit_tutari>)")){
				i++;
				if((i%2)==0 && (i!=0)){
					info = "<Kredi>"+s+"</Kredi>";
					String info11 = info.replaceAll("[ ]", "");

					StreamSource streamSource = new StreamSource(new StringReader(info11));
					JAXBElement<Kredi> je = unmarshaller.unmarshal(streamSource,Kredi.class);
					
					Kredi kredi = new Kredi();
					kredi = (Kredi)je.getValue();
					
					//INSERT TO TABLE Table_Kredi
					statement.setInt(1, kredi.getSira());
					statement.setString(2, kredi.getOdeme_tarihi());
					statement.setInt(3, kredi.getTaksit_tutari());
					statement.executeUpdate();
					
					
					Credits.add(j, kredi);
					j++;
				}

			}
			PreparedStatement updateStatement = null;
			updateStatement = baglanti.prepareStatement(SQL_UPDATE);
			updateStatement.setInt(1, AmountInstallment);
			updateStatement.setInt(2, Order);
			updateStatement.executeUpdate();
			
			//Return part of the code
			maxCredits = Kredi.getMaxInstallment(Credits);
			output="En yüksek tutarlý taksitler: ";
			int ssira;
			for(int a=0;a<maxCredits.size();a++){
				ssira = maxCredits.get(a);
				output = output + new Integer(ssira).toString();
				output = output + " ";
			}
			output = output +" no'lu taksit(ler)";
			 
		}catch(JAXBException e){
			e.printStackTrace();
			return "Error!" + e.getMessage();
		}
		
		return output;
	}
}
