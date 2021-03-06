 // Execuções Simultâneas
package treino.basicoavancado.InterfaceGrafica;

import javax.swing.*;
import java.awt.*;

public class Simultaneo extends JFrame {

    JLabel tempo = new JLabel("0");
    JLabel idade = new JLabel();
    int segundos = 0;

    public Simultaneo() {
        editarLayout();
        // new contarTempo().start(); --> start() vai iniciar o método run() da classe Thread de forma simultânea
        ContarTempo thread = new ContarTempo();
        thread.start();
        mostrarMensagem(); // Será simultâneo
        //thread.stop(); depreciado
        thread.feito = true; // Para o Thread.run() (Forma correta!!!)
        JOptionPane.showMessageDialog(null, "Você gastou " + segundos + " segundos para responder!");
    }

    public void editarLayout() {
        Font fonte1 = new Font("Arial", Font.BOLD, 50);
        Font fonte2 = new Font("Arial", Font.BOLD, 30);

        add(BorderLayout.NORTH, tempo).setFont(fonte2);
        add(BorderLayout.CENTER, idade).setFont(fonte1);

        tempo.setHorizontalAlignment(SwingConstants.CENTER);
        idade.setHorizontalAlignment(SwingConstants.CENTER);

        setTitle("Thread");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /*
    public void contarTempo() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (Exception erro) {}
            segundos++;
            tempo.setText(segundos + "");
        }
    }
    */ // Pulei para a classe contarTempo com Thread

    public void mostrarMensagem() {
        int anoNasc = Integer.parseInt(JOptionPane.showInputDialog("Em que ano você nasceu?"));
        int anoAtual = Integer.parseInt(JOptionPane.showInputDialog("Em que ano estamos?"));
        int resultado = anoAtual - anoNasc;
        idade.setText("Você tem " + resultado + " anos");
    }

    // O Thread pode ser executado de forma simultânea
    public class ContarTempo extends Thread {
        boolean feito = false; // Forma correta de parar
        public void run() {
            while (true) {
                if (feito) { // Dentro do while, forma correta de parar
                    tempo.setText(--segundos + "");
                    return;
                } else {
                    try { Thread.sleep(1000); } catch (Exception erro) {}
                    ++segundos;
                    tempo.setText(segundos + "");
                }
            }
        }
    }

    public static void main(String[] args) {
        new Simultaneo();
    }
    
}
