package com.appsoft.data_trans;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.appsoft.chart.TimeSeriesChart;
import com.appsoft.enums.MessageEnum;
import com.appsoft.event.MsgEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainFrame {

	private final static EventBus eventBus = new EventBus();
	private JFrame frame;
	static MainFrame window = null;
	JSplitPane splitPane = null;
	JLabel lbl_all_count = null;
	TimeSeriesChart seriesChart = null;
	JButton btnNewButton = null;
	JTextArea textArea = null;
	JSpinner spinner = null;
	JSpinner spinner_all_count = null;
	AppThread app = null;
	/**
	 * Launch the application.
	 * @throws UnsupportedLookAndFeelException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		UIManager.setLookAndFeel(lookAndFeel);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new MainFrame();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 970, 619);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		splitPane.setLeftComponent(panel);
		
		btnNewButton = new JButton("开始");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (btnNewButton.getText().equals("开始")) {
						btnNewButton.setText("停止");
						eventBus.register(window.new HelloEventListener());
						app = new AppThread(eventBus);
						app.setCount((Integer)spinner.getValue());
						app.setAllCount((Integer)spinner_all_count.getValue());
						Thread t = new Thread(app);
						t.start();
					} else if (btnNewButton.getText().equals("停止")) {
						app.stop();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("分页数量");
		
		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSpinner source = (JSpinner)e.getSource();
				if (app != null) {
					app.setCount((Integer)source.getValue());
				}
			}
		});
		spinner.setModel(new SpinnerNumberModel(1000, 10, 50000, 1));
		
		JPanel panel_chart = new JPanel();
		
		lbl_all_count = new JLabel("0");
		
		JLabel lblNewLabel_1 = new JLabel("总数据量：");
		
		spinner_all_count = new JSpinner();
		spinner_all_count.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSpinner source = (JSpinner)e.getSource();
				if (app != null) {
					app.setAllCount((Integer)source.getValue());
				}
			}
		});
		spinner_all_count.setModel(new SpinnerNumberModel(new Integer(11335786), null, null, new Integer(1)));
		
		JLabel lblNewLabel_2 = new JLabel("已处理（个）：");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel_chart, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 932, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lbl_all_count)
							.addPreferredGap(ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner_all_count, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(62)
							.addComponent(lblNewLabel)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(spinner, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_chart, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(lblNewLabel)
						.addComponent(spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(spinner_all_count, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2)
						.addComponent(lbl_all_count))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		splitPane.setDividerLocation(0.9);
		splitPane.setDividerSize(5);
		
		seriesChart = new TimeSeriesChart();
		panel_chart.setLayout(new BorderLayout(0, 0));
		panel_chart.add(seriesChart.getChartPanel(), BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		splitPane.setRightComponent(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); 
		textArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		//new Thread(seriesChart).start();
	}
	
	public void setValues(final String message) {
		SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	if (message.startsWith(MessageEnum.ALL_COUNT.getId())) {
					lbl_all_count.setText(String.valueOf(Integer.parseInt(lbl_all_count.getText()) + Integer.parseInt(message.substring(1))));
				}
				if (message.startsWith(MessageEnum.QUERY_TIME.getId())) {
					seriesChart.addData1(Double.parseDouble(message.substring(1)));
					//textArea.append("query_time:"+message.substring(1) + "\r\n");
				}
				if (message.startsWith(MessageEnum.WRITE_TIME.getId())) {
					seriesChart.addData2(Double.parseDouble(message.substring(1)));
					//textArea.append("write_time:"+message.substring(1) + "\r\n");
				}
				if (message.startsWith(MessageEnum.CONSOLE.getId())) {
					textArea.append(message.substring(1) + "\r\n");
					textArea.setCaretPosition(textArea.getText().length());  
				}
				if (message.startsWith(MessageEnum.OK.getId())) {
					btnNewButton.setText("开始");
				}
	        }
	    });
	}
	
	class HelloEventListener{

	    @Subscribe
	    public void listen(MsgEvent event) {
	    	setValues(event.getMessage());
	    }
	}
}
