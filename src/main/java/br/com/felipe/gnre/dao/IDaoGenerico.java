package br.com.felipe.gnre.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;


public interface IDaoGenerico<T, ID extends Serializable> {
	Class<T> getObjectClass();
	T save(T object);
	T update(T object);
	T findById(ID id);

}
