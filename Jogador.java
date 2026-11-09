import java.util.Random;
import java.util.Scanner;

public abstract class Jogador implements Entidade {
    // Atributos
    protected int atk, def, X, Y;
    protected boolean vivo;
    protected Estatistica est;

    /****************CONSTRUTORES****************/
    public Jogador(){};
    public Jogador(int X, int Y){
            this.X = X;
            this.Y = Y;
    }

    public Jogador(int X, int Y, Estatistica est){
            this.X = X;
            this.Y = Y;
            this.est = est;
    }

    /****************METODOS GET E SET****************/
    public void setAtk(int atk){
        if(atk > 0 && atk < 4)
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

    public int getX() {
            return this.X;
    }

    public void setX(int X) {
            this.X = X;
    }

    public int getY() {
            return this.Y;
    }

    public void setY(int Y) {
            this.Y = Y;
    }

    /****************OUTROS METODOS****************/

    //Imprime todas as opcoes que o usuario pode fazer
    public void opcoes(Scanner input, Tabuleiro tab, Game g){
        this.texto();
        System.out.println("Movimento: [W] Cima | [A] Esquerda | [S] Baixo | [D] Direita");
        Setor atu = tab.getSetor(this.X, this.Y);
        if(atu.qtdInimigosVivos() > 0){ 
            System.out.print("Atacar: ");
            for(int i = 1; i <= atu.getInimigos().size() ; i++)
                if (atu.getInimigos().get(i-1).isVivo()) System.out.printf("[A" + i + "] Atacar Inimigo %d | ", i);
            System.out.println();
        }
        System.out.println("Procurar: [P]");
    }

    //TODO: modularizar a parte do atk, deixa com o pai(vitor)
    public boolean turno(Scanner scanner, Tabuleiro tab, Game g, String state, Jogador p1){
        Setor atu = tab.getSetor(this.X, this.Y);
        if(atu.getEhFinal() == true)
            return(true);

        Random aleat = new Random();
        boolean acaoFeita = false;

        setarFlags(atu, g);

        String op = scanner.nextLine().toUpperCase();
        if(g.getMov() == true){
            acaoFeita = this.acaoMovimentar(op, tab);
        }
        if(acaoFeita == false && g.getAtk() == true){ 
            if (op.length() == 2 && op.charAt(0) == 'A')
                acaoFeita = this.acaoAtacar(op, atu, aleat);
        }
        
        if (acaoFeita == false && g.getProc() == true){ 
            if (op.length() == 1 && op.charAt(0) == 'P'){
                this.acaoProcurar(atu, tab);
                acaoFeita = true;  
            }
        }
        
        if(acaoFeita == false && state == "AcaoP2"){
            acaoFeita = this.acoesExtras(p1, op, tab);
        }

        for(int i = 0; i < atu.getInimigos().size(); i++) {
            if(!atu.getInimigos().get(i).isVivo()) {
                atu.getInimigos().remove(i);
            }
        }
        
        tab.imprimeTabuleiro();
        if(acaoFeita == false){
            Tabuleiro.limparTela();
            tab.imprimeTabuleiro();
            if(g.getProc() == false && op.charAt(0) == 'P')
                System.out.println("*************Procurar não permitido.*************");
            else
                System.out.println("*************Acao invalida*************");
        }

        return acaoFeita;
    }

    public void movimentar(Tabuleiro tab, String direcao){
        if(tab.movimentoLiberado(this.X, this.Y, direcao)){ 
            tab.unsetJogador(this);

            switch (direcao) {
                case "UP":
                    this.X--;
                    break;

                case "DOWN":
                    this.X++;
                    break;

                case "RIGHT":
                    this.Y++;
                    break;

                case "LEFT":
                    this.Y--;
                    break;
            }

            tab.setJogador(this);
        }
        else{
            System.out.println("Nao é possivel ir para " + direcao); 
        }
    }

    public boolean acaoMovimentar(String acao, Tabuleiro tab){
        Tabuleiro.limparTela();
        switch (acao){ //TODO: tem um bug q eh quando vc movimenta pra baixo na borda de baixo, ver como resolver isso dps
            case "W":
                this.movimentar(tab, "UP");
                return true;
            case "A":
                this.movimentar(tab, "LEFT");
                return true;
            case "S":
                this.movimentar(tab, "DOWN");
                return true;
            case "D":
                this.movimentar(tab, "RIGHT");
                return true;
            default:
                return false;
        }
    }

    // Recebe o setor atual do jogador e procura nele
    public void acaoProcurar(Setor setor, Tabuleiro tab){
        Random random = new Random();
        int num = random.nextInt(6)+1;
        this.est.setProcurasTotal(this.est.getProcurasTotal() + 1);
        Tabuleiro.limparTela();
        switch (num) {
            case 4:
                this.def++;
                System.out.println("Defesa incrementada!");
                break;
            case 5:
                System.out.println("Defesa incrementada em 2!");
                this.def += 2;
                break;
            case 6:
                for (int i = 0; i < setor.getInimigos().size() ; i++)//verifica a quantidade de inimigos para causar dano em todos
                    if (setor.getInimigos().get(i).isVivo() == true)
                        setor.getInimigos().get(i).dano(1);
                if(setor.getInimigos().size() > 0)
                    System.out.println("Todos os inimigos perderam vida!");
                break;
            default:
                System.out.println("Nada encontrado"); 
                return;
        }
        this.est.setProcurasCerta(this.est.getProcurasCerta() + 1);
        
    }

    public boolean acaoAtacar(String op, Setor atu, Random aleat){   
        Tabuleiro.limparTela();
        int i = 0;
        char temp = op.charAt(1);
        switch (temp) {
            case '1':
                i = 1;
                break;
            case '2':
                i = 2;
                break;
            case '3':
                i = 3;
                break;
        }

        if(i > 0){
            if (atu.getInimigos().size() <= i-1 || !atu.getInimigos().get(i-1).isVivo()){
                System.out.println("Não há inimigos.");
                return false;
            }
            if (atu.getTipoSetor() == 2){
                if (aleat.nextInt(2) == 0) {
                    System.out.println("Ataque bem sucedido!");
                    this.causarDano(atu.getInimigos().get(i-1));
                }
                else
                    System.out.println("Ataque falhou.");
            }
            else {
                System.out.println("Ataque bem sucedido!");
                this.causarDano(atu.getInimigos().get(i-1));
            }
        }
        else
            return false;
        return true;
    }

    public void causarDano(Inimigo op){
        op.dano(this.atk);
        if(op.getVivo() == false)
            this.est.mudarKills(this);
    }

    public boolean isVivo() {
        return vivo;
    }

    public void causarDano(Jogador p){};

    protected abstract void texto();

    protected void imprimirAcoesExtras(Jogador p1){}; //nn eh definido como abstrato pois se fosse jogadorSimples deveria implementar isso, mas nn pode

    protected boolean acoesExtras(Jogador p1, String op, Tabuleiro tab){
        return false;
    };

    public void dano(int dano){
        this.def -= dano;
        if (this.def <= 0){
            this.def = 0;
            this.vivo = false;
            int player;
            if (this instanceof JogadorSimples) player = 1;
            else player = 2;
            System.out.println("Jogador " + player + " morto!");
        }
    }
    
    public void setarFlags(Setor atu, Game g){
        if(atu.qtdInimigosVivos() == 0)
            g.setMOV(true);
        else
            g.setMOV(false);
        if(atu.qtdInimigosVivos() > 0)
            g.setATK(true);
        else
            g.setATK(false);
        if(atu.getTipoSetor() != 3)
            g.setPROC(true);
        else
            g.setPROC(false);
    }

}
