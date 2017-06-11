package testepratico.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

@Component
public interface ProdutoRepository extends  JpaRepository<Produto, Long>{

	public Produto findByNome(String nome);
	
	public List<Produto> findByIdProdutoPai(Long idProdutoPai);
	
	@Query(value="select p from produto p ")
	public List<Produto> findAllProdutos();
	
	@Query("select p from produto p where p.idProduto = :idProduto")
	public Produto findByIdProduto(@Param("idProduto") Long idProduto);
	
}
