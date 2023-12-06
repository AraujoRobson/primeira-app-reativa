package br.exemplo.api;

import java.util.List;

import br.exemplo.model.Produto;
import br.exemplo.repository.ProdutoRepository;
import io.smallrye.mutiny.Multi;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/produto")
public class ProdutoController {

	@Inject
	ProdutoRepository repo;

	@GET
	@Path("/imperativo")
	public List<Produto> getProdutosImperativo() {
		return repo.criaProdutosImperativo();
	}

	@GET
	@Path("/reativo")
	@Produces(MediaType.APPLICATION_JSON)
	public Multi<Produto> getProdutosReativo() {
		return repo.criaProdutosReativo();
	}

}
