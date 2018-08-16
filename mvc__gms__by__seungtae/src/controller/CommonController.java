package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.receiver.Path;

@WebServlet("/common.do")
public class CommonController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	enum Resources{
		CONTEXT,CSS,JS,IMG
	}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int i = 0;
		for(Resources r : Resources.values()) {
			request.getSession()
			.setAttribute(
					r.toString().toLowerCase(),
					request.getContextPath()+
						((i==0)?
						"":
						Path.RESOURCES.toString()
						+r.toString().toLowerCase()
						)
					);
			i++;
		}
		request.getRequestDispatcher(
				Path.WEBPATH.toString()
				+request.getServletPath()
					.substring(1, request.getServletPath().indexOf("."))
				+Path.MAIN.toString())
		.forward(request, response);
	}
}
