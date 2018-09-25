package itg8.com.busdriverapp.map;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by swapnilmeshram on 24/03/18.
 */

public class MapLatLngAddressModel {
    LatLng latLng;
    String placeAddress;

    public MapLatLngAddressModel(LatLng latLng, String placeAddress) {
        this.latLng = latLng;
        this.placeAddress = placeAddress;
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }
}
