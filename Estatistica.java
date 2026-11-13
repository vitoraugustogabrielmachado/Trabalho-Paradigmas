// Classe responsável por imprimir as estatísticas do jogo
public class Estatistica {
    private int killsP1, killsP2, procurasCerta, procurasTotal, qntdCura, qntdDanoRecebidoP1, qntdDanoRecebidoP2, vencedor;
    public Estatistica(){}
    public Estatistica(int killsP1, int killsP2, int procurasCerta, int procurasTotal, int qntdCura, int qntdDanoRecebidoP1, int qntdDanoRecebidoP2, int vencedor){
        this.setKillsP1(killsP1);
        this.setKillsP2(killsP2);
        this.setProcurasCerta(procurasCerta);
        this.setProcurasTotal(procurasTotal);
        this.setQntdCura(qntdCura);
        this.setQntdDanoRecebidoP1(qntdDanoRecebidoP1);
        this.setQntdDanoRecebidoP2(qntdDanoRecebidoP2);
        this.setVencedor(vencedor);
    }
    public void setKillsP1(int killsP1) {
        if(killsP1 >= 0)
            this.killsP1 = killsP1;
    }
    public int getKillsP1() {
        return killsP1;
    }
    public void setKillsP2(int killsP2) {
        if(killsP2 >= 0)
            this.killsP2 = killsP2;
    }
    public int getKillsP2() {
        return killsP2;
    }
    public void setProcurasCerta(int procurasCerta) {
        if(procurasCerta >= 0)
            this.procurasCerta = procurasCerta;
    }
    public int getProcurasCerta() {
        return procurasCerta;
    }
    public void setProcurasTotal(int procurasTotal) {
        if(procurasTotal >= 0)
            this.procurasTotal = procurasTotal;
    }
    public int getProcurasTotal() {
        return procurasTotal;
    }
    public void setQntdCura(int qntdCura) {
        if(qntdCura >= 0)
            this.qntdCura = qntdCura;
    }
    public int getQntdCura() {
        return qntdCura;
    }
    public void setQntdDanoRecebidoP1(int qntdDanoRecebidoP1) {
        if(qntdDanoRecebidoP1 >= 0)
            this.qntdDanoRecebidoP1 = qntdDanoRecebidoP1;
    }
    public int getQntdDanoRecebidoP1() {
        return qntdDanoRecebidoP1;
    }
    public void setQntdDanoRecebidoP2(int qntdDanoRecebidoP2) {
        if(qntdDanoRecebidoP2 >= 0)
            this.qntdDanoRecebidoP2 = qntdDanoRecebidoP2;
    }
    public int getQntdDanoRecebidoP2() {
        return qntdDanoRecebidoP2;
    }
    public void setVencedor(int vencedor) {
        if(vencedor >= 0)
            this.vencedor = vencedor;
    }
    public int getVencedor() {
        return vencedor;
    }

    // Altera as estatísticas de mortes dos jogadores P1 e P2
    public void mudarKills(Jogador p){
        if(p instanceof JogadorSimples){
            p.est.setKillsP1(p.est.getKillsP1() + 1);
        }else{
            p.est.setKillsP2(p.est.getKillsP2() + 1);
        }
    }

    public void mudarCura(Jogador p){
        if(p instanceof JogadorSuporte){
            p.est.setQntdCura(p.est.getQntdCura() + 2);
        }
    }

    public void mudarDanoRecebido(Jogador p, Inimigo o){
        if(p instanceof JogadorSimples){
            p.est.setQntdDanoRecebidoP1(p.est.getQntdDanoRecebidoP1() + o.getAtk());
        }else{
            p.est.setQntdDanoRecebidoP2(p.est.getQntdDanoRecebidoP2() + o.getAtk());
        }
    }

    public void imprimirTelaFinal(Game g){
        if(this.vencedor > 0){
            System.out.printf("Parabéns! O jogador P%d erradicou a ameaça com sucesso!\n", this.vencedor);
        }else if(g.getCiclos() >= 125){
            System.out.println("O limite de ciclos foi atingido...");
        }else if(g.getP1().isVivo() == false && g.getP2().isVivo() == false){
            System.out.println("Vocês foram destruídos pelos vírus...");
        }
    }

    public void imprimirP1(){
        System.out.println();
        System.out.println("********************ESTATISTICAS DE FIM DE JOGO********************");
        System.out.println("Quantidade de inimigos mortos por P1: " + this.killsP1);
        System.out.println("Quantidade de dano recebido por P1: " + this.qntdDanoRecebidoP1);
        
    }

    public void imprimirP2(Jogador p){
        System.out.println("Quantidade de inimigos mortos por P2: " + this.killsP2);
        System.out.println("Quantidade de dano recebido por P2: " + this.qntdDanoRecebidoP2);
        System.out.println("Quantidade de cura realizada por P2: " + this.qntdCura);
        System.out.printf("Quantidade de procuras corretas/procuras totais no jogo: " + (this.procurasCerta + p.est.getProcurasCerta()) + "/" + (this.procurasTotal + p.est.getProcurasTotal()) + "\n");
        System.out.println("*******************************************************************");
        
    }

}
