package perceptron_digitos;

public class Aprendizagem {
    
    private double x[][] = {
        // Representações binárias para 0 a 9
        {1, 1, 1, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 1, 1}, // 0
        {1, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 1, 1, 1}, // 1
        {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1}, // 2
        {1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // 3
        {1, 1, 0, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1}, // 4
        {1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, 1, 1}, // 5
        {1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 0, 1, 1, 1, 1}, // 6
        {1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1}, // 7
        {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1}, // 8
        {1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1}  // 9
    };

    private double w[][] = new double[16][10]; // Pesos para cada saída
    private double t[][] = {
        {1, -1, -1, -1, -1, -1, -1, -1, -1, -1},  // Saída esperada para 0
        {-1, 1, -1, -1, -1, -1, -1, -1, -1, -1}, // Saída esperada para 1
        {-1, -1, 1, -1, -1, -1, -1, -1, -1, -1}, // Saída esperada para 2
        {-1, -1, -1, 1, -1, -1, -1, -1, -1, -1}, // Saída esperada para 3
        {-1, -1, -1, -1, 1, -1, -1, -1, -1, -1}, // Saída esperada para 4
        {-1, -1, -1, -1, -1, 1, -1, -1, -1, -1}, // Saída esperada para 5
        {-1, -1, -1, -1, -1, -1, 1, -1, -1, -1}, // Saída esperada para 6
        {-1, -1, -1, -1, -1, -1, -1, 1, -1, -1}, // Saída esperada para 7
        {-1, -1, -1, -1, -1, -1, -1, -1, 1, -1}, // Saída esperada para 8
        {-1, -1, -1, -1, -1, -1, -1, -1, -1, 1}  // Saída esperada para 9
    }; 
    
    private int epocas;
        
    public Aprendizagem(){  
        epocas = 0;
    }
    
    public double[][] getw() {
        return w;
    }
    
    public double[][] gett() {
        return t;
    }    
    
    public int getepocas() {
        return epocas;
    }
    
    public double somatorio(int i, int k) {
        double yent = 0;       
        for (int j = 0; j < 16; j++)
            yent += x[i][j] * w[j][k];
        return yent;
    }

    public int saida(double[] yent, double limiar) {
        int classe = 0;
        double maiorSaida = -Double.MAX_VALUE;
        for (int i = 0; i < yent.length; i++) {
            if (yent[i] > maiorSaida) {
                maiorSaida = yent[i];
                classe = i;
            }
        }
        return classe;
    }
    
    public void atualiza(double alfa, double[][] f) {
        for (int i = 0; i < 10; i++) { // Ajusta os pesos para cada classe
            for (int j = 0; j < 16; j++) {
                for (int k = 0; k < 10; k++) { // Atualizado para 10 saídas
                    w[j][k] += alfa * (t[i][k] - f[i][k]) * x[i][j];
                }
            }
        }
    }

    public void algoritmo(double alfa, double limiar) {
        double[][] yent = new double[10][10]; // Para 10 classes e 10 saídas por classe
        double[][] f = new double[10][10];
        boolean mudou;

        for (int j = 0; j < 16; j++) { // Zerando os pesos
            for (int k = 0; k < 10; k++) {
                w[j][k] = 0;
            }
        }

        do {
            mudou = false;
            for (int i = 0; i < 10; i++) { // Para 10 classes
                for (int k = 0; k < 10; k++) {
                    yent[i][k] = somatorio(i, k);
                    f[i][k] = (yent[i][k] > limiar) ? 1 : -1;
                }
                if (f[i][0] != t[i][0] || f[i][1] != t[i][1] || f[i][2] != t[i][2] || f[i][3] != t[i][3] ||
                    f[i][4] != t[i][4] || f[i][5] != t[i][5] || f[i][6] != t[i][6] || f[i][7] != t[i][7] ||
                    f[i][8] != t[i][8] || f[i][9] != t[i][9]) { // Verifica se houve erro
                    mudou = true;
                    atualiza(alfa, f);
                }
            }
            epocas++;
        } while (mudou);
    }
}
