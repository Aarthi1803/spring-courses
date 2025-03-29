package com.telusko.SpringJDBCEx.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.telusko.SpringJDBCEx.model.Student;

@Repository
public class StudentRepo {

	private JdbcTemplate jdbc;
	
	public JdbcTemplate getJdbc() {
		return jdbc;
	}
	
	@Autowired
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public void save(Student s) { // In JDBC - we have two methods - executeUpdate() and execute().  executeUpdate() is used for updating, deleting, inserting in table. execute() is used for firing select query
		String sql = "insert into student (rollno, name, marks) values(?,?,?)";
		int rows = jdbc.update(sql, s.getRollno(), s.getName(), s.getMarks());
		System.out.println(rows + " row(s) affected ");
	}

	public List<Student> findAll() {
//		List<Student> students = new ArrayList<>();
//		return students;
		
		String sql = "select * from student";
		
//		Below code is using inner anonymous class of Functional Interface
		
//		RowMapper<Student> mapper = new RowMapper<Student>() {
//  	From this ResultSet, you get one by one data and RowMapper will help you to fetch that data from the ResultSet.

//		The job of mapRow is to take one row at a time from the ResultSet and give it to you.
//  	So basically, mapRow will give you one row at a time or one particular data or object at a time. 
//		rowNum is something which your mapRow uses behind the scene. rowNum - If you want to use it, you can, but we'll not be using this.
//			@Override
//			public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//				Student s = new Student();
//				s.setRollno(rs.getInt("rollno"));
//				s.setName(rs.getString("name"));
//				s.setMarks(rs.getInt("marks"));
//				return s;
//			}
//			
//		};
//		return jdbc.query(sql, mapper); 
		
		
//		Below code is using lambda expression of Functional Interface 

//		In above code remove lines from - new RowMapper<Student>() {  public Student mapRow. 
//		Also delete datatypes ResultSet, int, throws SQLException { }. Add -> for lambda expression
		
//		RowMapper<Student> mapper = (rs, rowNum) -> {
//				Student s = new Student();
//				s.setRollno(rs.getInt("rollno"));
//				s.setName(rs.getString("name"));
//				s.setMarks(rs.getInt("marks"));
//				return s;
//		};
//		return jdbc.query(sql, mapper); 
		
// 		In above  code we have return jdbc.query(sql, mapper); 	"mapper" keyowrd can be replaced adding RowMapper code inside query()
		return jdbc.query(sql, (rs, rowNum) -> {
			Student s = new Student();
			s.setRollno(rs.getInt("rollno"));
			s.setName(rs.getString("name"));
			s.setMarks(rs.getInt("marks"));
			return s;
		}); 
	
	}
	
}
