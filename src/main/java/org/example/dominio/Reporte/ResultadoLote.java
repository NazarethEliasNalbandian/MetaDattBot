package org.example.dominio.Reporte;

import lombok.Getter;
import lombok.Setter;
import org.example.dominio.Analizador.ParDocumento;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResultadoLote {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<ParDocumento> posiblesCopias;

    public ResultadoLote() {
        this.posiblesCopias = new ArrayList<>();
    }

    public ResultadoLote(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.posiblesCopias = new ArrayList<>();
    }

    public void agregarPar(ParDocumento par) {
        posiblesCopias.add(par);
    }

}
