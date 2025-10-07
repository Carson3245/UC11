import javax.swing.SwingUtilities;
import view.cadastroVIEW;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            cadastroVIEW tela = new cadastroVIEW();
            tela.setLocationRelativeTo(null);
            tela.setVisible(true);
        });
    }
}