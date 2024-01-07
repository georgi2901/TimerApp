import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerApp extends JFrame {
    private JLabel timerLabel;
    private JTextField timeTextField;
    private Timer timer;
    private int secondsRemaining;

    public TimerApp() {
        setTitle("Timer");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
    }

    private void initComponents() {
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        timerLabel = new JLabel("00:00");
        timerLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        timerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        container.add(timerLabel, BorderLayout.CENTER);

        timeTextField = new JTextField();
        timeTextField.setHorizontalAlignment(SwingConstants.CENTER);
        timeTextField.setToolTipText("Enter time in seconds");
        container.add(timeTextField, BorderLayout.NORTH);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void startTimer() {
        try {
            secondsRemaining = Integer.parseInt(timeTextField.getText());
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTimer();
                }
            });
            timer.start();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid time format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
            timerLabel.setText("00:00");
        }
    }

    private void updateTimer() {
        if (secondsRemaining > 0) {
            int minutes = secondsRemaining / 60;
            int seconds = secondsRemaining % 60;

            String time = String.format("%02d:%02d", minutes, seconds);
            timerLabel.setText(time);

            secondsRemaining--;
        } else {
            stopTimer();
            JOptionPane.showMessageDialog(this, "Timer Expired!", "Alert", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimerApp().setVisible(true);
            }
        });
    }
}
