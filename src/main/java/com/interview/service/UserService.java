package com.interview.service;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.interview.model.User;

/**
 *
 * @author Mujoko
 *
 */
@Path("v1")
public interface UserService {

    /**
     * @param user
     *            user need to be created.
     */
    @POST
    @Path("createUser")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public void create(User user);


    /**
     * @param user
     *            user need to be created.
     */
    @POST
    @Path("authenticate")
    @Produces({ MediaType.APPLICATION_JSON })
    public String authenticate(@FormParam("username")String  username, @FormParam("password") String password);


    /**
     * @param user
     *            user need to be created.
     */
    @POST
    @Path("createUser2")
    @Produces({ MediaType.APPLICATION_JSON })
    public String create(@FormParam("username")String  username, @FormParam("password") String password, @FormParam("firstName") String firstName, @FormParam("lastName") String lastName);


    
    /**
     *
     * @param user
     *    Entity to update.
     *
     */
    void update(User user);

    /**
     * @param parameters
     *            parameters of query.
     *@return {@link List}
     */
    List<User> find(String parameters);

    /**
     * find method.
     *
     * @param parameters
     *            customize where clause
     * @param start
     *            start record
     * @param limit
     *            limit record to be shown
     * @param sort
     *            sort by
     * @param dir
     *            directions asc/desc
     * @return list
     */
    List<User> find(String parameters, int start, int limit, String sort,
            String dir);

    /**
     * @param id
     *            pk of user.
     * @return user
     */
    @POST
    @Path("get/{userid}")
    @Produces({ MediaType.APPLICATION_JSON })
    User get(@PathParam("userid")String id);


    /**
     * Testing Only
     * @return
     */
    @GET
    @Path("user")
    @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    public User getUser();
    /**
     * @param id
     *            pk of user.
     */
    void delete(String id);

    /**
     * @param params
     *            params to count.
     * @return total of Records
     */
    Long count(String params);

    /**
     * @return total of Records
     */
    Long count();

    


    void deleteAll(final String[] ids);

	/**
	 * @return list
	*/
    @GET
    @Path("listuser")
    @Produces({ MediaType.APPLICATION_JSON })
    List<User> list();
   
}
