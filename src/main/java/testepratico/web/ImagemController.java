package testepratico.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import testepratico.domain.Imagem;
import testepratico.domain.ImagemRepository;

@Controller
public class ImagemController {
	
	@Autowired 
	private ImagemRepository repository;

	@RequestMapping("/imagens")
	public String listProdutos(Model model){
		
		Imagem i1 = new Imagem();
		i1.setTipo("tipoImagemProduto");
		i1.setIdProduto(1l);

		Imagem i2 = new Imagem();
		i2.setTipo("tipoImagemProduto");
		i2.setIdProduto(1l);

		Imagem i3 = new Imagem();
		i3.setTipo("tipoImagemProduto");
		i3.setIdProduto(2l);

		Imagem i4 = new Imagem();
		i4.setTipo("tipoImagemProduto");
		i4.setIdProduto(2l);

		Imagem i5 = new Imagem();
		i5.setTipo("tipoImagemProduto");
		i5.setIdProduto(2l);

		Imagem i6 = new Imagem();
		i6.setTipo("tipoImagemProduto");
		i6.setIdProduto(3l);

		Imagem i7 = new Imagem();
		i7.setTipo("tipoImagemProduto");
		i7.setIdProduto(4l);

		Imagem i8 = new Imagem();
		i8.setTipo("tipoImagemProduto");
		i8.setIdProduto(4l);

		Imagem i9 = new Imagem();
		i9.setTipo("tipoImagemProduto");
		i9.setIdProduto(5l);

		Imagem i10 = new Imagem();
		i10.setTipo("tipoImagemProduto");
		i10.setIdProduto(5l);
		
		repository.save(i1);
		repository.save(i2);
		repository.save(i3);
		repository.save(i4);
		repository.save(i5);
		repository.save(i6);
		repository.save(i7);
		repository.save(i8);
		repository.save(i9);
		repository.save(i10);
		
		Iterable<Imagem> imagens = repository.findAll();
		
		model.addAttribute("imagens", imagens);
		
		
		return "index2";
	}
	
}
