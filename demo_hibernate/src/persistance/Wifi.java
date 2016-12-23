package persistance;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by chebaane on 23/12/2016.
 */
@Entity
public class Wifi {
    private String bssid;
    private String ssid;
    private String security;
    private int level;
    private int ratting;
    private double posLatitude;
    private double posLongitude;

    @Id
    @Column(name = "BSSID", nullable = false, length = 255)
    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    @Basic
    @Column(name = "SSID", nullable = false, length = 255)
    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    @Basic
    @Column(name = "Security", nullable = false, length = 255)
    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    @Basic
    @Column(name = "Level", nullable = false)
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Basic
    @Column(name = "Ratting", nullable = false)
    public int getRatting() {
        return ratting;
    }

    public void setRatting(int ratting) {
        this.ratting = ratting;
    }

    @Basic
    @Column(name = "Pos_latitude", nullable = false, precision = 0)
    public double getPosLatitude() {
        return posLatitude;
    }

    public void setPosLatitude(double posLatitude) {
        this.posLatitude = posLatitude;
    }

    @Basic
    @Column(name = "Pos_longitude", nullable = false, precision = 0)
    public double getPosLongitude() {
        return posLongitude;
    }

    public void setPosLongitude(double posLongitude) {
        this.posLongitude = posLongitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wifi wifi = (Wifi) o;

        if (level != wifi.level) return false;
        if (ratting != wifi.ratting) return false;
        if (Double.compare(wifi.posLatitude, posLatitude) != 0) return false;
        if (Double.compare(wifi.posLongitude, posLongitude) != 0) return false;
        if (bssid != null ? !bssid.equals(wifi.bssid) : wifi.bssid != null) return false;
        if (ssid != null ? !ssid.equals(wifi.ssid) : wifi.ssid != null) return false;
        if (security != null ? !security.equals(wifi.security) : wifi.security != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = bssid != null ? bssid.hashCode() : 0;
        result = 31 * result + (ssid != null ? ssid.hashCode() : 0);
        result = 31 * result + (security != null ? security.hashCode() : 0);
        result = 31 * result + level;
        result = 31 * result + ratting;
        temp = Double.doubleToLongBits(posLatitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(posLongitude);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
