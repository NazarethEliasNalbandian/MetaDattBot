package org.example.dominio;

import org.example.dominio.Analizador.AnalisisDeCopia;
import org.example.dominio.Analizador.ParDocumento;
import org.example.dominio.Documento.Lote;
import org.example.dominio.Reporte.ResultadoLote;

import java.io.IOException;

public class CopiaMeApp {
    public static void main(String[] args) {
        // Capa de presentación (validación de entrada)
        String directorio = args[0];

        // Capa de datos
        Lote lote = new Lote(directorio);

        lote.validar(lote.getDirectorio());
        try {
            lote.cargar();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Capa de lógica / dominio
        float umbral = 0f;
        AnalisisDeCopia analisis = new AnalisisDeCopia(umbral, lote);
        ResultadoLote resultado = analisis.procesar();

        // Capa de presentación (salida)
        for (ParDocumento par : resultado.getPosiblesCopias()) {
            System.out.println(par.getDocumento1().getAutor() + " " + par.getDocumento2().getAutor());
        }
    }
}
