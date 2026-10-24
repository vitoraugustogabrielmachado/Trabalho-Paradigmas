import java.util.Random;

// TODO: Definir as portas abertas; melhorar código de impressão;

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
        // Escolhe o setor do meio como incial
        this.setores[2][2].setInicio();
        // Escolhe um setor final aleatóriamente
        int xFinal = aleat.nextInt(5);
        int yFinal = aleat.nextInt(5);

        // Evitar escolher começo como fim
        while(xFinal == 2 && yFinal == 2) {
            xFinal = aleat.nextInt(5);
            yFinal = aleat.nextInt(5);
        }

        this.setores[aleat.nextInt(5)][aleat.nextInt(5)].setFinal();
    }

    /************* GET / SET ***********/

    // Retorna tipo do setor (x, y)
    public int getTipo(int x, int y) {
        return this.setores[x][y].tipo;
    }
    public void p1Entrou(int x, int y) {
        this.setores[x][y].setJogadorP1();
    }
    public void p2Entrou(int x, int y) {
        this.setores[x][y].setJogadorP2();
    }
    public void p1Saiu(int x, int y) {
        this.setores[x][y].unsetJogadorP1();
    }
    public void p2Saiu(int x, int y) {
        this.setores[x][y].unsetJogadorP2();
    }
    public void imprimeTabuleiro() {
        imprimeTitulo();
        imprimeSetores();
        imprimeSetorJogadores();
    }

    private void imprimeTitulo() {
        System.out.println("-".repeat(30));
        System.out.println("|" + " ".repeat(4) + "Antivírus por um dia" + " ".repeat(4) + "|");
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
                        if(this.setores[i][j].UP && 
                        (this.setores[i][j].p1Esta || this.setores[i][j].p2Esta)) {
                            System.out.print("|-*-");
                        }
                        else {
                            System.out.print("|---");
                        }
                    }
                    else {
                        if(this.setores[i][j].UP && 
                        (this.setores[i][j].p1Esta || this.setores[i][j].p2Esta)) {
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
                    else if(this.setores[i][j].ehIncial) {
                        symbol = " C ";
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
                    if(this.setores[i][j].LEFT && 
                        (this.setores[i][j].p1Esta || this.setores[i][j].p2Esta)) {
                        System.out.print("*");
                    }
                    else {
                        System.out.print("|");
                    }
                    System.out.print(symbol);
                }
                else {
                    if(this.setores[i][j].LEFT && 
                        (this.setores[i][j].p1Esta || this.setores[i][j].p2Esta)) {
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
}