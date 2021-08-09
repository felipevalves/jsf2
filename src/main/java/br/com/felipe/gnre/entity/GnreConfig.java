package br.com.felipe.gnre.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author felipe.alves
 *
 */
@Entity
@Table(name = "GNRE_CONFIG")
public class GnreConfig {
	
	@Id
	private String uf;
	@Column(name = "COD_RECEITA")
	private long codReceita;
	@Column(name = "COD_DETALHE_RECEITA")
	private Integer codDetalheReceita;
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public long getCodReceita() {
		return codReceita;
	}
	public void setCodReceita(long codReceita) {
		this.codReceita = codReceita;
	}
	public Integer getCodDetalheReceita() {
		return codDetalheReceita;
	}
	public void setCodDetalheReceita(Integer codDetalheReceita) {
		this.codDetalheReceita = codDetalheReceita;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GnreConfig [uf=");
		builder.append(uf);
		builder.append(", codReceita=");
		builder.append(codReceita);
		builder.append(", codDetalheReceita=");
		builder.append(codDetalheReceita);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
