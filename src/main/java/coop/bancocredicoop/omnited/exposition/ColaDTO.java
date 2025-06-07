package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class ColaDTO {
    private int idCola;
    private String colaNombre;
    private EstrategiaDTO colaEstrategia; // Estrategia asociada a la cola
    private int colaRingueo;
    private int colaEspera;
    private int colaAutoPausa;
    private int colaDesborde;
    private int colaPrioridad;
    private Set<HabilidadDTO> colaHabilidad; // Habilidades asociadas a la cola
    private int idSector;

    public ColaDTO() {
    }

    public ColaDTO(int idCola, String colaNombre, EstrategiaDTO colaEstrategia, int colaRingueo, int colaEspera, int colaAutoPausa, int colaDesborde, int colaPrioridad, Set<HabilidadDTO> colaHabilidad, int idSector) {
        this.idCola = idCola;
        this.colaNombre = colaNombre;
        this.colaEstrategia = colaEstrategia;
        this.colaRingueo = colaRingueo;
        this.colaEspera = colaEspera;
        this.colaAutoPausa = colaAutoPausa;
        this.colaDesborde = colaDesborde;
        this.colaPrioridad = colaPrioridad;
        this.colaHabilidad = colaHabilidad;
        this.idSector = idSector;
    }

    public int getIdCola() {
        return idCola;
    }

    public void setIdCola(int id) {
        this.idCola = id;
    }

    public String getColaNombre() {
        return colaNombre;
    }

    public void setColaNombre(String nombre) {
        this.colaNombre = nombre;
    }

    public EstrategiaDTO getColaEstrategia() {
        return colaEstrategia;
    }

    public void setColaEstrategia(EstrategiaDTO estrategia) {
        this.colaEstrategia = estrategia;
    }

    public int getColaRingueo() {
        return colaRingueo;
    }

    public void setColaRingueo(int ringueo) {
        this.colaRingueo = ringueo;
    }

    public int getColaEspera() {
        return colaEspera;
    }

    public void setColaEspera(int espera) {
        this.colaEspera = espera;
    }

    public int getColaAutoPausa() {
        return colaAutoPausa;
    }

    public void setColaAutoPausa(int autoPausa) {
        this.colaAutoPausa = autoPausa;
    }

    public int getColaDesborde() {
        return colaDesborde;
    }

    public void setColaDesborde(int desborde) {
        this.colaDesborde = desborde;
    }

    public int getColaPrioridad() {
        return colaPrioridad;
    }

    public void setColaPrioridad(int prioridad) {
        this.colaPrioridad = prioridad;
    }

    public Set<HabilidadDTO> getColaHabilidad() {
        return colaHabilidad;
    }

    public void setColaHabilidad(Set<HabilidadDTO> habilidades) {
        this.colaHabilidad = habilidades;
    }

    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }
}