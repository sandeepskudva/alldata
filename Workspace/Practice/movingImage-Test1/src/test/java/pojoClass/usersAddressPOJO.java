package pojoClass;


public class usersAddressPOJO {

private String street;
private String suite;
private String city;
private String zipcode;
private usersAddressGeoPOJO geo;

public String getStreet() {
return street;
}

public void setStreet(String street) {
this.street = street;
}

public String getSuite() {
return suite;
}

public void setSuite(String suite) {
this.suite = suite;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getZipcode() {
return zipcode;
}

public void setZipcode(String zipcode) {
this.zipcode = zipcode;
}

public usersAddressGeoPOJO getGeo() {
return geo;
}

public void setGeo(usersAddressGeoPOJO geo) {
this.geo = geo;
}

@Override
public String toString() {
	return "userAddressPOJO [street=" + street + ", suite=" + suite + ", city=" + city + ", zipcode=" + zipcode
			+ ", geo=" + geo + "]";
}

}
