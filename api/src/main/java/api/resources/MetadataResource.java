package api.resources;


import com.kumuluz.ee.discovery.annotations.DiscoverService;
import entities.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import pojo.Metadata;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("metadata")
@ApplicationScoped
@Tags(value = @Tag(name = "authentication"))
public class MetadataResource {

    @Inject
    @DiscoverService(value = "profileservice", version = "1.0.x", environment = "dev")
    private Optional<WebTarget> profileServiceWebTarget;

    @Inject
    @DiscoverService(value = "userservice", version = "1.0.x", environment = "dev")
    private Optional<WebTarget> userServiceWebTarget;

    @Operation(
            summary = "Get metadata for milestone",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Skiprope metadata."),
                    @ApiResponse(responseCode = "400", description = "Getting of metadata failed."),
            }
    )
    @GET
    public Response registerUser() {
        List<String> microservices = new ArrayList<String>();
        if(profileServiceWebTarget.isPresent()) {
            WebTarget t = profileServiceWebTarget.get();
            microservices.add(t.getUri().toString());
        }

        if(userServiceWebTarget.isPresent()) {
            WebTarget t = userServiceWebTarget.get();
            microservices.add(t.getUri().toString());
        }

        Metadata metadata = new Metadata();
        metadata.setClani(new String[]{"bb3172","mc0239"});
        metadata.setOpisProjekta("Projekt implementira aplikacijo za streamanje glasbe.");
        metadata.setMikrostoritve(microservices.toArray(new String[0]));
        metadata.setGithub(new String[]{"https://github.com/Kolebnica/UserService",
                "https://github.com/Kolebnica/Frontend",
                "https://github.com/Kolebnica/ProfileService",
                "https://github.com/Kolebnica/SkipRope"});
        metadata.setTravis(new String[]{"https://travis-ci.org/Kolebnica/UserService",
                "https://travis-ci.org/Kolebnica/Frontend",
                "https://travis-ci.org/Kolebnica/ProfileService"});
        metadata.setDockerhub(new String[]{"https://hub.docker.com/r/skiprope/userservice/",
                "https://hub.docker.com/r/skiprope/profileservice/",
                "https://hub.docker.com/r/skiprope/frontend/"});
        return Response.ok(metadata).build();
    }
}
