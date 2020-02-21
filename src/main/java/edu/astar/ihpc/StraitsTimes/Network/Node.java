package edu.astar.ihpc.StraitsTimes.Network;

import com.google.gdata.util.common.base.Escaper;

import edu.astar.ihpc.StraitsTimes.database.dto.User;
import edu.astar.ihpc.StraitsTimes.database.model.UserBO;

public class Node {
	private Long likeCount;
	private Long newsCount;
	private Long commentCount;
	private User user;
	private UserBO userBo;
	

	public Node(User user) {
		this.user = user;
		
		userBo = new UserBO();
		this.likeCount = userBo.getCountLikes(user);
		this.newsCount = userBo.getNewsCount(user);
		this.commentCount = userBo.getCommentCount(user);
		
	}

	public void setLikeCount(Long likeCount) {
		this.likeCount = likeCount;
	}

	public Long getLikeCount() {
		return this.likeCount;
	}

	public void setNewsCount(Long newsCount) {
		this.newsCount = newsCount;
	}

	public Long getNewsCount() {
		return this.likeCount;
	}

	public void setCommentCount(Long commentCount) {
		this.commentCount = commentCount;
	}

	public Long getCommentCount() {
		return this.commentCount;
	}

	public String printGephi() {
		// TODO Auto-generated method stub
		String nodeGephi = "";
		
		
		nodeGephi += "\n\t\t\t<node id='"+ user.getId() + "' label='" + user.getName().replace("'", "") + "' >\n";
		nodeGephi += "\t\t\t\t<attvalues>\n";
		nodeGephi += "\t\t\t\t\t<attvalue for='0' value='"+ this.likeCount +"'/>\n";
		nodeGephi += "\t\t\t\t\t<attvalue for='1' value='"+ this.commentCount +"'/>\n";
		nodeGephi += "\t\t\t\t\t<attvalue for='2' value='"+ this.newsCount +"'/>\n";
		nodeGephi += "\t\t\t\t</attvalues>\n";
		nodeGephi += "\t\t\t</node>";
		return nodeGephi;
	}
}
