package org.example;

import java.sql.*;
import java.util.Scanner;

public class Student {

    public static Connection connection(){
//        String url = "jdbc:mysql://localhost:port/dbName";
        String url = "jdbc:mysql://localhost:3306/dbjava";
        String username = "root";
        String password = "";

        try{
//            System.out.println("Connected");
            return DriverManager.getConnection(url, username, password);
        }catch (Exception e){
//            System.out.println("Not Connected");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
//        Student.connection();

        Scanner sc = new Scanner(System.in);
        int op;
        String name;
        String gender;
        float score1,score2,score3,total,average;
        char grade;

        do {
            System.out.println("------ MENU --------");
            System.out.println("1. INSERT ");
            System.out.println("2. SELECT ");
            System.out.println("3. SEARCH by ID ");
            System.out.println("4. SEARCH by name ");
            System.out.println("5. UPDATE by ID 123");
            System.out.println("6. DELETE by ID ");
            System.out.println("7. SORT by ID DESC");
            System.out.println("8. SORT by name A-Z");
            System.out.print("Please Enter an option: ");
            op = sc.nextInt();
            switch (op) {
                case 1->{
                    int n;
                    System.out.println("------ INSERT -------");
                    System.out.print("Enter number of sutdents: ");
                    n = sc.nextInt();
                    for (int i = 0; i < n; i++) {
                        System.out.println("----- Student #" + (i + 1) + "-----");
                        System.out.print("Enter Name: ");
                        sc.nextLine();
                        name = sc.nextLine();
                        System.out.print("Enter Gender: ");
                        gender = sc.nextLine();
                        System.out.print("Enter Score 1: ");
                        score1 = sc.nextFloat();
                        System.out.print("Enter Score 2: ");
                        score2 = sc.nextFloat();
                        System.out.print("Enter Score 3: ");
                        score3 = sc.nextFloat();
                        total = score1 + score2 + score3;
                        average = total / 3;
                        grade = (average>=90)?'A' :
                                (average>=80)?'B':
                                (average>=70)?'C':
                                (average>=60)?'D':
                                (average>=50)?'E':'F';
                        String sql = "insert into student (name,gender,score1,score2,score3,total,average,grade) values(?,?,?,?,?,?,?,?)";
                        try{
                            Connection con = connection();
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, name);
                            ps.setString(2,gender);
                            ps.setFloat(3, score1);
                            ps.setFloat(4, score2);
                            ps.setFloat(5, score3);
                            ps.setFloat(6, total);
                            ps.setFloat(7, average);
                            ps.setString(8,grade+"");
                            ps.executeUpdate();

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Insert Successfully");
                }
                case 2->{
                    System.out.println("------ SELECT -------");
                    try{
                        Connection con = connection();
                        String sql = "SELECT * FROM student";//sql
                        Statement st = con.createStatement();// create statement to execute sql
                        ResultSet rs = st.executeQuery(sql);// execute sql
                        while(rs.next()){// get all data from result set
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("Score1 = "+rs.getFloat("score1"));
                            System.out.println("Score2 = "+rs.getFloat("score2"));
                            System.out.println("Score3 = "+rs.getFloat("score3"));
                            System.out.println("Total = "+rs.getFloat("total"));
                            System.out.println("Average = "+rs.getFloat("average"));
                            System.out.println("Grade = "+rs.getString("grade"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 3->{
                    System.out.println("------ SEARCH by ID -------");
                    System.out.print("Enter id to search: ");
                    int id = sc.nextInt();
                    String sql = "SELECT * FROM student WHERE id = " + id;
                    try {
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("Score1 = "+rs.getFloat("score1"));
                            System.out.println("Score2 = "+rs.getFloat("score2"));
                            System.out.println("Score3 = "+rs.getFloat("score3"));
                            System.out.println("Total = "+rs.getFloat("total"));
                            System.out.println("Average = "+rs.getFloat("average"));
                            System.out.println("Grade = "+rs.getString("grade"));
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 4->{
                    System.out.println("------ SEARCH by Name -------");
                    System.out.print("Enter name to search: ");
                    sc.nextLine();
                    String names = sc.nextLine();
                    String sql = "SELECT * FROM student WHERE name LIKE ?" ;
                    try {
                        Connection con = connection();
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, "%"+names+"%");
                        ResultSet rs = ps.executeQuery();

                        while(rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("Score1 = "+rs.getFloat("score1"));
                            System.out.println("Score2 = "+rs.getFloat("score2"));
                            System.out.println("Score3 = "+rs.getFloat("score3"));
                            System.out.println("Total = "+rs.getFloat("total"));
                            System.out.println("Average = "+rs.getFloat("average"));
                            System.out.println("Grade = "+rs.getString("grade"));
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }while(true);
    }
}