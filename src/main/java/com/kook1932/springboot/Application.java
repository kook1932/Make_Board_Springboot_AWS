package com.kook1932.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing
// @EnableJpaAuditing를 사용했기 때문에 최소 하나의 @Entity 클래스가 필요한데 @WebMvcTest에는 없다.
// 따라서 SpringBootApplication과 분리한다.
@SpringBootApplication
// 스프링부트의 자동 설정, 스프링 Bean 읽기와 생성을 모두 자동으로 설정된다.
// 이 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트 최상단에 위치
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        // SpringApplication.run으로 인해 내장 WAS를 실행한다.
        // 내장 WAS를 실행하면 항상 서버에 톰캣을 설치할 필요가 없고,
        // 스프링 부트로 만들어진 jar 파일로 실행하면 된다.
    }
}
