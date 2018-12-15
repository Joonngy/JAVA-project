package project;

import javax.swing.JFrame;

public class Game {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("GAME");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false); //frame 선언,생성. 사이즈조절 불가하게 설정.

		PrimaryPanel panel = new PrimaryPanel(); //Primarypanel 선언, 생성

		frame.getContentPane().add(panel);
		frame.pack();
		frame.setVisible(true); //frame에 panel넣어줌

	} // main method 프레임 선언&생성 및 패널 add

}// Exam class
