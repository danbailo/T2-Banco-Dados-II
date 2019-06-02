package T2_BDII;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class MySQL {
    private static String status;

    public Connection connect(String serverName, String mydatabase, String username, String password) throws SQLException{
        Connection connection = null;
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
            String url = "jdbc:mysql://" + serverName + ":3306/" + mydatabase;
            connection = DriverManager.getConnection(url, username, password); 
            status = (connection != null) ? "Conectado ao Banco de Dados com sucesso!" : "Não foi possivel realizar conexão!";
            System.out.println(status);
        }
        catch (ClassNotFoundException err){
            System.out.println("O driver expecificado não foi encontrado!");
            System.out.println(err.getMessage()+"\n");
            System.exit(0);            
        }
        catch (SQLException err){
            System.out.println("Não foi possivel conectar ao Banco de Dados!");
            System.out.println(err.getMessage()+"\n");
            System.exit(0);
        }
        return connection;
    }
    
    public void create_tables(Connection con){
        Statement stnt;
        try{
            stnt = (Statement) con.createStatement();
            stnt.execute("CREATE TABLE IF NOT EXISTS VENDEDOR(\n" +
            "	IDVENDEDOR INT NOT NULL AUTO_INCREMENT,\n" +
            "	NOME VARCHAR(30) NOT NULL,\n" +
            "	SEXO ENUM('M','F') NOT NULL,\n" +
            "	EMAIL VARCHAR(50) UNIQUE,\n" +
            "	CPF VARCHAR(15) UNIQUE,\n" +
            "	JANEIRO FLOAT(10,2) NOT NULL,\n" +
            "	FEVEREIRO FLOAT(10,2) NOT NULL,\n" +
            "	MARCO FLOAT(10,2) NOT NULL,\n" +      
            "   PRIMARY KEY(IDVENDEDOR)\n" +        
            ")");
            
        stnt.execute("CREATE TABLE IF NOT EXISTS TELEFONE(\n" +
            "	IDTELEFONE INT NOT NULL AUTO_INCREMENT,\n" +
            "	TIPO ENUM('COM','RES','CEL'),\n" +
            "	NUMERO VARCHAR(10),\n" +
            "	ID_VENDEDOR INT,\n" +     
            "   PRIMARY KEY(IDTELEFONE),\n" +     
            "	FOREIGN KEY(ID_VENDEDOR) REFERENCES VENDEDOR(IDVENDEDOR)\n" +
            ")");
        
        stnt.execute("CREATE TABLE IF NOT EXISTS ENDERECO(\n" +
            "	IDENDERECO INT NOT NULL AUTO_INCREMENT,\n" +
            "	RUA VARCHAR(30) NOT NULL,\n" +
            "	BAIRRO VARCHAR(30) NOT NULL,\n" +
            "	CIDADE VARCHAR(30) NOT NULL,\n" +
            "	ESTADO CHAR(2) NOT NULL,\n" +
            "	ID_VENDEDOR INT UNIQUE,\n" +      
            "   PRIMARY KEY(IDENDERECO),\n" +      
            "	FOREIGN KEY(ID_VENDEDOR) REFERENCES VENDEDOR(IDVENDEDOR)\n" +
            ")");     
        
            System.out.println("Tabelas criadas com sucesso!");
            stnt.close();
        }
        catch(SQLException err){
            System.out.println("Erro ao criar tabela!");
            System.out.println(err.getMessage()+"\n");
        }
    }
    
    public void delete_tables(Connection con){
        Statement stnt;
        try{
            stnt = (Statement) con.createStatement();
            stnt.execute("DROP TABLE IF EXISTS ENDERECO");            
            stnt.execute("DROP TABLE IF EXISTS TELEFONE");
            stnt.execute("DROP TABLE IF EXISTS VENDEDOR");
//            System.out.println("Tabelas deletadas com sucesso!");
            stnt.close();
        }
        catch(SQLException err){
            System.out.println("Erro ao deletar tabela!");
            System.out.println(err.getMessage()+"\n");
        }
    }    
    
    public void insert_vend(Connection con, Vendedor vend) throws SQLException{
        
        String sql = "insert into "
                + "VENDEDOR(NOME,SEXO,EMAIL,CPF,JANEIRO,FEVEREIRO,MARCO)"
                + " values(?,?,?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
    
        stmt.setString(1, vend.getNome());
        stmt.setString(2, vend.getSexo());
        stmt.setString(3, vend.getEmail());
        stmt.setString(4, vend.getCpf());
        stmt.setDouble(5, vend.getJaneiro());
        stmt.setDouble(6, vend.getFevereiro());     
        stmt.setDouble(7, vend.getMarco());  
        
        try{
            stmt.execute();
//            System.out.println("Dados gravados com sucesso!\n");
        }
        catch(SQLException err){
            System.out.println("Erro ao inserir os dados!");
            System.out.println(err.getMessage()+'\n');
        }
        stmt.close();
    }
    
    public void insert_tel(Connection con, Telefone tel) throws SQLException{
        
        String sql = "insert into "
                + "TELEFONE(TIPO,NUMERO,ID_VENDEDOR)"
                + " values(?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
    
        stmt.setString(1, tel.getTipo());
        stmt.setString(2, tel.getNumero());
        stmt.setLong(3, tel.getId_vendedor());
        
        try{
            stmt.execute();
//            System.out.println("Dados gravados com sucesso!\n");
        }
        catch(SQLException err){
            System.out.println("Erro ao inserir dado!");
            System.out.println(err.getMessage()+'\n');
        }
        stmt.close();
    }
    
    public void insert_end(Connection con, Endereco end) throws SQLException{
        
        String sql = "insert into "
                + "ENDERECO(RUA,BAIRRO,CIDADE,ESTADO,ID_VENDEDOR)"
                + " values(?,?,?,?,?)";
        PreparedStatement stmt = con.prepareStatement(sql);
    
        stmt.setString(1, end.getRua());
        stmt.setString(2, end.getBairro());
        stmt.setString(3, end.getCidade());
        stmt.setString(4, end.getEstado());
        stmt.setLong(5, end.getId_vendedor()); 
        
        try{
            stmt.execute();
//            System.out.println("Dados gravados com sucesso!\n");
        }
        catch(SQLException err){
            System.out.println("Erro ao inserir dado!");
            System.out.println(err.getMessage()+'\n');
        }
        stmt.close();
    }        
    public void query1(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM VENDEDOR");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                    "ID: "+rs.getString("IDVENDEDOR")+"\n"
                    + "Nome: "+rs.getString("NOME")+"\n"
                    +"Sexo: "+rs.getString("SEXO")+"\n"
                    +"Email: " +rs.getString("EMAIL")+"\n"
                    +"CPF: "+rs.getString("CPF")+"\n"
                    +"Janeiro: R$ "+rs.getDouble("JANEIRO")+"\n"
                    +"Fevereiro: R$ "+rs.getDouble("FEVEREIRO")+"\n"
                    +"Março: R$ "+rs.getDouble("MARCO"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    }
    
    public void query2(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM TELEFONE");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                    "ID: "+rs.getString("IDTELEFONE")+"\n"
                    + "Tipo: "+rs.getString("TIPO")+"\n"
                    +"Número: "+rs.getString("NUMERO")+"\n"
                    +"ID do Vendedor: " +rs.getString("ID_VENDEDOR"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    }   
    
    public void query3(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM ENDERECO");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                          "ID: "+rs.getLong("IDENDERECO")+"\n"
                        + "Rua: "+rs.getString("RUA")+"\n"
                        + "Bairro: "+rs.getString("BAIRRO")+"\n"
                        + "Cidade: "+rs.getString("CIDADE")+"\n"
                        + "Estado: "+rs.getString("ESTADO")+"\n"        
                        + "ID do Vendedor: " +rs.getLong("ID_VENDEDOR"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    }     
    
    public void query4(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT NOME, JANEIRO, FEVEREIRO, MARCO, (JANEIRO+FEVEREIRO+MARCO) AS 'TOTAL', (JANEIRO+FEVEREIRO+MARCO)*0.25 AS 'DESCONTO', (JANEIRO+FEVEREIRO+MARCO)/3 AS 'MEDIA' FROM VENDEDOR");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                    "Nome: "+rs.getString("NOME")+"\n"
                    + "Janeiro: R$ "+rs.getDouble("JANEIRO")+"\n"
                    + "Fevereiro: R$ "+rs.getDouble("FEVEREIRO")+"\n"
                    + "Março: R$ "+rs.getDouble("MARCO")+"\n"
                    + "Total: R$ "+rs.getDouble("TOTAL")+"\n"  
                    + "Desconto: R$ "+rs.getDouble("DESCONTO")+"\n"  
                    + "Média: R$ "+rs.getDouble("MEDIA"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    }

    public void query5(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM VENDEDOR WHERE IDVENDEDOR = (SELECT ID_VENDEDOR FROM ENDERECO WHERE BAIRRO LIKE 'JARDINS')");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                    "Nome: "+rs.getString("NOME")+"\n"
                    + "Sexo: "+rs.getString("SEXO"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    }    

    public void query6(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT * FROM VENDEDOR V INNER JOIN TELEFONE T ON V.IDVENDEDOR = T.ID_VENDEDOR INNER JOIN ENDERECO E ON V.IDVENDEDOR = E.ID_VENDEDOR WHERE V.IDVENDEDOR = 10");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                      "ID: "+rs.getString("V.IDVENDEDOR")+"\n"
                    + "Nome: "+rs.getString("V.NOME")+"\n"
                    + "Sexo: "+rs.getString("V.SEXO")+"\n"
                    + "Email: " +rs.getString("V.EMAIL")+"\n"
                    + "CPF: "+rs.getString("V.CPF")+"\n"
                    + "Janeiro: R$ "+rs.getDouble("V.JANEIRO")+"\n"
                    + "Fevereiro: R$ "+rs.getDouble("V.FEVEREIRO")+"\n"
                    + "Março: R$ "+rs.getDouble("V.MARCO")+"\n"
                    + "ID Telefone: "+rs.getString("T.IDTELEFONE")+"\n"
                    + "Tipo: "+rs.getString("T.TIPO")+"\n"
                    + "Número: "+rs.getString("T.NUMERO")+"\n"
                    + "ID do Vendedor: " +rs.getString("T.ID_VENDEDOR")+"\n"
                    + "ID Endereço: "+rs.getLong("E.IDENDERECO")+"\n"
                    + "Rua: "+rs.getString("E.RUA")+"\n"
                    + "Bairro: "+rs.getString("E.BAIRRO")+"\n"
                    + "Cidade: "+rs.getString("E.CIDADE")+"\n"
                    + "Estado: "+rs.getString("E.ESTADO")+"\n"        
                    + "ID do Vendedor: " +rs.getLong("E.ID_VENDEDOR"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    }  

    public void query7(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT V.NOME, V.SEXO FROM VENDEDOR V INNER JOIN ENDERECO E ON V.IDVENDEDOR = E.ID_VENDEDOR WHERE V.SEXO = 'M' AND E.CIDADE LIKE '%BONIFACIO'");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                    "Nome: "+rs.getString("V.NOME")+"\n"
                    + "Sexo: "+rs.getString("V.SEXO"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    }          

    public void query8(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT TIPO, NUMERO FROM TELEFONE WHERE TIPO LIKE 'CEL'  AND NUMERO LIKE '9%' OR NUMERO LIKE '%5'");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                    "Tipo: "+rs.getString("TIPO")+"\n"
                    + "Número: "+rs.getString("NUMERO"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    } 

    public void query9(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT NOME, FEVEREIRO FROM VENDEDOR WHERE FEVEREIRO < (SELECT AVG(FEVEREIRO) FROM VENDEDOR) AND SEXO ='F'");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                    "Nome: "+rs.getString("NOME")+"\n"
                    + "Fevereiro: R$ "+rs.getDouble("FEVEREIRO"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    }
    

    public void query10(Connection con) throws SQLException{
        PreparedStatement stmt = con.prepareStatement("SELECT V.NOME, COUNT(T.NUMERO) FROM VENDEDOR V INNER JOIN TELEFONE T ON V.IDVENDEDOR = T.ID_VENDEDOR GROUP BY V.NOME HAVING COUNT(T.NUMERO)>=2 ORDER BY V.NOME DESC");
        try{   
            ResultSet rs = stmt.executeQuery();
            if(!rs.next()){System.out.printf("A tabela está vazia!\n"); return;}
            rs.beforeFirst();
            while (rs.next()){
                System.out.println(
                    "Nome: "+rs.getString("V.NOME")+"\n"
                    + "Quantidade de telefones: "+rs.getInt("COUNT(T.NUMERO)"));
                System.out.println();
            }
        }
        catch(SQLException err){
            System.out.println(err.getMessage()+"\n");
        }
        stmt.close();            
    }
    
    public void show_tables(Connection con) throws SQLException{
        DatabaseMetaData meta = con.getMetaData();
        ResultSet rs1 = meta.getTables(null, null, null,new String[] {"TABLE"});
        System.out.println("\nTabelas existentes no banco de dados");
        while (rs1.next()){
            System.out.println(rs1.getString("TABLE_NAME"));
        }
        System.out.println();
    }
    
    public void close_connection(Connection con) throws SQLException{
        con.close();
    }
}
