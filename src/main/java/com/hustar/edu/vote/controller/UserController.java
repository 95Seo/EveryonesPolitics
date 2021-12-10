package com.hustar.edu.vote.controller;

//import com.hustar.edu.vote.auth.PrincipalDetail;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hustar.edu.vote.dto.KakaoProfile;
import com.hustar.edu.vote.dto.OAuthToken;
import com.hustar.edu.vote.dto.tb_user;
import com.hustar.edu.vote.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/**를 허용
// 그냥 주소가 /이면 index.jsp허용
// static이하에 있는 /js/**, /css/**, /images/**허용

@Slf4j
@Controller
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private UserService userSerivce;

    @Value("${cos.key}")
    private String cosKey;

    private final AuthenticationManager authenticationManager; // @Autowired

    @GetMapping("/auth/kakao/callback")
    public String kakaoCallback(String code, HttpServletRequest request) {    // Data를 리턴해주는 컨트롤러 함수

        //POST 방식으로 key = value 데이터를 요청 (카카오 쪽으로)

        // http 요청을 편하게 할 수 있다. Retrofit2, OKHttp, RestTemplate 라이브러리가 있다.
        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id","17d0622174b2b4cd77314e16dd2d9dde");
        params.add("redirect_uri","http://localhost:8080/auth/kakao/callback");
        params.add("code", code);

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest =
                new HttpEntity<>(params, headers);

        // Http 요청하기-Post 방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response = rt.exchange(
                "Https://kauth.kakao.com/oauth/token",
                HttpMethod.POST,
                kakaoTokenRequest,
                String.class
        );

        // 객체에 담아볼 것이다. Gson, Json Simple, ObjectMapper 라이브러리가 있다.
        ObjectMapper objectMapper = new ObjectMapper();

        OAuthToken oauthToken = null;
        try {
            oauthToken = objectMapper.readValue(response.getBody(), OAuthToken.class);
        } catch(JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println("카카오 엑세스 토큰 : " + oauthToken.getAccess_token());

        // http 요청을 편하게 할 수 있다. Retrofit2, OkHttp, RestTemplate 라이브러리가 있다.
        RestTemplate rt2 = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization","Bearer " + oauthToken.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = new HttpEntity<>(headers2);

        //Http 요청하기 - Post 방식으로 - 그리고 response 변수의 응답 받음.
        ResponseEntity<String> response2 = rt2.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                kakaoProfileRequest,
                String.class
        );

        ObjectMapper objectMapper2 = new ObjectMapper();
        KakaoProfile kakaoProfile = null;
        try {
            kakaoProfile = objectMapper2.readValue(response2.getBody(),KakaoProfile.class);
        } catch (JsonMappingException e) {
            log.info("Error1 : " + e.getMessage());
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            log.info("Error2 : " + e.getMessage());
            e.printStackTrace();
        }

        // User 오브젝트 : username, password, email
        System.out.println("카카오 아이디(번호) : " + kakaoProfile.getId());
        System.out.println("카카오 이메일 : " + kakaoProfile.getKakao_account().getEmail());
        System.out.println("카카오 연령대 : " + kakaoProfile.getKakao_account().getAge_range());

        System.out.println("모정서버 유저네임 : " + kakaoProfile.getKakao_account().getEmail() + "_" + kakaoProfile.getId());
        System.out.println("모정서버 이메일 : " + kakaoProfile.getKakao_account().getEmail());

        System.out.println("모정서버 패스워드 : " + cosKey);

        String maskedName = "";    // 마스킹 이름
        String firstName = "";    // 성
        String lastName = "";    // 이름
        int lastNameStartPoint;    // 이름 시작 포인터

        if(!kakaoProfile.getProperties().getNickname().equals("") || kakaoProfile.getProperties().getNickname() != null){
            if(kakaoProfile.getProperties().getNickname().length() > 1){
                firstName = kakaoProfile.getProperties().getNickname().substring(0, 1);
                lastNameStartPoint = kakaoProfile.getProperties().getNickname().indexOf(firstName);
                lastName = kakaoProfile.getProperties().getNickname().substring(lastNameStartPoint + 1, kakaoProfile.getProperties().getNickname().length());

                String makers = "";

                for(int i = 0; i < lastName.length(); i++){
                    makers += "*";
                }

                lastName = lastName.replace(lastName, makers);
                maskedName = firstName + lastName;
            }else{
                maskedName = "*";
            }
        }

        tb_user kakaoUser = tb_user.builder()
                .name(kakaoProfile.getProperties().getNickname())
                .username(kakaoProfile.getKakao_account().getEmail()+"_"+kakaoProfile.getId())
                .password(cosKey)
                .email(kakaoProfile.getKakao_account().getEmail())
                .nickname(maskedName)
                .age_range(kakaoProfile.getKakao_account().getAge_range())
                .build();

        // 가입자 혹은 비 가입자 체크해서 처리
        tb_user originUser = userSerivce.searchUser(kakaoUser.getUsername());

        if(originUser.getUsername() == null) {
            System.out.println("기존 회원이 아닙니다. 자동 회원가입을 진행합니다.");
            userSerivce.insertUser(kakaoUser);
        }

        System.out.println("자동 로그인을 진행합니다.");
//        HttpSession session = request.getSession();
//        System.out.println("kakaoUser : "+kakaoUser.getUsername());
//        session.setAttribute("user",userSerivce.login(kakaoUser.getUsername()));
//        System.out.println("session : "+userSerivce.login(kakaoUser.getUsername()));

        // 로그인 처리
        // 이이디와 패스워드로, Security가 알아볼수 있는 token 객체로 변경한다.
        // authenticationManager에 token을 넘기면 UserDetailsService가 받아 처리하도록 한다.
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));

        // 실제 SecurityContext에 authentication 정보를 등록한다.
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("로그인 성공");

//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        PrincipalDetail userDetails = (PrincipalDetail)principal;
//        String username = ((PrincipalDetail) principal).getUsername();
//        String password = ((PrincipalDetail) principal).getPassword();
//
//        System.out.println("username : " + username);
//        System.out.println("password : " + password);

        return "redirect:/";
    }

}
