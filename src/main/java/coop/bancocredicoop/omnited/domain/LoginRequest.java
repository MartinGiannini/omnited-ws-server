package coop.bancocredicoop.omnited.domain;

public class LoginRequest {

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    private String ticket;
}
