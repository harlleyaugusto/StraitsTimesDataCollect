package edu.astar.ihpc.StraitsTimes.database.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.astar.ihpc.StraitsTimes.database.dao.HibernateDao;
import edu.astar.ihpc.StraitsTimes.database.dto.BaseDto;

/**
 * Base BO.
 * 
 */
public class BaseBO
{
    /** Database instance. */
    protected HibernateDao database;

    /** Logger instance. */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Generic and empty constructor.
     */
    public BaseBO()
    {
        this.database = new HibernateDao();
    }

    /**
     * Constructor that receives the database.
     * 
     * @param database
     *            Database instance.
     */
    public BaseBO(HibernateDao database)
    {
        this.database = database;
    }

    /**
     * Begin transaction.
     */
    public void beginTransaction()
    {
        this.database.beginTransaction();
    }

    /**
     * End transaction.
     */
    public void endTransaction()
    {
        this.database.endTransaction();
    }

    /**
     * Update DTO.
     * 
     * @param dto
     *            DTO instance.
     */
    public void update(BaseDto dto)
    {
        this.database.update(dto);
    }

    /**
     * Update DTO.
     * 
     * @param dto
     *            DTO instance.
     * @return BaseDto.
     */
    public BaseDto updateDto(BaseDto dto)
    {
        return this.database.updateDto(dto);
    }

    /**
     * Save DTO.
     * 
     * @param dto
     *            DTO instance.
     * @result New DTO id.
     */
    public void save(BaseDto dto)
    {
        dto.setId((Long) this.database.save(dto));
    }
}
