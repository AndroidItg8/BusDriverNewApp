package itg8.com.busdriverapp.common;

/**
 * Created by swapnilmeshram on 15/03/18.
 */

public interface BaseView {
    void onNoInternet();
    void showProgress();
    void hideProgress();
    void onRetroError(Object obj);
}
