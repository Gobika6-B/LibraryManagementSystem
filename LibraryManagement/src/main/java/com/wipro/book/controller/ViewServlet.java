
package com.wipro.book.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.wipro.book.bean.BookBean;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ViewServlet
 */
@WebServlet("/ViewServlet")
public class ViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		BookBean b=(BookBean)session.getAttribute("book");
		out.print("<html><body>");
		out.print("<h3>Book Information</h3>");
		out.print("Book Title:"+b.getBookName());
		out.print("<br><br>Author Name:"+b.getAuthor().getAuthorName());
		out.print("<br><br>Author Contact:"+b.getAuthor().getContactNo());
		out.print("<br><br>Book Price:"+b.getCost());
		out.print("<br><br>Book ISBN:"+b.getIsbn());
		out.print("</body></html>");
		}
}

