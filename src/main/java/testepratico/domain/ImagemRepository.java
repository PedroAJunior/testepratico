package testepratico.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ImagemRepository extends JpaRepository<Imagem, Long>{
	
	public List<Imagem> findByIdProduto(Long idProduto);

}
