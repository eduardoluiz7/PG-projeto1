## Projeto 1 da disciplina de Processamento Gráfico 2017.2 - CIN UFPE

### 1.16 Parametrização unitária x Comprimento do arco em curvas de Bézier:

#### Especificação:
O sistema desenha a curva numa janela e numa outra desenha o gráfico
da função de parametrização de comprimento do arco aproximado da curva de
Bézier em termos da parametrização usual entre 0 e 1. Para isso o sistema
deve amostrar uma certa quantidade de valores entre 0 e 1 (configurável pelo usuário) 
e calcular a primeira derivada para todos estes valores de t. O sistema
soma todos estes tamanhos de vetores, assumindo então que isto representa o
comprimento da curva inteira. O gráfico que deve aparecer na segunda janela
é a norma acumulada dos vetores em função dos valores de t dividida pelo
comprimento total (isto como ordenada, e o intervalo [0,1] como abscissa).

#### Funcionamento:
O Projeto possui 3 Classes: TelaCurvas, CurvasDeBezier e Grafico. É executado através da classe TelaCurvas.

- O usuário desenha os pontos de controle através do mouse, clica no botão "desenhar curva" e o sistema 
faz o desenho.

- O botão "apagar" limpa o quadro de desenho

- O usuário pode alterar o valor de t no intervalo [0,1] digitando na caixa de texto e teclando ENTER

- O Gráfico gera a norma acumulada de vetores obtidos através da derivada da curva criada 
para os seguintes valores de t: 0.001, 0.05, 0.08, 0.1, 0.2, 0.3, 0.4, 0.5 e 1.
Ele mostra que quando menor o t, maior o valor da norma acumulada.

- Para ver com detalhes se aplica o zoom usando o mouse, também é possível redimensionar a janela do gráfico.

- Para excluir um ponto o usuário clica no botão "exluir ponto" e depois clica no ponto a ser excluido (ainda não implementado)

- Para criar o gráfico a biblioteca JFreeChart foi utilizada.