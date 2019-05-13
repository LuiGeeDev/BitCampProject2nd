package kr.co.groot.service;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.groot.action.Action;
import kr.co.groot.action.ActionForward;
import kr.co.groot.dao.PostDao;
import kr.co.groot.dto.Post;
import kr.co.groot.page.Paginator;


public class BoardListAction implements Action{

  @Override
  public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
    ActionForward forward = null;
    try {
      PostDao postDao = new PostDao();
      Paginator paginator = new Paginator();
      int pageNumber = Integer.parseInt(request.getParameter("page"));
      int boardType = Integer.parseInt(request.getParameter("boardtype"));
      String option = request.getParameter("option");
      String word = request.getParameter("word");
      if (word == null) {
        word = "";
      }
      String boardName = (boardType == 1) ? "공지사항" : "자유게시판";
      
      List<Post> list = null;
      int page = 1;
      switch (option) {
      case "default":
      case "count":
        list = postDao.getPostByPage(pageNumber, boardType);
        page = paginator.getPageNumber(boardType);
        break;
      case "title":
      case "content":
      case "all":
        list = postDao.getPostByOption(pageNumber, boardType, option, word);
        page = paginator.getPageNumber(option, word, boardType);
        break;
      }
      
      System.out.println("페이지 수: " + page);
      
      request.setAttribute("boardName", boardName);
      request.setAttribute("boardType", boardType);
      request.setAttribute("list", list);
      request.setAttribute("option", option);
      request.setAttribute("word", word);
      request.setAttribute("currentPage", pageNumber);
      request.setAttribute("pages", page);
      
      forward = new ActionForward();
      forward.setRedirect(false);
      forward.setPath("/WEB-INF/views/board/boardlist.jsp");
      
    } catch (SQLException e) {  
      System.out.println(e.getMessage());
    } catch (NamingException e) {
      System.out.println(e.getMessage());
    } 
    return forward;
  }
}
