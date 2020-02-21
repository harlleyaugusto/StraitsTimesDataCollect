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
public class CommentBO extends BaseBO {

	/**
	 * Constructor.
	 */
	public CommentBO() {
		super();
	}

	/**
	 * Constructor with database as parameter.
	 * 
	 * @param database
	 */
	public CommentBO(HibernateDao database) {
		super(database);
	}

	/**
	 * Load comment from database.
	 * 
	 * @param id_comment
	 * 
	 * @return Comment.
	 */
	public Comment loadCommentByIdComment(Long idComment) {
		List<Object> result = new ArrayList<Object>();

		String hql = "select comment from Comment as comment where comment.idComment = ?";
		result = this.database.executeHql(hql, idComment);

		if ((result == null) || (result.isEmpty())) {
			return null;
		}

		return (Comment) result.get(0);
	}

	public List<Object> getReplyByUser(User user) {
		// TODO Auto-generated method stub

		String hql = "select comment from Comment as comment, User as user where comment.user=? and comment.user = user.id and replyTo is not null";
		List<Object> result = this.database.executeHql(hql, user);
		return result;
	}

	public boolean isStored(Long id_comment) {
		// TODO Auto-generated method stub

		String hql = "select comment from Comment as comment where comment.idComment=?";
		List<Object> result = this.database.executeHql(hql, id_comment);

		if (result == null || result.isEmpty())
			return false;
		return true;
	}
}
