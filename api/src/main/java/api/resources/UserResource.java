package api.resources;


import beans.crud.UserBean;
import entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import pojo.ResponseError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("users")
@ApplicationScoped
@Tags(value = @Tag(name = "authentication"))
public class UserResource {

    @Inject
    UserBean userBean;

    @Operation(
            summary = "Returns all users in db",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Users."),
                    @ApiResponse(responseCode = "401", description = "Getting users failed."),
            }
    )
    @GET
    public Response getUsers() {
        return Response.ok(userBean.getAllUsers()).build();
    }
}
