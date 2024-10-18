import java.util.function.Function;

public class FalsaPosicion {

    public static void falsaPosicion(double a, double b, double tolerancia, int maxIteraciones, Function<Double, Double> funcion) {
        int iteracion = 1;
        double c, fA, fB, fC;
        double anteriorC=0;

        // Cabecera de la tabla
        System.out.printf("%-10s %-12s %-12s %-12s %-12s %-12s %-12s %-12s%n",
                "Iteración", "Valor a", "Valor b", "Xi", "f(Xi)", "Error Relativo (%)", "f(a)", "f(b)");

        // Evaluar los valores iniciales
        fA = funcion.apply(a);
        fB = funcion.apply(b);

        // Comprobar que hay una raíz en el intervalo
        if (fA * fB > 0) {
            System.out.println("No hay una raíz en el intervalo dado.");
            return;
        }


        // Ejecutar el ciclo mientras la diferencia sea mayor que la tolerancia
        while (iteracion <= maxIteraciones) {
            // Calcular el punto c
            c = (a * fB - b * fA) / (fB - fA);
            fC = funcion.apply(c);

            // Mostrar valores de la iteración actual
            System.out.printf("%-10d %-12.6f %-12.6f %-12.10f %-12.10f ", iteracion, a, b, c, fC);

            // Calcular el error relativo
            double errorRelativo = c-anteriorC;

            // Mostrar el error relativo
            System.out.printf("%-12.6f %-12.9f %-12.9f%n", errorRelativo, fA ,fB);

            // Decidir el nuevo intervalo
            if (fC == 0) {
                // Se ha encontrado la raíz
                System.out.println("Se encontró la raíz exacta: " + c);
                return;
            } else if (fA * fC < 0) {
                b = c; // La raíz está entre a y c
                fB = fC;
            } else {
                a = c; // La raíz está entre c y b
                fA = fC;
            }

            // Verificar si el error es menor que la tolerancia
            if (errorRelativo < tolerancia) {
                System.out.println("Se alcanzó la tolerancia deseada.");
                break;
            }
            anteriorC=c;
            iteracion++;
        }

        if (iteracion > maxIteraciones) {
            System.out.println("Se alcanzó el número máximo de iteraciones.");
        }
    }

    public static void main(String[] args) {
        // Definir la función
        Function<Double, Double> funcion = x -> Math.sqrt(Math.pow(x,2)+1)-Math.tan(x);

        // Parámetros para el método
        double a = 1.0/2.0; // Límite inferior
        double b =1; // Límite superior
        double tolerancia = 0.01; // Tolerancia del 1%
        int maxIteraciones = 100; // Máximo número de iteraciones

        // Llamar al método de falsa posición
        falsaPosicion(a, b, tolerancia, maxIteraciones, funcion);
    }
}
