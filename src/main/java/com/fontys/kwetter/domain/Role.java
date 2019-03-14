package domain;

/**
 * Role a user can have on the platform.
 * @author Arnoud Bevers
 * @project kwetter
 */
public enum Role {
    ADMINISTRATOR,
    MODERATOR,
    USER;

    @Override
    public String toString() {
        // Replace with lowercase, except first letter
        return this.name().substring(0,1) + this.name().substring(1).toLowerCase();
    }}
