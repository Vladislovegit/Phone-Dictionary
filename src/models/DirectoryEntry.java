package models;

public class DirectoryEntry {
    private String fullName;
    private String address;
    private String phoneNumber;

    public DirectoryEntry(String full_name, String address, String phone_number) {
        this.fullName = full_name;
        this.address = address;
        this.phoneNumber = phone_number;
    }

    public String getFullName() {
        return fullName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}