package com.doglab.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageCatcher {

	private Image bImage;
	
	public Image catchImage(String path) {
		try {
			bImage = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bImage;
	}
	
}
