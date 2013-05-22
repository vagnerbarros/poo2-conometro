package frame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import engine.Time;

public class Windows extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private JButton start;
	private JButton stop;
	private JLabel label;
	private Time time;
	private JButton btnReset;
	private JCheckBox chckbxDecrescent;
	private JButton btnSave;
	private List<String> tempos;
	private JTextArea textArea;
	
	public Windows() {
		super("Time");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(100, 300));
		
		tempos = new ArrayList<String>();
		
		start = new JButton("Start");
		start.addActionListener(this);
		
		stop = new JButton("Stop");
		stop.addActionListener(this);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(this);
		
		btnSave = new JButton("Save");
		btnSave.addActionListener(this);
		
		chckbxDecrescent = new JCheckBox("Decrescent");
		chckbxDecrescent.addActionListener(this);
		
		label = new JLabel("00:00:00");
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel.add(label);
		panel.add(start);
		panel.add(stop);
		panel.add(btnReset);
		panel.add(btnSave);
		panel.add(chckbxDecrescent);
		
		Container container = getContentPane();
		container.add(panel);
		
		time = new Time(label, 0, 0);
		
		textArea = new JTextArea();
		panel.add(textArea);
		
		setVisible(true);
		pack();
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals(start.getActionCommand())) {
			time.start();
		} else if (command.equals(stop.getActionCommand())) {
			time.stop();
		} else if (command.equals(btnReset.getActionCommand())) {
			time.stop();
			time.reset();
		} else if (command.equals(chckbxDecrescent.getActionCommand())) {
			if (chckbxDecrescent.isSelected()) {
				time.setDecrescent(true);
			} else {
				time.setDecrescent(false);
			}
		} else if (command.equals(btnSave.getActionCommand())) {
			tempos.add(time.getTime());
			Collections.sort(tempos);
			textArea.setText("");
			for (String t : tempos) {
				//add no textArea
			}
		}
		repaint();
	}
}
