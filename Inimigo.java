import java.util.Random; 

public class Inimigo implements Entidade{

    // ATRIBUTOS
    private int atk, def;
    private boolean vivo;

    /****************CONSTRUTORES****************/
    public Inimigo(){
        Random random = new Random();
        int num = random.nextInt(3)+1; //Dano e defesa do inimigo eh aleatoria
        this.atk = num;
        this.def = num;
        this.vivo = true;
    };

    public Inimigo(int atk, int def, boolean vivo){
        this.setAtk(atk);
        this.setDef(def);
        this.setVivo(vivo);
    }

    /****************METODOS GET E SET****************/
    public void setAtk(int atk){
        if(atk > 0 && atk < 4)
            this.atk = atk;
    }

    public int getAtk() {
        return this.atk;
    }

    public void setDef(int def) {
        if (def <= 0)
            this.def = 0;
        else if(def < 4)
            this.def = def;
    }

    public int getDef() {
        return this.def;
    }

    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    
    public boolean getVivo() {
        return this.vivo;
    }

    public boolean isVivo() {
        return this.vivo;
    }

    /****************OUTROS METODOS****************/

    public void causarDano(Inimigo oponente){};

    /*
    * Ataca o jogador p caso
    * o número sorteado não seja par.
    */
    public void causarDano(Jogador p){
        Random random = new Random();
        int num = random.nextInt(6)+1;
        if (num % 2 == 0){
            p.est.mudarDanoRecebido(p, this);
            p.dano(this.atk);
            if(p instanceof JogadorSimples)
                System.out.printf("Inimigo causou " + this.atk + " no Player1");
            else
                System.out.printf("Inimigo causou " + this.atk + " no Player2");
        }else{
            System.out.printf("Inimigo errou ataque");
        }
    }

    /*
    * Ataca os jogadores P1 e P2 usando o método causarDano().
    */
    public void ataqueInimigo(Tabuleiro tab, Jogador p1, Jogador p2){
        if(p1 instanceof JogadorSimples && p2 instanceof JogadorSuporte){ //Como p1 e p2 estao setados como Jogador, essa linha garante que eles representam as coisas certas
            if (p1.getX() != p2.getX() || p1.getY() != p2.getY()){ //Se p1 e p2 estao em setores diferentes
                Setor set1 = tab.getSetor(p1.getX(), p1.getY());
                Setor set2 = tab.getSetor(p2.getX(), p2.getY());

                //Os dois fors sao para, respectivamente, atacar p1 e atacar p2 em setores distintos
                for (int i = 0; i < set1.getInimigos().size(); i++){
                    Inimigo op = set1.getInimigos().get(i);

                    if (op.isVivo() && p1.isVivo()) op.causarDano(p1);
                }

                for (int i = 0; i < set2.getInimigos().size() ; i++){
                    Inimigo op = set2.getInimigos().get(i);

                    if (op.isVivo() && p2.isVivo()) op.causarDano(p2);
                }
            }
            else {//Esse eh o caso de p1 e p2 estarem no msm setor
                Setor set = tab.getSetor(p1.getX(), p1.getY());

                for (int i = 0; i < set.getInimigos().size(); i++){
                    Inimigo op = set.getInimigos().get(i);
                    if (op.isVivo() && p1.isVivo()) op.causarDano(p1);
                    if (op.isVivo() && p2.isVivo()) op.causarDano(p2);
                }
            }
        }
    }

    /*
    * Ajusta os atributos def e vivo da classe
    * após ela receber dano por um jogador.
    */
    public void dano(int dano){
        this.def -= dano;
        if (this.def <= 0){
            this.def = 0;
            this.vivo = false;
        }
    }
}
