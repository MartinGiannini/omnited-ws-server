package coop.bancocredicoop.omnited.model;

import java.util.Set;

public class SessionDatos {

    private Integer idUsuario;
    private Integer idPerfil;    
    private Set<Integer> idSectores;

    public SessionDatos(Integer idUsuario, Integer idPerfil, Set<Integer> idSectores) {
        this.idUsuario = idUsuario;
        this.idPerfil = idPerfil;
        this.idSectores = idSectores;
    }

    public SessionDatos() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(Integer idPerfil) {
        this.idPerfil = idPerfil;
    }

    public Set<Integer> getIdSectores() {
        return idSectores;
    }

    public void setIdSectores(Set<Integer> idSectores) {
        this.idSectores = idSectores;
    }
}