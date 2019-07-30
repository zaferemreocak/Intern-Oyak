package sqlhtml;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
@WebServlet
public class Sql_To_HTML extends HttpServlet {

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	ArrayList<Integer> 	IDRecords			 = new ArrayList<Integer>();
	  ArrayList<String> 	NameRecords			 = new ArrayList<String>();
	  ArrayList<String> 	SurnameRecords		 = new ArrayList<String>();
	  ArrayList<String> 	BirthDateRecords	 = new ArrayList<String>();
	  ArrayList<Integer> 	SalaryRecords		 = new ArrayList<Integer>();
	  ArrayList<String> 	NationalityRecords	 = new ArrayList<String>();
	  
	  Connection baglanti = null;
	  Statement statement = null;
	  ResultSet resultSet = null;
	  
 public void database() throws ClassNotFoundException {
	  int ID = 0,Salary = 0;
	  String output = "";
	  int i = 0;
	  try{
		  
		  
		  
		  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		  baglanti  = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=myDatabase;user=mytable;password=emre3673");
		  statement = baglanti.createStatement();
		  resultSet = statement.executeQuery("select * from Records");
		  
		  while(resultSet.next()){
			  ID = resultSet.getInt("ID");
			  IDRecords.add(i,ID);
			  output = resultSet.getString("Name");
			  NameRecords.add(i, output);
			  output = resultSet.getString("Surname");
			  SurnameRecords.add(i, output);
			  output = resultSet.getString("BirthDate");
			  BirthDateRecords.add(i, output);
			  Salary = resultSet.getInt("Salary");
			  SalaryRecords.add(i, Salary);
			  output = resultSet.getString("Nationality");
			  NationalityRecords.add(i,output);
			  i++;
		  }  
	  }catch(SQLException e){
		  e.printStackTrace();
	  }
	  
  }/*
 //METHOD TO SUBMIT
public void clickUpdate(HttpServletRequest request, HttpServletRequest response) throws ServletException, IOException, SQLException{
	Sql_To_HTML sql_to_html = new Sql_To_HTML();
	String button = request.getParameter("button");
	
	if("button1".equals(button)){
		sql_to_html.update();
	}
}*/
//METHOD FOR SQL COMMAND
public void update() throws SQLException{
	resultSet = statement.executeQuery("UPDATE myDatabase Set ID='ID' WHERE ID=ID");
}
 
  //ID RECORDS
  public void setIDRecords(ArrayList<Integer> IDRecords){
	  this.IDRecords = IDRecords;
  }
  
  public ArrayList<Integer> getIDRecords(){
	  return IDRecords;
  }
  //NAME RECORDS
  public void setNameRecords(ArrayList<String> NameRecords){
	  this.NameRecords = NameRecords;
  }
  
  public ArrayList<String> getNameRecords(){
	  return NameRecords;
  }
  //SURNAME RECORDS
  public void setSurnameRecords(ArrayList<String> SurnameRecords){
	  this.SurnameRecords = SurnameRecords;
  }
  
  public ArrayList<String> getSurnameRecords(){
	  return SurnameRecords;
  }
  //BIRTHDATE RECORDS
  public void setBirthDateRecords(ArrayList<String> BirthDateRecords){
	  this.BirthDateRecords = BirthDateRecords;
  }
  public ArrayList<String> getBirthDateRecords(){
	  return BirthDateRecords;
  }
  //SALARY RECORDS
  public void setSalaryRecords(ArrayList<Integer> SalaryRecords){
	  this.SalaryRecords = SalaryRecords;
  }
  
  public ArrayList<Integer> getSalaryRecords(){
	  return SalaryRecords;
  }
  //NATIONALITY RECORDS
  public void setNationalityRecords(ArrayList<String> NationalityRecords){
	  this.NationalityRecords = NationalityRecords;
  }
  
  public ArrayList<String> getNationalityRecords(){
	  return NationalityRecords;
  }

}