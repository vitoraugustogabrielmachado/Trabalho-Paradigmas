import java.util.Random;

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
        op.setDef(op.getDef() - this.atk);
        if (op.getDef() <= 0)
                op.setVivo(false);
    }

/*
    public void movimentar(Tabuleiro tab, int direcao){
        if(tab.temPorta(linha, coluna, direcao)){
            switch (direcao) {
                case Tabuleiro.NORTE:
                    linha--;
                    break;

                case Tabuleiro.SUL:
                    linha++;
                    break;

                case Tabuleiro.LESTE:
                    coluna++;
                    break;

                case Tabuleiro.OESTE:
                    coluna--;
                    break;
            }
        }
    }
*/
    public void atacar(Jogador p){};

    // Recebe o setor atual do jogador
    public void procurar(Setor setor){
        Random random = new Random();
        int num = random.nextInt(6)+1;
        switch (num) {
            case 4:
                this.def++;
                break;
            case 5:
                this.def += 2;
                break;
            case 6:
                for (int i = 0; i < 3; i++)
                        if (setor.inimigos[i].isVivo() == true)
                                setor.inimigos[i].setDef(setor.inimigos[i].getDef() - 1);
                break;
            default:
                System.out.println("Nada encontrado");
                break;
        }
    }
}
