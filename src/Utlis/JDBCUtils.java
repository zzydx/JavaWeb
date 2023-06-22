package Utlis;

import com.alibaba.druid.pool.DruidAbstractDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtils {

    private static DruidAbstractDataSource dataSource;
    private static ThreadLocal<Connection> connectionThreadLocal = new ThreadLocal<>();

    static {
        try {
            Properties properties = new Properties();
            //读取jdbc.properties配置文件
            InputStream inputStream = JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            //从流中加载数据
            properties.load(inputStream);
            //创建数据库连接池
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //获取数据库连接池中的链接,返回null获取失败
    public static Connection getConnection() {
        Connection connection = connectionThreadLocal.get();
        if (connection == null) {
            try {
                connection = dataSource.getConnection();
                //保存到threadlocal对象中
                connectionThreadLocal.set(connection);
                connection.setAutoCommit(false);//关闭事务自动提交

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
//        Connection connection = null;
//        try {
//            connection = dataSource.getConnection();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        return connection;
    }

    /*
    提交事务并关闭连接
     */
    public static void commitAndClose() {
        Connection connection = connectionThreadLocal.get();
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //移除线程本地共享变量的连接，将线程放回线程池
        connectionThreadLocal.remove();
    }

    /*
    回滚事务并关闭连接
     */
    public static void rollbackAndClose() {
       Connection connection = connectionThreadLocal.get();
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //移除线程本地共享变量的连接，将线程放回线程池
        connectionThreadLocal.remove();
    }

    //关闭连接 放回数据库连接池
//    public static void close(Connection conn) {
//        if (conn != null) {
//            try {
//                conn.close();
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
}
