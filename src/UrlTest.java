import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;


public class UrlTest {

    public static void main(String[] args) throws IOException {
        //testInetAddress();
        //testUrl();
        StringBuffer  a = new StringBuffer ("haha");
        StringBuffer  b = a;
        passValue(a);
        System.out.println(b + " " + a);
    }

    public static void passValue (StringBuffer  a) {
        a = new StringBuffer("sdf");
        System.out.println(a);
    }

    public static void testUrl() throws IOException {
        URL ecizep = new URL("http://ecizep.com");
        URL mainPage = new URL(ecizep, "/index.html");
        System.out.println("协议：" + mainPage.getProtocol());
        System.out.println("主机：" + mainPage.getHost());
        System.out.println("端口：" + mainPage.getPort());//没有指定端口号则返回默认80 端口，返回值为-1
        System.out.println("文件路径" + mainPage.getPath());

        //读取网络内容
        //获取URL对象的字节输入流
        InputStream inputStream = ecizep.openStream();
        //将字节输入流转化为字符输入流，UTF-8编码
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String data = bufferedReader.readLine();
        while(data != null){
            System.out.println(data);
            data = bufferedReader.readLine();
        }
        bufferedReader.close();
        inputStreamReader.close();
        inputStream.close();
    }

    public static void testInetAddress() throws UnknownHostException {
        /*InetAddress 类测试*/
        InetAddress address = InetAddress.getLocalHost();
        System.out.println("计算机：" + address.getHostName());
        System.out.println("IP地址：" + address.getHostAddress());

        byte[] bytes = address.getAddress();//获取字节数组形式的IP地址
        System.out.println(bytes);
        System.out.println(address);//直接输出计算机名+IP地址

        //根据机器名获取InetAddress实例
        InetAddress address1 = InetAddress.getByName("DESKTOP-7JV42E7");
        System.out.println("计算机：" + address1.getHostName());
        System.out.println("IP地址：" + address1.getHostAddress());

        //根据IP地址
        InetAddress address2 = InetAddress.getByName("121.195.167.128");
        System.out.println("计算机：" + address2.getHostName());
        System.out.println("IP地址：" + address2.getHostAddress());
    }
}
