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




      eventClick: function (arg){
        //있는 일정 클릭시,
        console.log("#등록된 일정 클릭#");
        console.log(arg.event);

        if (confirm('Are you sure you want to delete this event?')){
          arg.event.remove()
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