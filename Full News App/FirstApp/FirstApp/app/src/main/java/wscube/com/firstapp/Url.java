package wscube.com.firstapp;

/**
 * Created by wscube on 30/12/16.
 */
public class Url {
    public static final String BASE_URL = "http://wscubetechapps.in/mobileteam/friend_and_craft/api/";
    public static final String IMAGE_URL = "http://wscubetechapps.in/mobileteam/friend_and_craft/uploads/";

    public static final String REGISTRATION = BASE_URL + "registration.php?";
    //user_name=&user_email=&user_password=

    public static final String LOGIN = BASE_URL + "login.php?";
    //user_email=&user_password=

    public static final String VIEW_PROFILE = BASE_URL + "profile.php?";
    //user_id=

    public static final String PATTERN = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static final String IMAGEURL="http://www.wscubetechapps.in/mobileteam/automation_compare/api/view_news.php";

    public static final String PRE_IMG_URL="http://www.wscubetechapps.in/mobileteam/automation_compare/";

    public static final String OKHTTP_GET_URL="https://api.github.com/users/codepath";

    public static final String OKHTTP_POST_URL="http://vegforsure.com/api/order_detail.php";
}
