

package com.wipro.book.controller;

import java.io.IOException;

import com.wipro.book.bean.BookBean;
import com.wipro.book.dao.AuthorDAO;
import com.wipro.book.service.Administrator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		if(operation.equals("AddBook")) {
			String result=addBook(request);
			if(result.equals("SUCCESS")) {
				response.sendRedirect("Menu.html");
			}
			else if(result.equals("INVALID")) {
				response.sendRedirect("Invalid.html");
			}
			else if(result.equals("FAILURE")) {
				response.sendRedirect("Failure.html");
			}
		}
		else if(operation.equals("Search")) {
			String isbn=request.getParameter("isbn");
			BookBean b=viewBook(isbn);
			if(b==null) {
				     response.sendRedirect("Invalid.html");
			}
			else {
				HttpSession session=request.getSession();
				session.setAttribute("book",b);
				RequestDispatcher rd=request.getRequestDispatcher("ViewServlet");
				rd.forward(request, response);
			}
		}
	}
	public String addBook(HttpServletRequest request) {
		String isbn=request.getParameter("isbn");
		String bookName=request.getParameter("bookName");
		String bookType=request.getParameter("bookType");
		String authorName=request.getParameter("author");
		String cost=request.getParameter("cost");
		BookBean b=new BookBean();
		b.setIsbn(isbn);
		b.setBookName(bookName);
		b.setBookType(bookType.charAt(0));
		b.setCost(Float.parseFloat(cost));
		b.setAuthor(new AuthorDAO().getAuthor(authorName));
		String result=new Administrator().addBook(b);
		return result;
	}
   public BookBean viewBook(String isbn) {
	  return new Administrator().viewBook(isbn);
   }
}

