package socket;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.*;
import java.util.Arrays;

/**
 * Created by ECIZEP on 2017/3/1.
 */
public class ClientUDP {
    public static void main(String[] args) {
        try {
            // 1.服务器的IP地址和端口，以及要发送的数据
            InetAddress inetAddress = InetAddress.getByName("localhost");
            int port = 8800;
            byte[] data = "你好，我是客户端".getBytes();
            // 2.数据封装，创建数据报
            DatagramPacket datagramPacket = new DatagramPacket(data, data.length, inetAddress, port);
            // 3.创建UDP套接字
            DatagramSocket datagramSocket = new DatagramSocket();
            // 4.发送
            datagramSocket.send(datagramPacket);

            //1.创建一个数据报来接受服务器端的响应
            byte[] response = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(response, response.length);
            //2.接受数据报，没有接受到的时候该方法会阻塞
            datagramSocket.receive(responsePacket);
            //3.读取数据
            String responseStr = new String(response, 0, responsePacket.getLength());
            //String res = new String(responsePacket.getData());
            //System.out.println(responsePacket.getData() == response); true 同一个对象
            System.out.println("我是客户端，服务器说:" + responseStr);

            //关闭资源
            datagramSocket.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
