package com.vithu.uscms.entities;

import java.util.List;

/**
 * @author S.Sugashan
 * @CreatedOn 19th December 2017
 * @Purpose Employee Entity Class
 */
public class Employee {
       
		private Integer id;
        private User user;
        private String dob;
        private String nic;
        private String role;
        private String contact;
        private int addedBy;
        private int regionId;
        private int roleId;
		private String address;
        private String region;
        private List<UserAuthority> authorityList;
        
        //*************************************************************
        //** CONSTRUCTORS
        //*************************************************************
        
        
        public Employee() {
			super();
		}
        
        public Employee(Integer id, User user, String dob, String nic, String role, String contact, int addedBy,
				int regionId, int roleId, String address, String region, List<UserAuthority> authorityList) {
			super();
			this.id = id;
			this.user = user;
			this.dob = dob;
			this.nic = nic;
			this.role = role;
			this.contact = contact;
			this.addedBy = addedBy;
			this.regionId = regionId;
			this.roleId = roleId;
			this.address = address;
			this.region = region;
			this.authorityList = authorityList;
		}

		//*************************************************************
        //** GETTERS AND SETTERS
        //*************************************************************
 
		public int getroleId() {
			return roleId;
		}

		public String getDob() {
			return dob;
		}
		public void setDob(String dob) {
			this.dob = dob;
		}

		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
		
		public int getRegionId() {
			return regionId;
		}

    	public void setRegionId(int regionId) {
    		this.regionId = regionId;
    	}
        
        public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}
        
        public List<UserAuthority> getAuthorityList() {
			return authorityList;
		}

		public void setAuthorityList(List<UserAuthority> authorityList) {
			this.authorityList = authorityList;
		}     
        public int getAddedBy() {
                return addedBy;
        }
        public void setAddedBy(int addedBy) {
                this.addedBy = addedBy;
        }
        public Integer getId() {
                return id;
        }
        public void setId(Integer id) {
                this.id = id;
        }
        public User getUser() {
                return user;
        }
        public void setUser(User user) {
                this.user = user;
        }
        public String getNic() {
                return nic;
        }
        public void setNic(String nic) {
                this.nic = nic;
        }
        public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getContact() {
                return contact;
        }
        public void setContact(String contact) {
                this.contact = contact;
        }
        public String getAddress() {
                return address;
        }
        public void setAddress(String address) {
                this.address = address;
        }        
}
