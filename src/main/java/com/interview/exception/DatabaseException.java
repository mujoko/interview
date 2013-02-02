package com.interview.exception;
/**
 * @author mujoko
 */
public class DatabaseException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4545414244564739247L;

	/**
     * @param msg
     */
    public DatabaseException(String msg) {
        super(msg);
    }

}
