package it.polimi.ingsw.cg23.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Timer;

/**
 * timer class
 */
public class TimerClass {
	private JLabel label;
	Timer countdownTimer;
	int timeRemaining;

	/**
	 * costructor
	 * @param passedLabel, the label
	 * @param time, the time to countdown
	 */
	public TimerClass(JLabel passedLabel, int time) {
		timeRemaining=time/1000;
		countdownTimer = new Timer(1000, new CountdownTimerListener());
		this.label = passedLabel;
		countdownTimer.start();
	}

	class CountdownTimerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (--timeRemaining > 0) {
				label.setText(String.valueOf(timeRemaining));
			} else {
				label.setText("Time's up!");
				countdownTimer.stop();
			}
		}
	}
}