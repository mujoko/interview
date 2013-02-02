
package com.interview.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * @author mujoko
 */
@MappedSuperclass
public abstract class MasterEntityImpl extends BaseEntity {
	
	/**
     * Serial ID.
     */
    private static final long serialVersionUID = -2236989292136878302L;

    /**
     * Number of Change length.
     */
    private static final int NOC_LENGTH = 6;

    @Column(name = "NUMBER_OF_CHANGE", length = NOC_LENGTH)
    @Version
    private Integer numberOfChange = 0;

    /** Entity Updated On. */
    @Column(name = "UPDATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    /** Entity Created On. */
    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
     

    
    

    public Integer getNumberOfChange() {
		return numberOfChange;
	}

	public void setNumberOfChange(Integer numberOfChange) {
		this.numberOfChange = numberOfChange;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

}
