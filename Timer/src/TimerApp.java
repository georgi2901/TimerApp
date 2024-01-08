import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerApp extends JFrame {
    private JLabel timerLabel;
    private JLabel minutesLabel;
    private JLabel secondsLabel;
    private JTextField minutesTextField;
    private JTextField secondsTextField;
    private Timer timer;
    private int totalSeconds;

    public TimerApp() {
        setTitle("Programmable Timer App");
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

        JPanel timePanel = new JPanel();
        timePanel.setLayout(new GridLayout(1, 2));
        
        minutesLabel = new JLabel("Minutes: ");
        timePanel.add(minutesLabel);

        minutesTextField = new JTextField();
        minutesTextField.setHorizontalAlignment(SwingConstants.CENTER);
        minutesTextField.setToolTipText("Minutes");
        timePanel.add(minutesTextField);
        
        secondsLabel = new JLabel("Seconds: ");
        timePanel.add(secondsLabel);

        secondsTextField = new JTextField();
        secondsTextField.setHorizontalAlignment(SwingConstants.CENTER);
        secondsTextField.setToolTipText("Seconds");
        timePanel.add(secondsTextField);

        container.add(timePanel, BorderLayout.NORTH);

        JButton startButton = new JButton("Start");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            	minutesTextField.setText("");
            	secondsTextField.setText("");
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
            int minutes = Integer.parseInt(minutesTextField.getText());
            int seconds = Integer.parseInt(secondsTextField.getText());

            
            totalSeconds = minutes * 60 + seconds;

            updateTimerLabel();

            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    updateTimer();
                }
            });
            timer.start();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid time format. Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
            timerLabel.setText("00:00");
        }
    }

    private void updateTimer() {
        if (totalSeconds > 0) {
            totalSeconds--;
            updateTimerLabel();
        } else {
            stopTimer();
            JOptionPane.showMessageDialog(this, "Timer Expired!", "Timer Alert", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void updateTimerLabel() {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        String time = String.format("%02d:%02d", minutes, seconds);
        timerLabel.setText(time);
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