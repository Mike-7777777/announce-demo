package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class MockDownloadComponent {
    private static final Logger log = LoggerFactory.getLogger(DownloadController.class);

    @Async // 使其异步化
    public void mockDownload(String sessionid) {

        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(200 * i); // 模拟发布耗时

                String proplink = "./announcement_link?=00" + i;
                String proptitle = "这是第 " + i + " 个通知的标题";
                String proptime = "1"+i+":59";

                String content = String.format(
                        "{\"uid\":\"%s\",\"proplink\":\"%s\",\"proptitle\":\"%s\", \"proptime\":\"%s\"}", sessionid,
                        proplink, proptitle, proptime);
                log.info("username={}'s post called {} whose url is {} when {}", sessionid, proplink, proptitle,
                        proptime);
                // "uid":"001","proplink":"ann001","proptitle":"title001","proptime":"23:59"
                SseNotificationController.usesSsePush(sessionid, content);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // for (int i = 0; i < 100; i++) {
        // try {
        // TimeUnit.MILLISECONDS.sleep(100); //模拟下载耗时

        // int percent = i + 1;
        // String content = String.format("{\"username\":\"%s\",\"percent\":%d}",
        // sessionid, percent);
        // // 格式 {"username":"abc","percent":1}
        // log.info("username={}'s file has been finished [{}]% ", sessionid, percent);
        // //sse推送
        // SseNotificationController.usesSsePush(sessionid, content);
        // } catch (InterruptedException e) {
        // e.printStackTrace();
        // }
        // }
    }
}