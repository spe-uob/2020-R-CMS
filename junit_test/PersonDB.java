package database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class PersonDB {
	private ConDB db = new ConDB();
	 public void insert(Person person)
	 {
	  Connection con = null;
	  try
	  {
	   con = db.getConnection();
	   String sql = "insert into person(name,email,url) values (?,?,?)";
	 //  String sql = "INSERT INTO person(name,email,url) VALUES(NULL," + person.getname()
	//			+ ",'" + person.getemail() + ","+ person.geturl()+")";

	   PreparedStatement st = con.prepareStatement(sql);//现在都使用PreparedStatement 不用statement了
	   st.setString(1, person.getname());
	   st.setString(2, person.getemail());
	   st.setString(3, person.geturl());
	   st.executeUpdate();
	  } catch (Exception ex)
	  {
	   ex.printStackTrace();
	  } finally
	  {
	   try
	   {
	    con.close();
	   // st.close();

	   } catch (SQLException e)
	   {
	    e.printStackTrace();
	   }
	  }

	 }

	 public Person getById(int id)
	 {
	  Connection con = null;
	  Person person = new Person();

	  try
	  {
	   con = db.getConnection();

	   String sql = "select * from person where id = "+id;

	   PreparedStatement st = con.prepareStatement(sql);

	   ResultSet rs = st.executeQuery();

	   if(rs.next())
	   {
	    person.setId(id);
	    person.setname(rs.getString("name"));
	    person.setemail(rs.getString("email"));
	    person.seturl(rs.getString("url"));
	   }
	  }
	  catch (Exception ex)
	  {
	   ex.printStackTrace();
	  }
	  finally
	  {
	   try
	   {
	    con.close();
	   } catch (SQLException e)
	   {
	    e.printStackTrace();
	   }
	  }
	  return person;
	 }

	 public void delete(int id)
	 {
	  Connection con = null;

	  try
	  {
	   con = db.getConnection();

	   String sql ="delete person where id = "+id;

	   PreparedStatement st = con.prepareStatement(sql);

	   st.executeUpdate();
	  }
	  catch (Exception ex)
	  {
	   ex.printStackTrace();
	  }
	  finally
	  {
	   try
	   {
	    con.close();
	   } catch (SQLException e)
	   {
	    e.printStackTrace();
	   }
	  }
	 }

	 public void update(Person person)
	 {
	  Connection con = null;

	  try
	  {
	   con = db.getConnection();

	   String sql = "update person set name=? ,email=? ,url=? where id = ?";
	//	String sql = "UPDATE person SET name = '" + person.getname()
	//	    	+ "', email = '" + person.getemail()
	//	    	+ "', url = '" + person.geturl()+
	//	  "' WHERE id = '" + person.getId() + "'";
		//+ "'  WHERE number = '" + id + "'";
		PreparedStatement st = con.prepareStatement(sql);

	   st.setString(1, person.getname());
	   st.setString(2, person.getemail());
	   st.setString(3, person.geturl());
	   st.setInt(4, person.getId());

	   st.executeUpdate();


	  } catch (Exception ex)
	  {
	   ex.printStackTrace();
	  }
	  finally
	  {
	   try
	   {
	    con.close();
	   } catch (SQLException e)
	   {
	    e.printStackTrace();
	   }
	  }
	 }
}
