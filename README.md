# backend-qualification

Um crud para gravar endereços através de uma API Rest.

## Iniciando
Leia as instruções a serguir para ter uma cópia do projeto e rodar em sua máquina local.

### Pré-requisitos

O que você precisa instalar para rodar essa aplicação:

- Docker


### Instalando

#### Clone o projeto para uma pasta em sua máquina
```
git clone https://github.com/diegoqueres/backend-qualification.git
```

#### Rode os comandos para criar a imagem do Docker
```
docker build -f Dockerfile -t backend-qualification

docker images
docker run -p 8085:8085 backend-qualification
```

