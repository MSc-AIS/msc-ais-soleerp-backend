package msc.ais.soleerp.service;

import msc.ais.soleerp.model.User;

import java.util.Optional;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 19/2/2021.
 */
public class UserServiceImpl implements UserService {

    @Override
    public Optional<String> signUp(User user) {
        return Optional.empty();
    }

    @Override
    public Optional<String> singIn(String email, char[] password) {
        return Optional.empty();
    }

    @Override
    public boolean signOut(String tokenId) {
        return false;
    }
}
