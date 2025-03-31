package Analizador;

import org.example.dominio.Analizador.AnalisisDeCopia;
import org.example.dominio.Documento.Documento;
import org.example.dominio.Documento.Lote;
import org.example.dominio.Reporte.ResultadoLote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AnalisisDeCopiaTest {

    private Lote lote;

    @BeforeEach
    void setUp() {
        lote = new Lote("test"); // ruta ficticia, no se usa en este test
        lote.agregarDocumento(new Documento("doc1", "guitarra"));
        lote.agregarDocumento(new Documento("doc2", "guitara"));   // muy similar
        lote.agregarDocumento(new Documento("doc3", "trompeta"));  // distinto
    }

    @Test
    void testProcesarAnalisis() {
        double umbral = 0.3; // tolerancia: los parecidos deben pasar

        AnalisisDeCopia analisis = new AnalisisDeCopia(umbral, lote);
        ResultadoLote resultado = analisis.procesar();

        assertNotNull(resultado);
        assertNotNull(resultado.getFechaInicio(), "Debe tener fecha de inicio");
        assertNotNull(resultado.getFechaFin(), "Debe tener fecha de fin");

        // Al menos un par debería ser considerado copia con este umbral
        assertFalse(resultado.getPosiblesCopias().isEmpty(), "Debe detectar al menos un par como copia");

        System.out.println("📄 Resultado del análisis:");
        resultado.getPosiblesCopias().forEach(par -> {
            System.out.println(par.getDocumento1().getAutor() + " <-> " + par.getDocumento2().getAutor());
        });
    }
}
