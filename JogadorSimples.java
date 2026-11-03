import java.util.Scanner;

public class JogadorSimples extends Jogador {
    public JogadorSimples(){
        super(2, 2);
        this.atk = 2;
        this.def = 6;
        this.vivo = true;
    }
    //sera q nn eh preciso setar a posicao inicial deles?
    //da pra continuar usando o super so q mandando valores
    //fixos, sla ax mais bunitinho
    // Concordo 

    public void texto(){
        System.out.println("Ação P1: ");
    }

    public boolean haAcao(Setor set){
        return set.qtdInimigosVivos() > 0 || set.getTipo() != 3;
    }
}
