package ww.rent005;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages= {"ww.rent005.sys.mapper"})
public class Rent005Application {

    public static void main(String[] args) {
        SpringApplication.run(Rent005Application.class, args);
    }

}
