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
@Table(name = "User")
public class User extends BaseDto
{
    private String name;
    
    
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
    
    @Column(name = "name", length = 500, nullable = false, unique = false)
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
}