package coop.bancocredicoop.omnited.exposition;

public class EstrategiaDTO {
    private int idEstrategia;
    private String estrategiaNombre;

    public EstrategiaDTO(int idEstrategia, String nombre) {
        this.idEstrategia = idEstrategia;
        this.estrategiaNombre = nombre;
    }

    public EstrategiaDTO() {
    }

    public int getIdEstrategia() {
        return idEstrategia;
    }

    public void setIdEstrategia(int idEstrategia) {
        this.idEstrategia = idEstrategia;
    }

    public String getEstrategiaNombre() {
        return estrategiaNombre;
    }

    public void setEstrategiaNombre(String nombre) {
        this.estrategiaNombre = nombre;
    }
}