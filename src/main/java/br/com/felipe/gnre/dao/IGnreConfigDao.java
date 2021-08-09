package br.com.felipe.gnre.dao;

import java.util.List;

import br.com.felipe.gnre.entity.GnreConfig;

public interface IGnreConfigDao {

	List<GnreConfig> findAllConfig();
}
