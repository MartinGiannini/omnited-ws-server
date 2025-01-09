package coop.bancocredicoop.omnited.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(
        namespace = "http://www.yale.edu/tp/cas",
        name = "serviceResponse")
public class ServiceResponse {

    private AuthenticationSuccess authenticationSuccess;
    private AuthenticationFailure authenticationFailure;

    @XmlElement(namespace = "http://www.yale.edu/tp/cas")
    public AuthenticationSuccess getAuthenticationSuccess() {
        return authenticationSuccess;
    }

    public void setAuthenticationSuccess(AuthenticationSuccess authenticationSuccess) {
        this.authenticationSuccess = authenticationSuccess;
    }

    @XmlElement(namespace = "http://www.yale.edu/tp/cas")
    public AuthenticationFailure getAuthenticationFailure() {
        return authenticationFailure;
    }

    public void setAuthenticationFailure(AuthenticationFailure authenticationFailure) {
        this.authenticationFailure = authenticationFailure;
    }

    public boolean success() {
        return authenticationSuccess != null && authenticationSuccess.hasUser();
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ServiceResponse{");
        sb.append("authenticationSuccess=").append(authenticationSuccess);
        sb.append(", authenticationFailure='").append(authenticationFailure).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
