public class JogadorSuporte extends Jogador{
    // Construtor padrao
    public JogadorSuporte(){
            this.atk = 1;
            this.def = 7;
            this.vivo = true;
    }

    // Adiciona 2 pontos de def ao jogador simples
    // caso ele esteja no mesmo setor
    // Recebe o setor do jogador suporte e o p1
    public void restaurar(Setor setor, JogadorSimples p1){
            if (setor.getX() == p1.getX() && setor.getY() == p1.getY())
                    p1.setDef(p1.getDef() + 2);

    // Adiciona 2 pontos de def ao jogador de suporte
    public void restaurar(){
        this.def += 2;
    }
}
