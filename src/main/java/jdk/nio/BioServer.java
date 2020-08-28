package jdk.nio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO所谓阻塞时不管客户端还是服务端在读取数据（inputStream.read()）是阻塞的，必须等待另一方写数据
 * 
 * @author 胡鹏
 * @date 2020/08/27
 */
public class BioServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while (true) {
            System.out.println("等待连接。。");
            // 阻塞方法
            Socket socket = serverSocket.accept();
            System.out.println("有客户端连接了。。");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            // handler(socket);
        }
    }

    private static void handler(Socket socket) throws IOException {
        System.out.println("thread id = " + Thread.currentThread().getId());
        byte[] bytes = new byte[1024];

        System.out.println("准备read。。");
        // 接收客户端的数据，阻塞方法，没有数据可读时就阻塞
        int read = socket.getInputStream().read(bytes);
        System.out.println("read完毕。。");
        if (read != -1) {
            System.out.println("接收到客户端的数据：" + new String(bytes, 0, read));
            System.out.println("thread id = " + Thread.currentThread().getId());

        }
        socket.getOutputStream().write("HelloClient".getBytes());
        socket.getOutputStream().flush();
    }
}
