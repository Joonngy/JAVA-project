package project;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class QuizLabel extends JLabel implements Runnable {
	private String quiz;
	private String answer;
	private int nDelayTime;
	private Thread myThread;
	private PlayPanel playPanelview;
	private Color color;
	private int sleep;
	private int sleepTime;

	public QuizLabel(String quiz, String answer, PlayPanel view) {
		this.setText("" + quiz);
		this.quiz = quiz;
		this.answer = answer;
		sleep = 0;
		playPanelview = view;
	}//QuizLabel() 기본값 설정

	public String getAnswer() {
		return answer;
	}//getAnswer() answer반환

	public void setnDelayTime(int delaytime) {
		nDelayTime = delaytime;
	}//setnDelayTime() nDelayTime설정

	public void sleep(int time) {
		sleep = 1;
		sleepTime = time;
	}//sleep() sleep,sleepTime 설정

	public void start() {
		if (myThread == null) {
			myThread = new Thread(this);
		}
		myThread.start();
	}//start() 쓰레드 시작

	public void stop() {
		if (myThread != null) {
			myThread.stop();
		}
	}//stop() 쓰레드 중지

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			for (int i = 0; i < 420; i++) {
				if (sleep == 1) {
					myThread.sleep(sleepTime);
					sleep = 0;
				}//sleep 1일경우 쓰레드 sleep시키고 값 초기화
				myThread.sleep(nDelayTime);

				setLocation(this.getX(), this.getY() + 1);//위치설정

				if (this.getY() == 420 && this.isVisible()) {//y좌표 420이고 보이는 상태일때(문제가 바닥에 닿았을때)
					playPanelview.view.life--;
					this.setVisible(false);//라이프 갱신하고 문제 지움
					if (playPanelview.view.life == 2)
						playPanelview.lblLife.setForeground(Color.blue);
					else if (playPanelview.view.life == 1)
						playPanelview.lblLife.setForeground(Color.pink);
					else if (playPanelview.view.life <= 0) {
						playPanelview.lblLife.setForeground(Color.red); //남은 라이프 별로 글씨 색 바꿔줌
						playPanelview.view.life = 0;
						playPanelview.lblLife.setText("LIFE : " + playPanelview.view.life);
						playPanelview.btnStart.setEnabled(true);
						playPanelview.threadSpread.stop();
						playPanelview.txtAnswer.setEnabled(false);
						playPanelview.txtAnswer.setText(""); //라이프 다 소진하면, 쓰레드를 멈추고 Start버튼 활성화, 정답입력창 초기화&비활성화

						for (int j = 0; j < playPanelview.quizCnt; j++) {//
							if (playPanelview.quiz[j].isVisible())
								playPanelview.quiz[j].stop();

						}//라이프 다 소진했으므로 현재 보이는 퀴즈문제들 정지시킴
						JOptionPane.showMessageDialog(playPanelview, "" + playPanelview.view.score + "점 입니다 수고하셨습니다.",
								"패배", JOptionPane.PLAIN_MESSAGE); //패배 알리는 메시지창

						BufferedWriter bw;
						try {
							bw = new BufferedWriter(new FileWriter("TXT/rankData.txt", true));
							PrintWriter pw = new PrintWriter(bw, true);

							pw.write("" + playPanelview.view.name + "\n");
							pw.write("" + playPanelview.view.score + "\n");
							pw.flush();
							pw.close();
							//rankData 파일에 이름, 점수 입력.
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}//예외처리

					}

					playPanelview.lblLife.setText("LIFE : " + playPanelview.view.life);//lblLife 재설정

				}

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//예외처리
	}
}
