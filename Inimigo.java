import java.util.Random; 

public class Inimigo implements Entidade{

    // Atributos
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

    /****************OUTROS METODOS****************/

    public boolean isVivo() {
        return this.vivo;
    }

    public void atacar(Inimigo oponente){};

    // Ataca o jogador p caso num seja par
    public void causarDano(Jogador p){
        Random random = new Random();
        int num = random.nextInt(6)+1;
        if (num % 2 == 0) 
            p.dano(this.atk);
    }

    //Funcao que causa(possivelmente) danos aos player, eh uma chance de causar dano
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

    public void dano(int dano){
        this.def -= dano;
        if (this.def <= 0){
            this.def = 0;
            this.vivo = false;
        }
    }
}
