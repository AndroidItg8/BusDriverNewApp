package itg8.com.busdriverapp.request;

import java.util.List;

import itg8.com.busdriverapp.request.model.Role;
import itg8.com.busdriverapp.request.model.User;

public interface RoleClickListner {
    void onRoleClicked(int position, Role role);
    void onUserClicked(int position, User user);

    void onUserChecked(List<User> roleList, String roleID);

    void onRoleChecked(List<Role> roleList);

    void onUserChecked(List<User> userList);

    void unCheckedOnClearButton();



    public interface OnCheckChangedClickedListener{
        void onCheckBoxClickedUser(boolean b, List<User> list);
        void onCheckBoxClickedRole(boolean b, List<Role> list);

        void onCheckedUpdateList(List<User> userList);
        void onCheckedUpdateList(List<User> b, List<Role> userList);
    }
}
