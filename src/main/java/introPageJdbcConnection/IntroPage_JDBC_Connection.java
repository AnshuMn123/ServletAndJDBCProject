package introPageJdbcConnection;

import introClass.Intro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntroPage_JDBC_Connection {
    static Intro intro;

    public IntroPage_JDBC_Connection ( Intro i ) {
        intro = i;
    }

    public String setInSql ( ) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null, ps1 = null;
        ResultSet rs = null;
        try {
            System.out.println ( intro.getId ( ) );
            Class.forName ( "com.mysql.cj.jdbc.Driver" );
            con = DriverManager.getConnection ( "jdbc:mysql://localhost:3306/Intro", "root", "Anshu@123" );
            ps = con.prepareStatement ( "insert into stud(id, name) values(" + intro.getId ( ) + ", '" + intro.getName ( ) + "')" );

            int i = ps.executeUpdate ( );
            if ( i > 0 ) {
                System.out.println ( "pass" );
            } else {
                System.out.println ( "fail" );
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println ( e );
        } finally {
            ps.close ( );
        }

        try {
            ps1 = con.prepareStatement ( "select * from stud" );
            rs = ps1.executeQuery ( );

            StringBuilder result = new StringBuilder ( );
            while (rs.next ( )) {
                result.append ( rs.getInt ( "id" ) + " " + rs.getString ( "name" ) );
                result.append ( "\n" );
            }

            return result.toString ( );
        } catch (SQLException e) {
            System.out.println ( e );
        } finally {
            con.close ( );
            ps1.close ( );
        }
        return null;
    }
}
