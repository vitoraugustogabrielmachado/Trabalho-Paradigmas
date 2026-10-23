import java.util.Random;

// TODO: Definir as portas abertas

public class Tabuleiro {
    public Setor[][] setores;
    
    public Tabuleiro() {
        Random aleat = new Random();

        this.setores = new Setor[5][5];
        // Inicializa setor por setor;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                this.setores[x][y] = new Setor(x, y);
            }
        }

        // Escolhe um setor final aleatóriamente
        this.setores[aleat.nextInt(5)][aleat.nextInt(5)].setFinal();
    }

    private void imprimeTitulo() {
        System.out.println("-".repeat(30));
        System.out.println();
        System.out.println("|" + " ".repeat(4) + "Antivírus por um dia" + " ".repeat(4) + "|");
        System.out.println();
        System.out.println("-".repeat(30));
    }

    private void imprimeSetores() {
        System.out.print(" ".repeat(5));
        for(int i = 1; i < 6; i++) {
            System.out.print(i + "   ");
        }
        System.out.println();

        for(int i = 0; i < 5; i++) {
            System.out.print("   ");
            for(int j = 0; j < 5; j++) {
                // UP
                if(i == 0) {
                    if(j < 4) {
                        System.out.print("|---");
                    }
                    else {
                        System.out.print("|---|");
                    }
                }
                else if(i <= 4) {
                    if(j < 4) {
                        if(this.setores[i][j].UP) {
                            System.out.print("|-*-");
                        }
                        else {
                            System.out.print("|---");
                        }
                    }
                    else {
                        if(this.setores[i][j].UP) {
                            System.out.print("|-*-|");
                        }
                        else {
                            System.out.print("|---|");
                        }
                    }
                }
            }
                // MID
            System.out.println("");
            System.out.print((i+1) + "  ");

            for(int j = 0; j < 5; j++) {
                String symbol;
                    if(this.setores[i][j].ehFinal) {
                        symbol = " X ";
                    }
                    else if(this.setores[i][j].p1Esta && this.setores[i][j].p2Esta) {
                        symbol = " P ";
                    }
                    else if(this.setores[i][j].p1Esta) {
                        symbol = "P1 ";
                    }
                    else if(this.setores[i][j].p2Esta) {
                        symbol = "P2 ";
                    }
                    else {
                        symbol = "   ";
                    }
                if(j == 0) {
                    System.out.print("|");
                    System.out.print(symbol);
                }
                else if(j < 4) {
                    if(this.setores[i][j].LEFT) {
                        System.out.print("*");
                    }
                    else {
                        System.out.print("|");
                    }
                    System.out.print(symbol);
                }
                else {
                    if(this.setores[i][j].LEFT) {
                        System.out.print("*");
                    }
                    else {
                        System.out.print("|");
                    }
                    System.out.print(symbol);
                    System.out.print("|");
                }
            }
            System.out.println("");
        }
        System.out.print("   ");
        for(int j = 0; j < 5; j++) {
            if(j < 4) {
                System.out.print("|---");
            }
                else {
                    System.out.print("|---|");
                }
        }
        System.out.println("");
    }

    private void imprimeSetorJogadores() {

    }

    public void imprimeTabuleiro() {
        imprimeTitulo();
        imprimeSetores();
        imprimeSetorJogadores();
    }
}