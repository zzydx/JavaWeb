package Utlis;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO {
    //使用dbutils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update，insert，delete
     *
     * @param sql  执行的sql语句
     * @param args sql语句的占位符
     * @return
     */
    public int update(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回一个对象
     *
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T>  返回的对象类型
     * @return
     */
    public <T> T queryOne(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanHandler<T>(type), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询返回多个个对象
     *
     * @param type 返回的对象类型
     * @param sql  执行的sql语句
     * @param args sql对应的参数值
     * @param <T>  返回的对象类型
     * @return
     */
    public <T> List<T> queryList(Class<T> type, String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new BeanListHandler<T>(type), args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Object queryOneValue(String sql, Object... args) {
        Connection connection = JDBCUtils.getConnection();
        try {
            return queryRunner.query(connection, sql, new ScalarHandler(), args);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
