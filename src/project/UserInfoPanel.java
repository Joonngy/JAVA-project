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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class UserInfoPanel extends JPanel {//게임이용자정보 입력 패널

	private JLabel lblUser;
	private JButton btnM, btnW, btnInput;
	private JTextField txtName;
	private GamePlayPanel gamePlayPanel;
	private btnListener btnL;
	private JLabel imageIcon1, imageIcon2;
	private ImageIcon icon1, icon2;
	private ImageIcon iconBackground;
	private PrimaryPanel pPanel;

	public UserInfoPanel(GamePlayPanel gamePlayPanel) {
		setBackground(Color.white);
		setPreferredSize(new Dimension(1200, 800));
		setLayout(null); //UserInfoPanel 사이즈, 배경, 레이아웃 설정
		this.gamePlayPanel = gamePlayPanel; //패널의 gamePlayPanel을 인자로 들어온 패널로 설정.

		btnL = new btnListener();

		lblUser = new JLabel("USER SELECT");
		lblUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser.setForeground(Color.orange);
		lblUser.setFont(new Font("Arial", Font.BOLD, 100));
		lblUser.setBounds(0, 30, 1200, 100);
		this.add(lblUser);//lblUser 생성, 설정하고 패널에 add

		btnM = new JButton("MAN");
		btnM.setBounds(400, 500, 150, 150);
		btnM.setBackground(Color.GREEN);
		btnM.setFont(new Font("Verdana", Font.BOLD, 20));
		btnM.addActionListener(btnL);
		this.add(btnM);//btnM: MAN버튼 생성, 설정, 패널에 add
		
		btnW = new JButton("WOMAN");
		btnW.setBackground(Color.pink);

		btnW.setFont(new Font("Verdana", Font.BOLD, 20));
		btnW.setBounds(600, 500, 150, 150);
		btnW.addActionListener(btnL);
		this.add(btnW); //btnW: WOMAN버튼 생성, 설정, 패널에 add

		txtName = new JTextField();
		txtName.setBounds(400, 700, 230, 60);
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.addActionListener(btnL);
		txtName.setBorder(BorderFactory.createTitledBorder("닉네임"));
		this.add(txtName); //닉네임 입력하는 텍스트필드 생성, 설정, 패널에 add

		btnInput = new JButton("INPUT");
		btnInput.setFont(new Font("Verdana", Font.BOLD, 15));
		btnInput.setBackground(Color.darkGray);
		btnInput.setForeground(Color.white);
		btnInput.setBounds(650, 700, 100, 60);
		btnInput.addActionListener(btnL); 
		this.add(btnInput);//Input버튼 생성, 설정, 패널에 add

		icon1 = new ImageIcon("image/manpicture.png");
		imageIcon1 = new JLabel(icon1, SwingConstants.CENTER);
		imageIcon1.setBounds(425, 150, 300, 300);
		add(imageIcon1);
		imageIcon1.setVisible(false);//남자그림 생성, 설정, 패널에 add. 안보이게 설정

		icon2 = new ImageIcon("image/womanpicture.png");
		imageIcon2 = new JLabel(icon2, SwingConstants.CENTER);
		imageIcon2.setBounds(425, 150, 300, 300);
		add(imageIcon2);
		imageIcon2.setVisible(false);//여자그림 생성, 설정, 패널에 add. 안보이게 설정

	}//UserInfoPanel()

	public void setMain(PrimaryPanel panel) {
		this.pPanel = panel;
	}//UsefInfoPanel의 pPanel을 인자로 들어온 panel로 설정

	public class btnListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			Object obj = event.getSource(); //getSource로 발생한 이벤트 정보 받아옴

			imageIcon2.setVisible(false);
			imageIcon1.setVisible(false); //이미지 아이콘 안보이게 설정

			if (obj == btnM) {
				gamePlayPanel.gender = 1;

				imageIcon2.setVisible(false);
				imageIcon1.setVisible(true); //MAN 버튼일때: gender 값 설정, 남자 그림 보이게 함
			} else if (obj == btnW) {
				gamePlayPanel.gender = 2;

				imageIcon1.setVisible(false);
				imageIcon2.setVisible(true); //WOMAN 버튼일때: gender 값 설정, 여자 그림 보이게 함
			}
			if (obj == txtName && gamePlayPanel.gender != 0) {//키보드의 엔터통해 닉네임 입력되었을때
				gamePlayPanel.name = txtName.getText(); //getText통해 입력된 값 gamePlayPanel의 name에 넣어줌
				txtName.setText("");//txtName 초기화
				gamePlayPanel.lblName.setText("" + gamePlayPanel.name); //gamePlayPanel의 lblName설정

				gamePlayPanel.lblScore.setVisible(true);
				gamePlayPanel.lblName.setVisible(true);
				gamePlayPanel.lblLevel.setVisible(true);

				gamePlayPanel.lblTitle.setVisible(true);
				gamePlayPanel.btnBack.setVisible(true);//gamePlayPanel의 점수,이름,레벨,타이틀,Back버튼 보이게 설정

				pPanel.getUserInfoPanel().setVisible(false);
				pPanel.getGamePlayPanel().setVisible(true); //UserInfoPanel에서 getGamePlayPanel로 패널전환

				if (gamePlayPanel.gender == 1) {
					gamePlayPanel.icon = new ImageIcon("image/manpicture.png"); //MAN버튼일때의 아이콘 설정
				} else if (gamePlayPanel.gender == 2)
					gamePlayPanel.icon = new ImageIcon("image/womanpicture.png");//WOMAN버튼일때의 아이콘 설정

				gamePlayPanel.imageIcon = new JLabel("", gamePlayPanel.icon, SwingConstants.CENTER);
				gamePlayPanel.imageIcon.setBounds(910, 210, 280, 280);
				gamePlayPanel.add(gamePlayPanel.imageIcon);//아이콘 위치와 정렬 설정, gamePlayPanel에 삽입

			} else if (obj == btnInput && gamePlayPanel.gender != 0) {//Input버튼 통해 닉네임 입력되었을때. 위 엔터통해 입력되었을때와 같은 동작

				gamePlayPanel.name = txtName.getText();

				gamePlayPanel.lblName.setText("" + gamePlayPanel.name);
				gamePlayPanel.lblScore.setVisible(true);
				gamePlayPanel.lblName.setVisible(true);
				gamePlayPanel.lblLevel.setVisible(true);

				gamePlayPanel.lblTitle.setVisible(true);
				gamePlayPanel.btnBack.setVisible(true);

				pPanel.getUserInfoPanel().setVisible(false);
				pPanel.getGamePlayPanel().setVisible(true);

				txtName.setText("");
				if (gamePlayPanel.gender == 1)
					gamePlayPanel.icon = new ImageIcon("image/manpicture.png");
				else if (gamePlayPanel.gender == 2)
					gamePlayPanel.icon = new ImageIcon("image/womanpicture.png");

				gamePlayPanel.imageIcon = new JLabel("", gamePlayPanel.icon, SwingConstants.CENTER);
				gamePlayPanel.imageIcon.setBounds(910, 210, 280, 280);
				gamePlayPanel.add(gamePlayPanel.imageIcon);

			}

		}//actionPerformed
	}//btnListener class

	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		iconBackground = new ImageIcon("image/userPanelimg.jpg");

		page.drawImage(iconBackground.getImage(), 0, 0, null);

	}//paintComponent() 배경이미지 설정

}
