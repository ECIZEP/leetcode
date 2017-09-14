package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by ECIZEP on 2017/2/28.
 * TCP Socket编程
 */
public class ServerTCP {
    public static void main(String args[]) {
        try {
            int count = 0;
            //创建一个服务器端socket,绑定端口，IP地址在服务器端的绑定由jdk完成
            ServerSocket serverSocket = new ServerSocket(8888);
            //开始监听此端口
            System.out.println("等待客户端连接...");

            while (true) {
                Socket socket = serverSocket.accept();
                if(socket != null) {
                    ServerThread serverThread = new ServerThread(socket, "你是第" + (++count) + "连接的用户");
                    serverThread.setPriority(4);
                    serverThread.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

class ServerThread extends Thread {
    private Socket socket = null;
    private String message = null;

    public ServerThread(Socket socket, String message){
        this.socket = socket;
        this.message = message;
    }

    public void run(){
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        OutputStream outputStream = null;
        PrintWriter printWriter = null;
        try {
            //读取客户端请求
            inputStream = socket.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);
            bufferedReader = new BufferedReader(inputStreamReader);
            String info = null;
            System.out.print(socket.getInetAddress().getHostName() + " " + socket.getPort() + " :");
            while((info = bufferedReader.readLine()) != null){
                System.out.print(info);
            }
            System.out.println("");
            socket.shutdownInput();
            //获取输出流，响应客户端请求
            outputStream = socket.getOutputStream();
            printWriter = new PrintWriter(outputStream);
            printWriter.write(message);
            printWriter.flush();
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(printWriter != null){
                    printWriter.close();
                }
                if(outputStream != null){
                    outputStream.close();
                }
                if(bufferedReader != null){
                    bufferedReader.close();
                }
                if(inputStreamReader != null){
                    inputStreamReader.close();
                }
                if(inputStream != null){
                    inputStream.close();
                }
                if(socket != null) {
                    socket.close();
                }
            } catch (IOException e){
                e.printStackTrace();
            }

        }

    }
}
