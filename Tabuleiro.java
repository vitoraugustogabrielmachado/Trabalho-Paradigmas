import java.util.Random;

/* TODO: Definir as portas abertas; melhorar, comentar e otimizar código de impressão;
//  TALVEZ SEJA MELHOR COLOCAR O MÉTODO imprimeSetorJogadores(); em uma classe que contenha ambos atributos
//  Adaptar imprimeSetorJogadores(); com o restante do código;
*/

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

        this.setores[aleat.nextInt(5)][aleat.nextInt(5)].setFinal(); //nn seria xfinal e yfinal?
    }

    /************* GET / SET ***********/

    // Retorna tipo do setor (x, y)
    public int getTipo(int x, int y) {
        return this.setores[x][y].tipo;
    }
    public Inimigo[] getInimigos(int x, int y) {
        return this.setores[x][y].getInimigos();
    }
    public void setInimigos(int x, int y, Inimigo[] inimigos) {
        this.setores[x][y].setInimigos(inimigos);
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
        int xP1 = 2, yP1 = 2, xP2 = 2, yP2 = 2;

        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.setores[i][j].p1Esta) {
                    xP1 = i;
                    yP1 = j;            
                }
                else if(this.setores[i][j].p2Esta) {
                    xP2 = i;
                    yP2 = j;
                }
            }
        }
        boolean diferente = false;

        if (xP1 != xP2 || yP1 != yP2) {
            diferente = true;
        }
        System.out.println();
        System.out.print("  " + "Setor [" + xP1 + "," + yP1 + "]" + "     ");

        if(diferente) {
            System.out.print("  " + "Setor [" + xP2 + "," + yP2 + "]");
        }

        System.out.println();
        
        if(this.setores[xP1][yP1].UP) {
            System.out.print("|------*------|");
        }
        else {
            System.out.print("|-------------|");
        }

        if(diferente) {
            System.out.print("    ");
            if(this.setores[xP2][yP2].UP) {
                System.out.print("|------*------|");
            }
            else {
                System.out.print("|-------------|");
            }
        }

        System.out.println();
        Inimigo[] inimigos = this.setores[xP1][yP1].getInimigos();

        System.out.print("|");

        for(int i = 0; i < inimigos.length; i++) {
            System.out.print(" ");
            System.out.print(inimigos[i].atk + "/" + inimigos[i].def);
        }
        System.out.print(" ".repeat(13 - 4 * inimigos.length) + "|");
        
        if(diferente) {
            System.out.print("    ");
            inimigos = this.setores[xP2][yP2].getInimigos();

            System.out.print("|");

            for(int i = 0; i < inimigos.length; i++) {
                System.out.print(" ");
                System.out.print(inimigos[i].atk + "/" + inimigos[i].def);
            }
            System.out.print(" ".repeat(13 - 4 * inimigos.length) + "|");
        }
        
        System.out.println();

        System.out.print("|" + " ".repeat(13) + "|");
        if(diferente) {
            System.out.print("    ");
            System.out.print("|" + " ".repeat(13) + "|");
        }

        System.out.println();

        if(this.setores[xP1][yP1].LEFT) {
            System.out.print("*" + " ".repeat(13));
        }
        else {
            System.out.print("|" + " ".repeat(13));
        }
        if(this.setores[xP1][yP1].RIGHT) {
            System.out.print("*");
        }
        else {
            System.out.print("|");
        }

        if (diferente) {
            System.out.print("    ");
            if(this.setores[xP2][yP2].LEFT) {
            System.out.print("*" + " ".repeat(13));
            }
            else {
                System.out.print("|" + " ".repeat(13));
            }
            if(this.setores[xP2][yP2].RIGHT) {
                System.out.print("*");
            }
            else {
                System.out.print("|");
            }
        }

        System.out.println();

        System.out.print("|  P1");
        if(diferente) {
            System.out.print(" ".repeat(9) + "|" + "    " + "|  P2");
        }
        else {
            System.out.print("    P2   "+ "|");
        }

        System.out.println();
        

        // TODO: Pensar na melhor maneira de implementar os atributos dos jogadores

        System.out.println();

        if(this.setores[xP1][yP1].DOWN) {
            System.out.print("|------*------|");
        }
        else {
            System.out.print("|-------------|");
        }

        if(diferente) {
            System.out.print("    ");
            if(this.setores[xP2][yP2].DOWN) {
                System.out.print("|------*------|");
            }
            else {
                System.out.print("|-------------|");
            }
        }

        System.out.println();
    }
}