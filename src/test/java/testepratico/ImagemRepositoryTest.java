package testepratico;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import testepratico.domain.Imagem;
import testepratico.domain.ImagemRepository;
import testepratico.domain.Produto;
import testepratico.domain.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ImagemRepositoryTest{
	
	@LocalServerPort
    private int port;
	
    private String BASE_PATH;
    
    private RestTemplate restTemplate = new RestTemplate();;
     
	@Autowired
    private ImagemRepository imagemRepository;
	
	@Autowired
    private ProdutoRepository produtoRepository;
    
    @Test
    public void testFindByIdImagem() {
    	
    	BASE_PATH = "http://localhost:"+port+"/api/imagem/";
    	
        Imagem imagemTeste = new Imagem();
        imagemTeste.setTipo("testeImagem");
        
        imagemTeste = imagemRepository.save(imagemTeste);
     
        Imagem response = restTemplate.getForObject(BASE_PATH + "/" +
	    		imagemTeste.getIdImagem(), Imagem.class);

		
		assertNotNull(response);
		assertThat(imagemTeste.getTipo().equals(response.getTipo()));
        
    }
    
    @Test
    public void whenFindByIdProduto_thenReturnImagens() {
    	BASE_PATH = "http://localhost:"+port+"/api/imagem/produto/";
    	
        Produto produtoTeste = new Produto();
        produtoTeste.setNome("ProdutoTesteImagem");
        produtoTeste.setDescricao("Produto de teste de imagem");
        
        produtoTeste = produtoRepository.save(produtoTeste);
     
        Imagem imagem = new Imagem();
        for (int i = 1; i < 4; i++) {
        	imagem.setTipo("tipo"+i);
        	imagem.setIdProduto(produtoTeste.getIdProduto());
        	imagemRepository.save(imagem);
		}
        
        Imagem[] imgs = restTemplate.getForObject(BASE_PATH + produtoTeste.getIdProduto(), Imagem[].class);
		
		assertNotNull(imgs);
		
		for (Imagem img : imgs) {
			assertThat(produtoTeste.getIdProduto().equals(img.getIdProduto()));
		}
        
    }
    
    @Test
    public void testCreateProduto() throws JsonProcessingException{
		
		BASE_PATH = "http://localhost:"+port+"/api/imagem/";
 
		Imagem i1 = new Imagem();
		i1.setTipo("tipoImagemProduto API");
		i1.setIdProduto(null);
		
		Imagem response = restTemplate.postForObject(BASE_PATH, i1, Imagem.class);
 
		assertNotNull(response);
		assertThat("tipoImagemProduto API".equals(response.getTipo()));
    }
	
	
	@Test
	public void testUpdateImagem() throws IOException{
		
		BASE_PATH = "http://localhost:"+port+"/api/imagem/";
		
		Imagem imagemTeste = new Imagem();
		imagemTeste.setTipo("tipoImagemProduto API");
		imagemTeste.setIdProduto(null);
		
		imagemTeste = imagemRepository.save(imagemTeste);
		
		imagemTeste.setTipo("Produto atualizado API");
		
	    restTemplate.put(BASE_PATH, imagemTeste);
	     
	    Imagem response = restTemplate.getForObject(BASE_PATH + "/" +
	    		imagemTeste.getIdImagem(), Imagem.class);
	    
	    assertNotNull(response);
	    assertThat("Produto atualizado API".equals(response.getTipo()));
	}
	
	@Test
	public void testDeleteImagem() throws IOException{
		
		BASE_PATH = "http://localhost:"+port+"/api/imagem/";
		
		Imagem imagemTeste = new Imagem();
		imagemTeste.setTipo("tipoImagemProduto API");
		imagemTeste.setIdProduto(null);
		
		imagemTeste = imagemRepository.save(imagemTeste);
		
	    restTemplate.delete(BASE_PATH + imagemTeste.getIdImagem());
	     
	    Imagem response = restTemplate.getForObject(BASE_PATH + "/" +
	    		imagemTeste.getIdImagem(), Imagem.class);
	    
	    assertNull(response);
	}
	
}