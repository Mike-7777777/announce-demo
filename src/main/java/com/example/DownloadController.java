package com.example;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static org.slf4j.LoggerFactory.getLogger;


@RestController
public class DownloadController {
    private static final Logger log = getLogger(DownloadController.class);
    @Autowired
    private MockDownloadComponent downloadComponent;  

    @GetMapping("/api/download/")
    public String download(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String sessionid = session.getId();
        log.info("sessionid=[{}]", sessionid);
        downloadComponent.mockDownload(sessionid);  // 用于异步模拟下载文件的过程
        return "success"; 
    }
}