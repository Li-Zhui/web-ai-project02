package com.itheima;

import com.example.EnableHeaderConfig;
import com.example.HeaderConfig;
import com.example.MyImportSelector;
import com.example.TokenParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


//自动配置方案二：@Import导入
//@Import(TokenParser.class) //导入普通类
//@Import(HeaderConfig.class) //导入配置类
//@Import(MyImportSelector.class) //导入ImportSelector实现类
//@EnableHeaderConfig   //启用自定义的配置类

@Import(HeaderConfig.class)
@SpringBootApplication
public class SpringbootWebConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebConfigApplication.class, args);
    }

}
