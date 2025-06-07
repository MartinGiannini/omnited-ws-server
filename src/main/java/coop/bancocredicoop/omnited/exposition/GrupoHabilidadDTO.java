package coop.bancocredicoop.omnited.exposition;

import java.util.Set;

public class GrupoHabilidadDTO {
    private int idGrupoHabilidad;
    private String grupoHabilidadNombre;
    private Set<HabilidadDTO> habilidad;
    private SectorDTO sector;
    
    public GrupoHabilidadDTO(int id, String nombre, Set<HabilidadDTO> habilidades, SectorDTO sector) {
        this.idGrupoHabilidad = id;
        this.grupoHabilidadNombre = nombre;
        this.habilidad = habilidades;
        this.sector = sector;
    }
    
    public GrupoHabilidadDTO() {
    }
    
    public int getIdGrupoHabilidad() {
        return idGrupoHabilidad;
    }

    public void setIdGrupoHabilidad(int id) {
        this.idGrupoHabilidad = id;
    }

    public String getGrupoHabilidadNombre() {
        return grupoHabilidadNombre;
    }

    public void setGrupoHabilidadNombre(String nombre) {
        this.grupoHabilidadNombre = nombre;
    }

    public Set<HabilidadDTO> getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(Set<HabilidadDTO> habilidades) {
        this.habilidad = habilidades;
    }
    
    public SectorDTO getSector() {
        return sector;
    }

    public void setSector(SectorDTO sector) {
        this.sector = sector;
    }
    
}