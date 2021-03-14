package com.doglab.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;

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

public class Main extends Canvas{

	private static final long serialVersionUID = 1L;
	private JFrame frame;
	public static Main main;
	private ImageCatcher imageCatcher;
	private Kaomoji kaomoji;
	private ArrayList<JPanel> panels;
	private JPanel panelChanges;
	public JPanel panelAbsolute;
	private JScrollPane scroll;
	private JLabel show;
	public JPanel hugPanel;
	public JPanel pane;
	
	private double mX = 0;
	
	private Runnable r = new Runnable() {
		@Override
		public void run() {
			while(true) {
				try {
					mX = frame.getMousePosition().getX();
				}catch(NullPointerException e) {
					
				}
				if(show.isVisible()) {
					show.repaint();
					if(mX >= 23) {
						panelChanges.setVisible(false);
						scroll.getVerticalScrollBar().setVisible(true);
						show.setVisible(true);
					}else {
						panelChanges.setVisible(true);
						show.setVisible(false);
						scroll.getVerticalScrollBar().setVisible(false);
					}
				}else {
					panelChanges.repaint();
					if(mX >= 45) {
						panelChanges.setVisible(false);
						scroll.getVerticalScrollBar().setVisible(true);
						show.setVisible(true);
					}else {
						panelChanges.setVisible(true);
						show.setVisible(false);
						scroll.getVerticalScrollBar().setVisible(false);
					}
				}
			}
		}
	};
	
	public Main() {
		setPreferredSize(new Dimension(400, 400));
		panelChanges = new JPanel();
		panels = new ArrayList<JPanel>();
		kaomoji = new Kaomoji();
		imageCatcher = new ImageCatcher();
		initJFrame();
		new Thread(r).start();
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
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(this);
		pane = new JPanel();
		pane.setBounds(45, 0, 400, 400);
		pane.setPreferredSize(new Dimension(400, 400));
		pane.setLayout(null);
		
		panelAbsolute = new JPanel();
		panelAbsolute.setPreferredSize(new Dimension(355, 400));
		scroll = new JScrollPane(panelAbsolute, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		panelChanges.setBackground(Color.LIGHT_GRAY);
		panelChanges.setBounds(0, 0, 45, 420);
		panelChanges.setLayout(null);
		
		show = new JLabel();
		show.setBounds(0, -20, 45, 400);
		ImageIcon icon = new ImageIcon(imageCatcher.catchImage("/shower.png"));
		show.setIcon(icon);
		show.setVisible(false);
		frame.add(panelChanges);
		frame.add(show);

		hugPanel = new JPanel();
		hugPanel.setBounds(0, 10, 400, 150);
		hugPanel.setLayout(null);
		JButton hug = new JButton("");
		hug.setIcon(new ImageIcon(imageCatcher.catchImage("/clock.png")));
		hug.setBounds(7, 5, 30, 30);
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
		cat.setIcon(new ImageIcon(imageCatcher.catchImage("/cat.png")));
		cat.setBounds(7, 40, 30, 30);
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
		love.setIcon(new ImageIcon(imageCatcher.catchImage("/love.png")));
		love.setBounds(7, 75, 30, 30);
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
		happy.setIcon(new ImageIcon(imageCatcher.catchImage("/happy.png")));
		happy.setBounds(7, 110, 30, 30);
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
		sad.setIcon(new ImageIcon(imageCatcher.catchImage("/sad.png")));
		sad.setBounds(7, 145, 30, 30);
		sad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(sadPanel);
			}
		});
		panelChanges.add(sad);
		
		JPanel huggingPanel = new JPanel();
		huggingPanel.setLayout(null);
		huggingPanel.setBounds(0, 10, 400, 150);
		JButton hugging = new JButton("");
		hugging.setIcon(new ImageIcon(imageCatcher.catchImage("/hug.png")));
		hugging.setBounds(7, 180, 30, 30);
		hugging.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(huggingPanel);
			}
		});
		panelChanges.add(hugging);
		
		JPanel nervousPanel = new JPanel();
		nervousPanel.setLayout(null);
		nervousPanel.setBounds(0, 10, 400, 150);
		JButton nervous = new JButton("");
		nervous.setIcon(new ImageIcon(imageCatcher.catchImage("/nervous.png")));
		nervous.setBounds(7,215, 30, 30);
		nervous.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(nervousPanel);
			}
		});
		panelChanges.add(nervous);

		JPanel dancePanel = new JPanel();
		dancePanel.setLayout(null);
		dancePanel.setBounds(0, 10, 400, 150);
		JButton dance = new JButton("");
		dance.setIcon(new ImageIcon(imageCatcher.catchImage("/dance.png")));
		dance.setBounds(7, 250, 30, 30);
		dance.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(dancePanel);
			}
		});
		panelChanges.add(dance);
		
		JPanel surprisePanel = new JPanel();
		surprisePanel.setLayout(null);
		surprisePanel.setBounds(0, 10, 400, 150);
		JButton surprise = new JButton("");
		surprise.setIcon(new ImageIcon(imageCatcher.catchImage("/surprise.png")));
		surprise.setBounds(7, 285, 30, 30);
		surprise.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				changePanel(surprisePanel);
			}
		});
		panelChanges.add(surprise);
		
		kaomoji.createButtons(panelAbsolute, pane, panelCat, kaomoji.kaoAnimals);
		kaomoji.createButtons(panelAbsolute, pane, sadPanel, kaomoji.kaoCry);
		kaomoji.createButtons(panelAbsolute, pane, panelHappy, kaomoji.kaoSmile);
		kaomoji.createButtons(panelAbsolute, pane, panelLove, kaomoji.kaoLove);
		kaomoji.createButtons(panelAbsolute, pane, huggingPanel, kaomoji.kaoHug);
		kaomoji.createButtons(panelAbsolute, pane, nervousPanel, kaomoji.kaoNervous);
		kaomoji.createButtons(panelAbsolute, pane, dancePanel, kaomoji.kaoDance);
		kaomoji.createButtons(panelAbsolute, pane, surprisePanel, kaomoji.kaoSurprise);
		
		hugPanel.setVisible(false);
		panelLove.setVisible(false);
		sadPanel.setVisible(false);
		panelHappy.setVisible(false);
		panelCat.setVisible(false);
		huggingPanel.setVisible(false);
		nervousPanel.setVisible(false);
		dancePanel.setVisible(false);
		surprisePanel.setVisible(false);

		panels.add(pane);
		panels.add(hugPanel);
		panels.add(panelLove);
		panels.add(sadPanel);
		panels.add(panelHappy);
		panels.add(panelCat);
		panels.add(huggingPanel);
		panels.add(nervousPanel);
		panels.add(dancePanel);
		panels.add(surprisePanel);
		
		changePanel(panelLove);
		
		pane.add(hugPanel);
		pane.add(panelLove);
		pane.add(sadPanel);
		pane.add(panelHappy);
		pane.add(panelCat);
		pane.add(huggingPanel);
		pane.add(nervousPanel);
		pane.add(dancePanel);
		pane.add(surprisePanel);
		
		JButton add = new JButton("+");
		add.setBounds(2, 320, 40, 35);
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
								kaomoji.createButtons(panelAbsolute , pane, p, s);
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
		
		panelAbsolute.setLayout(null);
		panelAbsolute.setBounds(0, 0, 400, 400);
		
		panelAbsolute.add(pane);
		frame.add(scroll);
		
		Image img = imageCatcher.catchImage("/kaomoji icon.png");
		frame.setIconImage(img);
	
		frame.pack();
		frame.setVisible(true);
		requestFocus();
	}

	public void changePanel(JPanel current) {
		for(int i = 0; i < panels.size(); i++) {
			JPanel p = panels.get(i);
			if(i == 0) {
				p.setBounds(p.getX(), p.getY(), current.getWidth(), current.getHeight());
				continue;
			}
			if(!p.equals(current)) {
				p.setVisible(false);
			}
		}
		current.setVisible(true);
	}
}
