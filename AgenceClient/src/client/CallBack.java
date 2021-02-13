package client;
import agencecommun.Notify;
import agencecommun.Client;
import agencecommun.Reservation;
import agencecommun.Vehicule;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this templatae file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Feriel Mokhtari
 */
public class CallBack extends UnicastRemoteObject implements Notify{
    JTextArea jt ;
    private Client client ;
    
    public CallBack(JTextArea jt) throws RemoteException 
    {
        this.jt= jt;
    }

    @Override
    public void infoReservation(Reservation rsv) throws RemoteException {
        try {
            jt.append(rsv.getClientUsername()+ " a reservé  "+rsv.getVoiture().getMarque()+" pour "
                    + "le "+rsv.getDateDe() +" a "+rsv.getDateA()+"\n");
        } catch (Exception e) {
            jt.append(" erreur !\n");
            //e.printStackTrace();
        }
    }
    @Override
    public void annoncePromotion(Vehicule voiture) {
       try {
            jt.append(voiture.getMarque()+ " est en promotion \n");
        } catch (Exception e) {
            jt.append(" erreur !\n");
            //e.printStackTrace();
        }
    }

    @Override
    public void messageConnexion(Client client) throws RemoteException {
         try {
            jt.append(client.getUsername() + "  est connecté(e) \n");
        } catch (Exception e) {
            jt.append(" Connecter vous !\n");
            //e.printStackTrace();
        }
    }

    @Override
    public Client getClient() throws RemoteException {
      return client ;
    }

    @Override
    public void setClient(Client client) throws RemoteException {
     this.client=client ;
    }

    @Override
    public void messageDeconnexion(Client client) throws RemoteException {
          try {
            jt.append(client.getUsername() + " a déconnecté\n");
        } catch (Exception e) {
            jt.append(" Connecter vous !\n");
            //e.printStackTrace();
        }
    }

    @Override
    public void messageInscription() throws RemoteException {
         try {
            jt.append("Inscription réussite !bienvenue "+client.getUsername() + ".\n");
        } catch (Exception e) {
            jt.append(" Connecter vous !\n");
            //e.printStackTrace();
        }
    }

    @Override
    public void reservationAnnuler(Reservation rv) throws RemoteException {
          try {
            jt.append(client.getUsername() + " votre reservation numéro "+rv.getCode()+" est annulée avec une pénalité de 1000Da \n");
        } catch (Exception e) {
            jt.append(" Connecter vous !\n");
            //e.printStackTrace();
        }
    }

    @Override
    public void reservationTerminerAvecPenalite(Reservation rv) throws RemoteException {
        try {
            jt.append(client.getUsername() + " votre reservation numéro "+rv.getCode()+" est terminé avec une pénalité de 1500Da \n");
        } catch (Exception e) {
            jt.append(" Connecter vous !\n");
            //e.printStackTrace();
        }}

    @Override
    public void reservationTerminer(Reservation rv) throws RemoteException {
        try {
            jt.append(client.getUsername() + " votre reservation numéro "+rv.getCode()+" est terminée \n");
        } catch (Exception e) {
            jt.append(" Connecter vous !\n");
            //e.printStackTrace();
        } }
    
}
