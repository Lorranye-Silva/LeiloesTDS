/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public void cadastrarProduto (ProdutosDTO produto){
       conn = new conectaDAO().connectDB();
    try {
        String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?)";
        prep = conn.prepareStatement(sql);
        prep.setString(1, produto.getNome());
        prep.setInt(2, produto.getValor());
        prep.setString(3, produto.getStatus());
        prep.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!"); // Mensagem de sucesso
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao cadastrar produto: " + e.getMessage()); // Mensagem de erro
    } finally {
        try {
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }




    }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
      ArrayList<ProdutosDTO> lista = new ArrayList<>();
    conn = new conectaDAO().connectDB();
    try {
        String sql = "SELECT * FROM produtos";
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            lista.add(produto);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar produtos: " + e.getMessage());
    } finally {
        try {
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
    return lista;


    }
    public void venderProduto(int id) {
    conn = new conectaDAO().connectDB();
    try {
        String sql = "UPDATE produtos SET status = 'Vendido' WHERE id = ?";
        prep = conn.prepareStatement(sql);
        prep.setInt(1, id);
        prep.executeUpdate();
        JOptionPane.showMessageDialog(null, "Produto vendido com sucesso!");
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao vender produto: " + e.getMessage());
    } finally {
        try {
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
}
public ArrayList<ProdutosDTO> listarVendas() {
    ArrayList<ProdutosDTO> listaVendidos = new ArrayList<>();
    conn = new conectaDAO().connectDB(); // Conecta ao banco de dados
    try {
        String sql = "SELECT * FROM produtos WHERE status = 'Vendido'"; // Busca produtos vendidos
        prep = conn.prepareStatement(sql);
        resultset = prep.executeQuery();

        while (resultset.next()) {
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            listaVendidos.add(produto); // Adiciona o produto à lista
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Erro ao listar vendas: " + e.getMessage());
    } finally {
        try {
            conn.close(); // Fecha a conexão com o banco de dados
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
        }
    }
    return listaVendidos; // Retorna a lista de produtos vendidos
}

    
    
        
}

