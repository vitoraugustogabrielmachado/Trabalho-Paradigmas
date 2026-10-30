import java.util.Random;

public class Setor {
    private Jogador[] jogadores;
    private Inimigo[] inimigos;
    private Boolean ehFinal, ehIncial, p1Esta, p2Esta;
    private final Boolean UP, DOWN, LEFT, RIGHT; //Portas == True; False == 0
    private final int tipo; // 1 -> Normal; 2 -> Oculto; 3 -> Privado

    public Setor() {
        Random aleat = new Random();

        this.jogadores = new Jogador[2];

        inimigos = new Inimigo[aleat.nextInt(3) + 1];
        // Inicializa todos os inimigos do setor
        for(int i = 0; i < inimigos.length; i++) {
            inimigos[i] = new Inimigo(aleat.nextInt(1, 7), aleat.nextInt(1, 7), true);
        }

        // Tudo inicia falso
        this.ehIncial = false;
        this.ehFinal = false;
        this.p1Esta = false;
        this.p2Esta = false;

        this.UP = false;
        this.DOWN = false;
        this.LEFT= false;
        this.RIGHT = false;

        // define tipo do setor
        this.tipo = aleat.nextInt(3) + 1;
    }

    /********* GET/SET **********/
    /* Se i == 0 -> Retorna JogadorSimples p1
    // Se i == 1 -> Retorna JogadorSuporte p2*/
    public Jogador getJogador(int i) {
        return this.jogadores[i];
    }
    public Inimigo[] getInimigos() {
        return inimigos;
    }
    public int getTipo() {
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
    public void setInimigos(Inimigo[] inimigos) {
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
}
