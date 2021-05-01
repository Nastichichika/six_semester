package com.nastichichika.dao;

import com.nastichichika.model.Abonent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AbonentDAO {
    public static boolean validate(String name,String pass){
        boolean status=false;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/telephone_company", "postgres", "Fjgh2809")) {
            PreparedStatement ps=con.prepareStatement(
                    "select * from abonent where login=? and password=?");
            ps.setString(1,name);
            ps.setString(2,pass);

            ResultSet rs=ps.executeQuery();
            if (rs.next()) status = true;
            else status = false;
        } catch (SQLException io) {
            io.printStackTrace();
        }
        return status;
    }
    public static List<Abonent> listUnpaidAccount() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Abonent> list = new ArrayList<Abonent>();
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/telephone_company", "postgres", "Fjgh2809")) {
            String sql = "Select * \n" +
                    "From abonent inner join account on account.idabonent = abonent.id\n" +
                    "Where account.balance < 0";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(getAbonentFromResultSet(rs));
                System.out.println(getAbonentFromResultSet(rs));
            }

        } catch (SQLException io) {
            io.printStackTrace();
        }
        return list;
    }
    public static List<Abonent> listUnwordAccount() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<Abonent> list = new ArrayList<Abonent>();
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/telephone_company", "postgres", "Fjgh2809")) {
            String sql = "Select * From abonent " +
                    "Where abonent.status != '1'";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                list.add(getAbonentFromResultSet(rs));
                System.out.println(getAbonentFromResultSet(rs));
            }

        } catch (SQLException io) {
            io.printStackTrace();
        }
        return list;
    }
    public static Abonent getAbonentFromResultSet(ResultSet set) throws SQLException {
        Abonent temp = new Abonent();
        temp.setId(set.getString(1));
        temp.setName(set.getString(2));
        temp.setSurname(set.getString(3));
        temp.setPhoneNumber(set.getString(4));
        temp.setLogin(set.getString(5));
        temp.setPassword(set.getString(6));
        return temp;
    }
}