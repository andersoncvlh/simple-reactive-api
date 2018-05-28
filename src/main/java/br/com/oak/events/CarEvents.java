package br.com.oak.events;

import java.util.Date;

import br.com.oak.models.Car;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarEvents {
	
	private Car model;
	private Date when;
	
}
