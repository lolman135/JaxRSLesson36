package controller;

import dto.user.request.UserDtoRequest;
import dto.user.response.UserDtoCreateResponse;
import entity.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Link;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.experimental.Delegate;
import service.user.UserService;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    private UserService userService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createUser(final UserDtoRequest request) {

        User user = userService.create(request);
        if (user != null) {
            return Response.
                    ok().
                    entity(UserDtoCreateResponse.of(true, user))
                    .build();
        } else {
            return Response.
                    ok().
                    entity(UserDtoCreateResponse.of(false, user))
                    .build();
        }
    }

    @GET
    @Path("{id: [1-9][0-9]*}")
    public Response getUserById(@PathParam("id") final Long id) {
        User user = userService.getById(id);
        if (user != null) {
            return Response.
                    ok().
                    entity(UserDtoCreateResponse.of(true, user))
                    .build();
        } else {
            return Response.
                    ok().
                    entity(UserDtoCreateResponse.of(false, user))
                    .build();
        }
    }

    @GET
    public Response getAllUsers(){
        List<User> users = userService.getAll();
        if (users != null && !users.isEmpty()){
            return Response.ok(users).build();
        } else{
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity("No users found")
                    .build();
        }
    }

    @PUT
    @Path("{id: [1-9][0-9]*}")
    public Response updateUser(@PathParam("id") final Long id, final UserDtoRequest request){
        User user = userService.update(id, request);
        if (user != null) {
            return Response.
                    ok().
                    entity(UserDtoCreateResponse.of(true, user))
                    .build();
        } else {
            return Response.
                    ok().
                    entity(UserDtoCreateResponse.of(false, user))
                    .build();
        }
    }

    @DELETE
    @Path("{id: [1-9][0-9]*}")
    public Response deleteUserById(@PathParam("id") final Long id){
        boolean isDeleted = userService.deleteById(id);
        if (isDeleted){
            return Response
                    .ok()
                    .entity("User with id " + id + " Successfully deleted")
                    .build();
        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("No user with id " + id)
                    .build();
        }
    }

    @GET
    @Path("fetchByFirstName/{firstName: [a-zA-Z]*}")
    public Response fetchUsersByFirstName(@PathParam("firstName") final String firstName){
        List<User> users = userService.fetchByFirstName(firstName);
        if (users != null && !users.isEmpty()){
            return Response.ok(users).build();
        } else{
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity("No users found")
                    .build();
        }
    }

    @GET
    @Path("fetchByLastName/{lastName: [a-zA-Z]*}")
    public Response fetchUsersByжName(@PathParam("lastName") final String lastName){
        List<User> users = userService.fetchByLastName(lastName);
        if (users != null && !users.isEmpty()){
            return Response.ok(users).build();
        } else{
            return Response
                    .status(Response.Status.NO_CONTENT)
                    .entity("No users found")
                    .build();
        }
    }
}
