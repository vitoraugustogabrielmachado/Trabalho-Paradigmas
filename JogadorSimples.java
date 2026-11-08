public class JogadorSimples extends Jogador {
    public JogadorSimples(){
        super(2, 2);
        this.atk = 2;
        this.def = 6;
        this.vivo = true;
    }

    public void texto(){
        System.out.println("Ação P1: ");
    }
}
