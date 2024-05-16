package CodeTest;
import java.util.*;
import java.util.regex.Pattern;;

/**
 * Router {

  addRoute(path: String, result: String);
  
  route(path: String) : String;
  
}
Usage:
// setup routes
router.addRoute("/bar", "barFunc")
router.addRoute("/foo", "fooFunc")

// query for route
router.route("/bar") -> "barFunc"


 */
public class Router {

    public Map<String, String> routeMap;
    public Map<String, String> routeRegexMap;


    public Router(){
        routeMap = new HashMap<>();
        routeRegexMap = new HashMap<>();
    }

    public void addRoute(String path, String result){
        if (path.contains("*")){
            routeRegexMap.put(path, result);
        } else {
            routeMap.put(path, result);
        }
        return;
    }

    /**
     * router.route("/baz/123/baz") -> "bazFunc"
        router.route("/baz/456") -> "not found"
        router.addRoute("/baz/&/baz", "bazFunc
     */
    public String route(String path){ 

        for (String regreKey : routeRegexMap.keySet()) { 
            boolean isMatch = Pattern.matches(regreKey, path);
            if (isMatch){
                return routeRegexMap.get(regreKey);
            }
        }

        return routeMap.getOrDefault(path, ErrorMessage.errorWithNotExist);
    }

    public static void main(String[] args) {
        Router router = new Router();
        router.addRoute("/bar", "barFunc");
        router.addRoute("baz*baz", "testRegrefunc");
        router.addRoute("/foo", "fooFunc");
        System.out.println(router.route("/bar")); // return barFunc
        System.out.println(router.route("/notexist"));
        System.out.println(router.route("bazshimintestbaz"));
    }
}