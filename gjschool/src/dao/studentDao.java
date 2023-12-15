package dao;

import java.util.List;

import model.student;

public interface studentDao {

	//create
	//void add(String name,int chi,int eng);//聯想到UI介面,要讓使用者輸入什麼資料
	void add(student s);//裡面放class student的s物件
	
	
	//read
	String queryAll1();
	List<student> queryAll2();
	
	
	//update
	
	
	//delete
}
