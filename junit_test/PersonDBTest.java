package database;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Assert;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
public class PersonDBTest {
	private static Connection con = null;
	 private PersonDB personDB = null;
	 @BeforeClass
	 public static void setUpBeforeClass() throws Exception
	 {
	  con = ConDB.getConnection();//创建连接
	 }
	 @AfterClass
	 public static void tearDow nAfterClass() throws Exception
	 {
	  con.close();
	 }
	 @Before
	 public void setUp() throws Exception
	 {
	  personDB = new PersonDB();
	 }

	 @Test
	 public void testInsert()
	 {
	  Person person = new Person();//创建Person对象,插入..

	  person.setname("chensi");
	  person.setemail("123456@qq.com");

	  personDB.insert(person);

	  Person person2 = new Person();

	  person2 = personDB.getById(this.getMaxId());//这里用到了获取当前最大ID的方法.这个方法我设定为private类
	//型.是帮助方法..数据库的ID我设定为自增..每次加1..
	  this.Assert(person, person2);
	 // personDB.delete(this.getMaxId());
	  //最后删除这条数据...测试不要给数据库增加垃圾数据.
	 }
	 @Test
	 public void testGetById()//这里的测试和上面测试Insert方法的代码一样..这里我也有点不确定..你也可以在这里

	//重写一个getbyid方法.但是我觉得没必要..
	 {
	  Person person = new Person();
	  person.setname("chensi");
	  person.setemail("123456@qq.com");
	  personDB.insert(person);
	  Person person2 = new Person();
	  person2 = personDB.getById(this.getMaxId());
	  this.Assert(person, person2);
	//  personDB.delete(this.getMaxId());
    //  personDB.delete(getMaxId());
	 }
	 @Test
	 public void testDelete()//思想是,先插入,然后删除 最后看是否为空
	 {
	  Person person = new Person();
	  person.setname("han");
	  person.setemail("111@qq.com");
	  personDB.insert(person);
	  Person person2 = new Person();
	  person2 = personDB.getById(this.getMaxId());
	  this.Assert(person, person2);
	//  personDB.delete(this.getMaxId());
	  Person person3 = new Person();
	  person3 = personDB.getById(person2.getId());
//	  Assert.assertNull(person3.getname());//注意,断言的时候要断言具体的那个属性值.而不能断言person3为空.
	 // System.out.println(person2.getId());
	  //Assert.assertNull(personDB.getById(person2.getId()));
	  //Assert.assertNotNull(personDB.getById(this.getMaxId()));
	 }
	 @Test
	 public void testUpdate()
	 {
	  Person person = new Person();
	  person.setname("1111");
	  person.setemail("pass@qq.com");
	  personDB.insert(person);
	  Person person2 = new Person();
	  person2 = personDB.getById(this.getMaxId());
	  this.Assert(person, person2);
	  Person person3 = new Person();
	  person3.setId(person2.getId());
	  person3.setname("2222");
	  person3.setemail("word@qq.com");
	 // System.out.println(person3.getId());
	  personDB.update(person3);
	  Person person4 = new Person();
	  person4 = personDB.getById(this.getMaxId());
	  this.Assert(person3, person4);
	//  personDB.delete(person2.getId());
	 }
	 private int getMaxId()//帮助方法.所以设置为private
	 {
	  int id = 0;
	  try
	  {
	   String sql = "select max(id) from person ";
	   PreparedStatement st = con.prepareStatement(sql);
	   ResultSet rs = st.executeQuery();
	   if (rs.next())
	   {
	    id = rs.getInt(1);
	   }
	  } catch (Exception ex)
	  {
	   ex.printStackTrace();
	  }
	  return id;
	 }
	 private void Assert(Person person1, Person person2)//帮助代码.起到提取代码的作用.
	 {
	  assertEquals(person1.getname(), person2.getname());
	  assertEquals(person1.getemail(), person2.getemail());
	  assertEquals(person1.getAge(), person2.getAge());
	 }
}
