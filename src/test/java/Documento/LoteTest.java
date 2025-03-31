package Documento;

import org.example.dominio.Documento.Documento;
import org.example.dominio.Documento.Lote;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LoteTest {

    private final String rutaLote = "src/test/resources/lote_prueba";
    private Lote lote;

    @BeforeEach
    void setUp() {
        lote = new Lote(rutaLote);
    }

    @Test
    void testCargaDeDocumentos() throws IOException {
        lote.cargar();

        // Verificamos que cargó documentos
        assertFalse(lote.getDocumentos().isEmpty(), "La lista de documentos no debería estar vacía");

        // Mostramos por pantalla el contenido de cada documento
        System.out.println("📄 Documentos cargados:");
        for (Documento doc : lote.getDocumentos()) {
            System.out.println("Autor (nombre de archivo): " + doc.getAutor());
            System.out.println("Contenido: " + doc.getContenido());
            System.out.println("-------------------------");
        }

        // Verificamos que todos tengan contenido
        for (Documento doc : lote.getDocumentos()) {
            assertNotNull(doc.getContenido(), "El contenido no debe ser null");
            assertFalse(doc.getContenido().isBlank(), "El contenido no debe estar vacío");
        }

        // Verificamos que la cantidad coincida con los archivos reales
        long cantidadArchivosTxt = Files.list(Path.of(rutaLote))
                .filter(p -> p.toString().endsWith(".txt"))
                .count();

        assertEquals(cantidadArchivosTxt, lote.getDocumentos().size(),
                "La cantidad de documentos cargados debe coincidir con los archivos .txt en el directorio");
    }

}
