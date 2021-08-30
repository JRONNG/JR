package com.jr.gitdemo;

import com.jr.gitdemo.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
public class StreamTest {



    @Test
    public  void  filterToList()
    {
        List<User> userList = new ArrayList<>();
        for (int i= 0;i<10;i++)
        {
            User user = new User();
            user.setId(i);
            user.setPassWord("password"+i);
            user.setRealName("realName"+i);
            user.setUserName("userName"+i);
            userList.add(user);
        }

        List<User> userStream =  userList.stream().filter(user -> user.getId() == 3).collect(Collectors.toList());
        System.out.println(userStream);
    }

    @Test
    public  void  map()
    {
        List<User> userList = new ArrayList<>();
        for (int i= 0;i<10;i++)
        {
            User user = new User();
            user.setId(i);
            user.setPassWord("password"+i);
            user.setRealName("realName"+i);
            user.setUserName("userName"+i);
            userList.add(user);
        }

        List<User> userStream =  userList.stream().filter(user -> user.getId() == 3).collect(Collectors.toList());
        System.out.println(userStream);
    }


    @Test
    public  void  sorted()
    {
        List<User> userList = new ArrayList<>();
        for (int i= 0;i<10;i++)
        {
            User user = new User();
            user.setId(i);
            user.setPassWord("password"+i);
            user.setRealName("realName"+i);
            user.setUserName("userName"+i);
            userList.add(user);
        }

        List<User> userStream =  userList.stream().sorted(
                (o1,o2)->{
                    if(o1.getId() == o2.getId())
                        return o2.getUserName().compareTo(o1.getUserName());
                    else
                        return o2.getId() - o1.getId()  ;
                }
        ).collect(Collectors.toList());
        System.out.println(userStream);
    }

}
