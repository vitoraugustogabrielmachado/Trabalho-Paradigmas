import java.util.Random;

public class Setor {
    public int x, y;
    public Inimigo[] inimigos;
    public Boolean ehFinal, p1Esta, p2Esta;
    public Boolean UP, DOWN, LEFT, RIGHT; //Portas == True; False == 0
    public int tipo; // 1 -> Normal; 2 -> Oculto; 3 -> Privado

    public Setor(int x, int y) {
        Random aleat = new Random();
        this.x = x;
        this.y = y;

        inimigos = new Inimigo[aleat.nextInt(3) + 1];
        // Inicializa todos os inimigos do setor
        for(int i = 0; i < inimigos.length; i++) {
            inimigos[i] = new Inimigo();
        }

        // Tudo inicia falso
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

    public void setPorta(String direcao, boolean aberta) {
        switch (direcao) {
            case "UP":
                this.UP = aberta;
                break;
            case "DOWN":
                this.DOWN = aberta;
                break;
            case "LEFT":
                this.LEFT = aberta;
                break;
            case "RIGHT":
                this.RIGHT = aberta;
                break;
            default:
                throw new AssertionError();
        }
    }

    public void setFinal(){
        this.ehFinal = true;
    }
}
