package coop.bancocredicoop.omnited.message;

import coop.bancocredicoop.omnited.exposition.UsuarioDTO;
import java.util.Set;

public class UsuarioDatos {
    
    private IngresoDatos ingresoDatos;

    public IngresoDatos getIngresoDatos() {
        return ingresoDatos;
    }
    
    public void setIngresoDatos(IngresoDatos ingresoDatos) {
        this.ingresoDatos = ingresoDatos;
    }

    public static class IngresoDatos {
        private Set<Integer> idSectores;
        private UsuarioDTO usuario;
    
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
    }
}