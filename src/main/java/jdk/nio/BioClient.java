 package jdk.nio;

import java.io.IOException;
import java.net.Socket;

/**
 * BIO所谓阻塞时不管客户端还是服务端在读取数据（inputStream.read()）是阻塞的，必须等待另一方写数据
 * 
 * @author 胡鹏
 * @date 2020/08/28
 */
public class BioClient {
    
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 9000);
        //向服务端发送数据
        socket.getOutputStream().write("HelloServer".getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务端发送数据结束");
        byte[] bytes = new byte[1024];
        //接收服务端回传的数据
        socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据：" + new String(bytes));
        Thread.sleep(10000000);
        socket.close();
    }

}
