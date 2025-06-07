package coop.bancocredicoop.omnited.exposition;

public class PermisoDTO {
    private int idPermiso;
    private String permisoNombre;

    public PermisoDTO(int id, String nombre) {
        this.idPermiso = id;
        this.permisoNombre = nombre;
    }

    public PermisoDTO() {
    }

    public int getIdPermiso() {
        return idPermiso;
    }

    public void setIdPermiso(int id) {
        this.idPermiso = id;
    }

    public String getPermisoNombre() {
        return permisoNombre;
    }

    public void setPermisoNombre(String nombre) {
        this.permisoNombre = nombre;
    }
}