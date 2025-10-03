# Labirinto LIFO

## Compilação

No terminal:
```
javac *.java
```

## Execução

```
java LabirintoLifo --map=./mapas/map1.txt [--seed=3] [--player="Nome"]
```
- `--map`: caminho do arquivo de mapa (obrigatório)
- `--seed`: seed opcional para valores de tesouros
- `--player`: nome do jogador (opcional)

## Comandos no jogo
- `W`: mover para cima
- `S`: mover para baixo
- `A`: mover para esquerda
- `D`: mover para direita
- `Q`: sair do jogo

## Símbolos do mapa
- `#`: parede
- `.`: piso
- `S`: ponto de início
- `E`: saída
- `a-z`: chave
- `A-Z`: porta correspondente
- `$`: tesouro (10-50 pontos)
- `T`: armadilha (-20 pontos)

## Pontuação
- Cada passo: -1 ponto
- Abrir porta: +15 pontos
- Tesouro: +10 a +50 pontos
- Armadilha: -20 pontos
- Concluir (`E`): +100 pontos
- Bônus por chave não usada: +5 pontos por chave

## Exemplo de mapa (`map1.txt`)
```
;Mapa exemplo
7 12 5
############
#S..a...A..#
#..##..$...#
#..T...##..#
#...b..B...#
#..e....E..#
############
```

## Ranking
- Pontuação final é salva em `ranking.csv`
- Top 10 podem ser visualizados usando a ordenação interna do sistema
