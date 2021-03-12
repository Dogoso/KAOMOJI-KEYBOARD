package com.doglab.main;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Kaomoji {

	public String[] kaomoji = {"(●´⌓`●)", "(≧▽≦)", "(´ . .̫ . `)", "(๑╹◡╹๑)ﾉ", "⁄(⁄ ⁄•⁄-⁄•⁄ ⁄)⁄", 
			"ʕ ꈍᴥꈍʔ", "(っ˘з(˘⌣˘ )", "(人 •͈ᴗ•͈)", "╰(⸝⸝⸝´꒳`⸝⸝⸝)╯", "⁽⁽ଘ( ˊᵕˋ )ଓ⁾⁾", "(눈‸눈)", "(ب_ب)", 
			"｡:ﾟ(;´∩`;)ﾟ:｡", "(｡•́︿•̀｡)", "♡(˃͈ દ ˂͈ ༶ )"};

	private int heightMult = 0;
	
	public void createButtons(JPanel frame, String[] kaomojis) {
		int lastX = 55;
		for(int i = 0; i < kaomojis.length; i++) {
			String current = kaomojis[i];
			JButton button = new JButton(current);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					Kaomoji kaomoji = new Kaomoji();
					kaomoji.toClipBoard(button.getText());
				}
			});
			int width = (int)(40+(current.length()*6.5));
			if(lastX+width>400-55) {
				lastX = 55;
				heightMult++;
				frame.setPreferredSize(new Dimension(400, 255 * heightMult));
			}
			button.setBounds(lastX, 0+(40*heightMult), width, 30);
			lastX+=10+width;
			frame.add(button);
		}
	}
	
	private void toClipBoard(String kaomoji) {
		StringSelection stringSelection = new StringSelection(kaomoji);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}
	
}
