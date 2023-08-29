package my.app;

import java.sql.*;
import javax.naming.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;

@WebServlet("/hello1")
public class app implements Servlet {

	public void init (ServletConfig sc) throws ServletException{
		//
	}

	public ServletConfig getServletConfig(){
		return null;
	}

	public void service (ServletRequest req, ServletResponse res) throws ServletException,IOException{
		String name = req.getParameter("param1");
		PrintWriter out = res.getWriter();
		out.println("Hello ..."+name);

		try{

		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@tomcat:1521:orcl","usertest","usertest");

		//Context initContext = new InitialContext();
		//Context envContext = (Context) initContext.lookup("java:comp/env");
		//DataSource ds = (DataSource) envContext.lookup("jdbc/UsersDB");
		//Connection con = ds.getConnection();

		Statement st = con.createStatement();
		String sql = "select distinct * from users";
		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			out.println(rs.getString("username") + rs.getString("email"));
		}
		}catch(SQLException e){
			out.println(e);
		}catch (ClassNotFoundException ex) {
			out.println(ex);
		}


	}

	public String getServletInfo() {
		return null;
	}

	public void destroy() {

	}
}
