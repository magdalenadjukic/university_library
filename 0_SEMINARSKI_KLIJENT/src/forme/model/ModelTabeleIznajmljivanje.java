/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;


import domen.Bibliotekar;
import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.Student;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel {
    List<Iznajmljivanje> lista;
    String[] kolone={"ID","Datum iznajmljivanja","Ukupan dug","Bibliotekar","Student"};

    public ModelTabeleIznajmljivanje(List<Iznajmljivanje> lista) {
        this.lista=lista;
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        Iznajmljivanje izn=lista.get(rowIndex);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch (columnIndex) {
            case 0:
                return izn.getIdIznajmljivanje();
            case 1:
               return sdf.format(izn.getDatumIznajmljivanja());
            case 2:
                return Math.round(izn.getUkupanDug());
            case 3:
                return izn.getBibliotekar().getIme()+" "+izn.getBibliotekar().getPrezime();
            case 4:
                return izn.getStudent().getIme()+" "+izn.getStudent().getPrezime();
            default:
                return "N/A";
                
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    
}
