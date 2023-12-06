package br.exemplo.repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import br.exemplo.model.Produto;
import io.smallrye.mutiny.Multi;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProdutoRepository {

  private void pausar(int i){
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException();
    }
  }

  private Produto gerarNovoProduto(int i) {
    Produto produto = new Produto();
    produto.setId(i);
    produto.setNome("Produto " + i);
    return produto;
  }

  public List<Produto> criaProdutosImperativo() {
    return IntStream
            .range(0, 20)
            .peek(i -> System.out.println("Interaçao (Imperativa): " + i))
            .peek(this::pausar)
            .mapToObj(this::gerarNovoProduto)
            .collect(Collectors.toList());

  }
  
  public Multi<Produto> criaProdutosReativo() {
    return Multi.createFrom()
                .range(0, 20)
                .onItem().invoke(i -> System.out.println("Interação (Reativa): " + i))
                .onItem().invoke(this::pausar)
                .onItem().transform(this::gerarNovoProduto)
                .onCancellation().invoke( () -> System.out.println("Usuário cancelou a requisição.") )
                .onTermination().invoke( () -> System.out.println("Requisição foi finalizada.") );
            
  }
}
