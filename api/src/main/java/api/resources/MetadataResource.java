package api.resources;

import com.kumuluz.ee.logs.cdi.Log;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.metrics.annotation.Counted;
import pojo.Metadata;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("metadata")
@ApplicationScoped
@Tags(value = @Tag(name = "metadata"))
@Log
public class MetadataResource {

    private static final Logger LOG = LogManager.getLogger(MetadataResource.class.getName());

    @Operation(
            summary = "Get metadata for milestone",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Skiprope metadata."),
                    @ApiResponse(responseCode = "400", description = "Getting of metadata failed."),
            }
    )
    @GET
    @Counted(name = "metadataCall", monotonic = true)
    public Response getMetadata() {

        LOG.info("New metadata call :).");

        Metadata metadata = new Metadata();
        metadata.setClani(new String[]{"bb3172","mc0239"});
        metadata.setOpisProjekta("Projekt implementira aplikacijo za streamanje glasbe.");
        metadata.setMikrostoritve(new String[]{"http://159.122.179.108:30000/api/users","http://159.122.179.108:30001/api/profiles"});
        metadata.setGithub(new String[]{"https://github.com/Kolebnica/UserService",
                "https://github.com/Kolebnica/ProfileService"});
        metadata.setTravis(new String[]{"https://travis-ci.org/Kolebnica/UserService",
                "https://travis-ci.org/Kolebnica/ProfileService"});
        metadata.setDockerhub(new String[]{"https://hub.docker.com/r/skiprope/userservice/",
                "https://hub.docker.com/r/skiprope/profileservice/"});
        return Response.ok(metadata).build();
    }
}
