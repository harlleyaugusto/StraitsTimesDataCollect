package edu.astar.ihpc.StraitsTimes.database.dto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "News")
public class News extends BaseDto
{
    private String title;
    private String url;
    private String author;
    private String link;
    private String date;
    private String topic;
    
    @Override
    @Id
    @TableGenerator(name = "TABLEGEN", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "TABLEGEN")
    @Column(name = "id")
    public Long getId()
    {
        return super.getId();
    }

    public void setId(Long id)
    {
        super.setId(id);
    }
   
    
    @Column(name = "title", length = 500, nullable = true, unique = false)
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title= title;
    }
    
    @Column(name = "url", length = 5000, nullable = false, unique = false)
    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url= url;
    }
    
    @Column(name = "author", length = 200, nullable = true, unique = false)
    public String getAuthor()
    {
        return author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    @Column(name = "topic", length = 500, nullable = true, unique = false)
    public String getTopic()
    {
        return topic;
    }
    
    public void setTopic(String topic)
    {
        this.topic = topic;
    }
    
    @Column(name = "date", nullable = true)
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}