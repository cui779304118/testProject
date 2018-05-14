package com.test.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.google.zxing.EncodeHintType;

import net.glxn.qrgen.core.AbstractQRCode;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;


public class QRTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String text = "www.baidu.com";
		String filePath = "C://Users//weicui004746//Desktop//baidu.png";
		System.out.println(getQRCodeBase64Str(text, filePath) ? "成功！" : "失败！" );
	}
	
	public static boolean generateQR(String text, String filePath){
		ByteArrayOutputStream qrStream = QRCode.from(text).withCharset("utf-8").to(ImageType.PNG).withSize(300, 300).stream();
		File file = null;
		OutputStream ous = null;
		try {
			file = new File(filePath);
			ous = new FileOutputStream(file);
			ous.write(qrStream.toByteArray());
			ous.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public static boolean getQRCodeBase64Str(String fileName, String filePath) {
		File file = null;
		OutputStream ous = null;
		try {
			file = new File(filePath);
			ous = new FileOutputStream(file);
			AbstractQRCode qRCode = QRCode.from(fileName).withHint(EncodeHintType.MARGIN, 0);
			byte[] qrCodeByteArray = qRCode.stream().toByteArray();
			ous.write(qrCodeByteArray);
			ous.close();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
		// 转换链接为二维码
	}

}
