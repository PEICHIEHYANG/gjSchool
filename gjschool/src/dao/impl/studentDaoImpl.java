package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DbConnection;
import dao.studentDao;
import model.student;

public class studentDaoImpl implements studentDao{

	public static void main(String[] args) {
		/*student s=new student("teacher",90,90);
		//初始化class student 一個物件等於s,s裡面的屬性有(name,chi,eng),分別給值
		new studentDaoImpl().add(s);
		//初始化studentDaoImpl,使用裡面的add方法,再擺入s物件,s物件裡面有name=teacher,chi=90,eng=90)
		
		new studentDaoImpl().add(new student("apple",80,80));
		//初始化studentDaoImpl,使用裡面的add方法,再初始化student,student裡面有三個屬性,分別設定name=apple,chi=80,eng=80 
		*/
		
		/*System.out.println(new studentDaoImpl().queryAll1());*/
		//與靜態class不同class,所以要透過new才能用靜態class裡面的方法
		//使用studentDaoImpl裡面的queryAll1方法
		
		/**************************List ****************************************/
		List<student> l=new studentDaoImpl().queryAll2();
		//與靜態class不同,所以要經過new才能用靜態的methods
		//System.out.println(l); l是物件,所以會帶出記憶體位址
		int sum=0;
		for(student o:l)
		{
			System.out.println("id:"+o.getId()+
								"\t姓名:"+o.getName()+
								"\t國文:"+o.getChi()+
								"\t英文:"+o.getEng()+
								"\t總分:"+(o.getChi()+o.getEng()));
			
			sum=sum+(o.getChi()+o.getEng());
		}
		System.out.println("合計:"+sum);
	}//動態class 結束
	
	/**************************新增-->add************************************/
	@Override
	public void add(student s) {
		Connection conn=DbConnection.getDb();//方法getDb()-->連線到mysql
		String SQL="insert into student(name,chi,eng) values(?,?,?)";
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ps.setString(1, s.getName());//第一個問號是name(String),所以用setString設定
			ps.setInt(2, s.getChi());
			ps.setInt(3,s.getEng());
			ps.executeUpdate();//執行更新資料庫的動作
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}//add methods結束
	
	/**************************查詢-->String & List************************************/
	/******************************查詢-->String ************************************/
	@Override
	public String queryAll1() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from student";
		String show="";//宣告show等於空字串
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				show=show+"id:"+rs.getInt("id")+
							"\t姓名:"+rs.getString("name")+
							"\t國文:"+rs.getInt("chi")+
							"\t英文"+rs.getInt("eng")+"\n";
				
				/*show="id:"+rs.getInt("id")+
						"\t姓名:"+rs.getString("name")+
						"\t國文:"+rs.getInt("chi")+
						"\t英文"+rs.getInt("eng")+"\n";*/
				//因為每跑一次完迴圈,show的結果都會蓋過前一次的結果,所以只會出現一筆資料
				//要用show=show+我要輸出的資料,每一筆資料才會顯示
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return show;//回傳跑完迴圈後show的最終結果
	}//queryAll1 methods 結束
	
	
	/******************************查詢-->List ************************************/
	@Override
	public List<student> queryAll2() {
		Connection conn=DbConnection.getDb();
		String SQL="select * from student";
		List<student> l=new ArrayList();//List是介面不能new,才會new ArrayList-->也可以宣告ArrayList
		try {
			PreparedStatement ps=conn.prepareStatement(SQL);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				student s=new student();//初始化class student 擺入s物件
				s.setId(rs.getInt("id"));//呼叫s裡面的setId設定id的值,用rs物件的get方法取得資料庫"id"的資料
				s.setName(rs.getString("name"));//呼叫s裡面的setName設定name的值,用rs物件的get方法取得資料庫"name"的資料
				s.setChi(rs.getInt("chi"));//呼叫s裡面的setChi設定chi的值,用rs物件的get方法取得資料庫"chi"的資料
				s.setEng(rs.getInt("eng"));//呼叫s裡面的setEng設定id的值,用rs物件的get方法取得資料庫"eng"的資料
				
				
				l.add(s);//透過l物件的add方法,擺入s物件,s物件裡面有資料庫的id,name,chi,eng資料
			}//迴圈結束-->每跑一圈出現一筆資料直到迴圈結束
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;//回傳跑完迴圈後的l的結果
	}//queryAll2 methods 結束
	
	

}
