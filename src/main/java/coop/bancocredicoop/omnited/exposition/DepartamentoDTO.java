package coop.bancocredicoop.omnited.exposition;

public class DepartamentoDTO {
    private int idDepartamento;
    private String departamentoNombre;

    public DepartamentoDTO() {
    }

    public DepartamentoDTO(int id, String nombre) {
        this.idDepartamento = id;
        this.departamentoNombre = nombre;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int id) {
        this.idDepartamento = id;
    }

    public String getDepartamentoNombre() {
        return departamentoNombre;
    }

    public void setDepartamentoNombre(String nombre) {
        this.departamentoNombre = nombre;
    }
}