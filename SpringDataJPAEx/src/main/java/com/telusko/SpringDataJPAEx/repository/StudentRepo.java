package com.telusko.SpringDataJPAEx.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.telusko.SpringDataJPAEx.model.Student;


//@Repository
//public interface StudentRepo extends JpaRepository<Student, Integer> {
//	
//}

/* 
We are extending  particular interface "JpaRepository". We got this interface because we have added the library for Spring Data JPA.
Now it will just ask you for two things. First, which class type you are working with. So I'm working with Student type of class.
And what is a type of primary key. Now every table needs a primary key. And if you go back to our Student class rollno is primary key.
And the type of rollno is integer. So you have to mention that here integer.

public interface StudentRepo extends JpaRepository<Student, Integer>{}
 */

/*********************************************************************/
@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	
//	Using List because we might get names and marks of many students like there can be many Navin and many students might have marks greater than 70 so return via List
	
//	@Query("select s from Student s where s.name = ?1 ") // This is called as (JPAQL) JPA Query Language.
//	List<Student> findByName(String name);
	
	List<Student> findByName(String name);
	List<Student> findByMarks(int marks);
	List<Student> findByMarksGreaterThan(int marks);
//	List<Student> findByMarksGreaterThanEqual(int marks);
	
}

/* The difference between  SQL and JPAQL is, in SQL we use table names and column names in query.
In JPAQL we use class name and class property names in query.

select s from Student s where s.name = ?1 
- This question mark will be replaced by name. But then you might be having multiple question marks.
So let's say if you have a complex query and then you have 2 or 3 question marks, how do you differentiate that this particular question mark 
is for the first variable. The other question mark is for the second variable.
In that case you have to also give a number here which is "?1".
So what we are saying is this question mark "?1" will be replaced by the first parameter which is name.
If you are passing any other parameters, it will be for the second question mark if you specify that like ?2 etc.


If you want to have a method which might not be there by default in the JPA repository, in that case we have to specify @Query annotation.
@Query("select s from Student s where s.name = ?1") 
*/


/* 
(JPAQL) JPA Query Language and (DSL) Domain Specific Language:
// @Query("select s from Student s where s.name = ?1") 
   List<Student> findByName (String name);
   
If we do not mention @Query annotation then also it still works. So what JPA does is it uses "Domain Specific Language", which is DSL. 
So using this DSL, it creates certain methods for you behind the scene. So it will look for the column name or the property names. 
We have a property called "name". We have a property called "marks". So it will create certain queries behind the scene for you. 
You can use those methods directly.

But yes if you specify something else here, something like this findBysname, then it will not work because JPA has no idea about it. 
But JPA knows what is name.
But in case if you want something extra which is not provided by JPA, in that case you have to write @Query annotation. 
So you have to specify the query by yourself.

List<Student> findByName (String name);
List<Student> findByMarks(int marks);
But most of the time JPA will have the methods for you. But you have to make sure that the method name starts with findBy 
because that's the standard. And then you have to mention the variable name which is "name" or "marks". 
Or you can also specify ID which is the primary key.

So basically (DSL) Domain Specific Language in JPA will have certain queries for you pre-created (already created), 
which you can use with the help of method names, but you have to make sure that it is used along with "findBy".

And then you can mention the variable name or whatever operators you want to use 
like GreaterThan / LesserThan etc - List<Student> findByMarksGreaterThan(int marks);

*/

/*********************************************************************/

