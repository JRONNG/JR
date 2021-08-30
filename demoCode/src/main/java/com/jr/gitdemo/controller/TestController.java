package com.jr.gitdemo.controller;


import com.jr.gitdemo.dao.UserMapper;
import com.jr.gitdemo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Transactional
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    UserMapper userMapper;
    @RequestMapping("/aaa")
    @ResponseBody
    public String aaa ()
    {
        System.out.println("aaa");
        logger.info("this is a test");
        return  "aaa";
    }


    @RequestMapping("/transactionTest")
    @ResponseBody

    public void transactionTest ()
    {
        try {
            User  user = new User();
            user.setUserName("88");
            user.setPassWord("88");
            user.setRealName("88");
            int result = userMapper.insert(user);

            User  userSec = new User();
            userSec.setUserName("9");
            userSec.setPassWord("9");
            userSec.setRealName("9");
            int resultSec = userMapper.insert(userSec);
            throw  new Exception("我是故意的");
        }catch (Exception e)
        {
            logger.info("抛异常:{}",e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }


    }
}
