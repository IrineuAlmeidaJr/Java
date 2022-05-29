public class Patricia {

    private No raiz;

    public Patricia() {
        this.raiz = new No();
    }

    public No getRaiz() {
        return raiz;
    }

    private int buscaPos(No aux, String palavra) {
        int pos = 0;
        while(pos < aux.getTl() && aux.getVLig(pos).getInfo().charAt(0) < palavra.charAt(0))
            pos++;
        return pos;
    }
    void inserir(No no, String palavra) {
        palavra = palavra.toLowerCase(); // *** garante a palavra em minusculo
        // Busca Sequêncial, para ver onde inserir
        int pos = buscaPos(no, palavra);

        // Achou a primeira letra, ai vamos verificar se tem mais ocorência de letra na palavra
        if(pos < no.getTl() && no.getVLig(pos).getInfo().charAt(0) == palavra.charAt(0)) {
            No ant, atual;
            ant = no;
            atual = no.getVLig(pos);

            int ini, tam;
            String atual_palavra = atual.getInfo();
            tam = atual_palavra.length();
            // Busca para ver quantas letras são iguais, a variável 'ini' guarda até onde vai essa
            // ocorência, e também é o inicio para forma a nova palavra.
            ini=0;
            while(ini < tam && atual_palavra.charAt(ini) == palavra.charAt(ini))
                ini++;

            String novaPalavra;
            // Se a palavra que já está na árvore tem uma substring e a palavra que estou tentando
            // inserir ter também substring, devo fazer o código abaixo, ligando a anterior com a
            // temporária que dentro da temporária cria a divisão da nova palavra e irá apontar
            // também para caixa atual, a anterior irá apontar para temporária.
            if(ini < atual_palavra.length() && ini < palavra.length()) {
                novaPalavra = palavra.substring(ini, palavra.length());
                boolean tempFlag = ant.getVFlag(pos);
                ant.setVFlag(pos, false);
                // --- Tentar fazer ligação que o Chico falou - VER SE É ISSO (parece funcionar)
                No temp = new No(atual_palavra.substring(0, ini));
                inserir(temp, novaPalavra);
                ant.setVLig(pos, temp);
                atual.setInfo(atual_palavra.substring(ini, tam));
                pos = buscaPos(temp, atual.getInfo());
                temp.remaneja(pos);
                temp.setVLig(pos, atual);
                temp.setVFlag(pos, tempFlag);
                temp.setTl(temp.getTl()+1);

                // ***
                // Arrumar a questão do TRUE e FALSE aqui para ver se já terminou a palavra

            } else {
                // Essas situações abaixo é para tratar caso uma das palavras eu percorro toda ela
                // e não sobra substring, se sobra só de uma e de outra não, ai tenho que testar para
                // cada uma delas.
                if(ini < atual_palavra.length()) {
                    // Não muda o valor da flag
                    novaPalavra = atual_palavra.substring(ini, tam);
                    inserir(atual, novaPalavra);
                }
                if(ini < palavra.length()) {
                    novaPalavra = palavra.substring(ini, palavra.length());
                    inserir(atual, novaPalavra);
                }
            }

        } else { // Não achou na primeira letra então já insere
            no.remaneja(pos);
            no.setVLig(pos, new No(palavra));
            no.setVFlag(pos, true);
            no.setTl(no.getTl() + 1);
        }
    }

    void inserir(String palavra) {
        inserir(raiz, palavra);
    }

    public void pre_ordem(No raiz, int num) {
        if(raiz != null) {
            System.out.println("- - - - - - - - Nível " + num + " - - - - - - - -");
            System.out.println("[ Info: " + raiz.getInfo() + "]");
            for(int i=0; i < raiz.getTl(); i++) {
//                System.out.println("- - - - - - - - Nível " + num + " - - - - - - - -");
//                System.out.println("[ Info: " + raiz.getVLig(i).getInfo() + "\t| vLig: " + i +
//                                    "| vFlag = " + raiz.getVFlag(i) + "]");
//                System.out.println(i);
                pre_ordem(raiz.getVLig(i), num + 1);
            }
            pre_ordem(raiz.getVLig(raiz.getTl()), num + 1);
        }
    }

    public void pre_ordem() {
        pre_ordem(raiz, 0);
    }

}
