import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;
import java.util.Scanner;

public class Demo{
	static int x;
	public static void printMenu(String[] Options){
		x = 0;
		for (String s: Options){
			System.out.println(x + ": " + s);
			x++;
		}	
		System.out.print("Choose your option: ");
	}

/* 	public static <T> T cast(Gson g, String content){
		T rp = g.fromJson(content, T);
		return rp;
	}  */

	public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException, Exception {
		Scanner myObj = new Scanner(System.in);
		String URL_base, URL_String;
		String option_string;
		int option;
		while (true){
			System.out.print("Choose your base URL: " );
			URL_base = myObj.nextLine();
			if (URL_base.isEmpty()) URL_base = "https://auctions-app-2.herokuapp.com/api";
			else continue;

			System.out.println("Your base URL is: https://auctions-app-2.herokuapp.com/api");
			printMenu(Options.Options);
			System.out.print("Choose API: ");
			
			option_string = myObj.nextLine();
			option = Integer.parseInt(option_string);
			API_List API_list = new API_List();
			URL_String = URL_base.concat(API_list.API[option]);
			System.out.println("ULR: " + URL_String);

				//connect to URL API
			URL url = new URL(URL_String);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();			
			if ((option >= 0 && option <=3) || (option >= 9 && option <= 12) || (option >= 18 && option <= 20) || 
				option ==24 || option == 26 || option == 29) 
					connection.setRequestMethod("POST");			
			else connection.setRequestMethod("GET");
	
				//Map key-value
			Map<String, String> params = new HashMap<>();

			switch(option){
				case 0: {
					System.out.println("Enter your email: ");
					String email = myObj.nextLine();  
					System.out.println("Enter your password: ");
					String password = myObj.nextLine();
					params.put("email", email);
					params.put("password", password);
				}
			}

			//create stringbuilder object, eg: "email=khanh.hn200315@sis.hust.edi.vn&password=140602"
			StringBuilder postData = new StringBuilder();
			for (Map.Entry<String, String> param : params.entrySet()) {
				if (postData.length() != 0) {
					postData.append('&');
				}
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));           //encode 
				postData.append('=');
				postData.append(URLEncoder.encode(param.getValue(), "UTF-8"));
			}
	
			//byte-array of postData: postaDataBytes
			byte[] postDataBytes = postData.toString().getBytes("UTF-8");
			
			connection.setDoOutput(true);        //GET, PUT, send a request body
			try (DataOutputStream writer = new DataOutputStream(connection.getOutputStream())){
				writer.write(postDataBytes);
				// writer.flush();
				writer.close();
	
				StringBuilder content = new StringBuilder();
	
				try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
					String line;
					while ((line = in.readLine()) != null) {
						content.append(line);
						content.append(System.lineSeparator());
					}
				}
				System.out.println(content);

				//Gson
				Gson g = new Gson(); 
				ClassLogin rp = g.fromJson(content.toString(), ClassLogin.class);
				// ClassLogin rp = cast(g, content.toString(), ClassLogin.class);

				System.out.println("Unit test 1: The code and message strings shall be not NULL as well as non-empty:");
				assert(rp.code != null && !"".equals(rp.code)): "code is NULL or Empty" ;
				assert(rp.message != null && !"".equals(rp.message)): "message is NULL or Empty";
				System.out.println("Finished! Satisfied!");
			}finally {
	        	connection.disconnect();
			}
	    }

	} 
}

class API_List {
	public String[] API = {
	"/login",
	"/signup",
	"/edit",
	"/search",
	"/logout",
	"/auctions",
	"/auctions/listAuctionsByStatus/{statusId}",
	"/auctions/listAuctionByUser",
	"/auctions/auctions/listAuctions/{typeId}",
	"/auctions/detail/{auctionId}",
	"/auctions/create",
	"/auctions/edit/{auctionId}",
	"/items/create/{auctionId}"
	};
}

class Options {
	public static String[] Options = {
		"login",
		"sign_up",
		"edit_account",
		"logout",
		"get_list_auctions",
		"get_list_auctions_by_status",
		"get_list_auctions_by_user",
		"get_list_auctions_by_type",
		"get_detail_auction",
		"create_auction",
		"edit_auction",
		"create_item",
		"create_comment",
		"get_list_comments",
		"create_bid",
		"get_list_bids",
		"get_list_categoires",
		"get_list_brands",
		"accept_max_bid",
		"contact_us",
		"like_auction",
		"get_list_likes",
		"total_likes_of_auction",
		"get_news",
		"read_new",
		"get_notifications",
		"read_notifications",
		"get_slider",
		"search",
		"delete_comment",
	};
}


class ResponseSignUp{
	public String code;
	public String message;
}

class ClassLogin {
    public String code;
    public String message;
    public Data data;
}	class Data{
		public String access_token;
		public User user;
		public String token_type;
		public Integer expire_in;
		public Long exp;
	}	class User{
			public String name;
			public String role;
			public String avatar;
			public String email;
			public String address;
			public String phone;
			public String user_id;
		}
