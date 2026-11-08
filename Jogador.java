import java.util.Random;
import java.util.Scanner;

public abstract class Jogador implements Entidade {
    // Atributos
    protected int atk, def, X, Y;
    protected boolean vivo;

    // Construtores
    public Jogador(){};

    public Jogador(int X, int Y){
            this.X = X;
            this.Y = Y;
    }

    public Jogador(int atk, int def, boolean vivo){
        this.setAtk(atk);
        this.setDef(def);
        this.setVivo(vivo);
    }

    // Metodos
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

    public boolean isVivo() {
        return vivo;
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

    public void atacar(Inimigo op){
        op.dano(this.atk);
    }

    // considero que na main foi verificado se ha inimigos vivos, pq isso impossibilita o movimento
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
            System.out.println("Nao é possivel ir para " + direcao); //mesma questao do comentario do metodo abaixo
        }
    }

    public void causarDano(Jogador p){};

    // Recebe o setor atual do jogador **Acho que o ideal seria receber apenas os inimigos
    public void procurar(Setor setor, Tabuleiro tab){
        Random random = new Random();
        int num = random.nextInt(6)+1;
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
                for (int i = 0; i < setor.getInimigos().size() ; i++) //ax q assim funciona, por mais q dps a gente tem q mudar pra colecoes
                    if (setor.getInimigos().get(i).isVivo() == true)
                        setor.getInimigos().get(i).dano(1);
                System.out.println("Todos os inimigos perderam vida!");
                break;
            default:
                System.out.println("Nada encontrado"); //esse print talvez de errado, tem q posicionar ele em algum lugar, depende de onde ele vai imprimir
                break;
        }
    }

    protected abstract void texto();

    protected boolean haAcao(Setor set){
        return true;
    }

    protected void imprimirAcoesExtras(Jogador p1){};

    protected boolean acoesExtras(Jogador p1, String op, Tabuleiro tab){
        return false;
    };

    public boolean acaoMovimentar(String acao, Tabuleiro tab){
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
    public boolean turnoP1(Scanner scanner, Tabuleiro tab, Game g, String state, Jogador p1){
        Setor atu = tab.getSetor(this.X, this.Y);
        if(atu.getEhFinal() == true)
            return(true);

        Random aleat = new Random();
        boolean acaoFeita = false;

        //isso aq eh meio feio, talvez um metodo em game chamado setarflags, algo assim
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

        String op = scanner.nextLine().toUpperCase();
        if(g.getMov() == true){
            Tabuleiro.limparTela();
            acaoFeita = this.acaoMovimentar(op, tab);
        }
        if(acaoFeita == false && g.getAtk() == true){ 
            if (op.length() == 2 && op.charAt(0) == 'A'){
                
                if (op.charAt(1) == '1'){
                    acaoFeita = true;
                    Tabuleiro.limparTela(); //TODO: as impressoes as vzs aparecem as vzs nn ver isso dps
                    if (atu.getInimigos().size() == 0 || !atu.getInimigos().get(0).isVivo()){
                        System.out.println("Não há inimigos.");
                    }
                    if (atu.getTipoSetor() == 2){
                        if (aleat.nextInt(2) == 0) {
                            System.out.println("Ataque bem sucedido!");
                            this.atacar(atu.getInimigos().get(0));
                        }
                        else
                            System.out.println("Ataque falhou.");
                    }
                    else {
                        System.out.println("Ataque bem sucedido!");
                        this.atacar(atu.getInimigos().get(0));
                    }
                }
                else if (op.charAt(1) == '2'){
                    acaoFeita = true;
                    Tabuleiro.limparTela();
                    if (atu.getInimigos().size() < 2 || !atu.getInimigos().get(1).isVivo()){
                        System.out.println("Não há inimigos.");
                    }
                    if (atu.getTipoSetor() == 2){
                        if (aleat.nextInt(2) == 0) {
                            System.out.println("Ataque bem sucedido!");
                            this.atacar(atu.getInimigos().get(1));
                        }
                        else
                            System.out.println("Ataque falhou.");
                    }
                    else {
                        System.out.println("Ataque bem sucedido!");
                        this.atacar(atu.getInimigos().get(1));
                    }
                }
                else if (op.charAt(1) == '3'){
                    acaoFeita = true;
                    Tabuleiro.limparTela();
                    if (atu.getInimigos().size() < 3 || !atu.getInimigos().get(2).isVivo()){
                        System.out.println("Não há inimigos.");
                    }
                    if (atu.getTipoSetor() == 2){
                        if (aleat.nextInt(2) == 0) {
                            System.out.println("Ataque bem sucedido!");
                            this.atacar(atu.getInimigos().get(2));
                        }
                        else
                            System.out.println("Ataque falhou.");
                    }
                    else {
                        System.out.println("Ataque bem sucedido!");
                        this.atacar(atu.getInimigos().get(2));
                    }
                }
                else{
                    System.out.println("Inimigo não existente.");
                }
            }
        }
        
        if (op.length() == 1 && op.charAt(0) == 'P'){
            if (g.getProc() == true){
                this.procurar(atu, tab);
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
            if(g.getProc() == false && op.charAt(0) == 'P')
                System.out.println("*************Procurar não permitido.*************");
            else
                System.out.println("*************Acao invalida*************");
        }
        return acaoFeita;
    }

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
}
