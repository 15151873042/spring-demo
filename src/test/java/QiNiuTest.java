import org.junit.Test;

import com.pili.PiliException;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;

/**
 * @author 胡鹏
 * @date 2020/06/29
 */
public class QiNiuTest {
    
    public static void main(String[] args) throws PiliException {
        MailAccount account = new MailAccount();
        account.setHost("smtp.exmail.qq.com");
        account.setPort(465);
        account.setSslEnable(true);
        account.setAuth(true);
        account.setFrom("android@17got.com");
        account.setUser("android@17got.com");
        account.setPass("pyqho4EQaFEjNHn3");
        MailUtil.send(account, "hupeng@17got.com", "踢出直播间僵尸用户出错", "test", false);
        System.out.println("发送成功");
    }
    
    
    
    @Test
    public void test() throws Exception {
        String $name = "aaa";
    }
}
