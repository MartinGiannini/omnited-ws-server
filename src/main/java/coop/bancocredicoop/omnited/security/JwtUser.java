package coop.bancocredicoop.omnited.security;

public class JwtUser {

    private String id;
    private String username;
    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "JwtUser{"
                + "id='" + id + '\''
                + ", username='" + username + '\''
                + ", role='" + role + '\''
                + '}';
    }
}
