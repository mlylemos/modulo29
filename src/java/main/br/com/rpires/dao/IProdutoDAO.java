package br.com.rpires.dao;

import br.com.rpires.domain.Produto;
import java.util.List;

public interface IProdutoDAO {

    Integer cadastrar(Produto produto) throws Exception;

    Produto consultar(String codigo) throws Exception;

    Integer excluir(Produto produtoBD) throws Exception;

    List<Produto> buscarTodos() throws Exception;

    Integer atualizar(Produto produto) throws Exception;
}
