package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PlayPanel extends JPanel {//PlayPanel 게임실행패널

	protected GamePlayPanel view;

	protected JTextField txtAnswer;
	private ButtonListener btnL;
	protected ThreadSpread threadSpread;
	protected JLabel lblLife;
	protected ArrayList<QuizData> quizList;
	protected QuizLabel quiz[];
	protected int quizCnt = 0;
	protected JButton btnStart;
	private PlayPanel playPanel;
	private String answer;
	protected int gameCnt = 0;
	protected int allQuizCnt = 0;
	private int DeleteCnt = 0;
	private int answerCnt = 0;
	private ImageIcon backGround;
	private File file;
	private String strQuiz;
	private String strAnswer;
	private String line;
	private int lineCnt = 0;

	public PlayPanel(GamePlayPanel view) {

		setBounds(0, 100, 900, 700);
		setLayout(null); //패널 위치, 레이아웃 설정

		this.view = view;
		playPanel = this;
		btnL = new ButtonListener();

		btnStart = new JButton("START");
		btnStart.setBounds(10, 7, 100, 20);
		btnStart.setFont(new Font("Verdana", Font.BOLD, 15));
		btnStart.setBackground(Color.DARK_GRAY);
		btnStart.setForeground(Color.white);
		btnStart.addActionListener(btnL);
		add(btnStart); //Start버튼 생성, 설정, 패널에 add

		txtAnswer = new JTextField();
		txtAnswer.setBounds(300, 570, 300, 50);
		txtAnswer.setHorizontalAlignment(SwingConstants.CENTER);
		txtAnswer.addActionListener(btnL);
		add(txtAnswer);
		txtAnswer.setEnabled(false);//정답입력 텍스트필드 생성, 설정, 패널에 add. 비활성화해둠

		lblLife = new JLabel("LIFE : " + view.life);
		lblLife.setFont(new Font("Verdana", Font.BOLD, 15));
		lblLife.setBounds(300, 35, 300, 30);
		lblLife.setForeground(Color.black);
		lblLife.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblLife);//lblLife 생성, 설정, 패널에 add.

		quizList = new ArrayList<QuizData>();

		file = new File("TXT/quizData.txt");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			//파일 입력받음
			try {
				while ((line = br.readLine()) != null) {
					lineCnt++;//파일 총 몇줄인지 count
					if (lineCnt % 2 == 1) {
						strQuiz = line;//홀수번째 줄이면: 퀴즈 스트링에 넣어줌
					} else {
						strAnswer = line;//짝수번째 줄이면: 정답 스트링에 넣어줌.
						quizList.add(new QuizData("" + strQuiz, "" + strAnswer));//퀴즈리스트에 퀴즈와 정답 정보 넣어줌
						allQuizCnt++;//퀴즈 개수 카운팅
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//예외 처리해줌

	}

	private class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {

			Object obj = event.getSource();//getSource로 발생한 이벤트 파악

			if (obj == btnStart) { //Start버튼일때

				btnStart.setEnabled(false);
				txtAnswer.setEnabled(true);//시작버튼 비활성화, 정답입력 활성화
				if (quizCnt != 0) { 
					for (int i = 0; i < quizCnt; i++) {
						quiz[i].setVisible(false);
					}
				}
				quizCnt = 0;
				threadSpread = new ThreadSpread(playPanel); //쓰레드 생성

				view.level = 1;
				view.life = 3;
				view.score = 0;
				view.lblLevel.setText("LEVEL : " + view.level);
				view.lblScore.setText("SCORE : " + view.score);
				lblLife.setForeground(Color.black);
				lblLife.setText("LIFE : " + view.life);//level,life,score등 초기화

				gameCnt++;
				threadSpread.start();//쓰레드 start

			} else if (obj == txtAnswer) {//텍스트필드에 입력되었을때

				answer = txtAnswer.getText();//입력된 텍스트 answer에 저장
				txtAnswer.setText("");//초기화
				for (int i = 0; i < quizCnt; i++) {//quizCnt만큼 반복

					if (answer.equals(quiz[i].getAnswer()) && quiz[i].isVisible()) {//입력된 값과 정답이 일치하고, 퀴즈가 보이는 상태일때

						if (quiz[i].getForeground() != Color.green) {
							quiz[i].setVisible(false);
							quiz[i].stop();
							answerCnt++;
							view.score += 50;
							view.lblScore.setText("SCORE : " + view.score);//
						}//맞춘 정답이 초록색이 아니면(검정, 빨강, 파랑): 맞춘 문제는 사라지고 정답카운트, 점수 갱신

						if (quiz[i].getForeground().equals(Color.red)) {

							threadSpread.sleep(3000);
							for (int j = 0; j < quizCnt; j++) {
								if (quiz[j].isVisible())
									quiz[j].sleep(3000); //맞춘 정답이 빨강색이었다면(아이템): 쓰레드를 sleep(3초)
							}

						} else if (quiz[i].getForeground().equals(Color.green)) {

							for (int j = 0; j < quizCnt; j++) {
								if (quiz[j].isVisible()) {

									quiz[j].setVisible(false);
									DeleteCnt++;
									answerCnt++;
								}
							}//모든 문제 갯수를 세며 안보이게 처리.
							quiz[i].setVisible(false);
							quiz[i].stop();//퀴즈 멈춤
							view.score += 50 * DeleteCnt;
							DeleteCnt = 0;
							view.lblScore.setText("SCORE : " + view.score);
						}//맞춘 정답이 초록색이었다면(아이템): 모든 퀴즈문제들을 지워버림.초기화. 지워진만큼의 점수를 올려주고, 점수 재설정

						if (view.score >= 400 && view.level == 1) {
							view.level++;
							view.lblLevel.setText("LEVEL : " + view.level);
							for (int j = 0; j < quizCnt; j++) {
								if (quiz[j].isVisible())
									quiz[j].setnDelayTime(70);
							}//레벨1일때 점수 400돌파하면: 레벨업, 문제 내려오는 시간 조절

						} else if (view.score >= 800 && view.level == 2) {
							view.level++;
							view.lblLevel.setText("LEVEL : " + view.level);

							for (int j = 0; j < quizCnt; j++) {
								if (quiz[j].isVisible())
									quiz[j].setnDelayTime(50);
							}//레벨2일때 점수 800돌파하면: 레벨업, 문제 내려오는 시간 조절

						} else if (view.score >= 1200) {

							btnStart.setEnabled(true);
							threadSpread.stop();//쓰레드 멈춤
							txtAnswer.setEnabled(false);//답 입력 비활성화
							view.score = 1200;//최대점수는 1200점이므로.
							for (int j = 0; j < quizCnt; j++) {
								if (quiz[j].isVisible())
									quiz[j].stop();

							}//모든 퀴즈들 멈춤

							JOptionPane.showMessageDialog(playPanel, "" + view.score + "만점입니다. 수고하셨습니다.", "승리",
									JOptionPane.PLAIN_MESSAGE);//메세지 창으로 만점 알려줌

							BufferedWriter bw;
							try {
								bw = new BufferedWriter(new FileWriter("TXT/rankData.txt", true));
								PrintWriter pw = new PrintWriter(bw, true);

								pw.write("" + view.name + "\n");
								pw.write("" + view.score + "\n");
								pw.flush();
								pw.close();
								//rankData파일에 닉네임과 점수 정보를 입력해줌
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}//예외처리
						}

						if (quiz[i].getForeground().equals(Color.blue)) {

							for (int j = 0; j < quizCnt; j++) {
								if (quiz[j].isVisible())
									quiz[j].setnDelayTime(400);

							}

						}//맞춘 정답이 파란색이었다면(아이템): 퀴즈 내려오는 속도 조정

						break;

					} // if
				} // for
			} // obj == txtAnswer

		}// actionPerformed()

	}// ButtonListener()

	public void paintComponent(Graphics page) {
		super.paintComponent(page);

		backGround = new ImageIcon("image/theater.png");

		page.drawImage(backGround.getImage(), 0, 0, null); 

	}//paintComponent() 배경이미지 설정

}// PlayPanel()
