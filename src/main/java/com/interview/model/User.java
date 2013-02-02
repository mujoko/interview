package com.interview.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "USERS")
@XmlRootElement
public class User extends BaseEntity  {

	/** Serial ID. */
    private static final long serialVersionUID = -6821041239757408760L;

    /** Length of column USERNAME. */
    private static final int USERNAME_PASSWORD_LENGTH = 32;

    /** Length of column NAME. */
    private static final int NAME_LENGTH = 128;


    /** username. */
    @Column(name = "USERNAME", length = USERNAME_PASSWORD_LENGTH)
    private String username;

    /** password. */
    @Column(name = "USER_PASSWORD", length = USERNAME_PASSWORD_LENGTH)
    private String password;

    /** name of the user. */
    @Column(name = "name", length = NAME_LENGTH)
    private String name;

    
    @Column(name = "FIRST_NAME", length = NAME_LENGTH)
    private String firstName;
    
    
    @Column(name = "LAST_NAME", length = NAME_LENGTH)
    private String lastName;

    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

    
}
