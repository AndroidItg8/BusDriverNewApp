package itg8.com.busdriverapp.home;

import org.json.JSONException;

import itg8.com.busdriverapp.common.BaseActivity;
import itg8.com.busdriverapp.home.model.CheckpointData;

/**
 * Created by swapnilmeshram on 17/03/18.
 */

public interface HomeFragmentInteractor {
    void onAttach(String tag);
    void onStartMapFragment(String children, String halt, String skip, CheckpointData mCheckpointData) throws JSONException;
    void onDetach(String tag);
    void startLocationUpdate(BaseActivity.OnLocationUpdateListener listener);
    void startDownloadingAddressForMap(CheckpointData mRouteModel);

    void stopLocationUpdate();
}
