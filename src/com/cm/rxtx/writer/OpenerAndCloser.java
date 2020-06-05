package com.cm.rxtx.writer;

import gnu.io.*;

import java.util.Enumeration;

/**
 * 该类实现3个功能
 * 1.列举出本地所有的串口;
 * 2.打开所有串口(但是未向串口中写数据);
 * 3.关闭打开的串口。
 */
public class OpenerAndCloser {
	public static void main(String[] args){
		//1.获取本地所有的端口并输出其名称：
		//1.1.用于标识端口的变量
		CommPortIdentifier portIdentifier = null;
		
		//1.2.记录所有端口的变量
		Enumeration<?> allPorts 
			= CommPortIdentifier.getPortIdentifiers();
		
		//1.3.输出每一个端口
		while(allPorts.hasMoreElements()){
			portIdentifier 
				= (CommPortIdentifier) allPorts.nextElement();
			System.out.println("串口：" + portIdentifier.getName());
		}
		
		//2.打开COM11和COM21端口
		//2.1.获取两个端口
		CommPortIdentifier com11 = null;
		CommPortIdentifier com21 = null;
		try {
			com11 = CommPortIdentifier.getPortIdentifier("COM11");
			com21 = CommPortIdentifier.getPortIdentifier("COM21");
		} catch (NoSuchPortException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//2.2.打开两个端口，但是什么都没干
		SerialPort serialCom11 = null;
		SerialPort serialCom21 = null;
		try {
			//open方法的第1个参数表示串口被打开后的所有者名称，
			//第2个参数表示如果串口被占用的时候本程序的最长等待时间，以毫秒为单位。
			serialCom11 
				= (SerialPort)com11.open("OpenerAndCloser", 1000);
			serialCom21 
				= (SerialPort)com21.open("OpenerAndCloser", 1000);
		} catch (PortInUseException e) {
			//要打开的端口被占用时抛出该异常
			e.printStackTrace();
		}
		
		//2.3.设置两个端口的参数
		try {
			serialCom11.setSerialPortParams(
					9600, //波特率
					SerialPort.DATABITS_8,//数据位数
					SerialPort.STOPBITS_1,//停止位
					SerialPort.PARITY_NONE//奇偶位
			);
			
			serialCom21.setSerialPortParams(
					9600, //波特率
					SerialPort.DATABITS_8,//数据位数
					SerialPort.STOPBITS_1,//停止位
					SerialPort.PARITY_NONE//奇偶位
			);
		} catch (UnsupportedCommOperationException e) {
			e.printStackTrace();
		}
		
		//3.关闭COM11和COM21端口
		//关闭端口的方法在SerialPort类中
		serialCom11.close();
		serialCom21.close();
	}
}
