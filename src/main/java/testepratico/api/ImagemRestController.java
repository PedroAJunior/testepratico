package testepratico.api;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import testepratico.domain.Imagem;
import testepratico.domain.ImagemRepository;

@RestController
@RequestMapping("/api")
public class ImagemRestController {

	@Autowired
	private ImagemRepository imagemRepository;
	
	@RequestMapping(value = "/imagem", method = RequestMethod.GET)
	List<Imagem> listImagens() {
		return this.imagemRepository.findAll();
	}
	
	@RequestMapping(value = "/imagem/{idImagem}", method = RequestMethod.GET)
	Imagem findImagem(@PathVariable Long idImagem) {
		return this.imagemRepository.findOne(idImagem);
	}
	
	@RequestMapping(value = "/imagem/produto/{idProduto}", method = RequestMethod.GET)
	Iterable<Imagem> findImagemByProduto(@PathVariable Long idProduto) {
		return this.imagemRepository.findByIdProduto(idProduto);
	}
	
	@RequestMapping(value = "/imagem/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Imagem saveImagem(@RequestBody Imagem imagem) {
		return this.imagemRepository.save(imagem);
	}
	
	
	@RequestMapping(value = "/imagem/", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public void updateImagem(@RequestBody Imagem imagem) {
		this.imagemRepository.save(imagem);
	}
	
	
	@RequestMapping(value = "/imagem/{idImagem}", method = RequestMethod.DELETE)
	public void deleteImagem(@PathVariable Long idImagem) {
		this.imagemRepository.delete(idImagem);
	}
	
}
