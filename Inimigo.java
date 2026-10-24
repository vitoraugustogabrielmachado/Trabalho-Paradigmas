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
        this.vivo = true;
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

    public boolean isVivo() {
        return this.vivo;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public void atacar(Inimigo oponente){};

    // Ataca o jogador p caso num seja par
    public void atacar(Jogador p){
        Random random = new Random();
        int num = random.nextInt(6)+1;
        if (num % 2 == 0) {
            p.setDef(p.getDef()-this.atk);
            if (p.getDef() <= 0)
                    p.setVivo(false);
        }
    }
}
