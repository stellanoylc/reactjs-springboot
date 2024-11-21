package com.spring.springbootdemo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="googlelocation")
public class GoogleLocation {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name="id")
	    private int id;

	    @Column(name="name")
	    private String name;

	    @Column(name="address")
	    private String address;

	    @Column(name="location")
	    private String location;

	    public GoogleLocation() {}
	    
		public GoogleLocation(String name, String address, String location) {			
			this.name = name;
			this.address = address;
			this.location = location;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		@Override
		public String toString() {
			return "GoogleLocation [id=" + id + ", name=" + name + ", address=" + address + ", location=" + location
					+ "]";
		}


}
