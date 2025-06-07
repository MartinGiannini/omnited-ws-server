package coop.bancocredicoop.omnited.exposition;

public class HabilidadDTO {
    private int idHabilidad;
    private String habilidadNombre;
    private int habilidadValor;

    public HabilidadDTO(int idHabilidad, String nombreHabilidad, int valorHabilidad) {
        this.idHabilidad = idHabilidad;
        this.habilidadNombre = nombreHabilidad;
        this.habilidadValor = valorHabilidad;
    }

    public HabilidadDTO() {}

    public int getIdHabilidad() {
        return idHabilidad;
    }

    public void setIdHabilidad(int id) {
        this.idHabilidad = id;
    }

    public String getHabilidadNombre() {
        return habilidadNombre;
    }

    public void setHabilidadNombre(String nombre) {
        this.habilidadNombre = nombre;
    }

    public int getHabilidadValor() {
        return habilidadValor;
    }

    public void setHabilidadValor(int valor) {
        this.habilidadValor = valor;
    }
}