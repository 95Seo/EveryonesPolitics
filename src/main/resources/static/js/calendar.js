document.addEventListener('DOMContentLoaded', function() {
    // calendar element 취득
    var calendarEl = document.getElementById('calendar');


    // new FullCalendar.Calendar(대상 DOM객체 , {속성: 속성값, 속성2: 속성값2..})
    var calendar = new FullCalendar.Calendar(calendarEl, {
      
      //헤더에 표시할 툴바
      headerToolbar:{
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,listWeek'
      },
      
      expandRows : true,             //화면에 맞게 높이 재설정
      nowIndicator:true,             //현재 시간 마크
      initialView: 'dayGridMonth',   //초기 로드 될때 보이는 캘린더 화면( 기본 설정: 달)
      locale:'ko',                   //한국어 설정

        eventDidMount: function (info) {
            tippy(info.el,{

                theme : 'tomato',
                content: '<div class="CalendarTitle" style="background : '+info.event.backgroundColor+';" ><p>'
                    + info.event.title + '</p></div>'
                    + '<div class="CalendarInfo"><p><strong>후보자:</strong>' + info.event.extendedProps.groupname + '</p>'
                    + '<div class ="CalendarContent"><p><strong>위치:</strong>' + info.event.extendedProps.location + '</p>'
                    + '<p><strong>내용:</strong>' + info.event.extendedProps.description +  '</p></div>'
                    + '<p><strong>클릭시 링크로 바로가기</strong></p>',
                allowHTML: true,
            })
        },


        eventClick: function (arg){
            //있는 일정 클릭시,
            if(arg.event.url){
                location.href = arg.event.url;
            }
        },
        editable: true,
        dayMaxEvents: true, // 너무 많은 이벤트가 있을떄 "more" 링크 생기게 해주는 부분
        events: function(info, successCallback, failureCallback){
                $.ajax({
                  type: "get",
                  url: "calendar.do?method=data",
                  dataType: 'json',


                    success: function(info) {
                      successCallback(info);
                    }


                }); //ajax end
          }, //events:function end

      //===================== ajax데이터 불러올 부분 ========================//
    });
    calendar.render();
  });