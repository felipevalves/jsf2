package br.com.felipe.gnre;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.felipe.gnre.dao.IGnreConfigDao;
import br.com.felipe.gnre.entity.GnreConfig;

@Named
@SessionScoped
public class HomeBean implements Serializable {

	@Inject
	private IGnreConfigDao gnreDao;
	
	private String hello = "Olá mundo";

	public String getHello() {
		
		return hello;
	}

	public String getConfig() {
		
		List<GnreConfig> listConfig = gnreDao.findAllConfig();
		return listConfig.toString();
	}
	
	public IGnreConfigDao getGnreDao() {
		return gnreDao;
	}

	public void setGnreDao(IGnreConfigDao gnreDao) {
		this.gnreDao = gnreDao;
	}
	
	

}
