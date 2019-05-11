<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="messageList" value="${requestScope.messageList}" />
<c:set var="userId" value="${sessionScope.staff.id}" />  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/top-bottom.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">
<title>유니넷</title>
<link rel="stylesheet" href="css/message.css">
</head>
<body>
  <jsp:include page="/common/top.jsp" flush="false" />
  <div class="container">
    <div class="message-box">
      <h4 class="message-title">쪽지함</h4>
      <!-- 쪽지 있는 만큼 담기 -->
      <c:forEach var="message" items="${messageList}">
        <div class="message">
          <span class="hidden">${message.id}</span>
          <div class="message-sender">     
          <c:choose>
            <c:when test="${message.receiverId == userId}">
              <span class="sender">${message.senderName}</span>
            </c:when>
            <c:otherwise>
              <span class="sender">${message.receiverName}</span>
            </c:otherwise>
        </c:choose>
            <span class="time">${message.timeFormat}</span>
          </div>
          <p class="message-preview">${message.content}</p>  
        </div>
      </c:forEach>
    </div>
    <div class="message-paper"></div>
  </div>
  <jsp:include page="/common/bottom.jsp" flush="false" />
  <script>
    $(function() {
      const fromMain = ${requestScope.fromMain};
      const messageId = ${requestScope.id};
      if (fromMain) {
        $.ajax({
          url: "message/call",
          dataType : "html",
          data : {
            id : messageId
          },
          success : function(data) {
            $('.message-paper').html(data);
          },
          error : function(xhr) {
            console.log(xhr.status);
            alert("쪽지를 불러오지 못했습니다.");
          }
        })
      }
    }); 
  
    $(".message").click(function() {
      const messageId = $(this).children(".hidden").text();
      $.ajax({
        url: "message/call",
        dataType : "html",
        data : {
          id : messageId
        },
        success : function(data) {
          $('.message-paper').html(data);
        },
        error : function(xhr) {
          console.log(xhr.status);
          alert("쪽지를 불러오지 못했습니다.");
        }
      })
    });
    <c:if test="${requestScope.fromMain}">
      $(".message").trigger("click");
    </c:if>
  </script>
</body>
</html>