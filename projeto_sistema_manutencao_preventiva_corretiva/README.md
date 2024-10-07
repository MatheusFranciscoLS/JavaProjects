# Sistema de Manutenção Preventiva e Corretiva

## Descrição do Projeto
O Sistema de Manutenção Preventiva e Corretiva é uma aplicação desenvolvida em Java com interface gráfica em Swing, destinada ao gerenciamento do ciclo de vida de máquinas e equipamentos industriais. O foco do sistema é controlar manutenções preventivas e corretivas, gerenciar técnicos e falhas, e gerar relatórios de indicadores de desempenho como MTTR e MTBF.

## Objetivos
- Desenvolver uma **interface gráfica (Swing)** para gerenciamento de máquinas, técnicos e manutenções.
- Implementar **funcionalidades CRUD** para máquinas, manutenções, falhas e técnicos.
- **Gerar relatórios** com base nos dados registrados, incluindo indicadores de desempenho.
- **Conectar com API REST** para armazenar e manipular dados em tempo real.
- Realizar **validação e testes** para garantir a robustez do sistema.

## Funcionalidades
- **Cadastro e Gerenciamento de Máquinas**: Inclusão, edição e visualização de especificações técnicas.
- **Registro de Manutenções Preventivas e Corretivas**: Histórico completo por máquina, com informações de peças trocadas e tempo de inatividade.
- **Gerenciamento de Técnicos**: Cadastro e controle de disponibilidade e especialidades.
- **Relatórios e Indicadores de Desempenho**: Geração de relatórios com cálculos automáticos de MTTR (Tempo Médio de Reparo) e MTBF (Tempo Médio Entre Falhas).
- **API REST**: Integração com uma API REST para persistência de dados.

## Tecnologias Utilizadas
- **Java**: Linguagem principal para desenvolvimento do backend e interface gráfica.
- **Swing**: Para desenvolvimento da interface gráfica.
- **JSON-Server**: Utilizado para simulação da API REST e manipulação de dados.
- **JasperReports**: Para geração de relatórios.
- **Git**: Controle de versão e versionamento do código.

## Requisitos
- **Java 8** ou superior instalado.
- **IDE**: IntelliJ IDEA ou Eclipse.
- **JSON-Server**: Para simular a API REST (instruções de instalação abaixo).
- **Git**: Para versionamento do código (opcional).