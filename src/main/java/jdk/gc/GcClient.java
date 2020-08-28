 package jdk.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 胡鹏
 * @date 2020/08/16
 */
public class GcClient {
    
    public byte[] allocation1 = new byte[1];

    public static void main(String[] args) throws InterruptedException {
        List<GcClient> list = new ArrayList<GcClient>();
        list.add(new GcClient());
//        while(true) {
//        }
    }
}
