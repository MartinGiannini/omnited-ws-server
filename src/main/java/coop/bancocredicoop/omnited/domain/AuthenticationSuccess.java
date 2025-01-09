package coop.bancocredicoop.omnited.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
        namespace = "http://www.yale.edu/tp/cas",
        name = "authenticationSuccess")
public class AuthenticationSuccess {

    private String user;
    private String proxyGrantingTicket;

    @XmlElement(namespace = "http://www.yale.edu/tp/cas")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @XmlElement(namespace = "http://www.yale.edu/tp/cas")
    public String getProxyGrantingTicket() {
        return proxyGrantingTicket;
    }

    public void setProxyGrantingTicket(String proxyGrantingTicket) {
        this.proxyGrantingTicket = proxyGrantingTicket;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AuthenticationSuccess{");
        sb.append("user='").append(user).append('\'');
        sb.append(", proxyGrantingTicket='").append(proxyGrantingTicket).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public boolean hasUser() {
        return this.user != null;
    }
}
