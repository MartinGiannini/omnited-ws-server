package coop.bancocredicoop.omnited.security.cas;

import coop.bancocredicoop.omnited.domain.ServiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CasService {
    private static final Logger logger = LoggerFactory.getLogger(CasService.class);

    @Value("${cas.serverUrlPrefix}")
    private String casServerUrlPrefix;
    @Value("${cas.service}")
    private String casService;
    @Value("${cas.renew}")
    private String casRenew;
    @Value("${cas.serverLoginUrl}")
    private String casServerLoginUrl;
    
    public String validateTicket(String ticket) {
        try {
            UriComponents uri = UriComponentsBuilder.fromHttpUrl(casServerUrlPrefix + "/serviceValidate")
                    .queryParam("ticket", ticket)
                    .queryParam("service", casService)
                    .queryParam("renew", casRenew).build();
            RestTemplate rt = new RestTemplate();
            return rt.getForObject(uri.toUriString(), String.class);
        } catch (RestClientException e) {
            logger.error("validateTicket: ",e);
            return null;
        }
    }

    public ServiceResponse getServiceResponse(String response) throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(ServiceResponse.class);
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        return (ServiceResponse) unmarshaller.unmarshal(new ByteArrayInputStream(response.getBytes()));
    }

    public String casLoginUrl() {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(casServerLoginUrl)
                .queryParam("service", casService)
                .queryParam("renew", casRenew).build();
        return uri.toUriString();
    }
}