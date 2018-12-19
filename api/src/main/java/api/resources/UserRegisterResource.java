package api.resources;


import beans.crud.UserBean;
import beans.external.ProfileServiceBean;
import com.kumuluz.ee.logs.cdi.Log;
import entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import pojo.ResponseError;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("register")
@ApplicationScoped
@Tags(value = @Tag(name = "authentication"))
@Log
public class UserRegisterResource{

    @Inject
    UserBean userBean;

    @Inject
    ProfileServiceBean profileServiceBean;

    @Operation(
            summary = "User registration",
            responses = {
                    @ApiResponse(responseCode = "200", description = "User registration successful."),
                    @ApiResponse(responseCode = "400", description = "User registration failed."),
            },
            requestBody = @RequestBody(
                    content = @Content(schema = @Schema(implementation = User.class))
            )
    )
    @PUT
    public Response registerUser(@RequestBody User user) {
        if(userBean.getUserByUsername(user.getUsername()) != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ResponseError(400, "This username is already registered."))
                    .build();
        } else {
            user = userBean.insertUser(user);
            // TODO: what if profile creation fails?
            boolean success = profileServiceBean.createUserProfile(user.getId());
            return Response.ok(user).build();
        }
    }

}
