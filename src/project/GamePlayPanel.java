package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePlayPanel extends JPanel {//GamePlayPanel 게임시작전대기패널

	public JLabel lblTitle, lblName, lblLevel, lblScore;
	public JButton btnBack;
	public ImageIcon icon;
	public JLabel imageIcon;

	private Font fnt;

	private PlayPanel playPanel;

	private UserInfoPanel userinfoPanel;
	public int gender = 0;
	public String name = "";
	private btnListener btnL;

	public int level;
	public int score;
	public int life = 3;

	private PrimaryPanel pPanel;

	private ImageIcon iconBackground;

	public GamePlayPanel() {
		setBackground(Color.white);
		setPreferredSize(new Dimension(1200, 800));
		setLayout(null);
		level = 1;
		score = 0; //level,score 초기화

		fnt = new Font("Arial", Font.BOLD, 25);

		btnL = new btnListener();

		lblTitle = new JLabel("PLAY GAME !!");
		lblTitle.setBorder(BorderFactory.createLineBorder(Color.white));
		lblTitle.setForeground(Color.white);
		lblTitle.setFont(fnt);
		lblTitle.setBounds(300, 30, 300, 50);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);
		lblTitle.setVisible(false); //Title 설정

		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Verdana", Font.ITALIC, 20));
		btnBack.setBounds(910, 50, 100, 50);
		btnBack.setBackground(Color.DARK_GRAY);
		btnBack.setForeground(Color.white);
		btnBack.addActionListener(btnL);
		add(btnBack);
		btnBack.setVisible(false); //Back 버튼 설정

		// INFO
		lblName = new JLabel("" + name);
		lblName.setBounds(910, 110, 280, 90);
		lblName.setForeground(Color.white);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBorder(BorderFactory.createLineBorder(Color.white));
		add(lblName);
		lblName.setVisible(false); //lblName 설정

		lblLevel = new JLabel("LEVEL : " + level);
		lblLevel.setBounds(910, 510, 280, 90);
		lblLevel.setFont(fnt);

		lblLevel.setForeground(Color.white);
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setBorder(BorderFactory.createLineBorder(Color.white));
		add(lblLevel);
		lblLevel.setVisible(false); //lblLevel 설정

		lblScore = new JLabel("SCORE : " + score);
		lblScore.setBounds(910, 610, 280, 90);

		lblScore.setForeground(Color.white);
		lblScore.setFont(fnt);
		lblScore.setHorizontalAlignment(SwingConstants.CENTER);
		lblScore.setBorder(BorderFactory.createLineBorder(Color.white));
		add(lblScore);
		lblScore.setVisible(false); //lblScore 설정

		// userinfo
		userinfoPanel = new UserInfoPanel(this);

		playPanel = new PlayPanel(this);
		add(playPanel); //GamePlayPanel에 playPanel 넣음.

	}// GamePlayPanel()

	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		iconBackground = new ImageIcon("image/background.jpg");

		page.drawImage(iconBackground.getImage(), 0, 0, null);

	}//paintComponent() GamePlayPanel 배경이미지 설정

	public void setMain(PrimaryPanel panel) {
		this.pPanel = panel;
	}

	public class btnListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			Object obj = event.getSource(); //버튼 정보  Object로 받아줌

			if (obj == btnBack) { //Back 버튼 눌렸을때를 고려

				gender = 0;
				level = 1;
				life = 3;
				score = 0; //gender,level,life,score 초기화

				playPanel.gameCnt = 0; //게임횟수 초기화
				lblLevel.setText("LEVEL : " + level);
				lblScore.setText("SCORE : " + score); 
				playPanel.lblLife.setForeground(Color.black);
				playPanel.lblLife.setText("LIFE : " + life); //level과 score, life 텍스트 초기화

				if (playPanel.quizCnt != 0)
					playPanel.threadSpread.stop(); //퀴즈 카운트가 0이 아니면 쓰레드 멈춤
				playPanel.btnStart.setEnabled(true);//Start 버튼 활성화
				for (int i = 0; i < playPanel.quizCnt; i++) {
					if (playPanel.quiz[i].isVisible())
						playPanel.quiz[i].stop();//퀴즈문제 멈춤
				}//퀴즈 카운트 횟수만큼 반복
				if (playPanel.quizCnt != 0) {
					for (int i = 0; i < playPanel.quizCnt; i++) {
						playPanel.quiz[i].setVisible(false);//퀴즈문제 안보이게 설정
					}
				}//퀴즈 카운트 횟수만큼 반복
				pPanel.getGamePanel().setVisible(true);
				pPanel.getGamePlayPanel().setVisible(false);
				imageIcon.setVisible(false);
			}//if 
		}//actionPerformed()

	}//btnListener

}