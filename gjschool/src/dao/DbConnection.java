package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection // 產生兩個不同個class,一個靜態的DbConnection的class,一個動態的DbConnection的class
{
	String name;

	public static void main(String[] args) {
		
		
		 // DbConnection db=new DbConnection(); System.out.println(db.name);
		 

		System.out.println(DbConnection.getDb());

	}

	public static Connection getDb() {
		Connection conn = null ;//宣告conn初始值為null
		String utl = "jdbc:mysql://localhost:3306/school";
		String user = "root";
		String password = "1234";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//載入jdbc元件
			conn = DriverManager.getConnection(utl, user, password);//連線
			//等號左邊不用再寫Connection conn,直接寫conn就好,因為上面已經產生conn了
		} catch (ClassNotFoundException e) {
			System.out.println("no Driver");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("no collection");
			e.printStackTrace();
		}

		return conn;//沒有連上線或沒有載入Driver會輸出預設值null
	}

}
