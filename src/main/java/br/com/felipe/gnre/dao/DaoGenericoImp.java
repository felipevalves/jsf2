package br.com.felipe.gnre.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class DaoGenericoImp<T, ID extends Serializable> extends BaseDao  implements
		IDaoGenerico<T, ID> 
{
	private EntityManager entityManager = getEntityManagerFactory().createEntityManager();

	private final Class<T> oClass;// object class

	public Class<T> getObjectClass()
	{
		return this.oClass;
	}

	@SuppressWarnings("unchecked")
	@PersistenceContext
	public void setEntityManager(EntityManager em)
	{
		this.entityManager = em;
	}

	protected EntityManager getEntityManager()
	{
		if (entityManager == null)
			throw new IllegalStateException("EntityManager is null");

		if (!entityManager.isOpen())
			entityManager = getEntityManagerFactory().createEntityManager();

		return entityManager;
	}

	@SuppressWarnings("unchecked")
	public DaoGenericoImp()
	{
		this.oClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];

	}

	public T findById(ID id)
	{
		T t = (T) getEntityManager().find(oClass, id);
		
		return t; 
	}
	public T save(T object)
	{
		getEntityManager().getTransaction().begin();
		getEntityManager().clear();
		getEntityManager().persist(object);
		getEntityManager().getTransaction().commit();
		return object;
	}

	@Override
	public T update(T object) {
		getEntityManager().getTransaction().begin();
		getEntityManager().clear();
		getEntityManager().merge(object);
		getEntityManager().getTransaction().commit();
		return object;
	}


	public List<T> listPesqParam(String query, Map<String, Object> params)
	{
		Query q = getEntityManager().createQuery(query);
		montaQuery(params, q);
		return q.getResultList();
	}

	public List listPesqParamNativeQuery(String query, Map<String, Object> params) throws Exception
	{
		//getEntityManager().flush();
		Query q = getEntityManager().createNativeQuery(query);
		montaQuery(params, q);
		return q.getResultList();
	}
	
	public List listPesqParamNativeQuery(String query, Map<String, Object> params,int maximo, int atual) throws Exception
	{
		getEntityManager().flush();
		Query q = getEntityManager().createNativeQuery(query).setMaxResults(maximo).setFirstResult(atual);
		
		montaQuery(params, q);
		return q.getResultList();
	}

	public T pesqParam(String query, Map<String, Object> params)
	{
		Query q = getEntityManager().createQuery(query);

		montaQuery(params, q);
		try
		{
			return (T) q.getSingleResult();
		}
		catch (NoResultException nre)
		{
			return null;
		}
	}
	
	public Object findParamNativeQuery(String query, Map<String, Object> params) throws NoResultException
	{
		Query q = getEntityManager().createNativeQuery(query);
		montaQuery(params, q);
		return q.getSingleResult();
	}
	

	@SuppressWarnings("unchecked")
	public void queryParam(String query)throws Exception
	{
		Query q = getEntityManager().createQuery(query);
		q.executeUpdate();
	}
	
	public void queryParam(String query,Map<String,Object> params)throws Exception
	{
		Query q = getEntityManager().createQuery(query);
		montaQuery(params, q);
		q.executeUpdate();
	}
	
	public void queryNativeParam(String query,Map<String,Object> params)
	{
		getEntityManager().getTransaction().begin();
		Query q = getEntityManager().createNativeQuery(query);
		montaQueryNull(params, q);
		q.executeUpdate();
		getEntityManager().getTransaction().commit();
	}
	

	private void montaQuery(Map<String, Object> params, Query q)
	{
		for (String chave : params.keySet())
		{
			q.setParameter(chave, params.get(chave));
		}
	}

	private void montaQueryNull(Map<String, Object> params, Query q)
	{
		for (String chave : params.keySet())
		{
			if (params.get(chave) != null)
				q.setParameter(chave, params.get(chave));
			else
				q.setParameter(chave, "");
		}
	}

	public Integer getInteger(Object obj)
	{
		return obj != null ? Integer.valueOf(obj.toString()) : null;
	}
	
	public Integer getDoubleToInteger(Object obj)
	{
		return obj != null ? getDouble(obj).intValue() : null;
	}
	
	public int getInt(Object obj)
	{
		return obj != null ? Integer.parseInt(obj.toString()) : 0;
	}
	
	public double getDoublePrimitivo(Object obj)
	{
		return obj != null ? Double.parseDouble(obj.toString()) : 0d;
	}
	
	public Double getDouble(Object obj)
	{
		return obj != null ? Double.valueOf(obj.toString()) : null;
	}
	
	public String getString(Object obj)
	{
		return obj != null ? obj.toString() : null;
	}
	
	public boolean getBoolean(Object obj)
	{
		return obj != null && obj.toString().equals("1") ? true : false;
	}
	
	public Date getDate(Object obj)
	{
		return  obj != null ? (Date) obj : null;
	}
	
	public java.sql.Date getDateSql(Object obj)
	{
		Date myDate = getDate(obj);
		if (myDate == null)
			return null;
		
		return  new java.sql.Date(myDate.getTime());
	}
	
}
