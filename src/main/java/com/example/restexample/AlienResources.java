package com.example.restexample;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


//This you can consider as the driver class for this application.

@Path("aliens") // This will appear in the URL of the application & must be called by this name.
public class AlienResources {
	AlienRepository repo = new AlienRepository();

	
	//Function to display all aliens
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Alien> getAliens() {
		System.out.println("Alien get called");
		return repo.getAliens();
	}
	
	
	//Function to display aliens by particular ID
	@GET
	@Path("alien/{id}")
	@Produces(MediaType.APPLICATION_JSON) // Server/Database to client is @produce
	public List<Alien> getAlien(@PathParam("id") int id) {
		return repo.getAlien(id);
	}
	
	//Function to create a new Alien
    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    public String addAlien(Alien alien){
    	
        alien.setName(alien.getName());
        alien.setPoints(alien.getPoints());
                 
        repo.create(alien);
        
        return "Successfully Inserted";
    }
    
    //Function to update an Alien
	@PUT
    @Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public String update(@PathParam("id") int id, Alien alien){
       
        int count = repo.updateEmployee(id, alien);
        if(count==0){
            return "Not a Successful Update.";
        }
        return "Updated Seccessfully!";
	}
	
	//Function to delete an Alien
    @DELETE
    @Path("delete/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteEmployee(@PathParam("id") int id){
       
        int count = repo.delete(id);
        if(count==0){
            return "Bad Request";
        }
        return "ID: "+id +" is Successfully Deleted";
    }	
}




