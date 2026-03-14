/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.NivoStudija;
import domen.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author DELL
 */
public class ModelTabeleStudenti extends AbstractTableModel {
    List<Student> lista;
    String[] kolone={"id","ime","prezime","email","nivo studija"};

    public ModelTabeleStudenti(List<Student> lista) {
        this.lista=lista;
    }

    public List<Student> getLista() {
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
        Student s=lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return s.getIdStudent();
            case 1:
                return s.getIme();
            case 2:
                return s.getPrezime();
            case 3:
                return s.getEmail();
            case 4:
                return s.getNivoStudija().getNazivNivoa();
            default:
                return "N/A";
                
        }
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

}
