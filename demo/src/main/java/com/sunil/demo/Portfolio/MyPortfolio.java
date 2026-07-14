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
    @GetMapping("/skills")
    public String skills() {
        return """
                <h1>Skills</h1>
                <ul>
                    <li>Languages: C, C++, Java, Python, JavaScript</li>
                    <li>Frontend: React, Tailwind CSS, Bootstrap</li>
                    <li>Backend: Node.js, Express.js, Spring Boot</li>
                    <li>Database: MySQL, MongoDB</li>
                    <li>Tools: Git, Postman, Cloudinary</li>
                </ul>
                """;
    }
    @GetMapping("/education")
    public String education() {
        return """
                <h1>Education</h1>
                <p>B.Tech in Computer Science Engineering</p>
                <p>Lovely Professional University (2023 - 2027)</p>
                """;
    }
    @GetMapping("/projects")
    public String projects() {
        return """
                <h1>Projects</h1>
                <ul>
                    <li><b>AIIRA - AI InvestMent Platform</b> (MERN + Gemini API)</li>
                    <li><b>LearnPath - Coding Learning Platform</b> (MERN + WebSockets)</li>
                    <li><b>Academic Portal - Credentials Calculator</b> (Node.js, JWT, gemeni api)</li>
                </ul>
                """;
    }
}
