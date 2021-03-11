package com.doglab.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Kaomoji {

	private String[] kaomoji = {"(●´⌓`●)", "(≧▽≦)", "(´ . .̫ . `)", "(๑╹◡╹๑)ﾉ", "⁄(⁄ ⁄•⁄-⁄•⁄ ⁄)⁄", 
			"ʕ ꈍᴥꈍʔ", "(っ˘з(˘⌣˘ )", "(人 •͈ᴗ•͈)", "╰(⸝⸝⸝´꒳`⸝⸝⸝)╯", "⁽⁽ଘ( ˊᵕˋ )ଓ⁾⁾", "(눈‸눈)", "(ب_ب)", 
			"｡:ﾟ(;´∩`;)ﾟ:｡", "(｡•́︿•̀｡)", "♡(˃͈ દ ˂͈ ༶ )"};
	private int mult = 0;
	private int heightMult = 0;
	
	public void createButtons(JPanel frame, String[] kaomojis) {
		for(int i = 0; i < kaomojis.length; i++) {
			if(mult%3 == 0 && mult != 0) {
				mult = 0;
				heightMult++;
				frame.setPreferredSize(new Dimension(400, 70+105 * heightMult));
			}
			String current = kaomoji[i];
			JButton button = new JButton(current);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent a) {
					Kaomoji kaomoji = new Kaomoji();
					kaomoji.toClipBoard(button.getText());
				}
				
			});
			button.setBounds(25+(110*mult), 195+(60*heightMult), 100, 50);
			frame.add(button);
			frame.setLayout(null);
			mult++;
		}
	}
	
	private void toClipBoard(String kaomoji) {
		StringSelection stringSelection = new StringSelection(kaomoji);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
	
	public void addKaomoji(String kaomoji, JPanel frame) {
		JButton button = new JButton(kaomoji);
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				Kaomoji kaomoji = new Kaomoji();
				kaomoji.toClipBoard(button.getText());
			}
			
		});
		if(mult%5 == 0 && mult != 0) {
			mult = 0;
			heightMult++;
		}
		button.setBounds(25+(70*mult), 85+(80*heightMult), 60, 50);
		frame.add(button);
		mult++;
	}
	
}
