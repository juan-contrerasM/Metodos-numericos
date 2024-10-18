import java.util.function.Function;

public class NewtonRaphson {

    // Método de Newton-Raphson
    public static void newtonRaphson(double x0, double tolerancia, int maxIteraciones, Function<Double, Double> funcion, Function<Double, Double> derivada) {
        int iteracion = 1;
        double x1 = 0;
        double error = Double.MAX_VALUE; // Iniciamos con un error grande

        System.out.printf("%-10s %-12s %-12s %-12s %-12s%n", "Iteración", "X_n", "f(X_n)","f(derivada)", "Error %");

        while (error > tolerancia && iteracion <= maxIteraciones) {
            double fX0 = funcion.apply(x0);
            double derivadaFX0 = derivada.apply(x0);

            // Comprobar que la derivada no es cero (para evitar división por cero)
            if (derivadaFX0 == 0) {
                System.out.println("Derivada es cero. No se puede continuar con el método.");
                return;
            }

            // Calcular el siguiente valor de x usando la fórmula de Newton-Raphson
            x1 = x0 - fX0 / derivadaFX0;

            // Calcular el error relativo
            error = Math.abs((x1 - x0) / x1) * 100;
            x0 = x1;
            // Imprimir la iteración actual
            System.out.printf("%-10d %-12.9f %-12.9f %-12.9f  %-12.9f%n", iteracion, x0, fX0, derivadaFX0, error);

            // Actualizar el valor de x0 para la siguiente iteración

            iteracion++;
        }

        if (error <= tolerancia) {
            System.out.println("Se alcanzó la tolerancia deseada.");
            System.out.println("La raíz aproximada es: " + x1);
        } else {
            System.out.println("Se alcanzó el número máximo de iteraciones.");
        }
    }

    public static void main(String[] args) {
        // Definir otra función f(x) = x^2 - 2 (la raíz cuadrada de 2)
        Function<Double, Double> funcion = (x) -> Math.cos(x)-x;
        // Definir su derivada f'(x) = 2x
        Function<Double, Double> derivada = (x) -> -Math.sin(x)-1;

        // Valores iniciales
        double x0 = 1; // Aproximación inicial
        double tolerancia = 0.01; // Tolerancia (1e-3)
        int maxIteraciones = 100; // Máximo número de iteraciones

        // Ejecutar el método de Newton-Raphson
        newtonRaphson(x0, tolerancia, maxIteraciones, funcion, derivada);
    }
}
