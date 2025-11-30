package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class Holiday extends BaseEntity{
  
	    @Id
	    @NotBlank
	    private int holiday_id;
	    private  String day;
	    private  String reason;
	    
	    @Enumerated(EnumType.STRING)
	    private  Type type;

	    public enum Type {
	        FESTIVAL, FEDERAL
	    }

		
	    
	    

}
