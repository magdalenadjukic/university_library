/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaIznajmljivanja;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class ModelTabeleStavkaIznajmljivanja extends AbstractTableModel {
    List<StavkaIznajmljivanja> lista;
    String[] kolone = {"RB","Knjiga","Rok vracanja","Datum vracanja","Penali"};
    //zbog izmene datuma vracanja
    private int editabilniRed=-1;
    
    public void setEditabilniRed(int red) {
    this.editabilniRed = red;
    fireTableDataChanged();
}
    public ModelTabeleStavkaIznajmljivanja() {
        lista = new ArrayList<>();
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIznajmljivanja si = lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return si.getRb();
            case 1:
                return si.getKnjiga().getNazivKnjige();
            case 2:
                if(si.getRokVracanja()!=null){
                     return sdf.format(si.getRokVracanja());
                }
                else
                    return "/";
            case 3:
               if (si.getDatumVracanja() != null) {
               return sdf.format(si.getDatumVracanja());
               } else {
                return "/"; 
               }
            case 4:
                return Math.round(si.getPenali());
            
            default:
                return "NA";
        }
    }
    //dve metode za editovanje polja, tj datuma vracanja
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
    // Samo kolona Datum vracanja i samo sel red
        return rowIndex == editabilniRed && columnIndex == 3;
    }

    //izmena
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    if (columnIndex == 3) {
        StavkaIznajmljivanja si = lista.get(rowIndex);
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
            if (aValue == null || aValue.toString().equals("/") || aValue.toString().isEmpty()) {
                si.setDatumVracanja(null);
               
                if (si.getRokVracanja() != null && si.getKnjiga() != null) {
                long diffMs = new java.util.Date().getTime() - si.getRokVracanja().getTime();
                long danaKasnjenja = diffMs / (1000L * 60 * 60 * 24);
                if (danaKasnjenja > 0) {
                double penali = danaKasnjenja * 0.05 * si.getKnjiga().getCena();
                si.setPenali(Math.min(penali, si.getKnjiga().getCena()));
                } else {
                si.setPenali(0);
               }
             }
            } else {
                si.setDatumVracanja(sdf.parse(aValue.toString()));
               // System.out.println("setValueAt - datumVracanja: " + si.getDatumVracanja());

                if (si.getRokVracanja() != null && si.getKnjiga() != null) {
                    //racunanje razlike u danima
                    long diffMs = si.getDatumVracanja().getTime() - si.getRokVracanja().getTime();
                    long danaKasnjenja = diffMs / (1000L * 60 * 60 * 24);
                    if (danaKasnjenja > 0) {
                        double penali = danaKasnjenja * 0.05 * si.getKnjiga().getCena();
                        si.setPenali(Math.min(penali, si.getKnjiga().getCena()));
                    } else {
                        si.setPenali(0);
                    }
                }
            }
            fireTableCellUpdated(rowIndex, 3);
            fireTableCellUpdated(rowIndex, 4); // osvezi kolonu penali
        } catch (Exception e) {
            System.out.println("nevalidan unos datuma vracanja");
        }
    }
}
    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    public void setLista(List<StavkaIznajmljivanja> lista) {
        
        this.lista = lista;
        // pri ucitavanju, izracunaj penale za knjige koje nisu vracene a rok je prosao
        for (StavkaIznajmljivanja si : this.lista) {
        if (si.getDatumVracanja() == null && si.getRokVracanja() != null && si.getKnjiga() != null) {
            long diffMs = new java.util.Date().getTime() - si.getRokVracanja().getTime();
            long danaKasnjenja = diffMs / (1000L * 60 * 60 * 24);
            if (danaKasnjenja > 0) {
                 double penali = danaKasnjenja * 0.05 * si.getKnjiga().getCena();
                 si.setPenali(Math.min(penali, si.getKnjiga().getCena())); 
            }
        }
        }
         fireTableDataChanged();
    }

    public void dodajStavku(StavkaIznajmljivanja si) {
        lista.add(si);
        osveziRedneBrojeve();
        fireTableDataChanged();
    }

    public void obrisiStavku(int red) {
        lista.remove(red);
        osveziRedneBrojeve();
        fireTableDataChanged();
    }

    public void dodajStavke(List<StavkaIznajmljivanja> stavke) {
        for (StavkaIznajmljivanja si : stavke) {
            lista.add(si);
        }
        osveziRedneBrojeve();
        fireTableDataChanged();
    }
    
    private void osveziRedneBrojeve() {
        int rb = 1;
        for (StavkaIznajmljivanja si : lista) {
            si.setRb(rb++);
        }
    }
}
