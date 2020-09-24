package jdk.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @ClassName: MyRemoteInterface
 * @Description:
 * @Author 胡鹏
 * @Date 2020/9/24
 */
public interface MyRemoteInterface extends Remote {

    // 远程对象的方法必须抛出RemoteException
    String sayName() throws RemoteException;
}
