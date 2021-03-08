package com.hf.mybootDbJdbc.service.impl;

import com.hf.mybootDbJdbc.entity.OrderInfo;
import com.hf.mybootDbJdbc.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Service;

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

    @Override
    public int addOrderInfo(OrderInfo orderInfo) {
        return 0;
    }

    @Override
    public int[] addBatchOrderInfo(List<OrderInfo> orderInfoList) {
        System.out.println("开始操作入库。。。");
        ExecutorService executorService = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 10+1, 500, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(50000), new ThreadPoolExecutor.CallerRunsPolicy());
        String sql = "insert into order_info(order_status, total_amount) values (:orderStatus, :totalAmount)";
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
