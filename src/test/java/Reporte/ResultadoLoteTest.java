package Reporte;

import org.example.dominio.Analizador.ParDocumento;
import org.example.dominio.Reporte.ResultadoLote;
import org.example.dominio.Documento.Documento;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ResultadoLoteTest {

    @Test
    public void testInicializacion() {
        LocalDateTime inicio = LocalDateTime.now();
        LocalDateTime fin = inicio.plusMinutes(10);
        ResultadoLote resultado = new ResultadoLote(inicio, fin);

        assertEquals(inicio, resultado.getFechaInicio(), "La fecha de inicio debe coincidir");
        assertEquals(fin, resultado.getFechaFin(), "La fecha de fin debe coincidir");
        assertTrue(resultado.getPosiblesCopias().isEmpty(), "La lista de posibles copias debe estar vacía al inicio");
    }

    @Test
    public void testAgregarParDocumento() {
        ResultadoLote resultado = new ResultadoLote(LocalDateTime.now(), LocalDateTime.now().plusSeconds(1));
        Documento doc1 = new Documento("autor1", "hola mundo");
        Documento doc2 = new Documento("autor2", "hola mundo!");
        ParDocumento par = new ParDocumento(doc1, doc2);

        resultado.agregarPar(par);

        List<ParDocumento> posibles = resultado.getPosiblesCopias();
        assertEquals(1, posibles.size(), "Debe haber un par de documentos registrado");
        assertEquals(par, posibles.get(0), "El par agregado debe coincidir con el recuperado");
    }
}
