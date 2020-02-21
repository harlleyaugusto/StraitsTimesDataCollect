package edu.astar.ihpc.StraitsTimes.Crawler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import edu.astar.ihpc.StraitsTimes.database.dao.HibernateDao;
import edu.astar.ihpc.StraitsTimes.database.dto.Comment;
import edu.astar.ihpc.StraitsTimes.database.dto.News;
import edu.astar.ihpc.StraitsTimes.database.dto.User;
import edu.astar.ihpc.StraitsTimes.database.model.CommentBO;
import edu.astar.ihpc.StraitsTimes.database.model.NewsBO;
import edu.astar.ihpc.StraitsTimes.database.model.UserBO;

public class StraitsTimes {
	
	public static void fetchNews(String topic){
		WebDriver driver = new FirefoxDriver();
		HibernateDao database = new HibernateDao(); 
		
		driver.get("http://www.straitstimes.com/searchpage/" + topic);
		List<WebElement> pags = driver.findElements(By.xpath("//div[@class='gsc-cursor-page']"));
		
		Integer numPag = new Integer(pags.get(pags.size()-1).getText());
		Integer count = 1; 
		
		Integer countNewNews = 0;
		
		NewsBO newsBo = new NewsBO();
		
		while(count <= numPag){
			
			WebElement cursor = driver.findElement(By.xpath("//div[(@class='gsc-cursor-page' or @class='gsc-cursor-page gsc-cursor-current-page') and .=" + count + "]"));
			cursor.click();
			long end = System.currentTimeMillis() + 5000;
			while (System.currentTimeMillis() < end) {
			}
			
			List<WebElement> titles = driver.findElements(By.xpath("//div[@class='gs-title gsc-table-cell-thumbnail gsc-thumbnail-left']/a[@class='gs-title' and @target]"));
			
			
	        database.beginTransaction();
			
			for (WebElement title : titles) {
				
				if(!newsBo.isStored(title.getAttribute("href"))) {
					News i_new = new News();
					
					//System.out.println(title.getText());
					
					i_new.setTitle(title.getText());
					i_new.setUrl(title.getAttribute("href"));
					i_new.setTopic(topic.toLowerCase());
					
					database.save(i_new);
					countNewNews++;
				}
			}
			
			database.endTransaction();
			count++;
		}
		System.out.println("count new news: " + countNewNews);
	}
	
	private static void updateComments(String topic) {
		
		WebDriver driver = new FirefoxDriver();
		WebElement loadMore = null; 

		List<WebElement> comments;
		
		CommentBO commentBo = null;
		
		HibernateDao database = new HibernateDao();
		NewsBO newsBo = new NewsBO(database);
    	
		List<Object> newsList = newsBo.loadNewsByTopic(topic);
    	
		//List<Object> newsList = newsBo.loadNewsById();
    	
    	for(Object o : newsList){
    		
    		News news = (News) o;  
			driver.get(news.getUrl());
			
			if ((driver.findElements(By.xpath("//div[@class='story']")).size() > 0)){
				WebElement titleElement = driver.findElement(By.xpath("//div[@class='story']"));
				news.setTitle(titleElement.findElement(By.xpath("//h1")).getText());
			}
			
			if ((driver.findElements(By.xpath("//div[@class='byline']")).size() > 0)){
				WebElement authorElement = driver.findElement(By.xpath("//div[@class='byline']"));
				news.setAuthor(authorElement.getText());
			}	
			
			if ((driver.findElements(By.xpath("//div[@class='published']")).size() > 0)){
				WebElement dateElement = driver.findElement(By.xpath("//div[@class='published']"));
				news.setDate((dateElement.getText()));
			}
			
			while (driver.findElements(By.xpath("//button[@type='button' and @class='dsq-button-small dsq-paginate-append-button']")).size() == 1){
				loadMore = driver.findElement(By.xpath("//button[@type='button' and @class='dsq-button-small dsq-paginate-append-button']"));
				loadMore.click();
				
				/*long end = System.currentTimeMillis() + 5000;
				while (System.currentTimeMillis() < end) {
					//if(cursor.isDisplayed()) break;
				}*/
			}
			
			comments = driver.findElements(By.xpath("//div[@class='dsq-full-comment']"));
			
			database.beginTransaction();
			for(WebElement commentElement : comments){
			
				commentBo = new CommentBO(database);
				Long id_comment = getIdComment(commentElement);
				Comment comment = null;
				
				if(commentBo.isStored(id_comment)){
					comment = commentBo.loadCommentByIdComment(id_comment);
				}else{
					comment = new Comment();
				}
				
				comment.setNewsId(news);
				comment.setIdComment(id_comment);
				
				
				UserBO userBo = new UserBO(database);
				String userName = getUserName(id_comment, commentElement);
				User user = userBo.loadUser(userName); 
				if(user != null) {
					comment.setUser(user);
				}
				else {
					user = new User();
					user.setName(userName);
					comment.setUser(user);
					database.save(user);
				}
				
				String content = getContent(id_comment, commentElement);
				comment.setContent(content);
				
				
				Long likes = getLikes(id_comment, commentElement);
				comment.setCountLike(likes);
				
				Long replyTo = getReply(id_comment, commentElement);
				commentBo = new CommentBO(database);
				Comment commentReplyTo = commentBo.loadCommentByIdComment(replyTo);  
				if(commentReplyTo != null) comment.setReplyTo(commentReplyTo);
				
				System.out.println("idCommment: " + id_comment + " userName: " + userName + " like: " + likes);
				database.saveOrUpdate(comment);
			}
			database.update(news);
			database.endTransaction();
		}
	}
	
	private static Long getReply(Long id_comment, WebElement commentElement) {
		// TODO Auto-generated method stub
		//WebElement replyElement = commentElement.findElement(By.xpath(".//span[@class='dsq-comment-header-time']"));
		
		if(commentElement.findElements(By.xpath(".//a[@title='Jump to comment']")).size() > 0){
			return new Long(commentElement.findElement(By.xpath(".//a[@title='Jump to comment']")).getAttribute("href").split("#comment-")[1]);
		}else return null;
	}

	private static Long getLikes(Long id_comment, WebElement commentElement) {
		// TODO Auto-generated method stub
		
		WebElement likeElement = null;
		
		if (commentElement.findElements(By.xpath(".//span[@class='dsq-user-like']")).size() > 0){
			likeElement = commentElement.findElement(By.xpath(".//span[@class='dsq-user-like']"));
		}else return new Long(0);
		
		boolean group_sep = (likeElement.findElements(By.tagName("a")).size()) == 2;
		boolean sep = (likeElement.findElements(By.tagName("a")).size()) == 1;
		boolean group = (likeElement.findElements(By.tagName("a")).size()) == 0;
				
		if(group_sep){
			Integer count = new Integer(commentElement.findElement(By.xpath(".//span[@id='dsq-like-pts-" + id_comment + "']")).getText().trim().split(" ")[1]);
			return new Long(count + 1);
		} else if(sep) {
			return new Long(1);
		} else if(group){
			Integer count = new Integer(commentElement.findElement(By.xpath(".//span[@id='dsq-like-pts-" + id_comment + "']")).getText().trim().split(" ")[0]);
			return new Long(count);
		} else return new Long(0);
				
	}

	private static String getContent(Long id_comment, WebElement comment) {
		// TODO Auto-generated method stub
		if (comment.findElements(By.xpath(".//div[@id='dsq-comment-text-" + id_comment + "']")).size() > 0){
			return comment.findElement(By.xpath(".//div[@id='dsq-comment-text-" + id_comment + "']")).getText();
		}
		return null;
	}

	private static String getUserName(Long id_comment, WebElement comment) {
		// TODO Auto-generated method stub
		if (comment.findElements(By.xpath(".//span[@id='dsq-author-user-" + id_comment + "']")).size() > 0){
			return comment.findElement(By.xpath(".//span[@id='dsq-author-user-" + id_comment + "']")).getText(); 
		}else {
			return comment.findElement(By.xpath(".//a[@id='dsq-author-user-" + id_comment + "']")).getText();
		}
		
		//return 
	
	}

	private static Long getIdComment(WebElement comment) {
		// TODO Auto-generated method stub
		
		WebElement idElement = comment.findElement(By.xpath(".//div[@class='dsq-comment-header']"));
		int size = idElement.getAttribute("id").split("-").length;
		
		return new Long (idElement.getAttribute("id").split("-")[size-1]);
		
	}


	public static void main(String[] arg){
	
		fetchNews("citibank");
		
		updateComments("citibank");
	}
	
}
