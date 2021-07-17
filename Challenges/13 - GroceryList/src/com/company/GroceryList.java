package com.company;

import java.util.ArrayList;

public class GroceryList {
    private ArrayList<String> groceryList = new ArrayList<String>();

    public void addGroceryList(String item) {
        groceryList.add(item);
    }

    public ArrayList<String> getGroceryList() {
        return groceryList;
    }

    public void printGroceryList() {
        System.out.println("You have " + groceryList.size() + " items in your grocery list");
        for(int i=0; i<groceryList.size(); i++) {
            System.out.println((i+1) + ". " + groceryList.get(i));
        }
    }

    public void modifyGroceryItem(String currentItem, String newItem) {
        int position = findItem(currentItem);
        if(position >= 0) {
            modifyGroceryItem(position, newItem);
        }
    }

    private void modifyGroceryItem(int position, String newItem) {
        groceryList.set(position, newItem);
        System.out.println("Grocery Item " + (position+1) + " has been modified.");
    }

    public void removeGroceryItem(String item) {
        int position = findItem(item);
        if(position >= 0) {
            removeGroceryItem(position);
        }
    }

    private void removeGroceryItem(int position) {
        groceryList.remove(position);
    }

    private int findItem(String searchItem) {
        return groceryList.indexOf(searchItem); // retorna -1 se não encontrar
    }

    public boolean onFile(String searchItem) {
//        int position = findItem(searchItem);
//        if(position >= 0) {
//            return true;
//        } else {
//            return false;
//        }
        // OR -->
        return groceryList.contains(searchItem);
    }


//    public String findItem(String searchItem) {
//        // ***OBS: poderia fazer busca com um laço de repetição, mas Java tem métodos
////        boolean exists = groceryList.contains(searItem);
//        // O método acima retorna se tem o item procurado ou não, não retorna a o posição,
//        //pois, retorna 'true'ou 'false'. O método abaixo retorna a posição
//        int position = groceryList.indexOf(searchItem); // retorna -1 se não encontrar
//        if(position >= 0) {
//            return groceryList.get(position);
//        }
//        return null;
//    }


    
}
