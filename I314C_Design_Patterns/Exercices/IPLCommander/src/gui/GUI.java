package gui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardOpenOption;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import core.Fichier;

public class GUI extends JFrame implements ActionListener {
	private Fichier racine;
	private String path="";
	private DefaultListModel<String> listModel;
	private JList<String> list;
	private JLabel pathLabel;

	public GUI(Fichier racine) throws IOException {
		this.racine=racine;
		setTitle("IPLCommander");
		setSize(800,600);
		
		JPanel main=new JPanel(new BorderLayout());
				
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		main.add(new JScrollPane(list), BorderLayout.CENTER);
		
		JPanel buttons=new JPanel(new FlowLayout());
		
		JButton but=new JButton("..");
		but.addActionListener(this);
		buttons.add(but);
		but=new JButton("Aller...");
		but.addActionListener(this);
		buttons.add(but);
		but=new JButton("Sauver...");
		but.addActionListener(this);
		buttons.add(but);
		but=new JButton("Charger...");
		but.addActionListener(this);
		buttons.add(but);
		but=new JButton("Effacer...");
		but.addActionListener(this);
		buttons.add(but);
		
		pathLabel = new JLabel();
		buttons.add(pathLabel);
		
		main.add(buttons,BorderLayout.SOUTH);
		
		setContentPane(main);
		
		loadPath();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void loadPath() throws IOException {
		listModel.clear();
		for(String f:racine.liste(path)) {
			listModel.addElement(f);
		}
		pathLabel.setText(path);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int idx;
		String npath;
		String item;
		JFileChooser fc;
		switch (((JButton)e.getSource()).getText()) {
		case "..":
			idx=path.lastIndexOf("/");
			if (idx<0) return;
			npath=path.substring(0, idx);
			try {
				racine.liste(npath);
				path=npath;
				loadPath();
			} catch (IOException exp) {}
			break;
		case "Aller...":
			idx=list.getSelectedIndex();
			if (idx<0) return;
			item=listModel.getElementAt(idx);
			if (!item.endsWith("/")) return;
			path=path+"/"+item.substring(0, item.length()-1);
			try {
				loadPath();
			} catch (IOException exp) {}
			break;
		case "Sauver...":
			idx=list.getSelectedIndex();
			if (idx<0) return;
			item=listModel.getElementAt(idx);
			if (item.endsWith("/")) return;
			npath=path+"/"+item;
			fc=new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.showSaveDialog(this);
			if (fc.getSelectedFile()==null) return;
			try {
				byte[] buffer=racine.charge(npath);
				Files.write(fc.getSelectedFile().toPath(), buffer, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
			} catch (IOException exp) {}
			break;
		case "Charger...":
			fc=new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
			fc.showOpenDialog(this);
			if (fc.getSelectedFile()==null) return;
			try {
				byte[] buffer=Files.readAllBytes(fc.getSelectedFile().toPath());
				racine.sauve(path+"/"+fc.getSelectedFile().getName(), buffer);
				loadPath();
			} catch (IOException exp) {}
			break;
		case "Effacer...":
			idx=list.getSelectedIndex();
			if (idx<0) return;
			item=listModel.getElementAt(idx);
			if (item.endsWith("/")) return;
			npath=path+"/"+item;
			try {
				racine.efface(npath);
				loadPath();
			} catch (IOException exp) {}
			break;
		}
		
	}
}
