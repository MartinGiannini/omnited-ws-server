package coop.bancocredicoop.omnited.exposition;

public class EstadoDTO {
    private int idEstado;
    private String estadoNombre;
    private boolean estadoProductivo;
    private boolean estadoDedicadoUsuarioFinal;

    public EstadoDTO() {
    }

    // Constructor con argumentos
    public EstadoDTO(int id, String nombre, boolean productivo, boolean dedicadoUsuarioFinal) {
        this.idEstado = id;
        this.estadoNombre = nombre;
        this.estadoProductivo = productivo;
        this.estadoDedicadoUsuarioFinal = dedicadoUsuarioFinal;
    }
    
    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int id) {
        this.idEstado = id;
    }

    public String getEstadoNombre() {
        return estadoNombre;
    }

    public void setEstadoNombre(String nombre) {
        this.estadoNombre = nombre;
    }

    public boolean isEstadoProductivo() {
        return estadoProductivo;
    }

    public void setEstadoProductivo(boolean productivo) {
        this.estadoProductivo = productivo;
    }

    public boolean isEstadoDedicadoUsuarioFinal() {
        return estadoDedicadoUsuarioFinal;
    }

    public void setEstadoDedicadoUsuarioFinal(boolean dedicadoUsuarioFinal) {
        this.estadoDedicadoUsuarioFinal = dedicadoUsuarioFinal;
    }

    
}