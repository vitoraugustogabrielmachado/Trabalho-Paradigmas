public interface Entidade {
    //interfaces nn podem ter atributos e nem construtores
    public abstract void atacar(Inimigo oponente);
    public abstract void causarDano(Jogador p);
    public abstract void dano(int dano);
}
