package edu.cis.CIS350.MovieDB;

import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;

public class GUIPanel extends JPanel {
	private JTable table;
	private JTextField txtEnterSearch;
	public GUIPanel() {
		setupPanel();
	}
	
	private void setupPanel(){
		setBackground(new Color(0, 206, 209));
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 293, 733, 190);
		add(scrollPane);
		
		table = new JTable();
		table.setBackground(Color.GRAY);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Movie Title", "Run Time", "Genre", "Actor"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		scrollPane.setViewportView(table);
		
		txtEnterSearch = new JTextField();
		txtEnterSearch.setBackground(new Color(255, 255, 224));
		txtEnterSearch.setToolTipText("Enter in search");
		txtEnterSearch.setBounds(26, 234, 368, 22);
		add(txtEnterSearch);
		txtEnterSearch.setColumns(10);
		
		JList list = new JList();
		list.setForeground(Color.WHITE);
		list.setBackground(new Color(128, 128, 128));
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"Title", "Genre", "Actor", "Director"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBounds(429, 208, 316, 72);
		add(list);
		
		JButton btnTakeQuiz = new JButton("Take Quiz!");
		btnTakeQuiz.setBounds(507, 59, 144, 31);
		add(btnTakeQuiz);
	}
}
