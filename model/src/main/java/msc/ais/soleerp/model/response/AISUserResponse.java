package msc.ais.soleerp.model.response;

import msc.ais.soleerp.model.AISUser;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class AISUserResponse {

    private final String tokenId;
    private final AISUser user;

    public AISUserResponse(AISUser user, String tokenId) {
        this.user = user;
        this.tokenId = tokenId;
    }

    public AISUser getUser() {
        return user;
    }

    public String getTokenId() {
        return tokenId;
    }
}
