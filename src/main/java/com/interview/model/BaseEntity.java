package com.interview.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author mujoko
 */
@MappedSuperclass
public class BaseEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6285251539010517474L;

	private static final int LENGTH = 32;
	@Version
	@Column(name = "number_of_change")
	private Integer numberOfChange = 0;

	/**
	 * Entity deleted.
	 */
	@Column(name = "DELETED", nullable = true)
	private Boolean deleted = false;

	/**
	 * Id which generated from hibernate hex.
	 */
	@Id
	@GeneratedValue(generator = "idGen")
	@GenericGenerator(strategy = "uuid.hex", name = "idGen")
	@Column(name = "ID", length = LENGTH)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public Integer getNumberOfChange() {
		return numberOfChange;
	}

	public void setNumberOfChange(Integer numberOfChange) {
		this.numberOfChange = numberOfChange;
	}

}
