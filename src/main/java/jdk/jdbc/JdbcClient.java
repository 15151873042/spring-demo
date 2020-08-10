 package jdk.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

/**
 * @author 胡鹏
 * @date 2020/07/17
 */
public class JdbcClient {
    
    
    @Test
    public void statementTest() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/ai_app_dev?autoReconnect=true&useSSL=false&allowMultiQueries=true";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement statement = connection.createStatement();
        String id = "10sDRG2U2XVuyzq4VkS";
        ResultSet result = statement.executeQuery("select * from app_anchor where id = '"+ id + "'");
        while(result.next()) {
            System.out.println("is_deleted = " +result.getBoolean("is_deleted"));
        }
    }
    
    
    @Test
    public void prepareStatementTest() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://127.0.0.1:3306/ai_app_dev?autoReconnect=true&useSSL=false&allowMultiQueries=true";
        String user = "root";
        String password = "root";
        Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement prepareStatement = connection.prepareStatement("select * from app_base_dict where group_key = ?");
        prepareStatement.setString(1, "chatRoomType");
        ResultSet result = prepareStatement.executeQuery();
        while(result.next()) {
            System.out.println("id = " +result.getString("id"));
        }
    }

}
