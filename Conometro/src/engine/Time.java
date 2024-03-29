package engine;

import javax.swing.JLabel;

import util.Formato;

public class Time implements Runnable {

	private boolean isDecrescent;
	private boolean isStart;
	private boolean isReset;
	private int segundo;
	private int minuto;
	private int hora;
	private JLabel time;
	private Thread t;

	public Time(JLabel label, int hr, int min) {
		hora = hr;
		minuto = min;
		time = label;
		isDecrescent = false;
		t = null;
	}

	@Override
	public void run() {
		try {
			while (t.isAlive()) {
				while (isStart) {
					Thread.sleep(1000);

					if (isDecrescent) {
						decresce();
					} else {
						incresce();
					}
					if (isReset) {
						segundo = 0;
						minuto = 0;
						hora = 0;
						isReset = false;
					}
					String timerStr = Formato.formatTime(hora, minuto, segundo);

					time.setText(timerStr);
					time.revalidate();
				}
			}
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

	public void incresce() {
		segundo++;

		if (segundo > 59) {
			segundo = 0;
			minuto++;
		}

		if (minuto > 59) {
			minuto = 0;
			hora++;
		}

		if (hora == 24) {
			segundo = 0;
			minuto = 0;
			hora = 0;
		}

	}

	public void decresce() {
		segundo--;

		if (segundo < 0) {
			segundo = 59;
			minuto--;
		}

		if (minuto < 0) {
			minuto = 59;
			hora--;
		}

		if (hora < 0) {
			segundo = 59;
			minuto = 59;
			hora = 23;
		}

	}

	public void start() {
		isStart = true;
		if (t == null) {
			t = new Thread(this);
			t.start();
		}
	}

	public void stop() {
		isStart = false;
	}

	public void reset() {
		isReset = true;
	}

	public void setDecrescent(boolean isDecrescent) {
		this.isDecrescent = isDecrescent;
	}

	public String getTime() {
		return time.getText();
	}

	public void setTime(String time) {
		hora = Integer.parseInt(time.substring(0, 2));
		minuto = Integer.parseInt(time.substring(3, 5));
		segundo = Integer.parseInt(time.substring(6, 8));
		
		this.time.setText(time);
	}
	
}
