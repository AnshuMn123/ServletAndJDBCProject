package servletFile;
import introClass.Intro;

import introPageJdbcConnection.IntroPage_JDBC_Connection;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/form")
public class IntroPage_Servlet extends HttpServlet {
    @Override
    public void service ( ServletRequest req, ServletResponse res ) throws IOException {
        PrintWriter pw = res.getWriter ();
        String id = (String)req.getParameter ( "id" );
        String name = (String) req.getParameter ( "name" );
        System.out.println (id + " " + name);

        Intro i = new Intro ();
        i.setId ( id );
        i.setName ( name );

        IntroPage_JDBC_Connection ip = new IntroPage_JDBC_Connection (i);
        try {
            pw.println (ip.setInSql ());
        } catch (SQLException e) {
            System.out.println (  e );
        }
    }
}
