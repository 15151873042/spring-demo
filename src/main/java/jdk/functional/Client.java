 package jdk.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author 胡鹏
 * @date 2020/07/06
 */
public class Client {

    @Test
    public void listTest() {
        UserModel user1 = new UserModel(10, "boy");
        UserModel user2 = new UserModel(10, "boy");
        UserModel user3 = new UserModel(10, "boy");
        UserModel user4 = new UserModel(10, "boy");
        UserModel user5 = new UserModel(10, "boy");
        List<UserModel> list = Arrays.asList(user1, user2, user3, user4, user5);
        list.forEach(this::setUserSexValue);
        System.out.println(list);
    }
    
    private void setUserSexValue(UserModel user) {
        if(user.getSexKey().equals("boy")) {
            user.setSexValue("男");
        } else {
            user.setSexValue("女");
        }
    }
    
    
    public static void main(String[] args) {

        List<String> list1 = Arrays.asList(
            "1Nju0RTPpCqkDOwP4zO",
            "3pdLXGL8bNs8Er6u7Ah",
            "67T1v5NbuYHQ7uijJDS",
            "6d91wV1i8EjTo8rcyWj",
            "725pB6onvs0bOl8CjBS",
            "8HoAHDYKco3waYL5zMW",
            "9Ik2TRJSF5ZxtXO5fgj",
            "a2Vj3Twl31p6jjhqI1M",
            "ap8p3y3K9jJNvBnwbSg",
            "b4xBn6CtUndKsc1mZV1",
            "BM2T5a7VPCh5jTYIl9d",
            "bo2jPqSZ7u8i5GViJCe",
            "bosUser000000000002",
            "BTIbZSAPauYL9fyz407",
            "bTqijeV9fGsmmxQZRuB",
            "c82BqXecVUxX0aqpH8I",
            "cLHPNulJSOcwaOntnX1",
            "DXou07W86izKKi94L4E",
            "f95oe6DW1FFt6jve3S2",
            "GHgF3wLLXivi69HOqpP",
            "id9sSh4akaaXUSOroRE",
            "IlTcEok34SZsL3uq8oc",
            "J2HotOt4yERMON4IOQq",
            "k2DQ4lsQkQa2grn0JOD",
            "K6UhKpGVkWsBe4uTpB5",
            "kj1JjOP8GSpCRu18jwH",
            "kL7ueOAKUHm8ozHjGfm",
            "LjBJni492Vx8PIHqlYW",
            "MGDlFtsX5GsUSOth3UM",
            "nNlqyVk5wDIzoFwLUqd",
            "ocrvzQEsyb6rTs5hbAA",
            "oITUwW15vdAlGgJO8R8",
            "OWI1XUkqtT5kHNt44NJ",
            "wl6XyrL0kReRrbDFW8S",
            "X7w6NVuegO6iuZlkzo2",
            "yzF9a3O403oe249nz52",
            "z6YM2wVfYPzpRkjo6pY",
            "ZPjwvoPQqF7m9uaOiNY",
            "zWVELFtrkbGvTyXxLai");
        
       List<String> list2 = Arrays.asList("bosUser000000000002",
                    "c82BqXecVUxX0aqpH8I",
                    "6d91wV1i8EjTo8rcyWj",
                    "9Ik2TRJSF5ZxtXO5fgj",
                    "K6UhKpGVkWsBe4uTpB5",
                    "X7w6NVuegO6iuZlkzo2",
                    "oITUwW15vdAlGgJO8R8",
                    "ap8p3y3K9jJNvBnwbSg",
                    "Ms3BQk6HY1c1viTTgIU",
                    "1Nju0RTPpCqkDOwP4zO",
                    "IlTcEok34SZsL3uq8oc",
                    "8HoAHDYKco3waYL5zMW",
                    "f95oe6DW1FFt6jve3S2",
                    "z6YM2wVfYPzpRkjo6pY",
                    "ocrvzQEsyb6rTs5hbAA",
                    "kL7ueOAKUHm8ozHjGfm",
                    "kj1JjOP8GSpCRu18jwH",
                    "id9sSh4akaaXUSOroRE",
                    "ZPjwvoPQqF7m9uaOiNY",
                    "OWI1XUkqtT5kHNt44NJ",
                    "yzF9a3O403oe249nz52",
                    "zWVELFtrkbGvTyXxLai",
                    "nNlqyVk5wDIzoFwLUqd",
                    "cLHPNulJSOcwaOntnX1",
                    "BTIbZSAPauYL9fyz407",
                    "LjBJni492Vx8PIHqlYW",
                    "725pB6onvs0bOl8CjBS",
                    "DXou07W86izKKi94L4E",
                    "k2DQ4lsQkQa2grn0JOD",
                    "J2HotOt4yERMON4IOQq",
                    "a2Vj3Twl31p6jjhqI1M",
                    "bo2jPqSZ7u8i5GViJCe",
                    "3pdLXGL8bNs8Er6u7Ah",
                    "67T1v5NbuYHQ7uijJDS",
                    "bTqijeV9fGsmmxQZRuB",
                    "BM2T5a7VPCh5jTYIl9d",
                    "GHgF3wLLXivi69HOqpP",
                    "b4xBn6CtUndKsc1mZV1",
                    "MGDlFtsX5GsUSOth3UM",
                    "wl6XyrL0kReRrbDFW8S");
       
       ArrayList<String> l1 = new ArrayList<>(list1);
       ArrayList<String> l2 = new ArrayList<>(list2);
       l2.removeAll(l1);
       
       System.out.println(l2);
         
        
        
        
    
    }
    
    @Test
    public void test() {
        List<String> list1 = Arrays.asList("88HmllAB3YMsKFd84oj",
            "IlTcEok34SZsL3uq8oc",
            "LjBJni492Vx8PIHqlYW",
            "cLHPNulJSOcwaOntnX1",
            "bTqijeV9fGsmmxQZRuB",
            "bosUser000000000002",
            "z6YM2wVfYPzpRkjo6pY",
            "ocrvzQEsyb6rTs5hbAA",
            "nNlqyVk5wDIzoFwLUqd",
            "ap8p3y3K9jJNvBnwbSg",
            "yzF9a3O403oe249nz52",
            "f95oe6DW1FFt6jve3S2",
            "a2Vj3Twl31p6jjhqI1M",
            "c82BqXecVUxX0aqpH8I",
            "X7w6NVuegO6iuZlkzo2",
            "AWBCy3XCdInJcXSlDm6",
            "8HoAHDYKco3waYL5zMW",
            "kL7ueOAKUHm8ozHjGfm",
            "Ms3BQk6HY1c1viTTgIU",
            "BM2T5a7VPCh5jTYIl9d",
            "kj1JjOP8GSpCRu18jwH",
            "ZPjwvoPQqF7m9uaOiNY",
            "6d91wV1i8EjTo8rcyWj",
            "BTIbZSAPauYL9fyz407",
            "id9sSh4akaaXUSOroRE",
            "wl6XyrL0kReRrbDFW8S",
            "bo2jPqSZ7u8i5GViJCe",
            "9Ik2TRJSF5ZxtXO5fgj",
            "K6UhKpGVkWsBe4uTpB5",
            "3pdLXGL8bNs8Er6u7Ah",
            "67T1v5NbuYHQ7uijJDS",
            "DXou07W86izKKi94L4E",
            "MGDlFtsX5GsUSOth3UM",
            "zWVELFtrkbGvTyXxLai",
            "1Nju0RTPpCqkDOwP4zO",
            "GHgF3wLLXivi69HOqpP",
            "J2HotOt4yERMON4IOQq",
            "725pB6onvs0bOl8CjBS",
            "oITUwW15vdAlGgJO8R8",
            "b4xBn6CtUndKsc1mZV1",
            "k2DQ4lsQkQa2grn0JOD",
            "OWI1XUkqtT5kHNt44NJ");
        
       List<String> list2 = Arrays.asList("bosUser000000000002",
                    "c82BqXecVUxX0aqpH8I",
                    "6d91wV1i8EjTo8rcyWj",
                    "9Ik2TRJSF5ZxtXO5fgj",
                    "K6UhKpGVkWsBe4uTpB5",
                    "X7w6NVuegO6iuZlkzo2",
                    "oITUwW15vdAlGgJO8R8",
                    "ap8p3y3K9jJNvBnwbSg",
                    "Ms3BQk6HY1c1viTTgIU",
                    "1Nju0RTPpCqkDOwP4zO",
                    "IlTcEok34SZsL3uq8oc",
                    "8HoAHDYKco3waYL5zMW",
                    "f95oe6DW1FFt6jve3S2",
                    "z6YM2wVfYPzpRkjo6pY",
                    "ocrvzQEsyb6rTs5hbAA",
                    "kL7ueOAKUHm8ozHjGfm",
                    "kj1JjOP8GSpCRu18jwH",
                    "id9sSh4akaaXUSOroRE",
                    "ZPjwvoPQqF7m9uaOiNY",
                    "OWI1XUkqtT5kHNt44NJ",
                    "yzF9a3O403oe249nz52",
                    "zWVELFtrkbGvTyXxLai",
                    "nNlqyVk5wDIzoFwLUqd",
                    "cLHPNulJSOcwaOntnX1",
                    "BTIbZSAPauYL9fyz407",
                    "LjBJni492Vx8PIHqlYW",
                    "725pB6onvs0bOl8CjBS",
                    "DXou07W86izKKi94L4E",
                    "k2DQ4lsQkQa2grn0JOD",
                    "J2HotOt4yERMON4IOQq",
                    "a2Vj3Twl31p6jjhqI1M",
                    "bo2jPqSZ7u8i5GViJCe",
                    "3pdLXGL8bNs8Er6u7Ah",
                    "67T1v5NbuYHQ7uijJDS",
                    "bTqijeV9fGsmmxQZRuB",
                    "BM2T5a7VPCh5jTYIl9d",
                    "GHgF3wLLXivi69HOqpP",
                    "b4xBn6CtUndKsc1mZV1",
                    "MGDlFtsX5GsUSOth3UM",
                    "wl6XyrL0kReRrbDFW8S");
       list1.removeAll(list2);
       
       System.out.println(list1);
         
        
        
        
    }
}
