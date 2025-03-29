OWASP Top 10:

So before jumping into spring security, let's talk about the top ten security risks. Now to understand this, we have a place where we can see the top ten security risks.
Now, when you say top ten is not the only ten, we have a lot of security risks which have to take care of.But then these are top ten. At least solve this problem first.
Now to create this top ten we got something called a OWASP (Open Web Application Security Project). And they create this top ten every four years. So last time they did is in 2021.
And before that it was 2017 and now expecting it to run 25. So till that we have to go for this top ten. And of course I want you to be updated. So if you're watching this video after a few years, make sure that you check the list at that time. 
So let's talk about the first one. So you can see we have top ten. We got the first one which is broken access control. Now basically this this talks about the access control.
Remember when we talked about authentication and authorization. So as a user what are the resources I can access. What are the resources I can modify. So all this controls if those are broken then it's a security risk. 
The next one we have is cryptographic failure. Now cryptography becomes a very important thing in security is because whatever data you send from server to client, from client to server need to be secured. So let's say if a server is basically secured by saying, hey, no one other than the actual users can access this data, but the data is getting passed from the server to client, right? If you are not encrypting it, then anyone can basically attack, which is a man in the middle attack and they can read data. But now let's say I'm using encryption there.
But the question is the encryption technique, which you're using, is it secure. So not every encryption technique is secure. So you have to check which one is secure and which one is not, which is the latest standard. And if you're going for one way encryption which is hashing basically. So we have different techniques for hashing techniques. We have MD5 and Sha and those are deprecated. So are you still using them? So that's something you have to take care of when you are building an application or when you're securing an application.

Next interesting one we have is injection. Now basically your application can be hacked. Now when you say application here we can talk about Desktop application as well.
But the security becomes important when it is a web application because anyone from the world can access it. But what about web application? Anyone can access it from all over the world.
And to secure it, of course, we have to maintain the security, but then even then they can hack with the help of injections. Now let me give an example here. When you talk about SQL. So let's say we use JDBC. And then when you want to let's say search something from the table. Of course you find a Select query. Right now when you say select * from a table, basically you get everything. But then most of the time we pass the values as well based on which you want to filter. So you will apply a Where clause.
But then if you want to use a normal statement in JDBC, what we do is we use a query and then we pass the values at the end by doing plus and then single quote a lengthy string.
select * from user where username = + " ' username' ";

So let's say if you go to a website and say my name is Naveen, my password is this. If you are a real user, of course you will get the access if you're not a real user.
If you're an attacker, you don't have your username password in the database. It will not allow you, right? So what do you think? What will be the query for this? Of course you can simply say select * from table where the username is this where the password is. This important thing is if this query fires and if you give, it gives you any row that means the user is valid.
But what if you don't receive any rows? That means the user is invalid. So ultimately it's all about true and false right? So what if as a user or as an attacker, I'm passing a username as Naveen and by saying Naveen or 1=1 So basically it's all comes down to and false. And that's a SQL injection. And one way to prevent it is with the help of prepared statement.
Because in prepare statement we takes the value with the type. That's how you can basically, uh, secure it. But then SQL is not the only injection we have.We have different injections as well. We got no SQL injection OS commands injection, ORM injection as well. So there are different types of injection attack which can happen and you have to take care of it.

Uh, next is about how you are designing your application. It's not about implementation but design how well you're designing your application. Is it secure?

Uh, next we have is security misconfiguration. whenever you use any particular library or a server or a framework or a tool, basically most of these tools, frameworks and libraries gives you a default configuration, and sometimes we don't change them. And since its default configuration, everyone knows about those configuration. So as an attacker, simply I can if I know which tool you are using, which server you are using. Basically, I can just go there and I can hack with the default configuration. Even if you have used ten tools in your project and you have secured and you have changed the configuration for the nine tools. That one tool will create mess for your project. So you have to make sure that you change the configurations.

Next is vulnerable and outdated components. We do have this tendency of using external tools, let's say DBMs or a library. And if they are deprecated, if they are not supported by the company itself, don't use them. If they are not in support, if they don't get security updates, it can also lead to attacks.

Then we have authentication failures with the username passwords. We do have a tendency of storing the passwords directly in the database. I used to do that in my first initial projects, and then I realized not a good idea, right? So you have to always make sure that you encrypt your password when you want to store in the database, not two way encryption. Basically, once you store the password in the database, no one should be able to decrypt it. So hashing works there.

Also missing the multifactor authentication. So don't just go with one auto authentication example. Most of the websites now they ask you for username password but they also send you SMS to verify. They also send you app notification to verify. So you can have a multi layered or multi factor authentication enabled.

And then we have more security issues here regarding the data integrity. Then we have logging as well. Now this is interesting is because we all log our activities on the website, right?
So we do write the log statements and we have seen that in AOP as well. Every time you call a function maintain the log. But there's one issue with the log. In fact there are two issues.
The first issue is most of the people avoid logging is because it affects your performance, especially if it is a performance critical application. So we skip the log statements.
But the problem is, if someone is attacking your application, how will we know that there's an attack? So if you have a log maintained properly, you can actually read the logs.
But it also creates a second problem. The problem is if you have a log, there are thousands of activities going on at the same time, right? And if you have a log for everything, how will you know which one is a security risk or which one is a security attack which is happening. So you have to make sure that you maintain your log properly and observe your logs, which is very important. let's check the logs, keep monitoring your logs. It will reveal how many attacks are going on.

And also we have a server side request forgery. There was client side scripting, but also now we have server side request forgery. What if you are sending some data from server to client and the data itself is malicious, which is you are sending from the server to client. So basically you are getting your client in risk. So we have to mitigate all these things.

And as I mentioned before, these are not the only risk we have. We have lot of risk. But these are top ten. And I would encourage you to go through this top ten once again. Read it and you will get more idea on it. Now let's see how exactly we can implement that with spring security.


 
