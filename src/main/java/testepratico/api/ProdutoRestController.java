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
import testepratico.domain.Produto;
import testepratico.domain.ProdutoRepository;

@RestController
@RequestMapping("/api")
public class ProdutoRestController {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ImagemRepository imagemRepository;
	
	@RequestMapping(value = "/produto/simple", method = RequestMethod.GET)
	List<Produto> listAllProdutos() {
		return this.produtoRepository.findAllProdutos();
	}
	
	@RequestMapping(value = "/produto/simple/{idProduto}", method = RequestMethod.GET)
	Produto findProdutoById(@PathVariable Long idProduto) {
		return this.produtoRepository.findByIdProduto(idProduto);
	}
	
	@RequestMapping(value = "/produto", method = RequestMethod.GET)
	List<Produto> listProdutos() {
		List<Produto> produtos = this.produtoRepository.findAll();
		
		if(produtos != null){
			for (int i = 0; i < produtos.size(); i++) {
				List<Imagem> imagens = imagemRepository.findByIdProduto(produtos.get(i).getIdProduto());
				produtos.get(i).setImagens(imagens);
			}
		}
		
		return produtos;
	}
	
	@RequestMapping(value = "/produto/{idProduto}", method = RequestMethod.GET)
	Produto findProduto(@PathVariable Long idProduto) {
		Produto produto = this.produtoRepository.findOne(idProduto);
		if(produto != null){
			produto.setImagens(imagemRepository.findByIdProduto(produto.getIdProduto()));
		}
		
		return produto;
	}
	
	@RequestMapping(value = "/produto/parent/{idProdutoPai}", method = RequestMethod.GET)
	List<Produto> findProdutoFilho(@PathVariable Long idProdutoPai) {
		return this.produtoRepository.findByIdProdutoPai(idProdutoPai);
	}
	
	
	@RequestMapping(value = "/produto/", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Produto saveProduto(@RequestBody Produto produto) {
		return this.produtoRepository.save(produto);
	}
	
	
	@RequestMapping(value = "/produto/", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json")
	public void updateProduto(@RequestBody Produto produto) {
		this.produtoRepository.save(produto);
	}
	
	
	@RequestMapping(value = "/produto/{idProduto}", method = RequestMethod.DELETE)
	public void deleteProduto(@PathVariable Long idProduto) {
		this.produtoRepository.delete(idProduto);
	}
	
}
