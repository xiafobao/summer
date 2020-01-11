package com.xia.demo.bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

public class SocketThreadOutput extends Thread {
	private InputStream isIn;
	private OutputStream osOut;
 
	public SocketThreadOutput(InputStream isIn, OutputStream osOut) {
		this.isIn = isIn;
		this.osOut = osOut;
	}
 
	private byte[] buffer = new byte[409600];
 
	@Override
	public void run() {
		try {
			int len;
			while ((len = isIn.read(buffer)) != -1) {
				if (len > 0) {
					System.out.println("请求内容==>"+ URLDecoder.decode(new String(buffer, 0, len), StandardCharsets.UTF_8.name()));
					osOut.write(buffer, 0, len);
					osOut.flush();
				}
			}
		} catch (Exception e) {
			System.out.println("SocketThreadOutput leave");
		}
	}
}