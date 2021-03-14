package com.doglab.main;

import java.util.ArrayList;

import javax.swing.JButton;

public class MostUsedKaomojis {

	public ArrayList<JButton> mostUsed;
	public ArrayList<JButton> allButtons;
	public ArrayList<Integer> cont;
	public int[] biggersIndex;
	
	public MostUsedKaomojis() {
		mostUsed = new ArrayList<JButton>();
		allButtons = new ArrayList<JButton>();
		cont = new ArrayList<Integer>();
		biggersIndex = new int[10];
		for(int i = 0; i < biggersIndex.length; i++) {
			biggersIndex[i] = -1;
		}
	}
	
}
