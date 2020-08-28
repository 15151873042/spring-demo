import java.io.IOException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 胡鹏
 * @date 2020/08/10
 */
public class Client {

    private static Lock lock = new ReentrantLock();
    
    public static void main(String[] args) throws IOException, InterruptedException {
        LockSupport.park();
        
    }
        
}
