package com.doglab.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.doglab.graphics.ImageCatcher;

import de.javasoft.plaf.synthetica.SyntheticaSimple2DLookAndFeel;



public class Main extends Canvas implements KeyListener{

	private static final long serialVersionUID = 1L;
	private static Main main;
	private JFrame frame;
	private ImageCatcher imageCatcher;
	private Kaomoji kaomoji;
	private ArrayList<JPanel> panels;
	
	public Main() {
		panels = new ArrayList<JPanel>();
		kaomoji = new Kaomoji();
		initJFrame();
		addKeyListener(this);
		imageCatcher = new ImageCatcher();
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new SyntheticaSimple2DLookAndFeel());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		main = new Main();
	}
	
	private void initJFrame() {
		frame = new JFrame("Kaomoji Keyboard");
		frame.setSize(new Dimension(400, 400));
		frame.setType(Type.NORMAL);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispose();
		frame.add(this);
		//frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		JPanel pane = new JPanel();
		pane.setPreferredSize(new Dimension(400, 400));
		pane.setLayout(null);
		JScrollPane scroll = new JScrollPane(pane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.getViewport().add(pane);
		
		Image img2 = null;
		Image imgH = null;
		Image imgS = null;
		Image imgC = null;
		Image imgL = null;
		try {
			img2 = ImageIO.read(getClass().getResource("/clock.png"));
			imgC = ImageIO.read(getClass().getResource("/cat.png"));
			imgL = ImageIO.read(getClass().getResource("/love.png"));
			imgS = ImageIO.read(getClass().getResource("/sad.png"));
			imgH = ImageIO.read(getClass().getResource("/happy.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		JPanel panelChanges = new JPanel();
		panelChanges.setBackground(Color.LIGHT_GRAY);
		panelChanges.setBounds(0, 0, 45, 400);
		panelChanges.setLayout(null);
		
		JPanel hugPanel = new JPanel();
		hugPanel.setBounds(0, 10, 400, 150);
		hugPanel.setLayout(null);
		JButton hug = new JButton("");
		hug.setIcon(new ImageIcon(img2));
		hug.setBounds(5, 10, 35, 35);
		hug.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(hugPanel);
			}
		});
		panelChanges.add(hug);
		
		JPanel panelCat = new JPanel();
		
		panelCat.setBounds(0, 10, 400, 150);
		panelCat.setLayout(null);
		JButton cat = new JButton("");
		cat.setIcon(new ImageIcon(imgC));
		cat.setBounds(5, 55, 35, 35);
		cat.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(panelCat);
			}
		});
		panelChanges.add(cat);
		
		JPanel panelLove = new JPanel();
	
		panelLove.setBounds(0, 10, 400, 150);
		panelLove.setLayout(null);
		JButton love = new JButton("");
		love.setIcon(new ImageIcon(imgL));
		love.setBounds(5, 100, 35, 35);
		love.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(panelLove);
			}
		});
		panelChanges.add(love);
		
		JPanel panelHappy = new JPanel();
		
		panelHappy.setLayout(null);
		panelHappy.setBounds(0, 10, 400, 150);
		JButton happy = new JButton("");
		happy.setIcon(new ImageIcon(imgH));
		happy.setBounds(5, 145, 35, 35);
		happy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(panelHappy);
			}
		});
		panelChanges.add(happy);
		
		JPanel sadPanel = new JPanel();
		sadPanel.setLayout(null);
		sadPanel.setBounds(0, 10, 400, 150);
		JButton sad = new JButton("");
		sad.setIcon(new ImageIcon(imgS));
		sad.setBounds(5, 190, 35, 35);
		sad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(sadPanel);
			}
		});
		panelChanges.add(sad);
		
		String[] s = {"╰(⸝⸝⸝´꒳`⸝⸝⸝)╯", "´ - `", "; _ ;", "rola"};
		kaomoji.createButtons(hugPanel, s);
		kaomoji.createButtons(hugPanel, s);
		kaomoji.createButtons(hugPanel, s);
		kaomoji.createButtons(hugPanel, s);
		kaomoji.createButtons(hugPanel, s);
		kaomoji.createButtons(hugPanel, s);
		kaomoji.createButtons(hugPanel, s);
		kaomoji.createButtons(hugPanel, s);
		//kaomoji.createButtons(hugPanel, s);
		//kaomoji.createButtons(sadPanel, s);
		//kaomoji.createButtons(panelLove, s);
		//kaomoji.createButtons(panelCat, s);
		//kaomoji.createButtons(panelHappy, s);
		
		hugPanel.setVisible(true);
		panelLove.setVisible(false);
		sadPanel.setVisible(false);
		panelHappy.setVisible(false);
		panelCat.setVisible(false);

		panels.add(pane);
		panels.add(hugPanel);
		panels.add(panelLove);
		panels.add(sadPanel);
		panels.add(panelHappy);
		panels.add(panelCat);
		JButton add = new JButton("+");
		add.setBounds(2, 235, 40, 35);
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame2;
				JTextField textField = new JTextField();
				textField.setBounds(80, 10, 100, 30);
				JLabel label2 = new JLabel("KAOMOJI: ");
				label2.setBounds(10, 10, 90, 30);
				JButton button2 = new JButton("add");
				button2.setBounds(200, 10, 60, 30);
				button2.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String newKaomoji = textField.getText();
						for(int i = 0; i < panels.size(); i++) {
							if(i == 0) {
								continue;
							}
							JPanel p = panels.get(i);
							String[] s = {newKaomoji};
							if(p.isVisible()) {
								kaomoji.createButtons(p, s);
							}
						}
					}
				});
				
				frame2 = new JFrame("Insert KAOMOJI");
				frame2.setSize(new Dimension(300, 100));
				frame2.setResizable(false);
				frame2.dispose();
				frame2.setLocationRelativeTo(null);
				frame2.add(textField);
				frame2.add(label2);
				frame2.add(button2);
				frame2.setLayout(null);
				frame2.setVisible(true);
			}
			
		});
		panelChanges.add(add);
		
		pane.add(hugPanel);
		pane.add(panelLove);
		pane.add(sadPanel);
		pane.add(panelHappy);
		pane.add(panelCat);
		frame.add(panelChanges);
		frame.add(scroll);
		Image img = null;
		try {
			img = ImageIO.read(getClass().getResource("/kaomoji icon.png"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		frame.setIconImage(img);
		frame.setVisible(true);
		requestFocus();
	}

	public void changePanel(JPanel current) {
		for(int i = 0; i < panels.size(); i++) {
			if(i == 0) {
				continue;
			}
			JPanel p = panels.get(i);
			if(!p.equals(current)) {
				p.setVisible(false);
			}
		}
		current.setVisible(true);
	}
	
	public static void selectLayer(JPanel pane, JScrollPane scroll, JPanel pane2, JScrollPane scroll2) {
		pane.setVisible(false);
		scroll.setVisible(false);
		pane2.setVisible(true);
		scroll2.setVisible(true);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
}
