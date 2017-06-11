package testepratico.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="imagem")
public class Imagem {
	
	@Id
	@GeneratedValue
	private Long idImagem;
	
	private String tipo;
	
	private Long idProduto;

	public Long getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(Long idImagem) {
		this.idImagem = idImagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	@Override
	public String toString() {
		return "Imagem [idImagem=" + idImagem + ", tipo=" + tipo + ", idProduto=" + idProduto + "]";
	}

}
