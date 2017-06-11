package testepratico;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;

import testepratico.domain.Produto;
import testepratico.domain.ProdutoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoRepositoryTest {
	
	@LocalServerPort
    private int port;
	
    private String BASE_PATH;
 
    private RestTemplate restTemplate = new RestTemplate();;
     
    @Autowired
	private ProdutoRepository produtoRepository;
    
    @Test
	public void testFindAll() {
		BASE_PATH = "http://localhost:"+port+"/api/produto/";
		
		Produto produtoTeste = new Produto();
		for (int i = 1; i < 5; i++) {
			produtoTeste.setNome("Produto " + i);
			produtoTeste.setDescricao("Produto de teste");

			produtoRepository.save(produtoTeste);
		}
		
		List<Produto> produtosBanco = produtoRepository.findAll();

		Produto[] produtosResponse = restTemplate.getForObject(BASE_PATH, Produto[].class);

		
		assertNotNull(produtosResponse);
		assertThat(produtosBanco.size() == produtosResponse.length);
		
		for (Produto produto : produtosResponse) {
			assertNotNull(produto.getImagens());
		}

	}
	
	@Test
	public void testFindAllSimple() {
		BASE_PATH = "http://localhost:"+port+"/api/produto/simple";
		
		Produto produtoTeste = new Produto();
		for (int i = 1; i < 5; i++) {
			produtoTeste.setNome("Produto " + i);
			produtoTeste.setDescricao("Produto de teste");

			produtoRepository.save(produtoTeste);
		}
		
		List<Produto> produtosBanco = produtoRepository.findAll();

		Produto[] produtosResponse = restTemplate.getForObject(BASE_PATH, Produto[].class);

		
		assertNotNull(produtosResponse);
		assertThat(produtosBanco.size() == produtosResponse.length);
		
		for (Produto produto : produtosResponse) {
			assertNull(produto.getImagens());
		}

	}

	@Test
	public void testFindByIdProduto() {
		BASE_PATH = "http://localhost:"+port+"/api/produto/";
		
		Produto produtoTeste = new Produto();
		produtoTeste.setNome("testeProduto");
		produtoTeste.setDescricao("Produto de teste");

		produtoTeste = produtoRepository.save(produtoTeste);

		Produto response = restTemplate.getForObject(BASE_PATH + "/" +
	    		produtoTeste.getIdProduto(), Produto.class);

		
		assertNotNull(response);
		assertNotNull(response.getImagens());
		assertThat(produtoTeste.getNome().equals(response.getNome()));

	}
	
	@Test
	public void testFindByIdProdutoSimple() {
		BASE_PATH = "http://localhost:"+port+"/api/produto/simple/";
		
		Produto produtoTeste = new Produto();
		produtoTeste.setNome("testeProduto");
		produtoTeste.setDescricao("Produto de teste");

		produtoTeste = produtoRepository.save(produtoTeste);

		Produto response = restTemplate.getForObject(BASE_PATH + "/" +
	    		produtoTeste.getIdProduto(), Produto.class);

		
		assertNotNull(response);
		assertNull(response.getImagens());
		assertThat(produtoTeste.getNome().equals(response.getNome()));

	}

	@Test
	public void testFindByIdProdutoPai() {
		BASE_PATH = "http://localhost:"+port+"/api/produto/parent/";

		Produto produtoTeste = new Produto();
		produtoTeste.setNome("ProdutoPai");
		produtoTeste.setDescricao("Produto de teste");

		Produto pai = produtoRepository.save(produtoTeste);

		Produto filho = new Produto();
		
		for (int i = 1; i < 3; i++) {
			filho.setNome("ProdutoFilho " + i);
			filho.setDescricao("Produto de teste filho");
			filho.setIdProdutoPai(pai.getIdProduto());

			produtoRepository.save(filho);
		}
		

		Produto[] prods = restTemplate.getForObject(BASE_PATH + pai.getIdProduto(), Produto[].class);
		
		assertNotNull(prods);
		
		for (Produto produto : prods) {
			assertThat(produto.getIdProdutoPai().equals(pai.getIdProduto()));
		}

	}

	@Test
    public void testCreateProduto() throws JsonProcessingException{
		
		BASE_PATH = "http://localhost:"+port+"/api/produto/";
 
		Produto p1 = new Produto();
		p1.setNome("ProdutoTeste");
		p1.setDescricao("Produto de teste post");
		p1.setIdProdutoPai(null);
		
		Produto response = restTemplate.postForObject(BASE_PATH, p1, Produto.class);
 
		assertThat("ProdutoTeste".equals(response.getNome()));
    }
	
	
	@Test
	public void testUpdateProduto() throws IOException{
		
		BASE_PATH = "http://localhost:"+port+"/api/produto/";
		
		Produto produtoTeste = new Produto();
		produtoTeste.setNome("Produto inserido");
		produtoTeste.setDescricao("Produto de teste insercao");
		
		produtoTeste = produtoRepository.save(produtoTeste);
		
		produtoTeste.setNome("Produto atualizado");
		produtoTeste.setDescricao("Produto de teste atualizado");
		
	    restTemplate.put(BASE_PATH, produtoTeste);
	     
	    Produto response = restTemplate.getForObject(BASE_PATH + "/" +
	    		produtoTeste.getIdProduto(), Produto.class);
	    
	    assertNotNull(response);
	    assertThat("Produto atualizado".equals(response.getNome()));
	    assertThat("Produto de teste atualizado".equals(response.getDescricao()));
	     
	}
	
	
	@Test
	public void testDeleteProduto() throws IOException{
		
		BASE_PATH = "http://localhost:"+port+"/api/produto/";
		
		Produto produtoTeste = new Produto();
		produtoTeste.setNome("Produto inserido");
		produtoTeste.setDescricao("Produto de teste insercao");
		
		produtoTeste = produtoRepository.save(produtoTeste);
		
	    restTemplate.delete(BASE_PATH + produtoTeste.getIdProduto());
	     
	    Produto response = restTemplate.getForObject(BASE_PATH + "/" +
	    		produtoTeste.getIdProduto(), Produto.class);
	    
	    assertNull(response);
	}
	
}