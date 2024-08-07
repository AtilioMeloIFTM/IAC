package perceptron_digitos;

public class Validacao {
    
    public Validacao() {
    }
    
    public double[] somatorio(int[][] mat, double[][] w) {
        double[] yent = new double[10]; // Atualizado para 10 saídas
        double[] entrada = new double[16];
        int l = 1;
        entrada[0] = 1;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                entrada[l] = mat[i][j];
                l++;
            }
        }
        for (int k = 0; k < 10; k++) { // Atualizado para 10 saídas
            yent[k] = 0;
            for (int j = 0; j < 16; j++) {
                yent[k] += entrada[j] * w[j][k];
            }
        }
        return yent;
    }

    public String teste(int[][] mat, double[][] w, double[][] t, double limiar) {
        double[] yent = somatorio(mat, w);
        int classe = 0;
        double maiorSaida = -Double.MAX_VALUE;
        for (int i = 0; i < yent.length; i++) {
            if (yent[i] > maiorSaida) {
                maiorSaida = yent[i];
                classe = i;
            }
        }
        return Integer.toString(classe);
    }
}
