package edu.backend.dto;

public class CustomerDTO {
    private String cid;
    private String name;
    private String address;
//    private String cphone;

    public CustomerDTO() {

    }

    public CustomerDTO(String cid, String name, String address) {
        this.cid = cid;
        this.name = name;
        this.address = address;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

//    public String getCphone() {
//        return cphone;
//    }
//
//    public void setCphone(String cphone) {
//        this.cphone = cphone;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
                "caddress='" + address + '\'' +
                ", cid='" + cid + '\'' +
                ", ncame='" + name + '\'' +
//                ", cphone='" + cphone + '\'' +
                '}';
    }
}