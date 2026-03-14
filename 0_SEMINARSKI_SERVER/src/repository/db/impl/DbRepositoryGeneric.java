/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domen.ApstraktniDomenskiObjekat;
import java.util.ArrayList;
import java.util.List;
import repository.db.DBRepository;
import java.sql.*;
import repository.db.DbConnectionFactory;
/**
 *
 * @author DELL
 */
public class DbRepositoryGeneric implements DBRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param,String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        String upit="SELECT * FROM "+param.vratiNazivTabele();
        if(uslov!=null){
            upit+=uslov;
        }
        System.out.println(upit);
        Statement st=DbConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs=st.executeQuery(upit);
        lista=param.vratiListu(rs);
        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
       
        String  upit="INSERT INTO "+param.vratiNazivTabele()+" ( "+param.vratiKoloneZaUbacivanje()+" ) VALUES ( "+param.vratiVrednostiZaUbacivanje()+" )";
        
        System.out.println(upit);
        Statement st=DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
        
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.vratiNazivTabele() + " SET " + param.vratiVrednostiZaIzmenu()+ " WHERE " + param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st=DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit="DELETE FROM "+param.vratiNazivTabele()+" WHERE "+param.vratiPrimarniKljuc();
        System.out.println(upit);
        Statement st=DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();

        return lista;
    }

    @Override
    public int create(ApstraktniDomenskiObjekat param) throws Exception {
    //prima praznog studenta, vraca id
    int genId;

    String upit = "INSERT INTO "+param.vratiNazivTabele()+" () VALUES ()";
    System.out.println("sql create: " + upit);

    try (PreparedStatement ps = DbConnectionFactory.getInstance()
            .getConnection()
            .prepareStatement(upit, Statement.RETURN_GENERATED_KEYS)) {

        ps.executeUpdate();

        try (ResultSet rs = ps.getGeneratedKeys()) {
            if (rs.next()) {
                genId = rs.getInt(1);
                System.out.println("Generisani ID: " + genId);
                return genId;
            } else {
                throw new SQLException("Sistem nije vratio generisani kljuc.");
            }
        }
    }

    }

    
    
    
}
