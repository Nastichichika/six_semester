import java.sql.*;
/*
7. Система Телефонная станция.
Администратор
    1. подключение Абонентов.
    2. просмотреть список неоплаченных Счетов
    3. заблокировать Абонента.
Абонент
    1. выбрать одну или несколько из предоставляемых Услуг.
    2. оплачивает Счет за разговоры и Услуги.

*/
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/telephone_company", "postgres", "456789")) {
            /*Statement st = con.createStatement();
            String sql = "INSERT INTO Tariff (nameTariff, price) VALUES ('Max_Fun', 120)";
            st.execute(sql);
            st.close();*/
            Statement st = con.createStatement();
            String sql = "SELECT * FROM account";
            ResultSet result = st.executeQuery(sql);
            while(result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2) + " " + result.getInt(3));
            }
        } catch (SQLException io) {
            io.printStackTrace();
        }

    }

}
