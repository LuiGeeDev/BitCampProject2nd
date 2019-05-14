﻿package kr.co.groot.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.service.AdminAction;
import kr.co.groot.service.AdminSearchByInputAction;
import kr.co.groot.service.MyPageDeleteAction;
import kr.co.groot.service.AdminModifyAction;
import kr.co.groot.service.AdminModifyOkAction;
import kr.co.groot.service.MyPageMyAction;
import kr.co.groot.service.MyPageSetInfoAction;
import kr.co.groot.service.MyPageSetPictureAction;
import kr.co.groot.service.MyPageSetPwdAction;
import kr.co.groot.service.MyPageUpdateInfoAction;
import kr.co.groot.service.MyPageUpdatePictureAction;
import kr.co.groot.service.MypagePwdUpdateAction;
import kr.co.groot.service.StaffInfoAction;

@WebServlet(description = "마이페이지 작업 처리", urlPatterns = { "/mypage/*", "/info" })
public class MypageController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  public MypageController() {
  }

  protected void doProcess(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String requestUri = request.getRequestURI();
    String contextPath = request.getContextPath();
    String urlCommand = requestUri.substring(contextPath.length());

    Action action = null;
    ActionForward forward = null;
    if (urlCommand.equals("/mypage")) {
      action = new MyPageMyAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/setinfo")) {
      action = new MyPageSetInfoAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/setpassword")) {
      action = new MyPageSetPwdAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/setpicture")) {
      action = new MyPageSetPictureAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/admin")) {
      action = new AdminAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/updateInfo")) {
      action = new MyPageUpdateInfoAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/updatePwd")) {
      action = new MypagePwdUpdateAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/updatePicture")) {
      action = new MyPageUpdatePictureAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/modify")) {
      action = new AdminModifyAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/modifyOk")) {
      action = new AdminModifyOkAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/search")) {
      action = new AdminSearchByInputAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/mypage/delete")) {
      action = new MyPageDeleteAction();
      forward = action.execute(request, response);
    } else if (urlCommand.equals("/info")) {
      action = new StaffInfoAction();
      forward = action.execute(request, response);
    }

    if (forward != null) {
      if (forward.isRedirect()) {
        response.sendRedirect(forward.getPath());
      } else {
        RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
        dis.forward(request, response);

      }
    }
  }

  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response);
  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doProcess(request, response);
  }

}
