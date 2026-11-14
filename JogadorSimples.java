public class JogadorSimples extends Jogador {
    /****************CONSTRUTORES****************/
    public JogadorSimples(){
        super(2, 2);
        this.atk = 2;
        this.def = 6;
        this.vivo = true;
    }

    public JogadorSimples(Estatistica est){
        super(2, 2, est);
        this.atk = 2;
        this.def = 6;
        this.vivo = true;
    }

    /****************OUTROS METODOS****************/
    @Override 
    public void texto(){
        System.out.println("-> Turno P1: ");
    }

    @Override
    public void imprimirAcoesExtras(Jogador p1){
        System.out.println("╚═══════════════════════════════════════╝");
        System.out.print("Escolha sua ação > ");
        
        System.out.println();
    }
}
