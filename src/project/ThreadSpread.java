package project;

import java.awt.Color;

public class ThreadSpread implements Runnable { //쓰레드

	private int nDelayTime;
	private Thread myThread;
	private PlayPanel playPanelview;
	private int random;
	private int sleep;
	private int sleepTime;
	private int item1;
	private int item2;
	private int item3;
	private int overLap[];
	private int overLapCnt = 0;

	public ThreadSpread(PlayPanel view) {
		nDelayTime = 3000;
		myThread = null;
		playPanelview = view;
		playPanelview.quiz = new QuizLabel[playPanelview.allQuizCnt];
		overLap = new int[playPanelview.allQuizCnt];
		sleep = 0;

	}//ThreadSpread() 데이터들 초기화.

	public void sleep(int time) {
		sleep = 1;
		sleepTime = time;
	}//sleep() sleep,sleepTime 설정

	public void start() {
		if (myThread == null) {
			myThread = new Thread(this);
		}
		myThread.start();
	}//start() myThread 시작

	public void stop() {
		if (myThread != null) {
			myThread.stop();
		}
	}//stop() myThread 정지

	public void setnDelayTime(int nDelayTime) {
		this.nDelayTime = nDelayTime;
	}//setnDelayTime() 인자를 nDelaytime에 넣어줌

	public void run() {

		try {
			
			for (int i = 0; i < playPanelview.allQuizCnt; i++) { //allquizCnt(만들어둔 문제개수) 만큼 반복해줌

				if (sleep == 1) {
					myThread.sleep(sleepTime);
					sleep = 0;
				}//sleep이 1일때: 쓰레드 sleepTime만큼 정지해두고 sleep 초기화
				random = (int) (Math.random() * playPanelview.allQuizCnt);
				overLap[i] = random;//생성한 난수 오버랩 배열에 넣어줌

				for (int j = 0; j < overLapCnt; j++) { //overLapCnt만큼 반복

					while (overLap[j] == random) {
						random = (int) (Math.random() * playPanelview.allQuizCnt);
						j = 0;
					}

				}
				overLap[i] = random;
				overLapCnt++; //새로 설정된 랜덤값 오버랩에 넣어주고 카운트 갱신.

				item1 = (int) (Math.random() * playPanelview.allQuizCnt);
				item2 = (int) (Math.random() * playPanelview.allQuizCnt);
				item3 = (int) (Math.random() * playPanelview.allQuizCnt);
				playPanelview.quiz[i] = new QuizLabel(playPanelview.quizList.get(random).quiz, playPanelview.quizList.get(random).answer, playPanelview);
				if (i == item1)
					playPanelview.quiz[i].setForeground(Color.red);
				else if (i == item2)
					playPanelview.quiz[i].setForeground(Color.blue);
				else if (i == item3)
					playPanelview.quiz[i].setForeground(Color.green);//랜덤으로 생성한 난수로 아이템 문제 만들어줌
				playPanelview.quiz[i].setBounds((int) (Math.random() * 600) + 100, 30, 300, 15);
				playPanelview.add(playPanelview.quiz[i]);//퀴즈문제의 x좌표는 랜덤으로 설정, 패널에 넣어줌

				if (playPanelview.view.level == 1) {
					playPanelview.quiz[i].setnDelayTime(100);
				} else if (playPanelview.view.level == 2) {
					playPanelview.quiz[i].setnDelayTime(70);
				}

				else if (playPanelview.view.level == 3) {
					playPanelview.quiz[i].setnDelayTime(50);
				}											//레벨에 따른 퀴즈문제들의 떨어지는 속도 설정

				playPanelview.quiz[i].setVisible(true);
				playPanelview.quizCnt++;
				playPanelview.quiz[i].start();
				myThread.sleep(nDelayTime);//퀴즈문제 보이게 설정, 떨어지게 시작시킴.
			}

		}//try
		catch (InterruptedException e) {

			e.printStackTrace();
		}//예외처리

	}//run()

}// ThreadSpread
