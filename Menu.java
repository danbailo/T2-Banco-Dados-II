/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T2_BDII;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author daniel
 */
public final class Menu {
    Menu(MySQL database, Connection con, String serverName, String mydatabase,
            String username, String password) throws SQLException{
        database.create_tables(con);
        options(database, con, serverName, mydatabase, username, password);       
    }
    
    void options(MySQL database,Connection con, String serverName, String mydatabase,
            String username, String password) throws SQLException{
        Scanner input = new Scanner(System.in);        
        while(true){
            System.out.println("\n\nEscolha a opção desejada");
            System.out.println("(1) Inserir Dados");
            System.out.println("(2) Realizar Consultas");
            System.out.println("(3) Criar Tabelas");
            System.out.println("(4) Deletar Tabelas");
            System.out.println("----------------------------------------");
            System.out.println("(5) Inserir Dados Automaticamente");
            System.out.println("(6) Realizar Consultas Automaticamente");
            System.out.println("----------------------------------------");            
            System.out.println("(7) Sair");
            switch (input.nextInt()) {
                case 1:
                    input.nextLine();
                    switch (Insert.insert_into()) {
                        case 1:
                            database.insert_vend(con, Insert.insert_vendedor());
                            break;
                        case 2:
                            database.insert_tel(con, Insert.insert_telefone());                            
                            break;
                        case 3:
                            database.insert_end(con, Insert.insert_endereco());
                            break;
                        default:
                            break;
                    }
                    break;

                case 2:
                    input.nextLine();
                    database.show_tables(con);
                    System.out.println("Digite a tabela na qual você que deseja realizar uma consulta:");
                    String table = input.nextLine().toUpperCase();
                    System.out.print("Entre com a consulta: ");
                    String query = input.nextLine().toUpperCase();
                    System.out.println();
                    database.query(con, table, query);
                    break;
                case 3:
                    database.create_tables(con);
                    break;
                case 4:
                    database.delete_table(con);
                    break;                    
                case 5:         
                    input.nextLine();
                    switch (Insert.insert_into()) {
                        case 1:
                            database.insert_vend(con, new Vendedor(null, "JOAO","M","JOAO@IG.COM","98547-6",76234.78,88346.87,5756.90));
                            database.insert_vend(con, new Vendedor(null, "DANIEL","M","DANIEL@GMAIL.COM","1123-7",4785.78,66478.87,6887.90));
                            database.insert_vend(con, new Vendedor(null, "JOSUE","M","JOSUE@TERRA.COM","91212-2",89667.78,57654.87,5755.90));
                            database.insert_vend(con, new Vendedor(null, "GIULIANO","M","GIULIANO@HOTMAIL.COM","21346-5",45000.50, 84523.22,4500.89));
                            database.insert_vend(con, new Vendedor(null, "LUCAS","M","LUCAS@TERRA.COM","523400-0",9850.80, 77889.20, 8570.58));
                            database.insert_vend(con, new Vendedor(null, "THIAGO","M","THIAGO@GMAIL.COM","51233-5",8505.98, 87951.52, 4789.52));
                            database.insert_vend(con, new Vendedor(null, "LEANDRO","M","LEANDRO@IG.COM","88657-5",5779.78, 446886.87,8965.90));
                            database.insert_vend(con, new Vendedor(null, "ANA","F","ANA@GLOBO.COM","75658-5",8769.78,6685.87,6664.90));
                            database.insert_vend(con, new Vendedor(null, "EDUARDO","M","EDUARDO@IG.COM","11457-5",9119.05,126836.95,12954.90));
                            database.insert_vend(con, new Vendedor(null, "CLARA","F","CLARA@IG.COM","99754-7",676545.78,77544.87,578665.90));
                            break;
                        case 2:
                            database.insert_tel(con, new Telefone(null, "CEL","9955331","1"));
                            database.insert_tel(con, new Telefone(null, "COM","6574565","3"));
                            database.insert_tel(con, new Telefone(null, "CEL","8864566","2"));
                            database.insert_tel(con, new Telefone(null, "CEL","5557798","7"));
                            database.insert_tel(con, new Telefone(null, "COM","6765768","1"));
                            database.insert_tel(con, new Telefone(null, "RES","5676765","6"));
                            database.insert_tel(con, new Telefone(null, "CEL","5765547","1"));
                            database.insert_tel(con, new Telefone(null, "CEL","8865645","7"));
                            database.insert_tel(con, new Telefone(null, "RES","7555446","7"));
                            database.insert_tel(con, new Telefone(null, "CEL","5788654","3"));
                            database.insert_tel(con, new Telefone(null, "CEL","7865644","6"));
                            database.insert_tel(con, new Telefone(null, "RES","5754644","8"));
                            database.insert_tel(con, new Telefone(null, "RES","1231231","9"));
                            database.insert_tel(con, new Telefone(null, "RES","5751235","10"));
                            database.insert_tel(con, new Telefone(null, "RES","2987512","8"));
                            database.insert_tel(con, new Telefone(null, "COM","1574565","3"));
                            break;
                        case 3:
                            database.insert_end(con, new Endereco(null,"RUA A","CENTRO","BELO HORIZONTE","MG","4"));
                            database.insert_end(con, new Endereco(null,"RUA B","CENTRO","RIO DE JANEIRO","RJ","1"));
                            database.insert_end(con, new Endereco(null,"RUA C","JARDINS","SAO BONIFACIO","ES","3"));
                            database.insert_end(con, new Endereco(null,"RUA B","ESTACIO","RIO DE JANEIRO","RJ","8"));
                            database.insert_end(con, new Endereco(null,"RUA Z","CRUZEIRO","BELO HORIZONTE","MG","2"));
                            database.insert_end(con, new Endereco(null,"RUA X","FLAMENGO","RIO DE JANEIRO","RJ","7"));
                            database.insert_end(con, new Endereco(null,"RUA Z","CRUZEIRO","BELO HORIZONTE","MG","5"));
                            database.insert_end(con, new Endereco(null,"RUA X","CENTRO","JOSE BONIFACIO","SP","6"));
                            database.insert_end(con, new Endereco(null,"RUA X","CENTRO","JOSE BONIFACIO","SP","10"));
                            database.insert_end(con, new Endereco(null,"RUA H","CENTRO","RIO BONIFACIO","MG","9"));
                            break;                            
                        default:
                            break;
                    }                    
                    break;
                case 6:
                    System.out.println("\nConsulta 1: Todos os vendedores.");database.query(con, "VENDEDOR", "SELECT * FROM VENDEDOR");                    
                    System.out.println("\nConsulta 2: Endereço dos vendedores.");database.query(con, "ENDERECO", "SELECT * FROM ENDERECO");                    
                    System.out.println("\nConsulta 3: Telefone dos vendedores.");database.query(con, "TELEFONE", "SELECT * FROM TELEFONE");                    
                    System.out.println("\nConsulta 4: Mostra apenas os telefones que são celulares.");database.query(con, "TELEFONE", "SELECT * FROM TELEFONE WHERE TIPO = 'CEL'");
                    System.out.println("\nConsulta 5: Mostra o endereço dos vendedores que moram numa cidade que tem 'Bonifacio' como ultimo nome.");database.query(con, "ENDERECO", "SELECT * FROM ENDERECO WHERE CIDADE LIKE '%BONIFACIO'");
                    break;
                case 7:
                    input.close();
                    database.close_connection(con);
                    System.exit(0);
                default:
                    break;
            }
        }        
    }
}
