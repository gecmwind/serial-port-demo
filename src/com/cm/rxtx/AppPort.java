package com.cm.rxtx;


import com.cm.rxtx.listen.SerialBean;
import gnu.io.CommPortIdentifier;

import java.util.Enumeration;
import java.util.Scanner;

/**
 * @author cm.ge.
 */
public class AppPort {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //根据操作类型，列举
        System.out.println("please select operate type:List SerialPorts(1) or get serialPort(2) ");
        String type = sc.nextLine();
        if ("1".equals(type)){
            listPortChoices();
        }else {
            System.out.println("please input com name:");
            String name = sc.nextLine();
            System.out.println("readly listen "+name);
            SerialBean serialBean = new SerialBean(name, 115200);
            serialBean.Initialize();
        }
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
