import java.util.Random;

/* TODO: Definir as portas abertas; melhorar, comentar e otimizar código de impressão;
// Melhor definir as portas no contrutor do tabuleiro; Eu estava pensando em fazer o contrutor de setor
// receber como parametro as portas que devem ficar abertas ou um vetor de boolean em que 
// V[0] -> UP ; V[1] -> DOWN; V[2] -> LEFT; V[3] -> RIGHT; EX -> V[0] == 1 -> setor.UP = true */

public class Tabuleiro {
    private String titulo = "Antivírus por um dia"; // Podemos colocar isso no construtor para receber por parâmetro
    private final Setor[][] setores;
    
    public Tabuleiro() {
        Random aleat = new Random();

        this.setores = new Setor[5][5];
        // Inicializa setor por setor;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                this.setores[x][y] = new Setor();
            }
        }
        // Escolhe o setor do meio como incial
        this.setores[2][2].setInicio();
        // Escolhe um setor final aleatóriamente
        int xFinal = aleat.nextInt(5);
        int yFinal = aleat.nextInt(5);

        // Evita escolher começo como fim
         while(xFinal == 2 && yFinal == 2) {
            xFinal = aleat.nextInt(5);
            yFinal = aleat.nextInt(5);
        }

        this.setores[xFinal][yFinal].setFinal();
    }
    public Tabuleiro(String titulo) {
        this();
        this.titulo = titulo;
    }
    /************* GET / SET ***********/

    // Retorna o setor (x, y)
    public Setor getSetor(int x, int y) {
        return this.setores[x][y];
    }
    // Retorna tipo do setor (x, y)
    public int getTipo(int x, int y) {
        return this.setores[x][y].getTipo();
    }
    // Retorna o vetor de inimigos do setor (x, y)
    public Inimigo[] getInimigos(int x, int y) {
        return this.setores[x][y].getInimigos();
    }
    // Substitui o setor (x, y) por "setor"
    public void setSetor(int x, int y, Setor setor) {
        this.setores[x][y] = setor;
    }
    // Substitui o vetor de inimigos do setor (x, y) pelo vetor parâmetro
    public void setInimigos(int x, int y, Inimigo[] inimigos) {
        this.setores[x][y].setInimigos(inimigos);
    }
    // Indica que p1 está no setor (x, y)
    public void setJogador(JogadorSimples p1) {
        this.setores[p1.getX()][p1.getY()].setJogador(p1);
    }
    // Indica que p2 está no setor (x, y)
    public void setJogador(JogadorSuporte p2) {
        this.setores[p2.getX()][p2.getY()].setJogador(p2);
    }
    // Indica que p1 não está ou saiu do setor (x, y)
    public void unsetJogador(JogadorSimples p1) {
        this.setores[p1.getX()][p1.getY()].unsetJogador(p1);
    }
    // Indica que p2 não está ou saiu do setor (x, y)
    public void unsetJogador(JogadorSuporte p2) {
        this.setores[p2.getX()][p2.getY()].unsetJogador(p2);
    }
    /* porta representa a direcao do movimento desejado; por isso deve
    // ser uma das seguintes strings: "UP"||"DOWN"||"LEFT"||"RIGHT". CC -> return = false 
    // RETORNA true caso o movimento para essa porta esteja liberado e false CC. */
    public boolean movimentoLiberado(int x, int y, String porta) {
        return this.setores[x][y].ehPorta(porta);
    }
    /* Imprime o tabuleiro + setores
    // TODO: o ideal seria esse método só imprimir o tabuleiro e colocarmos imprimeSetorJogadores(); em outra classe
    // ou chamarmos separadamente, pois esse método usará atributos da classe jogadores. */
    public void imprimeTabuleiro() {
        imprimeTitulo();
        imprimeSetores();
        imprimeSetorJogadores();
    }


    // Imprime o título
    private void imprimeTitulo() {
        System.out.println("-".repeat(30));
        System.out.println("|" + " ".repeat(4) + this.titulo + " ".repeat(4) + "|");
        System.out.println("-".repeat(30));
    }
    /* Imprime setor por setor
    //TODO: comentar e limpar/otimizar */
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
                        if(this.setores[i][j].ehPorta("UP") && 
                        (this.setores[i][j].getP1Esta() || this.setores[i][j].getP2Esta())) {
                            System.out.print("|-*-");
                        }
                        else {
                            System.out.print("|---");
                        }
                    }
                    else {
                        if(this.setores[i][j].ehPorta("UP") && 
                        (this.setores[i][j].getP1Esta() || this.setores[i][j].getP2Esta())) {
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
                    if(this.setores[i][j].getEhFinal()) {
                        symbol = " X ";
                    }
                    else if(this.setores[i][j].getEhInicial()) {
                        symbol = " C ";
                    }
                    else if(this.setores[i][j].getP1Esta() && this.setores[i][j].getP2Esta()) {
                        symbol = " P ";
                    }
                    else if(this.setores[i][j].getP1Esta()) {
                        symbol = "P1 ";
                    }
                    else if(this.setores[i][j].getP2Esta()) {
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
                    if(this.setores[i][j].ehPorta("LEFT") && 
                        (this.setores[i][j].getP1Esta() || this.setores[i][j].getP2Esta())) {
                        System.out.print("*");
                    }
                    else {
                        System.out.print("|");
                    }
                    System.out.print(symbol);
                }
                else {
                    if(this.setores[i][j].ehPorta("LEFT") && 
                        (this.setores[i][j].getP1Esta() || this.setores[i][j].getP2Esta())) {
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
    /* Imprime os setores dos jogadores
    // p1Esta e p2Esta definem a impressão dos jogadores
    // TODO: comentar, limpar/otimizar e, talvez, colocar em outra classe */
    private void imprimeSetorJogadores() {
        Jogador p1 = new JogadorSimples();
        p1.setVivo(false);
        Jogador p2 = new JogadorSuporte();
        p2.setVivo(false);

        boolean diferente = false;

        for (int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if(this.setores[i][j].getP1Esta())
                    p1 = this.setores[i][j].getJogador(0); 
                if(this.setores[i][j].getP2Esta())
                    p2 = this.setores[i][j].getJogador(1);
            }
        }
        
        if(!p1.vivo && !p2.vivo)
            return;

        // Define se será impresso um ou 2 setores;
        if(p1.vivo && p2.vivo) {
            if (p1.X != p2.X || p1.Y != p2.Y) {
                diferente = true;
            }
        }
        else if(!p1.vivo && p2.vivo)
            diferente = true;

        if(p1.vivo) {
            System.out.println();
            System.out.print("  " + "Setor [" + (p1.X + 1) + "," + (p1.Y + 1) + "]" + "      ");
        }
        if(diferente && p2.vivo) {
            System.out.print("  " + "Setor [" + (p2.X + 1) + "," + ( p2.Y + 1) + "]");
        }

        System.out.println();
        
        if(p1.vivo) {
            if(this.setores[p1.X][p1.Y].ehPorta("UP")) {
                System.out.print("|------*------|");
            }
            else {
                System.out.print("|-------------|");
            }
            System.out.print("    ");
        }
        if(diferente && p2.vivo) {
            if(this.setores[p2.X][p2.Y].ehPorta("UP")) {
                System.out.print("|------*------|");
            }
            else {
                System.out.print("|-------------|");
            }
        }

        System.out.println();

        Inimigo[] inimigos;

        if(p1.vivo) {
            inimigos = this.setores[p1.X][p1.Y].getInimigos();
            System.out.print("|");

            for(int i = 0; i < inimigos.length; i++) {
                System.out.print(" ");
                System.out.print(inimigos[i].getAtk() + "/" + inimigos[i].getDef());
            }
            System.out.print(" ".repeat(13 - 4 * inimigos.length) + "|");

            System.out.print("    ");
        }
        if(diferente && p2.vivo) {
            inimigos = this.setores[p2.X][p2.Y].getInimigos();

            System.out.print("|");

            for(int i = 0; i < inimigos.length; i++) {
                System.out.print(" ");
                System.out.print(inimigos[i].getAtk() + "/" + inimigos[i].getDef());
            }
            System.out.print(" ".repeat(13 - 4 * inimigos.length) + "|");
        }
        
        System.out.println();

        if(p1.vivo) {
            System.out.print("|" + " ".repeat(13) + "|");
            System.out.print("    ");
        }
        if(diferente && p2.vivo) {
            System.out.print("|" + " ".repeat(13) + "|");
        }

        System.out.println();

        if(p1.vivo) {
            if(this.setores[p1.X][p1.Y].ehPorta("LEFT")) {
                System.out.print("*" + " ".repeat(13));
            }
            else {
                System.out.print("|" + " ".repeat(13));
            }
            if(this.setores[p1.X][p1.Y].ehPorta("RIGHT")) {
                System.out.print("*");
            }
            else {
                System.out.print("|");
            }
            System.out.print("    ");
        }
        if (diferente ) {
            if(this.setores[p2.X][p2.Y].ehPorta("LEFT")) {
            System.out.print("*" + " ".repeat(13));
            }
            else {
                System.out.print("|" + " ".repeat(13));
            }
            if(this.setores[p2.X][p2.Y].ehPorta("RIGHT")) {
                System.out.print("*");
            }
            else {
                System.out.print("|");
            }
        }

        System.out.println();

        if(p1.vivo) {
            System.out.print("|  P1");
        }
        else if(p2.vivo)
            System.out.print("|  P2");

        if(p1.vivo && diferente && p2.vivo) {
            System.out.print(" ".repeat(9) + "|" + "    " + "|  P2");
            System.out.print("         "+ "|");
        }
        else if (p1.vivo && p2.vivo) {
            System.out.print("    P2   "+ "|");
        }
        else {
            System.out.print("         "+ "|");
        }

        System.out.println();
        
        // Atributos
        
        if(p1.vivo) {
            System.out.print("|  "+ p1.getAtk() + "/" + p1.getDef());
        }
        else if(p2.vivo) {
            System.out.print("|  "+ p2.getAtk() + "/" + p2.getDef());
        }

        if(p1.vivo && diferente && p2.vivo) {
            System.out.print(" ".repeat(8) + "|" + "    " + "|  " + p2.getAtk() + "/" + p2.getDef());
            System.out.print("        "+ "|");
        }
        else if (p1.vivo && p2.vivo) {
            System.out.print("   " +p2.getAtk() + "/" + p2.getDef() + "  "+ "|");
        }
        else {
            System.out.print("        "+ "|");
        }

        System.out.println();

        if(p1.vivo) {
            if(this.setores[p1.X][p1.Y].ehPorta("DOWN")) {
                System.out.print("|------*------|");
            }
            else {
                System.out.print("|-------------|");
            }
            System.out.print("    ");
        }
        if(diferente && p2.vivo) {
            if(this.setores[p2.X][p2.Y].ehPorta("DOWN")) {
                System.out.print("|------*------|");
            }
            else {
                System.out.print("|-------------|");
            }
        }

        System.out.println();
    }
}