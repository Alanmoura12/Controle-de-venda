    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import models.Cliente;
import jdbc.ConnectionFactory;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


/**
 *
 * @author alanm
 */
public class ClienteDAO {
    
    private Connection con;
    
    //Conecta ao banco de dados
    public ClienteDAO(){
        
        this.con = new ConnectionFactory().getConnection();
    }
   
    
    
    //metodo cadastrar cliente
    public void cadastrarCliente(Cliente obj){
        
        try {
            //comando SQL
            String sql = "insert into tb_clientes (nome,rg,cpf,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)"
                    + " values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            
            
            //organizar e executar o comando SQL
                PreparedStatement stmt = con.prepareStatement(sql);
                
                stmt.setString(1,obj.getNome());
                stmt.setString(2,obj.getRg());
                stmt.setString(3,obj.getCpf());
                stmt.setString(4,obj.getEmail());
                stmt.setString(5,obj.getTelefone());
                stmt.setString(6,obj.getCelular());
                stmt.setString(7,obj.getCep());
                stmt.setString(8,obj.getEndereco());
                stmt.setInt(9,obj.getNumero());
                stmt.setString(10,obj.getComplemento());
                stmt.setString(11,obj.getBairro());
                stmt.setString(12,obj.getCidade());
                stmt.setString(13,obj.getEstado());
                
                
                //executar e fechar a conexão
                stmt.execute();
                stmt.close();
            
            
            JOptionPane.showMessageDialog(null,"Cliente cadastrado com sucesso");
            
        } catch (Exception erro) {
            
            JOptionPane.showMessageDialog(null,"ERRO: " + erro);

        }
    } 
    
    
    //método listar todos clientes
    public List<Cliente> listarCliente (){
        try {
            //criar comendo SQL
            String sql = "select * from tb_clientes";
            
            //criar a lista
            List<Cliente> lista = new ArrayList<>();
            
            //organizar e executar o comando SQL
            PreparedStatement stmt = con.prepareStatement(sql);
                
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente obj = new Cliente();
                
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setRg(rs.getString("rg"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setEstado(rs.getString("estado"));
               
            }
            return lista;
             
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
            return null;
        }
    }
    
}

