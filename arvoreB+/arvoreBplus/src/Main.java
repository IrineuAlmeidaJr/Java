public class Main {

    public static void main(String[] args) {
        BTreePlus tree = new BTreePlus();
//        tree.inserir(2);
//        tree.inserir(3);
//        tree.inserir(4);
//        tree.inserir(5);
//        tree.inserir(6);
//        tree.inserir(7);
//        tree.inserir(8);
//        tree.inserir(9);
//        tree.inserir(10);
//        tree.inserir(11);
//        tree.inserir(12);
//        tree.inserir(13);
//        tree.inserir(14);
//        tree.inserir(15);
//        tree.inserir(16);
//        tree.inserir(17);
//        tree.inserir(18);
//        tree.inserir(19);
//        tree.inserir(20);
//        tree.inserir(21);
//        tree.inserir(6);
//        tree.inserir(17);
//        tree.inserir(90);
//        tree.inserir(37);
//        tree.inserir(38);
//        tree.inserir(39);

        for(int i=1; i<100; i++)
            tree.inserir(i);

//       tree.in_ordem();
        tree.exibir();

    }
}