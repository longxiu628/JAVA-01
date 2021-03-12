package pers.peixinyi.work.tcc.service.impl;

import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Service;
import pers.peixinyi.work.tcc.service.HelloService;

/**
 * @author: peixinyi
 * @version: V1.0.0.0
 * @date: 2021-03-11 11:30
 */
@Service
public class HelloServiceImpl implements HelloService {


    //当出现异常则会调用sayCancel 手动进行回滚操作
    //当成功执行会进行confirmMethod
    //正确: hello == xxx value !=0
    //错误: hello == xxx value == 0
    @HmilyTCC(confirmMethod = "sayConfrim", cancelMethod = "sayCancel")
    @Override
    public void say(String hello, int value) {
        int i = 100 / value;
        System.out.println("say:" + hello + ":" + i);
    }


    @Override
    public void sayConfrim(String hello, int value) {
        System.out.println("sayConfrim:" + hello);
    }

    @Override
    public void sayCancel(String hello, int value) {
        System.out.println("sayCancel:" + hello);
    }
}
