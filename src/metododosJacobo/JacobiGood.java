package metododosJacobo;
public class JacobiGood {

    public static void main(String[] args) {
        double[][] A = {
                {0.7, 0.1, -8.2},
                {25, -0.9,- 0.3},
                {3.7, -7.3, -0.1}
        };
        double[] b = {-56.4, 20.2, -18.9};

        double[] x = {1, 0, 1}; // Vector inicial
        double[] xNuevo = new double[b.length];
        double errorTolerancia = 0.001; // Tolerancia de error
        double[] errores = new double[b.length]; // Array para almacenar errores
        boolean convergente;

        int iteraciones = 0;

        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n", "Iteración", "x1", "x2", "x3", "Error x1", "Error x2", "Error x3", "Convergente");
        System.out.println("------------------------------------------------------------------------------------------------");

        do {
            convergente = true;

            for (int i = 0; i < b.length; i++) {
                double suma = 0;
                for (int j = 0; j < A[i].length; j++) {
                    if (i != j) {
                        suma += A[i][j] * x[j];
                    }
                }
                xNuevo[i] = (b[i] - suma) / A[i][i];
            }

            // Calcular errores y verificar convergencia
            for (int i = 0; i < x.length; i++) {
                errores[i] = Math.abs(xNuevo[i] - x[i])/xNuevo[i];
                if (errores[i] > errorTolerancia) {
                    convergente = false;
                }
            }

            // Imprimir los valores actuales y los errores
            System.out.printf("%-10d %-10.6f %-10.6f %-10.6f %-10.6f %-10.6f %-10.6f %-10s%n", iteraciones + 1, xNuevo[0], xNuevo[1], xNuevo[2], errores[0], errores[1], errores[2], convergente ? "Sí" : "No");

            // Actualizar x
            System.arraycopy(xNuevo, 0, x, 0, x.length);
            iteraciones++;
        } while (!convergente);

        // Mostrar resultados finales
        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("Solución final:");
        for (int i = 0; i < x.length; i++) {
            System.out.printf("x[%d] = %.6f%n", i + 1, x[i]);
        }
        System.out.println("Número de iteraciones: " + iteraciones);
    }
}
