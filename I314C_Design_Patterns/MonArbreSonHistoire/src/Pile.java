public interface Pile {
    boolean estVide();
    void push(Object n);
    Object pop();
    Object sommet();
    int taille();
    String toString();
}