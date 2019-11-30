import java.util.Scanner;

public class Aplicacao {

	public static void main(String[] args) {
		Scanner leitor = new Scanner(System.in);
		System.out.print("Deseja usar os valores do exercício (s para sim / n para não): ");
		String op = leitor.next();
		leitor.nextLine();
		int troco;
		int[] moedas;
		int[][] matriz;
		int numMoedas = 0;
		if(op.equalsIgnoreCase("s")) {
			troco = 10;
			numMoedas = 4;
			moedas = new int[numMoedas];
			moedas[0] = 1;
			moedas[1] = 2;
			moedas[2] = 6;
			moedas[3] = 8;
			matriz = new int[4][11];
			System.out.print("\nTroco: " + troco + "\nMoedas: ");
			for(int i=0; i<numMoedas; i++)
				System.out.print(moedas[i] + " ");
			System.out.println();
		} else {
			System.out.print("\nInforme o valor do troco, em número inteiro: ");
			troco = leitor.nextInt();
			leitor.nextLine();
			System.out.print("Informe o valor das moedas, separadas por espaço, ordenadas de forma crescente: ");
			String entrada = leitor.nextLine();
			String[] vetorMoedas = entrada.split(" ");
			for(int i=0; i<vetorMoedas.length; i++)
				if(isNumeric(vetorMoedas[i]))
					numMoedas++;
			moedas = new int[numMoedas];
			for(int i=0, j=0; i<vetorMoedas.length; i++) {
				if(isNumeric(vetorMoedas[i])) {
					moedas[j] = Integer.parseInt(vetorMoedas[i]);
					j++;
				}
				if(j == numMoedas) {
					i = vetorMoedas.length;
				}
			}
			matriz = new int[numMoedas][troco+1];
		}
		leitor.close();
		for(int i=0; i<numMoedas; i++) {
			for(int j=0; j<=troco; j++) {
				if(j < moedas[i] || j == 0) {
					if(i == 0)
						matriz[i][j] = 0;
					else
						matriz[i][j] = matriz[i-1][j];
				}
				else {
					if(i == 0)
						matriz[i][j] = matriz[i][j-moedas[i]]+1;
					else
						matriz[i][j] = Math.min(matriz[i-1][j], matriz[i][j-moedas[i]]+1);
				}
			}
		}
//		for(int i=0; i<numMoedas; i++) {
//			for(int j=0; j<=troco; j++) {
//				System.out.print(matriz[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println("\nMenor número de moedas possível para o troco: " + matriz[numMoedas-1][troco]);
	}

	public static boolean isNumeric(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
