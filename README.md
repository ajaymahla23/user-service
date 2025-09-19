# user-service
Microservice Project -User Service
1 Github
2 Lombok

--USERSERVICE
 /register

@PrePersist                                    //TABLE MEIN DATA SAVE HONE SE PEHLA IS AUTOMATICALLY RUN KAR DEGA OR YEH ENTITY MEIN AAYAGA
    public void genterateUserId() {
        if (role != null && id != null) {
            this.userId = role.name().charAt(0) + "-" + String.format("%02d", id);
        }
    }
	
	JWT Token done
	/login
