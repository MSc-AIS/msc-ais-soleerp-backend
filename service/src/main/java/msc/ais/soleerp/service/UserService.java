package msc.ais.soleerp.service;

import msc.ais.soleerp.model.AISUser;
import msc.ais.soleerp.model.response.AISUserResponse;

import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public interface UserService {

    Optional<AISUserResponse> signUp(AISUser user);

    Optional<String> singIn(String email, char[] password);

    boolean signOut(String tokenId);

}
