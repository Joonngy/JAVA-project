package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameScorePanel extends JPanel { //GameScorePanel 점수판 패널

	private ImageIcon iconBackground;

	private PrimaryPanel pPanel;

	private JLabel firstName, secoundName, thirdName;
	private JLabel firstScore, secoundScore, thirdScore;
	private JLabel lblTitle;
	private JLabel rank4, rank5, rank6, rank7;
	private Font fnt;
	private JButton btnBack;
	private BtnListener btnL;
	private File file;
	private String line;
	private int lineCnt = 0;
	private ArrayList<rankData> rankList;
	private int rankCnt = 0;
	private rankData rankData;
	private ImageIcon icon2, icon3;

	public GameScorePanel() {
		setPreferredSize(new Dimension(1200, 800));
		setLayout(null); //패널 사이즈, 레이아웃 설정

		rankList = new ArrayList<rankData>();
		rankData = new rankData();
		file = new File("TXT/rankData.txt");//rankList,rankData,file 생성.

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			//버퍼리더 br 생성하여 파일 입력받음
			
			try {
				while ((line = br.readLine()) != null) {
					lineCnt++;
					if (lineCnt % 2 == 1) {
						rankData.name = line; //lineCnt 홀수이면(입력받은 파일의 홀수번째 줄:이름 저장되어있음) rankData.name에 저장
					} else {
						rankData.score = Integer.parseInt(line);
						rankCnt++;
						rankList.add(new rankData(rankData.name, rankData.score));
					}//lineCnt 짝수이면 점수정보이므로 정수로 변환하여 rankData.score에 저장. 이름과 점수를 입력받았으므로 rankList에 add

				}//try 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}//

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//파일입력에서 발생하는 예외들 처리해줌

		DescendingObj descending = new DescendingObj();
		Collections.sort(rankList, descending); //내림차순 정렬

		fnt = new Font("Arial", Font.BOLD, 35);
		if (rankCnt < 1)
			firstName = new JLabel("NORECORD"); //랭크카운트(등록된 점수) 1보다 작을때
		else
			firstName = new JLabel("1st  " + rankList.get(0).name);//1등 이름 출력 설정
		firstName.setBounds(553, 120, 100, 30);
		firstName.setHorizontalAlignment(SwingConstants.CENTER);
		firstName.setOpaque(true);
		firstName.setBackground(Color.black);
		firstName.setForeground(Color.orange);
		add(firstName);//패널에 firstName(1등 이름) 삽입

		if (rankCnt < 1)
			firstScore = new JLabel("NORECORD"); //랭크카운트(등록된 점수) 1보다 작을때
		else
			firstScore = new JLabel("SCORE : " + rankList.get(0).score);//점수 출력 설정
		firstScore.setBounds(453, 160, 300, 40);
		firstScore.setHorizontalAlignment(SwingConstants.CENTER);
		firstScore.setFont(fnt);

		add(firstScore);//패널에 firstScore(1등 점수) 삽입

		if (rankCnt < 2)
			secoundName = new JLabel("NORECORD");//랭크카운트 2보다 작을때(2등 존재X)
		else
			secoundName = new JLabel("2nd  " + rankList.get(1).name);//이름 출력 설정
		secoundName.setBounds(223, 500, 100, 30);
		secoundName.setHorizontalAlignment(SwingConstants.CENTER);
		secoundName.setOpaque(true);
		secoundName.setBackground(Color.black);
		secoundName.setForeground(Color.LIGHT_GRAY);
		add(secoundName);//패널에 secondName(2등 이름) 삽입

		if (rankCnt < 2)
			secoundScore = new JLabel("NORECORD");
		else
			secoundScore = new JLabel("SCORE : " + rankList.get(1).score);//점수 출력 설정
		secoundScore.setBounds(123, 540, 300, 40);
		secoundScore.setHorizontalAlignment(SwingConstants.CENTER);
		secoundScore.setFont(fnt);
		add(secoundScore);//패널에 secondScore(2등 점수) 삽입

		if (rankCnt < 3)
			thirdName = new JLabel("NORECORD"); //랭크카운트 3보다 작을때(3등 존재X)
		else
			thirdName = new JLabel("3th  " + rankList.get(2).name); //이름 출력 설정
		thirdName.setBounds(883, 500, 100, 30);
		thirdName.setHorizontalAlignment(SwingConstants.CENTER);
		thirdName.setOpaque(true);
		thirdName.setBackground(Color.black);
		thirdName.setForeground(new Color(185, 122, 87));
		add(thirdName); //패널에 thirdName(3등 이름) 삽입

		if (rankCnt < 3)
			thirdScore = new JLabel("NORECORD");
		else
			thirdScore = new JLabel("SCORE : " + rankList.get(2).score);//점수 출력 설정
		thirdScore.setBounds(783, 540, 300, 40);
		thirdScore.setHorizontalAlignment(SwingConstants.CENTER);
		thirdScore.setFont(fnt);
		add(thirdScore);//패널에 thirdScore(3등 점수) 삽입

		if (rankCnt < 4)
			rank4 = new JLabel("4. NORECORD");
		else
			rank4 = new JLabel("4. " + rankList.get(3).name + "   " + rankList.get(3).score);
		if (rankCnt < 5)
			rank5 = new JLabel("5. NORECORD");
		else
			rank5 = new JLabel("5. " + rankList.get(4).name + "   " + rankList.get(4).score);
		if (rankCnt < 6)
			rank6 = new JLabel("6. NORECORD");
		else
			rank6 = new JLabel("6. " + rankList.get(5).name + "   " + rankList.get(5).score);
		if (rankCnt < 7)
			rank7 = new JLabel("7. NORECORD");
		else
			rank7 = new JLabel("7. " + rankList.get(6).name + "   " + rankList.get(6).score);

		rank4.setBounds(950, 100, 300, 30);
		rank5.setBounds(950, 140, 300, 30);
		rank6.setBounds(950, 180, 300, 30);
		rank7.setBounds(950, 220, 300, 30);
		add(rank4);
		add(rank5);
		add(rank6);
		add(rank7);		//4~7등의 이름과 점수 정보 설정&패널 삽입

		btnL = new BtnListener();

		lblTitle = new JLabel("RANKING");
		lblTitle.setBounds(450, 400, 300, 40);
		lblTitle.setFont(fnt);
		lblTitle.setForeground(Color.black);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblTitle);//타이틀 설정& 패널 삽입

		btnBack = new JButton("BACK");
		btnBack.setBounds(950, 40, 100, 30);
		btnBack.setFont(new Font("Verdana", Font.BOLD, 20));
		btnBack.setForeground(Color.white);
		btnBack.setBackground(Color.black);
		btnBack.addActionListener(btnL);
		add(btnBack);//Back 버튼 설정& 패널 삽입

	}//GameScorePanel() 스코어판 패널 설정.

	public void setMain(PrimaryPanel panel) {
		this.pPanel = panel;
	}//setMain() 인자로 들어온 패널을 스코어패널의 pPanel로 설정

	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		iconBackground = new ImageIcon("image/rank.jpg");
		page.drawImage(iconBackground.getImage(), 0, 0, null); //배경 이미지 설정, 삽입

		icon2 = new ImageIcon("image/index.png");
		page.drawImage(icon2.getImage(), 50, 150, null);
		icon3 = new ImageIcon("image/index2.png");
		page.drawImage(icon3.getImage(), 880, 150, null); //빵빠레 이미지 설정, 삽입

	}//paintComponent()

	public class DescendingObj implements Comparator<rankData> {

		@Override
		public int compare(rankData o1, rankData o2) {
			return o2.score.compareTo(o1.score);
		}//compare()

	}//DescendingObj class 내림차순 정렬하는 클래스.

	private class BtnListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			Object obj = event.getSource();

			if (obj == btnBack) { //Back버튼이면

				pPanel.getGameScorePanel().setVisible(false);
				pPanel.getGamePanel().setVisible(true); //스코어패널에서 메인화면패널로 화면 전환

			}//if
		}//actionPerformed()

	}//BtnListener class

}