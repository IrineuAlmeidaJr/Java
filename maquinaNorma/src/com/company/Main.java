package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {

    /*
        EDUARDO HENRIQUE SHIKAMA DIAS - RA: 10.17.30.322
        IRINEU DE ALMEIDA JUNIOR - RA: 10.20.22.798
     */

    public static Registrador[] leituraReg(String titulo) {
        System.out.println("\n--------------------------------------\n" +
                "\t* * * " + titulo + " * * *\n");
        Scanner sc = new Scanner(System.in);
        System.out.printf("Informe o número de registradores: ");
        int numReg = sc.nextInt();

        Registrador reg[] = new Registrador[numReg];
        for(int i = 0; i < numReg; i++) {
            System.out.printf("Informe o nome do %dº registradores: ", i+1);
            reg[i] = new Registrador(sc.next(), 0, 0);
        }

        System.out.printf("Deseja inserir valores no registradores ? <S/N> ");
        if(sc.next().equalsIgnoreCase("S")) {
            for(int i = 0; i < reg.length; i++) {
                System.out.printf("\n\t\t REGISTRADOR %s " +
                                "\n\t** Informe Sinal: (0 - POSITIVO) (1 - NEGATIVO) -> ",
                        reg[i].nome);
                reg[i].setSinal(sc.nextInt());
                System.out.printf("\t** Informe o Valor: ");
                reg[i].setValor(sc.nextInt());
            }
        }
        return reg;
    }

    public static Registrador[] leituraReg(String titulo, int numReg, int qtdeLeitura) {
        int num;

        System.out.println("\n--------------------------------------\n" +
                "\t* * * " + titulo + " * * *\n");
        Scanner sc = new Scanner(System.in);

        Registrador reg[] = new Registrador[numReg];
        for(int i = 0; i < numReg; i++) {
            reg[i] = new Registrador(String.valueOf((char) (65+i)), 0, 0);
        }

        System.out.printf("Deseja inserir valores no registradores ? <S/N> ");
        if(sc.next().equalsIgnoreCase("S")) {
            for(int i = 0; i < qtdeLeitura; i++) {
                System.out.printf("\n\t\t REGISTRADOR %s " +
                                "\n\t** Informe Sinal: (0 - POSITIVO) (1 - NEGATIVO) -> ",
                        reg[i].getNome());
                reg[i].setSinal(sc.nextInt());
                System.out.printf("\t** Informe o Valor: ");
                reg[i].setValor(sc.nextInt());
            }
        }

        return reg;
    }

    public static Registrador[] leituraRegSemSinal(String titulo, int numReg, int qtdeLeitura) {
        int num;

        System.out.println("\n--------------------------------------\n" +
                "\t* * * " + titulo + " * * *\n");
        Scanner sc = new Scanner(System.in);

        Registrador reg[] = new Registrador[numReg];
        for(int i = 0; i < numReg; i++) {
            reg[i] = new Registrador(String.valueOf((char) (65+i)), 0, 0);
        }

        System.out.printf("Deseja inserir valores no registradores ? <S/N> ");
        if(sc.next().equalsIgnoreCase("S")) {
            for(int i = 0; i < qtdeLeitura; i++) {
                System.out.printf("\n\t\t REGISTRADOR %s", reg[i].getNome());
                reg[i].setSinal(0);
                System.out.printf("\n\t** Informe o Valor: ");
                reg[i].setValor(sc.nextInt());
            }
        }

        return reg;
    }

    public static void leituraIns(List<String> listaInstrucoes) {
        String comando;
        String[] instrucaoSeparada;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--------------------------------------\n" +
                "\t* * * LEITURA INSTRUÇÕES * * *");
        System.out.println("\nInforme os comando:");
        do {
            comando = sc.nextLine();
//            System.out.println(comando);
            instrucaoSeparada = comando.split(":\\s");
            listaInstrucoes.add(comando);
//            System.out.println(instrucaoSeparada[0] + " " + instrucaoSeparada[1]);

        } while (!instrucaoSeparada[1].equalsIgnoreCase("HALT"));
    }

    public static int posRegistro(Registrador[] registrador, String letra) {
        int pos = 0;
        while(pos < registrador.length && !registrador[pos].getNome().equals(letra)) {
            pos++;
        }
        if(pos < registrador.length) {
            return pos;
        } else  {
            return -1;
        }
    }

    public static void processarCodigo(Registrador[] reg, List<String> listaInstrucoes) throws InterruptedException {
        int i = 0, j=0;
        String comando;
        String[] instrucaoSeparada;
        System.out.println("\n--------------------------------------\n" +
                "\t* * * EXECUTANDO * * *");
        while (i < listaInstrucoes.size()) {
            comando = listaInstrucoes.get(i);
            instrucaoSeparada = comando.split(":\\s");

            // Não consegui separar com um rejax só ai, coloquei espaço
            // para dividir a string.
            comando = instrucaoSeparada[1];
            comando = comando.replaceAll(",", " ");
            comando = comando.replaceAll("\\(", " ");
            comando = comando.replaceAll("\\)", " ");
            instrucaoSeparada = comando.split("\\s");

            int pos;
            String op = instrucaoSeparada[0];
            switch(op) {
                case "JMP0":
                    // Tem fazer do B ou só tem do A ??? PERGUNTAR
                    pos =  posRegistro(reg, instrucaoSeparada[1]);
                    // Abaixo valida o retorna da posição para ver se tem o registrador
                    if(pos != -1) {
                        System.out.printf("\n%d: Fazendo %s,    Registrador %s -> %c%d", j++,
                                listaInstrucoes.get(i), reg[pos].getNome(),
                                reg[pos].getSinal() == 0 ? '+' : '-',reg[pos].getValor());
                        if (reg[pos].getValor() == 0) {
                            i = Integer.parseInt(instrucaoSeparada[2]);
                        } else {
                            i++;
                        }
                    } else {
                        System.out.println("\n# ERRO # registrador não encontrado");
                        i = listaInstrucoes.size();
                    }
                    break;
                case "GOTO":
                    System.out.printf("\n%d: Fazendo %s", j++, listaInstrucoes.get(i));
                    i = Integer.parseInt(instrucaoSeparada[1]);
                    break;
                case "DEC":
                    pos =  posRegistro(reg, instrucaoSeparada[1]);
                    if(pos != -1) {
                        // --> Decrementa
                        if(reg[pos].getSinal() == 0) { // Positivo
                            if(reg[pos].getValor() == 0) {
                                reg[pos].setSinal(1);
                                reg[pos].setValor(1);
                            } else {
                                reg[pos].setValor(reg[pos].getValor() - 1);
                            }
                        } else { // Negativo
                            reg[pos].setValor(reg[pos].getValor() + 1);
                        }
                        // --------------
                        System.out.printf("\n%d: Fazendo %s,    Registrador %s -> %c%d", j++,
                                listaInstrucoes.get(i), reg[pos].getNome(),
                                reg[pos].getSinal() == 0 ? '+' : '-',reg[pos].getValor());
                        i++;
                    } else {
                        System.out.println("\n# ERRO # registrador não encontrado");
                        i = listaInstrucoes.size();
                    }
                    break;
                case "INC":
                    pos =  posRegistro(reg, instrucaoSeparada[1]);
                    if(pos != -1) {
                        // --> Incrementa
                        if(reg[pos].getSinal() == 0) {
                            reg[pos].setValor(reg[pos].getValor() + 1);
                        } else {
                            reg[pos].setValor(reg[pos].getValor() - 1);

                            if(reg[pos].getValor() == 0) {
                                reg[pos].setSinal(0);
                            }
                        }
                        System.out.printf("\n%d: Fazendo %s,    Registrador %s -> %c%d", j++,
                                listaInstrucoes.get(i), reg[pos].getNome(),
                                reg[pos].getSinal() == 0 ? '+' : '-',reg[pos].getValor());
                        i++;
                    } else {
                        System.out.println("\n# ERRO # registrador não encontrado");
                        i = listaInstrucoes.size();
                    }
                    break;
                case "teste_mod":
                    pos =  posRegistro(reg, instrucaoSeparada[1]);
                    int pos2 = posRegistro(reg, instrucaoSeparada[2]);
                    // Abaixo valida o retorna da posição para ver se tem o registrador
                    if(pos != -1 || pos2 != -1) {
                        System.out.printf("\n%d: Fazendo %s,    Registrador %s -> %c%d  " +
                                        "Registrador %s -> %c%d", j++,
                                listaInstrucoes.get(i), reg[pos].getNome(),
                                reg[pos].getSinal() == 0 ? '+' : '-',reg[pos].getValor(),
                                reg[pos2].getNome(),
                                reg[pos2].getSinal() == 0 ? '+' : '-',reg[pos2].getValor());

                        int regD = posRegistro(reg, "D");
                        if(reg[pos].getValor() % reg[pos2].getValor() == 0) {
                            reg[regD].setValor(0);
                        } else {
                            reg[regD].setValor(1);
                        }
                    } else {
                        System.out.println("\n# ERRO # registrador não encontrado");
                        i = listaInstrucoes.size();
                    }
                    break;
                case "HALT":
                    System.out.printf("\n%d: Fazendo %s ", j++, listaInstrucoes.get(i));
                    i++;
                    break;
                default:
                    System.out.println("\n# ERRO # instrução '"+ op +"' não existe");
                    i = listaInstrucoes.size();
            }
            Thread.sleep(10);
        }
    }

    public static void exibirValorRegistrador(Registrador[] registrador, String titulo) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n-----------------" + titulo  + "------------------\n" +
                "\t* * * REGISTRADORES * * *");
        for(int i=0; i<registrador.length; i++) {
            System.out.printf("\t%s - %c%d\n", registrador[i].getNome(),
                    registrador[i].getSinal() == 0 ? '+' : '-', registrador[i].getValor());
        }
        System.out.printf("\t- RESULTADO => %c%d",  registrador[0].getSinal() == 0 ? '+' : '-',
                registrador[0].getValor());
        sc.nextLine();
    }

    public static void exibirValorRegistradorMenor(Registrador[] registrador, String titulo) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n-----------------" + titulo  + "------------------\n" +
                "\t* * * REGISTRADORES * * *");
        for(int i=0; i<registrador.length; i++) {
            System.out.printf("\t%s - %c%d\n", registrador[i].getNome(),
                    registrador[i].getSinal() == 0 ? '+' : '-', registrador[i].getValor());
        }
        if(registrador[3].getValor() == 1) {
            System.out.printf("\n\t- RESULTADO => A e B SÃO IGUAIS");
        } else {
            System.out.printf("\n\t- RESULTADO => %s MENOR",
                    registrador[2].getValor() == 1 ? registrador[0].getNome() :
                            registrador[1].getNome());
        }

        sc.nextLine();
    }

    public static byte operacoes() {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n--------------------------------------\n" +
                "\t* * * OPERAÇÕES * * *\n" +
                "[1] - Leitura de Instruções\n" +
                "[2] - Soma (Precisa 2 registradores) *Sem preservar conteúdo\n" +
                "[3] - Soma (Precisa 3 registradores) *Preservar conteúdo\n" +
                "[4] - Multiplicação (Precisa 4 registradores)\n" +
                "[5] - Compara menor A < B ou A <= B (Precisa 3 registradores)\n" +
                "[6] - Primo (Precisa 3 registradores)\n" +
                "[7] - Fatorial (Precisa 5 registradores)\n" +
                "[8] - Potencia (Precisa 5 registradores)\n" +
                "\n[0] - SAIR");
        return sc.nextByte();
    }

    // SOMA sem preservar conteúdo
    public static void somaNaoPreserva(Registrador[] reg, List<String> listaInstrucoes) {
        if((reg[0].getSinal() == 0 && reg[1].getSinal() == 0) ||
                (reg[0].getSinal() == 1 && reg[1].getSinal() == 0))  {
            listaInstrucoes.add("0: JMP0(B,4)");
            listaInstrucoes.add("1: INC(A)");
            listaInstrucoes.add("2: DEC(B)");
            listaInstrucoes.add("3: GOTO(0)");
            listaInstrucoes.add("4: HALT");
        } else if((reg[0].getSinal() == 1 && reg[1].getSinal() == 1) ||
                (reg[0].getSinal() == 0 && reg[1].getSinal() == 1)) {
            listaInstrucoes.add("0: JMP0(B,4)");
            listaInstrucoes.add("1: DEC(A)");
            listaInstrucoes.add("0: INC(B)");
            listaInstrucoes.add("3: GOTO(0)");
            listaInstrucoes.add("4: HALT");
        }
    }

    // SOMA preservar conteúdo
    public static void somaPreserva(Registrador[] reg, List<String> listaInstrucoes) {
        if((reg[0].getSinal() == 0 && reg[1].getSinal() == 0) ||
                (reg[0].getSinal() == 1 && reg[1].getSinal() == 0)) {
            listaInstrucoes.add("0: JMP0(B,5)");
            listaInstrucoes.add("1: INC(A)");
            listaInstrucoes.add("2: DEC(B)");
            listaInstrucoes.add("3: INC(C)");
            listaInstrucoes.add("4: GOTO(0)");
            listaInstrucoes.add("5: JMP0(C,9)");
            listaInstrucoes.add("6: INC(B)");
            listaInstrucoes.add("7: DEC(C)");
            listaInstrucoes.add("8: GOTO(5)");
            listaInstrucoes.add("9: HALT");
        } else if((reg[0].getSinal() == 1 && reg[1].getSinal() == 1) ||
                (reg[0].getSinal() == 0 && reg[1].getSinal() == 1)) {
            listaInstrucoes.add("0: JMP0(B,5)");
            listaInstrucoes.add("1: DEC(A)");
            listaInstrucoes.add("2: INC(B)");
            listaInstrucoes.add("3: INC(C)");
            listaInstrucoes.add("4: GOTO(0)");
            listaInstrucoes.add("5: JMP0(C,9)");
            listaInstrucoes.add("6: DEC(B)");
            listaInstrucoes.add("7: DEC(C)");
            listaInstrucoes.add("8: GOTO(5)");
            listaInstrucoes.add("9: HALT");
        }

    }

    public static void multiplicacao(List<String> listaInstrucoes) {
        listaInstrucoes.add("0: JMP0(A,4)");
        listaInstrucoes.add("1: INC(C)");
        listaInstrucoes.add("2: DEC(A)");
        listaInstrucoes.add("3: GOTO(0)");
        listaInstrucoes.add("4: JMP0(C,16)");
        // A:= A + B usando D
        listaInstrucoes.add("5: JMP0(B,10)");
        listaInstrucoes.add("6: INC(A)");
        listaInstrucoes.add("7: DEC(B)");
        listaInstrucoes.add("8: INC(D)");
        listaInstrucoes.add("9: GOTO(5)");
        listaInstrucoes.add("10: JMP0(D,14)");
        listaInstrucoes.add("11: INC(B)");
        listaInstrucoes.add("12: DEC(D)");
        listaInstrucoes.add("13: GOTO(10)");
        // Termina Soma
        listaInstrucoes.add("14: DEC(C)");
        listaInstrucoes.add("15: GOTO(4)");
        listaInstrucoes.add("16: HALT");
    }

    public static void verificaMenor(List<String> listaInstrucoes) {
        listaInstrucoes.add("0: JMP0(B,7)");
        listaInstrucoes.add("1: JMP0(A,5)");
        listaInstrucoes.add("2: DEC(A)");
        listaInstrucoes.add("3: DEC(B)");
        listaInstrucoes.add("4: GOTO(0)");
        listaInstrucoes.add("5: INC(C)");
        listaInstrucoes.add("6: GOTO(10)");
        // A chegou em zero primeiro que o B, então A<B, logo, VERDADEIRO .... C := C + 1
        listaInstrucoes.add("7: JMP0(A,9)");
        listaInstrucoes.add("8: GOTO(10)");
        // B chegou em zero primeiro que o A, e o A não é zero, então A>B, logo, FALSO ...... C = 0
        listaInstrucoes.add("9: INC(D)");
        listaInstrucoes.add("10: HALT");
        // B chegou em zero primeiro que o A, e o A é zero, então A==B, logo, VERDADEIRO ...... C := C + 1
    }

    public static void verificaPrimo(List<String> listaInstrucoes) {
        listaInstrucoes.add("0: JMP0(A,100)"); // Ver para onde pula para o final para o INC(C)
        // Tme incremetar algo `E` por exemplo
        // C := A
        listaInstrucoes.add("1: JMP0(A,5)");
        listaInstrucoes.add("2: INC(C)");
        listaInstrucoes.add("3: DEC(A)");
        listaInstrucoes.add("4: GOTO(1)");
        // C := -1
        listaInstrucoes.add("5: DEC(C)");
        // C = 0
        listaInstrucoes.add("6: JMP0(C,12)"); // Se não for continua se for pula
        listaInstrucoes.add("7: teste_mod(A,C)");
        listaInstrucoes.add("8: HALT");


//        listaInstrucoes.add("0: JMP0(A,4)");
//        listaInstrucoes.add("1: INC(A)");
//        listaInstrucoes.add("2: DEC(B)");
//        listaInstrucoes.add("3: GOTO(0)");
//        listaInstrucoes.add("4: HALT");
//
//        listaInstrucoes.add("1: INC(C)");
//        listaInstrucoes.add("2: GOTO(10)"); // Ver onde é HALT

    }

    public static void fatorial(List<String> listaInstrucoes) {
        // Faz B recebe A - 1
        listaInstrucoes.add("0: JMP0(A,5)");
        listaInstrucoes.add("1: INC(B)");
        listaInstrucoes.add("2: INC(E)");
        listaInstrucoes.add("3: DEC(A)");
        listaInstrucoes.add("4: GOTO(0)");
        listaInstrucoes.add("5: DEC(B)");
        listaInstrucoes.add("6: JMP0(E,10)");
        listaInstrucoes.add("7: INC(A)");
        listaInstrucoes.add("8: DEC(E)");
        listaInstrucoes.add("9: GOTO(6)");
//        // Multiplicar
        listaInstrucoes.add("10: JMP0(A,14)");
        listaInstrucoes.add("11: INC(C)");
        listaInstrucoes.add("12: DEC(A)");
        listaInstrucoes.add("13: GOTO(10)");
        listaInstrucoes.add("14: JMP0(C,26)");
        // A:= A + B usando D
        listaInstrucoes.add("15: JMP0(B,20)");
        listaInstrucoes.add("16: INC(A)");
        listaInstrucoes.add("17: DEC(B)");
        listaInstrucoes.add("18: INC(D)");
        listaInstrucoes.add("19: GOTO(15)");
        listaInstrucoes.add("20: JMP0(D,24)");
        listaInstrucoes.add("21: INC(B)");
        listaInstrucoes.add("22: DEC(D)");
        listaInstrucoes.add("23: GOTO(20)");
        // Termina Soma
        listaInstrucoes.add("24: DEC(C)");
        listaInstrucoes.add("25: GOTO(14)");
        // Decrementa B para multiplicar novamente,
        // se ele chegar a 0 para de multiplicar.
        listaInstrucoes.add("26: DEC(B)");
        listaInstrucoes.add("27: JMP0(B,29)");
        listaInstrucoes.add("28: GOTO(10)");
        listaInstrucoes.add("29: HALT");
    }

    public static void potencia(List<String> listaInstrucoes) {
//        // Faz B recebe A - 1
//        listaInstrucoes.add("0: JMP0(A,5)");
//        listaInstrucoes.add("1: INC(F)");
//        listaInstrucoes.add("2: INC(E)");
//        listaInstrucoes.add("3: DEC(A)");
//        listaInstrucoes.add("4: GOTO(0)");
//        listaInstrucoes.add("5: JMP0(E,9)");
//        listaInstrucoes.add("6: INC(A)");
//        listaInstrucoes.add("7: DEC(E)");
//        listaInstrucoes.add("8: GOTO(5)");
//        listaInstrucoes.add("9: DEC(B)");
//        // Multiplicar
//        listaInstrucoes.add("10: JMP0(A,14)");
//        listaInstrucoes.add("11: INC(C)");
//        listaInstrucoes.add("12: DEC(A)");
//        listaInstrucoes.add("13: GOTO(10)");
//        listaInstrucoes.add("14: JMP0(C,26)");
//        // A:= A + B usando D
//        listaInstrucoes.add("15: JMP0(F,20)");
//        listaInstrucoes.add("16: INC(A)");
//        listaInstrucoes.add("17: DEC(F)");
//        listaInstrucoes.add("18: INC(D)");
//        listaInstrucoes.add("19: GOTO(15)");
//        listaInstrucoes.add("20: JMP0(D,24)");
//        listaInstrucoes.add("21: INC(F)");
//        listaInstrucoes.add("22: DEC(D)");
//        listaInstrucoes.add("23: GOTO(20)");
//        // Termina Soma
//        listaInstrucoes.add("24: DEC(C)");
//        listaInstrucoes.add("25: GOTO(14)");
//        // Decrementa B para saber a quantidade de
//        //  vezes em que vou repitir a multiplicação
//        //  por ele mesmo que está guardado no registrador
//        //  F, chegando 0 para de multiplicar.
//        listaInstrucoes.add("26: DEC(B)");
//        listaInstrucoes.add("27: JMP0(B,29)");
//        listaInstrucoes.add("28: GOTO(10)");
//        listaInstrucoes.add("29: HALT");
    }

    public static void main(String[] args) throws InterruptedException {
        /*
            ***OBS -> as instruções para fazer operações já tem o nome de seus
            registradores, por isso eu tenho que deixar o nome do registrador
            igual, pois, utilizamos A e B.
                A questão de permitir o usuário colocar o nome do seu registrador
            utilizar isso apenas no case 1, onde vai processar a instrução que o
            usuário digitar.
         */

        Scanner sc = new Scanner(System.in);
        List<String> listaInstrucoes = new ArrayList<String>(); // Guarda o Programa
        String comando;
        String[] instrucaoSeparada;
        int registrador[];
        Registrador reg[];
        byte op;
        do {
            op = operacoes();
            switch(op) {
                case 1:
                    reg = leituraReg("LEITURA");
                    leituraIns(listaInstrucoes);
                    processarCodigo(reg, listaInstrucoes);
                    exibirValorRegistrador(reg, "OPERAÇÃO DO USUÁRIO");
                    listaInstrucoes.removeAll(listaInstrucoes);
                    break;
                case 2:
                    reg = leituraReg("LEITURA - SOMA", 2, 2);
                    somaNaoPreserva(reg ,listaInstrucoes);
                    processarCodigo(reg, listaInstrucoes);
                    exibirValorRegistrador(reg, "SOMA");
                    listaInstrucoes.removeAll(listaInstrucoes);
                    break;
                case 3:
                    reg = leituraReg("LEITURA - SOMA", 3, 2);
                    somaPreserva(reg, listaInstrucoes);
                    processarCodigo(reg, listaInstrucoes);
                    exibirValorRegistrador(reg, "SOMA");
                    listaInstrucoes.removeAll(listaInstrucoes);
                    break;
                case 4:
                    reg = leituraRegSemSinal("LEITURA - MULTIPLICAÇÃO", 4, 2);
                    multiplicacao(listaInstrucoes);
                    processarCodigo(reg, listaInstrucoes);
                    exibirValorRegistrador(reg, "MULTIPLICAÇÃO");
                    listaInstrucoes.removeAll(listaInstrucoes);
                    break;
                case 5:
                    reg = leituraRegSemSinal("LEITURA - A <= B", 4, 2);
                    verificaMenor(listaInstrucoes);
                    processarCodigo(reg, listaInstrucoes);
                    exibirValorRegistradorMenor(reg, "REG_1 <= REG_2");
                    listaInstrucoes.removeAll(listaInstrucoes);
                    break;
                case 6:
                    reg = leituraRegSemSinal("LEITURA - PRIMO", 5, 1);
                    verificaPrimo(listaInstrucoes);
                    processarCodigo(reg, listaInstrucoes);
                    exibirValorRegistradorMenor(reg, "REG_1 <= REG_2");
                    listaInstrucoes.removeAll(listaInstrucoes);
                    break;
                case 7:
                    reg = leituraRegSemSinal("LEITURA - FATORIAL", 5, 1);
                    if(reg.length == 5 && reg[0].getValor() > 1) {
                        fatorial(listaInstrucoes);
                        processarCodigo(reg, listaInstrucoes);
                        exibirValorRegistrador(reg, "FATORIAL");
                    } else if(reg[0].getValor() > 0) {
                        exibirValorRegistrador(reg, "FATORIAL");
                    } else {
                        System.out.println("\n *** ERRO - NaN");
                    }
                    listaInstrucoes.removeAll(listaInstrucoes);
                    break;
                case 8:
                    reg = leituraRegSemSinal("LEITURA - POTENCIA A^B", 6, 2);
                    if(reg[0].getValor() > 0 && reg[1].getValor() > 0) {
                        potencia(listaInstrucoes);
                        processarCodigo(reg, listaInstrucoes);
                        exibirValorRegistrador(reg, "POTENCIA A^B");
                    } else if(reg[1].getValor() == 0) {
                        reg[0].setValor(1);
                        exibirValorRegistrador(reg, "POTENCIA A^B");
                    } else {
                        System.out.println("ERRO - NaN");
                    }
                    listaInstrucoes.removeAll(listaInstrucoes);
                    break;
            }
        }while(op != 0);
    }
}
