package client;

import agencecommun.Client;
import agencecommun.InterfaceClient;
import agencecommun.InterfaceServeur;
import agencecommun.Notify;
import agencecommun.Reservation;
import agencecommun.Vehicule;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Feriel Mokhtari
 */
public class ClientIHM extends javax.swing.JFrame implements InterfaceClient {

    static InterfaceServeur stub;
    static ArrayList<Vehicule> liste;
    HashMap<Integer, Vehicule> HV;
    ArrayList<Client> listec;
    HashMap<Integer, Client> HC;
    static Client client;
    Notify info;
    DefaultTableModel tableur;
    Object[] tab;
    DefaultTableModel tableur1;
    Object[] tab1;
    static int i = 0;

    /**
     * Creates new form Client
     */
    public ClientIHM() throws RemoteException {

        initIHM();

    }

    private void initIHM() throws RemoteException {

        initComponents();

        try {

            stub = (InterfaceServeur) Naming.lookup("serveur");
            info = new CallBack(commentaire);
            // stub.rejoindre(info);
            stub.callMeBack(info);

            //new ListeVehicule(this, stub.afficherToutParMarque()).setVisible(true);
            //System.out.println(liste.size());
            liste = stub.afficherToutParTri("disponible", "asc");
            affichageListeVehiculePic(liste);

            //  affichageListeVehiculePic(liste);
        } catch (NotBoundException | MalformedURLException ex) {
            Logger.getLogger(ClientIHM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ClientIHM(Client client) throws RemoteException {
        this.client = client;
        initIHM();

    }

    /*  void clearContainer() {
        container.removeAll();
        repaint();

    }*/
   void affichageListeVehiculePic(ArrayList<Vehicule> list) {
        container.removeAll();

        int x = 4;
        int y = list.size() / x;
        container.setLayout(new GridLayout(y, x));

        list.forEach((Vehicule v) -> {
            JPanel p = new JPanel();
            p.setLayout(new GridBagLayout());
            GridBagConstraints c = new GridBagConstraints();
            
            p.setBackground(new java.awt.Color(0, 153, 153));
            p.setPreferredSize(new Dimension(100, 180));
            JButton b = new JButton();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 0;
            c.gridy = 0;
            c.gridwidth = 1;
            c.insets = new Insets(10,10,0,0); 
            b.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/visualiser.png"))
                    .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH)));
            b.addActionListener((e) -> {
                try {
                    visualiser(v);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientIHM.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            p.add(b, c);
            JLabel ll = new JLabel();
            c.fill = GridBagConstraints.HORIZONTAL;
            c.gridx = 2;
            c.gridy = 0;
            c.gridwidth = 1;
            
           
            if (v.isDisponible()) {
                ll.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/dispo.png"))
                        .getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
            } else {
                ll.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/nondispo.png"))
                        .getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH)));
            }
            p.add(ll, c);
           
            JLabel l = new JLabel();
            if (v.getPhoto() != null) {
                l.setIcon(new ImageIcon(new ImageIcon(v.getPhoto()).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
          
            } else {
                l.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/defImage.png")).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
            }
            c.fill = GridBagConstraints.HORIZONTAL;

            c.ipady = 50;      //make this component tall
            c.weightx = 0.0;
            c.gridwidth = 3;
            c.insets = new Insets(-20, 10,10,10);  //top padding
            c.gridx = 0;
            c.gridy = 1;
            
            p.add(l, c);

            JLabel lb = new JLabel("hellooooo");
            c.fill = GridBagConstraints.HORIZONTAL;
            c.ipady = 0;       //reset to default
            c.weighty = 1.0;   //request any extra vertical space
            c.anchor = GridBagConstraints.BELOW_BASELINE; //bottom of space
           
            c.gridx = 0;       //aligned with button 2
            c.gridwidth = 3;   //2 columns wide
            c.gridy = 2;  
            //third row
            lb.setText(v.getMarque());
            p.add(lb, c);

            
            p.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
            container.add(p);

        });
        container.revalidate();
        container.repaint();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        trierpar = new javax.swing.ButtonGroup();
        carburantgroup = new javax.swing.ButtonGroup();
        triType = new javax.swing.ButtonGroup();
        msgcarburant = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        commentaire = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        trier = new javax.swing.JButton();
        ordre = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        tri = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        refresh = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        solde = new javax.swing.JLabel();
        nom = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        container = new javax.swing.JPanel();
        msg11 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        listeR = new javax.swing.JTable();
        msg2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        seconnecter = new javax.swing.JMenuItem();
        sinscrire = new javax.swing.JMenuItem();
        msolde = new javax.swing.JMenuItem();
        deconnexion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBounds(new java.awt.Rectangle(200, 100, 0, 0));
        setMinimumSize(new java.awt.Dimension(400, 302));
        setPreferredSize(new java.awt.Dimension(1000, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 400));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        msgcarburant.setForeground(new java.awt.Color(204, 0, 0));
        getContentPane().add(msgcarburant, new org.netbeans.lib.awtextra.AbsoluteConstraints(708, 557, 184, -1));

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));
        jPanel1.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel1.setLayout(null);

        commentaire.setColumns(20);
        commentaire.setRows(5);
        jScrollPane1.setViewportView(commentaire);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(60, 440, 840, 70);

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        trier.setText("Trier");
        trier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                trierActionPerformed(evt);
            }
        });
        jPanel2.add(trier, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 80, -1));

        ordre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Croissant", "Decroissant" }));
        jPanel2.add(ordre, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 20, 115, -1));

        jLabel3.setText("Dans un ordre :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 20, 120, 20));

        tri.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "marque", "couleur", "carburant", "kilometrage", "prix", "disponible" }));
        tri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                triActionPerformed(evt);
            }
        });
        jPanel2.add(tri, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, -1, -1));

        jLabel2.setText("Trier par :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 70, 20));

        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });
        jPanel2.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 60, 30));
        refresh.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/refresh.png"))
            .getImage().getScaledInstance(25, 25, Image.SCALE_SMOOTH)));

    jLabel1.setText("Solde :");
    jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 40, -1, -1));

    solde.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    solde.setForeground(new java.awt.Color(204, 204, 255));
    solde.setText("....");
    jPanel2.add(solde, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 30, 100, 30));

    nom.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    nom.setForeground(new java.awt.Color(204, 204, 255));
    nom.setText("  ");
    jPanel2.add(nom, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 130, -1));

    jPanel1.add(jPanel2);
    jPanel2.setBounds(80, 10, 800, 60);

    container.setBackground(new java.awt.Color(0, 153, 153));
    jScrollPane3.setViewportView(container);

    jPanel1.add(jScrollPane3);
    jScrollPane3.setBounds(60, 80, 840, 350);

    msg11.setText("       ");
    jPanel1.add(msg11);
    msg11.setBounds(780, 20, 120, 14);

    jTabbedPane1.addTab("Acceuil", jPanel1);

    jPanel4.setBackground(new java.awt.Color(0, 102, 102));
    jPanel4.addContainerListener(new java.awt.event.ContainerAdapter() {
        public void componentRemoved(java.awt.event.ContainerEvent evt) {
            jPanel4ComponentRemoved(evt);
        }
    });

    jButton1.setBackground(new java.awt.Color(0, 153, 153));
    jButton1.setText("Annuler ou terminer réservation");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            jButton1ActionPerformed(evt);
        }
    });

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
            .addContainerGap(106, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(98, 98, 98))
    );
    jPanel5Layout.setVerticalGroup(
        jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jButton1)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    jScrollPane4.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            jScrollPane4MouseClicked(evt);
        }
    });

    listeR.setBackground(new java.awt.Color(204, 204, 204));
    listeR.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {

        },
        new String [] {
            "code", "marque", "Date début", "Date fin", "prix"
        }
    ) {
        Class[] types = new Class [] {
            java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
        };
        boolean[] canEdit = new boolean [] {
            false, false, false, false, false
        };

        public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return canEdit [columnIndex];
        }
    });
    jScrollPane4.setViewportView(listeR);

    msg2.setForeground(new java.awt.Color(204, 0, 0));
    msg2.setText("        ");

    javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(162, 162, 162)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel4Layout.createSequentialGroup()
                    .addGap(117, 117, 117)
                    .addComponent(msg2, javax.swing.GroupLayout.PREFERRED_SIZE, 529, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap(189, Short.MAX_VALUE))
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(106, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 742, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(107, Short.MAX_VALUE)))
    );
    jPanel4Layout.setVerticalGroup(
        jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(jPanel4Layout.createSequentialGroup()
            .addContainerGap()
            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(18, 18, 18)
            .addComponent(msg2)
            .addContainerGap(488, Short.MAX_VALUE))
        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE)))
    );

    jTabbedPane1.addTab("Mes réservations", jPanel4);

    getContentPane().add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 960, 600));

    jPanel3.setBackground(new java.awt.Color(0, 102, 102));
    getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 990, 600));

    jMenu1.setText("Parametre");

    seconnecter.setText("Se connecter");
    seconnecter.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            seconnecterActionPerformed(evt);
        }
    });
    jMenu1.add(seconnecter);

    sinscrire.setText("S'inscrire");
    sinscrire.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            sinscrireActionPerformed(evt);
        }
    });
    jMenu1.add(sinscrire);

    msolde.setText("Ajouter solde");
    msolde.setEnabled(false);
    msolde.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            msoldeActionPerformed(evt);
        }
    });
    jMenu1.add(msolde);

    deconnexion.setText("Deconnexion");
    deconnexion.setEnabled(false);
    deconnexion.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            deconnexionActionPerformed(evt);
        }
    });
    jMenu1.add(deconnexion);

    jMenuBar1.add(jMenu1);

    setJMenuBar(jMenuBar1);

    pack();
    }// </editor-fold>//GEN-END:initComponents


    private void seconnecterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seconnecterActionPerformed
        new SeConnecter(this).setVisible(true);
    }//GEN-LAST:event_seconnecterActionPerformed

    private void deconnexionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deconnexionActionPerformed
        try {
            deconnexion();
        } catch (RemoteException ex) {
            Logger.getLogger(ClientIHM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_deconnexionActionPerformed
    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    private void sinscrireActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sinscrireActionPerformed

        try {
            openWebpage(new URL("http://localhost:8080/AgenceClient1/Client/enrClient.jsp").toURI());
        } catch (URISyntaxException | MalformedURLException ex) {
            // Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_sinscrireActionPerformed

    private void msoldeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msoldeActionPerformed
        String soldeAAjouter = JOptionPane.showInputDialog("votre "
                + "solde est :" + client.getSolde() + "\n Tapez le solde a ajouter :");
        System.out.println(soldeAAjouter);

        if (soldeAAjouter != null && !soldeAAjouter.equals("")) {
            try {
                System.out.println("gg");
                stub.modifierSoldeClient(client.getUsername(), Double.parseDouble(soldeAAjouter));
                client.setSolde(stub.getClient(client.getUsername()).getSolde());
                solde.setText("" + client.getSolde());
                JOptionPane.showMessageDialog(null, "modification réussite !\n nouveau solde est :" + client.getSolde());

            } catch (RemoteException ex) {
                System.out.println("solde erreur");
            }
        } else {
            JOptionPane.showMessageDialog(null, "vous devez tapez le solde a ajouter !");
        }
    }//GEN-LAST:event_msoldeActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        try {
            if (client != null) {
                client = stub.getClient(client.getUsername());
                solde.setText("" + client.getSolde());
            }
            liste = stub.afficherToutParTri("marque", "desc");
        } catch (RemoteException ex) {
            Logger.getLogger(ClientIHM.class.getName()).log(Level.SEVERE, null, ex);
        }
        affichageListeVehiculePic(liste);
    }//GEN-LAST:event_refreshActionPerformed

    private void triActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_triActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_triActionPerformed

    private void trierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_trierActionPerformed

        String orderBy = "desc";
        if (ordre.getSelectedItem().equals("Croissant")) {
            orderBy = "asc";
        }
        try {
            liste = stub.afficherToutParTri((String) tri.getSelectedItem(), orderBy);
            System.out.println("trier par :" + tri.getSelectedItem() + " ordre :" + orderBy);
            
            affichageListeVehiculePic(liste);
        } catch (RemoteException ex) {
            Logger.getLogger(ClientIHM.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_trierActionPerformed
    void viderTable(JTable list) {
        int size = list.getModel().getRowCount();
        for (int j = size - 1; j >= 0; j--) {
            ((DefaultTableModel) list.getModel()).removeRow(j);
        }

    }

    void affichageReservation(HashMap<Integer, Reservation> rv) throws RemoteException {
        tableur1 = (DefaultTableModel) listeR.getModel();
        tab1 = new Object[5];
        viderTable(listeR);
        Set cles = rv.keySet();
        Iterator it = cles.iterator();
        while (it.hasNext()) {
            //listv.add(v.getCode() + " " + v.getMarque());
            // tu peux typer plus finement ici

            Reservation r = rv.get(it.next()); // tu peux typer plus finement ici

            tab1[0] = r.getCode();

            tab1[1] = r.getVoiture().getMarque();
            tab1[2] = r.getDateDe();
            tab1[3] = r.getDateA();
            tab1[4] = r.getPrix();

            tableur1.addRow(tab1);

        }
    }

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        msg2.setText(" ");
        switch (jTabbedPane1.getSelectedIndex()) {
            case 0: {
                // affichageListeVehiculePic(liste);
            }
            break;
            case 1: {
                if (client != null) {
                    try {
                        HashMap<Integer, Reservation> rv = stub.reservationsClient(client.getUsername());

                        affichageReservation(rv);
                    } catch (RemoteException ex) {
                        Logger.getLogger(ClientIHM.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            break;

        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void jPanel4ComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_jPanel4ComponentRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel4ComponentRemoved

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int row = listeR.getSelectedRow();
        //int code = (int) tableur.getValueAt(row, 0);
        // System.out.println(listv.getSelectedItem());

        //   if (listv.getSelectedItem() != null) {
        if (row != -1) {
            int codeReservation = (int) tableur1.getValueAt(row, 0);
            double prix = (double) tableur1.getValueAt(row, 4);
            try {
                int n = stub.annulerReservation(codeReservation, info);
                if (n == 1) {
                    JOptionPane.showMessageDialog(null, "Réservation annuler");
                    client.setSolde(client.getSolde() + prix - 1500);
                    solde.setText("" + client.getSolde());

                } else if (n == 2) {
                    JOptionPane.showMessageDialog(null, "Réservation terminer !");
                } else {
                    JOptionPane.showMessageDialog(null, "Réservations terminer avec penalité !");
                    client.setSolde(client.getSolde() - 1000);
                }
                tableur1.removeRow(row);

            } catch (RemoteException ex) {
                Logger.getLogger(ClientIHM.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            msg2.setText("Séléctionnez la réservation a annuler !");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jScrollPane4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane4MouseClicked

    }//GEN-LAST:event_jScrollPane4MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws NotBoundException, MalformedURLException, RemoteException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientIHM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                try {
                    new ClientIHM().setVisible(true);
                } catch (RemoteException ex) {
                    Logger.getLogger(ClientIHM.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup carburantgroup;
    private javax.swing.JTextArea commentaire;
    private javax.swing.JPanel container;
    private javax.swing.JMenuItem deconnexion;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable listeR;
    private javax.swing.JLabel msg11;
    private javax.swing.JLabel msg2;
    private javax.swing.JLabel msgcarburant;
    private javax.swing.JMenuItem msolde;
    private javax.swing.JLabel nom;
    private javax.swing.JComboBox<String> ordre;
    private javax.swing.JButton refresh;
    private javax.swing.JMenuItem seconnecter;
    private javax.swing.JMenuItem sinscrire;
    private javax.swing.JLabel solde;
    private javax.swing.JComboBox<String> tri;
    private javax.swing.ButtonGroup triType;
    private javax.swing.JButton trier;
    private javax.swing.ButtonGroup trierpar;
    // End of variables declaration//GEN-END:variables

    @Override
    public double seConnecter(Client client) throws RemoteException {
        return stub.connexion(client);
    }

    public HashMap<Integer, Reservation> listeReservation(Vehicule voiture) throws RemoteException {
        return stub.reservationsVoiture(voiture);
    }

    @Override
    public void visualiser(Vehicule voiture) throws RemoteException {

        try {
            new VisualiserIHM(this, voiture, client).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(ClientIHM.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    void connecte(Client client) throws RemoteException {
        this.client = client;
        solde.setText("" + client.getSolde());
        nom.setText(client.getUsername());
        seconnecter.setEnabled(false);
        sinscrire.setEnabled(false);
        deconnexion.setEnabled(true);
        msolde.setEnabled(true);
        info.setClient(client);
        stub.rejoindre(info);
    }

    @Override
    public void deconnexion() throws RemoteException {
        solde.setText("....");
        nom.setText(" ");
        this.client = null;
        seconnecter.setEnabled(true);
        sinscrire.setEnabled(true);
        msolde.setEnabled(false);
        deconnexion.setEnabled(false);
        stub.quitter(info);
    }

    @Override
    public boolean sinscrire(Client client) throws RemoteException {
        return stub.inscription(client);
    }

    public static Client getClient() {
        return client;
    }

    public static void setClient(Client client) {
        ClientIHM.client = client;
    }

    @Override
    public void inscriptionReussite(Client client) throws RemoteException {
        info.setClient(client);
        stub.clientInscrit(info);
    }

    public JLabel getSolde() {
        return solde;
    }

    public void setSolde(JLabel solde) {
        this.solde = solde;
    }

    @Override
    public boolean peutReserver(Reservation rv) throws RemoteException {
        return stub.peutReserver(info, rv);
    }
}
