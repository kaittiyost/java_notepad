package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.accessibility.Accessible;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import gui.main_panel;

public class RunGui extends JComponent implements Accessible {
	static JTextArea area;

	public static void main(String[] args) {

		JFrame frame = new JFrame();
		main_panel pane = new main_panel();

		JMenuBar menubar = new JMenuBar();

//		menubar.setBackground(Color.DARK_GRAY);
//		menubar.setForeground(Color.WHITE);
		JMenu menuFile = new JMenu("file");
		JMenu menuEdit = new JMenu("edit");
		JMenu menuFormat = new JMenu("format");

		JMenuItem item_new = new JMenuItem("New File");
		JMenuItem item_open = new JMenuItem("Open File..");
		JMenuItem item_save = new JMenuItem("Save File");

		menuFile.add(item_new);
		menuFile.add(item_open);
		menuFile.add(item_save);

		menubar.add(menuFile);
		menubar.add(menuEdit);
		menubar.add(menuFormat);

		item_open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF Images", "jpg", "gif");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
					area.setText("");
					try {
						File fileToSave = chooser.getSelectedFile();
						BufferedReader br = new BufferedReader(new FileReader(fileToSave.getAbsolutePath()));
						String line;
						StringBuffer buf = new StringBuffer();
						while ((line = br.readLine()) != null) {
						//	System.out.println(":: " + line);
							buf.append(line + "\n");
							area.append(line + "\n");
						}
						frame.setTitle(fileToSave.getName());
					} catch (Exception ex) {

					}
				}

			}
		});

		item_save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser_save = new JFileChooser();
				fileChooser_save.setDialogTitle("Specify a file to save");

				int userSelection = fileChooser_save.showSaveDialog(fileChooser_save);

				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToSave = fileChooser_save.getSelectedFile();
					System.out.println("Save as file: " + fileToSave.getAbsolutePath());
					try {
						BufferedWriter bw = new BufferedWriter(new FileWriter(fileToSave.getAbsolutePath() + ".txt"));
						System.out.println("::" + area.getText());
						bw.write(area.getText());
						bw.flush();
						bw.close();

					} catch (Exception ex) {
						ex.getMessage();
						ex.printStackTrace();
					}
				}

			}
		});

		menuFormat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				formatFrame f = new formatFrame();
				f.setVisible(true);
			}
		});
		area = new JTextArea();
		area.setBackground(new Color(31, 31, 31));
		area.setBounds(5, 0, 900, 650);
		area.setFont(new Font("Helvetica Neue", Font.PLAIN, 20));
		area.setForeground(Color.WHITE);
		JScrollPane scrollpane = new JScrollPane(area);
		scrollpane.setBounds(0, 0, 900, 650);

		ImageIcon img = new ImageIcon("img/icon.png");

		frame.setSize(new Dimension(900, 650));
		frame.setLocationRelativeTo(null);
		frame.setTitle("notepad for mac");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(img.getImage());
		frame.setJMenuBar(menubar);
		frame.getContentPane().add(scrollpane);
		frame.setVisible(true);

		item_new.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.setVisible(true);
			}
		});
	}
}
