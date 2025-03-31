package Documento;

import org.example.dominio.Documento.Documento;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DocumentoTest {

    @Test
    public void testDocumentosIguales() {
        Documento doc1 = new Documento("", "hola");
        Documento doc2 = new Documento("", "hola");
        assertEquals(0.0, doc1.distancia(doc2),
                "Los textos que son iguales deben dar una distancia de 0");
    }

    @Test
    public void testDocumentosDistintos() {
        Documento doc1 = new Documento("", "chau");
        Documento doc2 = new Documento("", "hola");
        assertEquals(1.0, doc1.distancia(doc2), 0.01,
                "Los textos totalmente distintos deben dar una distancia de 1");
    }

    @Test
    public void testDocumentosParecidos() {
        Documento doc1 = new Documento("", "hola");
        Documento doc2 = new Documento("", "halo");
        assertEquals(0.5, doc1.distancia(doc2), 0.1,
                "Los textos parecidos deben dar una distancia cercana a 0.5");
    }
}
