package edu.astar.ihpc.StraitsTimes.model.dto;

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
@Table(name = "tweet")
public class Tweet extends BaseDto
{
    private String content;
    private String username;
    private String link;
    
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
    
    @Column(name = "content", length = 1000, nullable = false, unique = true)
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    @Column(name = "username")
    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    
    @Column(name = "link")
    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }
}