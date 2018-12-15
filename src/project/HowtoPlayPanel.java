package project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class HowtoPlayPanel extends JPanel {//HowtoPlayPanel 게임방법패널

	private ImageIcon img;
	private PrimaryPanel pPanel;
	private JButton btnBack;
	private ActionListener btnL;
	private JPanel item, play;
	private JLabel title;
	private JLabel lbl0, lbl1, lbl2, lbl3, lbl4;
	private JLabel lbl5, lbl6, lbl7, lbl8;
	private JLabel lbl9;
	private JLabel lblred, lblblue, lblgreen;
	private ImageIcon icon;
	private JLabel image;

	public HowtoPlayPanel() {
		setPreferredSize(new Dimension(1200, 800));
		setBackground(Color.white);
		setLayout(null);//패널 사이즈, 배경색, 레이아웃 설정

		btnL = new BtnListener();

		title = new JLabel("HOW TO PLAY");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setForeground(Color.orange);
		title.setFont(new Font("Arial", Font.BOLD, 100));
		title.setBounds(0, 30, 1200, 100);
		add(title); //title 설정

		btnBack = new JButton("BACK");
		btnBack.setFont(new Font("Verdana", Font.BOLD, 50));
		btnBack.addActionListener(btnL);
		btnBack.setBounds(0, 0, 200, 80);
		btnBack.setBackground(Color.DARK_GRAY);
		btnBack.setForeground(Color.white);
		add(btnBack); //Back 버튼 설정

		play = new JPanel();
		play.setBounds(150, 150, 500, 500);
		play.setBackground(Color.white);
		play.setLayout(null);
		add(play); //play 패널 설정, HowtoPlayPanel에 add
		
		item = new JPanel();
		item.setBounds(650, 150, 350, 500);
		item.setBackground(Color.white);
		item.setLayout(null);
		add(item);//item 패널 설정, HowtoPlayPanel에 add

		lbl0 = new JLabel("게임방법");
		lbl0.setBounds(20, 20, 100, 20);
		play.add(lbl0);

		lbl9 = new JLabel("1. PLAY버튼을 누른후 성별과 닉네임을 입력하세요");
		lbl9.setBounds(20, 50, 500, 20);
		play.add(lbl9);

		lbl1 = new JLabel("2. 하늘에서 내려오는 명대사들의 영화제목을 맞추세요");
		lbl1.setBounds(20, 80, 500, 20);
		play.add(lbl1);
		lbl2 = new JLabel("3. 명대사가 바닥에 떨어지면 라이프가줄어들고 라이프가0이되면 게임이끝나게 됩니다.");
		lbl2.setBounds(20, 110, 500, 20);
		play.add(lbl2);
		lbl3 = new JLabel("4. 아이템들을 사용해서 더높은단계에 도전하세요");
		lbl3.setBounds(20, 140, 500, 20);
		play.add(lbl3);
		lbl4 = new JLabel("5. 즐겜~");
		lbl4.setBounds(20, 170, 500, 20);
		play.add(lbl4); 		//게임방법 설명하는 lbl들 설정, play 패널에 배치

		icon = new ImageIcon("image/how.png");
		image = new JLabel(icon);
		image.setBounds(70, 210, 300, 243);
		play.add(image);//게임화면 예시 이미지 play패널에 배치

		lbl5 = new JLabel("아이템");
		lbl5.setBounds(20, 20, 100, 20);
		item.add(lbl5);

		lblred = new JLabel("Red Color");
		lblred.setForeground(Color.red);
		lblred.setBounds(20, 60, 100, 20);
		item.add(lblred);
		lbl6 = new JLabel("빨간글씨 정답을 맞추면 3초간 화면이 멈춥니다.");
		lbl6.setBounds(20, 80, 400, 20);
		item.add(lbl6);

		lblblue = new JLabel("Blue Color");
		lblblue.setForeground(Color.blue);
		lblblue.setBounds(20, 120, 100, 20);
		item.add(lblblue);
		lbl7 = new JLabel("파란글씨 정답을 맞추면 내려오는 속도가 줄어듭니다.");
		lbl7.setBounds(20, 140, 400, 20);
		item.add(lbl7);

		lblgreen = new JLabel("Green Color");
		lblgreen.setForeground(Color.green);
		lblgreen.setBounds(20, 180, 100, 20);
		item.add(lblgreen);
		lbl8 = new JLabel("초록글씨 정답을 맞추면 화면이 초기화됩니다.");
		lbl8.setBounds(20, 200, 400, 20);
		item.add(lbl8); 		//아이템 설명하는 lbl들 설정, item 패널에 배치.

	}//HowtoPlayPanel() 생성자. play, item 패널로 이루어짐

	public void setMain(PrimaryPanel panel) {
		this.pPanel = panel;
	}//setMain() HowtoPlayPanel의 pPanel을 인자로 들어온 panel로.

	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		img = new ImageIcon("image/cgv1.jpg");

		page.drawImage(img.getImage(), -50, 0, null);
	}//paintComponent() 배경 이미지 설정

	public class BtnListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == btnBack) {//Back버튼일때
				pPanel.getGamePanel().setVisible(true);
				pPanel.getHowtoPlayPanel().setVisible(false); //GamePanel로 화면 전환
			}//if 
		}//actionPerformed()

	}//BtnListener
}