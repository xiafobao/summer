package com.xia.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/config/client")
public class ConfigClientController {
    /** 外部属性 env */
    @Value("${account}")
    private String account;

    @Value("${repositoryUrl}")
    private String repositoryUrl;

    @Value("${other}")
    private String other;

    @GetMapping("/getRepositoryUrl")
    public String getRepositoryUrl() {
        StringBuilder resultUrl = new StringBuilder("other：");
        resultUrl.append(other);
        resultUrl.append("<br/>");
        resultUrl.append("account：");
        resultUrl.append(account);
        resultUrl.append("<br/>");
        resultUrl.append("repositoryUrl：");
        resultUrl.append(repositoryUrl);
        return resultUrl.toString();
    }
}