package socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by ECIZEP on 2017/3/1.
 */
public class ServerUDP {

    public static void main (String args[]) {
        try {
            //1.创建服务端DaragramSocket,指定端口
            DatagramSocket datagramSocket = new DatagramSocket(8800);
            //2.创建数据报用于接收客户端发送的数据
            byte[] data = new byte[1024];
            //3.接受客户端的数据，没有接受到数据前会阻塞
            System.out.println("服务器正在等待客户端的请求......");
            while(true){
                DatagramPacket dpReceive = new DatagramPacket(data, data.length);
                datagramSocket.receive(dpReceive);
                ServerUDPThread serverUDPThread = new ServerUDPThread(datagramSocket,dpReceive);
                serverUDPThread.start();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


class ServerUDPThread extends Thread {
    private DatagramSocket datagramSocket = null;
    private DatagramPacket datagramPacket = null;

    ServerUDPThread(DatagramSocket datagramSocket, DatagramPacket datagramPacket){
        this.datagramSocket = datagramSocket;
        this.datagramPacket = datagramPacket;
    }

    public void run(){
        //读取数据，因为UDP是无连接的，所以socket中没有IP地址和端口信息，而是存在GramPacket中
        String info = new String(datagramPacket.getData(), 0, datagramPacket.getLength());
        System.out.println(datagramPacket.getAddress() + " " + datagramPacket.getPort() + ":" + info);
        //1. 响应客户端,先获取IP地址，端口号，定义数据
        InetAddress inetAddress = datagramPacket.getAddress();
        int clientPort = datagramPacket.getPort();
        byte[] response = "欢迎你！".getBytes();
        //2. 创建数据报，封装数据
        DatagramPacket datagramPacket1 = new DatagramPacket(response,response.length, inetAddress, clientPort);
        //3. 发送数据
        try {
            datagramSocket.send(datagramPacket1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
