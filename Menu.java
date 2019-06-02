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
        database.delete_tables(con);
        database.create_tables(con);
        try{
            insert(database, con);
            System.out.println("Dados inseridos com sucesso!");
//            System.out.println("Foram inseridos 10 tuplas na tabela VENDEDOR\n");
        }
        catch(Exception err){
            System.out.println(err.getMessage());
        }
        options(database, con, serverName, mydatabase, username, password);       
    }
    
    void insert(MySQL database,Connection con) throws SQLException{
        database.insert_vend(con, new Vendedor("JOAO","M","JOAO@IG.COM","98547-6",76234.78,88346.87,5756.90));
        database.insert_vend(con, new Vendedor("DANIEL","M","DANIEL@GMAIL.COM","1123-7",4785.78,66478.87,6887.90));
        database.insert_vend(con, new Vendedor("JOSUE","M","JOSUE@TERRA.COM","91212-2",89667.78,57654.87,5755.90));
        database.insert_vend(con, new Vendedor("GIULIANO","M","GIULIANO@HOTMAIL.COM","21346-5",45000.50, 84523.22,4500.89));
        database.insert_vend(con, new Vendedor("LUCAS","M","LUCAS@TERRA.COM","523400-0",9850.80, 77889.20, 8570.58));
        database.insert_vend(con, new Vendedor("THIAGO","M","THIAGO@GMAIL.COM","51233-5",8505.98, 87951.52, 4789.52));
        database.insert_vend(con, new Vendedor("LEANDRO","M","LEANDRO@IG.COM","88657-5",5779.78, 446886.87,8965.90));
        database.insert_vend(con, new Vendedor("ANA","F","ANA@GLOBO.COM","75658-5",8769.78,6685.87,6664.90));
        database.insert_vend(con, new Vendedor("EDUARDO","M","EDUARDO@IG.COM","11457-5",9119.05,126836.95,12954.90));
        database.insert_vend(con, new Vendedor("CLARA","F","CLARA@IG.COM","99754-7",676545.78,77544.87,578665.90));
        database.insert_tel(con, new Telefone("CEL","9955331",1));
        database.insert_tel(con, new Telefone("COM","6574565",3));
        database.insert_tel(con, new Telefone("CEL","8864566",2));
        database.insert_tel(con, new Telefone("CEL","5557798",7));
        database.insert_tel(con, new Telefone("COM","6765768",1));
        database.insert_tel(con, new Telefone("RES","5676765",6));
        database.insert_tel(con, new Telefone("CEL","5765547",1));
        database.insert_tel(con, new Telefone("CEL","8865645",7));
        database.insert_tel(con, new Telefone("RES","7555446",7));
        database.insert_tel(con, new Telefone("CEL","5788654",3));
        database.insert_tel(con, new Telefone("CEL","7865644",6));
        database.insert_tel(con, new Telefone("RES","5754644",8));
        database.insert_tel(con, new Telefone("RES","1231231",9));
        database.insert_tel(con, new Telefone("RES","5751235",10));
        database.insert_tel(con, new Telefone("RES","2987512",8));
        database.insert_tel(con, new Telefone("COM","1574565",3));
        database.insert_end(con, new Endereco("RUA A","CENTRO","BELO HORIZONTE","MG",4));
        database.insert_end(con, new Endereco("RUA B","CENTRO","RIO DE JANEIRO","RJ",1));
        database.insert_end(con, new Endereco("RUA C","JARDINS","SAO BONIFACIO","ES",3));
        database.insert_end(con, new Endereco("RUA B","ESTACIO","RIO DE JANEIRO","RJ",8));
        database.insert_end(con, new Endereco("RUA Z","CRUZEIRO","BELO HORIZONTE","MG",2));
        database.insert_end(con, new Endereco("RUA X","FLAMENGO","RIO DE JANEIRO","RJ",7));
        database.insert_end(con, new Endereco("RUA Z","CRUZEIRO","BELO HORIZONTE","MG",5));
        database.insert_end(con, new Endereco("RUA X","CENTRO","JOSE BONIFACIO","SP",6));
        database.insert_end(con, new Endereco("RUA X","CENTRO","JOSE BONIFACIO","SP",10));
        database.insert_end(con, new Endereco("RUA H","CENTRO","RIO BONIFACIO","MG",9));         
    }
    
    void options(MySQL database,Connection con, String serverName, String mydatabase,
            String username, String password) throws SQLException{
        Scanner input = new Scanner(System.in);        
        while(true){
            System.out.println("\nEscolha a opção desejada");
//            System.out.println("(1) Inserir Dados");
            System.out.println("(1) Realizar Consultas");        
            System.out.println("(2) Sair");
            switch (input.nextInt()) {                
                case 1:
                    System.out.println("Consulta 1 - Retorna os dados de todos VENDEDORES."); database.query1(con);
                    System.out.println("Consulta 2 - Retorna os TELEFONES de todos VENDEDORES."); database.query2(con);
                    System.out.println("Consulta 3 - Retorna os ENDEREÇO de todos VENDEDORES."); database.query3(con);                    
                    System.out.println("Consulta 4 - Retorna o nome de cada VENDEDOR, incluindo o valor de venda de cada mês, o total, junto com um desconto de 25% e a média também."); database.query4(con);                    
                    System.out.println("Consulta 5 - Retorna os dados do VENDEDOR que mora no bairro 'Jardins'."); database.query5(con);    
                    System.out.println("Consulta 6 - Retorna TODOS os dados do vendedor 10."); database.query6(con);                    
                    System.out.println("Consulta 7 - Retorna o nome e o sexo do VENDEDOR que é homem e mora numá cidade que contém 'Bonifácio' no último nome."); database.query7(con);
                    System.out.println("Consulta 8 - Retorna o tipo e o número do telefone que é um celular, que começa com 9 ou que termine com 5."); database.query8(con);
                    System.out.println("Consulta 9 - Retorna o nome, e o valor, referente ao mês de fevereiro, onde este valor esta abaixo da média, apenas dos vendedores que são mulheres."); database.query9(con);
                    System.out.println("Consulta 10 - Retorna o nome e a quantidade de telefone dos vendedores que tenham 2 ou mais telefones"); database.query10(con);                                                            
                    break;
                case 2:
                    input.close();
                    database.close_connection(con);
                    System.exit(0);
                default:
                    break;
            }
        }        
    }
}
