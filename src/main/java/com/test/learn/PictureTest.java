package com.test.learn;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

public class PictureTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}
	
	public static BufferedImage readImage(String path){
		BufferedImage bImage = null;
		try {
			ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bImage;
	}
	
	
}
