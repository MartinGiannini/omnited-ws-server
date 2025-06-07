package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class SessionDTO {

    private Set<Integer> idSectores;
    private UsuarioDTO usuario;

    public SessionDTO(Set<Integer> idSectores, UsuarioDTO usuario) {
        this.idSectores = idSectores;
        this.usuario = usuario;
    }

    public SessionDTO() {
    }

    public Set<Integer> getIdSectores() {
        return idSectores;
    }

    public void setIdSectores(Set<Integer> idSectores) {
        this.idSectores = idSectores;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "SessionDTO{"
                + "idSectores=" + idSectores
                + ", usuario=" + usuario
                + '}';
    }
}