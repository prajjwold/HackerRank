package hackerrank.test.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Hello world!
 *
 */
public class JsonHttpGet {
	public static void main(String[] args) {
		try {
			int value = getCountries("un", 1000);

			System.out.println("The number of countries:" + value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static int getCountries(String str, int p) throws Exception {
		String urlString = "http://restcountries.eu/rest/v1/name/" + str;
		URL url = new URL(urlString);

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("GET");

		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		StringBuilder strBuilder = new StringBuilder();
		String line;
		while ((line = in.readLine()) != null) {
			strBuilder.append(line);
		}
		in.close();

		String jsonResponse = strBuilder.toString();
		System.out.println(jsonResponse);

		JSONArray jsonArray = new JSONArray(jsonResponse);

		int value = 0;
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			String countryName = (String) jsonObject.get("name");
			int population = (Integer) jsonObject.get("population");

			if (countryName.contains(str) && population > p) {
				value++;
			}
		}

		return value;

	}
}
