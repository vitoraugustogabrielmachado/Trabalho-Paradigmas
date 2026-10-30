public class teste {
    public static void main(String[] args) {
        Tabuleiro table = new Tabuleiro();
        JogadorSimples p1 = new JogadorSimples();

        // TESTE P1 E P2 NO MESMO SETOR
        p1.setX(4);
        p1.setY(2);
        JogadorSuporte p2 = new JogadorSuporte();
        p2.setX(4);
        p2.setY(2);
        table.setJogador(p1);
        table.setJogador(p2);
        table.imprimeTabuleiro();

        // TESTE P1 e P2 EM SETORES DIFERENTES
        table.unsetJogador(p1);
        p1.setX(3);
        table.setJogador(p1);
        table.imprimeTabuleiro();

        // TESTE P2 MORTO
        p2.setVivo(false);
        table.imprimeTabuleiro();

        // TESTE P1 MORTO
        p2.setVivo(true);
        p1.setVivo(false);
        table.imprimeTabuleiro();

        // TESTE AMBOS MORTOS
        p2.setVivo(false);
        table.imprimeTabuleiro();
    }
}