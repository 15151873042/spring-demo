package jdk.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @ClassName: MyRemoteObject
 * @Description:
 * @Author 胡鹏
 * @Date 2020/9/24
 */
public class MyRemoteObject extends UnicastRemoteObject implements MyRemoteInterface {

    public String name;

    protected MyRemoteObject(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyRemoteObject{" +
                "name='" + name + '\'' +
                '}';
    }



    @Override
    public String sayName() throws RemoteException {
        return name;
    }
}
