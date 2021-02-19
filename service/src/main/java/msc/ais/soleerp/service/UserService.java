package msc.ais.soleerp.service;

import msc.ais.soleerp.model.User;

import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public interface UserService {

    Optional<String> signUp(User user);

    Optional<String> singIn(String email, char[] password);

    boolean signOut(String tokenId);

}
