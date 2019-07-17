package hibernate.lesson4.model;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;

public class Filter {
    private Integer numberOfGuests;
    private Double price;
    private Boolean breakfastIncluded;
    private Boolean petsAllowed;
    private Date dateAvailableFrom;
    private String country;
    private String city;

    public Filter(Integer numberOfGuests, Double price, Boolean breakfastIncluded,
                  Boolean petsAllowed, Date dateAvailableFrom, String country, String city) {

        this.numberOfGuests = numberOfGuests;
        this.price = price;
        this.breakfastIncluded = breakfastIncluded;
        this.petsAllowed = petsAllowed;
        this.dateAvailableFrom = dateAvailableFrom;
        this.country = country;
        this.city = city;
    }

    public Integer getNumberOfGuests() {
        return numberOfGuests;
    }

    public Double getPrice() {
        return price;
    }

    public Boolean getBreakfastIncluded() {
        return breakfastIncluded;
    }

    public Boolean getPetsAllowed() {
        return petsAllowed;
    }

    public Date getDateAvailableFrom() {
        return dateAvailableFrom;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public HashMap<String, Object> getParamMap() throws IllegalAccessException {

        Field[] fields = this.getClass().getDeclaredFields();

        HashMap<String, Object> map = new HashMap<>(fields.length);

        for (Field f : fields) {

            map.put(f.getName(), f.get(this));
        }
        return map;
    }
}
