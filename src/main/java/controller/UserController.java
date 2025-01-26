package controller;

import dto.user.request.UserDtoRequest;
import dto.user.response.UserDtoCreateResponse;
import entity.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.user.UserService;

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
}
