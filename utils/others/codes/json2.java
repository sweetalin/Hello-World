//http 传输 json 进行服务端接口与客户端对接，以及 restful 实现
//服务端接口编写，我用的是 springmvc, 服务端接口其实和平时 web 开发一样，就是返回出 json 就好了，还有就是接受数据也是 json，方法如下：

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userservice;
	@RequestMapping("/getUserByName")
	public @ResponseBody User getUserByName(HttpServletRequest request) throws IOException{
		StringBuffer str = new StringBuffer(); 
		try {
			   BufferedInputStream in = new BufferedInputStream(request.getInputStream());
			      int i;
			      char c;
			      while ((i=in.read())!=-1) {
			      c=(char)i;
			      str.append(c);
			      }
			     }catch (Exception ex) {
			   ex.printStackTrace();
			   }
        JSONObject obj= JSONObject.fromObject(str.toString());
        System.out.println(obj.get("name"));
		User user= userservice.getUserByName(obj.get("name").toString());
		return user;
	}
	
}
//实现的是一个通过服务端接收客户端 json{“name”:"cwh"} 名字 name 进行查询操作，然后给客户端返回 json;

//客户端实现如下：

import java.io.IOException;

import net.sf.json.JSONObject;
import net.spring.model.User;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class Client {
	@Test
	public void HttpPostData() {  
	      try { 
	          HttpClient httpclient = new DefaultHttpClient();  
	          String uri = "http://localhost:8080/springMVC/user/getUserByName"; 
	          HttpPost httppost = new HttpPost(uri);   
	          //添加http头信息 
	          httppost.addHeader("Authorization", "your token"); //认证token 
	          httppost.addHeader("Content-Type", "application/json"); 
	          httppost.addHeader("User-Agent", "imgfornote");  
	          JSONObject obj = new JSONObject();
	          obj.put("name", "cwh"); 
	          httppost.setEntity(new StringEntity(obj.toString()));     
	          HttpResponse response;  
	          response = httpclient.execute(httppost);  
	          //检验状态码，如果成功接收数据  
	          int code = response.getStatusLine().getStatusCode();  
	          System.out.println(code+"code");
	          if (code == 200) {  
	              String rev = EntityUtils.toString(response.getEntity());//返回json格式： {"id": "","name": ""}         
	              obj= JSONObject.fromObject(rev);
	          
	              User user = (User)JSONObject.toBean(obj,User.class);
	              System.out.println("返回数据==="+user.toString());
	          } 
	          } catch (ClientProtocolException e) { 
	        	  e.printStackTrace();
	          } catch (IOException e) {  
	        	  e.printStackTrace();
	          } catch (Exception e) { 
	        	  e.printStackTrace();
	          } 
	     }
}

//当然也可以用 RESTFUL 风格来实现

//服务端可以这么编写：

@RequestMapping("/getUserByName/{name}")
	public @ResponseBody User getUserByName(@PathVariable("name")String name) throws IOException{
		User user= userservice.getUserByName(name);
		return user;
	}
//那么客户端请求就这么写了：
public void HttpPostData() {  
	      try { 
	          HttpClient httpclient = new DefaultHttpClient();  
	          String uri = "http://localhost:8080/springMVC/user/getUserByName/cwh"; 
	          HttpPost httppost = new HttpPost(uri);   
	          JSONObject obj = new JSONObject();
	          
	          HttpResponse response;  
	          response = httpclient.execute(httppost);  
	          //检验状态码，如果成功接收数据  
	          int code = response.getStatusLine().getStatusCode();  
	          System.out.println(code+"code");
	          if (code == 200) {  
	              String rev = EntityUtils.toString(response.getEntity());//返回json格式： {"id": "","name": ""}         
	              obj= JSONObject.fromObject(rev);
	              User user = (User)JSONObject.toBean(obj,User.class);
	              System.out.println("返回数据==="+user.toString());
	          } 
	          } catch (ClientProtocolException e) { 
	        	  e.printStackTrace();
	          } catch (IOException e) {  
	        	  e.printStackTrace();
	          } catch (Exception e) { 
	        	  e.printStackTrace();
	          } 
	     }
