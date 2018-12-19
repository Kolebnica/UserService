package beans.external;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import entities.User;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class ProfileServiceBean {

    @Inject
    @DiscoverService(value = "profileservice", version = "1.0.x", environment = "dev")
    private Optional<WebTarget> profileServiceWebTarget;

    @Counted(name = "callProfileService", monotonic = true)
    public User getUserProfile(int profileId) {
        if(profileServiceWebTarget.isPresent()) {
            WebTarget t = profileServiceWebTarget.get();
            // TODO
            // Profile p = t.path("api/userprofile/" + profileId).request().get(Profile.class);

            return null;
        }
        // no service found
        return null;
    }

    public boolean createUserProfile(int profileId) {
        if (profileServiceWebTarget.isPresent()) {
            WebTarget t = profileServiceWebTarget.get();
            Response resp = t.path("api/profiles/" + profileId).request().post(null);
            return (resp.getStatus() == 201);
        }
        return false;
    }
}
