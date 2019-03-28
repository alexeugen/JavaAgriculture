package ro.unibuc.fmi.pao.project.classes;

public class Location {
    public String city;
    public String address;
    public String surface;

    public Location(String city) {
        this.city = city;
    }

    public Location(String city, String address, String surface) {
        this.city = city;
        this.address = address;
        this.surface = surface;
    }

//    Getters
    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getSurface() {
        return surface;
    }

//    Setters
    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }
}
