package com.shf.test;

import cn.hutool.core.io.FileUtil;
import io.jsonwebtoken.*;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.InputStream;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试jwt令牌
 */
public class JwtTest {
//    通过jjwt生成和解析jwt令牌，不使用签名算法
    @Test
    public void test1(){
//        用于封装jwt的header部分
        Map header = new HashMap();
//        不使用签名算法
        header.put("alg", "none");
//        指定令牌的类型,如果是jwt令牌都统一写为JWT
        header.put("typ","JWT");

//        用于封装jwt的body部分
        Map body = new HashMap();
        body.put("userId", "100");
        body.put("account", "admin");
        body.put("role","admin");

//        使用jjwt提供的API生成jwt令牌
        String jwt = Jwts
                .builder()
                .setHeader(header)
                .setClaims(body)
                .setId("101")
                .compact();
        System.out.println(jwt);


//        使用jjwt提供的API解析jwt令牌
        Jwt parse = Jwts
                .parser()
                .parse(jwt);
        Header header1 = parse.getHeader();
        Object body1 = parse.getBody();
        System.out.println(header1);
        System.out.println(body1);
    }

    @Test
    public void test2(){
//        用于封装jwt的header部分
        Map header = new HashMap();
//        不使用签名算法
        header.put("alg", SignatureAlgorithm.HS256.getValue());
//        指定令牌的类型,如果是jwt令牌都统一写为JWT
        header.put("typ","JWT");

//        用于封装jwt的body部分
        Map body = new HashMap();
        body.put("userId", "100");
        body.put("account", "admin");
        body.put("role","admin");

//        使用jjwt提供的API生成jwt令牌
        String jwt = Jwts
                .builder()
                .setHeader(header)
                .setClaims(body)
                .setId("101")
                .signWith(SignatureAlgorithm.HS256,"shuhongfan")
                .compact();
        System.out.println(jwt);


//        使用jjwt提供的API解析jwt令牌
        Jwt parse = Jwts
                .parser()
                .setSigningKey("shuhongfan")
                .parse(jwt);
        Header header1 = parse.getHeader();
        Object body1 = parse.getBody();
        System.out.println(header1);
        System.out.println(body1);
    }

    //生成jwt时使用签名算法生成签名部分----基于RS256签名算法
    @Test
    public void test3() throws Exception {
//        用于封装jwt的header部分
        Map header = new HashMap();
//        不使用签名算法
        header.put("alg", SignatureAlgorithm.RS256.getValue());
//        指定令牌的类型,如果是jwt令牌都统一写为JWT
        header.put("typ","JWT");

//        用于封装jwt的body部分
        Map body = new HashMap();
        body.put("userId", "100");
        body.put("account", "admin");
        body.put("role","admin");

//        使用jjwt提供的API生成jwt令牌
        String jwt = Jwts
                .builder()
                .setHeader(header)
                .setClaims(body)
                .setId("101")
                .signWith(SignatureAlgorithm.RS256,getPriKey())
                .compact();
        System.out.println(jwt);


//        使用jjwt提供的API解析jwt令牌
        Jwt parse = Jwts
                .parser()
                .setSigningKey(getPubKey())
                .parse(jwt);
        Header header1 = parse.getHeader();
        Object body1 = parse.getBody();
        System.out.println(header1);
        System.out.println(body1);
    }


    //获取私钥
    public PrivateKey getPriKey() throws Exception{
        InputStream resourceAsStream =
                this.getClass().getClassLoader().getResourceAsStream("key\\pri.key");
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    //获取公钥
    public PublicKey getPubKey() throws Exception{
        InputStream resourceAsStream =
                this.getClass().getClassLoader().getResourceAsStream("key\\pub.key");
        DataInputStream dis = new DataInputStream(resourceAsStream);
        byte[] keyBytes = new byte[resourceAsStream.available()];
        dis.readFully(keyBytes);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    //生成自己的 秘钥/公钥 对
    @Test
    public void test4() throws Exception{
        //自定义 随机密码,  请修改这里
        String password = "shuhongfan";

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        SecureRandom secureRandom = new SecureRandom(password.getBytes());
        keyPairGenerator.initialize(1024, secureRandom);
        KeyPair keyPair = keyPairGenerator.genKeyPair();

        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();

        FileUtil.writeBytes(publicKeyBytes, "C:\\Users\\shuho\\Documents\\Code\\PinDa-Authority_Demo01\\jwt-demo\\src\\main\\resources\\key\\pub.key");
        FileUtil.writeBytes(privateKeyBytes, "C:\\Users\\shuho\\Documents\\Code\\PinDa-Authority_Demo01\\jwt-demo\\src\\main\\resources\\key\\pri.key");
    }
}
