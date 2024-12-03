package cr.go.ucr.examen1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Joshua Sancho
 */

class ComisionVentaTest {

    @Test
    void testCalcularComision_valorNegativo() {
        // Prueba de caja negra: valor negativo
        Double resultado = ComisionVenta.getCurrentInstance().calcular(-1.0);
        assertEquals(-1.0, resultado, "Debe devolver -1 para valores negativos");
    }

    @Test
    void testCalcularComision_rangoInferior() {
        // Prueba de caja negra: valor dentro del rango menor o igual a 1500
        Double resultado = ComisionVenta.getCurrentInstance().calcular(1500.0);
        assertEquals(1500.0 * 0.03, resultado, "Debe calcular correctamente para valores en el rango 0-1500");
    }

    @Test
    void testCalcularComision_rangoMedio() {
        // Prueba de caja negra: valor dentro del rango de 1500.01 a 5000
        Double resultado = ComisionVenta.getCurrentInstance().calcular(3000.0);
        assertEquals(3000.0 * 0.07, resultado, "Debe calcular correctamente para valores en el rango 1500.01-5000");
    }

    @Test
    void testCalcularComision_valorExcedido() {
        // Prueba de caja negra: valor mayor a 100000
        Double resultado = ComisionVenta.getCurrentInstance().calcular(100001.0);
        assertEquals(-1.0, resultado, "Debe devolver -1 para valores mayores a 100000");
    }

    @Test
    void testCalcularComision_rangoAlto() {
        // Prueba de caja negra: valor dentro del rango de 15000.01 a 100000
        Double resultado = ComisionVenta.getCurrentInstance().calcular(75000.0);
        assertEquals(75000.0 * 0.15, resultado, "Debe calcular correctamente para valores en el rango 15000.01-100000");
    }

    @Test
    void testCalcularComision_recorrerCoberturaCompleta() {
        // Prueba de caja blanca: recorrer todo el código con cobertura
        Double resultado1 = ComisionVenta.getCurrentInstance().calcular(0.0); // Límite inferior
        Double resultado2 = ComisionVenta.getCurrentInstance().calcular(1500.0); // Rango inferior
        Double resultado3 = ComisionVenta.getCurrentInstance().calcular(3000.0); // Rango medio
        Double resultado4 = ComisionVenta.getCurrentInstance().calcular(12000.0); // Rango alto
        Double resultado5 = ComisionVenta.getCurrentInstance().calcular(100000.0); // Límite superior

        assertEquals(0.0 * 0.03, resultado1, "Debe calcular correctamente para el límite inferior");
        assertEquals(1500.0 * 0.03, resultado2, "Debe calcular correctamente para valores en el rango 0-1500");
        assertEquals(3000.0 * 0.07, resultado3, "Debe calcular correctamente para valores en el rango 1500.01-5000");
        assertEquals(12000.0 * 0.12, resultado4, "Debe calcular correctamente para valores en el rango 5000.01-15000");
        assertEquals(100000.0 * 0.15, resultado5, "Debe calcular correctamente para el límite superior");
    }
}
