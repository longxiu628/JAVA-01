package com.hf.mybootDbDruid.service.impl;

import com.google.common.collect.Lists;
import com.hf.mybootDbDruid.entity.OrderInfo;
import com.hf.mybootDbDruid.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author hfzhang
 * @date 2021/3/3
 */
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 10+1, 500, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(50000), new ThreadPoolExecutor.CallerRunsPolicy());
    private String sql = "insert into order_info(order_status, total_amount) values (:orderStatus, :totalAmount)";

    @Override
    public int addOrderInfo(OrderInfo orderInfo) {
        int result = jdbcTemplate.update(sql, new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, orderInfo.getOrderStatus());
                preparedStatement.setInt(2, orderInfo.getTotalAmount());
            }
        });
        return result;
    }

    @Override
    public void saveBatchOrderInfo(List<OrderInfo> orderInfoList) {
        System.out.println("开始操作入库。。。"+ new Date());
        long start = System.currentTimeMillis();
        List<List<OrderInfo>> partitionList = Lists.partition(orderInfoList, 500);
        List<Callable<Object>> list = Lists.newArrayList();
        for(int i=0;i<partitionList.size();i++){
            List<OrderInfo> orderInfos = partitionList.get(i);
            list.add(new Callable<Object>(){
                @Override
                public Object call() throws Exception {
//                    System.out.println("开始执行。。。");
                    int[] result = namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(orderInfos));
//                    System.out.println("执行结束。。。"+(System.currentTimeMillis()-start));
                    return result;
                }
            });
        }

        try {
            System.out.println("任务长度："+list.size());
            List<Future<Object>> futures = executorService.invokeAll(list);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("批量插入耗时："+(System.currentTimeMillis()-start)+"   时间："+new Date());
    }

    @Override
    public int[] addBatchOrderInfo(List<OrderInfo> orderInfoList) {
        System.out.println("开始操作入库。。。");
        long start = System.currentTimeMillis();
        Future<Object> future = executorService.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                System.out.println("开始执行。。。");
                int[] result = namedParameterJdbcTemplate.batchUpdate(sql, SqlParameterSourceUtils.createBatch(orderInfoList));
                System.out.println("执行结束。。。"+(System.currentTimeMillis()-start));
                return result;
            }
        });

        int[] exeResult = null;
        try {
            exeResult = (int[]) future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("批量插入耗时："+(System.currentTimeMillis()-start));
        return exeResult;
    }
}
