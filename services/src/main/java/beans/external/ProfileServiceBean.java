package beans.external;

import com.kumuluz.ee.discovery.annotations.DiscoverService;
import entities.User;
import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.WebTarget;
import java.util.Optional;

@ApplicationScoped
public class ProfileServiceBean {

    @Inject
    @DiscoverService(value = "profileservice", version = "1.0.x", environment = "dev")
    private Optional<WebTarget> profileServiceWebTarget;

    @Counted(name = "callProfileService", monotonic = true)
    public User getUserProfile() {
        if(profileServiceWebTarget.isPresent()) {
            WebTarget t = profileServiceWebTarget.get();

            User u = t.path("api/userprofile/1").request().get(User.class);

            return u;
        }

        // no service found
        return null;
    }
}
