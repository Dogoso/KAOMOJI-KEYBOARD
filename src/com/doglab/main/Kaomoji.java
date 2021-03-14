package com.doglab.main;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Kaomoji {

	public String[] kaoLove = {"(っ˘з(˘⌣˘ )", "(｡・//ε//・｡)", "(ʃƪ＾3＾）", "(๑˙❥˙๑)"};
	public String[] kaoHug = {"( >'-')>", "╰(⸝⸝⸝´꒳`⸝⸝⸝)╯", "(⊃｡•́‿•̀｡)⊃", "⊂((・▽・))⊃", "⊂(´･◡･⊂ )∘˚˳° "};
	public String[] kaoAnimals = {"ʕ ꈍᴥꈍʔ", "(=｀ェ´=)", "(^._.^)ﾉ", "-ᄒᴥᄒ-", "ʕ´•ᴥ•`ʔ"};
	public String[] kaoCry = {"(´ . .̫ . `)", "｡:ﾟ(;´∩`;)ﾟ:｡", "(｡•́︿•̀｡)", "(ب_ب)", "(눈‸눈)", "(｡ﾉω＼｡)",
			"(｡ŏ﹏ŏ)", "(╥﹏╥)"};
	public String[] kaoNervous = {"(●´⌓`●)", "(• ▽ •;)", "(´-﹏-`；)", "(；^ω^）"}; 
	public String[] kaoSmile = {"(≧▽≦)", "(人 •͈ᴗ•͈)", "(๑╹◡╹๑)ﾉ", "( ꈍᴗꈍ)", "(ㆁωㆁ)"};
	public String[] kaoSurprise = {"⁄(⁄ ⁄•⁄-⁄•⁄ ⁄)⁄", "(((;ꏿ_ꏿ;)))", "(´⊙ω⊙`)！", "⋋✿ ⁰ o ⁰ ✿⋌"};
	public String[] kaoDance = {"⁽⁽ଘ( ˊᵕˋ )ଓ⁾⁾", "\\(๑╹◡╹๑)ﾉ♬", "♪ヽ(･ˇ∀ˇ･ゞ)", "ヾ(･ω･*)ﾉ"};
	
	private MostUsedKaomojis mUK;
	
	private int heightMult = 0;
	private int lastX = 5;
	
	public Kaomoji() {
		mUK = new MostUsedKaomojis();
	}
	
	public void createButtons(JPanel frame3, JPanel frame2, JPanel frame, String[] kaomojis) {
		heightMult = 0;
		lastX = 5;
		
		int size = frame.getComponents().length;
		if(size > 0) {
			Component[] c = frame.getComponents();
			lastX = c[c.length-1].getX()+c[c.length-1].getWidth()+10;
			int checkHeightMult = 10;
			for(int i = 0 ; i < c.length; i++) {
				int width = c[i].getWidth();
				if(checkHeightMult+width+45>370) {
					checkHeightMult = 5;
					heightMult++;
				}
				checkHeightMult+=10+width;
			}
			
		}

		for(int i = 0; i < kaomojis.length; i++) {
			String current = "";
			try {
				current = new String(kaomojis[i].getBytes(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			JButton button = new JButton(current);
			button.setFont(new Font("NotoSans-Black", Font.PLAIN, button.getFont().getSize()));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent a) {
					toClipBoard(button);
				}
			});
			int width = (int)(40+(current.length()*6.5));
			if(lastX+width+45>370) {
				lastX = 5;
				heightMult++;
				if(frame.isVisible()) {
					frame3.setBounds(frame3.getX(), frame3.getY(), 400, 85 * heightMult);
					frame3.setPreferredSize(new Dimension(400, 65 * heightMult));
					frame2.setBounds(frame2.getX(), frame2.getY(), 400, 85 * heightMult);
					frame.setBounds(frame.getX(), frame.getY(), 400, 85 * heightMult);
				}else {
					frame.setBounds(frame.getX(), frame.getY(), 400, 85 * heightMult);
				}
			}
			button.setBounds(lastX, 0+(40*heightMult), width, 30);
			mUK.allButtons.add(button);
			mUK.cont.add(0);
			lastX+=10+width;
			frame.add(button);	
		}
	}
	
	private void toClipBoard(JButton current) {
		StringSelection stringSelection = new StringSelection(current.getText());
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		
		int index = 0;
		int usedTimes = 0;
		for(int i = 0; i < this.mUK.allButtons.size(); i++) {
			JButton b = mUK.allButtons.get(i);
			if(b.equals(current)) {
				this.mUK.cont.set(i, this.mUK.cont.get(i)+1);
				index = i;
				usedTimes = this.mUK.cont.get(i);
			}
		}
		
		mainLoop:
		for(int i = 0; i < this.mUK.biggersIndex.length; i++) {
			if(this.mUK.biggersIndex[i] == -1) {
				for(int ii = 0; ii < this.mUK.biggersIndex.length; ii++) {
					if(this.mUK.biggersIndex[ii] == index) {
						break mainLoop;
					}
				}
				this.mUK.biggersIndex[i] = index;
				break;
			}else {
				if(mUK.cont.get(mUK.biggersIndex[i]) < usedTimes) {
					for(int ii = 0; ii < this.mUK.biggersIndex.length; ii++) {
						if(this.mUK.biggersIndex[ii] == index) {
							break mainLoop;
						}
					}
					this.mUK.biggersIndex[i] = index;
					break;
				}
			}
		}

		mUK.mostUsed.clear();
		for(int i : this.mUK.biggersIndex) {
			if(i == -1) {
				break;
			}
			this.mUK.mostUsed.add(this.mUK.allButtons.get(i));
		}

		Main.main.hugPanel.removeAll();
		
		String[] s = new String[1];
		for(int i = 0; i < mUK.mostUsed.size(); i++) {
			s[0] = mUK.mostUsed.get(i).getText();
			this.createButtons(Main.main.panelAbsolute, Main.main.pane, Main.main.hugPanel, s);
		}
		
	}
}
