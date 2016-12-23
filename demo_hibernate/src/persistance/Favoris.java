package persistance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by chebaane on 23/12/2016.
 */
@Entity
@IdClass(FavorisPK.class)
public class Favoris {
    private String username;
    private String bssid;

    @Id
    @Column(name = "Username", nullable = false, length = 255)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Id
    @Column(name = "BSSID", nullable = false, length = 255)
    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favoris favoris = (Favoris) o;

        if (username != null ? !username.equals(favoris.username) : favoris.username != null) return false;
        if (bssid != null ? !bssid.equals(favoris.bssid) : favoris.bssid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (bssid != null ? bssid.hashCode() : 0);
        return result;
    }
}
