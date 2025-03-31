package org.example.dominio.Analizador;

import lombok.Getter;
import org.example.dominio.Documento.Lote;
import org.example.dominio.Reporte.ResultadoLote;
import org.paukov.combinatorics3.Generator;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class AnalisisDeCopia {
    private double umbral;
    private Lote lote;

    public AnalisisDeCopia(double umbral, Lote lote) {
        this.umbral = umbral;
        this.lote = lote;
    }

    public ResultadoLote procesar() {
        // Genero todos los pares de documentos Posibles
        List<ParDocumento> pares = Generator.combination(this.lote.getDocumentos())
                .simple(2)
                .stream()
                .map(t-> new ParDocumento(t.getFirst(),t.get(1)) )
                .toList();

        // Armo el resultado procesando cada par
        ResultadoLote rl = new ResultadoLote();
        rl.setFechaInicio(LocalDateTime.now());
        for (ParDocumento parDocumentos : pares) {
            if(parDocumentos.esCopia(this.umbral)) {
                rl.agregarPar(parDocumentos);
            }
        }
        rl.setFechaFin(LocalDateTime.now());
        return rl;
    }

}
