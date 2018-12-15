package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {//GamePanel 게임 메인화면 패널

	private JButton btnPlay, btnHow, btnScore;
	private BtnListener btnL;
	private PrimaryPanel pPanel;
	private ImageIcon img;

	public GamePanel() {

		setBackground(Color.white);
		setPreferredSize(new Dimension(1200, 800));
		setLayout(null); //GamePanel 배경색, 사이즈 설정.

		btnL = new BtnListener();

		btnPlay = new JButton("PLAY");
		btnPlay.setBounds(850, 400, 300, 100);
		btnPlay.setForeground(Color.white);
		btnPlay.setFont(new Font("Verdana", Font.PLAIN, 30));
		btnPlay.setBackground(Color.GRAY);
		btnPlay.setHorizontalAlignment(SwingConstants.CENTER);
		btnPlay.addActionListener(btnL);
		add(btnPlay); //play 버튼  설정

		btnHow = new JButton("How To Play");
		btnHow.setBounds(850, 510, 300, 100);
		btnHow.setForeground(Color.white);
		btnHow.setFont(new Font("Verdana", Font.PLAIN, 30));
		btnHow.setBackground(Color.GRAY);
		btnHow.setHorizontalAlignment(SwingConstants.CENTER);
		btnHow.addActionListener(btnL);
		add(btnHow); //How To Play 버튼 설정

		btnScore = new JButton("ScoreBoard");
		btnScore.setBounds(850, 620, 300, 100);
		btnScore.setForeground(Color.white);
		btnScore.setFont(new Font("Verdana", Font.PLAIN, 30));
		btnScore.setBackground(Color.GRAY);
		btnScore.setHorizontalAlignment(SwingConstants.CENTER);
		btnScore.addActionListener(btnL);
		add(btnScore); //ScoreBoard 버튼 설정

	}//GamePanel() 생성자. 메인화면 사이즈와 버튼 배치 설정.

	public void paintComponent(Graphics page) {

		super.paintComponent(page);

		img = new ImageIcon("image/cgv1.jpg");

		page.drawImage(img.getImage(), -50, 0, null);

	} //paintComponent() 메인화면 배경이미지 설정

	public void setMain(PrimaryPanel panel) {
		this.pPanel = panel;
	}

	private class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource(); //버튼 파악위해 Object 방식으로 정보 받아줌
			String message;
			if (obj == btnPlay) {
				pPanel.getGamePanel().setVisible(false);
				pPanel.getGamePlayPanel().setVisible(false);
				pPanel.getUserInfoPanel().setVisible(true); //Play버튼: 사용자 정보 설정 패널 띄워줌
			} else if (obj == btnHow) {
				pPanel.getHowtoPlayPanel().setVisible(true);
				pPanel.getGamePanel().setVisible(false);//HowToPlay버튼: 게임 방법 패널 띄워줌
			} else if (obj == btnScore) {
				pPanel.getGamePanel().setVisible(false);
				pPanel.makeGameScorePanel().setVisible(true);//Score버튼: 점수판 패널 띄워줌
			}//if 버튼 구분하여 맞는 패널 띄워줌.
		}//actionPerformed
	}//BtnListener

}