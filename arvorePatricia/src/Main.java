public class Main {

    public static void main(String[] args) {
        Patricia tree = new Patricia();
//        tree.inserir("animal");
//        tree.inserir("adega");
//        tree.inserir("baixo");
//        tree.inserir("alfinente");
//        tree.inserir("alfaiate");

        tree.inserir("bear");
        tree.inserir("Bell");
        tree.inserir("bid");
        tree.inserir("Bull");
        tree.inserir("buy");
        tree.inserir("sell");
        tree.inserir("stock");
        tree.inserir("stop");
        // *** Da erro ao inserir "be" (resolvido)
        tree.inserir("Be");

        // Essa insereção abaixo dava erro ai tive  que fazer modificação na linha 66, pois,
        // ocorria de a palavra que estou inseridno ser menor do que a que está inserida na árvore,
        // nesse caso devo cortar a palavra que está na árvore e inserir o resto de novo e sem
        // mudar o flag porque continuará sendo uma palavra, no caso "ele"
//        tree.inserir("elenise");
//        tree.inserir("ele");

        tree.pre_ordem();
        System.out.println("\n- - - Palavras - - -");
        tree.exibirPalavras();

    }
}