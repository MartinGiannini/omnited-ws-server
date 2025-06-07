package coop.bancocredicoop.omnited.exposition;

public class CondicionDTO {
    private int idCondicion;
    private String condicionNombre;

    public CondicionDTO(int id, String nombre) {
        this.idCondicion = id;
        this.condicionNombre = nombre;
    }

    public CondicionDTO() {}

    public int getIdCondicion() {
        return idCondicion;
    }

    public void setIdCondicion(int id) {
        this.idCondicion = id;
    }

    public String getCondicionNombre() {
        return condicionNombre;
    }

    public void setCondicionNombre(String nombre) {
        this.condicionNombre = nombre;
    }
}