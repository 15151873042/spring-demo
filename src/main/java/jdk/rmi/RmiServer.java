package jdk.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * @ClassName: RmiClient
 * @Description:
 * @Author 胡鹏
 * @Date 2020/9/24
 */
public class RmiServer {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException, MalformedURLException {
        LocateRegistry.createRegistry(6666);
        MyRemoteInterface obj = new MyRemoteObject("胡鹏hp");
        Naming.bind("rmi://localhost:6666/obj", obj);
    }

}
