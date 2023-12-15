package controller;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.impl.studentDaoImpl;
import model.student;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JScrollPane;

public class studentUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField name;
	private JTextField chi;
	private JTextField eng;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					studentUI frame = new studentUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public studentUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("學員管理系統");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("微軟正黑體", Font.BOLD, 24));
		lblNewLabel.setBounds(148, 10, 240, 41);
		contentPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(41, 57, 463, 90);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("姓名");
		lblNewLabel_1.setBounds(10, 10, 46, 15);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("國文");
		lblNewLabel_2.setBounds(152, 10, 46, 15);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("英文");
		lblNewLabel_3.setBounds(303, 10, 46, 15);
		panel.add(lblNewLabel_3);

		name = new JTextField();
		name.setBounds(46, 7, 96, 21);
		panel.add(name);
		name.setColumns(10);

		chi = new JTextField();
		chi.setBounds(197, 7, 96, 21);
		panel.add(chi);
		chi.setColumns(10);

		eng = new JTextField();
		eng.setBounds(345, 7, 96, 21);
		panel.add(eng);
		eng.setColumns(10);

		JButton btnNewButton = new JButton("新增");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/*
				 * 1.擷取 name,chi,eng getText() 
				 * 2.chi,eng-->轉整數 
				 * 3.new student(name,chi,eng);
				 * 4.add(s);
				 */
				String Name = name.getText();//擷取name輸入的資料
				int Chi = Integer.parseInt(chi.getText());//擷取chi輸入的資料+轉型成int
				int Eng = Integer.parseInt(eng.getText());//擷取eng輸入的資料+轉型成int

				student s = new student(Name, Chi, Eng);
				/*宣告student類型的s物件,s物件裡面有三個屬性:name變數(String),chi變數(Integer),eng變數(Integer),
				 * 並複製變數Name,變數Chi,變數Eng的值到s物件內
				 */
				new studentDaoImpl().add(s);
				//初始化 studentDaoImpl後才可用裡面的add方法,再將s物件擺入add方法內

			}
		});

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(41, 157, 463, 136);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 443, 116);
		panel_1.add(scrollPane);

		JTextArea output = new JTextArea();
		scrollPane.setViewportView(output);

		btnNewButton.setBounds(33, 57, 85, 23);
		panel.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("查詢(String)");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				/*
				 * 1.queryAll2()--->List 2.String show="";
				 */

				/**************** String show ************************************/
				output.setText(new studentDaoImpl().queryAll1());
				// output要顯示class studentDaoImpl裡面的queryAll1方法

			}
		});
		btnNewButton_1.setBounds(169, 57, 110, 23);
		panel.add(btnNewButton_1);

		JButton btnlist = new JButton("查詢(List)");
		btnlist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				List<student> l = new studentDaoImpl().queryAll2();
				String show = "";// 宣告初始的show為空字串
				int sum = 0;// 宣告初始的總分為0-->國文+英文
				int chiSum = 0;// 宣告初始的國文總分為0
				int engSum = 0;// 宣告初始的英文總分為0
				int count = 0;// 宣告初始的人數為0

				for (student o : l)// for-each迴圈撈資料-->宣告一個student型態的o物件去複製l
				{
					count++;// 每跑一圈人數+1
					chiSum = chiSum + o.getChi();// final國文總分 等於o物件的國文總分+目前國文總分-->getchiSum()會等於全部迴圈跑完的結果
					engSum = engSum + o.getEng();// final英文總分 等於o物件的英文總分+目前英文總分-->getengSum()會等於全部迴圈跑完的結果
					sum = sum + (o.getChi() + o.getEng());// final 總分 等於o物件國+英總分+目前國+英總分-->getChi+getEng
					show = show + ("id:" + o.getId() + "\tname:" + o.getName() + "\tchi:" + o.getChi() + "\teng:"
							+ o.getEng()) + "\tsum:" + (o.getChi() + o.getEng()) + "\n";
					// show跑第一圈等於撈第一筆的資料,跑第二圈再新增下一筆的資料(接續第一筆資料),以此類推全部跑完再擺入show
				}
				show = show + "\nsum:" + sum + "\tcount:" + count + "\nchiAverage:" + (chiSum / count) + "\tengAverage:"
						+ (engSum / count) + "\ttotalAverage:" + (sum / count);

				output.setText(show);// output會顯示最後的show

			}
		});
		btnlist.setBounds(303, 57, 110, 23);
		panel.add(btnlist);
	}
}
