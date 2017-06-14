// 做任务六碰到的一些有意思的代码
//（1）服务端提供 JSON 数据接口：http://192.168.0.129:8080/JSONInterface/JsonServlet
package JsonManager;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
/**
 * JSONObject 创建一个 JSON 对象并 out.write();
 * @author Dana·Li
 */
public class JsonServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");      // 解决中文乱码问题

        PrintWriter out = response.getWriter();
        Map map = new HashMap(); 

        map.put("name", "Dana、Li"); 
        map.put("age", new Integer(22)); 
        map.put("Provinces", new String("广东省")); 
        map.put("citiy", new String("珠海市")); 
        map.put("Master", new String("C、C++、Linux、Java"));
        JSONObject json = JSONObject.fromObject(map); 
        
        out.write(json.toString());
        out.flush();
        out.close();
    }

}
//(2) 客户端调用接口解析 JSON 数据
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
/**
 * 接收服务端 Json 数据
 * @author Dana·Li
 */
public class GetJsonInterfaceInfo{
    private static String urlPath="http://192.168.0.129:8080/JSONInterface/JsonServlet";
    /**
     * @param args
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        //ServerFactory.getServer(8080).start();
        // 列出原始数据
        StringBuilder json = new StringBuilder();   
        
        
        URL oracle = new URL(GetJsonInterfaceInfo.urlPath); 
        URLConnection yc = oracle.openConnection(); 
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream(),"UTF-8")); 
        String inputLine = null; 
        while ((inputLine = in.readLine()) != null){ 
            json.append(inputLine); 
        } 
        in.close(); 
        String Strjson=json.toString();
        System.out.println("原始数据:");
        System.out.println(Strjson.toString()); 
    }

}
