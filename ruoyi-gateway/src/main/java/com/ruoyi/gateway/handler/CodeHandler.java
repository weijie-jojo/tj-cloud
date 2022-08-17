package com.ruoyi.gateway.handler;

import com.ruoyi.gateway.config.CodePicture;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.RenderedImage;
import java.io.IOException;

@RestController
public class CodeHandler {

    @GetMapping("/code")
    public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //        获取图片及验证码
        Object[] code = CodePicture.createImage();
        //        把验证码的值存入session
        req.getSession().setAttribute("code",code[0]);
        //        将验证码图片返回给浏览器
        ImageIO.write((RenderedImage) code[1],"jpg",resp.getOutputStream());
    }
}
