import java.sql.Connection;
import java.sql.DriverManager;



public class ConDB
{
	protected static String dbClassName = "com.hxtt.sql.access.AccessDriver";
	protected static String dbUrl = "jdbc:Access:///e://db_demo/db_student.accdb";
	protected static String dbUser = "";
	protected static String dbEml = "";

 public static Connection getConnection()
 {
  Connection con = null;

  String path = "jdbc:Access:///" + System.getProperty("user.dir") + "/db_student.accdb";

	try {
		Class.forName(dbClassName).newInstance();
		con = DriverManager.getConnection(path, dbUser, dbPwd);
		System.out.println("success");
	} catch (Exception e) {
		System.out.println("fail");
		e.printStackTrace();
	}
  return con;
 }

}
