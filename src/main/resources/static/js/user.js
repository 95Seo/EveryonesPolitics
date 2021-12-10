let index = {

    init:function () {
        $("#btn-save").on("click",()=>{
            this.save();
        });
        $("#btn-login").on("click",()=>{
            this.login();
        });
    },
    
    save:function () {
        // alert("user의 save함수 호출됨");

        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        }
        
        console.log(data);
        
        $.ajax().done().fail(); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
    },

    login:function () {
        // alert("user의 login함수 호출 됨");

        let data = {
            username: $("#username").val(),
            password: $("#password").val()
        };

        $.ajax({
            type: "POST",
            url: "/api/user/login",
            data: JSON.stringify(data), // JSON문자열, http body 데이터
            contentType: "application/json;charset=utf-8",  // body데이터가 어떤 타입인지 (MIME)
            dataType:"json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경
        }).done(function (resp){
            alert("로그인이 완료 되었습니다.");
            // console.log(resp)
            location.href="/main";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
}

index.init();