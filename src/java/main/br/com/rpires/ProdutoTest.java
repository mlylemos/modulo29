package br.com.rpires;

import br.com.rpires.dao.IProdutoDAO;
import br.com.rpires.dao.ProdutoDAO;
import br.com.rpires.domain.Produto;

import java.util.List;

public class ProdutoTest {

    public static void main(String[] args) throws Exception {
        IProdutoDAO produtoDAO = new ProdutoDAO();

        Produto produto = new Produto();
        produto.setCodigo("P01");
        produto.setNome("Caderno");
        produto.setPreco(15.99);

        Integer retorno = produtoDAO.cadastrar(produto);
        System.out.println("Cadastrado: " + retorno);

        Produto produtoConsultado = produtoDAO.consultar("P01");
        System.out.println("Consultado: " + produtoConsultado.getNome());

        produtoConsultado.setNome("Caderno Universitário");
        produtoConsultado.setPreco(19.99);
        produtoDAO.atualizar(produtoConsultado);
        System.out.println("Atualizado!");

        List<Produto> produtos = produtoDAO.buscarTodos();
        System.out.println("Todos os produtos:");
        for (Produto p : produtos) {
            System.out.println(p.getCodigo() + " - " + p.getNome());
        }

        produtoDAO.excluir(produtoConsultado);
        System.out.println("Excluído!");
    }
}
