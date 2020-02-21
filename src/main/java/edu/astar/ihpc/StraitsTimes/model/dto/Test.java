package edu.astar.ihpc.StraitsTimes.model.dto;
  
import edu.astar.ihpc.StraitsTimes.model.dao.HibernateDao;

public class Test
{

    public static void main(String[] args) {
        System.out.println("Hibernate one to one (Annotation)");
        HibernateDao database = new HibernateDao(); 

        database.beginTransaction();

        Tweet tweet = new Tweet();
        
        tweet.setContent("galo campeao da libertadores");
        tweet.setUsername("harlley");
        //nome.setId(new Long (100579));
        database.update(tweet);
        
        database.endTransaction();
    }
}