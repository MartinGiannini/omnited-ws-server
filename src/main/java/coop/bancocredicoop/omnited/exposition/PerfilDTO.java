package coop.bancocredicoop.omnited.exposition;

public class PerfilDTO {
    private int idPerfil;
    private String perfilNombre;

    public PerfilDTO(int id, String nombre) {
        this.idPerfil = id;
        this.perfilNombre = nombre;
    }

    public PerfilDTO() {}

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int id) {
        this.idPerfil = id;
    }

    public String getPerfilNombre() {
        return perfilNombre;
    }

    public void setPerfilNombre(String nombre) {
        this.perfilNombre = nombre;
    }
}