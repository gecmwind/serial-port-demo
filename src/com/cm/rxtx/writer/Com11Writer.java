package com.cm.rxtx.writer;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Com11Writer类的功能是向COM11串口发送字符串“Hello World!”
 */
public class Com11Writer {

    private static String postName = "COM11";
    public static void main(String[] args) {
        //1.定义变量
        CommPortIdentifier com11 = null;//用于记录本地串口
        SerialPort serialCom11 = null;//用于标识打开的串口

        try {
            //2.获取COM11口
            com11 = CommPortIdentifier.getPortIdentifier(postName);
            //3.打开COM11
            //4.往串口写数据（使用串口对应的输出流对象）
            //4.1.获取串口的输出流对象
            //4.2.通过串口的输出流向串口写数据“Hello World!”：
            //使用输出流往串口写数据的时候必须将数据转换为byte数组格式或int格式，
            //当另一个串口接收到数据之后再根据双方约定的规则，对数据进行解码。
//			outputStream.write(new byte[]{'H','e','l','l','o',
//					' ','W','o','r','l','d','!'});
            serialCom11 = (SerialPort) com11.open("Com11Writer", 0);
            OutputStream outputStream = serialCom11.getOutputStream();
            outputStream.write("this is test data".getBytes("utf-8"));
            outputStream.flush();
            //4.3.关闭输出流
            outputStream.close();
            //5.关闭串口
            serialCom11.close();
        } catch (IOException e) {
            //如果获取输出流失败，则抛出该异常
            e.printStackTrace();
        } catch (NoSuchPortException e) {
            //如果获取输出流失败，则抛出该异常
            e.printStackTrace();
        } catch (PortInUseException e) {
            //如果获取输出流失败，则抛出该异常
            e.printStackTrace();
        } finally {
            if (serialCom11 != null) {
                serialCom11.close();
            }
        }
    }
}
