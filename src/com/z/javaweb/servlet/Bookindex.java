package com.z.javaweb.servlet;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Bookindex
 */
@WebServlet("/Bookindex")
public class Bookindex extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Bookindex() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ArrayList<String> bookNames = new ArrayList<String>();
		Connection con;
		try {
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/bookdb", "postgres", "1");
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("select *from bookInfo");
			while(rs.next()) {
				bookNames.add(rs.getString("bookName"));
			}
			rs.close();
			con.close();
		}catch(Exception e) {
			bookNames.add(e.toString());
		}
		
		StringBuilder strBuilder = new StringBuilder();
		Iterator<String> it = bookNames.iterator();
		while(it.hasNext()) {
			strBuilder.append(it.next());
			
		}
		response.getWriter().append(strBuilder.toString());
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
