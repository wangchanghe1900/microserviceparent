package cn.unicom.microservice.controller;

import cn.unicom.microservice.web.Response;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author 王长河
 * @create 2019-12-09 16:20
 */
@Controller
public class LoginController {

    @Autowired
    private Producer producer;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String index(){
        return "login/login";
    }

    @GetMapping("/index")
    public String main(){
        return "index";
    }

    @RequestMapping("captcha.jpg")
    public void captcha(HttpServletRequest request,HttpServletResponse response)throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        HttpSession session = request.getSession();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        //ShiroUtils.setSessionAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @PostMapping("/login")
    @ResponseBody
    public Response login(HttpServletRequest request, String userName, String password, String code){
        HttpSession session = request.getSession();
        String sessioncode = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        if(!sessioncode.equals(code)){
            return new Response(500,"验证码错误！");
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.add("X-Auth-Token", "e348bc22-5efa-4299-9142-529f07a18ac9");
            MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<String, String>();
            postParameters.add("userName","admin");
            postParameters.add("password",password);
            HttpEntity<MultiValueMap<String, String>> requestEntity  = new HttpEntity<MultiValueMap<String, String>>(postParameters, headers);
            Response response = restTemplate.postForObject("http://127.0.0.1:9001/v1/getUserByName",requestEntity,Response.class);
            if(response!=null){
                return response;
            }
            return new Response(500,"用户不存在！");
        }

    }
   @GetMapping("/main")
    public String main1(){
        return "main";
   }
   @GetMapping("/test")
    public String test(){
        return "test";
   }
}
