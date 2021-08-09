package br.com.felipe.gnre.dao;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;

import br.com.felipe.gnre.entity.GnreConfig;

@Stateless
public class GnreConfigDao extends DaoGenericoImp<GnreConfig, Integer> implements IGnreConfigDao, Serializable  {

	
	private static final long serialVersionUID = 4494651389910096458L;

	public List<GnreConfig> findAllConfig() {
		return getEntityManager().createQuery("FROM GnreConfig g", GnreConfig.class).getResultList();
	}

}
