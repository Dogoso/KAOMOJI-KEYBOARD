package com.doglab.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import com.doglab.graphics.ImageCatcher;

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
		main = new Main();
	}
	
	private void initJFrame() {
		frame = new JFrame("Kaomojis Keyboard");
		frame.setSize(new Dimension(400, 400));
		frame.setType(Type.NORMAL);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.dispose();
		frame.add(this);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		
		JPanel pane = new JPanel();
		pane.setBackground(Color.black);
		pane.setPreferredSize(new Dimension(400, 70));
		JLabel label = new JLabel("- Kaomojis -");
		label.setBounds(100, 20, 190, 50);
		label.setFont(new Font("arial", Font.PLAIN, 30));
		label.setForeground(Color.white);
		frame.add(label);
		
		JScrollPane scroll = new JScrollPane(pane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.getViewport().add(pane);
		
		JButton add = new JButton("+");
		add.setBounds(295, 10, 50, 50);
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
		JPanel hugPanel = new JPanel();
		JButton hug = new JButton("");
		hug.setIcon(new ImageIcon(img2));
		hug.setBounds(70, 100, 35, 35);
		frame.add(hug);
		
		JPanel panelCat = new JPanel();
		JButton cat = new JButton("");
		cat.setIcon(new ImageIcon(imgC));
		cat.setBounds(115, 100, 35, 35);
		frame.add(cat);
		
		JPanel panelLove = new JPanel();
		JButton love = new JButton("");
		love.setIcon(new ImageIcon(imgL));
		love.setBounds(160, 100, 35, 35);
		frame.add(love);
		
		JPanel panelHappy = new JPanel();
		JButton happy = new JButton("");
		happy.setIcon(new ImageIcon(imgH));
		happy.setBounds(205, 100, 35, 35);
		frame.add(happy);
		
		JPanel sadPanel = new JPanel();
		JButton sad = new JButton("");
		sad.setIcon(new ImageIcon(imgS));
		sad.setBounds(250, 100, 35, 35);
		sad.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(JPanel b : panels) {
					if(!b.equals(sadPanel)) {
						System.out.println("cu");
						pane.setVisible(false);
						scroll.setVisible(false);
					}
				}
			}
			
		});
		frame.add(sad);
		
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
						kaomoji.addKaomoji(newKaomoji, pane);
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
		frame.add(add);
		
		String[] kaomojis = {"(●´⌓`●)", "(≧▽≦)", "(´ . .̫ . `)", "(๑╹◡╹๑)ﾉ", "⁄(⁄ ⁄•⁄-⁄•⁄ ⁄)⁄", 
				"ʕ ꈍᴥꈍʔ", "(っ˘з(˘⌣˘ )", "(人 •͈ᴗ•͈)", "╰(⸝⸝⸝´꒳`⸝⸝⸝)╯", "⁽⁽ଘ( ˊᵕˋ )ଓ⁾⁾", "(눈‸눈)", "(ب_ب)", 
				"｡:ﾟ(;´∩`;)ﾟ:｡", "(｡•́︿•̀｡)", "♡(˃͈ દ ˂͈ ༶ )"};
		kaomoji.createButtons(pane, kaomojis);
		frame.add(scroll);

		panels.add(pane);
		
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
