# Trabalho Paradigmas de Programação

## Tabuleiro
   ### Atributos:
    - Setor setores[][] -> Uma matriz de setores

    ### Métodos:
    - getTipo(int x, int y); -> Retorna o tipo do setor (x, y)
    - p1Entrou(int x, int y); -> Define que p1 entrou no setor (x, y)
    - p2Entrou(int x, int y); -> Define que p2 entrou no setor (x, y)
    - p1Saiu(int x, int y); -> Define que p1 saiu do setor (x, y)
    - p2Saiu(int x, int y); -> Define que p2 saiu do setor (x, y)
    - imprimeTabuleiro(); -> Imprime o tabuleiro

    ### Construtor:
    - Constrói a matriz quadrada de tamanho 5 -> Matriz[5][5]
    - Inicializa cada setor definindo um setor final de forma aleatória
    - Indica o setor de inicio sendo o setor do meio -> Matriz [2][2]

    ### Setor:
    - Possui atributos booleanos que indicam se o setor é final, inicial, está contendo o p1, p2; um vetor de inimigos de tamanho definido aleatóriamente e depois cada objeto inimigo é criado desse vetor; o tipo desse setor (normal, oculto ou privado).