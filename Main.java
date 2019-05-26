package T2_BDII;

import java.sql.SQLException;
import java.sql.Connection;

public class Main{

    public static void main(String[] args) throws SQLException{
        MySQL database = new MySQL();

        String serverName = "localhost"; // Conexão
        String mydatabase = "T2_BDII"; // Nome do banco de dados
        String username = "daniel"; // Nome do usuário do banco
        String password = "123456789"; // Senha do usuário

        Connection con = database.connect(serverName, mydatabase, username, password);
        new Menu(database, con, serverName, mydatabase, username, password);
    }
}
