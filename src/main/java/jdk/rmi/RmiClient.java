package jdk.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * http://www.360doc.com/content/11/0928/10/1073512_151819927.shtml
 * @ClassName: RmiClient
 * @Description:
 * @Author 胡鹏
 * @Date 2020/9/24
 */
public class RmiClient {

    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        MyRemoteInterface obj = (MyRemoteInterface) Naming.lookup("rmi://localhost:6666/obj");
        String name = obj.sayName();
        System.out.println(name);
    }

}
