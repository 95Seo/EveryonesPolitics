package com.hustar.edu.vote.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

//@DinamicInsert 어노테이션을 붙이면 null값을 제외하고 전송한다.
@Data
@NoArgsConstructor()
@Entity
public class tb_user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int idx;

    private int vote;

    private String name;

    @Column(name="username", length = 100)  // null 여부와 길이제한
    private String username;

    @Column(name="password", length = 100)  // 123456 -> 해쉬(비밀번호 암호화)
    private String password;

    @Column(name="email", length = 100)
    private String email;

    @Column(name="nickname", length = 100)
    private String nickname;

    @Column(name="profile_img", length = 500)
    private String profile_img = "https://vote-web-bucket.s3.ap-northeast-2.amazonaws.com/USER/PROFILE/DEFAULT/vote_img.png";

    // 1 - Y (default), 2 - N
    @ColumnDefault("Y")
    private String use_yn = "Y";

    private String gender;

    private String age_range;

    // Enum 사용 (Enum 안에 정의된 문자들만 쓸수 있기 때문에 안정성이 올라간다.)
    // DB는 RoleType이라는게 없다.
    private String role;

    @CreationTimestamp  // 시간이 자동 입력
    private Date sysregdate;

    private Timestamp sysmoddate;

    @Builder
    public tb_user(String name, String username, String password, String email, String nickname, String gender, String age_range, String use_yn, String profile_img) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.gender = gender;
        this.age_range = age_range;
    }
}
