package com.happybudui.springtest;

import com.happybudui.springbase.SpringBaseApplication;
import com.happybudui.springbase.mapper.UserMapper;
import com.happybudui.springbase.entity.UserEntity;
import com.happybudui.springbase.service.MailService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBaseApplication.class)
public class SpringBaseApplicationTests {
    //sendmail test
    //has passed
    @Autowired
    private MailService mailService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendHtmlMail("one_how@163.com","test simple mail"," hello this is simple mail");
    }

    @Test
    public void testSendActiveMail() throws  Exception{
        mailService.sendActiveMail("c961ac31e261424ab0b87a5318fd7143","one_how@163.com");
    }

    //mapper test
    @Test
    public void testMapperInsert(){
        UserEntity userEntity=new UserEntity("one_how@163.com","wangh","123");
        int res=userMapper.insertUser(userEntity);
        Assert.assertEquals(1,res);

        UserEntity userEntity1=userMapper.getUserInfoByMail("one_how@163.com");
        Assert.assertEquals(userEntity.getUserId(),userEntity1.getUserId());
    }

    @Test
    public void testMapperSelect(){
       UserEntity userEntity=userMapper.getUserInfoById("c961ac31e261424ab0b87a5318fd7143");
       Assert.assertEquals("alevery",userEntity.getUserName());
    }

    @Test
    public void testMapperUpdate(){
        int res=userMapper.updateUserNameById("c961ac31e261424ab0b87a5318fd7143","alevery2");
        Assert.assertEquals(1,res);
        res=userMapper.updateUserPasswordById("c961ac31e261424ab0b87a5318fd7143","123456");
        Assert.assertEquals(1,res);
        res=userMapper.updateUserMailActiveStatus("c961ac31e261424ab0b87a5318fd7143");
        Assert.assertEquals(1,res);
        res=userMapper.updateUserStatusdById("c961ac31e261424ab0b87a5318fd7143",1);
        Assert.assertEquals(1,res);
    }

    @Test
    public void testMapperDelete(){
        int res=userMapper.deleteUserById("c961ac31e261424ab0b87a5318fd7143");
        Assert.assertEquals(1,res);
    }
}
