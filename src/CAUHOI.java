
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JRadioButton;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ngosi
 */
class CAUHOI implements Serializable {
    private int id_wh;
    private String cauhoi;
    private String dapAn1;
    private String dapAn2;
    private String dapAn3;
    private String dapAn4;
    private javax.swing.JRadioButton dapAnUser = new javax.swing.JRadioButton();
    private static final long serialVersionUID = -6849794470754667710L;
    
    
    public CAUHOI() {
    }

    public CAUHOI(int id_wh, String cauhoi, String dapAn1, String dapAn2, String dapAn3, String dapAn4) {
        this.cauhoi = cauhoi;
        this.id_wh = id_wh;
        this.dapAn1 = dapAn1;
        this.dapAn2 = dapAn2;
        this.dapAn3 = dapAn3;
        this.dapAn4 = dapAn4;
    }

   public JRadioButton getDapAnUser() {
        return dapAnUser;
    }

    public void setDapAnUser(JRadioButton dapAnUser) {
        this.dapAnUser = dapAnUser;
    }
    public int getId_wh() {
        return id_wh;
    }

    public void setId_wh(int id_wh) {
        this.id_wh = id_wh;
    }
    
    public String getCauhoi() {
        return cauhoi;
    }

    public String getDapAn1() {
        return dapAn1;
    }

    public String getDapAn2() {
        return dapAn2;
    }

    public String getDapAn3() {
        return dapAn3;
    }

    public String getDapAn4() {
        return dapAn4;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public void setDapAn1(String dapAn1) {
        this.dapAn1 = dapAn1;
    }

    public void setDapAn2(String dapAn2) {
        this.dapAn2 = dapAn2;
    }

    public void setDapAn3(String dapAn3) {
        this.dapAn3 = dapAn3;
    }

    public void setDapAn4(String dapAn4) {
        this.dapAn4 = dapAn4;
    }

}

class DAPAN implements Serializable{
    private int idAns;
    private String dapAnDung;
    private static final long serialVersionUID = -6849794470754667710L;
    public DAPAN() {
    }

    
    public DAPAN(int idAns, String dapAnDung) {
        this.idAns = idAns;
        this.dapAnDung = dapAnDung;
    }

    public int getIdAns() {
        return idAns;
    }

    public void setIdAns(int idAns) {
        this.idAns = idAns;
    }

    public String getDapAnDung() {
        return dapAnDung;
    }

    public void setDapAnDung(String dapAnDung) {
        this.dapAnDung = dapAnDung;
    }
    
}  
class DSCAUHOI {

    public ArrayList<CAUHOI> list;

    public DSCAUHOI() {
        list = new ArrayList<>();
    }

    public ArrayList<CAUHOI> docFileWh() {
        ArrayList<CAUHOI> listCauHoi = new ArrayList<>();
        try {
            //  DataOutputStream DIS = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("cauhoi.txt")));
            CAUHOI wh = null;
            Scanner sc = new Scanner(new FileInputStream("wh.txt"), "UTF-8");
            while (sc.hasNext()) {
               wh = new CAUHOI();
               wh.setId_wh(Integer.parseInt(sc.next().replace('.', ' ').trim()));
               wh.setCauhoi(sc.nextLine());
               wh.setDapAn1(sc.nextLine());
               wh.setDapAn2(sc.nextLine());
               wh.setDapAn3(sc.nextLine());
               wh.setDapAn4(sc.nextLine());
               list.add(wh);
            }
            Collections.shuffle(list);
            
            for(int i=0;i<20;i++){
                listCauHoi.add(list.get(i));
            }
            sc.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DSCAUHOI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listCauHoi;
    }

}
class DSDAPAN{
    private ArrayList<DAPAN> listDapAn;

    public DSDAPAN() {
        listDapAn = new ArrayList<>();
    }

    public ArrayList<DAPAN> getListDapAn() {
        return listDapAn;
    }
    
    public void docFileAns(){
        DAPAN ans = null;
        try {
            Scanner sc = new Scanner(new FileInputStream("ans.txt"), "UTF-8");
            while (sc.hasNext()) {
               ans = new DAPAN();
               ans.setIdAns(Integer.parseInt(sc.next()));
               ans.setDapAnDung(sc.next());
               listDapAn.add(ans);
            }
            sc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
       
    }
    public int Search(int idAns){
        int length = listDapAn.size();
        for (int i = 0; i < length; i++) {
            if(listDapAn.get(i).getIdAns() == idAns ){
                return i;
            }
        }
        return -1;
    }
    
}
