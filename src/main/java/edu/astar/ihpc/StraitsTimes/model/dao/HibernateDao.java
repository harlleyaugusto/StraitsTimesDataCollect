package edu.astar.ihpc.StraitsTimes.model.dao;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import edu.astar.ihpc.StraitsTimes.model.dto.BaseDto;

public class HibernateDao
{
    private static final SessionFactory sessionFactory;

    private Session session;

   // private Logger logger = LoggerFactory.getLogger(HibernateDao.class);

    static
    {
        try
        {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable ex)
        {
            // Log exception!
            throw new ExceptionInInitializerError(ex);
        }
    }

    public HibernateDao()
    {
        //this.logger.debug("Opening new session.");
        this.session = sessionFactory.openSession();
    }

    public void beginTransaction()
    {
        System.err.println("(begin transaction)");
        this.session.beginTransaction();
    }

    public void update(BaseDto dto)
    {
        this.session.save(dto);
        
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

    //@SuppressWarnings("unchecked")
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
