package gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.textModel;

public class main_panel extends JPanel {
	
	String text = "";
	public main_panel() {
		setSize(new Dimension(900, 650));
		setLayout(null);
		setBackground(Color.white);

		JTextArea area = new JTextArea();
		area.setBounds(5, 0, 900, 650);

		add(area);
		System.out.println(area.getText());
		
		textModel txt_model = new textModel();
		txt_model.setText(area.getText());
		

	}


}
