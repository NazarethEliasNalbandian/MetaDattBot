package org.example.dominio.Documento;

import lombok.Getter;
import org.apache.commons.text.similarity.LevenshteinDistance;

@Getter
public class Documento {
    private String autor;
    private String contenido;

    public Documento(String autor, String contenido) {
        this.autor = autor;
        this.contenido = contenido;
    }

    public double distancia(Documento otro) {
        String texto1 = this.contenido;
        String texto2 = otro.contenido;

        LevenshteinDistance ld = new LevenshteinDistance();
        int distancia = ld.apply(texto1, texto2);

        int maxLen = Math.max(texto1.length(), texto2.length());
        if (maxLen == 0) return 0.0; // ambos vacíos

        return (double) distancia / maxLen;
    }

}
