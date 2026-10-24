public class Inimigo implements Entidade{
    public int atk, def, linha, coluna;
    public boolean vivo;


    public Inimigo(){};
    public Inimigo(int atk, int def, boolean vivo, int linha, int coluna){
        this.setAtk(atk);
        this.setDef(def);
        this.setVivo(vivo);
    }

    public void setAtk(int atk){
        if(atk > 0) 
            this.atk = atk;
    }

    public int getAtk() {
        return atk;
    }

    public void setDef(int def) {
        if(def > 0)
            this.def = def;
    }

    public int getDef() {
        return def;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public boolean getVivo() {
        return vivo;
    }

    public void atacar(Inimigo oponente){};
    public void atacar(JogadorSimples p1){
        p1.recebeuDano(atk);
    }

    public void atacar(JogadorSuporte p2){
        p2.recebeuDano(atk);
    }

    public void recebeuDano(int dano){
        this.def -= dano;
        if(this.def <= 0)
            this.vivo = false;
    }
}
