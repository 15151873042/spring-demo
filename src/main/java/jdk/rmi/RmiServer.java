package jdk.rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @ClassName: RmiClient
 * @Description:
 * @Author 胡鹏
 * @Date 2020/9/24
 */
public class RmiServer {

    public static void main(String[] args) throws RemoteException {
        MyRemoteInterface obj = new MyRemoteObject("胡鹏"); // #1
        Registry registry = LocateRegistry.createRegistry(1099); // #3
        registry.rebind("Hello", obj); // #4
    }

}
