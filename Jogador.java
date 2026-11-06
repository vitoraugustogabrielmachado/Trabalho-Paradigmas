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

    public void atacar(Jogador p){};

    // Recebe o setor atual do jogador **Acho que o ideal seria receber apenas os inimigos
    public void procurar(Setor setor){
        Random random = new Random();
        int num = random.nextInt(6)+1;
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

    protected void imprimirAcoesExtras(JogadorSimples p1){};

    protected boolean acoesExtras(JogadorSimples p1, String op, Tabuleiro tab){
        return false;
    };

    public void acaoMovimentar(Scanner scanner, Tabuleiro tab){
        //Setor set = tab.getSetor(this.X, this.Y);

        while (true){
            System.out.println("Movimento: [W] Cima | [A] Esquerda | [S] Baixo | [D] Direita | [F] Ficar");

            switch (scanner.nextLine().toUpperCase()){
                case "W":
                    this.movimentar(tab, "UP");
                    return;
                case "A":
                    this.movimentar(tab, "LEFT");
                    return;
                case "S":
                    this.movimentar(tab, "DOWN");
                    return;
                case "D":
                    this.movimentar(tab, "RIGHT");
                    return;
                case "F":
                    return;
                default:
                    System.out.println("Opção não existente\n");
                    continue;
            }
        }
    }

    public boolean turno(Scanner scanner, Tabuleiro tab, JogadorSimples p1){
        Random aleat = new Random();
        int acoes = 2;

        Setor set = tab.getSetor(this.X, this.Y);


        while (acoes > 0){
            boolean ATK = false, PROC = false, MOV = false;
            MOV = set.qtdInimigosVivos() == 0;

            if(MOV){
                this.texto();
                this.acaoMovimentar(scanner, tab);
                acoes--;
		tab.limparTela();
                tab.imprimeTabuleiro();
                if(acoes == 0)
                    break;

                set = tab.getSetor(this.X, this.Y);
                if (set.getEhFinal())
                    return true;
            }
            set = tab.getSetor(this.X, this.Y);
            ATK = set.qtdInimigosVivos() > 0;
            PROC = set.getTipoSetor() != 3;

            if (!this.haAcao(set))
                break;

            this.texto();

            if (ATK){
                System.out.print("Atacar: ");

                for(int i = 1; i <= set.getInimigos().size() ; i++)
                    if (set.getInimigos().get(i-1).isVivo()) System.out.printf("[A" + i + "] Atacar Inimigo %d | ", i);
                
                System.out.println();
            }


            if (PROC)
                System.out.println("Procurar: [P]");

            this.imprimirAcoesExtras(p1);

            String op = scanner.nextLine().toUpperCase();

            if (op.length() == 2 && op.charAt(0) == 'A'){
		tab.limparTela();
                if (op.charAt(1) == '1'){
                    if (set.getInimigos().size() == 0 || !set.getInimigos().get(0).isVivo()){
                        System.out.println("Não há inimigos.");
                        continue;
                    }
                    if (set.getTipoSetor() == 2){
                        if (aleat.nextInt(2) == 0) {
                            System.out.println("Ataque bem sucedido!");
                            this.atacar(set.getInimigos().get(0));
                        }
                        else
                            System.out.println("Ataque falhou.");
                    }
                    else {
                        System.out.println("Ataque bem sucedido!");
                        this.atacar(set.getInimigos().get(0));
                    }
                }
                else if (op.charAt(1) == '2'){
		    tab.limparTela();
                    if (set.getInimigos().size() < 2 || !set.getInimigos().get(1).isVivo()){
                        System.out.println("Não há inimigos.");
                        continue;
                    }
                    if (set.getTipoSetor() == 2){
                        if (aleat.nextInt(2) == 0) {
                            System.out.println("Ataque bem sucedido!");
                            this.atacar(set.getInimigos().get(1));
                        }
                        else
                            System.out.println("Ataque falhou.");
                    }
                    else {
                        System.out.println("Ataque bem sucedido!");
                        this.atacar(set.getInimigos().get(1));
                    }
                }
                else if (op.charAt(1) == '3'){
		    tab.limparTela();	
                    if (set.getInimigos().size() < 3 || !set.getInimigos().get(2).isVivo()){
                        System.out.println("Não há inimigos.");
                        continue;
                    }
                    if (set.getTipoSetor() == 2){
                        if (aleat.nextInt(2) == 0) {
                            System.out.println("Ataque bem sucedido!");
                            this.atacar(set.getInimigos().get(2));
                        }
                        else
                            System.out.println("Ataque falhou.");
                    }
                    else {
                        System.out.println("Ataque bem sucedido!");
                        this.atacar(set.getInimigos().get(2));
                    }
                }
                else{
                    System.out.println("Inimigo não existente.");
                    continue;
                }
            }
            else if (op.length() == 1 && op.charAt(0) == 'P'){
		tab.limparTela();
                if (!PROC){
                    System.out.println("Procurar não permitido.");
                    continue;
                }else
                    this.procurar(set);                
            }
            else if(!this.acoesExtras(p1, op, tab)){
		tab.limparTela();
                System.out.println("Ação não existente.");
                continue;
            }
            tab.imprimeTabuleiro();

            acoes--;
        }

        return false;
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
