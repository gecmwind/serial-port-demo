package com.cm.rxtx.listen;


import gnu.io.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

/**
 * @author cm.ge.
 */
public class SerialBean implements SerialPortEventListener {
    private String PortName;
    private static CommPortIdentifier portId;
    private static SerialPort serialPort;
    private static OutputStream out;
    private static InputStream in;

    public int baud;


    public SerialBean(String com, int baud) {
        this.PortName = com;
        this.baud = baud;
    }

    // 初始化串口，将输入流返回用于事件读取
    public void Initialize() {
        try {
            portId = CommPortIdentifier.getPortIdentifier(PortName);
            // 由对象打开串口，并为串口命名
            serialPort = (SerialPort) portId.open("JAVA_SERIAL", 1000);
            // 注册一个SerialPortEventListener事件来监听串口事件
            serialPort.addEventListener(this);
            // 数据可用则触发事件
            serialPort.notifyOnDataAvailable(true);
            // 打开输入输出流
            in = serialPort.getInputStream();
            out = serialPort.getOutputStream();
            // 设置串口参数，波特率115200(baud)，8位数据位，1位停止位，无奇偶校验
            serialPort.setSerialPortParams(baud, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

        } catch (PortInUseException e) {
            System.out.println("该串口正在使用中");
        } catch (NoSuchPortException e) {
            System.out.println("该串口当前不可使用");
        } catch (UnsupportedCommOperationException e) {
            System.out.println("错误的串口参数配置");
        } catch (TooManyListenersException e) {
            System.out.println("该串口已存在监听器");
        } catch (IOException e) {
            System.out.println("输入输出流打开失败");
        }
    }

    // 关闭串口
    public void ClosePort() {
        serialPort.close();
    }

    // 监听串口事件
    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI://Break interrupt,通讯中断
                System.out.println("Break interrupt");
                break;
            case SerialPortEvent.OE://Overrun error，溢位错误
                System.out.println("Overrun error");
                break;
            case SerialPortEvent.FE://Framing error，传帧错误
                System.out.println("Framing error");
                break;
            case SerialPortEvent.PE://Parity error，校验错误
                System.out.println("Parity error");
                break;
            case SerialPortEvent.CD://Carrier detect，载波检测
                System.out.println("Carrier detect");
                break;
            case SerialPortEvent.CTS://Clear to send，清除发送
                System.out.println("Clear to send");
                break;
            case SerialPortEvent.DSR:// Data set ready，数据设备就绪
                System.out.println("Data set ready");
                break;
            case SerialPortEvent.RI://Ring indicator，响铃指示
                System.out.println("Ring indicator");
                break;
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:// Output buffer is empty，输出缓冲区清空
                System.out.println("Output buffer is empty");
                break;
            case SerialPortEvent.DATA_AVAILABLE://Data available at the serial port，端口有可用数据。读到缓冲数组，输出到终端
                String r = "";
                int c;
                byte[] cache = new byte[1024];
                try {
                    if (in != null) {
                        int availableBytes = in.available();
                        while (availableBytes > 0) {
                            in.read(cache);
                            for(int i = 0;i < availableBytes; i++){
                                //解码并输出数据
                                Character d = new Character((char) cache[i]);
                                 r = r.concat(d.toString());
                            }
                            availableBytes = in.available();
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println(r);
                break;
        }
    }
}
