import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        BinarySearchTree bst = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.println("Bem-vindo à Árvore AVL!");

        int choice;

        do {
            System.out.println("Escolha a quantidade de elementos a serem inseridos nas árvores:");
            System.out.println("1. Inserir 100 elementos");
            System.out.println("2. Inserir 500 elementos");
            System.out.println("3. Inserir 1000 elementos");
            System.out.println("4. Inserir 10000 elementos");
            System.out.println("5. Inserir 20000 elementos");
            System.out.println("6. Sair");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                    int count = (int) Math.pow(10, choice);
                    long startTime;

                    int[] randomValues = generateRandomValues(random, count); // Gere os mesmos valores para ambas as árvores

                    // Inserir na Árvore AVL
                    startTime = System.nanoTime();
                    insertElements(avlTree, randomValues);
                    long avlInsertTime = System.nanoTime() - startTime;

                    // Inserir na Árvore BST
                    startTime = System.nanoTime();
                    insertElements(bst, randomValues);
                    long bstInsertTime = System.nanoTime() - startTime;

                    System.out.println(count + " elementos inseridos na Árvore AVL em " + avlInsertTime / 1000000 + " ms.");
                    System.out.println(count + " elementos inseridos na Árvore BST em " + bstInsertTime / 1000000 + " ms.");
                    break;
                case 6:
                    System.out.println("Encerrando o programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

            if (choice != 6) {
                // Exibir menu principal para buscar e excluir
                showMainMenu(avlTree, bst, scanner);
            }
        } while (choice != 6);
    }

    private static int[] generateRandomValues(Random random, int count) {
        int[] values = new int[count];
        for (int i = 0; i < count; i++) {
            values[i] = random.nextInt(100000); // Valor aleatório de 0 a 99999
        }
        return values;
    }

    private static void insertElements(AVLTree avlTree, int[] values) {
        for (int value : values) {
            avlTree.insert(value);
        }
    }

    private static void insertElements(BinarySearchTree bst, int[] values) {
        for (int value : values) {
            bst.insert(value);
        }
    }

    private static void showMainMenu(AVLTree avlTree, BinarySearchTree bst, Scanner scanner) {
        int choice;

        do {
            System.out.println("Escolha uma opção:");
            System.out.println("1. Buscar elemento na Árvore AVL");
            System.out.println("2. Buscar elemento na Árvore BST");
            System.out.println("3. Excluir elemento na Árvore AVL");
            System.out.println("4. Excluir elemento na Árvore BST");
            System.out.println("5. Voltar para o menu anterior");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Digite o valor a ser buscado na Árvore AVL: ");
                    int searchData = scanner.nextInt();
                    long startTime = System.nanoTime();
                    boolean found = avlTree.search(searchData);
                    long searchTime = System.nanoTime() - startTime;
                    if (found) {
                        System.out.println("Elemento encontrado na Árvore AVL em " + searchTime / 1000000 + " ms.");
                    } else {
                        System.out.println("Elemento não encontrado na Árvore AVL.");
                    }
                    break;
                case 2:
                    System.out.print("Digite o valor a ser buscado na Árvore BST: ");
                    int searchDataBST = scanner.nextInt();
                    startTime = System.nanoTime();
                    boolean foundBST = bst.search(searchDataBST);
                    searchTime = System.nanoTime() - startTime;
                    if (foundBST) {
                        System.out.println("Elemento encontrado na Árvore BST em " + searchTime / 1000000 + " ms.");
                    } else {
                        System.out.println("Elemento não encontrado na Árvore BST.");
                    }
                    break;
                case 3:
                    System.out.print("Digite o valor a ser excluído da Árvore AVL: ");
                    int deleteData = scanner.nextInt();
                    startTime = System.nanoTime();
                    avlTree.delete(deleteData);
                    long deleteTime = System.nanoTime() - startTime;
                    System.out.println("Elemento " + deleteData + " excluído da Árvore AVL em " + deleteTime / 1000000 + " ms.");
                    break;
                case 4:
                    System.out.print("Digite o valor a ser excluído da Árvore BST: ");
                    int deleteDataBST = scanner.nextInt();
                    startTime = System.nanoTime();
                    bst.delete(deleteDataBST);
                    deleteTime = System.nanoTime() - startTime;
                    System.out.println("Elemento " + deleteDataBST + " excluído da Árvore BST em " + deleteTime / 1000000 + " ms.");
                    break;
                case 5:
                    System.out.println("Retornando ao menu anterior.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (choice != 5);
    }
}
