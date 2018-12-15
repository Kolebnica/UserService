package api.resources;

import beans.crud.UserBean;
import beans.external.ProfileServiceBean;
import com.kumuluz.ee.logs.cdi.Log;
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
@Path("login")
@ApplicationScoped
@Tags(value = @Tag(name = "authentication"))
@Log
public class UserLoginResource {

    @Inject
    UserBean userBean;

    @Inject
    ProfileServiceBean profileServiceBean;


    @Operation(
            summary = "User login",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User login successful."),
                    @ApiResponse(responseCode = "401", description = "User login failed."),
            },
            parameters = {
                    @Parameter(name = "username", in = ParameterIn.QUERY),
                    @Parameter(name = "password", in = ParameterIn.QUERY),
            }
    )
    @POST
    public Response loginUser(
            @QueryParam("username") String username,
            @QueryParam("password") String password) {

        User u = userBean.getUserByUsername(username);
        if(u != null) {
            if(u.getPassword().equals(password)) {
                return Response.ok(u).build();
            }
        }

        return Response.status(Response.Status.UNAUTHORIZED)
                .entity(new ResponseError(401, "This combination of username and password doesn't exist."))
                .build();
    }
}
