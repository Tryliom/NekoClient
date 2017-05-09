package neko.gui;

import java.awt.Color;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Auteur: Tryliom 
 * Projet: Neko 
 * Fichier: UpdateVersion 
 * Version: 0.2 
 * Date: 11.11.2016
 */
public class NekoUpdate extends javax.swing.JFrame {
	String newVersion;
	String actualVersion;
	ArrayList<String> s = new ArrayList<>();

	public NekoUpdate(String newVer, String ver, ArrayList<String> sr) {
		initComponents();
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		newVersion = newVer;
		actualVersion = ver;
		s = sr;
		lblNewVer.setText(newVer);
		lblVer.setText(actualVersion);
		String cl = "";
		for (int i = 1; i < s.size(); i++) {
			cl += s.get(i) + "\n";
		}
		txtCl.setText("ChangeLog:\n\n" + cl);
		float color[] = Color.RGBtoHSB(205, 205, 255, null);
		this.getContentPane().setBackground(Color.getHSBColor(color[0], color[1], color[2]));
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		lblNeko = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		lblVer = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		lblNewVer = new javax.swing.JLabel();
		btnUpdate = new javax.swing.JButton();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtCl = new javax.swing.JTextArea();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Neko Update");

		lblNeko.setFont(new java.awt.Font("Century Gothic", 3, 24)); // NOI18N
		lblNeko.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblNeko.setText("Neko Update");

		jLabel2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
		jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel2.setText("Version actuelle");
		jLabel2.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		lblVer.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
		lblVer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblVer.setText("1.4.2");
		lblVer.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		jLabel4.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
		jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		jLabel4.setText("Version supérieur");
		jLabel4.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		lblNewVer.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
		lblNewVer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lblNewVer.setText("1.4.3");
		lblNewVer.setBorder(javax.swing.BorderFactory
				.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		btnUpdate.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
		btnUpdate.setText("Update !");
		btnUpdate.setContentAreaFilled(false);
		btnUpdate.setFocusPainted(false);
		btnUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnUpdateActionPerformed(evt);
			}
		});

		txtCl.setEditable(false);
		txtCl.setColumns(20);
		txtCl.setFont(new java.awt.Font("Century Gothic", 2, 18)); // NOI18N
		txtCl.setRows(5);
		txtCl.setText("ChangeLog:\n");
		txtCl.setToolTipText("");
		txtCl.setBorder(new javax.swing.border.SoftBevelBorder(
				javax.swing.border.BevelBorder.RAISED));
		txtCl.setRequestFocusEnabled(false);
		jScrollPane1.setViewportView(txtCl);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addGap(39, 39, 39)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(jScrollPane1)
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						jLabel2,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE)
																				.addComponent(
																						lblVer,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						167,
																						Short.MAX_VALUE))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING,
																				false)
																				.addComponent(
																						jLabel4,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						167,
																						Short.MAX_VALUE)
																				.addComponent(
																						lblNewVer,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						Short.MAX_VALUE))))
								.addGap(39, 39, 39))
				.addGroup(
						javax.swing.GroupLayout.Alignment.TRAILING,
						layout.createSequentialGroup()
								.addContainerGap(266, Short.MAX_VALUE)
								.addComponent(lblNeko,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										226,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(261, 261, 261))
				.addGroup(
						layout.createSequentialGroup()
								.addGap(282, 282, 282)
								.addComponent(btnUpdate,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										198,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGap(41, 41, 41)
								.addComponent(lblNeko,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										38,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(38, 38, 38)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														jLabel2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														44,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														jLabel4,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														44,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														lblVer,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														48,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(
														lblNewVer,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														48,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(jScrollPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										285,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										35, Short.MAX_VALUE)
								.addComponent(btnUpdate,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										64,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(27, 27, 27)));

		pack();
	}// </editor-fold>

	private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			URI url = URI.create("http://neko.alwaysdata.net/");
			Desktop.getDesktop().browse(url);
			this.dispose();
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), "Pas de connexion à internet :c");
		}

	}

	// Variables declaration - do not modify
	private javax.swing.JButton btnUpdate;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblNeko;
	private javax.swing.JLabel lblNewVer;
	private javax.swing.JLabel lblVer;
	private javax.swing.JTextArea txtCl;
	// End of variables declaration
}
