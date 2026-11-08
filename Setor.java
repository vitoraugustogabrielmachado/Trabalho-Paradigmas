import java.util.Random;
import java.util.ArrayList;

public class Setor {
    private Jogador[] jogadores;
    private ArrayList<Inimigo> inimigos;
    private Boolean ehFinal, ehIncial, p1Esta, p2Esta;
    private final Boolean UP, DOWN, LEFT, RIGHT; //Portas == True; False == 0
    private final int tipo; // 1 -> Normal; 2 -> Oculto; 3 -> Privado


    /****************CONSTRUTORES****************/
    public Setor(int[] portas) {
        Random aleat = new Random();

        this.jogadores = new Jogador[2];

        int qtd = aleat.nextInt(3) + 1; //Quantidade de inimigos criadas em cada setor eh aleatoria
        inimigos = new ArrayList<Inimigo>(qtd); //USO DE COLECOES
        // Inicializa todos os inimigos do setor
        for(int i = 0; i < qtd; i++) {
            int num = aleat.nextInt(3) + 1;
            inimigos.add(new Inimigo(num, num, true));
        }

        // Tudo inicia como falso
        this.ehIncial = false;
        this.ehFinal = false;
        this.p1Esta = false;
        this.p2Esta = false;

        //Define as portas de cada setor(essencial para a impressao)
        if(portas[0] == 1)
            this.UP = true;
        else
            this.UP = false;
        if(portas[1] == 1)
            this.DOWN = true;
        else
            this.DOWN = false;
        if(portas[2] == 1)
            this.LEFT = true;
        else
            this.LEFT = false;
        if(portas[3] == 1)
            this.RIGHT = true;
        else
            this.RIGHT = false;

        //Define o tipo do setor
        this.tipo = aleat.nextInt(3) + 1;
    }

    public Setor(boolean ehInicial){    
        inimigos = new ArrayList<Inimigo>(0);

        this.jogadores = new Jogador[2];

        this.ehIncial = true;
        this.ehFinal = false;
        this.p1Esta = false;
        this.p2Esta = false;

        this.UP = true;
        this.DOWN = true;
        this.LEFT= true;
        this.RIGHT = true;



        Random aleat = new Random();

        // define tipo do setor
        this.tipo = aleat.nextInt(3) + 1;
    }

    /********* GET/SET **********/
    /* Se i == 0 -> Retorna JogadorSimples p1
    // Se i == 1 -> Retorna JogadorSuporte p2*/
    public Jogador getJogador(int i) {
        return this.jogadores[i];
    }
    public ArrayList<Inimigo> getInimigos() {
        return inimigos;
    }
    public int getTipoSetor() {
        return this.tipo;
    }
    public boolean getEhFinal() {
        return this.ehFinal;
    }
    public boolean getEhInicial() {
        return this.ehIncial;
    }
    public boolean getP1Esta() {
        return this.p1Esta;
    }
    public boolean getP2Esta() {
        return this.p2Esta;
    }
    public void setInimigos(ArrayList<Inimigo> inimigos) {
        this.inimigos = inimigos;
    }
    public void setJogador(JogadorSimples p1) {
        this.jogadores[0] = p1;
        this.p1Esta = true;
    }
    public void setJogador(JogadorSuporte p2) {
        this.jogadores[1] = p2;
        this.p2Esta = true;
    }
    public void setInicio() {
        this.ehIncial = true;
    }
    public void setFinal(){
        this.ehFinal = true;
    }
    public void unsetJogador(JogadorSimples p1) {
        this.p1Esta = false;
    }
    public void unsetJogador(JogadorSuporte p2) {
        this.p2Esta = false;
    }
    public boolean ehPorta(String direcao){
        switch (direcao) {
            case "UP": return this.UP;
            case "DOWN": return this.DOWN;
            case "LEFT": return this.LEFT;
            case "RIGHT": return this.RIGHT;
            default:
                return false;
        }
    }
    
    public int qtdInimigosVivos(){
        int qtd = 0;
        for (int i = 0; i < this.getInimigos().size(); i++)
            if (this.getInimigos().get(i).isVivo())
                qtd++;

        return qtd;
    }

    public void destruirInimigos(){
        for(Inimigo i : this.getInimigos())
            if(i.getVivo() == true)
                i.setVivo(false);
    }

}
