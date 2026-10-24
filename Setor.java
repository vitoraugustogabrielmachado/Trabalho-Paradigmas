import java.util.Random;

public class Setor {
    public int x, y;
    public Inimigo[] inimigos;
    public Boolean ehFinal, ehIncial, p1Esta, p2Esta;
    public Boolean UP, DOWN, LEFT, RIGHT; //Portas == True; False == 0
    public int tipo; // 1 -> Normal; 2 -> Oculto; 3 -> Privado

    public Setor(int x, int y) {
        Random aleat = new Random();
        this.x = x;
        this.y = y;

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
    public Inimigo[] getInimigos() {
        return inimigos;
    }
    public void setInimigos(Inimigo[] inimigos) {
        this.inimigos = inimigos;
    }
    public void setJogadorP1() {
        this.p1Esta = true;
    }
    public void setJogadorP2() {
        this.p2Esta = false;
    }
    public void setInicio() {
        this.ehIncial = true;
    }
    public void setFinal(){
        this.ehFinal = true;
    }
    public void unsetJogadorP1() {
        this.p1Esta = false;
    }

    public void unsetJogadorP2() {
        this.p2Esta = false;
    }
}
