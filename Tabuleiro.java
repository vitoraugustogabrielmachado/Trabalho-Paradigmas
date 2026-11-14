import java.util.ArrayList;
import java.util.Random;

public class Tabuleiro {
    private final String titulo = "Antivírus por um dia"; 
    private final Setor[][] setores;
    private int[] portas;
    
    /****************CONSTRUTORES****************/
    public Tabuleiro() {
        Random aleat = new Random();
        this.portas = new int[4];
        this.setores = new Setor[5][5];
        // Inicializa setor por setor;
        for (int y = 0; y < 5; y++) {
            for (int x = 0; x < 5; x++) {
                this.setPortas(x, y);
                if (x == 2 && y == 2) this.setores[2][2] = new Setor(true);
                else this.setores[x][y] = new Setor(portas);
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

    /****************METODOS GET E SET****************/
    // Retorna o setor (x, y)
    public Setor getSetor(int x, int y) {
        return this.setores[x][y];
    }
    // Retorna tipo do setor (x, y)
    public int getTipo(int x, int y) {
        return this.setores[x][y].getTipoSetor();
    }
    public void setPortas(int x, int y){
        if(y == 0){
            if(x == 0){
                portas[0] = 0;
                portas[1] = 1;
                portas[2] = 0;
                portas[3] = 1;
            }else if(x == 4){
                portas[0] = 1;
                portas[1] = 0;
                portas[2] = 0;
                portas[3] = 1;
            }else{
                portas[0] = 1;
                portas[1] = 1;
                portas[2] = 0;
                portas[3] = 1;
            }
        }else if(y != 0 && x == 0){
            if(y == 4){
                portas[0] = 0;
                portas[1] = 1;
                portas[2] = 1;
                portas[3] = 0;
            }else{
                portas[0] = 0;
                portas[1] = 1;
                portas[2] = 1;
                portas[3] = 1;
            }
        }else if(y == 4){
            if(x == 4){
                portas[0] = 1;
                portas[1] = 0;
                portas[2] = 1;
                portas[3] = 0;
            }else{
                portas[0] = 1;
                portas[1] = 1;
                portas[2] = 1;
                portas[3] = 0;
            }
        }else if(y != 4 && x == 4){
            portas[0] = 1;
            portas[1] = 0;
            portas[2] = 1;
            portas[3] = 1;
        }else{
            portas[0] = 1;
            portas[1] = 1;
            portas[2] = 1;
            portas[3] = 1;
        }
    }
    // Retorna o vetor de inimigos do setor (x, y)
    public ArrayList<Inimigo> getInimigos(int x, int y) {
        return this.setores[x][y].getInimigos();
    }
    // Substitui o setor (x, y) por "setor"
    public void setSetor(int x, int y, Setor setor) {
        this.setores[x][y] = setor;
    }
    // Substitui o vetor de inimigos do setor (x, y) pelo vetor parâmetro
    public void setInimigos(int x, int y, ArrayList<Inimigo> inimigos) {
        this.setores[x][y].setInimigos(inimigos);
    }
    // Indica que p está no setor (x, y)
    public void setJogador(Jogador p) {
        if (p instanceof JogadorSimples)
            this.setores[p.getX()][p.getY()].setJogador((JogadorSimples) p);
        if (p instanceof JogadorSuporte)
            this.setores[p.getX()][p.getY()].setJogador((JogadorSuporte) p);
    }
    // Indica que p1 está no setor (x, y)
    public void setJogador(JogadorSimples p1) {
        this.setores[p1.getX()][p1.getY()].setJogador(p1);
    }
    // Indica que p2 está no setor (x, y)
    public void setJogador(JogadorSuporte p2) {
        this.setores[p2.getX()][p2.getY()].setJogador(p2);
    }
    // Indica que p não está ou saiu do setor (x, y)
    public void unsetJogador(Jogador p) {
        if (p instanceof JogadorSimples)
            this.setores[p.getX()][p.getY()].unsetJogador((JogadorSimples) p);
        if (p instanceof JogadorSuporte)
            this.setores[p.getX()][p.getY()].unsetJogador((JogadorSuporte) p);
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
    /* Imprime o tabuleiro + setores*/
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
    /* Imprime setor por setor */
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
                        if((this.setores[i][j].ehPorta("UP"))  && 
                        (this.setores[i-1][j].getP1Esta() || this.setores[i][j].getP1Esta() || this.setores[i-1][j].getP2Esta() || this.setores[i][j].getP2Esta())) {
                            System.out.print("|-*-");
                        }
                        else {
                            System.out.print("|---");
                        }
                    }
                    else {
                        if((this.setores[i][j].ehPorta("UP")) && 
                        (this.setores[i][j].getP1Esta() || this.setores[i][j].getP1Esta() || this.setores[i-1][j].getP2Esta() || this.setores[i-1][j].getP2Esta())) {
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
                        (this.setores[i][j-1].getP1Esta() || this.setores[i][j].getP1Esta() || this.setores[i][j-1].getP2Esta() || this.setores[i][j].getP2Esta())) {
                        System.out.print("*");
                    }
                    else {
                        System.out.print("|");
                    }
                    System.out.print(symbol);
                }
                else {
                    if(this.setores[i][j].ehPorta("LEFT") && 
                        (this.setores[i][j-1].getP1Esta() || this.setores[i][j].getP1Esta() || this.setores[i][j].getP2Esta() || this.setores[i][j-1].getP2Esta())) {
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

    /* Imprime os setores dos jogadores */
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

        imprimeSetorTipo(p1, p2, diferente);
        imprimeSetorPortaSuperior(p1, p2, diferente);
        imprimeSetorInimigos(p1, p2, diferente);
        imprimeSetorLinhaVazia(p1, p2, diferente);
        imprimeSetorPortasLaterais(p1, p2, diferente);
        imprimeSetorRotulos(p1, p2, diferente);
        imprimeSetorAtributos(p1, p2, diferente);
        imprimeSetorPortaInferior(p1, p2, diferente);
    }

    // Imprime o tipo dos setores dos jogadores
    private void imprimeSetorTipo(Jogador p1, Jogador p2, boolean diferente){
        int tipo1 = this.setores[p1.X][p1.Y].getTipoSetor();
        int tipo2 = this.setores[p2.X][p2.Y].getTipoSetor();

        if(p1.vivo) {
            System.out.println();
            if(tipo1 == 1)
                System.out.print("  " + "Setor Normal [" + (p1.X + 1) + "," + (p1.Y + 1) + "]" + "      ");
            else if(tipo1 == 2)
                System.out.print("  " + "Setor Oculto [" + (p1.X + 1) + "," + (p1.Y + 1) + "]" + "      ");
            else if(tipo1 == 3)
                System.out.print("  " + "Setor Privado [" + (p1.X + 1) + "," + (p1.Y + 1) + "]" + "      ");
        }
        if(diferente && p2.vivo) {
            if(tipo2 == 1)
                System.out.print("  " + "Setor Normal [" + (p2.X + 1) + "," + ( p2.Y + 1) + "]");
            else if(tipo2 == 2)
                System.out.print("  " + "Setor Oculto [" + (p2.X + 1) + "," + ( p2.Y + 1) + "]");
            else if(tipo2 == 3)
                System.out.print("  " + "Setor Privado [" + (p2.X + 1) + "," + ( p2.Y + 1) + "]");
        }

        System.out.println();
    }

    // Imprime a porta superior do setor, se presente
    private void imprimeSetorPortaSuperior(Jogador p1, Jogador p2, boolean diferente){
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
    }

    // Imprime os atributos dos inimigos presentes nos setores
    private void imprimeSetorInimigos(Jogador p1, Jogador p2, boolean diferente){
        ArrayList<Inimigo> inimigos;

        if(p1.vivo) {
            inimigos = this.setores[p1.X][p1.Y].getInimigos();
            System.out.print("|");

            for(int i = 0; i < inimigos.size() ; i++) {
                System.out.print(" ");
                System.out.print(inimigos.get(i).getAtk() + "/" + inimigos.get(i).getDef());
            }
            System.out.print(" ".repeat(13 - 4 * inimigos.size() ) + "|");

            System.out.print("    ");
        }
        if(diferente && p2.vivo) {
            inimigos = this.setores[p2.X][p2.Y].getInimigos();

            System.out.print("|");

            for(int i = 0; i < inimigos.size() ; i++) {
                System.out.print(" ");
                System.out.print(inimigos.get(i).getAtk() + "/" + inimigos.get(i).getDef());
            }
            System.out.print(" ".repeat(13 - 4 * inimigos.size() ) + "|");
        }
        
        System.out.println();
    }

    // Imprime a linha vazia no setor
    private void imprimeSetorLinhaVazia(Jogador p1, Jogador p2, boolean diferente){
        if(p1.vivo) {
            System.out.print("|" + " ".repeat(13) + "|");
            System.out.print("    ");
        }
        if(diferente && p2.vivo) {
            System.out.print("|" + " ".repeat(13) + "|");
        }

        System.out.println();
    }

    // Imprime as portas laterais presentes no setor
    private void imprimeSetorPortasLaterais(Jogador p1, Jogador p2, boolean diferente){
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
    }

    // Imprime rótulos dos jogadores nos setores
    private void imprimeSetorRotulos(Jogador p1, Jogador p2, boolean diferente){
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
    }

    // Imprime os atributos dos jogadores nos setores
    private void imprimeSetorAtributos(Jogador p1, Jogador p2, boolean diferente){
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
    }

    // Imprime porta inferior do setor, se presente
    private void imprimeSetorPortaInferior(Jogador p1, Jogador p2, boolean diferente){
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

    // Metodo com codigo ANSI para limpar o terminal
    public static void limparTela() {
        System.out.print("\033[H\033[2J\033[3J\033c");
        System.out.flush();
    }
}

