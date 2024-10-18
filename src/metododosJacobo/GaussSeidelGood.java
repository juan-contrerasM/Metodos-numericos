package metododosJacobo;

public class GaussSeidelGood {
    public static void main(String[] args) {
        double[][] A = {
                {12, 3, -5},
                {1, 5, 3},
                {3, 7, 13}
        };
        double[] b = {1 ,28, 76};

        double[] x = {1, 0, 1}; // Vector inicial
        double errorTolerancia = 0.05; // Tolerancia de error
        double[] errores = new double[b.length]; // Array para almacenar errores
        boolean convergente;

        int iteraciones = 0;

        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s %-10s%n", "Iteración", "x1", "x2", "x3", "Error x1", "Error x2", "Error x3", "Convergente");
        System.out.println("------------------------------------------------------------------------------------------------");

        do {
            convergente = true;

            // Almacenar los valores anteriores para calcular los errores
            double[] xAnterior = x.clone();

            // Actualizar x usando el método de Gauss-Seidel
            x[0] = (b[0] - (A[0][1] * x[1] + A[0][2] * x[2])) / A[0][0];
            x[1] = (b[1] - (A[1][0] * x[0] + A[1][2] * x[2])) / A[1][1];
            x[2] = (b[2] - (A[2][0] * x[0] + A[2][1] * x[1])) / A[2][2];

            // Calcular errores y verificar convergencia
            for (int i = 0; i < x.length; i++) {
                errores[i] = Math.abs(x[i] - xAnterior[i]) / (x[i] != 0 ? x[i] : 1); // Evitar división por cero
                if (errores[i] > errorTolerancia) {
                    convergente = false;
                }
            }

            // Imprimir los valores actuales y los errores
            System.out.printf("%-10d %-10.6f %-10.6f %-10.6f %-10.6f %-10.6f %-10.6f %-10s%n", iteraciones + 1, x[0], x[1], x[2], errores[0], errores[1], errores[2], convergente ? "Sí" : "No");

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