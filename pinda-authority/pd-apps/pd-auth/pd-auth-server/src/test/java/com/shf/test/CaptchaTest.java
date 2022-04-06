package com.shf.test;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.ChineseCaptcha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * easyCaptcha
 */
public class CaptchaTest {
    public static void main(String[] args) throws FileNotFoundException {
//        中文验证码
        ChineseCaptcha chineseCaptcha = new ChineseCaptcha();
//        获取背刺生成的验证码
        String text = chineseCaptcha.text();
        System.out.println(text);

        chineseCaptcha.out(new FileOutputStream(new File("C:\\Users\\shuho\\Documents\\Code\\PinDa-Authority_Demo01\\pinda-authority\\pd-apps\\pd-auth\\pd-auth-server\\src\\test\\java\\com\\shf\\test\\chineseCaptcha.png")));

//        算数验证码
        ArithmeticCaptcha arithmeticCaptcha = new ArithmeticCaptcha();
//        获取背刺生成的验证码
        text = arithmeticCaptcha.text();
        System.out.println(text);

        arithmeticCaptcha.out(new FileOutputStream(new File("C:\\Users\\shuho\\Documents\\Code\\PinDa-Authority_Demo01\\pinda-authority\\pd-apps\\pd-auth\\pd-auth-server\\src\\test\\java\\com\\shf\\test\\arithmeticCaptcha.png")));

    }
}
