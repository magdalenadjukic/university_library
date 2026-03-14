/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacija;

import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.DbRepositoryGeneric;

/**
 *
 * @author DELL
 */
public abstract class ApstraktnaGenerickaOperacija {
    
    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this.broker = new DbRepositoryGeneric();
    }
    public final void izvrsi(Object obj,String kljuc) throws Exception{
        try {
            preduslovi(obj);
            zapocniTransakciju();
            izvrsiOperaciju(obj,kljuc);
            potvrdiTransakciju();
        } catch (Exception e) {
            ponistiTransakciju();
            throw e;
        } finally {
           // ugasiKonekciju();
        }
    }

    protected abstract void preduslovi(Object obj) throws Exception;
    protected abstract void izvrsiOperaciju(Object obj,String kljuc) throws Exception;
  
    
    private void zapocniTransakciju() throws Exception {
        ((DBRepository) broker).connect();
    }

    private void potvrdiTransakciju() throws Exception {
        ((DBRepository) broker).commit();
    }

    private void ponistiTransakciju() throws Exception {
        ((DBRepository) broker).rollback();
    }

    private void ugasiKonekciju() throws Exception {
        ((DBRepository) broker).disconnect();
    }
    
}
