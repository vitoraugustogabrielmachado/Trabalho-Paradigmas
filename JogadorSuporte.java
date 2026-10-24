public class JogadorSuporte extends Jogador{
    // Construtor padrao
    public JogadorSuporte(){
            this.atk = 1;
            this.def = 7;
            this.vivo = true;
    }

    // Adiciona 2 pontos de def ao jogador simples
    // caso ele esteja no mesmo setor
    public void restaurar(JogadorSimples p1){
        if (this.X == p1.getX() && this.Y == p1.getY())
            p1.setDef(p1.getDef() + 2);
    }

    // Adiciona 2 pontos de def ao jogador de suporte
    public void restaurar(){
        this.def += 2;
    }
}
