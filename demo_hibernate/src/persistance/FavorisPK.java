package persistance;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by chebaane on 23/12/2016.
 */
public class FavorisPK implements Serializable {
    private String username;
    private String bssid;

    @Column(name = "Username", nullable = false, length = 255)
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(name = "BSSID", nullable = false, length = 255)
    @Id
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

        FavorisPK favorisPK = (FavorisPK) o;

        if (username != null ? !username.equals(favorisPK.username) : favorisPK.username != null) return false;
        if (bssid != null ? !bssid.equals(favorisPK.bssid) : favorisPK.bssid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (bssid != null ? bssid.hashCode() : 0);
        return result;
    }
}
