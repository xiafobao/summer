package com.xia.demo.bio;

import java.io.InputStream;
import java.io.OutputStream;

public class SocketThreadInput extends Thread {
	private InputStream isOut;
	private OutputStream osIn;
 
	public SocketThreadInput(InputStream isOut, OutputStream osIn) {
		this.isOut = isOut;
		this.osIn = osIn;
	}
 
	private byte[] buffer = new byte[409600];
 
	@Override
	public void run() {
		try {
			int len;
			while ((len = isOut.read(buffer)) != -1) {
				if (len > 0) {
					System.out.println("响应内容===>"+new String(buffer, 0, len));
					osIn.write(buffer, 0, len);
					osIn.flush();
				}
			}
		} catch (Exception e) {
			System.out.println("SocketThreadInput leave");
		}
	}
}