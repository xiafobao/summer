package com.xia.demo.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketThread extends Thread {
	private Socket socketIn;
	private InputStream isIn;
	private OutputStream osIn;
	//
	private Socket socketOut;
	private InputStream isOut;
	private OutputStream osOut;
 
	public SocketThread(Socket socket) {
		this.socketIn = socket;
	}
 
	private byte[] buffer = new byte[4096];
	private static final byte[] VER = { 0x5, 0x0 };
	private static final byte[] CONNECT_OK = { 0x5, 0x0, 0x0, 0x1, 0, 0, 0, 0, 0, 0 };
 
	@Override
	public void run() {
		try {
			System.out.println("\n\n接收到一个客户端连接===>" + socketIn.getInetAddress() + ":" + socketIn.getPort());
			isIn = socketIn.getInputStream();
			osIn = socketIn.getOutputStream();
			int len = isIn.read(buffer);
			System.out.println("< " + bytesToHexString(buffer, 0, len));
			osIn.write(VER);
			osIn.flush();
			System.out.println("> " + bytesToHexString(VER, 0, VER.length));
			len = isIn.read(buffer);
			System.out.println("< " + bytesToHexString(buffer, 0, len));
			// 查找主机和端口
			String host = findHost(buffer);
			int port = findPort(buffer);
			System.out.println("host=" + host + ",port=" + port);
			socketOut = new Socket(host, port);
			isOut = socketOut.getInputStream();
			osOut = socketOut.getOutputStream();
			System.arraycopy(buffer, 4, CONNECT_OK, 4, 6);
			osIn.write(CONNECT_OK);
			osIn.flush();
			System.out.println("> " + bytesToHexString(CONNECT_OK, 0, CONNECT_OK.length));
			SocketThreadOutput out = new SocketThreadOutput(isIn, osOut);
			out.start();
			SocketThreadInput in = new SocketThreadInput(isOut, osIn);
			in.start();
			out.join();
			in.join();
		} catch (Exception e) {
			System.out.println("a client leave");
		} finally {
			try {
				if (socketIn != null) {
					socketIn.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("socket close");
	}
 
	/*public static String findHost(byte[] bArray, int begin, int end) {
		StringBuffer sb = new StringBuffer();
		for (int i = begin; i <= end; i++) {
			sb.append(Integer.toString(0xFF & bArray[i]));
			sb.append(".");
		}
		sb.deleteCharAt(sb.length() - 1);
		return sb.toString();
	}*/

	/**
	 * 经过上一步的握手 client将发送如下信息到
	 * 属性   VER  CMD RSV  ATYP   DST.ADDR   DST.PORT
	 * 字节数  1	   1  1	    1      variable	   2
	 * 备注
	 * @param bArray 收到内容
	 * @return
	 */
	public static String findHost(byte[] bArray) {
		//获取atyp值，1=ip4 3=域名 4=ip6
		int atyp = Integer.valueOf(Integer.toHexString(0xFF & bArray[3]));
		StringBuilder sb = new StringBuilder();
		if(atyp == 3){
			//说明是网址地址
			//网址长度
			int size = bArray[4];
			for(int i=5;i<(5+size);i++){
				sb.append((char)bArray[i]);
			}
		}else if(atyp == 1){
			for (int i = 4; i <= 7; i++) {
				Integer integer = Integer.valueOf(Integer.toString(0xFF & bArray[i]));
				if(integer < 0){
					integer = 256 + integer;
				}
				sb.append(integer);
				sb.append(".");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}
 
	/*public static int findPort(byte[] bArray, int begin, int end) {
		int port = 0;
		for (int i = begin; i <= end; i++) {
			port <<= 16;
			port += bArray[i];
		}
		return port;
	}*/

	//解析端口
	public int findPort(byte[] bArray){
		//获取atyp值，1=ip4 3=域名 4=ip6
		int atyp = Integer.valueOf(Integer.toHexString(0xFF & bArray[3]));
		int portBegin;
		if(atyp == 1){
			portBegin = 4+4;
		}else if(atyp == 3){
			int size = bArray[4];
			portBegin = 4+size;
		}else{
			portBegin = 4+16;
		}
		int thod = bArray[portBegin+1];
		int port=bArray[portBegin+2];
		if(port>0){
			return 256*thod+port;
		}else{
			return 256*thod +(256+port);
		}
	}
	// 4A 7D EB 69
	// 74 125 235 105
	public static final String bytesToHexString(byte[] bArray, int begin, int end) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = begin; i < end; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2) {
				sb.append(0);
			}
			sb.append(sTemp.toUpperCase());
			sb.append(" ");
		}
		return sb.toString();
	}
}