package testepratico.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import testepratico.domain.Produto;
import testepratico.domain.ProdutoRepository;

@Controller
public class ProdutoController {
	
	@Autowired 
	private ProdutoRepository repository;
	
	@RequestMapping("/produtos")
	public String listProdutos(Model model){
		
		Produto p1 = new Produto();
		p1.setNome("Produto1");
		p1.setDescricao("Produto de teste id: 1");
		p1.setIdProdutoPai(null);

		Produto p2 = new Produto();
		p2.setNome("Produto2");
		p2.setDescricao("Produto de teste id: 2");
		p2.setIdProdutoPai(1l);

		Produto p3 = new Produto();
		p3.setNome("Produto3");
		p3.setDescricao("Produto de teste id: 3");
		p3.setIdProdutoPai(1l);

		Produto p4 = new Produto();
		p4.setNome("Produto4");
		p4.setDescricao("Produto de teste id: 4");
		p4.setIdProdutoPai(2l);

		Produto p5 = new Produto();
		p5.setNome("Produto5");
		p5.setDescricao("Produto de teste id: 5");
		p5.setIdProdutoPai(2l);
		
		
		repository.save(p1);
		repository.save(p2);
		repository.save(p3);
		repository.save(p4);
		repository.save(p5);
		
		Iterable<Produto> produtos = repository.findAll();
		
		model.addAttribute("produtos", produtos);
		
		return "index";
	}
	
}
