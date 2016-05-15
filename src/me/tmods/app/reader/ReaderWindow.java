package me.tmods.app.reader;

import java.awt.EventQueue;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.tmods.app.MidiReader;

public class ReaderWindow {

	private static JFrame frmMidiReader;
	private static JTextField txtDatei;
	private static JTextField txtBpm;
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void open() {
		initialize();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmMidiReader.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private static void initialize() {
		frmMidiReader = new JFrame();
		frmMidiReader.setResizable(false);
		frmMidiReader.setTitle("Midi Reader");
		frmMidiReader.setBounds(100, 100, 324, 212);
		frmMidiReader.getContentPane().setLayout(null);
		
		Label label = new Label("Tempo:");
		label.setBounds(10, 11, 46, 22);
		frmMidiReader.getContentPane().add(label);
		
		txtDatei = new JTextField();
		txtDatei.setBounds(44, 104, 86, 20);
		frmMidiReader.getContentPane().add(txtDatei);
		txtDatei.setColumns(10);
		
		JLabel lblDatei = new JLabel("Datei:");
		lblDatei.setBounds(10, 107, 46, 14);
		frmMidiReader.getContentPane().add(lblDatei);
		
		txtBpm = new JTextField();
		txtBpm.setBounds(217, 104, 95, 20);
		frmMidiReader.getContentPane().add(txtBpm);
		txtBpm.setColumns(10);
		
		JLabel lblBpm = new JLabel("BPM:");
		lblBpm.setBounds(186, 107, 46, 14);
		frmMidiReader.getContentPane().add(lblBpm);
		
		JLabel label_1 = new JLabel("0");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(10, 45, 46, 14);
		frmMidiReader.getContentPane().add(label_1);
		
		JSlider slider = new JSlider();
		slider.setMaximum(100);
		slider.setMinimum(-100);
		slider.setMajorTickSpacing(0);
		slider.setSnapToTicks(false);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				label_1.setText("" + slider.getValue());
				label_1.repaint();
			}
		});
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("Additiv");
		tglbtnNewToggleButton.setBounds(62, 11, 121, 23);
		frmMidiReader.getContentPane().add(tglbtnNewToggleButton);
		slider.setValue(0);
		slider.setBounds(66, 45, 200, 26);
		frmMidiReader.getContentPane().add(slider);
		
		JButton btnNewButton = new JButton("lesen");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					MidiReader.read(txtDatei.getText(),Integer.valueOf(txtBpm.getText()),slider.getValue(),tglbtnNewToggleButton.isSelected());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(13, 141, 299, 34);
		frmMidiReader.getContentPane().add(btnNewButton);
	}
	public static void close() {
		frmMidiReader.setVisible(false);
	}
}
