package com.example.UserWishList.controller;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@AllArgsConstructor
public class redirectController {
    HttpServletResponse response;
    @GetMapping("/")
    @SneakyThrows
    public void redirectToSwagger(){
        response.sendRedirect("/swagger-ui/index.html#");
    }
}
