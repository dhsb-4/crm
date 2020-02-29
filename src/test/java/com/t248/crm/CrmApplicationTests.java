package com.t248.crm;

import com.t248.crm.com.ucpaas.restDemo.client.JsonReqClient;
import com.t248.crm.service.email.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class CrmApplicationTests {

    @Autowired
    private MailService mailService;
    @Test
    public void testMail() throws  Exception {
        String to = "857989923@qq.com";
        String subject = "咸蛋超人";
        String content = "好了没";
        try {
            mailService.sendSimpleMail(to, subject, content);
            System.out.println("成功了");

        } catch (MailException e) {
            System.out.println("失败了");
            e.printStackTrace();
        }
    }

    /**
     * 验证码
     */
    @Test
    public void authCode() throws Exception {

        String sid = "e21f93b47d21f012791f820c6732eaa0";//用户的账号唯一标识“Account Sid”
        String token = "1a9df3660675c869f4c30fff9e336e52";//用户密钥“Auth Token”
        String appid = "bcfe4a7c79254ead86949019d4d8ba49";//创建应用时系统分配的唯一标示
        String templateid = "494081";//可在后台短信产品→选择接入的应用→短信模板-模板ID，查看该模板ID
        String param = generateWord();//模板中的替换参数（验证码）
        String mobile = "18688254730";//接收的单个手机号，暂仅支持国内号码
        String uid = "";//用户透传ID，随状态报告返回

        try {
            String result=new JsonReqClient().sendSms(sid, token, appid, templateid, param, mobile, uid);
            System.out.println("Response content is: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 产生随机的6位数字字符串
     */
    private static String generateWord() {
        int length = 6;
        String[] beforeShuffle = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        List list = Arrays.asList(beforeShuffle);
        Collections.shuffle(list);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i));
        }
        String afterShuffle = sb.toString();
        return afterShuffle.substring(2, 2 + length);
    }

}
