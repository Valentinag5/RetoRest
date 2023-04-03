package co.sofkau.models;

import java.util.ArrayList;
import java.util.List;

public class ResponseGetListar {

    private Integer porPagina;
    private Integer totales;
    private Integer totalPagina;
    private List <Datos> datos = new ArrayList<Datos>();

    public Integer getPorPagina() {
        return porPagina;
    }

    public void setPorPagina(Integer porPagina) {
        this.porPagina = porPagina;
    }

    public Integer getTotales() {
        return totales;
    }

    public void setTotales(Integer totales) {
        this.totales = totales;
    }

    public Integer getTotalPagina() {
        return totalPagina;
    }

    public void setTotalPagina(Integer totalPagina) {
        this.totalPagina = totalPagina;
    }

    public List<Datos> getDatos() {
        return datos;
    }

    public void setDatos(List<Datos> datos) {
        this.datos = datos;
    }
}
