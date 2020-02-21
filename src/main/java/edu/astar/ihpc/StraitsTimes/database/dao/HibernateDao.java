package edu.astar.ihpc.StraitsTimes.database.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.astar.ihpc.StraitsTimes.database.dto.BaseDto;

public class HibernateDao
{
    private static final SessionFactory sessionFactory;

    private Session session;


    static
    {
        try
        {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex)
        {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public HibernateDao()
    {
        this.session = sessionFactory.openSession();
    }

    public void beginTransaction()
    {
        System.err.println("(begin transaction)");
        this.session.beginTransaction();
    }

    public Serializable save(BaseDto dto)
    {
        return this.session.save(dto);
    }
    
    public void update(BaseDto dto)
    {
        this.session.update(dto);
        
    }
    
    public void saveOrUpdate(BaseDto dto)
    {
        
        this.session.saveOrUpdate(dto);
    }
    
    public BaseDto updateDto(BaseDto dto)
    {
        StringBuilder hql = new StringBuilder();
        hql.append("select dto from ");
        hql.append(dto.getClass().getSimpleName());
        hql.append(" as dto where id = ?");
        List<Object> res = this.executeHql(hql.toString(), dto.getId());
        return (BaseDto) res.get(0);
    }
    
    
    public void endTransaction()
    {
        System.err.println("(end transaction)");
        try
        {
            this.session.getTransaction().commit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Transaction t = this.session.getTransaction();
            if (t != null)
            {
                t.rollback();
            }
        }
    }

    public void closeSession()
    {
        this.session.close();
    }

    public List<Object> executeHql(String hql, Object... param)
    {
        Query query = this.session.createQuery(hql);
        for (int i = 0; i < param.length; i++)
        {
            query.setParameter(i, param[i]);
        }

        List<Object> res = (List<Object>) query.list();
        if (res != null && res.size() > 0)
        {
            return res;
        }
        else
        {
            return null;
        }
    }

}
