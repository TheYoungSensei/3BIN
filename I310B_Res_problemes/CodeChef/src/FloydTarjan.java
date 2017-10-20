// I301B, 017, Floyd et les rues de Pallo Alto 2016, E09, S03, Q1, 2017-2018

import java.util.Scanner;

class FloydTarjan {
    static Scanner scanner = new Scanner(System.in);

    //Nombre de croquis
    static int t;
    //Nombre de lignes du croquis courant
    static int n;
    //Nombre de colonnes du croquis courant
    static int m;
    //croquis courant
    static char[][] map;

    // Num鲯 de ligne de Floyd
    static int nF;
    // Num鲯 de colonne de Floyd
    static int mF;
    // Num鲯 de ligne de Tarjan
    static int nT;
    // Num鲯 de colonne de Tarjan
    static int mT;

    // Table de pr鳥nce des 鴡ts visit鳍
    // rf est un tableau rectangulaire de n lignes et m colonnes.
    // rf[i][j] == true <==> la case dont le num鲯 de ligne vaut i et
    // dont le num鲯 de colonne vaut j a 鴩 visit饮
    static boolean[][] rf;

    // Coordonn饳 des cases appartenant ࠬa fronti貥.
    // f a une taille logique de n x m;
    // f[0] contient des num鲯s de lignes.
    // f[1] contient des num鲯s de colonnes.
    static int[][] f;

    // Taille logique de f
    // 0 <= f <= n x m;
    static int szf;

    static int nb;

    public static void main(String[] args) {
        t = scanner.nextInt();
        int i = 0;
        while (i != t) {
            readInputs();
            analyzeMap();
            i++;
        }
    }

    static void analyzeMap() {
        //Obtention des coordonnées GPS
        nb = 0;
        rf[nF][mF] = true;
        addPile(nF, mF);
        //Mais où est donc Tarjan ?
        boolean found = false;
        if(mF == mT && nF == nT) { //Ah il était juste sur nos coordonnées
            System.out.println("0 true"); //Quelle chance
        }
        //Tant que je n'ai pas fais chaque coordonnée possible, je continue
        while(szf != 0) {
            int c[][] = popPile(); //Obtention d'une coordonnée potentielle (Où suis je ?)
            succ(c[0][0], c[1][0]); //Obtention des coordonnées possible
            if(map[c[0][0]][c[1][0]] == 'T') { //Ho tiens voici Tarjan
                found = true;
            }
        }
        if(found) {
            System.out.println(nb + " true");
        } else {
            System.out.println(nb + " false");
        }
    }

    static void addPile(int ligne, int colonne) {
        nb++;
        rf[ligne][colonne] = true;
        f[0][szf] = ligne;
        f[1][szf] = colonne;
        szf++;
    }

    static int[][] popPile(){
        int elem[][] = new int[2][1];
        elem[0][0] = f[0][szf - 1];
        elem[1][0] = f[1][szf - 1];
        szf--;
        return elem;
    }

    static void succ(int ligne, int colonne) {
        if(ligne < map.length - 1) { //Puis je aller en haut ?
            check(ligne + 1, colonne);
        }
        if(ligne != 0) { //Puis je aller en bas ?
            check(ligne-1, colonne);
        }
        if(colonne < map[ligne].length - 1) { //Puis je aller à droite ?
            check(ligne, colonne + 1);
        }
        if(colonne != 0) { //Puis je aller à gauche ?
            check(ligne, colonne - 1);
        }
    }

    private static void check(int ligne, int colonne) {
        //Si je n'ai pas déjà visiter cette case et que je peux y accéder (qu'il s'agisse soit d'un . ou de Tarjan
        if(!rf[ligne][colonne] && (map[ligne][colonne] == '.' || map[ligne][colonne] == 'T')) {
            addPile(ligne, colonne); //Alors j'essaye de m'en souvenir
        }
    }

    // Cette m鴨ode lit les informations sur l'entr饠standard
    // et initialise les attributs relatifs au croquis courant
    // et ࠳on parcours.
    static void readInputs() {
        n = scanner.nextInt();
        m = scanner.nextInt();
        scanner.nextLine();

        map = new char[n][m];
        rf = new boolean[n][m];
        f = new int[2][n * m];
        szf = 0;

        int i = 0;
        while (i != n) {
            map[i] = scanner.nextLine().toCharArray();

            int j = 0;
            while (j != m) {
                if (map[i][j] == 'F') {
                    nF = i;
                    mF = j;
                }
                if (map[i][j] == 'T') {
                    nT = i;
                    mT = j;
                }
                j++;
            }
            i++;
        }
    }
}