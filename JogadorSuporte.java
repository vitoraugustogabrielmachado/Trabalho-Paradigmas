import java.util.Scanner;

public class JogadorSuporte extends Jogador{
    // Construtor padrao
    public JogadorSuporte(){
            super(2, 2);
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

    @Override
    public void texto(){
        System.out.println("Ação P2: ");
    }

    @Override
    public void imprimirAcoesExtras(JogadorSimples p1){
        System.out.print("Restaurar: [R2] Restaurar P2");

        if (p1.getX() == this.getX() && p1.getY() == this.getY())
            System.out.print(" | [R1] Restaurar P1");

        System.out.println();
    }

    @Override
    public boolean acoesExtras(JogadorSimples p1, String op, Tabuleiro tab){
        tab.limparTela();
        if (op.length() == 2 && op.charAt(0) == 'R'){
            if (op.charAt(1) == '1'){
                if (p1.getX() == this.getX() && p1.getY() == this.getY() && p1.isVivo()){
                    System.out.println("DEF de P1 aumentada em 2!");
                    this.restaurar(p1);
                    return true;
                }
                return false;
            }
            if (op.charAt(1) == '2'){
                System.out.println("DEF de P2 aumentada em 2!");
                this.restaurar();
                return true;
            }
            return false;
        }
        return false;
    }
}
