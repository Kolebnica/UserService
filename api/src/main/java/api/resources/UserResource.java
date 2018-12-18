package api.resources;


import beans.crud.UserBean;
import com.kumuluz.ee.logs.cdi.Log;
import entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("users")
@ApplicationScoped
@Tags(value = @Tag(name = "users"))
@Log
public class UserResource {

    @Inject
    UserBean userBean;

    @Operation(
            summary = "Get users",
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of users"),
            }
    )
    @GET
    public Response getUsers() {
        return Response.ok(userBean.getAllUsers()).build();
    }

    @Operation(
            summary = "Get user",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User with given ID"),
                    @ApiResponse(responseCode = "404", description = "User with ID not found"),
            },
            parameters = {
                    @Parameter(name = "userId", in = ParameterIn.PATH),
            }
    )
    @GET
    @Path("{userId}")
    public Response getUserById(@PathParam("userId") int userId) {
        User a = userBean.getUser(userId);
        if (a == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(a).build();
    }
}
