package msc.ais.soleerp.model;

/**
 * @author Konstantinos Raptis [kraptis at unipi.gr] on 20/2/2021.
 */
public class AISToken {

    private final String id;
    private final int userId;
    private final long createdTimestamp;

    private AISToken(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.createdTimestamp = builder.createdTimestamp;
    }

    public String getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public long getCreatedTimestamp() {
        return createdTimestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private String id;
        private int userId;
        private long createdTimestamp;

        public Builder tokenId(String id) {
            this.id = id;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder createdTimestamp(long timestamp) {
            this.createdTimestamp = timestamp;
            return this;
        }

        public AISToken build() {
            return new AISToken(this);
        }

    }

    @Override
    public String toString() {
        return "Token{" +
            "id='" + id + '\'' +
            ", userId=" + userId +
            ", createdTimestamp=" + createdTimestamp +
            '}';
    }

}
