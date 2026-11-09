import java.util.Scanner;

public class JogadorSuporte extends Jogador{
    // Construtor padrao
    public JogadorSuporte(){
            super(2, 2);
            this.atk = 1;
            this.def = 7;
            this.vivo = true;
    }
    public JogadorSuporte(Estatistica est){
            super(2, 2, est);
            this.atk = 1;
            this.def = 7;
            this.vivo = true;
    }

    // Adiciona 2 pontos de def ao jogador simples
    // caso ele esteja no mesmo setor
    public void restaurar(Jogador p1){
            if (this.X == p1.getX() && this.Y == p1.getY())
                p1.setDef(p1.getDef() + 2);
    }

    // Adiciona 2 pontos de def ao jogador de suporte
    public void restaurar(){
        this.def += 2;
    }

    @Override
    public void texto(){
        System.out.println("Ação P2: ");
    }

    @Override
    public void imprimirAcoesExtras(Jogador p1){
        System.out.print("Restaurar: [R2] Restaurar P2");

        if (p1.getX() == this.getX() && p1.getY() == this.getY())
            System.out.print(" | [R1] Restaurar P1");

        System.out.println();
    }

    @Override
    public boolean acoesExtras(Jogador p1, String op, Tabuleiro tab){
        Tabuleiro.limparTela();
        if (op.length() == 2 && op.charAt(0) == 'R'){
            if (op.charAt(1) == '1'){
                if (p1.getX() == this.getX() && p1.getY() == this.getY() && p1.isVivo()){
                    System.out.println("DEF de P1 aumentada em 2!");
                    this.restaurar(p1);
                    this.est.mudarCura(this);
                    return true;
                }
                return false;
            }
            if (op.charAt(1) == '2'){
                System.out.println("DEF de P2 aumentada em 2!");
                this.restaurar();
                this.est.mudarCura(this);
                return true;
            }
            return false;
        }
        return false;
    }

    public void opcoes(Scanner input, Tabuleiro tab, Jogador p1, Game g){
        super.opcoes(input, tab, g);
        this.imprimirAcoesExtras(p1);
    }
}
