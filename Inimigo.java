import java.util.Random;

public class Inimigo implements Entidade{

    // Atributos
    private int atk, def;
    private boolean vivo;

    // Construtores
    public Inimigo(){
        // cria o inimigo aleatoriamente
        Random random = new Random();
        int num = random.nextInt(3)+1;
        this.atk = num;
        this.def = num;
        this.vivo = True;
    };

    public Inimigo(int atk, int def, boolean vivo){
        this.setAtk(atk);
        this.setDef(def);
        this.setVivo(vivo);
    }

    // Metodos
    public void setAtk(int atk){
        if(atk > 0 && atk < 4)
            this.atk = atk;
    }

    public int getAtk() {
        return this.atk;
    }

    public void setDef(int def) {
        if(def > 0 && def < 4)
            this.def = def;
    }

    public int getDef() {
        return this.def;
    }

    public void isVivo(boolean vivo) {
        return this.vivo;
    }

    public boolean setVivo() {
        this.vivo = vivo
    }

    public void atacar(Inimigo oponente){};

    // Ataca o jogador p1 caso num seja par
    public void atacar(JogadorSimples p1){
        Random random = new Random();
        int num = random.nextInt(6)+1;
        if (num % 2 == 0) {
            p1.setDef(p1.getDef()-this.atk);
            if (p2.getDef() <= 0)
                    p2.setVivo(false);
        }
    }

    // Ataca o jogador p2 caso num seja par
    public void atacar(JogadorSuporte p2){
        Random random = new Random();
        int num = random.nextInt(6)+1;
        if (num % 2 == 0) {
            p2.setDef(p2.getDef()-this.atk);
            if (p2.getDef() <= 0)
                    p2.setVivo(false);
        }
    }
}
