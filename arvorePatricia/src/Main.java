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
        tree.inserir("buy");




        tree.pre_ordem();
//        System.out.println(tree.getRaiz().getVLig(0).getInfo()+ " - "+ tree.getRaiz().getVLig(0).getVFlag(0));
    }
}