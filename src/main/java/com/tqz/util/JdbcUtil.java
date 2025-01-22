package com.tqz.util;

import java.sql.*;

/**
 * @author <a href="https://github.com/tian-qingzhao">tianqingzhao</a>
 * @since 2024/12/19 16:05
 */
public class JdbcUtil {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //1、加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        //2、创建连接
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seata-moxa", "root", "123456");
        //3、写SQL语句
        String sql = "select * from undo_log where branch_id = 18575184999858684";
        //4、获得statement对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //5、执行SQL语句 得到结果集
        ResultSet resultSet = statement.executeQuery();
        //6、处理结果集
        while (resultSet.next()) {
            System.out.println(resultSet.getLong(1));
            System.out.println(resultSet.getString(2));
            System.out.println(resultSet.getString(3));
            System.out.println(resultSet.getString(4));
            System.out.println(resultSet.getString(5));
            System.out.println(resultSet.getString(6));
            System.out.println(resultSet.getString(7));
        }
    }
}
