package com.func.cosmos.user;

import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Container(containerName = "mycollection")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	private String id;
	private String firstName;
	@PartitionKey
	private String lastName;
	private String address;

	@Override
	public String toString() {
		return String.format("%s %s, %s", firstName, lastName, address);
	}
}