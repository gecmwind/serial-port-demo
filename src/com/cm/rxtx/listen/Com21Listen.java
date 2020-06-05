package com.cm.rxtx.listen;

import gnu.io.CommPortIdentifier;

import java.util.Enumeration;

/**
 * @author cm.ge.
 */
public class Com21Listen {
    public static void main(String[] args) {
        //打开COM21 监听
        SerialBean serialBean = new SerialBean("COM21", 115200);
        serialBean.Initialize();
        //列举当前可用串口
//        listPortChoices();
    }
    public static void listPortChoices() {
        CommPortIdentifier portId;
        Enumeration en = CommPortIdentifier.getPortIdentifiers();
        // iterate through the ports.
        while (en.hasMoreElements()) {
            portId = (CommPortIdentifier) en.nextElement();
            System.out.println("当前可用串口：");
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(portId.getName());
            }
        }
    }
}
