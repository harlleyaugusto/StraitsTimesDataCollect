package edu.astar.ihpc.StraitsTimes.database.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

/**
 * Base dto.
 */
@MappedSuperclass
public class BaseDto {
	
	private Long id;

	/**
	 * Date of insertion in the database.
	 */
	private Date insertDate;


	/**
	 * Constructor.
	 */
	public BaseDto() {
		insertDate = new Date();
	}

	@Transient
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "insertDate", nullable = false)
	public Date getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

}
