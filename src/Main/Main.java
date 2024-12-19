package Main;

import View.GestionEmployesConges;  // Importer la classe GestionEmployesConges du package 'view'

public class Main {

    public static void main(String[] args) {
        // Créer une instance de la classe GestionEmployesConges et rendre la fenêtre visible
        javax.swing.SwingUtilities.invokeLater(() -> {
            new GestionEmployesConges().setVisible(true);
        });
    }
}
