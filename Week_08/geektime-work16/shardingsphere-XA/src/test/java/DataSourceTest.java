import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.peixinyi.week16.RunApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-09 16:45
 */
@SpringBootTest(classes = RunApplication.class)
public class DataSourceTest {

    private static Logger logger = LoggerFactory.getLogger(DataSourceTest.class);

    @Autowired
    DataSource dataSource;

    @Test
    public void NoTransactionTest() {
        String sql = "insert into t_order (user_id,order_id,note)values(?,?,?)";
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.info("无法建立与DataSource的连接...");
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            connection.setAutoCommit(false);
            for (int i = 0; i < 32; i++) {
                ps.setInt(1, i);
                if (i == 31) {
                    ps.setString(1, "失败");
                }
                ps.setInt(2, i);
                ps.setString(3, "失败");
                ps.executeUpdate();
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 没有理解在ShardingSphere XA的功能.
     * 所谓的提交或未提交不是commit所管理的么?
     */
    @Test
    public void transactionTest() {
        TransactionTypeHolder.set(TransactionType.XA);
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("无法建立连接.");
        }
        String sql = "insert into t_order (user_id,order_id,note)values(?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql);) {
            connection.setAutoCommit(false);
            for (int i = 16; i < 32; i++) {
                if (i == 31) {
                    ps.setString(1, "B");
                } else {
                    ps.setInt(1, i);
                }
                ps.setInt(2, i);
                ps.setString(3, i + "你好");
                ps.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        logger.info("transaction end");
    }

}
