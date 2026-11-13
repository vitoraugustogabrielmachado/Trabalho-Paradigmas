/*
* Classe responsável por fornecer os métodos
* para controlar o fluxo principal do jogo.
*/
// import java.util.Random;
import java.util.LinkedList;
import java.util.Scanner;

public class Game {
    // ATRIBUTOS
    private Tabuleiro tab;
    private Jogador p1, p2;
    private LinkedList<String> fila;
    private int ciclos;
    private String state;
    private boolean ATK, MOV, PROC;

    /****************CONSTRUTORES****************/
    public Game(){}
    /* A ideia é mudar o game.play() para um switch de game.state em que ele controlará o fluxo total do começo, turnos e fim. 
    os turnos poderiam estar em uma lista circular com ordem TURNOP1 -> TURNOP1 -> TURNOP2 -> TURNOP2 -> TURNOINIMIGOS -> TURNOP1...
    Assim a cada ação a fila andaria e passaria o valor para game.state.
    Caso os jogores achem a infecção game.state -> Fim
    Caso morram Game.state -> Fim
    Caso ciclo max -> game.state -> Fim 
    arraydeque -> classe fodona dar uma olhada
    */

    /****************METODOS GET E SET****************/

    public void setCiclos(int ciclos) {
        if(ciclos > 0)
            this.ciclos = ciclos;
    }
    public int getCiclos() {
        return this.ciclos;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return this.state;
    }
    public void setTab(Tabuleiro tab) {
        this.tab = tab;
    }
    public Tabuleiro getTab() {
        return this.tab;
    }
    public void setATK(boolean atk) {
        ATK = atk;
    }
    public boolean getAtk(){
            return this.ATK;
    }
    public void setMOV(boolean mv) {
        MOV = mv;
    }
    public boolean getMov(){
            return this.MOV;
    }
    public void setPROC(boolean pro) {
        PROC = pro;
    }
    public boolean getProc(){
            return this.PROC;
    }
    public void setP1(Jogador p1) {
        this.p1 = p1;
    }
    public Jogador getP1() {
        return p1;
    }
    public void setP2(Jogador p2) {
        this.p2 = p2;
    }
    public Jogador getP2() {
        return p2;
    }
    public void setFila(LinkedList<String> fila) {
        this.fila = fila;
    }
    public LinkedList<String> getFila() {
        return fila;
    }

    /****************OUTROS METODOS****************/

    /*
    * Adiciona na lista os eventos que vão ocorrer em loop.
    */
    public void adicionarFila(){
        LinkedList<String> temp = this.getFila();
        temp.addLast("AcaoP1");
        temp.addLast("AcaoP1");
        temp.addLast("AcaoP2");
        temp.addLast("AcaoP2");
        temp.addLast("AcaoOponentes");
        this.setFila(temp);
    }
    
    /*
    * Inicializa os atributos de Game para iniciar o jogo.
    */
    public void init() {
        this.setTab(new Tabuleiro());
        this.setP1(new JogadorSimples(new Estatistica())); //TODO: USO DE POLIMORFISMO COERCAO UPCAST
        this.setP2(new JogadorSuporte(new Estatistica())); //TODO: USO DE POLIMORFISMO COERCAO UPCAST
        this.setCiclos(0);
        this.getTab().setJogador(p1);
        this.getTab().setJogador(p2);
    }

    /*
    * Loop principal onde a lógica do jogo
    * é de fato implementada.
    */
    public void play() {
        int cont = 0;
        boolean valido = false;

        Scanner scanner = new Scanner(System.in);
        this.setFila(new LinkedList<>());
        this.adicionarFila();

        Tabuleiro.limparTela();
        tab.imprimeTabuleiro();
        //ta indo ate 125 pq eu conto cont a cada jogada ent como cada turno sao 5 jogadas, 125
        while (this.getCiclos() < 125 && (p1.getDef() > 0 || p2.getDef() > 0) && this.p1.est.getVencedor() == 0){ //Verifico as condicoes de derrota e vitoria
            this.setState(this.getFila().removeFirst()); //Removo o primeiro evento, mas salvo ele num atributo da classe
            switch (this.getState()) {
                case "AcaoP1":
                    while(this.getP1().isVivo() == true && valido == false){ //A flag de valido eh para saber se o usuario nn fez uma acao impossivel no setor
                        this.getP1().opcoes(scanner, this.getTab(), this); //Imprime as opcoes que o P1 pode fazer num geral, ate mesmo as que ele ta impossibilitado no momento
                        valido = this.getP1().turno(scanner, tab, this, this.getState(), this.getP1()); //Ocorre a acao
                        cont = this.getCiclos() + 1;
                    }
                    break;
                case "AcaoP2": //Mesma coisa que para P1
                    while(this.getP2().isVivo() == true && valido == false){
                        if(p2 instanceof JogadorSuporte){
                            JogadorSuporte temp = (JogadorSuporte) p2; // TODO: USO DE POLIMORFISMO COERCAO DOWNCAST
                            temp.opcoes(scanner, tab, p1, this); //As opcoes de P2 sao diferentes devido ao recuperarDef
                        }
                        valido = this.getP2().turno(scanner, tab, this, this.getState(), this.getP1());
                        cont = this.getCiclos() + 1;
                    }
                    break;
                case "AcaoOponentes": //Mesma situacao dos jogadores
                    Inimigo op = new Inimigo();
                    op.ataqueInimigo(tab, p1, p2);
                    Tabuleiro.limparTela();
                    this.getTab().imprimeTabuleiro();
                    cont = this.getCiclos() + 1;
                    break;
            }
            this.setCiclos(cont);

            if(this.getTab().getSetor(this.getP1().getX(), this.getP1().getY()).getEhFinal() == true) //Verifica se P1 venceu
                this.p1.est.setVencedor(1); 
            else if(this.getTab().getSetor(this.getP2().getX(), this.getP2().getY()).getEhFinal() == true) //Verifica se P2 venceu
                this.p1.est.setVencedor(2); 

            fila.addLast(this.getState()); //Recoloca o evento retirado novamente na fila, mas agr esta na ultima posicao
            valido = false; //Recoloca a flag com o valor certo, para nn ficar com "lixo" de memoria
        }

    }

    /*
    * Método responsáevl por fornecer as estatísticas finais
    * do jogo após seu término.
    */
    public void end() {
        this.p1.est.imprimirTelaFinal(this);
        this.p1.est.imprimirP1();
        this.p2.est.imprimirP2(this.p1);
    }

}

