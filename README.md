# Projeto Calculadora de IMC - Android

## Descrição Geral

Este é um aplicativo Android simples projetado para calcular o Índice de Massa Corporal (IMC) de um usuário. Com base na altura (em metros) e peso (em quilogramas) fornecidos, o aplicativo calcula o IMC, classifica o resultado em categorias padrão da OMS (Abaixo do Peso, Peso Normal, Sobrepeso, Obesidade Grau I, II ou III) e exibe o resultado detalhado (altura, peso e IMC calculado) em uma tela específica para cada classificação.

O fluxo principal do aplicativo é:
1.  Tela inicial (`MainActivity`) com um botão para iniciar o cálculo.
2.  Tela de cálculo (`CalculoIMCActivity`) onde o usuário insere sua altura e peso. Possui botões para calcular, limpar os campos e fechar a tela.
3.  Após o cálculo, o usuário é redirecionado para uma das seis telas de resultado (`AbaixoDoPesoActivity`, `PesoNormalActivity`, `SobrepesoActivity`, `ObesidadeGrau1Activity`, `ObesidadeGrau2Activity`, `ObesidadeGrau3Activity`), dependendo do valor do IMC calculado.
4.  Cada tela de resultado exibe a altura, o peso e o IMC formatado, além de um botão para retornar à tela inicial e uma mensagem positiva para cada classificação do IMC. (`MainActivity`).

## Aluno

*   **Nome:** Pedro Schaurich Maia
*   **Curso:** Ciência da Computação 3° Semestre (Noturno)
*   **Matrícula:** 24026011

## Processo de Desenvolvimento

O desenvolvimento deste aplicativo seguiu as etapas básicas de criação de um app Android nativo utilizando Java e XML no Android Studio.

**Decisões de Design:**

*   **Múltiplas Activities para Resultados:** Foi decidido criar uma `Activity` separada para cada faixa de resultado do IMC. Embora isso aumente o número de arquivos de classe e layout, permite uma customização mais fácil e visualmente distinta para cada categoria de resultado no futuro (por exemplo, adicionando mensagens customizadas para cada grupo ou mudando a cor de fundo).
*   **Navegação:** A navegação entre as telas é gerenciada usando `Intent`. Dados como altura, peso e IMC são passados entre a tela de cálculo e as telas de resultado através de `Extras` e `Bundles`.
*   **Retorno à Tela Inicial:** O botão "Fechar" nas telas de resultado utiliza `Intent.FLAG_ACTIVITY_CLEAR_TOP` para garantir que, ao retornar à `MainActivity`, todas as activities intermediárias (cálculo e resultado) sejam removidas da pilha e fechadas, proporcionando uma experiência de navegação mais limpa.

**Desafios Encontrados:**

*   **Erro de Layout nas Telas de Resultado:** Um desafio inicial significativo foi um bug onde, independentemente do resultado do IMC, o aplicativo sempre exibia a tela correspondente a "Abaixo do Peso". A investigação revelou que a lógica de seleção da `Activity` correta na `CalculoIMCActivity` estava funcionando, mas as Activities de resultado subsequentes (`SobrepesoActivity`, `PesoNormalActivity`, etc.) estavam incorretamente inflando o layout `R.layout.activity_abaixo_do_peso` em seus métodos `onCreate`. A correção envolveu garantir que cada Activity de resultado chamasse `setContentView()` com seu próprio arquivo de layout XML correspondente (e.g., `R.layout.activity_sobrepeso`, `R.layout.activity_peso_normal`).
*   **Validação de Entrada:** Implementar validação robusta para os campos de altura e peso foi necessário para evitar crashes ou cálculos incorretos. Isso incluiu verificar se os campos não estavam vazios e se os valores inseridos eram numéricos e positivos, usando `try-catch` para `NumberFormatException` e exibindo mensagens de erro (`Toast`) para o usuário.
*   **Formatação de Saída:** Garantir que o valor do IMC fosse exibido com um número adequado de casas decimais (duas, neste caso) exigiu o uso de `String.format("%.2f", imc)`.

## Tecnologias Utilizadas

*   **Linguagem:** Java
*   **Plataforma:** Android SDK Nativo
*   **IDE:** Android Studio
*   **Layout:** XML
*   **Componentes Principais:** `AppCompatActivity`, `EditText`, `Button`, `TextView`, `Intent`, `Bundle`, `StartActivity`
