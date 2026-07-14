package com.sunil.demo.Portfolio;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyPortfolio {

    @GetMapping("/myself")
    public String myself() {
        return """
                <h1>About Me</h1>
                <p>Hi, I am Sunil Kumar, a B.Tech CSE student (2027) with strong interest in Full Stack Development and Game Development.</p>                <p>GitHub: <a href="https://github.com/sunilkumar0004/SpringBoot" target="_blank">
                Github Repo SpringBoot</a></p>
                """;
    }
}
