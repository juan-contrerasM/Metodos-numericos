import java.util.Arrays;

public class Jacobi {

    // Método para aplicar el método de Jacobi
    public static double[] jacobi(double[][] A, double[] b, double[] x0, double tolerancia, int maxIteraciones) {
        int n = b.length;
        double[] x = Arrays.copyOf(x0, n); // Vector inicial
        double[] xNuevo = new double[n];   // Vector para la siguiente iteración

        for (int iteracion = 0; iteracion < maxIteraciones; iteracion++) {
            // Para cada componente de x
            for (int i = 0; i < n; i++) {
                double suma = 0.0;
                // Sumamos los elementos a_ij * x_j para j != i
                for (int j = 0; j < n; j++) {
                    if (i != j) {
                        suma += A[i][j] * x[j];
                    }
                }
                // Calculamos x_i^{(k+1)} usando la fórmula de Jacobi
                xNuevo[i] = (b[i] - suma) / A[i][i];
            }

            // Verificar si el error es menor que la tolerancia
            double error = calcularError(x, xNuevo);
            System.out.printf("Iteración %d: x = %s, Error = %.6f%n", iteracion + 1, Arrays.toString(xNuevo), error);

            if (error < tolerancia) {
                break;
            }

            // Actualizamos x para la siguiente iteración
            System.arraycopy(xNuevo, 0, x, 0, n);
        }

        return xNuevo;
    }

    // Método para calcular el error (norma infinita)
    public static double calcularError(double[] xViejo, double[] xNuevo) {
        double maxError = 0.0;
        for (int i = 0; i < xViejo.length; i++) {
            maxError = Math.max(maxError, Math.abs(xNuevo[i] - xViejo[i]));
        }
        return maxError;
    }

    public static void main(String[] args) {
        // Ejemplo de un sistema de ecuaciones: Ax = b
        double[][] A = {
                { 12, 3, -5},
                { 1, 5, 3},
                { 3, 7, 13}

        };
        double[] b = { 1,28,76 };
        double[] x0 = { 1, 0, 1 }; // Vector inicial

        double tolerancia = 0.005; // Tolerancia para la convergencia
        int maxIteraciones = 100; // Número máximo de iteraciones

        // Ejecutar el método de Jacobi
        double[] solucion = jacobi(A, b, x0, tolerancia, maxIteraciones);

        // Mostrar la solución final
        System.out.println("Solución final: " + Arrays.toString(solucion));
    }
}
