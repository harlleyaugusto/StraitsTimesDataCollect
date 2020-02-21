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
@Table(name = "Comment")
public class Comment extends BaseDto
{
    private String content;
    private News newsId;
    private Long countLike;
    private Long idComment;
    private User user;
    private Comment replyTo;
    
    
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
    
    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "comment_newsFK")
    @JoinColumn(name = "newsId", updatable = false, nullable = true)
    @Fetch(FetchMode.SELECT)
    public News getNewsId()
    {
        return newsId;
    }

    public void setNewsId(News newsId)
    {
        this.newsId = newsId;
    }
    
    @Column(name = "content", length = 20000, nullable = true, unique = false)
    public String getContent()
    {
        return content;
    }
    
    public void setContent(String content)
    {
        this.content = content;
    }
    
    @Column(name = "countLike", nullable = true, unique = false)
    public Long getCountLike()
    {
        return countLike;
    }
    
    public void setCountLike(Long countLike)
    {
        this.countLike = countLike;
    }
    
    @Column(name = "idComment", nullable = true, unique = false)
    public Long getIdComment()
    {
        return idComment;
    }
    
    public void setIdComment(Long idComment)
    {
        this.idComment = idComment;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "comment_userFK")
    @JoinColumn(name = "userId", updatable = false, nullable = true)
    @Fetch(FetchMode.SELECT)
    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
    @ForeignKey(name = "comment_replyToFK")
    @JoinColumn(name = "replyTo", updatable = false, nullable = true)
    @Fetch(FetchMode.SELECT)
    public Comment getReplyTo()
    {
        return replyTo;
    }

    public void setReplyTo(Comment replyTo)
    {
        this.replyTo = replyTo;
    }
}