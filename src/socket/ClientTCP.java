package socket;

import javax.xml.soap.SOAPConnection;
import java.io.*;
import java.net.Socket;

/**
 * Created by ECIZEP on 2017/2/28.
 */
public class ClientTCP {
    public static void main(String args[]) {
        try {
            Socket socket = new Socket("localhost", 8888);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream);
            printWriter.write("你好，我是客户端");
            printWriter.flush();
            socket.shutdownOutput();
            //printWriter.close();  关闭输入输出流回导致socket也被关闭

            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String data = null;
            while((data = bufferedReader.readLine()) != null){
                System.out.println("我是客户端，服务器说：" + data);
            }

            //对于同一个socket,如果关闭了输入输出流，则与该输出流相关的socket页会被关闭，所以一般不用关闭流，直接关闭socket
            /*
            socket.shutdownInput();
            printWriter.close();
            outputStream.close();
            bufferedReader.close();
            inputStream.close();*/
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
