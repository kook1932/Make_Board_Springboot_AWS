package com.kook1932.springboot.config.auth.dto;

import com.kook1932.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // SessionUser에는 인증된 사용자 정보만 필요하다.
    // name, email, picture만 필드로 선언한다.

    /* User 클래스를 사용하면 안되는 이유
    1. User 클래스를 세션에 저장하려고하면 User 클래스에 '직렬화'를 구현하지 않았다는 에러가 뜬다.
    2. 하지만 직렬화 코드를 넣는것은 생각해 볼 것이 많다.
    3. 그 이유는 User 클래스가 '엔터티'이기 때문이다.
    4. 엔터티 클래스에는 언제 다른 엔터티와 관계가 형성될지 모른다.
    5. 만약 자식 엔터티를 갓고 있다면 직렬화 대상에 자식들까지 포함되니 성능 이슈, 부수 효과가 발생한다.
    6. 따라서 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 이후 운영 및 유지보수 때 많은 도움이 된다.

    * 자바 직렬화?
    1. 자바 직렬화란 자바 시스템 내부에서 사용되는 객체 또는 데이터를 외부의 자바 시스템에서도 사용할 수 있도록 바이트(byte) 형태로
    데이터 변환하는 기술과 바이트로 변환된 데이터를 다시 객체로 변환하는 기술(역직렬화)을 아울러서 이야기합니다.
    2. JVM의 메모리에 상주(힙 또는 스택)되어 있는 객체 데이터를 바이트 형태로 변환하는 기술과
    직렬화된 바이트 형태의 데이터를 객체로 변환해서 JVM으로 상주시키는 형태를 같이 이야기합니다.
    3. 자바 직렬화는 사용하는 이유 : 자바 직렬화 형태의 데이터 교환은 자바 시스템 간의 데이터 교환을 위해서 존재한다.
    참고 : https://woowabros.github.io/experience/2017/10/17/java-serialize.html

     */
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
