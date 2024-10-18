import java.util.function.Function;



public class Biseccion {

    // TODO -> Método para implementar el algoritmo de bisección
    public static void biseccion(double a, double b, double tolerancia, int maxIteraciones, Function<Double, Double> funcion) {
        int iteracion = 1;
        double xMedio, fXMedio, bMenosA;

        // Cabecera de la tabla
        System.out.printf("%-10s %-12s %-12s %-12s %-12s %-12s %s%n ",
                "Iteración", "Valor a", "Valor b", "X Medio", "f(X Medio)", "b - a","intervalo");

        // Ejecutar el ciclo mientras la diferencia entre b y a sea mayor que la tolerancia
        while (iteracion <= maxIteraciones) {
            xMedio = (a + b) / 2;  // Punto medio
            fXMedio = funcion.apply(xMedio);  // Evaluación de la función en el punto medio

            // Mostrar valores de la iteración actual
            System.out.printf("%-10d %-12.9f %-12.9f %-12.9f  %-12.9f ", iteracion, a, b, xMedio, fXMedio);


            // Decidir el nuevo intervalo
            if (funcion.apply(a) * fXMedio < 0) {
                b = xMedio;  // Nuevo extremo superior

            } else {
                a = xMedio;  // Nuevo extremo inferior

            }

            bMenosA = b - a;  // Diferencia entre los extremos del intervalo

            // Mostrar la diferencia b - a
            System.out.printf(" %-12.9f %s%n", bMenosA, "["+a+"-"+b+"]");

            // Si la diferencia entre b y a es menor que la tolerancia, terminamos
            if (bMenosA < tolerancia) {
                System.out.println("Se alcanzó la tolerancia deseada.");
                break;
            }

            iteracion++;
        }

        if (iteracion > maxIteraciones) {
            System.out.println("Se alcanzó el número máximo de iteraciones.");
        }
    }

    public static void main(String[] args) {
        // Definir la función a evaluar (ejemplo: f(x) = exp(-x^3) - 2*x + 1)
        Function<Double, Double> funcion = x ->  Math.exp(Math.pow(-x,3))-2*x+1;//Math.pow(x,3)+x-6;


        // Parámetros para la bisección
        double a = 3.0/4.0;  // Límite inferior del intervalo
        double b = 1.0;  // Límite superior del intervalo
        double tolerancia = 0.01;  // Tolerancia para detener el algoritmo
        int maxIteraciones = 100;  // Máximo número de iteraciones permitidas

        // Ejecutar el método de bisección
        biseccion(a, b, tolerancia, maxIteraciones, funcion);
    }
}
