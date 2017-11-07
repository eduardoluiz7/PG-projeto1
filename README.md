## Projeto 1 da disciplina de Processamento Gr�fico 2017.2 - CIN UFPE

### 1.16 Parametriza��o unit�ria x Comprimento do arco em curvas de B�zier:

#### Especifica��o:
O sistema desenha a curva numa janela e numa outra desenha o gr�fico
da fun��o de parametriza��o de comprimento do arco aproximado da curva de
B�zier em termos da parametriza��o usual entre 0 e 1. Para isso o sistema
deve amostrar uma certa quantidade de valores entre 0 e 1 (configur�vel pelo usu�rio) 
e calcular a primeira derivada para todos estes valores de t. O sistema
soma todos estes tamanhos de vetores, assumindo ent�o que isto representa o
comprimento da curva inteira. O gr�fico que deve aparecer na segunda janela
� a norma acumulada dos vetores em fun��o dos valores de t dividida pelo
comprimento total (isto como ordenada, e o intervalo [0,1] como abscissa).

#### Funcionamento:
O Projeto possui 3 Classes: TelaCurvas, CurvasDeBezier e Grafico. � executado atrav�s da classe TelaCurvas.

- O usu�rio desenha os pontos de controle atrav�s do mouse, clica no bot�o "desenhar curva" e o sistema 
faz o desenho.

- O bot�o "apagar" limpa o quadro de desenho

- O usu�rio pode alterar o valor de t no intervalo [0,1] digitando na caixa de texto e teclando ENTER

- O Gr�fico gera a norma acumulada de vetores obtidos atrav�s da derivada da curva criada 
para os seguintes valores de t: 0.001, 0.05, 0.08, 0.1, 0.2, 0.3, 0.4, 0.5 e 1.
Ele mostra que quando menor o t, maior o valor da norma acumulada.

- Para ver com detalhes se aplica o zoom usando o mouse, tamb�m � poss�vel redimensionar a janela do gr�fico.

- Para excluir um ponto o usu�rio clica no bot�o "exluir ponto" e depois clica no ponto a ser excluido (ainda n�o implementado)

- Para criar o gr�fico a biblioteca JFreeChart foi utilizada.