/*
Eu pensei assim: jogadorSup -> jogadorSimples -> Entidade e Inimigos -> Entidade
ou seja jogadorsup implementa jogadorsimples que implementa entidade
a maior dificuldade imagino q seja mexer na main e o tabuleiro.
cada "elemento" da matriz, ou seja, cada (x, y) precisa ter uma quantidade de portas, com limitaçoes caso sejam bordas,
definidas em tempo de compilação se nn me engano, uma qntd de inimigos, que pelo q entendi, coleções vao ser tipos abstratos de dados,
como listas e tals, ent daria pra fazer uma lista de inimigos para cada (x, y), o tamanho eh randomico, e ai teria q manipular isso td, 
por exemplo, jogadorsimples ataca, ent teria q pegar o (x,y) do jogadorsimples e retirar a quantidade de atk dele da defesa do inimigo0, o 
primeiro da lista, caso morra a lista deve andar pro proximo, eh meio chato, mas nn ax q seja mt dificil, coloquei as minhas ideias em cada arquivo

parte 2: tava implementando as coisas enquanto via os slides e percebi q confundi abstrata com interface, abstrata tbm nn pode ser declarada na main
e eu tava usando jogSimples como abstrata, mas se nn pode ser declarada na main ent nn faz o menor sentido jogSimples ser abstrata, ela deve ser concreta
contudo, eu nn sei onde eu colocaria a classe abstrata para preencher os requisitos do trabalho, q fala q deve ter interface e abstrata, interface eh a 
Entidade.java, pode ser abstrata, mas ai fica faltando o outro, talvez precise de mais uma classe alem das q eu pensava

parte 2.2: talvez entidade precise ser abstrata, assim ela ficaria com os atributos q inimigo e jogSImples teriam em comum e poderiamos fazer dois metodos
de atacar fazendo uma sobrecarga nele, um ataca o oponente o outro o player, nn preciso explicar em q situaçoes a gente usaria isso

parte 2.3: pensei numa ideia, fazer assim JogadorSup -> Jogador, JogadorSimples -> Jogador, Jogador -> Entidade, Inimigo -> Entidade (se nn entenderam
desenhem num papel pq fica confuso msm), assim entidade seria interface, nn pode ser chamado na main blz, e jogador uma classe abstrata, tbm nn pode
ser chamado, e entidade teria o metodo atacar, comum a todos, jogador teria os metodos movimentar e atacar definidos. Pensando melhor, ainda teria um
problema pois entidade precisa ter tres metodos atacar, um atacar(jogsimples p1), atacar(jogsuporte p2), atacar(inimigo op), pq? pq tem diferença entre um
inimigo e um jogsimples etc, o problema: se entidade eh interface ent jogsimples vai precisar implementar o atacar(jogsimples p1), oq nn faz sentido
ele causar dano a si msm, mas a gente pode simplesmente nn definir  a funcao, tipo atacar(jogsimples p1){}; coisa assim, pois a gente nunca vai chamar
p1.atacar(p1), nn faz sentido isso.

parte 2.4: fiz umas alterações, criei a classe abstrata Jogador, e simples e suporte puxam dela, ela tem td definido, oq condiz com abstrato, inimigo puxa
de entidade diretamente, oq falta(alem de mt coisa) eh imprimir o tabuleiro e definir como fazer os inimigos, alem de colocar o conceito de polimorfismo aq
q eu nn sei bem como encaixar ainda.

parte 2.5: finalizando, coloquei a parte basica por ora no trabalho, a gente precisa definir como vao ser os inimigos e descobrir como imprimir o tabuleiro
da forma q ela mostrou no pdf, eu ax q base ta certa, tipo atk restaurar e tals, so precisa criar a logica na main para tudo isso acontecer

*/

//import java.util.Random;

public class Game {
    public Tabuleiro [][] table;
    public JogadorSimples p1;
    public JogadorSuporte p2;

    public void init() {
        //Random aleat = new Random();
        table = new Tabuleiro[5][5];
        p1 = new JogadorSimples();//ver comentario no jogadorsimples.java
        p2 = new JogadorSuporte(); 
    }


    public void play() {
        /*
        inicializar players
        while(turnos != 25, alcancado == 0, mortos == 0){
            acaop1
            acaop1
            acaop2
            acaop2
            inimigos
        }
        */
    }

    public void end() {

    }

}

