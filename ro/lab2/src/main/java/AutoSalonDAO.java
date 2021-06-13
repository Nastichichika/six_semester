import java.sql.*;

public class AutoSalonDAO {
    public static void createProducer(String name_create) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/lab2database", "postgres", "456789")) {
            String sql = "INSERT INTO procedure (name) VALUES (?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name_create);
            st.executeUpdate();
        } catch (SQLException io) {
            io.printStackTrace();
        }
    }
    public static void deleteProducer(int id_delete) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/lab2database", "postgres", "456789")) {
            String sql = "DELETE FROM procedure WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_delete);
            st.executeUpdate();
        } catch (SQLException io) {
            io.printStackTrace();
        }
    }
    public static void readProcedure(int id_find) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/lab2database", "postgres", "456789")) {
            String sql = "select * from procedure where id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_find);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2));
            }
        } catch (SQLException io) {
            io.printStackTrace();
        }
    }
    public static void updateProcedure(int id_update, String name_update) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/lab2database", "postgres", "456789")) {
            String sql = "UPDATE procedure SET name=? WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name_update);
            st.setInt(2, id_update);
            st.executeUpdate();

        } catch (SQLException io) {
            io.printStackTrace();
        }
    }
    public static void createBrand(String name_create, int id_producer) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/lab2database", "postgres", "456789")) {
            String sql = "INSERT INTO brand (name, id_producer) VALUES (?, ?);";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, name_create);
            st.setInt(2, id_producer);
            st.executeUpdate();
        } catch (SQLException io) {
            io.printStackTrace();
        }
    }
    public static void deleteBrand(int id_delete) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/lab2database", "postgres", "456789")) {
            String sql = " DELETE FROM brand WHERE id = ?;";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_delete);
            st.executeUpdate();
        } catch (SQLException io) {
            io.printStackTrace();
        }
    }
    public static void readBrand(int id_find) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/lab2database", "postgres", "456789")) {
            String sql = "select * from brand where id=?";

            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id_find);
            ResultSet result = st.executeQuery();
            while(result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString(2));
            }
        } catch (SQLException io) {
            io.printStackTrace();
        }
    }
    public static void updateBrand(int id_update, String name_update, int id_producer) throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        try(Connection con  = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/lab2database", "postgres", "456789")) {
            String sql = "UPDATE brand SET name=?, id_producer=? WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(2, id_producer);
            st.setString(1, name_update);
            st.setInt(3, id_update);
            st.executeUpdate();

        } catch (SQLException io) {
            io.printStackTrace();
        }
    }

    public static Producer getProducerFromResultSet(ResultSet set) throws SQLException {
        Producer temp = new Producer();
        temp.setId(set.getInt(1));
        temp.setName(set.getString(2));
        return temp;
    }
    public static Brand getBrandFromResultSet(ResultSet set) throws SQLException {
        Brand temp = new Brand();
        temp.setId(set.getInt(1));
        temp.setName(set.getString(2));
        temp.setId_producer(set.getInt(3));
        return temp;
    }

}