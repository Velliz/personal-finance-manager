package edu.maranatha.pbol.model.pojo;
// Generated May 12, 2016 9:02:47 AM by Hibernate Tools 3.6.0


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Agenda generated by hbm2java
 */
@Entity
@Table(name="agenda"
    ,catalog="moneymanager"
)
public class Agenda  implements java.io.Serializable {


     private Integer idagenda;
     private User user;
     private int nominalanggaran;
     private Date tanggal;
     private String keterangan;
     private boolean otoritas;

    public Agenda() {
    }

    public Agenda(User user, int nominalanggaran, Date tanggal, String keterangan, boolean otoritas) {
       this.user = user;
       this.nominalanggaran = nominalanggaran;
       this.tanggal = tanggal;
       this.keterangan = keterangan;
       this.otoritas = otoritas;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="idagenda", unique=true, nullable=false)
    public Integer getIdagenda() {
        return this.idagenda;
    }
    
    public void setIdagenda(Integer idagenda) {
        this.idagenda = idagenda;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="iduser", nullable=false)
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    
    @Column(name="nominalanggaran", nullable=false)
    public int getNominalanggaran() {
        return this.nominalanggaran;
    }
    
    public void setNominalanggaran(int nominalanggaran) {
        this.nominalanggaran = nominalanggaran;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="tanggal", nullable=false, length=19)
    public Date getTanggal() {
        return this.tanggal;
    }
    
    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    
    @Column(name="keterangan", nullable=false, length=65535)
    public String getKeterangan() {
        return this.keterangan;
    }
    
    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    
    @Column(name="otoritas", nullable=false)
    public boolean isOtoritas() {
        return this.otoritas;
    }
    
    public void setOtoritas(boolean otoritas) {
        this.otoritas = otoritas;
    }




}


