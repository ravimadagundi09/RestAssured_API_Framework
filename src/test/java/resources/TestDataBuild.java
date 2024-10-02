package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String address, String language) {
		AddPlace a=new AddPlace();
		a.setAccuracy(50);
		a.setAddress(address);
		a.setLanguage(language);
		a.setName(name);
		a.setPhone_number("991763182");
		a.setWebsite("\"http://google.com");
		
		List<String> myList=new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		myList.add("shop2");
		
		a.setTypes(myList);
		
		Location l=new Location();
		l.setLat(38.383494);
		l.setLng(33.427362);
		
		a.setLocation(l);
		return a;
	}
	
	public String deletePlacePayload(String PlaceId) {
		return "{\r\n    \"place_id\":\""+PlaceId+"\"\r\n}";
	}

}
