/*
Miles: SQL query Recommedation

Group 3 :

1.> Naman Agarwal(1401CS28)
2.> Mayank Goyal(1401CS25)
3.> Vipin Mavi(1401CS48)
4.> Hitesh Golchha(1401CS56)
5.> Dnyaneshwar Shendudwadkar(1401CS43)

*/


import java.util.*;
import java.sql.*;

class Query_structure
{
	String query;
	int frequency;
	Query_structure()
    {
		query = "";
		frequency = 0;
	}
	Query_structure(String query,int frequency)
    {
		this.query = query;
		this.frequency = this.frequency;
	}
}

class mini_project
{
    public static List<Query_structure> sorting(List<Query_structure> qr)
    {
        Collections.sort(qr, new Comparator<Query_structure>() {
        @Override
        public int compare(Query_structure qr1, Query_structure qr2)
        {
            if(qr2.frequency > qr1. frequency)
                return 1;
            return 0;
        }
    	});
        
        return qr;
    }
    
	public static void main(String[] args) 
    {
        
        System.out.println("***********************************************************************************************");
        System.out.println("This is Sql Query Recommendation based on past or differnt user queries.When a user inserts a succesful and differnt query it is stored into the database of queries and will be used to recommend next time.");
        System.out.println("***********************************************************************************************");
        
        List<Query_structure> qr=new ArrayList<Query_structure>();
        String [] queries_initial = new String[13];
        
		queries_initial[0] = "CREATE TABLE Students (sid CHAR(9),name VARCHAR(20),login CHAR(20),age INTEGER,gpa REAL);";
		queries_initial[1] = "ALTER TABLE Students DROP login;";
		queries_initial[2] = "ALTER TABLE Students ADD phone CHAR(10);";
		queries_initial[3] = "NSERT INTO Students VALUES (‘536880’,’Mayank’,’mayank22’,21,8.0);";
        queries_initial[4] = "DELETE FROM Students WHERE name=‘Mayank’;";
        queries_initial[5] = "INSERT INTO Students VALUES (‘420420’,’Danny’,’danny007’,20,9.0);";
		queries_initial[6] = "UPDATE Students S SET S.age=S.age+1 WHERE S.sid = ‘536880’;";
		queries_initial[7] = "ALTER TABLE Students ADD CONSTRAINT check_age CHECK(age >= 18);";
		queries_initial[8] = "SELECT COUNT(*) FROM Students;";
		queries_initial[9] = "SELECT * FROM Students;";
		queries_initial[10] = "SELECT name FROM Students WHERE gpa = 8.0 GROUP BY name;";
		queries_initial[11] = "DROP TABLE Students;";
		queries_initial[12] = "CREATE TRIGGER trigger_1 BEFORE INSERT ON Students FOR EACH ROW BEGIN :NEW.MARK := (:NEW.T1 + :NEW.T2 + :NEW.T3)/3; END;";
		
        for(int i=0;i<13;i++)
        {
            Query_structure temp = new Query_structure(queries_initial[i].toLowerCase(),1);
            qr.add(temp);
        }

		qr = sorting(qr);
		Scanner in = new Scanner(System.in);
        
        while(true)
        {
            System.out.println("Insert your query(or partial for recommendation)");
            String test = in.nextLine();
            test = test.toLowerCase();
            int n = test.length();
            boolean correct_syntax= false;
            if(test.charAt(n-1) == ';')
            {
                // cheacking ;
                try
                {  
                    Class.forName("com.mysql.jdbc.Driver");  
                    Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","password");  
                    //here students is database name, root is username and password  
                    Statement stmt=con.createStatement();    
                    stmt.execute(test);
                    // to print result of the executed query
                    
                    /*ResultSet rs=stmt.executeQuery("select * from faculty"); 
                    ResultSetMetaData rsmd = rs.getMetaData();
                    int NumOfCol = rsmd.getColumnCount();
                    for(int i = 1; i <= NumOfCol; i++)
                    {
                        System.out.print(rsmd.getColumnName(i)+ "\t");
                    }
                    System.out.println();
                    while (rs.next())
                    {

                        for(int i = 1; i <= NumOfCol; i++)
                        {
                        System.out.print(rs.getString(i)+ "\t");
                        }
                        System.out.println();
                    }*/
                        
                    con.close(); 
                    correct_syntax = true;
                    System.out.println("query executes successfully");
                }
                catch(Exception e)
                {
                    System.out.println(e); 
                }  
            }  
            else
            {
                System.out.println("******************************");
                System.out.println("Recommended queries :-");
                int list_size = qr.size();
                int i=0;
                for(i=0;i<list_size;i++)
                {
                    Query_structure test_query = qr.get(i);
                    String sample = test_query.query;
                    int j=sample.indexOf(test.charAt(0));
                    if(j==0)
                    {
                        System.out.println("\t" + sample);
                    }
                }
                System.out.println("******************************");
                // checking 
            }
            if(correct_syntax)
            {
                int flag=0;
                    
                for(int i=0;i<qr.size();i++)
                {
                    Query_structure insert = qr.get(i);
                    String check = insert.query;
                    if(test.equals(check))
                    {
                        insert.frequency++;
                        flag = 1;
                        break;
                    }
                }
                if(flag==0)
                {
                    Query_structure insert_query = new Query_structure(test,1);
                    qr.add(insert_query);
                }
            }
            //sorting list
            qr = sorting(qr);
        }
	}
}
