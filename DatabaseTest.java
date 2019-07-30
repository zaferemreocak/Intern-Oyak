import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTest {
  public static void main(String[] args) {
	
	  try{
		  
		  Connection baglanti = null;
		  Statement statement = null;
		  ResultSet resultSet = null;
		  
		  baglanti  = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=myDatabase;user=mytable;password=emre3673");
		  statement = baglanti.createStatement();
		  //statement.execute("insert into myTable (ID,Name) values (123456,'OYAK')");
		  //statement.execute("delete from myTable where ID=123456 and Name='OYAK'");
		  resultSet = statement.executeQuery("select * from Records");
		  
		  while(resultSet.next()){
			  System.out.print(resultSet.getInt("ID"));
			  System.out.print("\t");
			  System.out.print(resultSet.getString("Name"));
			  System.out.print("\t");
			  System.out.print(resultSet.getString("Surname"));
			  System.out.print("\t");
			  System.out.print(resultSet.getString("BirthDate"));
			  System.out.print("\t");
			  System.out.print(resultSet.getString("Salary"));
			  System.out.print("\t");
			  System.out.print(resultSet.getString("Nationality"));
			  System.out.println("\n");
		  }
	  }catch(SQLException e){
		  e.printStackTrace();
	  }
	  
  }
}