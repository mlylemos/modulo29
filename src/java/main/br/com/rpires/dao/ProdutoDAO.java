package br.com.rpires.dao;

import br.com.rpires.domain.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO implements IProdutoDAO {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_alunos", "postgres", "1234567br");
    }

    @Override
    public Integer cadastrar(Produto produto) throws Exception {
        Connection conn = getConnection();
        String sql = "INSERT INTO tb_produto (codigo, nome, preco) VALUES (?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getCodigo());
        stmt.setString(2, produto.getNome());
        stmt.setDouble(3, produto.getPreco());
        int result = stmt.executeUpdate();
        stmt.close();
        conn.close();
        return result;
    }

    @Override
    public Produto consultar(String codigo) throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT * FROM tb_produto WHERE codigo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, codigo);
        ResultSet rs = stmt.executeQuery();
        Produto produto = null;
        if (rs.next()) {
            produto = new Produto();
            produto.setId(rs.getLong("id"));
            produto.setCodigo(rs.getString("codigo"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return produto;
    }

    @Override
    public Integer excluir(Produto produtoBD) throws Exception {
        Connection conn = getConnection();
        String sql = "DELETE FROM tb_produto WHERE codigo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produtoBD.getCodigo());
        int result = stmt.executeUpdate();
        stmt.close();
        conn.close();
        return result;
    }

    @Override
    public List<Produto> buscarTodos() throws Exception {
        Connection conn = getConnection();
        String sql = "SELECT * FROM tb_produto";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Produto> produtos = new ArrayList<>();
        while (rs.next()) {
            Produto produto = new Produto();
            produto.setId(rs.getLong("id"));
            produto.setCodigo(rs.getString("codigo"));
            produto.setNome(rs.getString("nome"));
            produto.setPreco(rs.getDouble("preco"));
            produtos.add(produto);
        }
        rs.close();
        stmt.close();
        conn.close();
        return produtos;
    }

    @Override
    public Integer atualizar(Produto produto) throws Exception {
        Connection conn = getConnection();
        String sql = "UPDATE tb_produto SET nome = ?, preco = ? WHERE codigo = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, produto.getNome());
        stmt.setDouble(2, produto.getPreco());
        stmt.setString(3, produto.getCodigo());
        int result = stmt.executeUpdate();
        stmt.close();
        conn.close();
        return result;
    }
}
