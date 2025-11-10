import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class Browser extends JFrame implements ActionListener{
	private int downloads;
	private JProgressBar[] bars;
	private JButton startButton;

    private CountDownLatch countDownLatch;
    private CyclicBarrier cyclicBarrier;

	public Browser(int downloads) {
		super("my download browser");
		this.downloads = downloads;

        countDownLatch = new CountDownLatch(1);
        cyclicBarrier = new CyclicBarrier(downloads + 1);

		bars = new JProgressBar[downloads];
		JPanel rows = new JPanel(new GridLayout(downloads, 1));

		for(int i=0; i<downloads; i++) {
			JPanel row = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 10));
			bars[i] = new JProgressBar(0, 100);
                        bars[i].setPreferredSize(new Dimension(500, 20));
			row.add(bars[i]);
			rows.add(row);

            new Thread(new Download(bars[i], countDownLatch, cyclicBarrier)).start();
        }

		startButton = new JButton("start downloads");
		startButton.addActionListener(this);

		this.add(rows, BorderLayout.CENTER);
		this.add(startButton, BorderLayout.SOUTH);


		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) throws InterruptedException {
		new Browser(5);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

        countDownLatch.countDown();

		startButton.setEnabled(false);
		startButton.setSelected(false);
		startButton.setText("downloading ...");

        new Thread(() -> {
            try{
                cyclicBarrier.await();
            } catch(Exception exception){}

            startButton.setEnabled(true);
    		startButton.setSelected(true);
    		startButton.setText("downloads finished");
        }).start();
	}
}
