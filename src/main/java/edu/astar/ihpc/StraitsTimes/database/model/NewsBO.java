package edu.astar.ihpc.StraitsTimes.database.model;

import java.util.List;

import edu.astar.ihpc.StraitsTimes.database.dao.HibernateDao;
import edu.astar.ihpc.StraitsTimes.database.dto.News;
import edu.astar.ihpc.StraitsTimes.database.dto.User;

/**
 * Database operations for News DTO.
 */
public class NewsBO extends BaseBO {

	/**
	 * Constructor.
	 */
	public NewsBO() {
		super();
	}

	/**
	 * Constructor with database as parameter.
	 * 
	 * @param database
	 */
	public NewsBO(HibernateDao database) {
		super(database);
	}

	/**
	 * Load the news by id.
	 * 
	 * @param news
	 *            News.
	 * @return List of news.
	 */
	public List<Object> load(News news) {
		String hql = "from News as news where News.id=?";
		List<Object> result = this.database.executeHql(hql, news);
		return result;
	}

	public News load(Long id) {
		String hql = "from News as news where News.id= ? ";
		List<Object> result = this.database.executeHql(hql, id);
		return (News) result.get(0);
	}

	public List<Object> loadNewsById() {
		// TODO Auto-generated method stub
		List<Object> result = this.database
				.executeHql("select news from News as news where news.id=125");
		return result;
	}

	public boolean isStored(String url) {
		// TODO Auto-generated method stub

		String hql = "select news from News as news where news.url=?";
		List<Object> result = this.database.executeHql(hql, url);

		if (result == null || result.isEmpty())
			return false;
		return true;
	}

	public List<Object> loadNewsByTopic(String topic) {
		// TODO Auto-generated method stub

		String hql = "select news from News as news where news.topic=?";
		List<Object> result = this.database.executeHql(hql, topic);

		return result;
	}
}
