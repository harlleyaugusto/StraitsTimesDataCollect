package edu.astar.ihpc.StraitsTimes.database.model;

import java.util.ArrayList;
import java.util.List;


import edu.astar.ihpc.StraitsTimes.database.dao.HibernateDao;
import edu.astar.ihpc.StraitsTimes.database.dto.Comment;
import edu.astar.ihpc.StraitsTimes.database.dto.News;
import edu.astar.ihpc.StraitsTimes.database.dto.User;


/**
 * Database operations for Comment DTO.
 */
public class UserBO
    extends BaseBO
{

    /**
     * Constructor.
     */
    public UserBO()
    {
        super();
    }

    /**
     * Constructor with database as parameter.
     * 
     * @param database
     */
    public UserBO(HibernateDao database)
    {
        super(database);
    }
    
    /**
     * Load users from database.
     * 
     * @param  user name.
     * @return List of users.
     */
    public User loadUser(String name)
    {
        List<Object> result = new ArrayList<Object>();

        String hql = "select user from User as user where user.name = ?";
        result = this.database.executeHql(hql, name);

        if ((result == null) || (result.isEmpty()))
        {
            return null;
        }

        return (User) result.get(0);
    }

	public List<Object> load() {
		// TODO Auto-generated method stub
		List<Object> result = this.database.executeHql("select user from User as user");
    	return result;
	}

	public User loadUser(Long idUser) {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>();

        String hql = "select user from User as user where user.id = ?";
        result = this.database.executeHql(hql, idUser);

        if ((result == null) || (result.isEmpty()))
        {
            return null;
        }

        return (User) result.get(0);
	}

	public Long getCountLikes(User user) {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>(); 

        String hql = "select sum(countLike) from Comment as comment where comment.user = ?";
        result = this.database.executeHql(hql, user);

        //Object[] v = (Object[]) result.get(0);
        return (Long) result.get(0);
	
	}

	public Long getNewsCount(User user) {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>(); 

        String hql = "select count(distinct news) from Comment as comment, News as news where comment.user = ? and comment.newsId = news.id ";
        result = this.database.executeHql(hql, user);

        //Object[] v = (Object[]) result.get(0);
        return (Long) result.get(0);

	}

	public Long getCommentCount(User user) {
		// TODO Auto-generated method stub
		List<Object> result = new ArrayList<Object>(); 

        String hql = "select count(*) from Comment as comment where comment.user = ?";
        result = this.database.executeHql(hql, user);

        //Object[] v = (Object[]) result.get(0);
        return (Long) result.get(0);
	}
}
