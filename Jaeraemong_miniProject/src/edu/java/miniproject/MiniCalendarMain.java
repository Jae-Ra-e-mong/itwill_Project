package edu.java.miniproject;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.awt.event.ActionEvent;

public class MiniCalendarMain extends CalendarDataManager {

	JFrame frame;
	ImageIcon icon = 
			new ImageIcon(Toolkit.getDefaultToolkit()
					.getImage(getClass().getResource("icon.png")));

	JPanel infoPanel;
	JLabel infoClock;
	JLabel selectedDate;

	JPanel calOpPanel;
	JLabel todayLab;
	JButton todayBut;
	JButton lYearBut;
	JButton lMonBut;
	JButton nMonBut;
	JButton nYearBut;
	JLabel curMMYYYYLab;
	ListenForCalOpButtons lForCalOpButtons = new ListenForCalOpButtons();

	JPanel memoPanel;
	JTextArea memoArea;

	JPanel calPanel;
	JButton weekDaysName[];
	JButton dateButs[][] = new JButton[6][7];
	listenForDateButs lForDateButs = new listenForDateButs();

	JPanel frameBottomPanel;
	JLabel bottomInfo;

	JPanel allTextPanel;
	JTextArea allTextArea;
	MiniCalDao dao;

	final String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
	final String title = "캘린더 달력 ver 1.0";
	final String SaveButMsg1 = "를 MemoData폴더에 저장하였습니다.";
	final String SaveButMsg2 = "메모를 먼저 작성해 주세요.";
	final String SaveButMsg3 = "<html><font color=red>ERROR : 파일 쓰기 실패</html>";
	final String DelButMsg1 = "메모를 삭제하였습니다.";
	final String DelButMsg2 = "작성되지 않았거나 이미 삭제된 memo입니다.";
	final String DelButMsg3 = "<html><font color=red>ERROR : 파일 삭제 실패</html>";
	final String ClrButMsg1 = "입력된 메모를 비웠습니다.";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MiniCalendarMain window = new MiniCalendarMain();
					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MiniCalendarMain() {
		dao = MiniCalDaoImple.getinstance();
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame(title);
		frame.setBounds(100, 100, 892, 723);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setIconImage(icon.getImage());
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");// LookAndFeel Windows 스타일 적용
			SwingUtilities.updateComponentTreeUI(frame);
		} catch (Exception e) {
			bottomInfo.setText("ERROR : LookAndFeel setting failed");
		}

		// calOpPanel Start
		calOpPanel = new JPanel();
		calOpPanel.setBounds(0, 0, 751, 87);
		frame.getContentPane().add(calOpPanel);
		calOpPanel.setLayout(null);

		todayBut = new JButton("Today");
		todayBut.setToolTipText("Previous Today");
		todayBut.addActionListener(lForCalOpButtons);
		todayBut.setBounds(12, 10, 97, 23);
		calOpPanel.add(todayBut);

		todayLab = new JLabel(today.get(Calendar.MONTH) + 1 + "/" + today.get(Calendar.DAY_OF_MONTH) + "/"
				+ today.get(Calendar.YEAR));
		todayLab.setBounds(121, 14, 57, 15);
		calOpPanel.add(todayLab);

		lYearBut = new JButton("<<");
		lYearBut.setToolTipText("Previous Year");
		lYearBut.addActionListener(lForCalOpButtons);
		lYearBut.setFont(new Font("굴림", Font.BOLD, 12));
		lYearBut.setBounds(26, 54, 83, 23);
		calOpPanel.add(lYearBut);

		lMonBut = new JButton("<");
		lMonBut.setToolTipText("Previous Month");
		lMonBut.addActionListener(lForCalOpButtons);
		lMonBut.setFont(new Font("굴림", Font.BOLD, 12));
		lMonBut.setBounds(121, 54, 57, 23);
		calOpPanel.add(lMonBut);

		curMMYYYYLab = new JLabel("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
				+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
		curMMYYYYLab.setBounds(190, 58, 110, 19);
		calOpPanel.add(curMMYYYYLab);

		nMonBut = new JButton(">");
		nMonBut.setToolTipText("Next Month");
		nMonBut.addActionListener(lForCalOpButtons);
		nMonBut.setFont(new Font("굴림", Font.BOLD, 12));
		nMonBut.setBounds(312, 54, 57, 23);
		calOpPanel.add(nMonBut);

		nYearBut = new JButton(">>");
		nYearBut.setToolTipText("Next Year");
		nYearBut.addActionListener(lForCalOpButtons);
		nYearBut.setFont(new Font("굴림", Font.BOLD, 12));
		nYearBut.setBounds(385, 54, 83, 23);
		calOpPanel.add(nYearBut);

		// calPanel Start
		calPanel = new JPanel();
		calPanel.setBounds(12, 97, 475, 389);
		weekDaysName = new JButton[7];
		frame.getContentPane().add(calPanel);
		for (int i = 0; i < CAL_WIDTH; i++) {
			weekDaysName[i] = new JButton(WEEK_DAY_NAME[i]);
			weekDaysName[i].setBorderPainted(false);
			weekDaysName[i].setContentAreaFilled(false);
			weekDaysName[i].setForeground(Color.WHITE);

			if (i == 0) {
				weekDaysName[i].setBackground(new Color(200, 50, 50));

			} else if (i == 6) {
				weekDaysName[i].setBackground(new Color(50, 100, 200));

			} else {
				weekDaysName[i].setBackground(new Color(150, 150, 150));
			}
			weekDaysName[i].setOpaque(true);
			weekDaysName[i].setFocusPainted(false);
			calPanel.add(weekDaysName[i]);
		} // end for(CAL_WIDTH)

		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				dateButs[i][j] = new JButton();
				dateButs[i][j].setBorderPainted(false);
				dateButs[i][j].setContentAreaFilled(false);
				dateButs[i][j].setBackground(Color.WHITE);
				dateButs[i][j].setOpaque(true);
				dateButs[i][j].addActionListener(lForDateButs);
				calPanel.add(dateButs[i][j]);
			}

		} // end for (CAL_HEIGHT)

		calPanel.setLayout(new GridLayout(0, 7, 2, 2));
		calPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
		showCal();

		// infoPanel Start
		infoPanel = new JPanel();
		infoPanel.setBounds(753, 0, 111, 87);
		frame.getContentPane().add(infoPanel);
		infoPanel.setLayout(new BorderLayout());
		infoClock = new JLabel(" ", SwingConstants.RIGHT);
		infoClock.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		infoPanel.add(infoClock, BorderLayout.NORTH);
		selectedDate = new JLabel("<Html><font size=3>" + (today.get(Calendar.MONTH) + 1) + "/"
				+ today.get(Calendar.DAY_OF_MONTH) + "/" + today.get(Calendar.YEAR) + "&nbsp;(Today)</html>",
				SwingConstants.LEFT);
		selectedDate.setBounds(12, 19, 111, 15);

		// memoPanel Start
		memoPanel = new JPanel();
		memoPanel.setBorder(BorderFactory.createTitledBorder(null, "Memo", 0, 0, null, Color.red));

		memoPanel.setBounds(495, 97, 369, 389);
		frame.getContentPane().add(memoPanel);
		memoPanel.setLayout(null);
		memoArea = new JTextArea();
		memoArea.setLineWrap(true);
		memoArea.setWrapStyleWord(true);

		JScrollPane memoAreaSP = new JScrollPane(memoArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
//		readMemo();

		JPanel memoSubPanel = new JPanel();
		memoSubPanel.setBounds(12, 45, 345, 293);
		memoPanel.add(memoSubPanel);
		memoSubPanel.setLayout(new BorderLayout(0, 0));
		memoSubPanel.add(memoAreaSP, BorderLayout.CENTER);
		memoAreaSP.setViewportView(memoArea);
		readMemo();

		JButton saveBut = new JButton("Save");
		saveBut.addActionListener((e) -> {
			saveNewCalendar();
		});
		saveBut.setBounds(22, 348, 97, 23);
		memoPanel.add(saveBut);

		// delBut Start

		JButton delBut = new JButton("Delete");
		delBut.addActionListener((e) -> {
			deleteNewCalendar();
		});
		delBut.setBounds(131, 348, 97, 23);
		memoPanel.add(delBut);

		// clearBut Start
		JButton clearBut = new JButton("Clear");
		clearBut.addActionListener((e) -> {
			clearNewCalendar();
		});
		clearBut.setBounds(240, 348, 97, 23);
		memoPanel.add(clearBut);
		memoPanel.add(selectedDate);
		selectedDate.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));

		frameBottomPanel = new JPanel();
		frameBottomPanel.setBounds(0, 496, 886, 25);
		frame.getContentPane().add(frameBottomPanel);

		bottomInfo = new JLabel("Welcome to Memo Calendar!");
		bottomInfo.setFont(new Font("굴림", Font.BOLD, 15));
		frameBottomPanel.add(bottomInfo);

		allTextPanel = new JPanel();
		allTextPanel.setBounds(0, 531, 886, 153);
		frame.getContentPane().add(allTextPanel);
		allTextPanel.setLayout(null);

		allTextArea = new JTextArea();
		allTextArea.setBounds(0, 0, 886, 153);
		allTextPanel.add(allTextArea);
		frame.setVisible(true);
		focusToday();

		// Thread 작동(시계, bottomMsg 일정시간후 삭제)
		ThreadControl threadCnl = new ThreadControl();
		threadCnl.start();
	} // end initialize()

//	private void selectAllCalendar () {
//		List<MiniCalendar> list = dao.select();
//		StringBuilder builder = new StringBuilder();
//		for (MiniCalendar c : list) {
//			builder.append(c.toString()).append("\n");
//		} // end for
//		
//		allTextArea.setText(builder.toString());
//		
//	} // end selectAllCalendar()

	private void saveNewCalendar() {
		try {
			File f = new File("MemoData");
			if (!f.isDirectory()) {
				f.mkdir();
			}
			String memo = memoArea.getText();
			if (memo.length() > 0) {

				BufferedWriter out = new BufferedWriter(new FileWriter("MemoData/" + calYear
						+ ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1) + calDayOfMon + ".txt"));
				String str = memoArea.getText();
				out.write(str);
				out.close();
				bottomInfo.setText(calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
						+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt" + SaveButMsg1);

				String text = memoArea.getText();

				MiniCalendar miniCal = new MiniCalendar(sqlDate, text);
				MiniCalendar result = dao.select(sqlDate);

				if (result == null) {
					// 널이면 insert

					int result2 = dao.save(miniCal);

					if (result2 == 1) {
						allTextArea.setText("성공");
					} else {
						allTextArea.setText("실패");
					}
				} else {
					// 아니면 update 메소드도 만들어야함.
					
					int result3 = dao.update(miniCal);
					
					
				}

				allTextArea.setText(result + "입력안료.");

			} else {
				bottomInfo.setText(SaveButMsg2);
			}
		} catch (IOException e1) {
			bottomInfo.setText(SaveButMsg3);
		}
		showCal();

	}// end saveNewCalendar

	private void clearNewCalendar() {
		memoArea.setText(null);
		bottomInfo.setText(ClrButMsg1);
		
		
	}

	private void deleteNewCalendar() {
		File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
				+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt");
		if (f.exists()) {
			f.delete();
			showCal();
			bottomInfo.setText(DelButMsg1);
		} else {
			bottomInfo.setText(DelButMsg2);
		}

		int result = dao.delete(sqlDate);
		allTextArea.setText(result + "행이 삭제되었습니다.");
		memoArea.setText("");
	}// end deleteNewCalendar()

	private void focusToday() {
		if (today.get(Calendar.DAY_OF_WEEK) == 1) {
			dateButs[today.get(Calendar.WEEK_OF_MONTH)][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
		} else {
			dateButs[today.get(Calendar.WEEK_OF_MONTH) - 1][today.get(Calendar.DAY_OF_WEEK) - 1].requestFocusInWindow();
		}
	} // end focusToday()

	private void readMemo() {
		try {
			File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
					+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt");
			if (f.exists()) {
				BufferedReader in = new BufferedReader(
						new FileReader("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
								+ (calDayOfMon < 10 ? "0" : "") + calDayOfMon + ".txt"));
				String memoAreaText = new String();
				while (true) {
					String tempStr = in.readLine();
					if (tempStr == null) {
						break;

					}
					memoAreaText = memoAreaText + tempStr + System.getProperty("line.separator");
				}
				memoArea.setText(memoAreaText);
				in.close();
			} else {
				memoArea.setText("");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	} // end readMemo()

	private void showCal() {
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				String fontColor = "black";
				if (j == 0) {
					fontColor = "red";
				} else if (j == 6) {
					fontColor = "blue";
				}
				File f = new File("MemoData/" + calYear + ((calMonth + 1) < 10 ? "0" : "") + (calMonth + 1)
						+ (calDates[i][j] < 10 ? "0" : "") + calDates[i][j] + ".txt");
				if (f.exists()) {
					dateButs[i][j]
							.setText("<html><b><font color=" + fontColor + ">" + calDates[i][j] + "</font></b></html>");
				} else {
					dateButs[i][j].setText("<html><font color=" + fontColor + ">" + calDates[i][j] + "</font></html>");
				} // end if

				JLabel todayMark = new JLabel("<html><font color=green>*</html>");
				dateButs[i][j].removeAll();
				if (calMonth == today.get(Calendar.MONTH) && calYear == today.get(Calendar.YEAR)
						&& calDates[i][j] == today.get(Calendar.DAY_OF_MONTH)) {
					dateButs[i][j].add(todayMark);
					dateButs[i][j].setToolTipText("Today");
				} // end if

				if (calDates[i][j] == 0) {
					dateButs[i][j].setVisible(false);
				} else {
					dateButs[i][j].setVisible(true);
				} // if end

			} // end for (j)
		} // end for (i)
	} // end showCal()

	private class ListenForCalOpButtons implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == todayBut) {
				setToday();
				lForDateButs.actionPerformed(e);
				focusToday();

			} else if (e.getSource() == lYearBut) {
				moveMonth(-12);
			} else if (e.getSource() == lMonBut) {
				moveMonth(-1);
			} else if (e.getSource() == nMonBut) {
				moveMonth(1);
			}else if (e.getSource() == nYearBut){
				moveMonth(12);
			}
			curMMYYYYLab.setText("<html><table width=100><tr><th><font size=5>" + ((calMonth + 1) < 10 ? "&nbsp;" : "")
					+ (calMonth + 1) + " / " + calYear + "</th></tr></table></html>");
			showCal();
		}
	} // end class ListenForCalOpButtons

	private class listenForDateButs implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int k = 0, l = 0;
			for (int i = 0; i < CAL_HEIGHT; i++) {
				for (int j = 0; j < CAL_WIDTH; j++) {
					if (e.getSource() == dateButs[i][j]) {
						k = i;
						l = j;
					} // end if
				} // end for(j)
			} // end for(i)
			if (!(k == 0 && l == 0)) {
				calDayOfMon = calDates[k][l]; // today버튼을 눌렀을때도 이 actionPerformed함수가 실행되기 때문에 넣은 부분
			}
			cal = new GregorianCalendar(calYear, calMonth, calDayOfMon);
			sqlDate = convertCalToDate(cal);

			String dDayString = new String();
			int dDay = ((int) ((cal.getTimeInMillis() - today.getTimeInMillis()) / 100 / 60 / 60 / 24));

			if (dDay == 0 && (cal.get(Calendar.YEAR) == today.get(Calendar.YEAR))
					&& (cal.get(Calendar.MONTH) == today.get(Calendar.MONTH))
					&& (cal.get(Calendar.DAY_OF_MONTH) == today.getActualMaximum(Calendar.DAY_OF_MONTH))) {
				dDayString = "Today";
			} else if (dDay >= 0) {
				dDayString = "D-" + (dDay + 1);
			} else if (dDay < 0) {
				dDayString = "D+" + (dDay * (-1));
			}

			selectedDate.setText("<Html><font size=3>" + (calMonth + 1) + "/" + calDayOfMon + "/" + calYear + "&nbsp;("
					+ dDayString + ")</html>");

			readMemo();
		} // end actionPerformed()

	} // end listenForDateButs

	private class ThreadControl extends Thread {
		public void run() {
			boolean msgCntFlag = false;
			int num = 0;
			String curStr = new String();
			while (true) {
				try {
					today = Calendar.getInstance();
					String amPm = (today.get(Calendar.AM_PM) == 0 ? "AM" : "PM");
					String hour;
					if (today.get(Calendar.HOUR) == 0) {
						hour = "12";
					} else if (today.get(Calendar.HOUR) == 12) {
						hour = "0";
					} else {
						hour = (today.get(Calendar.HOUR) < 10 ? " " : "") + today.get(Calendar.HOUR);
					}
					String min = (today.get(Calendar.MINUTE) < 10 ? "0" : "") + today.get(Calendar.MINUTE);
					String sec = (today.get(Calendar.SECOND) < 10 ? "0" : "") + today.get(Calendar.SECOND);
					infoClock.setText(amPm + " " + hour + ":" + min + ":" + sec);

					sleep(1000);
					String infoStr = bottomInfo.getText();

					if (infoStr != " " && (msgCntFlag == false || curStr != infoStr)) {
						num = 5;
						msgCntFlag = true;
						curStr = infoStr;

					} else if (infoStr != " " && msgCntFlag == true) {
						if (num > 0) {
							num--;
						} else {
							msgCntFlag = false;
							bottomInfo.setText(" ");
						}
					}
				} catch (InterruptedException e) {
					System.out.println("Thread:Error");
					e.printStackTrace();
				}

			} // end while
		} // end run()
	} // end TreadControl
}
