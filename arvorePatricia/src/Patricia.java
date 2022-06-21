public class Patricia {

    private No raiz;

    public Patricia() {
        this.raiz = new No();
    }

    private int buscaPos(No aux, String palavra) {
        int pos = 0;
        while(pos < aux.getTl() && aux.getVLig(pos).getInfo().charAt(0) < palavra.charAt(0))
            pos++;
        return pos;
    }
    private void inserir(No no, String palavraInserir) {
        palavraInserir = palavraInserir.toLowerCase(); // *** garante a palavra em minusculo
        // Busca Sequêncial, para ver onde inserir
        int pos = buscaPos(no, palavraInserir);

        // Achou a primeira letra, ai vamos verificar se tem mais ocorência de letra na palavra
        if(pos < no.getTl() && no.getVLig(pos).getInfo().charAt(0) == palavraInserir.charAt(0)) {
            No ant, atual;
            ant = no;
            atual = no.getVLig(pos);

            int ini, tamAtualPalavra, tamPalavraInserir;
            String atualPalavra = atual.getInfo();
            tamAtualPalavra = atualPalavra.length();
            tamPalavraInserir = palavraInserir.length();
            // Busca para ver quantas letras são iguais, a variável 'ini' guarda até onde vai essa
            // ocorência, e também é o inicio para forma a nova palavra.
            ini=0;
            while(ini < tamAtualPalavra && ini < tamPalavraInserir
                    && atualPalavra.charAt(ini) == palavraInserir.charAt(ini))
                ini++;

            String novaPalavra;
            // Se a palavra que já está na árvore tem uma substring e a palavra que estou tentando
            // inserir ter também substring, devo fazer o código abaixo, ligando a anterior com a
            // temporária que dentro da temporária cria a divisão da nova palavra e irá apontar
            // também para caixa atual, a anterior irá apontar para temporária.
            if(ini < tamAtualPalavra && ini < tamPalavraInserir) {
                novaPalavra = palavraInserir.substring(ini, tamPalavraInserir);
                boolean tempFlag = ant.getVLig(pos).getFlag();
                ant.getVLig(pos).setFlag(false);
                // --- Tentar fazer ligação que o Chico falou - VER SE É ISSO (parece funcionar)
                No temp = new No(atualPalavra.substring(0, ini));
                temp.setVLig(0, new No(novaPalavra));
                temp.setTl(temp.getTl() + 1);
                temp.setFlag(false);
                ant.setVLig(pos, temp);
                atual.setInfo(atualPalavra.substring(ini, tamAtualPalavra));
                pos = buscaPos(temp, atual.getInfo());
                temp.remaneja(pos);
                temp.setVLig(pos, atual);
                temp.getVLig(pos).setFlag(tempFlag);
                temp.setTl(temp.getTl()+1);
            } else {
                // Essas situações abaixo é para tratar caso uma das palavras eu percorro toda ela
                // e não sobra substring, se sobra só de uma e de outra não, ai tenho que testar para
                // cada uma delas.
                if(ini < tamAtualPalavra) {
                    atual.setInfo(palavraInserir);
                    atual.setFlag(true); // porque tem uma palavra aqui agora, a que mandei inserir
                    novaPalavra = atualPalavra.substring(ini, tamAtualPalavra);
                    inserir(atual, novaPalavra);
                } else {
                    if(ini < tamPalavraInserir) {
                        novaPalavra = palavraInserir.substring(ini, tamPalavraInserir);
                        inserir(atual, novaPalavra);
                    } else { // - Palavras Identicas
                        // Caso a palavra que estou tentando inserir seja igual a palavra que já
                        // está na árvore
                        atual.setFlag(true);
                    }
                }
            }
        } else { // Não achou na primeira letra então já insere
            no.remaneja(pos);
            no.setVLig(pos, new No(palavraInserir));
            no.setTl(no.getTl() + 1);
        }
    }

    public void inserir(String palavra) {
        inserir(raiz, palavra);
    }

    private void pre_ordem(No no, int nivel) {
        if(no != null) {
            System.out.println("- - - - - - - - Nível " + nivel + " - - - - - - - -");
            System.out.println("[ Info: " + no.getInfo() + "]");
            for(int i=0; i < no.getTl(); i++) {
//                System.out.println("- - - - - - - - Nível " + num + " - - - - - - - -");
//                System.out.println("[ Info: " + raiz.getVLig(i).getInfo() + "\t| vLig: " + i +
//                                    "| vFlag = " + raiz.getVFlag(i) + "]");
//                System.out.println(i);
                pre_ordem(no.getVLig(i), nivel + 1);
            }
            pre_ordem(no.getVLig(no.getTl()), nivel + 1);
        }
    }

    public void pre_ordem() {
        pre_ordem(raiz, 0);
    }

    private void exibirPalavras(No no, String partePalavra) {
        if(no != null) {
            String palavra;
            for(int i=0; i < no.getTl(); i++) {
                palavra = no.getVLig(i).getInfo();
                if(no.getVLig(i).getFlag() == true) {
                    System.out.println(partePalavra + palavra);
                }
                exibirPalavras(no.getVLig(i), partePalavra + palavra);
            }
            exibirPalavras(no.getVLig(no.getTl()), partePalavra);
        }
    }

    public void exibirPalavras() {
        exibirPalavras(raiz, "");
    }

}
