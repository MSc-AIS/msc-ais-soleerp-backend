package msc.ais.soleerp.service;

import msc.ais.soleerp.model.AISUser;
import msc.ais.soleerp.model.response.AISUserResponse;
import msc.ais.soleerp.service.exception.ServiceException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class UserServiceTest {

    @Disabled
    @Test
    public void testSignUp() throws Exception {

        UserService userService = ServiceFactory.createUserService();

        AISUser user = AISUser.builder()
            .username("KRtester3")
            .password(new char[] {'a', 'b', 'c'})
            .email("KRtester3@test.com")
            .build();

        AISUserResponse response = userService.signUp(user)
            .orElseThrow(() -> new ServiceException("Error... Failed to signUp user: " + user.getEmail()));

        System.out.println("The created token id is: " + response.getTokenId());
    }

}
