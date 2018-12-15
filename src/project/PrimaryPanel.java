package project;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

public class PrimaryPanel extends JPanel { //PrimaryPanel 전체 패널들을 관할함

	private JPanel panel0;
	private GamePanel panel1;
	private GamePlayPanel panel2;
	private UserInfoPanel panel3;
	private GameScorePanel panel4;
	private HowtoPlayPanel panel5;

	public PrimaryPanel() {
		panel1 = new GamePanel();
		panel2 = new GamePlayPanel();
		panel3 = new UserInfoPanel(panel2);
		panel5 = new HowtoPlayPanel(); //panel1,2,3,5 생성해줌

		panel0 = new JPanel();
		panel0.setVisible(true);
		panel0.setBackground(Color.white);
		panel0.setPreferredSize(new Dimension(1200, 800));
		panel0.setBounds(0, 0, 1200, 800);
		panel0.setLayout(null); //panel0 생성, 사이즈/배경/위치/레이아웃 설정.

		add(panel1);
		add(panel2);
		add(panel3);
		add(panel5); //PrimaryPanel에 panel1,2,3,5 add 해줌

		panel2.setMain(this);
		panel1.setMain(this);
		panel3.setMain(this);
		panel5.setMain(this);

		panel1.setVisible(true);
		panel2.setVisible(false);
		panel3.setVisible(false);
		panel5.setVisible(false); //메인화면패널인 panel1만 보이게 설정
	}//PrimaryPanel() 

	public GamePanel getGamePanel() {
		return panel1;
	}//getGamePanel() panel1 반환

	public GamePlayPanel getGamePlayPanel() {
		return panel2;
	}//getGamePlayPanel() panel2 반환

	public UserInfoPanel getUserInfoPanel() {
		return panel3;
	}//getUserInfoPanel() panel3 반환

	public GameScorePanel getGameScorePanel() {
		return panel4;
	}//getGameScorePanel() panel4 반환

	public HowtoPlayPanel getHowtoPlayPanel() {
		return panel5;
	}//getHowtoPlayPanel() panel5 반환

	public GameScorePanel makeGameScorePanel() {

		panel4 = new GameScorePanel();
		add(panel4);
		panel4.setMain(this);
		return panel4;
	}//makeGameScorePanel() panel4 생성, 삽입후 반환

}