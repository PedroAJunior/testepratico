package testepratico.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;

@Entity(name="produto")
public class Produto {
	
	@Id
	@GeneratedValue
	private Long idProduto;
	
	private String nome;
	
	private String descricao;
	
	private Long idProdutoPai;
	
	@Transient
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProdutoPai")
	private Set<Produto> produtosFilhos;
	
	@Transient
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "idProduto")
	private List<Imagem> imagens;

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getIdProdutoPai() {
		return idProdutoPai;
	}

	public void setIdProdutoPai(Long idProdutoPai) {
		this.idProdutoPai = idProdutoPai;
	}

	@Transactional
	public List<Imagem> getImagens() {
		return imagens;
	}

	public void setImagens(List<Imagem> imagens) {
		this.imagens = imagens;
	}
	
	public Set<Produto> getProdutosFilhos() {
		return produtosFilhos;
	}

	public void setProdutosFilhos(Set<Produto> produtosFilhos) {
		this.produtosFilhos = produtosFilhos;
	}

	@Override
	public String toString() {
		return "Produto [idProduto=" + idProduto + ", nome=" + nome + ", descricao=" + descricao + ", idProdutoPai="
				+ idProdutoPai + ", produtosFilhos=" + produtosFilhos + ", imagens=" + imagens + "]";
	}

}