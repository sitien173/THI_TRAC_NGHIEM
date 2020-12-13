
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    private String dapanA;
    private String dapanB;
    private String dapanC;
    private String dapanD;
    private javax.swing.JRadioButton dapAnUser = new javax.swing.JRadioButton();
    private static final long serialVersionUID = -6849794470754667710L;
    
    
    public CAUHOI() {
    }

    public CAUHOI(int id_wh, String cauhoi, String dapAn1, String dapAn2, String dapAn3, String dapAn4) {
        this.cauhoi = cauhoi;
        this.id_wh = id_wh;
        this.dapanA = dapAn1;
        this.dapanB = dapAn2;
        this.dapanC = dapAn3;
        this.dapanD = dapAn4;
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

    public String getDapanA() {
        return dapanA;
    }

    public String getDapanB() {
        return dapanB;
    }

    public String getDapanC() {
        return dapanC;
    }

    public String getDapanD() {
        return dapanD;
    }

    public void setCauhoi(String cauhoi) {
        this.cauhoi = cauhoi;
    }

    public void setDapanA(String dapanA) {
        this.dapanA = dapanA;
    }

    public void setDapanB(String dapanB) {
        this.dapanB = dapanB;
    }

    public void setDapanC(String dapanC) {
        this.dapanC = dapanC;
    }

    public void setDapanD(String dapanD) {
        this.dapanD = dapanD;
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
    public void add(CAUHOI ch) {
        list.add(ch);
    }
    
    public ArrayList<CAUHOI> docFileWh(File file) {
        ArrayList<CAUHOI> listCauHoi = new ArrayList<>();
        try {
            //  DataOutputStream DIS = new DataOutputStream(new BufferedOutputStream(new FileOutputStream("cauhoi.txt")));
            CAUHOI wh = null;
            Scanner sc = new Scanner(new FileInputStream(file), "UTF-8");
            while (sc.hasNext()) {
               wh = new CAUHOI();
               wh.setId_wh(Integer.parseInt(sc.next().replace('.', ' ').trim()));
               wh.setCauhoi(sc.nextLine());
               wh.setDapanA(sc.nextLine());
               wh.setDapanB(sc.nextLine());
               wh.setDapanC(sc.nextLine());
               wh.setDapanD(sc.nextLine());
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
    public void ghiFile(File file){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("database/".concat(file.toString())));
            int length = list.size();
            for(int i=0;i<length;i++){
                list.get(i).setId_wh(i+1);
                bw.write(String.valueOf(i+1));
                bw.write(".");
                bw.write(" ");
                bw.write(list.get(i).getCauhoi() + '\n');
                bw.write(list.get(i).getDapanA() + '\n');
                bw.write(list.get(i).getDapanB() + '\n');
                bw.write(list.get(i).getDapanC() + '\n');
                bw.write(list.get(i).getDapanD() + '\n');
            }
            bw.close();
        } catch (Exception e) {
        }
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
    
    public void add(DAPAN da) {
        if(this.Search(da.getIdAns()) != -1){
            return;
        }
    }
    public void docFileAns(File file){
        DAPAN ans = null;
        try {
            Scanner sc = new Scanner(new FileInputStream(file), "UTF-8");
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
    public void ghiFile(File file){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("database/".concat(file.toString())));
            int length = listDapAn.size();
            for(int i=0;i<length;i++){
                listDapAn.get(i).setIdAns(i+1);
                bw.write(String.valueOf(i+1));
                bw.write(" ");
                bw.write(listDapAn.get(i).getDapAnDung());
                bw.write('\n');
            }
            bw.close();
        } catch (Exception e) {
        }
    }
}
