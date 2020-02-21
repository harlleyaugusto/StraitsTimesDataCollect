package edu.astar.ihpc.StraitsTimes.database.dto;
  
import java.util.List;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

import edu.astar.ihpc.StraitsTimes.database.dao.HibernateDao;
import edu.astar.ihpc.StraitsTimes.database.model.NewsBO;

public class Test
{

    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
//        System.out.println("Hibernate one to one (Annotation)");
        HibernateDao database = new HibernateDao(); 
//
//        database.beginTransaction();
//
//        News i_new = new News();
//        i_new.setTitle("Galo Campeão da libertadores");
//        i_new.setUrl("http://www.atletico.com.br");
//        
//        database.update(i_new);
//
//        
//        database.endTransaction();
//    	
//    	AnnotationConfiguration config = new AnnotationConfiguration();
  //  	config.addAnnotatedClass(News.class);
   // 	config.configure();
   // 	new SchemaExport(config).create(true, true);
    	
//    	NewsBO newsBo = new NewsBO(database);
//    	
//    	List<Object> newsList = newsBo.loadUncollectedCommentNews();
//    	
//    	
//    	for(Object o : newsList){
//    		
//    		News news = (News) o;
//            
//            System.out.println("url: " +news.getUrl()+ " title: " + news.getTitle());
//    		
//    		
//    	}
    }
}