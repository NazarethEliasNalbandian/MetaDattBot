package org.example.dominio.Analizador;

import lombok.Getter;
import org.example.dominio.Documento.Documento;

@Getter
public class ParDocumento {
    private Documento documento1;
    private Documento documento2;

    public ParDocumento(Documento documento1, Documento documento2) {
        this.documento1 = documento1;
        this.documento2 = documento2;
    }

    public boolean esCopia(double umbral) {
        double distancia = documento1.distancia(documento2);
        return distancia <= umbral;
    }

}
